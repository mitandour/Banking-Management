package com.esp.banque.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nom;
    private String prenom;
    private Integer numero;
    private String agence;
    private ArrayList<Integer> comptes;

    public ClientDto() {}

    public ClientDto(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public ClientDto(String nom, String prenom, Integer numero, String agence) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.agence = agence;
        comptes = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public ClientDto setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }

    public ClientDto setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public Integer getNumero() {
        return numero;
    }

    public ClientDto setNumero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public String getAgence() {
        return agence;
    }

    public ClientDto setAgence(String agence) {
        this.agence = agence;
        return this;
    }

    public ArrayList<Integer> getComptes() {
        return comptes;
    }

    public ClientDto setComptes(ArrayList<Integer> comptes) {
        this.comptes = comptes;
        return this;
    }
}
