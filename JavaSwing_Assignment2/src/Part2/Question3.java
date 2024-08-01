package Part2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Question3 {
	public static void main(String[] args) {
		// Run the GUI creation on the Event Dispatch Thread
		SwingUtilities.invokeLater(() -> {
			// Create the main frame
			JFrame frame = new JFrame("User Input Form");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 250);

			// Create a panel with GridBagLayout
			JPanel panel = new JPanel(new GridBagLayout());
			frame.add(panel);

			// Create constraints for GridBagLayout
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(5, 5, 5, 5); // Padding

			// Create and add the Name field
			JLabel nameLabel = new JLabel("Name:");
			gbc.gridx = 0;
			gbc.gridy = 0;
			panel.add(nameLabel, gbc);

			JTextField nameField = new JTextField(20);
			gbc.gridx = 1;
			gbc.gridy = 0;
			panel.add(nameField, gbc);

			// Create and add the Email field
			JLabel emailLabel = new JLabel("Email:");
			gbc.gridx = 0;
			gbc.gridy = 1;
			panel.add(emailLabel, gbc);

			JTextField emailField = new JTextField(20);
			gbc.gridx = 1;
			gbc.gridy = 1;
			panel.add(emailField, gbc);

			// Create and add the Password field
			JLabel passwordLabel = new JLabel("Password:");
			gbc.gridx = 0;
			gbc.gridy = 2;
			panel.add(passwordLabel, gbc);

			JPasswordField passwordField = new JPasswordField(20);
			gbc.gridx = 1;
			gbc.gridy = 2;
			panel.add(passwordField, gbc);

			// Create and add the Submit button
			JButton submitButton = new JButton("Submit");
			gbc.gridx = 1;
			gbc.gridy = 3;
			gbc.anchor = GridBagConstraints.EAST;
			panel.add(submitButton, gbc);

			// Create and add the Reset button
			JButton resetButton = new JButton("Reset");
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.anchor = GridBagConstraints.WEST;
			panel.add(resetButton, gbc);

			// Action Listener for Submit Button
			submitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Validate inputs
					String name = nameField.getText().trim();
					String email = emailField.getText().trim();
					String password = new String(passwordField.getPassword()).trim();

					if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Validation Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frame, "Submitted!\nName: " + name + "\nEmail: " + email,
								"Success", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});

			// Action Listener for Reset Button
			resetButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					nameField.setText("");
					emailField.setText("");
					passwordField.setText("");
				}
			});

			// Display the frame
			frame.setVisible(true);
		});
	}
}
