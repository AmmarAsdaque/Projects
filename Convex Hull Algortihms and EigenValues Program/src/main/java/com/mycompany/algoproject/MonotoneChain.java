/*          DAA PROJECT - CONVEX HULL ALGORITHMS                  */
/*               21K-3830   21K-3923    21K-3909                 */
package com.mycompany.algoproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/*
 *
 * @author k213923
 */
public class MonotoneChain extends javax.swing.JFrame implements ActionListener, MouseListener{
	ArrayList<Point> points = new ArrayList<>();
        ArrayList<Point> convexHull = new ArrayList<>();
	int x,y;
	public MonotoneChain() {
		setResizable(false);
		setTitle("Andrew's Monotone Chain Algorithm");
		initComponents();
		jPanel1.addMouseListener(this);
	}
	
        @Override
        public void mouseClicked(MouseEvent e) {
                x=e.getX();
                y=e.getY();
                Graphics g=jPanel1.getGraphics();
                g.setColor(Color.GREEN);
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
				g.setColor(Color.GREEN);
				for (int i = 0; i < convexHull.size(); i++) {
					g.drawLine(convexHull.get(i).x,convexHull.get(i).y, convexHull.get((i + 1)%convexHull.size()).x,
							convexHull.get((i + 1)%convexHull.size()).y);
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
        
        private static int orientation(Point p, Point q, Point r) {
            int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
            if (val == 0) return 0;
            return (val > 0) ? 1 : 2;
        }
        public void convexHull() {
            Point[] points = new Point[this.points.size()];
            for(int i = 0; i < this.points.size(); i++){
                points[i] = new Point(this.points.get(i).x,this.points.get(i).y);
            }
            int n = points.length;
            if (n < 3) {
                return;
            }
            Arrays.sort(points, Comparator.comparingInt((Point p) -> p.x).thenComparingInt(p -> p.y));
            Stack<Point> lowerHull = new Stack<>();
            for (int i = 0; i < n; i++) {
                while (lowerHull.size() >= 2 && orientation(lowerHull.elementAt(lowerHull.size() - 2), lowerHull.peek(), points[i]) != 2) {
                    lowerHull.pop();
                }
                lowerHull.push(points[i]);
            }
            Stack<Point> upperHull = new Stack<>();
            for (int i = n - 1; i >= 0; i--) {
                while (upperHull.size() >= 2 && orientation(upperHull.elementAt(upperHull.size() - 2), upperHull.peek(), points[i]) != 2) {
                    upperHull.pop();
                }
                upperHull.push(points[i]);
            }
            convexHull.addAll(lowerHull.subList(0, lowerHull.size() - 1));
            convexHull.addAll(upperHull.subList(0, upperHull.size() - 1));

        }
    
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MonotoneChain().setVisible(true);
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
class AndrewsAlgorithm {
    
}
