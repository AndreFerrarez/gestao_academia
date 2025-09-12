package com.example.gestao_academica.controller;

import com.example.gestao_academica.model.Aluno;
import com.example.gestao_academica.model.Matricula;
import com.example.gestao_academica.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;

    @PostMapping
    public Matricula matricular(@RequestParam Long alunoId, @RequestParam Long disciplinaId) {
        return matriculaService.matricular(alunoId, disciplinaId);
    }

    @PutMapping("/{id}/nota")
    public Matricula atribuirNota(@PathVariable Long id, @RequestParam Double nota) {
        return matriculaService.atribuirNota(id, nota);
    }

    @GetMapping("/disciplina/{disciplinaId}/aprovados")
    public List<Aluno> listarAprovados(@PathVariable Long disciplinaId) {
        return matriculaService.listarAprovados(disciplinaId);
    }

    @GetMapping("/disciplina/{disciplinaId}/reprovados")
    public List<Aluno> listarReprovados(@PathVariable Long disciplinaId) {
        return matriculaService.listarReprovados(disciplinaId);
    }
}