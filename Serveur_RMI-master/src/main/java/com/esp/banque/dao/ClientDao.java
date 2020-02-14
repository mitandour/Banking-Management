package com.esp.banque.dao;

import com.esp.banque.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientDao extends JpaRepository<Client, Integer> {

    Optional<Client> findByNumero(Integer numero);
}
