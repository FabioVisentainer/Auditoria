package com.qualidade.auditoria.controller;

import com.qualidade.auditoria.model.enums.EnumResponsaveis;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auditores")
public class AuditoresController {

    @GetMapping
    public String listAuditores(Model model) {
        model.addAttribute("auditores", EnumResponsaveis.values()); // envia todos os enums
        return "auditores";
    }
}
