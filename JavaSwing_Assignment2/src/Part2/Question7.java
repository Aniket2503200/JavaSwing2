package Part2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Question7 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Drawing Application");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 600);
			frame.setLayout(new BorderLayout());

			// Create the drawing panel
			DrawingPanel drawingPanel = new DrawingPanel();
			frame.add(drawingPanel, BorderLayout.CENTER);

			// Create control panel with buttons
			JPanel controlPanel = new JPanel();
			JButton lineButton = new JButton("Line");
			JButton rectangleButton = new JButton("Rectangle");
			JButton circleButton = new JButton("Circle");
			JButton colorButton = new JButton("Choose Color");
			JButton clearButton = new JButton("Clear");

			controlPanel.add(lineButton);
			controlPanel.add(rectangleButton);
			controlPanel.add(circleButton);
			controlPanel.add(colorButton);
			controlPanel.add(clearButton);

			frame.add(controlPanel, BorderLayout.NORTH);

			// Add action listeners
			lineButton.addActionListener(e -> drawingPanel.setShapeType(DrawingPanel.ShapeType.LINE));
			rectangleButton.addActionListener(e -> drawingPanel.setShapeType(DrawingPanel.ShapeType.RECTANGLE));
			circleButton.addActionListener(e -> drawingPanel.setShapeType(DrawingPanel.ShapeType.CIRCLE));

			colorButton.addActionListener(e -> {
				Color newColor = JColorChooser.showDialog(frame, "Choose a color", drawingPanel.getCurrentColor());
				if (newColor != null) {
					drawingPanel.setCurrentColor(newColor);
				}
			});

			clearButton.addActionListener(e -> drawingPanel.clear());

			frame.setVisible(true);
		});
	}
}

class DrawingPanel extends JPanel {
	enum ShapeType {
		LINE, RECTANGLE, CIRCLE
	}

	private ShapeType currentShape = ShapeType.LINE;
	private Color currentColor = Color.BLACK;
	private int startX, startY, endX, endY;

	public DrawingPanel() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(800, 500));

		// Mouse listeners for drawing
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				startX = e.getX();
				startY = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				endX = e.getX();
				endY = e.getY();
				repaint();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				endX = e.getX();
				endY = e.getY();
				repaint();
			}
		});
	}

	public void setShapeType(ShapeType shapeType) {
		this.currentShape = shapeType;
	}

	public void setCurrentColor(Color color) {
		this.currentColor = color;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void clear() {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(currentColor);

		// Draw the shape based on the current shape type
		switch (currentShape) {
		case LINE:
			g2d.drawLine(startX, startY, endX, endY);
			break;
		case RECTANGLE:
			int rectWidth = Math.abs(endX - startX);
			int rectHeight = Math.abs(endY - startY);
			g2d.drawRect(Math.min(startX, endX), Math.min(startY, endY), rectWidth, rectHeight);
			break;
		case CIRCLE:
			int diameter = Math.max(Math.abs(endX - startX), Math.abs(endY - startY));
			g2d.drawOval(Math.min(startX, endX), Math.min(startY, endY), diameter, diameter);
			break;
		}
	}
}
