package Part2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Question8 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Create the main frame
			JFrame frame = new JFrame("Swing Application with About Dialog");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 300);
			frame.setLayout(new BorderLayout());

			// Create a menu bar
			JMenuBar menuBar = new JMenuBar();
			JMenu helpMenu = new JMenu("Help");
			JMenuItem aboutMenuItem = new JMenuItem("About");

			helpMenu.add(aboutMenuItem);
			menuBar.add(helpMenu);
			frame.setJMenuBar(menuBar);

			// Add action listener for "About" menu item
			aboutMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Create and display the custom dialog
					showAboutDialog(frame);
				}
			});

			// Add a placeholder component to the frame
			frame.add(new JLabel("Main Content Here", SwingConstants.CENTER), BorderLayout.CENTER);

			// Display the frame
			frame.setVisible(true);
		});
	}

	private static void showAboutDialog(JFrame parentFrame) {
		// Create the dialog
		JDialog dialog = new JDialog(parentFrame, "About", true);
		dialog.setLayout(new BorderLayout());
		dialog.setSize(300, 200);
		dialog.setLocationRelativeTo(parentFrame);

		// Add content to the dialog
		JLabel label = new JLabel(
				"<html><h2>My Swing Application</h2><p>Version 1.0<br/>Developed by Your Name</p></html>",
				SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 14));

		JButton okButton = new JButton("OK");
		okButton.addActionListener(e -> dialog.dispose());

		dialog.add(label, BorderLayout.CENTER);
		dialog.add(okButton, BorderLayout.SOUTH);

		// Show the dialog
		dialog.setVisible(true);
	}
}
