package com.example.gestao_academica.controller;

import com.example.gestao_academica.model.Disciplina;
import com.example.gestao_academica.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping
    public Disciplina criar(@Valid @RequestBody Disciplina disciplina) {
        return disciplinaService.salvar(disciplina);
    }

    @GetMapping
    public List<Disciplina> listar() {
        return disciplinaService.listarTodas();
    }
}
