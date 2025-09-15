package com.qualidade.auditoria.controller;

import com.qualidade.auditoria.model.Checklist;
import com.qualidade.auditoria.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class DashboardController {

    @Autowired
    private ChecklistRepository checklistRepository;

    @GetMapping
    public String dashboard(Model model) {
        List<Checklist> checklists = checklistRepository.findAll();
        model.addAttribute("checklists", checklists);
        return "dashboard";
    }
}
