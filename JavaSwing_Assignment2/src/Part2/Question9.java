package Part2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Question9 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Timer-Based Animation");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 600);
			frame.setLayout(new BorderLayout());

			AnimationPanel animationPanel = new AnimationPanel();
			frame.add(animationPanel, BorderLayout.CENTER);

			Timer timer = new Timer(20, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					animationPanel.moveBall();
				}
			});
			timer.start();

			frame.setVisible(true);
		});
	}
}

class AnimationPanel extends JPanel {
	private static final int BALL_SIZE = 30;
	private static final int MOVE_STEP = 5;
	private int ballX = 0;
	private int ballY = 100;

	public AnimationPanel() {
		setPreferredSize(new Dimension(800, 600));
		setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
	}

	public void moveBall() {
		ballX += MOVE_STEP;
		if (ballX > getWidth()) {
			ballX = -BALL_SIZE; // Reset position to start from the left
		}
		repaint();
	}
}
