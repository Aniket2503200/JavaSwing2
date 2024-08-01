package Part2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Question15 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Custom Painting Example");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 600);
			frame.setLayout(new BorderLayout());

			// Create the drawing panel
			CustomPanel customPanel = new CustomPanel();
			frame.add(customPanel, BorderLayout.CENTER);

			// Create control panel
			JPanel controlPanel = new JPanel();
			controlPanel.setLayout(new GridLayout(2, 3));

			// Color selection
			JButton colorButton = new JButton("Change Color");
			controlPanel.add(colorButton);

			// Size selection
			JButton increaseSizeButton = new JButton("Increase Size");
			JButton decreaseSizeButton = new JButton("Decrease Size");
			controlPanel.add(increaseSizeButton);
			controlPanel.add(decreaseSizeButton);

			frame.add(controlPanel, BorderLayout.SOUTH);

			// Add action listeners
			colorButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					customPanel.changeColor();
				}
			});

			increaseSizeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					customPanel.increaseSize();
				}
			});

			decreaseSizeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					customPanel.decreaseSize();
				}
			});

			frame.setVisible(true);
		});
	}
}

class CustomPanel extends JPanel {
	private Color houseColor = Color.RED;
	private int houseSize = 100;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(houseColor);

		// Draw the house
		int x = getWidth() / 2 - houseSize / 2;
		int y = getHeight() / 2 - houseSize / 2;
		int roofHeight = houseSize / 2;

		// Draw the house body
		g2d.fillRect(x, y + roofHeight, houseSize, houseSize);

		// Draw the roof
		Polygon roof = new Polygon();
		roof.addPoint(x, y + roofHeight);
		roof.addPoint(x + houseSize / 2, y);
		roof.addPoint(x + houseSize, y + roofHeight);
		g2d.fillPolygon(roof);

		// Draw the door
		g2d.setColor(new Color(139, 69, 19)); // Using RGB values for brown color
		g2d.fillRect(x + houseSize / 3, y + houseSize + roofHeight - houseSize / 4, houseSize / 3, houseSize / 4);
	}

	public void changeColor() {
		// Change the house color randomly
		houseColor = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
		repaint();
	}

	public void increaseSize() {
		houseSize += 10;
		repaint();
	}

	public void decreaseSize() {
		if (houseSize > 20) {
			houseSize -= 10;
			repaint();
		}
	}
}
