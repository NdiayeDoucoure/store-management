package com.taysirsoftware.store.entities;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproduit;

    private String designation;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    @Column(name = "quantite_stock")
    private Integer quantiteStock;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Category category;

    // Getters and Setters

    public Long getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(Long idproduit) {
        this.idproduit = idproduit;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(Integer quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idproduit=" + idproduit +
                ", designation='" + designation + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", quantiteStock=" + quantiteStock +
                ", category=" + category +
                '}';
    }
}
