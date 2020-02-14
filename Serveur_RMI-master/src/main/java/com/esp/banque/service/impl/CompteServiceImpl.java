package com.esp.banque.service.impl;

import com.esp.banque.dao.CompteDao;
import com.esp.banque.domain.Client;
import com.esp.banque.domain.Compte;
import com.esp.banque.domain.Operation;
import com.esp.banque.dto.CompteDto;
import com.esp.banque.service.ClientService;
import com.esp.banque.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private final CompteDao dao;
    private final ClientService clientService;

    @Autowired
    public CompteServiceImpl(CompteDao compteDao, ClientService clientService){
        this.dao = compteDao;
        this.clientService = clientService;
    }

    @Override
    public void save(CompteDto compte, Integer clientNum) {
        Compte newCompte = new Compte();
        newCompte.setLibelle(compte.getLibelle());
        newCompte.setSens(compte.getSens());
        newCompte.setSolde(compte.getSolde());
        try {
            newCompte.setClient(clientService.getWithNumero(clientNum));
        } catch (Exception e) {
            e.printStackTrace();
        }

        dao.save(newCompte);
    }

    @Override
    public List<CompteDto> listAll() {
        return dao.findAll().stream().map(this::format).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer numCompte) {
            dao.deleteById(numCompte);
    }

    @Override
    public void editLibelle(Integer numCompte, String libelle) {
        Optional<Compte> eCompte = dao.findByNumero(numCompte);
        if (eCompte.isPresent()) {
            Compte compte = eCompte.get();
            compte.setLibelle(libelle);
            dao.save(compte);
        }
    }

    @Override
    //sens true pour créditer et false pour débiter
    public void updateBalance(Integer montant, Integer numCompte, boolean sens) throws Exception {
        Optional<Compte> eCompte = dao.findByNumero(numCompte);
        if (eCompte.isPresent()){
            Compte compte = eCompte.get();
            Integer solde = compte.getSolde();
            if(sens) {
                compte.setSolde(solde + montant);
            }
            else {
                if(solde > 0){
                    compte.setSolde(solde - montant);
                }
                else{
                    throw new Exception("Votre solde est insuffisant");
                }
            }
            dao.save(compte);
        }
        else {
            throw new Exception("Ce compte n\'existe pas");
        }
    }

    @Override
    public List<Operation> getOperationsList() {
        return null;
    }

    @Override
    public Optional<Compte> findByNumero(Integer numero) {
        return dao.findByNumero(numero);
    }

    private CompteDto format(Compte compte){
        return new CompteDto()
                .setNumero(compte.getNumero())
                .setLibelle(compte.getLibelle())
                .setSens(compte.getSens())
                .setSolde(compte.getSolde())
                .setClient(compte.getClient().toString());
    }
}
