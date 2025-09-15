package com.qualidade.auditoria.controller;

import com.qualidade.auditoria.model.Checklist;
import com.qualidade.auditoria.model.Respostas;
import com.qualidade.auditoria.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class DashboardController {

    @Autowired
    private ChecklistRepository checklistRepository;

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
}
