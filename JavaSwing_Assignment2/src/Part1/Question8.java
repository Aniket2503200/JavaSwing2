package Part1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Question8 {
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	private static final int DEFAULT_CIRCLE_DIAMETER = 30;
	private static final int DEFAULT_MOVE_STEP = 5;
	private static final int DEFAULT_TIMER_DELAY = 20; // Milliseconds

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Advanced Animation with Timer");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(WIDTH, HEIGHT);
			frame.setLayout(new BorderLayout());

			AnimationPanel animationPanel = new AnimationPanel(DEFAULT_CIRCLE_DIAMETER, DEFAULT_MOVE_STEP, WIDTH,
					HEIGHT);
			frame.add(animationPanel, BorderLayout.CENTER);

			// Control panel for animation
			JPanel controlPanel = new JPanel();
			controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

			JButton startButton = new JButton("Start");
			JButton stopButton = new JButton("Stop");
			JButton resetButton = new JButton("Reset");
			JButton changeColorButton = new JButton("Change Color");
			JTextField speedField = new JTextField(String.valueOf(DEFAULT_TIMER_DELAY), 5);

			// Timer and control actions
			Timer timer = new Timer(DEFAULT_TIMER_DELAY, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					animationPanel.moveCircle();
				}
			});

			startButton.addActionListener(e -> timer.start());

			stopButton.addActionListener(e -> timer.stop());

			resetButton.addActionListener(e -> animationPanel.resetCircle());

			changeColorButton.addActionListener(e -> animationPanel.changeCircleColor());

			speedField.addActionListener(e -> {
				try {
					int delay = Integer.parseInt(speedField.getText());
					if (delay > 0) {
						timer.setDelay(delay);
					}
				} catch (NumberFormatException ex) {
					// Handle invalid number format
				}
			});

			controlPanel.add(startButton);
			controlPanel.add(stopButton);
			controlPanel.add(resetButton);
			controlPanel.add(changeColorButton);
			controlPanel.add(new JLabel("Speed (ms):"));
			controlPanel.add(speedField);

			frame.add(controlPanel, BorderLayout.SOUTH);

			frame.setVisible(true);
		});
	}
}

class AnimationPanel extends JPanel {
	private int circleX = 0;
	private int circleY = 100;
	private int circleDiameter;
	private int moveStep;
	private Color circleColor = Color.RED;

	public AnimationPanel(int circleDiameter, int moveStep, int width, int height) {
		this.circleDiameter = circleDiameter;
		this.moveStep = moveStep;
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(circleColor);
		g.fillOval(circleX, circleY, circleDiameter, circleDiameter);
	}

	public void moveCircle() {
		circleX += moveStep;
		if (circleX > getWidth()) {
			circleX = -circleDiameter; // Reset position to start from the left
		}
		repaint();
	}

	public void resetCircle() {
		circleX = 0;
		repaint();
	}

	public void changeCircleColor() {
		// Change to a random color
		circleColor = new Color((int) (Math.random() * 0x1000000));
		repaint();
	}
}
