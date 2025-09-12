package com.example.gestao_academica.service;

import com.example.gestao_academica.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProfessorUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return professorRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Professor não encontrado"));
    }
}
