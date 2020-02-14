package com.esp.banque.service;

import com.esp.banque.domain.Compte;
import com.esp.banque.domain.Operation;
import com.esp.banque.dto.CompteDto;

import java.util.List;
import java.util.Optional;

public interface CompteService {

    void save(CompteDto compte, Integer numClient);
    List<CompteDto> listAll();
    void delete(Integer numCompte);
    void editLibelle(Integer numCompte, String libelle);
    void updateBalance(Integer montant, Integer numCompte, boolean sens) throws Exception;
    List<Operation> getOperationsList();

    Optional<Compte> findByNumero(Integer numero);
}
