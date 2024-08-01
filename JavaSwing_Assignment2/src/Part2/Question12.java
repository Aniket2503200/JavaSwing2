package Part2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Question12 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("JSplitPane Example");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 600);

			// Create panels for the split pane
			JPanel leftPanel = new JPanel();
			leftPanel.setLayout(new BorderLayout());
			JTextArea leftTextArea = new JTextArea("Left Panel");
			leftPanel.add(new JScrollPane(leftTextArea), BorderLayout.CENTER);

			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new BorderLayout());
			JTextArea rightTextArea = new JTextArea("Right Panel");
			rightPanel.add(new JScrollPane(rightTextArea), BorderLayout.CENTER);

			// Create split pane
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
			splitPane.setDividerLocation(400); // Initial position of the divider

			frame.add(splitPane);

			// Button to update content dynamically
			JButton updateButton = new JButton("Update Content");
			updateButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					leftTextArea.setText("Updated Left Panel Content");
					rightTextArea.setText("Updated Right Panel Content");
				}
			});

			frame.add(updateButton, BorderLayout.SOUTH);

			frame.setVisible(true);
		});
	}
}
