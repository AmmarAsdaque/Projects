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
public class GrahamScan extends javax.swing.JFrame implements ActionListener, MouseListener{

	ArrayList<Point> points = new ArrayList<>();
        ArrayList<Point> convexHull = new ArrayList<>();
	int x,y;
	public GrahamScan() {
		setResizable(false);
		setTitle("Graham Scan");
		initComponents();
		jPanel1.addMouseListener(this);
	}
	
        public void mouseClicked(MouseEvent e) {
                x=e.getX();
                y=e.getY();
                Graphics g=jPanel1.getGraphics();
                g.setColor(Color.CYAN);
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
				convexHull();
				g.setColor(Color.CYAN);
				for (int i = 0; i < convexHull.size(); i++) {
					g.drawLine(convexHull.get(i).x,convexHull.get(i).y,
                                                convexHull.get((i + 1)%convexHull.size()).x,convexHull.get((i + 1)%convexHull.size()).y);
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

                JLabel timeComplexityLabel = new JLabel("Time Complexity: O(nlogn)");

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
        
        private static Point findLowestPoint(ArrayList<Point> points) {
            Point lowest = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                Point current = points.get(i);
                if (current.y < lowest.y || (current.y == lowest.y && current.x < lowest.x)) {
                    lowest = current;
                }
            }
            return lowest;
        }

        private static class PolarAngleComparator implements Comparator<Point> {
            private Point lowest;

            public PolarAngleComparator(Point lowest) {
                this.lowest = lowest;
            }

            @Override
            public int compare(Point p1, Point p2) {
                double angle1 = Math.atan2(p1.y - lowest.y, p1.x - lowest.x);
                double angle2 = Math.atan2(p2.y - lowest.y, p2.x - lowest.x);

                if (angle1 < angle2) {
                    return -1;
                } else if (angle1 > angle2) {
                    return 1;
                } else {
                    double dist1 = Math.pow(p1.x - lowest.x, 2) + Math.pow(p1.y - lowest.y, 2);
                    double dist2 = Math.pow(p2.x - lowest.x, 2) + Math.pow(p2.y - lowest.y, 2);
                    return Double.compare(dist1, dist2);
                }
            }
        }

        // Function to perform the Graham Scan algorithm.
        public void convexHull() {
            Point lowest = findLowestPoint(points);
            points.sort(new PolarAngleComparator(lowest));
            convexHull.add(points.get(0));
            convexHull.add(points.get(1));
            convexHull.add(points.get(2));
            for (int i = 3; i < points.size(); i++) {
                while (orientation(convexHull.get(convexHull.size() - 2), convexHull.get(convexHull.size() - 1), points.get(i)) != 2) {
                    convexHull.remove(convexHull.size() - 1);
                }
                convexHull.add(points.get(i));
            }
        }
        private static int orientation(Point p, Point q, Point r) {
            int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
            if (val == 0) {
                return 0; 
            }
            return (val > 0) ? 1 : 2; 
        }
        
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GrahamScan().setVisible(true);
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
