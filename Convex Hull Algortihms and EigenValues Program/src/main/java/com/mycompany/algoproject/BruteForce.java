/*          DAA PROJECT - CONVEX HULL ALGORITHMS                  */
/*               21K-3830   21K-3923    21K-3909                 */
package com.mycompany.algoproject;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 *
 * @author k213923
 */
public class BruteForce extends javax.swing.JFrame implements ActionListener, MouseListener{
	ArrayList<Point> points = new ArrayList<>();
        ArrayList<Point> convexHull = new ArrayList<>();
	int x,y;
	public BruteForce() {
		setResizable(false);
		setTitle("Brute Force");
		initComponents();
		jPanel1.addMouseListener(this);
	}
	
        public void mouseClicked(MouseEvent e) {
                x=e.getX();
                y=e.getY();
                Graphics g=jPanel1.getGraphics();
                g.setColor(Color.WHITE);
                drawCenteredCircle(g, x, y, 10);
                points.add(new Point(e.getX(), e.getY()));
        }

        public void drawCenteredCircle(Graphics g, int x, int y, int r) {
                  x = x-(r/2);
                  y = y-(r/2);
                  g.fillOval(x,y,r,r);
                }
        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
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
                    ArrayList<Point> n = new ArrayList<>();
                    Graphics g = jPanel1.getGraphics();
                    convexHull(g);
                }
            });

            JButton btnreset = new JButton("Reset");
            btnreset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    points.clear();
                    convexHull.clear();
                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
                    jPanel1.removeAll();
                    jPanel1.revalidate();
                    repaint();
                    jPanel1.repaint();
                }
            });

            // Add the label with the title "Time Complexity: O(n^3)"
            JLabel timeComplexityLabel = new JLabel("Time Complexity: O(n^3)");

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

        public void convexHull(Graphics g){
            int left=0;
            int right=0;
            int along=0;
            boolean flag=true;
            g.setColor(Color.WHITE);
            ArrayList<PointSet> pointSet= new ArrayList<>();
            for(int i = 0; i < points.size();i++) {
                for(int j = 0; j < points.size(); j++) {
                    if(j != i) {
                            for(int k = 0; k < points.size();k++) {
                                if(k != j && k!= i) {

                                        int AB = (points.get(j).y-points.get(i).y)*(points.get(k).x)+
                                                (points.get(i).x-points.get(j).x)*(points.get(k).y);
                                        int C = points.get(i).x*points.get(j).y-points.get(i).y*points.get(j).x;

                                        if((AB-C)<0) {
                                                left++;
                                        }
                                        else if((AB-C)>0) {
                                                right++;
                                        }
                                        else {
                                                 along++;
                                        }
                                }

                            }	
                        //System.out.println("p1:"+points.get(i).toString()+"p2:"+points.get(j).toString());
                        if(left==points.size()-2||right==points.size()-2) {
                                 for(PointSet pair :pointSet) {
                                         if(pair.getFirst().equals(points.get(i)) && pair.getSecond().equals(points.get(j))
                                                 || pair.getFirst().equals(points.get(j)) && pair.getSecond().equals(points.get(i))){
                                            flag=false;
                                         }

                                 }
                                 if(flag==true) {
                                        convexHull.add(points.get(i));
                                        convexHull.add(points.get(j));
                                        System.out.print(points.get(i).x +","+points.get(i).y);
                                        System.out.println("\t"+points.get(j).x +","+points.get(j).y);
                                        g.drawLine(points.get(i).x, points.get(i).y, points.get(j).x, points.get(j).y);
                                        pointSet.add(new PointSet(points.get(i),points.get(j)));
                                 }
                             flag=true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                        }
                        left=0;
                        right=0;
                        along=0;
                    }
                }
            }
        }
        
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BruteForce().setVisible(true);
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

}