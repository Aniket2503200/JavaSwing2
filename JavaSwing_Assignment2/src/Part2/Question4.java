package Part2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Question4 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Create the main frame
			JFrame frame = new JFrame("Custom Component with Color Chooser");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 300);
			frame.setLayout(new BorderLayout());

			// Create the custom colored rectangle component
			ColoredRectanglePanel rectanglePanel = new ColoredRectanglePanel();
			frame.add(rectanglePanel, BorderLayout.CENTER);

			// Create a button to open color chooser dialog
			JButton colorButton = new JButton("Choose Color");
			colorButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Open color chooser dialog
					Color newColor = JColorChooser.showDialog(frame, "Select a Color",
							rectanglePanel.getRectangleColor());
					if (newColor != null) {
						// Set the new color to the rectangle
						rectanglePanel.setRectangleColor(newColor);
					}
				}
			});
			frame.add(colorButton, BorderLayout.SOUTH);

			// Display the frame
			frame.setVisible(true);
		});
	}
}

// Custom Swing Component
class ColoredRectanglePanel extends JPanel {
	private Color rectangleColor = Color.BLUE; // Default color

	public ColoredRectanglePanel() {
		setPreferredSize(new Dimension(300, 200));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Draw the colored rectangle
		g2d.setColor(rectangleColor);
		g2d.fillRect(50, 50, 200, 100);
	}

	public Color getRectangleColor() {
		return rectangleColor;
	}

	public void setRectangleColor(Color color) {
		this.rectangleColor = color;
		repaint(); // Request to repaint the panel with the new color
	}
}
