package com.qualidade.auditoria.controller;

import com.qualidade.auditoria.model.Checklist;
import com.qualidade.auditoria.model.Frases;
import com.qualidade.auditoria.model.Respostas;
import com.qualidade.auditoria.repository.ChecklistRepository;
import com.qualidade.auditoria.repository.FrasesRepository;
import com.qualidade.auditoria.repository.RespostasRepository;
//import com.qualidade.auditoria.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
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

//    @Autowired
//    private EmailService emailService; // injeta o serviço

    @GetMapping
    public String listChecklist(Model model) {
        Checklist checklist = new Checklist();
        List<Frases> frases = frasesRepository.findAll();
        model.addAttribute("checklist", checklist);
        model.addAttribute("frases", frases);
        return "auditorias/checklist";
    }

    @PostMapping("/save")
    public String saveResposta(Checklist checklist, List<Respostas> respostas) {
        // Salva o checklist primeiro (agora ele terá um ID válido)
        Checklist checklistSalvo = checklistRepository.save(checklist);

        for (Respostas r : respostas) {
            r.setIdCheckList(checklistSalvo); // associa o checklist já salvo
            respostasRepository.save(r);

            // dispara email para o responsável usando o Enum
            String destinatario = r.getIdResponsavelAud().getEmail();
            String assunto = "Nova resposta registrada no checklist";
            String mensagem = "Frase: " + r.getIdFrase().getFrase() +
                    "\nClassificação: " + r.getClassificacao().getDescricao() +
                    "\nConformidade: " + r.getIsConforme().getDescricao();

            // emailService.enviarEmail(destinatario, assunto, mensagem);
        }

        return "redirect:/checklist";
    }
}
