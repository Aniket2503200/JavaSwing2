package Part2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Question1 {
	public static void main(String[] args) {
		// Run the GUI creation on the Event Dispatch Thread
		SwingUtilities.invokeLater(() -> {
			// Create the main frame
			JFrame frame = new JFrame("Simple GUI Application");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(300, 200);
			frame.setLayout(new BorderLayout());

			// Create a panel to hold components
			JPanel panel = new JPanel();
			frame.add(panel, BorderLayout.CENTER);

			// Create a label with initial message
			JLabel label = new JLabel("Hello, World!", JLabel.CENTER);
			panel.add(label);

			// Create a button to change the label's text
			JButton button = new JButton("Click Me");
			panel.add(button);

			// Add an ActionListener to the button
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Change the label's text when the button is clicked
					label.setText("Button Clicked!");
				}
			});

			// Display the frame
			frame.setVisible(true);
		});
	}
}
