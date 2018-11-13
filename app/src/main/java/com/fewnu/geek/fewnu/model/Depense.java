package com.fewnu.geek.fewnu.model;

public class Depense {
    private String designation;
    private Double prix;
    private String id;

    public Depense() {
    }



    public Depense( String id, String designation, Double prix) {
        this.designation = designation;
        this.prix = prix;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
