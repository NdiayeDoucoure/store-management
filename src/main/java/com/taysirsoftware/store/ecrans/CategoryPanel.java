package com.taysirsoftware.store.ecrans;

import com.taysirsoftware.store.entities.Category;

import jakarta.persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoryPanel extends JFrame {
    private EntityManagerFactory emf;
    private EntityManager em;

    private JTextField txtLibelle, txtDescription;
    private JButton btnAdd, btnUpdate, btnDelete, btnList;
    private JTextArea txtOutput;

    public CategoryPanel() {
        emf = Persistence.createEntityManagerFactory("storePU");
        em = emf.createEntityManager();

        setTitle("Category Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Libelle:"));
        txtLibelle = new JTextField();
        inputPanel.add(txtLibelle);

        inputPanel.add(new JLabel("Description:"));
        txtDescription = new JTextField();
        inputPanel.add(txtDescription);

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        inputPanel.add(btnAdd);
        inputPanel.add(btnUpdate);

        add(inputPanel, BorderLayout.NORTH);

        add(getContentPanel(), BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        btnDelete = new JButton("Delete");
        btnList = new JButton("List All");
        actionPanel.add(btnDelete);
        actionPanel.add(btnList);
        add(actionPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(new AddCategoryListener());
        btnUpdate.addActionListener(new UpdateCategoryListener());
        btnDelete.addActionListener(new DeleteCategoryListener());
        btnList.addActionListener(new ListCategoryListener());
    }

    public JPanel getContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        contentPanel.add(new JScrollPane(txtOutput), BorderLayout.CENTER);

        return contentPanel;
    }

    // Listener pour ajouter une catégorie
    private class AddCategoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String libelle = txtLibelle.getText();
            String description = txtDescription.getText();

            if (libelle.isEmpty() || description.isEmpty()) {
                txtOutput.setText("Please fill in all fields!");
                return;
            }

            Category category = new Category();
            category.setLibelle(libelle);
            category.setDescription(description);

            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();

            txtOutput.setText("Category added successfully!");
        }
    }

    // Listener pour mettre à jour une catégorie
    private class UpdateCategoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String libelle = txtLibelle.getText();
            String description = txtDescription.getText();

            if (libelle.isEmpty() || description.isEmpty()) {
                txtOutput.setText("Please fill in all fields!");
                return;
            }

            em.getTransaction().begin();
            List<Category> categories = em.createQuery("SELECT c FROM Category c WHERE c.libelle = :libelle", Category.class)
                    .setParameter("libelle", libelle)
                    .getResultList();

            if (categories.isEmpty()) {
                txtOutput.setText("Category not found!");
                em.getTransaction().rollback();
                return;
            }

            Category category = categories.get(0);
            category.setDescription(description);
            em.merge(category);
            em.getTransaction().commit();

            txtOutput.setText("Category updated successfully!");
        }
    }

    // Listener pour supprimer une catégorie
    private class DeleteCategoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String libelle = txtLibelle.getText();

            if (libelle.isEmpty()) {
                txtOutput.setText("Please enter the category's libelle!");
                return;
            }

            em.getTransaction().begin();
            List<Category> categories = em.createQuery("SELECT c FROM Category c WHERE c.libelle = :libelle", Category.class)
                    .setParameter("libelle", libelle)
                    .getResultList();

            if (categories.isEmpty()) {
                txtOutput.setText("Category not found!");
                em.getTransaction().rollback();
                return;
            }

            Category category = categories.get(0);
            em.remove(category);
            em.getTransaction().commit();

            txtOutput.setText("Category deleted successfully!");
        }
    }

    // Listener pour lister toutes les catégories
    private class ListCategoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Category> categories = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();

            StringBuilder output = new StringBuilder("Categories:\n");
            for (Category category : categories) {
                output.append("ID: ").append(category.getIdcategorie())
                        .append(", Libelle: ").append(category.getLibelle())
                        .append(", Description: ").append(category.getDescription())
                        .append("\n");
            }
            txtOutput.setText(output.toString());
        }
    }

    // Méthode pour fermer les ressources
    public void closeResources() {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CategoryPanel screen = new CategoryPanel();
            screen.setVisible(true);
        });
    }
}