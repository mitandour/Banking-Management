package com.esp.banque.service;

import com.esp.banque.dto.ClientDto;

import java.util.List;

public interface ClientService {

    void save(boolean update, ClientDto client, Integer numAgence);
    List<ClientDto> listAll();
    void delete(Integer Clientnum);
}
