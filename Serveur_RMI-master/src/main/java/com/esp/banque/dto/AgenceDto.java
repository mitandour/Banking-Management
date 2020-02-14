package com.esp.banque.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class AgenceDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer numero;
    private String nom;
    private String adresse;
    private ArrayList<ClientDto> clients;

    public AgenceDto() {

    }

    public AgenceDto(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
        clients = new ArrayList<>();
    }

    public AgenceDto(Integer numero, String nom, String adresse) {
        this.numero = numero;
        this.nom = nom;
        this.adresse = adresse;
        clients = new ArrayList<>();
    }

    public Integer getNumero() {
        return numero;
    }

    public AgenceDto setNumero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public AgenceDto setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getAdresse() {
        return adresse;
    }

    public AgenceDto setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }
    public ArrayList<ClientDto> getClients() {
        return clients;
    }

    public AgenceDto setClients(ArrayList<ClientDto> clients) {
        this.clients = clients;
        return this;
    }
}
