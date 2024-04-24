/*          DAA PROJECT - CONVEX HULL ALGORITHMS                  */
/*               21K-3830   21K-3923    21K-3909                 */
package com.mycompany.algoproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

/*
 *
 * @author k213923
 */
public class QuickHull extends javax.swing.JFrame implements ActionListener, MouseListener{
	ArrayList<Point> points = new ArrayList<>();
        ArrayList<Point> convexHull = new ArrayList<>();
	int x,y;
	public QuickHull() {
		setResizable(false);
		setTitle("Quick Hull");
		initComponents();
		jPanel1.addMouseListener(this);
	}
	
        public void mouseClicked(MouseEvent e) {
                x=e.getX();
                y=e.getY();
                Graphics g=jPanel1.getGraphics();
                g.setColor(Color.YELLOW);
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
                            Graphics g = jPanel1.getGraphics();
                            quickHull();
                            g.setColor(Color.YELLOW);
                            for (int i = 0; i < convexHull.size(); i++) {
                                    g.drawLine(convexHull.get(i).x,convexHull.get(i).y,
                                            convexHull.get((i + 1)%convexHull.size()).x,	convexHull.get((i + 1)%convexHull.size()).y);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }

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

		 JLabel timeComplexityLabel = new JLabel("Time Complexity: O(n^2)");
                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup().addGap(53)
                                .addComponent(btnx, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE).addGap(45)
                                .addComponent(btnreset, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE).addGap(52))
                        .addGroup(layout.createSequentialGroup().addGap(20).addComponent(timeComplexityLabel).addContainerGap(20, Short.MAX_VALUE)));
                layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout
                                        .createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnreset).addComponent(btnx))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(timeComplexityLabel)
                                .addContainerGap()));
                jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
                getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>
        
        public void quickHull() {
            if (points.size() < 3) {
                return; 
            }

            int minIdx = -1, maxIdx = -1;
            int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;

            for (int i = 0; i < points.size(); i++) {
                int x = points.get(i).x;
                if (x < minX) {
                    minX = x;
                    minIdx = i;
                }
                if (x > maxX) {
                    maxX = x;
                    maxIdx = i;
                }
            }

            Point A = points.get(minIdx);
            Point B = points.get(maxIdx);

            convexHull.add(A);
            convexHull.add(B);
            points.remove(A);
            points.remove(B);

            ArrayList<Point> leftSet = new ArrayList<>();
            ArrayList<Point> rightSet = new ArrayList<>();

            for (Point p : points) {
                if (pointLocation(A, B, p) == -1)
                    leftSet.add(p);
                else if (pointLocation(A, B, p) == 1)
                    rightSet.add(p);
            }

            hullSet(A, B, rightSet, convexHull);
            hullSet(B, A, leftSet, convexHull);
        }

        private static int distance(Point A, Point B, Point C) {
            int ABx = B.x - A.x;
            int ABy = B.y - A.y;
            int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
            if (num < 0) {
                num = -1 * num;
            }
            return num;
        }

        private static void hullSet(Point A, Point B, ArrayList<Point> set, ArrayList<Point> hull) {
            int insertIdx = hull.indexOf(B);
            if (set.size() == 0) {
                return;
            }
            if (set.size() == 1) {
                Point p = set.get(0);
                set.remove(p);
                hull.add(insertIdx, p);
                return;
            }

            int dist = Integer.MIN_VALUE;
            int furthestPoint = -1;

            for (int i = 0; i < set.size(); i++) {
                Point p = set.get(i);
                int cDist = distance(A, B, p);
                if (cDist > dist) {
                    dist = cDist;
                    furthestPoint = i;
                }
            }

            Point P = set.get(furthestPoint);
            set.remove(furthestPoint);
            hull.add(insertIdx, P);

            ArrayList<Point> leftSetAP = new ArrayList<>();
            for (Point point : set) {
                if (pointLocation(A, P, point) == 1) {
                    leftSetAP.add(point);
                }
            }

            ArrayList<Point> rightSetPB = new ArrayList<>();
            for (Point point : set) {
                if (pointLocation(P, B, point) == 1) {
                    rightSetPB.add(point);
                }
            }

            hullSet(A, P, leftSetAP, hull);
            hullSet(P, B, rightSetPB, hull);
        }

        private static int pointLocation(Point A, Point B, Point P) {
            int val = (B.y - A.y) * (P.x - B.x) - (B.x - A.x) * (P.y - B.y);
            if (val == 0) {
                return 0;
            }
            return (val > 0) ? 1 : -1;
        }
        
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new QuickHull().setVisible(true);
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
