package com.esp.banque.service;

import com.esp.banque.domain.Client;
import com.esp.banque.dto.ClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    void save(boolean update, ClientDto client, Integer numAgence);
    List<ClientDto> listAll();
    void delete(Integer Clientnum);

    Client getWithNumero(Integer numero) throws Exception;
}
