package Part1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Question3 extends JComponent {
	private Point currentPoint;

	public Question3() {
		currentPoint = new Point(50, 50);

		// Mouse listener to handle pressing and releasing
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Check if the point is within the draggable circle
				if (isWithinCircle(e.getPoint())) {
					currentPoint = e.getPoint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// Optional: Reset the current point when released
				// currentPoint = null;
			}
		});

		// Mouse motion listener to handle dragging
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (currentPoint != null) {
					currentPoint = e.getPoint();
					repaint();
				}
			}
		});
	}

	private boolean isWithinCircle(Point p) {
		int radius = 25;
		int centerX = currentPoint.x;
		int centerY = currentPoint.y;
		return Math.sqrt(Math.pow(p.x - centerX, 2) + Math.pow(p.y - centerY, 2)) <= radius;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (currentPoint != null) {
			g.setColor(Color.BLUE);
			g.fillOval(currentPoint.x - 25, currentPoint.y - 25, 50, 50);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Interactive Drag-and-Drop");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.add(new Question3());
		frame.setVisible(true);
	}
}
