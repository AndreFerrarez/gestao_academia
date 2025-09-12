package com.example.gestao_academica.service;

import com.example.gestao_academica.model.Disciplina;
import com.example.gestao_academica.repository.DisciplinaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DisciplinaServiceTest {

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @InjectMocks
    private DisciplinaService disciplinaService;

    @Test
    void deveSalvarDisciplinaComSucesso() {
        // Cenário
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Cálculo I");
        disciplina.setCodigo("MAT123");

        when(disciplinaRepository.save(any(Disciplina.class))).thenReturn(disciplina);

        // Ação
        Disciplina disciplinaSalva = disciplinaService.salvar(disciplina);

        // Verificação
        assertNotNull(disciplinaSalva);
        assertEquals("Cálculo I", disciplinaSalva.getNome());
        verify(disciplinaRepository, times(1)).save(disciplina);
    }

    @Test
    void deveListarTodasAsDisciplinas() {
        // Cenário
        Disciplina d1 = new Disciplina();
        d1.setNome("Cálculo I");
        Disciplina d2 = new Disciplina();
        d2.setNome("Física I");
        List<Disciplina> listaDeDisciplinas = List.of(d1, d2);

        when(disciplinaRepository.findAll()).thenReturn(listaDeDisciplinas);

        // Ação
        List<Disciplina> resultado = disciplinaService.listarTodas();

        // Verificação
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Cálculo I", resultado.get(0).getNome());
        verify(disciplinaRepository, times(1)).findAll();
    }
}