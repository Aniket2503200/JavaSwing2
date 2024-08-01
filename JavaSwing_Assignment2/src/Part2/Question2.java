package Part2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Question2 {
	public static void main(String[] args) {
		// Run the GUI creation on the Event Dispatch Thread
		SwingUtilities.invokeLater(() -> {
			// Create the main frame
			JFrame frame = new JFrame("Event Handling Example");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 200);
			frame.setLayout(new BorderLayout());

			// Create a panel to hold components
			JPanel panel = new JPanel();
			frame.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout());

			// Create a text field for user input
			JTextField textField = new JTextField(20);
			panel.add(textField, BorderLayout.NORTH);

			// Create a button to trigger the action
			JButton button = new JButton("Show Text");
			panel.add(button, BorderLayout.CENTER);

			// Create a label to display the entered text
			JLabel label = new JLabel(" ", JLabel.CENTER);
			panel.add(label, BorderLayout.SOUTH);

			// Add an ActionListener to the button
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Get text from the text field and set it in the label
					String text = textField.getText();
					label.setText(text);
				}
			});

			// Display the frame
			frame.setVisible(true);
		});
	}
}
