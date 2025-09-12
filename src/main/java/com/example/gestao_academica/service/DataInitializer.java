package com.example.gestao_academica.service;

import com.example.gestao_academica.model.Professor;
import com.example.gestao_academica.repository.ProfessorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DataInitializer {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (professorRepository.findByUsername("professor").isEmpty()) {
            Professor professor = new Professor();
            professor.setUsername("professor");
            professor.setPassword(passwordEncoder.encode("senha123"));
            professorRepository.save(professor);
        }
    }
}