package com.esp.banque.service;

import com.esp.banque.domain.Compte;
import com.esp.banque.domain.Operation;
import com.esp.banque.dto.OperationDto;

import java.util.List;

public interface OperationService {

    List<OperationDto> listAll();
    void mouvement(OperationDto dto, Integer numCompte) throws Exception;
    void virement( Integer montant, Compte crediteur, Compte debiteur) throws Exception;
}
