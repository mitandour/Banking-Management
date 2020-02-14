package com.esp.banque.service.impl;

import com.esp.banque.dao.AgenceDao;
import com.esp.banque.dao.ClientDao;
import com.esp.banque.domain.Client;
import com.esp.banque.dto.ClientDto;
import com.esp.banque.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientDao dao;
    private final AgenceDao agenceDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao, AgenceDao agenceDao) {
        this.dao = clientDao;
        this.agenceDao = agenceDao;
    }

    @Override
    public void save(boolean update, ClientDto clientDto, Integer numAgence) {
        Client client = null;
        if (update) {
            Optional<Client> oClient = dao.findByNumero(clientDto.getNumero());
            if (oClient.isPresent())
                client = oClient.get();
        } else {
            client = new Client();
        }
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setAgence(agenceDao.findByNumero(numAgence).get());
        dao.save(client);
    }

    @Override
    public List<ClientDto> listAll() {
       /* return dao.findAll().stream().map(client -> {
            return format(client)
        });*/
        return dao.findAll().stream().map(this::format).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer Clientnum) {
        dao.deleteById(Clientnum);
    }

    @Override
    public Client getWithNumero(Integer numero) throws Exception {
        Optional<Client> optionalClient = dao.findByNumero(numero);
        if (optionalClient.isPresent())
            return optionalClient.get();
        else
            throw new Exception("Ce client n\'existe pas");
    }

    private ClientDto format(Client client) {
        return new ClientDto()
                .setNom(client.getNom())
                .setPrenom(client.getPrenom())
                .setNumero(client.getNumero())
                .setAgence(client.getAgence() == null ? null : client.getAgence().getNom());
    }
}
