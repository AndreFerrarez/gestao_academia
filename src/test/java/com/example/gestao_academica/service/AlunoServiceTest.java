package com.example.gestao_academica.service;

import com.example.gestao_academica.model.Aluno;
import com.example.gestao_academica.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    void deveSalvarAlunoComSucesso() {
        Aluno aluno = new Aluno();
        aluno.setNome("João da Silva");

        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

        Aluno alunoSalvo = alunoService.salvar(aluno);

        assertNotNull(alunoSalvo);
        assertEquals("João da Silva", alunoSalvo.getNome());
    }

    @Test
    void deveListarTodosOsAlunos() {
        List<Aluno> alunos = List.of(new Aluno(), new Aluno());
        when(alunoRepository.findAll()).thenReturn(alunos);

        List<Aluno> resultado = alunoService.listarTodos();

        assertEquals(2, resultado.size());
    }
}