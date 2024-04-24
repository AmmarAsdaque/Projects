/*          DAA PROJECT - CONVEX HULL ALGORITHMS                  */
/*               21K-3830   21K-3923    21K-3909                 */
package com.mycompany.algoproject;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 *
 * @author k213923
 */
public class JarvisMarch extends javax.swing.JFrame implements ActionListener, MouseListener{
	ArrayList<Point> points = new ArrayList<>();
        ArrayList<Point> convexHull = new ArrayList<>();
        private int currentStep;
	int x,y;
	public JarvisMarch() {
		setResizable(false);
		setTitle("Jarvis March");
                initComponents();
                jPanel1.addMouseListener(this);
	}
	
        public void mouseClicked(MouseEvent e) {
                x=e.getX();
                y=e.getY();
                Graphics g=jPanel1.getGraphics();
                g.setColor(Color.RED);
                drawCenteredCircle(g, x, y, 10);
                points.add(new Point(e.getX(), e.getY()));
        }

        public void drawCenteredCircle(Graphics g, int x, int y, int r) {
                  x = x-(r/2);
                  y = y-(r/2);
                  g.fillOval(x,y,r,r);
        }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">

    private void initComponents() {

        jPanel1 = new JPanel(null);
        jPanel1.setPreferredSize(new Dimension(500, 100));
        jPanel1.setBackground(Color.DARK_GRAY);
        jPanel1.setForeground(new Color(255, 0, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JButton btnx = new JButton("Draw Hull");
        btnx.setForeground(new Color(0, 0, 0));

        btnx.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                        Graphics g = jPanel1.getGraphics();
                        g.setColor(Color.RED);
                        convexHull();
                }

        });

        JButton btnreset = new JButton("Reset");
        btnreset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                        points.clear();
                        convexHull.clear();
                        currentStep = 0;
                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
                        jPanel1.removeAll();
                        jPanel1.revalidate();
                        repaint();
                        jPanel1.repaint();
                }
        });

         JLabel timeComplexityLabel = new JLabel("Time Complexity: O(n*h)");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup().addGap(53)
                            .addComponent(btnx, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE).addGap(45)
                            .addComponent(btnreset, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE).addGap(52))
                    .addGroup(layout.createSequentialGroup().addGap(20).addComponent(timeComplexityLabel).addContainerGap(20, Short.MAX_VALUE)));
            layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                            .addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout
                                    .createParallelGroup(Alignment.BASELINE).addComponent(btnreset).addComponent(btnx))
                            .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(timeComplexityLabel)
                            .addContainerGap()));
            jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>

    @Override
    public void paint(Graphics g) {
    super.paint(g);
    // Draw points
    g.setColor(Color.RED);
    for (Point point : points) {

        drawCenteredCircle(g,point.x,point.y,10);
    }
    // Draw convex hull up to the current step
    g.setColor(Color.RED);
    for (int i = 1; i < currentStep; i++) {
        Point p1 = convexHull.get(i - 1);
        Point p2 = convexHull.get(i);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    }
    private boolean CCW(Point p, Point q, Point r)
    {
    int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

     if (val >= 0)
         return false;
     return true;
    }
    public void convexHull()
    {
    int n = points.size();      
    Graphics g = jPanel1.getGraphics();
    int leftMost = 0;
    for (int i = 1; i < n; i++)
        if (points.get(i).x < points.get(leftMost).x)
            leftMost = i;
    int p = leftMost, q;
    convexHull.add(points.get(p));
    currentStep++;
    do
    {
        q = (p + 1) % n;
        for (int i = 0; i < n; i++)
          if (CCW(points.get(p), points.get(i), points.get(q)))
             q = i;
        convexHull.add(points.get(q));
        currentStep++;
        p = q; 
    } while (p != leftMost);
    paint(g);
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                        new JarvisMarch().setVisible(true);
                }
        });
    }


    // Variables declaration - do not modify
    private JPanel jPanel1;
    // End of variables declaration

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
