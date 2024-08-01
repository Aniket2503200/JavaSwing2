package Part2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Question5 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Student Management");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(600, 400);
			frame.setLayout(new BorderLayout());

			// Create table model with columns
			DefaultTableModel model = new DefaultTableModel(new Object[] { "Name", "Age", "Grade" }, 0);
			JTable table = new JTable(model);

			// Create input fields and buttons
			JPanel inputPanel = new JPanel(new GridLayout(4, 2));
			JTextField nameField = new JTextField();
			JTextField ageField = new JTextField();
			JTextField gradeField = new JTextField();
			JButton addButton = new JButton("Add");
			JButton editButton = new JButton("Edit");
			JButton deleteButton = new JButton("Delete");

			// Add components to the input panel
			inputPanel.add(new JLabel("Name:"));
			inputPanel.add(nameField);
			inputPanel.add(new JLabel("Age:"));
			inputPanel.add(ageField);
			inputPanel.add(new JLabel("Grade:"));
			inputPanel.add(gradeField);
			inputPanel.add(addButton);
			inputPanel.add(editButton);
			inputPanel.add(deleteButton);

			// Add table and input panel to the frame
			frame.add(new JScrollPane(table), BorderLayout.CENTER);
			frame.add(inputPanel, BorderLayout.SOUTH);

			// Add button listeners
			addButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String name = nameField.getText();
					String age = ageField.getText();
					String grade = gradeField.getText();
					if (!name.isEmpty() && !age.isEmpty() && !grade.isEmpty()) {
						model.addRow(new Object[] { name, age, grade });
						nameField.setText("");
						ageField.setText("");
						gradeField.setText("");
					} else {
						JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			editButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						String name = nameField.getText();
						String age = ageField.getText();
						String grade = gradeField.getText();
						if (!name.isEmpty() && !age.isEmpty() && !grade.isEmpty()) {
							model.setValueAt(name, selectedRow, 0);
							model.setValueAt(age, selectedRow, 1);
							model.setValueAt(grade, selectedRow, 2);
						} else {
							JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Please select a row to edit", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			deleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						model.removeRow(selectedRow);
					} else {
						JOptionPane.showMessageDialog(frame, "Please select a row to delete", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			// Display the frame
			frame.setVisible(true);
		});
	}
}
