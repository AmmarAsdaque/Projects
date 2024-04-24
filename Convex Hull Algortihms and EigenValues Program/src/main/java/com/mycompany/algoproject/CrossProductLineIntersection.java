package com.mycompany.algoproject;

/*          DAA PROJECT - LINE INTERSECTION ALGORITHMS            */
/*               21K-3830   21K-3923    21K-3909                 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CrossProductLineIntersection extends JFrame {
    private java.awt.Point[] line1, line2;
    private int pointCount;
    private java.awt.Point intersectionPoint;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public CrossProductLineIntersection() {
        line1 = new java.awt.Point[2];
        line2 = new java.awt.Point[2];
        pointCount = 0;
        setTitle("Line Intersection Using Cross Product");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 240, 240));
        setLayout(new BorderLayout());
        resultTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"Label", "Coordinates"}, 0);
        resultTable.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        add(scrollPane, BorderLayout.SOUTH);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e);
                repaint();
            }
        });
    }

    private void handleClick(MouseEvent e) {
        if (pointCount == 0 || pointCount == 1) {
            line1[pointCount] = e.getPoint();
            pointCount++;
            if (pointCount == 2) {
                showMessageDialog("Now click for Line 2 points.", new Color(128, 0, 128));
            }
        } else if (pointCount == 2 || pointCount == 3) {
            line2[pointCount - 2] = e.getPoint();
            pointCount++;
            if (pointCount == 4) {
                checkIntersection();
                pointCount = 0;
            }
        }
    }

    private void checkIntersection() {
        int crossProduct1 = computeCrossProduct(line1[0], line1[1], line2[0]);
        int crossProduct2 = computeCrossProduct(line1[0], line1[1], line2[1]);
        if ((crossProduct1 > 0 && crossProduct2 < 0) || (crossProduct1 < 0 && crossProduct2 > 0)) {
            intersectionPoint = computeIntersectionPoint(line1[0], line1[1], line2[0], line2[1]);
            displayResults();
            showMessageDialog("Line segments intersect at: " + intersectionPoint, Color.GREEN);
        }
        else {
            clearIntersection();
            showMessageDialog("Line segments do not intersect.", Color.RED);
        }
    }   

    private void clearIntersection() {
        intersectionPoint = null;
        tableModel.setRowCount(0);
    }

    private void displayResults() {
        tableModel.setRowCount(0);
        tableModel.addRow(new Object[]{"Intersection Point", "(" + intersectionPoint.x + ", " + intersectionPoint.y + ")"});
        addLineToTable("Line 1", line1, new Color(128, 0, 128));
        addLineToTable("Line 2", line2, new Color(255, 182, 193));
    }

    private void addLineToTable(String label, java.awt.Point[] line, Color color) {
        tableModel.addRow(new Object[]{label + " Start", "(" + line[0].x + ", " + line[0].y + ")"});
        tableModel.addRow(new Object[]{label + " End", "(" + line[1].x + ", " + line[1].y + ")"});

        Graphics g = getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(line[0].x, line[0].y, line[1].x, line[1].y);
    }

    private void showMessageDialog(String message, Color color) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Message");
        dialog.getContentPane().setBackground(color);
        dialog.setModal(false);
        dialog.setVisible(true);
    }

    private java.awt.Point computeIntersectionPoint(java.awt.Point p1, java.awt.Point p2, java.awt.Point p3, java.awt.Point p4) {
        int x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y;
        int x3 = p3.x, y3 = p3.y, x4 = p4.x, y4 = p4.y;
        double det = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        double px = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / det;
        double py = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / det;
        return new java.awt.Point((int) px, (int) py);
    }

    private int computeCrossProduct(java.awt.Point p1, java.awt.Point p2, java.awt.Point p3) {
        return (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
    }

    @Override
    public void paint(Graphics g) {
          super.paint(g);
        for (int i = 0; i < line1.length; i++) {
            if (line1[i] != null && i + 1 < line1.length && line1[i + 1] != null) {
                java.awt.Point start = line1[i];
                java.awt.Point end = line1[i + 1];
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(128, 0, 128));
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(start.x, start.y, end.x, end.y);
                drawDirection(g, start, end, "");
            }
        }
        for (int i = 0; i < line2.length; i++) {
            if (line2[i] != null && i + 1 < line2.length && line2[i + 1] != null) {
                java.awt.Point start = line2[i];
                java.awt.Point end = line2[i + 1];
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(255, 182, 193));
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(start.x, start.y, end.x, end.y);
                drawDirection(g, start, end, "");
            }
        }
        if (intersectionPoint != null) {
            g.setColor(Color.BLACK);
            g.fillOval(intersectionPoint.x - 8,
                    intersectionPoint.y - 8,16,16);
        }
    }
    
    private void drawDirection(Graphics g, java.awt.Point p1, java.awt.Point p2, String label) {
        int midX = (p1.x + p2.x) / 2;
        int midY = (p1.y + p2.y) / 2;
        g.setColor(Color.BLACK);
        g.drawString(label, midX, midY);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CrossProductLineIntersection intersectionGUI = new CrossProductLineIntersection();
            JOptionPane.showMessageDialog(intersectionGUI,
                    "Click for two points for Line 1, then click for two points for Line 2.",
                    "Instructions", JOptionPane.INFORMATION_MESSAGE);
            intersectionGUI.setVisible(true);
        });
    }
}
