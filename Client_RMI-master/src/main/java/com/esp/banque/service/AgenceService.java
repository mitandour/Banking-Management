package com.esp.banque.service;

import com.esp.banque.dto.AgenceDto;

import java.util.List;

public interface AgenceService {

    void save(AgenceDto agence, boolean update);
    List<AgenceDto> listAll();
    void delete(Integer Agence);
}
