package com.example.gestao_academica.repository;

import com.example.gestao_academica.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findByDisciplinaIdAndNotaGreaterThanEqual(Long disciplinaId, Double nota);
    List<Matricula> findByDisciplinaIdAndNotaLessThan(Long disciplinaId, Double nota);
}