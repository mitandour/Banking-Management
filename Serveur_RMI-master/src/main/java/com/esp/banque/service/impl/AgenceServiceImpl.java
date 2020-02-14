package com.esp.banque.service.impl;

import com.esp.banque.dao.AgenceDao;
import com.esp.banque.domain.Agence;
import com.esp.banque.dto.AgenceDto;
import com.esp.banque.service.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgenceServiceImpl implements AgenceService {

    private final AgenceDao dao;

    @Autowired
    public AgenceServiceImpl(AgenceDao AgenceDao){
        this.dao = AgenceDao;
    }

    @Override
    public void save(AgenceDto agence, boolean update) {
        Agence newAgence = null;
        if(update){
            Optional<Agence> eAgence = dao.findByNumero(agence.getNumero());
            if(eAgence.isPresent()){
                newAgence = eAgence.get();
            }
        }
        else{
            newAgence = new Agence();
        }
        newAgence.setNom(agence.getNom());
        newAgence.setAdresse(agence.getAdresse());

        dao.save(newAgence);

    }

    @Override
    public List<AgenceDto> listAll() {
        return dao.findAll().stream().map(this::format).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer agence) {
        dao.deleteById(agence);
    }

    private AgenceDto format(Agence agence){
        return new AgenceDto()
                .setNumero(agence.getNumero())
                .setNom(agence.getNom())
                .setAdresse(agence.getAdresse());
    }
}
