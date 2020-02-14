package com.esp.banque.service;

import com.esp.banque.dto.OperationDto;

import java.util.List;

public interface OperationService {

    List<OperationDto> listAll();
    void mouvement(OperationDto dto, Integer numCompte) throws Exception;
}
