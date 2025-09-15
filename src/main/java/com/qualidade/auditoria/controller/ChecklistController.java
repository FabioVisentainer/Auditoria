package com.qualidade.auditoria.controller;

import com.qualidade.auditoria.model.Checklist;
import com.qualidade.auditoria.model.Frases;
import com.qualidade.auditoria.model.Respostas;
import com.qualidade.auditoria.model.enums.EnumConformidades;
import com.qualidade.auditoria.repository.ChecklistRepository;
import com.qualidade.auditoria.repository.FrasesRepository;
import com.qualidade.auditoria.repository.RespostasRepository;
import com.qualidade.auditoria.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/checklist")
public class ChecklistController {

    @Autowired
    private FrasesRepository frasesRepository;

    @Autowired
    private RespostasRepository respostasRepository;

    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String listChecklist(Model model) {
        Checklist checklist = new Checklist();
        List<Frases> frases = frasesRepository.findAll();
        model.addAttribute("checklist", checklist);
        model.addAttribute("frases", frases);
        return "auditorias/checklist";
    }

    @PostMapping("/save")
    public String saveChecklist(Checklist checklist) {
        // Associa cada resposta ao checklist
        if (checklist.getRespostas() != null) {
            for (Respostas r : checklist.getRespostas()) {
                r.setChecklist(checklist);
            }
        }

        checklistRepository.save(checklist); // salva checklist + respostas

        // Envio de e-mail (opcional)
        if (checklist.getRespostas() != null) {
            for (Respostas r : checklist.getRespostas()) {
                if (r.getIsConforme() == EnumConformidades.NAO_CONFORME) {
                    String destinatario = r.getResponsavelAud().getEmail();
                    String assunto = "Nova resposta NÃO conforme registrada no checklist";
                    String mensagem = "Frase: " + r.getFrase().getFrase() +
                            "\nClassificação: " + r.getClassificacao().getDescricao() +
                            "\nConformidade: " + r.getIsConforme().getDescricao();
                    emailService.enviarEmail(destinatario, assunto, mensagem);
                }
            }
        }

        return "redirect:/checklist";
    }
}
