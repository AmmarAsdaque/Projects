package com.mycompany.algoproject;

/*          DAA PROJECT - LINE INTERSECTION ALGORITHMS            */
/*               21K-3830   21K-3923    21K-3909                 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SlopeLineIntersection extends JFrame {
    private java.awt.Point[] line1, line2;
    private int pointCount;
    private List<java.awt.Point> intersectionPoints;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public SlopeLineIntersection() {
        line1 = new java.awt.Point[2];
        line2 = new java.awt.Point[2];
        intersectionPoints = new ArrayList<>();
        setTitle("Line Intersection Using Slope");
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
        calculateIntersections();
        displayResults();
    }

    private void calculateIntersections() {
        intersectionPoints.clear();
        double[] intersection = findIntersection(
                line1[0].getX(), line1[0].getY(), line1[1].getX(), line1[1].getY(),
                line2[0].getX(), line2[0].getY(), line2[1].getX(), line2[1].getY()
        );
        if (intersection != null) {
            intersectionPoints.add(new java.awt.Point((int) intersection[0], (int) intersection[1]));
        }
    }

    private void displayResults() {
        tableModel.setRowCount(0);
        if (!intersectionPoints.isEmpty()) {
            for (int i = 0; i < intersectionPoints.size(); i++) {
                java.awt.Point intersectionPoint = intersectionPoints.get(i);
                tableModel.addRow(new Object[]{"Intersection Point " + (i + 1),
                        "(" + intersectionPoint.x + ", " + intersectionPoint.y + ")"});
            }
            addLineToTable("Line 1", line1, new Color(128, 0, 128));
            addLineToTable("Line 2", line2, new Color(255, 182, 193));
        } else {
            showMessageDialog("Lines do not intersect.", Color.RED);
        }
    }

    private void showMessageDialog(String message, Color color) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Message");
        dialog.getContentPane().setBackground(color);
        dialog.setModal(false);
        dialog.setVisible(true);
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

    private double[] findIntersection(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double m1 = (y2 - y1) / (x2 - x1);
        double m2 = (y4 - y3) / (x4 - x3);
        double b1 = y1 - (m1 * x1);
        double b2 = y3 - (m2 * x3);
        if (m1 == m2 && b1 == b2) {
            return new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
        }
        if (m1 == m2) {
            return null;
        }
        double x = (b2 - b1) / (m1 - m2);
        double y = m1 * x + b1;
        if (x >= min(line1[0].getX(), line1[1].getX()) && x <= max(line1[0].getX(), line1[1].getX())
                && x >= min(line2[0].getX(), line2[1].getX()) && x <= max(line2[0].getX(), line2[1].getX())) {
            return new double[]{x, y};
        }
        return null;
    }

    private double min(double a, double b) {
        return a < b ? a : b;
    }

    private double max(double a, double b) {
        return a > b ? a : b;
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
        for (java.awt.Point intersectionPoint : intersectionPoints) {
            g.setColor(Color.BLACK);
            g.fillOval(intersectionPoint.x - 8, intersectionPoint.y - 8, 16, 16);
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
            SlopeLineIntersection intersectionGUI = new SlopeLineIntersection();
            JOptionPane.showMessageDialog(intersectionGUI,
                    "Click for two points for Line 1, then click for two points for Line 2.",
                    "Instructions", JOptionPane.INFORMATION_MESSAGE);
            intersectionGUI.setVisible(true);
        });
    }
}



