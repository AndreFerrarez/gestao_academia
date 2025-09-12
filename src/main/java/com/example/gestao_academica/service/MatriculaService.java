package com.example.gestao_academica.service;

import com.example.gestao_academica.model.Aluno;
import com.example.gestao_academica.model.Disciplina;
import com.example.gestao_academica.model.Matricula;
import com.example.gestao_academica.repository.AlunoRepository;
import com.example.gestao_academica.repository.DisciplinaRepository;
import com.example.gestao_academica.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired private AlunoRepository alunoRepository;
    @Autowired private DisciplinaRepository disciplinaRepository;

    public Matricula matricular(Long alunoId, Long disciplinaId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setDisciplina(disciplina);
        return matriculaRepository.save(matricula);
    }

    public Matricula atribuirNota(Long matriculaId, Double nota) {
        Matricula matricula = matriculaRepository.findById(matriculaId).orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        matricula.setNota(nota);
        return matriculaRepository.save(matricula);
    }

    public List<Aluno> listarAprovados(Long disciplinaId) {
        return matriculaRepository.findByDisciplinaIdAndNotaGreaterThanEqual(disciplinaId, 7.0)
                .stream()
                .map(Matricula::getAluno)
                .toList();
    }

    public List<Aluno> listarReprovados(Long disciplinaId) {
        return matriculaRepository.findByDisciplinaIdAndNotaLessThan(disciplinaId, 7.0)
                .stream()
                .map(Matricula::getAluno)
                .toList();
    }
}