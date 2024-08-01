package Part1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Question6 {
	public static void main(String[] args) {
		// Set the Nimbus look-and-feel
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Create a frame
		JFrame frame = new JFrame("Look and Feel Customization");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLayout(new BorderLayout());

		// Create a button with custom styles
		JButton button = new JButton("Click Me");

		// Customize button appearance using UIDefaults
		UIDefaults defaults = UIManager.getDefaults();
		defaults.put("Button.background", Color.CYAN);
		defaults.put("Button.foreground", Color.BLACK);
		defaults.put("Button.font", new Font("Arial", Font.BOLD, 16));

		// Re-apply the Look and Feel with updated defaults
		SwingUtilities.updateComponentTreeUI(frame);

		// Add components to the frame
		frame.add(button, BorderLayout.CENTER);

		// Add action listener to show a dialog
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Button clicked!", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Show the frame
		frame.setVisible(true);
	}
}
