package com.esp.banque.service;

import com.esp.banque.dto.CompteDto;

import java.util.List;

public interface CompteService {

    void save(CompteDto compte, Integer numClient);
    List<CompteDto> listAll();
    void delete(Integer numCompte);
    void editLibelle(Integer numCompte, String libelle);
    void updateBalance(Integer montant, Integer numCompte, boolean sens) throws Exception;
}
