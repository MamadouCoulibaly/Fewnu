package com.fewnu.geek.fewnu.model;

public class Depense {
    private String designation;
    private Double prix;

    public Depense() {
    }

    public Depense(String designation, Double prix) {
        this.designation = designation;
        this.prix = prix;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
}
