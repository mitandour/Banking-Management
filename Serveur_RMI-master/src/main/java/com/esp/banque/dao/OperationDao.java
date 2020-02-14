package com.esp.banque.dao;

import com.esp.banque.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationDao extends JpaRepository<Operation, Integer> {

    Optional<Operation> findByNumero(Integer numero);
}
