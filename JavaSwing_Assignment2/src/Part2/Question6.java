package Part2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Question6 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("File Chooser Example");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(600, 200);
			frame.setLayout(new BorderLayout());

			// Create a button to open the file chooser
			JButton openFileButton = new JButton("Open File");
			frame.add(openFileButton, BorderLayout.NORTH);

			// Create a label to display file path and size
			JLabel fileInfoLabel = new JLabel("Select a file to see its path and size");
			frame.add(fileInfoLabel, BorderLayout.CENTER);

			// Add action listener to the button
			openFileButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Create a file chooser
					JFileChooser fileChooser = new JFileChooser();
					int returnValue = fileChooser.showOpenDialog(frame);

					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						String filePath = selectedFile.getAbsolutePath();
						long fileSize = selectedFile.length(); // File size in bytes

						// Update label with file path and size
						fileInfoLabel.setText("File: " + filePath + " | Size: " + fileSize + " bytes");
					} else {
						fileInfoLabel.setText("File selection cancelled");
					}
				}
			});

			// Display the frame
			frame.setVisible(true);
		});
	}
}
