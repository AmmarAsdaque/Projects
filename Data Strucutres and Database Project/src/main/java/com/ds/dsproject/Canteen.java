/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ds.dsproject;

import java.awt.Color;
import java.awt.Image;
import static java.lang.Character.isSpaceChar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP 840 G2
 */
public class Canteen extends javax.swing.JFrame {
    int id;
    Connection con1,con2;
    Savepoint save1,save2;
    /**
     * Creates new form Canteen
     */
    public Canteen() {
        
        initComponents();
        setVisible(true);
        id = 1;
        try {    
            Class.forName("java.sql.DriverManager");
            String url="jdbc:mysql://127.0.0.1:3306/mydb";
            con1 = DriverManager.getConnection(url);
            Statement st = con1.createStatement();
            String sql = "Select status from Canteen where canteen_id = "+ id;
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
            jCheckBox1.setSelected(!rs.getBoolean(1));
            con2 = DriverManager.getConnection(url);
            con1.setAutoCommit(false);
            con2.setAutoCommit(false);
            save1 = con1.setSavepoint();
            save2 = con2.setSavepoint();
            
            fillTable1();
            fillTable2();
        } catch (Exception ex) {
            jOptionPane1.showMessageDialog(this, ex.getMessage());
        }
    }
    public Canteen(int x) {
        initComponents();
        setVisible(true);
        id = x;
        try {    
            Class.forName("java.sql.DriverManager");
            String url="jdbc:mysql://127.0.0.1:3306/mydb";
            con1 = DriverManager.getConnection(url);
            Statement st = con1.createStatement();
            String sql = "Select status from Canteen where canteen_id = "+ id;
            ResultSet rs = st.executeQuery(sql);
            if(!rs.equals(null))
            jCheckBox1.setSelected(rs.getBoolean(1));
            con2 = DriverManager.getConnection(url);
            con1.setAutoCommit(false);
            con2.setAutoCommit(false);
            save1 = con1.setSavepoint();
            save2 = con2.setSavepoint();
            
            fillTable1();
            fillTable2();
        } catch (Exception ex) {
            jOptionPane1.showMessageDialog(this, ex.getMessage());
        }
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 153, 0));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setText("CANTEEN MANAGER");

        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });
        jLabel11.setIcon(new ImageIcon("imgs\\cancel.png"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Cost", "Price", "Status", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        selectionModel1 = jTable1.getSelectionModel();
        selectionModel1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = jTable1.getSelectedRow();
                    int selectedColumn = jTable1.getSelectedColumn();
                    if(selectedRow < 0){
                        selectedRow = 0;
                    }
                    checkData1(selectedRow,selectedColumn);
                    System.out.println("Selected Row: " + selectedRow + ", Selected Column: " + selectedColumn);
                }
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(30);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
            jTable1.getColumnModel().getColumn(1).setMinWidth(150);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(2).setMinWidth(60);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(3).setMinWidth(40);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(40);
            jTable1.getColumnModel().getColumn(4).setMinWidth(60);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });
        jLabel3.setIcon(new ImageIcon("imgs\\loading-arrows.png"));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "User ID", "Bill", "Accepted", "Delivered", "Item Name", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        selectionModel2 = jTable2.getSelectionModel();
        selectionModel2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = jTable2.getSelectedRow();
                    int selectedColumn = jTable2.getSelectedColumn();
                    if(selectedRow < 0){
                        selectedRow = 0;
                    }
                    checkData2(selectedRow,selectedColumn);
                    System.out.println("Selected Row: " + selectedRow + ", Selected Column: " + selectedColumn);
                }
            }
        });
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMinWidth(30);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable2.getColumnModel().getColumn(0).setMaxWidth(30);
            jTable2.getColumnModel().getColumn(1).setMinWidth(60);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTable2.getColumnModel().getColumn(1).setMaxWidth(60);
            jTable2.getColumnModel().getColumn(2).setMinWidth(40);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(40);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(40);
            jTable2.getColumnModel().getColumn(3).setMinWidth(60);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTable2.getColumnModel().getColumn(3).setMaxWidth(60);
            jTable2.getColumnModel().getColumn(4).setMinWidth(60);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(60);
            jTable2.getColumnModel().getColumn(4).setMaxWidth(60);
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getColumnModel().getColumn(6).setMinWidth(50);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(6).setMaxWidth(50);
        }

        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });
        jLabel4.setIcon(new ImageIcon("imgs\\verified.png"));

        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });
        jLabel5.setIcon(new ImageIcon("imgs\\loading-arrows.png"));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Menu:");
        jLabel6.setOpaque(true);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Pending Orders:");
        jLabel7.setOpaque(true);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });
        jLabel8.setIcon(new ImageIcon("imgs\\verified.png"));

        jCheckBox1.setText("Canteen Open");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });
        jLabel5.setIcon(new ImageIcon("imgs\\undo.png"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(105, 105, 105)
                .addComponent(jCheckBox1)
                .addGap(108, 108, 108)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jCheckBox1))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(27, 27, 27))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        JLabel lbl = (JLabel) evt.getComponent();
        lbl.setOpaque(true);
        lbl.setBackground((Color.RED));
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        JLabel lbl = (JLabel) evt.getComponent();
        lbl.setOpaque(false);
        lbl.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
        dispose();
    }//GEN-LAST:event_jLabel11MousePressed

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        JLabel lbl = (JLabel) evt.getComponent();
        lbl.setOpaque(true);
        lbl.setBackground((Color.WHITE));
        jTextField1.setText("Refresh Table");
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        JLabel lbl = (JLabel) evt.getComponent();
        lbl.setOpaque(true);
        lbl.setBackground((Color.WHITE));
        jTextField1.setText("Confirm Changes");
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        JLabel lbl = (JLabel) evt.getComponent();
        lbl.setOpaque(true);
        lbl.setBackground((Color.WHITE));
        jTextField2.setText("Refresh Table");
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        JLabel lbl = (JLabel) evt.getComponent();
        lbl.setOpaque(false);
        lbl.setBackground(new Color(153, 255, 153));
        jTextField1.setText("");
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        JLabel lbl = (JLabel) evt.getComponent();
        lbl.setOpaque(false);
        lbl.setBackground(new Color(153, 255, 153));
        jTextField1.setText("");
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        JLabel lbl = (JLabel) evt.getComponent();
        lbl.setOpaque(false);
        lbl.setBackground(new Color(153, 255, 153));
        jTextField2.setText("");
    }//GEN-LAST:event_jLabel3MouseExited

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        try {
            con1.rollback(save1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        try {
            con2.rollback(save2);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        try {
            con2.commit();
            save1 = con1.setSavepoint();
            save2 = con2.setSavepoint();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        JLabel lbl = (JLabel) evt.getComponent();
        lbl.setOpaque(true);
        lbl.setBackground((Color.WHITE));
        jTextField1.setText("Confirm Changes");
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        JLabel lbl = (JLabel) evt.getComponent();
        lbl.setOpaque(false);
        lbl.setBackground(new Color(153, 255, 153));
        jTextField2.setText("");
    }//GEN-LAST:event_jLabel8MouseExited

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        try {
            String sql = "Update Canteen set status = "+!jCheckBox1.isSelected()+" where canteen_id = "+id;
            Statement st = con1.createStatement();
            st.execute(sql);
            con1.commit();
            save1 = con1.setSavepoint();
            save2 = con2.setSavepoint();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jLabel8MousePressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        new Login();
    }//GEN-LAST:event_jLabel9MousePressed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Canteen();
			}
		});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.ListSelectionModel selectionModel1;
    private javax.swing.JTable jTable2;
    private javax.swing.ListSelectionModel selectionModel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    public void checkData1(int row,int column){
        if(jTable1.getValueAt(row, column) == null){
            jOptionPane1.showMessageDialog(this,"Field can't be null");
            return;
        }
        boolean flag = false;
        boolean check = false;
        var itemid = jTable1.getValueAt(row,0);
        String x = (String) jTable1.getValueAt(row , 1);
        for(int i = 0; i < x.length();i++)
            if(!(Character.isLetter(x.charAt(i))||Character.isSpaceChar(x.charAt(i)))){
                jOptionPane1.showMessageDialog(this,"Name must be only letters");
                flag = true;
                break;
            }
        String y = (String) jTable1.getValueAt(row , 5);
        for(int i = 0; i < y.length();i++)
            if(!(Character.isLetter(y.charAt(i))||Character.isSpaceChar(y.charAt(i)))){
                jOptionPane1.showMessageDialog(this,"Description must be only letters");
                flag = true;
                break;
            }
        if(!flag){
            
                if(x.length()<50)
                    check = true;
                else{
                    jOptionPane1.showMessageDialog(this,"Name exceeds length");
                    return;
                }
            
                if(x.length()<255)
                    check = true;
                else{
                    jOptionPane1.showMessageDialog(this,"Description exceeds length");
                    return;
                }
        }else
            return;
        if((float)jTable1.getValueAt(row, 2) < (float)jTable1.getValueAt(row, 3))
            check = true;
        else
            return;
        if(jTable1.getValueAt(row, 4)!=null){
            check = true;
        }else
            return;
        if(check){
        try{
            
            String sql;
            if(itemid == null){
                sql = "INSERT into MENU( `item_name`, `cost`,`price` ,`canteen_id`, `availability`, `description`) VALUES (?,?,?,?,?,?)";
                PreparedStatement st = con1.prepareStatement(sql);
                st.setString(1, (String) jTable1.getValueAt(row, 1));
                st.setFloat(2, (float) jTable1.getValueAt(row, 2));
                st.setFloat(3, (float) jTable1.getValueAt(row, 3));
                st.setInt(4, id);
                st.setBoolean(5, (boolean) jTable1.getValueAt(row, 4));
                st.setString(6, (String) jTable1.getValueAt(row, 5));
                st.execute();
            }else{
                int item = (int) itemid;
                sql = "UPDATE `menu` SET `item_name`=?,`cost`=?,`availability`=?,`price`=?,`description`=? WHERE item_id = "+item;
                PreparedStatement st = con1.prepareStatement(sql);
                st.setString(1, (String) jTable1.getValueAt(row, 1));
                st.setFloat(2, (float) jTable1.getValueAt(row, 2));
                st.setBoolean(3, (boolean) jTable1.getValueAt(row, 4));
                st.setFloat(4, (float) jTable1.getValueAt(row, 3));
                st.setString(5, (String) jTable1.getValueAt(row, 5));
                st.execute();
            }
        }catch(Exception e){
            jOptionPane1.showMessageDialog(this,e);
        }
        fillTable1();}
    }
    public void checkData2(int row,int column){
        var x = jTable2.getValueAt(row, 0);
        if(x == null){
            jOptionPane1.showMessageDialog(this,"You can't change this value");
            jTable2.setValueAt(false, row, 3);
            jTable2.setValueAt(false, row, 4);
            return;
        }else if(column == 3 && ((boolean)jTable2.getValueAt(row, column))==false){
            jOptionPane1.showMessageDialog(this,"You can't change this value");
            jTable2.setValueAt(true, row, column);
            return;
        }else if(column==4 && (((boolean)jTable2.getValueAt(row, column-1))== false)){
            jOptionPane1.showMessageDialog(this,"You can't change this value");
            jTable2.setValueAt(false, row, column);
            return;
        }else if(column==4 && ((boolean)jTable2.getValueAt(row, column))==false){
            jOptionPane1.showMessageDialog(this,"You can't change this value");
            jTable2.setValueAt(true, row, column);
            return;
        }
        try {
            int order = (int) x;
            String sql = "UPDATE `orders` SET `status`=? WHERE order_id = "+order;
            PreparedStatement st = con1.prepareStatement(sql);
            if(((boolean)jTable2.getValueAt(row, 3))== true && ((boolean)jTable2.getValueAt(row, 4)== true))
                    st.setString(1, "Delivered");
            else if(((boolean)jTable2.getValueAt(row, 3))== true && ((boolean)jTable2.getValueAt(row, 4)== false))
                    st.setString(1, "Accepted");
            else{
                st.setString(1, "Placed");
            }
            st.execute();
            fillTable2();
        } catch (SQLException ex) {
            jOptionPane1.showMessageDialog(this,ex);
        }
    }
    private void fillTable1() {
        try{
            
            java.sql.Statement st=con1.createStatement();
            String sql="SELECT item_id,item_name,cost,price,availability,description FROM Menu WHERE canteen_id = "+id;
            java.sql.ResultSet rs1= st.executeQuery(sql);
            int i = 0;
            if(rs1.equals(null)){
                jOptionPane1.showMessageDialog(this, "No menu items avaliable! Add some");
            }else {
                while(rs1.next()){
                    jTable1.setValueAt(rs1.getInt(1), i, 0);
                    jTable1.setValueAt(rs1.getString(2), i, 1);
                    jTable1.setValueAt(rs1.getFloat(3), i, 2);
                    jTable1.setValueAt(rs1.getFloat(4), i, 3);
                    jTable1.setValueAt(rs1.getBoolean(5), i, 4);
                    jTable1.setValueAt(rs1.getString(6), i, 5);
                    i++;
                }
            }
            rs1.close();
            st.close();
        }catch(Exception e){
              jOptionPane1.showMessageDialog(this, e.getMessage());
        }
    }

    private void fillTable2() {
        try{
            
            java.sql.Statement st=con2.createStatement();
            String sql="SELECT O.order_id,O.user_id,O.bill,O.status,M.item_name,O.quantity FROM Orders O LEFT JOIN Menu M ON O.item_id = M.item_id WHERE O.canteen_id = "+id;
            java.sql.ResultSet rs1= st.executeQuery(sql);
            int i = 0;
            if(rs1.equals(null)){
                jOptionPane1.showMessageDialog(this, "No pending orders");
            }else {
                while(rs1.next()){
                    String x = rs1.getString(4);
                    boolean y,z;
                    if(x.equals("Placed")){
                        y = false;
                        z = false;
                    }else if(x.equals("Accepted")){
                        y = true;
                        z = false;
                    }else{
                        y = true;
                        z = true;
                    }
                    jTable2.setValueAt(rs1.getInt(1), i, 0);
                    jTable2.setValueAt(rs1.getString(2), i, 1);
                    jTable2.setValueAt(rs1.getFloat(3), i, 2);
                    jTable2.setValueAt(y, i, 3);
                    jTable2.setValueAt(z, i, 4);
                    jTable2.setValueAt(rs1.getString(5), i, 5);
                    jTable2.setValueAt(rs1.getInt(6), i, 6);
                    i++;
                }
            }
            rs1.close();
            st.close();
        }catch(Exception e){
            System.out.println(e);
              
        }
    }
}
