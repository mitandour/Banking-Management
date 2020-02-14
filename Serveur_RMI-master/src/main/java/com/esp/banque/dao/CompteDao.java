package com.esp.banque.dao;

import com.esp.banque.domain.Compte;
import com.esp.banque.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompteDao extends JpaRepository<Compte, Integer> {


    Optional<Compte> findByNumero(Integer numero);
}
