package com.esp.banque.service.impl;

import com.esp.banque.dao.OperationDao;
import com.esp.banque.domain.Compte;
import com.esp.banque.domain.Operation;
import com.esp.banque.dto.OperationDto;
import com.esp.banque.service.CompteService;
import com.esp.banque.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {

    private final OperationDao operationDao;
    private final CompteService compteService;

    @Autowired
    public OperationServiceImpl(OperationDao operationDao, CompteService compteService){
        this.operationDao = operationDao;
        this.compteService = compteService;
    }

    private void save(OperationDto operation, Compte compte) {
        Operation newOperation = new Operation();
        newOperation.setDate(new Date());
        newOperation.setLibelle(operation.getLibelle());
        newOperation.setMontant(operation.getMontant());
        newOperation.setSens(operation.getSens());
        newOperation.setCompte(compte);
        operationDao.save(newOperation);
    }

    @Override
    public List<OperationDto> listAll() {
        return operationDao.findAll().stream().map(this::format).collect(Collectors.toList());
    }

    @Override
    // sens est true pour CR et false pour DB
    public void mouvement(OperationDto dto, Integer numCompte) throws Exception {
        Optional<Compte> eCompte = compteService.findByNumero(numCompte);
        if(eCompte.isPresent()){
            compteService.updateBalance(dto.getMontant(), numCompte,
                    dto.getSens().equals("CR") || (!dto.getSens().equals("DB")));
            save(dto, eCompte.get());
        }
        throw new Exception("Ce compte n\'existe pas");
    }

    @Override
    public void virement(Integer montant, Compte crediteur, Compte debiteur) throws Exception {
        /*OperationDto operation = null;
        Optional<Compte> eCrediteur = compteService.findByNumero(crediteur.getNumero());
        Optional<Compte> eDebiteur = compteService.findByNumero(debiteur.getNumero());

        if(eCrediteur.isPresent() && eDebiteur.isPresent()){
            mouvement(montant,crediteur,true);
            mouvement(montant,debiteur,false);
        }
        else {
            throw new Exception("Numéro(s) de compte erroné(s)");
        }*/
    }

    private OperationDto format (Operation operation){
        return new OperationDto()
                .setNumero(operation.getNumero())
                .setLibelle(operation.getLibelle())
                .setSens(operation.getSens())
                .setMontant(operation.getMontant())
                .setDate(operation.getDate().toString())
                .setCompte(operation.getCompte().getNumero());
    }
}
