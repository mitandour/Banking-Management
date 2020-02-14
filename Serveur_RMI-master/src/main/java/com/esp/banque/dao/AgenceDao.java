package com.esp.banque.dao;

import com.esp.banque.domain.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgenceDao extends JpaRepository <Agence, Integer> {

    Optional<Agence> findByNumero(Integer numero);

}
