package com.qualidade.auditoria.controller;

import com.qualidade.auditoria.model.Respostas;
import com.qualidade.auditoria.repository.RespostasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;


@Controller
@RequestMapping("/")
public class DashboardController {

    @Autowired
    private RespostasRepository respostasRepository;

    @GetMapping
    public String listRespostas(Model model) {
        List<Respostas> respostas = respostasRepository.findAll();
        model.addAttribute(respostas);
        return "/dashboard";
    }

}
