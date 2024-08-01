package Part2;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class Question13 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("File System Viewer");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 600);
			frame.setLayout(new BorderLayout());

			// Create the JTree and load file system
			JTree fileTree = new JTree(createFileSystemModel());
			fileTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

			// Create a button to open the selected file
			JButton openButton = new JButton("Open File");
			openButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TreePath selectedPath = fileTree.getSelectionPath();
					if (selectedPath != null) {
						File selectedFile = (File) ((DefaultMutableTreeNode) selectedPath.getLastPathComponent())
								.getUserObject();
						if (selectedFile.isFile()) {
							try {
								// Attempt to open the file with the default application
								Desktop.getDesktop().open(selectedFile);
							} catch (IOException ex) {
								JOptionPane.showMessageDialog(frame, "Error opening file: " + ex.getMessage(), "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(frame, "Please select a file.", "No File Selected",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			});

			// Add components to the frame
			frame.add(new JScrollPane(fileTree), BorderLayout.CENTER);
			frame.add(openButton, BorderLayout.SOUTH);

			// Display the frame
			frame.setVisible(true);
		});
	}

	private static DefaultMutableTreeNode createFileSystemModel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(File.listRoots()[0]);
		addNodes(root, File.listRoots()[0]);
		return root;
	}

	private static void addNodes(DefaultMutableTreeNode node, File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File child : files) {
					DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
					node.add(childNode);
					addNodes(childNode, child);
				}
			}
		}
	}
}
