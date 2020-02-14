package com.esp.banque.dto;

import java.io.Serializable;

public class OperationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer numero;
    private String sens;
    private String libelle;
    private Integer montant;
    private String date;
    private Integer compte;

    public OperationDto(String sens, String libelle, Integer montant) {
        this.libelle = libelle;
        this.sens = sens;
        this.montant = montant;
    }


    public Integer getNumero() {
        return numero;
    }

    public OperationDto setNumero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public String getSens() {
        return sens;
    }

    public OperationDto setSens(String sens) {
        this.sens = sens;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public OperationDto setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public Integer getMontant() {
        return montant;
    }

    public OperationDto setMontant(Integer montant) {
        this.montant = montant;
        return this;
    }

    public String getDate() {
        return date;
    }

    public OperationDto setDate(String date) {
        this.date = date;
        return this;
    }

    public Integer getCompte() {
        return compte;
    }

    public OperationDto setCompte(Integer compte) {
        this.compte = compte;
        return this;
    }
}
