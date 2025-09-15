package com.qualidade.auditoria.controller;

import com.qualidade.auditoria.model.Checklist;
import com.qualidade.auditoria.model.Respostas;
import com.qualidade.auditoria.model.enums.EnumConformidades;
import com.qualidade.auditoria.model.enums.EnumResponsaveis;
import com.qualidade.auditoria.repository.ChecklistRepository;
import com.qualidade.auditoria.repository.RespostasRepository;
import com.qualidade.auditoria.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private RespostasRepository respostaRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String dashboard(Model model) {
        List<Checklist> checklists = checklistRepository.findAll();

        List<Map<String, Object>> resumos = new ArrayList<>();

        for (Checklist c : checklists) {
            long conformes = c.getRespostas().stream()
                    .filter(r -> r.getIsConforme() != null && r.getIsConforme().name().equals("CONFORME"))
                    .count();
            long naoConformes = c.getRespostas().stream()
                    .filter(r -> r.getIsConforme() != null && r.getIsConforme().name().equals("NAO_CONFORME"))
                    .count();
            long naoAplicaveis = c.getRespostas().stream()
                    .filter(r -> r.getIsConforme() != null && r.getIsConforme().name().equals("NAO_APLICAVEL"))
                    .count();

            double aderencia = (conformes + naoConformes) > 0 ?
                    ((double) conformes / (conformes + naoConformes)) * 100 : 0;

            Map<String, Object> resumo = new HashMap<>();
            resumo.put("checklist", c);
            resumo.put("conformes", conformes);
            resumo.put("naoConformes", naoConformes);
            resumo.put("naoAplicaveis", naoAplicaveis);
            resumo.put("aderencia", aderencia);

            resumos.add(resumo);
        }

        model.addAttribute("resumos", resumos);
        return "dashboard";
    }

    @GetMapping("/checklist/detalhes")
    public String detalhesChecklist(Integer id, Model model) {
        Checklist checklist = checklistRepository.findById(id).orElse(null);
        if (checklist != null) {
            // Filtra apenas respostas não conformes
            List<Respostas> naoConformes = checklist.getRespostas().stream()
                    .filter(r -> r.getIsConforme() != null && r.getIsConforme().name().equals("NAO_CONFORME"))
                    .toList();

            model.addAttribute("checklist", checklist);
            model.addAttribute("naoConformes", naoConformes);
        }
        return "detalhes";
    }


    @PostMapping("/checklist/resolver/{idResposta}")
    public String marcarResolvido(@PathVariable Integer idResposta) {
        Respostas resposta = respostaRepository.findById(idResposta)
                .orElseThrow(() -> new RuntimeException("Resposta não encontrada"));

        resposta.setIsConforme(EnumConformidades.CONFORME); // muda para conforme
        respostaRepository.save(resposta);

        return "redirect:/dashboard/checklist/detalhes?id=" + resposta.getChecklist().getId();    }

    @PostMapping("/checklist/naoResolvido/{idResposta}")
    public String marcarNaoResolvido(@PathVariable Integer idResposta) {
        Respostas resposta = respostaRepository.findById(idResposta)
                .orElseThrow(() -> new RuntimeException("Resposta não encontrada"));

        resposta.setResponsavelAud(EnumResponsaveis.GERPRO);
        respostaRepository.save(resposta);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataResolucaoFormatada = resposta.getDataResolucao().format(formatter);

        // Monta e envia e-mail
        String destinatario = EnumResponsaveis.GERPRO.getEmail(); // gerente
        String assunto = "Resposta NÃO resolvida no checklist";
        String mensagem = "Frase: " + resposta.getFrase().getFrase() +
                "\nClassificação: " + resposta.getClassificacao().getDescricao() +
                "\nData para a resolução: " + dataResolucaoFormatada +
                "\nConformidade: " + resposta.getIsConforme().getDescricao();
        emailService.enviarEmail(destinatario, assunto, mensagem);

        return "redirect:/dashboard/checklist/detalhes?id=" + resposta.getChecklist().getId();    }
}
