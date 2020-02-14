package com.esp.banque.dto;

import java.io.Serializable;

public class CompteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer numero;
    private String libelle;
    private String sens;
    private Integer solde;
    private String client;

    public CompteDto() {
    }

    public CompteDto(String libelle, String sens, Integer solde) {
        this.libelle = libelle;
        this.sens = sens;
        this.solde = solde;
    }

    public Integer getNumero() {
        return numero;
    }

    public CompteDto setNumero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public CompteDto setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public String getSens() {
        return sens;
    }

    public CompteDto setSens(String sens) {
        this.sens = sens;
        return this;
    }

    public Integer getSolde() {
        return solde;
    }

    public CompteDto setSolde(Integer solde) {
        this.solde = solde;
        return this;
    }

    public String getClient() {
        return client;
    }

    public CompteDto setClient(String client) {
        this.client = client;
        return this;
    }
}
