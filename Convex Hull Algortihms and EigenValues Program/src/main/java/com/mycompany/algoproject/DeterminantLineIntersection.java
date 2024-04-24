package com.mycompany.algoproject;

/*          DAA PROJECT - LINE INTERSECTION ALGORITHMS            */
/*               21K-3830   21K-3923    21K-3909                 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeterminantLineIntersection extends JFrame {
    private JButton checkIntersectionButton;
    private JLabel resultLabel;
    private java.awt.Point intersectionPoint;
    private DefaultTableModel tableModel;
    private JFrame tableFrame;
    
    public DeterminantLineIntersection() {
        setTitle("Line Intersection Using Determinant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 240, 240));
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(144, 238, 144));
        checkIntersectionButton = new JButton("Check Intersection");
        checkIntersectionButton.setBackground(new Color(255, 165, 0));
        checkIntersectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkIntersection(buttonPanel.getGraphics());
                repaint();
            }
        });
        resultLabel = new JLabel("");
        resultLabel.setForeground(Color.BLUE);
        JButton showTableButton = new JButton("Show Result Table");
        showTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResultTable();
            }
        });
        buttonPanel.add(checkIntersectionButton);
        buttonPanel.add(showTableButton);
        buttonPanel.add(resultLabel);
        add(buttonPanel, BorderLayout.SOUTH);
        intersectionPoint = new java.awt.Point(-1, -1);
    }

    private void checkIntersection(Graphics g) {
        try {
            int x1 = Integer.parseInt(JOptionPane.showInputDialog("Enter x1:"));
            int y1 = Integer.parseInt(JOptionPane.showInputDialog("Enter y1:"));
            int x2 = Integer.parseInt(JOptionPane.showInputDialog("Enter x2:"));
            int y2 = Integer.parseInt(JOptionPane.showInputDialog("Enter y2:"));
            int x3 = Integer.parseInt(JOptionPane.showInputDialog("Enter x3:"));
            int y3 = Integer.parseInt(JOptionPane.showInputDialog("Enter y3:"));
            int x4 = Integer.parseInt(JOptionPane.showInputDialog("Enter x4:"));
            int y4 = Integer.parseInt(JOptionPane.showInputDialog("Enter y4:"));
            g.drawLine(x1, y1, x2, y2);
            g.drawLine(x3, y3, x4, y4);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            boolean intersection = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
            resultLabel.setText("Lines " + (intersection ? "intersect" : "do not intersect"));
            if (intersection) {
                intersectionPoint = computeIntersectionPoint(new java.awt.Point(x1, y1), new java.awt.Point(x2, y2),
                        new java.awt.Point(x3, y3), new java.awt.Point(x4, y4));
            }
            String message = intersection ? "Lines intersect at: " + intersectionPoint :
                    "Lines do not intersect.";
            showMessageDialog(message, intersection ? Color.GREEN : Color.RED);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter integers for coordinates.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMessageDialog(String message, Color color) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Intersection Result");
        dialog.getContentPane().setBackground(color);
        dialog.setModal(false);
        dialog.setVisible(true);
    }

    private boolean doLinesIntersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int det1 = determinant(x1, y1, x2, y2, x3, y3);
        int det2 = determinant(x1, y1, x2, y2, x4, y4);
        int det3 = determinant(x3, y3, x4, y4, x1, y1);
        int det4 = determinant(x3, y3, x4, y4, x2, y2);
        repaint();
        return (det1 * det2 <= 0) && (det3 * det4 <= 0);
    }

    private int determinant(int x1, int y1, int x2, int y2, int x3, int y3) {
        return x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2);
    }

    private java.awt.Point computeIntersectionPoint(java.awt.Point p1, java.awt.Point p2, java.awt.Point p3, java.awt.Point p4) {
        int x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y;
        int x3 = p3.x, y3 = p3.y, x4 = p4.x, y4 = p4.y;
        double det = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        double px = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / det;
        double py = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / det;
        return new java.awt.Point((int) px, (int) py);
    }

    private void showResultTable() {
        if (tableFrame == null) {
            tableFrame = new JFrame("Result Table");
            tableFrame.setSize(300, 400);
            tableFrame.setLocationRelativeTo(this);
            tableModel = new DefaultTableModel(new Object[]{"Label", "Coordinates"}, 0);
            JTable resultTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(resultTable);
            tableFrame.add(scrollPane);
        }
        tableModel.setRowCount(0);
        tableModel.addRow(new Object[]{"Intersection Point", "(" + intersectionPoint.x + ", " + intersectionPoint.y + ")"});
        tableFrame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(128, 0, 128));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(0, intersectionPoint.y, getWidth(), intersectionPoint.y);
        g2d.setColor(new Color(255, 182, 193));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(intersectionPoint.x, 0, intersectionPoint.x, getHeight());
        g.setColor(Color.RED);
        g.fillOval(intersectionPoint.x - 4, intersectionPoint.y - 4, 8, 8);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DeterminantLineIntersection().setVisible(true);
        });
    }
}
