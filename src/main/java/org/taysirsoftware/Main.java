package org.taysirsoftware;

import com.taysirsoftware.store.entities.*;
import com.taysirsoftware.store.services.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bismilah, Testing the entire application...");

        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            // Initialize EntityManagerFactory
            emf = Persistence.createEntityManagerFactory("storePU");
            em = emf.createEntityManager();

            System.out.println("Baxna, Database connection successful!");

            // Initialize services
            CategoryService categoryService = new CategoryService(em);
            ProductService productService = new ProductService(em);
            ClientService clientService = new ClientService(em);
            CommandeService commandeService = new CommandeService(em);
            LigneCommandeService ligneCommandeService = new LigneCommandeService(em);

            // Test Category
            System.out.println("\n*** Testing Category ***");
            Category newCategory = new Category();
            newCategory.setLibelle("Smartphone");
            newCategory.setDescription("Electronic devices and gadgets");
            categoryService.addCategory(newCategory);

            List<Category> categories = categoryService.getAllCategories();
            System.out.println("Categories: " + categories);

            // Test Product
            System.out.println("\n*** Testing Product ***");
            Product newProduct = new Product();
            newProduct.setDesignation("iPhone 15 Pro");
            newProduct.setPrixUnitaire(500000.0);
            newProduct.setQuantiteStock(20);
            newProduct.setCategory(newCategory);
            productService.addProduct(newProduct);

            List<Product> products = productService.getAllProducts();
            System.out.println("Products: " + products);

            // Test Client
            System.out.println("\n*** Testing Client ***");
            Client newClient = new Client();
            newClient.setNom("Doucouré");
            newClient.setPrenom("Ndiaye");
            newClient.setAdresse("128, Guédiawaye, Dakar Street");
            newClient.setEmail("contact@taysirsoftware.com");
            newClient.setTelephone1("+123456789");
            newClient.setTelephone2("+987654321");
            newClient.setDateNaissance(LocalDate.of(2000, 5, 20));
            clientService.addClient(newClient);

            List<Client> clients = clientService.getAllClients();
            System.out.println("Clients: " + clients);

            // Test Commande
            System.out.println("\n*** Testing Commande ***");
            Commande newCommande = new Commande();
            newCommande.setDateCommande(LocalDate.now());
            newCommande.setClient(newClient);
            commandeService.addCommande(newCommande);

            List<Commande> commandes = commandeService.getAllCommandes();
            System.out.println("Commandes: " + commandes);

            // Test Ligne Commande
            System.out.println("\n*** Testing Ligne Commande ***");
            LigneCommande newLigneCommande = new LigneCommande();
            newLigneCommande.setCommande(newCommande);
            newLigneCommande.setProduct(newProduct);
            newLigneCommande.setQuantite_stock(20);
            ligneCommandeService.addLigneCommande(newLigneCommande);

            List<LigneCommande> ligneCommandes = ligneCommandeService.getAllLigneCommandes();
            System.out.println("Ligne Commandes: " + ligneCommandes);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}