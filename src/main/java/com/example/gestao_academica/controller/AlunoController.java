package com.example.gestao_academica.controller;

import com.example.gestao_academica.model.Aluno;
import com.example.gestao_academica.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public Aluno criar(@Valid @RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    @GetMapping
    public List<Aluno> listar() {
        return alunoService.listarTodos();
    }
}
