/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ds.dsproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Credentials extends JFrame {
    private JTable credentialsTable;
    private DefaultTableModel tableModel;
    private JTextField usernameField, passwordField;
    private javax.swing.JOptionPane jOptionPane1;

    public Credentials() {
        initComponents();
        setVisible(true);
        loadData();
    }

    private void initComponents() {
        jOptionPane1 = new javax.swing.JOptionPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Credentials Viewer");
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(230, 230, 255)); // Light purple background
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Username");
        tableModel.addColumn("Login ID");
        tableModel.addColumn("Password");

        credentialsTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(credentialsTable);
        tableScrollPane.setBackground(new Color(255, 255, 255)); // White background

        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        JPanel detailsPanel = new JPanel(new GridLayout(4, 2));
        detailsPanel.setBackground(new Color(200, 220, 255)); // Light blue background

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JTextField();
        JButton updateButton = new JButton("Update");
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Admin(); 
            }
        });


        detailsPanel.add(usernameLabel);
        detailsPanel.add(usernameField);
        detailsPanel.add(passwordLabel);
        detailsPanel.add(passwordField);

        updateButton.setBackground(new Color(100, 180, 100)); // Light green background
        updateButton.setForeground(Color.WHITE); // White text
        updateButton.setFocusPainted(false); // Remove focus border

        updateButton.addActionListener(e -> updateCredentials());

        detailsPanel.add(updateButton);
        detailsPanel.add(backButton);

        mainPanel.add(detailsPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void loadData() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/mydb";
            Class.forName("java.sql.DriverManager");
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Login");
            tableModel.setRowCount(0);
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("username"));
                row.add(resultSet.getString("login_id"));
                row.add(resultSet.getString("password"));
                tableModel.addRow(row);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            jOptionPane1.showMessageDialog(this,e.getMessage());
        }
    }

    private void updateCredentials() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/mydb";
            Class.forName("java.sql.DriverManager");
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();

            int selectedRow = credentialsTable.getSelectedRow();

            if (selectedRow != -1) {
                // Get updated values from text fields
                String updatedUsername = usernameField.getText();
                String updatedPassword = passwordField.getText();

                // Update the database with the new values
                String updateQuery = String.format(
                        "UPDATE Login SET username = '%s', password = '%s' WHERE login_id = '%s'",
                        updatedUsername, updatedPassword,
                        tableModel.getValueAt(selectedRow, 1)
                );
                statement.executeUpdate(updateQuery);

                loadData();

                usernameField.setText("");
                passwordField.setText("");
            }else{
                jOptionPane1.showMessageDialog(this,"Please select a row");
            }

            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            jOptionPane1.showMessageDialog(this,e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Credentials().setVisible(true);
        });
    }
}