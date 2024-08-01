package Part1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Question7 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Dialogs and Menus");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(600, 400);
			frame.setLayout(new BorderLayout());

			// Create a button to show the custom dialog
			JButton showDialogButton = new JButton("Show Custom Dialog");
			showDialogButton.addActionListener(e -> showCustomDialog(frame));

			// Create a panel to add the button
			JPanel panel = new JPanel();
			panel.add(showDialogButton);
			frame.add(panel, BorderLayout.CENTER);

			// Create a context-sensitive popup menu
			JPopupMenu popupMenu = new JPopupMenu();
			JMenuItem item1 = new JMenuItem("Option 1");
			JMenuItem item2 = new JMenuItem("Option 2");
			JMenuItem item3 = new JMenuItem("Option 3");
			JMenuItem item4 = new JMenuItem("Enable/Disable Option 2");

			item1.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Option 1 Selected"));
			item2.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Option 2 Selected"));
			item3.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Option 3 Selected"));
			item4.addActionListener(e -> toggleMenuItem(item2));

			popupMenu.add(item1);
			popupMenu.add(item2);
			popupMenu.add(item3);
			popupMenu.add(new JSeparator()); // Separator line in menu
			popupMenu.add(item4);

			// Add mouse listener to show the popup menu
			frame.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (e.isPopupTrigger()) {
						popupMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						popupMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});

			frame.setVisible(true);
		});
	}

	private static void showCustomDialog(JFrame parent) {
		// Create a custom dialog
		JDialog dialog = new JDialog(parent, "Custom Dialog", true);
		dialog.setLayout(new BorderLayout());
		dialog.setSize(350, 200);
		dialog.setLocationRelativeTo(parent);

		// Create panel for input fields
		JPanel inputPanel = new JPanel(new GridLayout(3, 2));
		JTextField nameField = new JTextField();
		JTextField ageField = new JTextField();
		JTextField occupationField = new JTextField();

		inputPanel.add(new JLabel("Name:"));
		inputPanel.add(nameField);
		inputPanel.add(new JLabel("Age:"));
		inputPanel.add(ageField);
		inputPanel.add(new JLabel("Occupation:"));
		inputPanel.add(occupationField);

		dialog.add(inputPanel, BorderLayout.CENTER);

		// Add buttons
		JPanel buttonPanel = new JPanel();
		JButton submitButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");

		submitButton.addActionListener(e -> {
			String name = nameField.getText();
			String age = ageField.getText();
			String occupation = occupationField.getText();
			JOptionPane.showMessageDialog(dialog,
					"Submitted:\nName: " + name + "\nAge: " + age + "\nOccupation: " + occupation);
			dialog.dispose();
		});

		cancelButton.addActionListener(e -> dialog.dispose());

		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		dialog.add(buttonPanel, BorderLayout.SOUTH);

		dialog.setVisible(true);
	}

	private static void toggleMenuItem(JMenuItem item) {
		if (item.isEnabled()) {
			item.setEnabled(false);
		} else {
			item.setEnabled(true);
		}
	}
}
