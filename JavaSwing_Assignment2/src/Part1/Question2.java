package Part1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.ExecutionException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class Question2 extends JComponent {
	private String status = "Processing...";
	private int progress = 0;
	private JProgressBar progressBar;

	public Question2() {
		// Initialize the progress bar
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		this.setLayout(null);
		progressBar.setBounds(50, 130, 200, 30);
		this.add(progressBar);

		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws InterruptedException {
				// Simulate a long task with progress updates
				for (int i = 0; i <= 100; i += 10) {
					Thread.sleep(200); // Simulate work
					progress = i;
					progressBar.setValue(progress); // Update the progress bar
				}
				return null;
			}

			@Override
			protected void done() {
				try {
					get(); // Get result to handle exceptions
					status = "Completed!";
					progressBar.setVisible(false); // Hide the progress bar
				} catch (InterruptedException e) {
					status = "Failed!";
				} catch (ExecutionException e) {
					status = "Failed!";
				}
				repaint();
			}
		};
		worker.execute();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		g2d.drawString(status, 50, 100);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("SwingWorker Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.add(new Question2());
		frame.setVisible(true);
	}
}
