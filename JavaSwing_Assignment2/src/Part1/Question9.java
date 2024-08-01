package Part1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Question9 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("High DPI Handling");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 600);
			frame.setLayout(new BorderLayout());

			// Create a panel with custom rendering
			HighDPIPanel panel = new HighDPIPanel();
			frame.add(panel, BorderLayout.CENTER);

			// Create buttons and sliders for additional functionality
			JPanel controlPanel = new JPanel();
			JButton changeBgButton = new JButton("Change Background Color");
			JButton changeShapeButton = new JButton("Change Shape");
			JTextField textField = new JTextField("High DPI Example", 20);
			JSlider sizeSlider = new JSlider(10, 200, 100);
			JLabel sizeLabel = new JLabel("Circle Size: 100");

			// Add action listener to change background color
			changeBgButton.addActionListener(e -> panel.changeBackgroundColor());

			// Add action listener to change shape
			changeShapeButton.addActionListener(e -> panel.toggleShape());

			// Add action listener to update text
			textField.addActionListener(e -> panel.setText(textField.getText()));

			// Add change listener to adjust circle size
			sizeSlider.addChangeListener(e -> {
				int size = sizeSlider.getValue();
				panel.setCircleDiameter(size);
				sizeLabel.setText("Circle Size: " + size);
			});

			controlPanel.add(changeBgButton);
			controlPanel.add(changeShapeButton);
			controlPanel.add(textField);
			controlPanel.add(sizeLabel);
			controlPanel.add(sizeSlider);

			frame.add(controlPanel, BorderLayout.SOUTH);

			// Display the frame
			frame.setVisible(true);
		});
	}
}

class HighDPIPanel extends JPanel {
	private int shapeX = 50;
	private int shapeY = 50;
	private int shapeDiameter = 100;
	private Color shapeColor = Color.RED;
	private Color backgroundColor = Color.WHITE;
	private String displayText = "High DPI Example";
	private boolean isOval = true;
	private double angle = 0;
	private Timer timer;

	public HighDPIPanel() {
		setPreferredSize(new Dimension(800, 600));
		setBackground(backgroundColor);

		// Set up timer for animation
		timer = new Timer(30, e -> {
			moveShape();
			repaint();
		});
		timer.start();

		// Add mouse listener for interactive color change
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shapeColor = new Color((int) (Math.random() * 0x1000000)); // Random color
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// Enable anti-aliasing for better quality
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Get DPI scaling factor
		double scaleX = (double) getWidth() / 800;
		double scaleY = (double) getHeight() / 600;

		// Apply scaling transformation
		g2d.scale(scaleX, scaleY);

		// Apply rotation transformation
		g2d.rotate(angle, shapeX + shapeDiameter / 2, shapeY + shapeDiameter / 2);

		// Draw dynamic shapes
		g2d.setColor(shapeColor);
		if (isOval) {
			g2d.fillOval(shapeX, shapeY, shapeDiameter, shapeDiameter);
		} else {
			g2d.fillRect(shapeX, shapeY, shapeDiameter, shapeDiameter);
		}

		// Draw text with dynamic font size
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		g2d.drawString(displayText, 200, 100);
	}

	private void moveShape() {
		shapeX += 5;
		angle += Math.toRadians(2); // Rotate shape
		if (shapeX > getWidth()) {
			shapeX = -shapeDiameter; // Reset position to start from the left
		}
	}

	public void changeBackgroundColor() {
		backgroundColor = new Color((int) (Math.random() * 0x1000000)); // Random background color
		setBackground(backgroundColor);
		repaint();
	}

	public void toggleShape() {
		isOval = !isOval;
		repaint();
	}

	public void setText(String text) {
		this.displayText = text;
		repaint();
	}

	public void setCircleDiameter(int diameter) {
		this.shapeDiameter = diameter;
		repaint();
	}
}
