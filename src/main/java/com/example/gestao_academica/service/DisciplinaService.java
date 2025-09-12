package com.example.gestao_academica.service;

import com.example.gestao_academica.model.Disciplina;
import com.example.gestao_academica.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina salvar(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }
}