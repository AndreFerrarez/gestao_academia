package com.example.gestao_academica.service;

import com.example.gestao_academica.model.Aluno;
import com.example.gestao_academica.model.Disciplina;
import com.example.gestao_academica.model.Matricula;
import com.example.gestao_academica.repository.AlunoRepository;
import com.example.gestao_academica.repository.DisciplinaRepository;
import com.example.gestao_academica.repository.MatriculaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatriculaServiceTest {

    @Mock
    private MatriculaRepository matriculaRepository;
    @Mock
    private AlunoRepository alunoRepository;
    @Mock
    private DisciplinaRepository disciplinaRepository;

    @InjectMocks
    private MatriculaService matriculaService;

    @Test
    void deveMatricularAlunoEmDisciplinaComSucesso() {
        // Cenário
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        Disciplina disciplina = new Disciplina();
        disciplina.setId(1L);
        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setDisciplina(disciplina);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(disciplinaRepository.findById(1L)).thenReturn(Optional.of(disciplina));
        when(matriculaRepository.save(any(Matricula.class))).thenReturn(matricula);

        // Ação
        Matricula resultado = matriculaService.matricular(1L, 1L);

        // Verificação
        assertNotNull(resultado);
        assertEquals(1L, resultado.getAluno().getId());
        assertEquals(1L, resultado.getDisciplina().getId());
        verify(matriculaRepository, times(1)).save(any(Matricula.class));
    }

    @Test
    void deveLancarExcecaoAoMatricularAlunoInexistente() {
        // Cenário
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Ação e Verificação
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            matriculaService.matricular(99L, 1L);
        });
        assertEquals("Aluno não encontrado", exception.getMessage());
    }

    @Test
    void deveAtribuirNotaComSucesso() {
        // Cenário
        Matricula matricula = new Matricula();
        matricula.setId(1L);
        when(matriculaRepository.findById(1L)).thenReturn(Optional.of(matricula));
        when(matriculaRepository.save(any(Matricula.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ação
        Matricula resultado = matriculaService.atribuirNota(1L, 8.5);

        // Verificação
        assertNotNull(resultado);
        assertEquals(8.5, resultado.getNota());
        verify(matriculaRepository, times(1)).save(matricula);
    }

    @Test
    void deveListarAlunosAprovados() {
        // Cenário
        Aluno alunoAprovado = new Aluno();
        alunoAprovado.setId(1L);
        alunoAprovado.setNome("Aprovado");
        Matricula matriculaAprovado = new Matricula();
        matriculaAprovado.setAluno(alunoAprovado);
        matriculaAprovado.setNota(7.0);

        when(matriculaRepository.findByDisciplinaIdAndNotaGreaterThanEqual(1L, 7.0))
                .thenReturn(List.of(matriculaAprovado));

        // Ação
        List<Aluno> aprovados = matriculaService.listarAprovados(1L);

        // Verificação
        assertNotNull(aprovados);
        assertEquals(1, aprovados.size());
        assertEquals("Aprovado", aprovados.get(0).getNome());
    }

    @Test
    void deveListarAlunosReprovados() {
        // Cenário
        Aluno alunoReprovado = new Aluno();
        alunoReprovado.setId(2L);
        alunoReprovado.setNome("Reprovado");
        Matricula matriculaReprovado = new Matricula();
        matriculaReprovado.setAluno(alunoReprovado);
        matriculaReprovado.setNota(6.9);

        when(matriculaRepository.findByDisciplinaIdAndNotaLessThan(1L, 7.0))
                .thenReturn(List.of(matriculaReprovado));

        // Ação
        List<Aluno> reprovados = matriculaService.listarReprovados(1L);

        // Verificação
        assertNotNull(reprovados);
        assertEquals(1, reprovados.size());
        assertEquals("Reprovado", reprovados.get(0).getNome());
    }
}