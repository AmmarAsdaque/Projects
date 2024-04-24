/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ds.dsproject;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCanteen extends JFrame {

    private JTextField nameField;
    private JTextField locationField;
    private JTextField managerIdField;

    public AddCanteen() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        // Set a modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Add Canteen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Add some padding

        JLabel nameLabel = new JLabel("Name:");
        JLabel locationLabel = new JLabel("Location:");
        JLabel managerIdLabel = new JLabel("Manager ID:");

        nameField = new JTextField(15);
        locationField = new JTextField(15);
        managerIdField = new JTextField(15);

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        // Customize button appearance
        submitButton.setBackground(new Color(34, 167, 240)); // Blue color
        submitButton.setForeground(Color.BLACK);
        backButton.setBackground(new Color(192, 57, 43)); // Red color
        backButton.setForeground(Color.BLACK);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCanteenToDatabase();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Add();
                dispose(); // Close the current form
            }
        });

        // Add components to the panel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(locationLabel, gbc);

        gbc.gridx = 1;
        panel.add(locationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(managerIdLabel, gbc);

        gbc.gridx = 1;
        panel.add(managerIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Full width for buttons
        panel.add(submitButton, gbc);

        gbc.gridy = 4;
        panel.add(backButton, gbc);

        add(panel);

        setLocationRelativeTo(null);
    }


    private void addCanteenToDatabase() {
        String url = "jdbc:mysql://127.0.0.1:3306/mydb";
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "INSERT INTO Canteen (name, location, manager_id,status,total_sales) VALUES (?, ?, ?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nameField.getText());
                preparedStatement.setString(2, locationField.getText());
                preparedStatement.setString(3, managerIdField.getText());
                preparedStatement.setInt(4, 1);
                preparedStatement.setFloat(5, 0);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Canteen added successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add canteen. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database.");
        }
    }
    private void clearFields() {
        nameField.setText("");
        locationField.setText("");
        managerIdField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddCanteen().setVisible(true));
    }
}
