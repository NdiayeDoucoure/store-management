package com.taysirsoftware.store.entities;

import jakarta.persistence.*;

@Entity
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Product product;

    private int quantite_stock;

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantite_stock() {
        return quantite_stock;
    }

    public void setQuantite_stock(int quantite_stock) {
        this.quantite_stock = quantite_stock;
    }

    @Override
    public String toString() {
        return "LigneCommande{" +
                "id=" + id +
                ", commande=" + commande +
                ", product=" + product +
                ", quantite_stock=" + quantite_stock +
                '}';
    }
}
