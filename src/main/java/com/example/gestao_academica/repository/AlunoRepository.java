package com.example.gestao_academica.repository;

import com.example.gestao_academica.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {}