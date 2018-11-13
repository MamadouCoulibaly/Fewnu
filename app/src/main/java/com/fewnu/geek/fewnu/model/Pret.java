package com.fewnu.geek.fewnu.model;

public class Pret {
    private String nom;
    private Double montant;

    public Pret() {
    }

    public Pret(String nom, Double montant) {
        this.nom = nom;
        this.montant = montant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
}
