package Part1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

// Custom Layout Manager
class CustomLayout implements LayoutManager {
	@Override
	public void addLayoutComponent(String name, Component comp) {
		// Not used in this example
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		// Not used in this example
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return new Dimension(400, 400); // Default size
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return preferredLayoutSize(parent);
	}

	@Override
	public void layoutContainer(Container parent) {
		int componentCount = parent.getComponentCount();
		Insets insets = parent.getInsets();
		int availableWidth = parent.getWidth() - insets.left - insets.right;
		int availableHeight = parent.getHeight() - insets.top - insets.bottom;
		int width = availableWidth / 3;
		int height = availableHeight / 3;

		for (int i = 0; i < componentCount; i++) {
			Component comp = parent.getComponent(i);
			int x = (i % 3) * width + insets.left;
			int y = (i / 3) * height + insets.top;
			comp.setBounds(x, y, width, height);
		}
	}
}

// Custom Table Model
class CustomTableModel extends AbstractTableModel {
	private final List<List<Object>> data;
	private final String[] columnNames;

	public CustomTableModel(List<List<Object>> data, String[] columnNames) {
		this.data = data;
		this.columnNames = columnNames;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex).get(columnIndex);
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true; // Allow editing cells
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		data.get(rowIndex).set(columnIndex, value);
		fireTableCellUpdated(rowIndex, columnIndex);
	}
}

// Main Class
public class Question5 {
	public static void main(String[] args) {
		// Custom Layout Manager Demo
		JFrame layoutFrame = new JFrame("Custom Layout Manager");
		layoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layoutFrame.setSize(600, 600);
		layoutFrame.setLayout(new CustomLayout());

		JButton button1 = new JButton("Button 1");
		JButton button2 = new JButton("Button 2");
		JButton button3 = new JButton("Button 3");
		JButton button4 = new JButton("Button 4");

		// Customizing Buttons
		button1.setBackground(Color.CYAN);
		button1.setForeground(Color.BLACK);
		button2.setBackground(Color.MAGENTA);
		button2.setForeground(Color.WHITE);
		button3.setBackground(Color.GREEN);
		button3.setForeground(Color.BLACK);
		button4.setBackground(Color.ORANGE);
		button4.setForeground(Color.WHITE);

		layoutFrame.add(button1);
		layoutFrame.add(button2);
		layoutFrame.add(button3);
		layoutFrame.add(button4);

		layoutFrame.setVisible(true);

		// JTable Custom Model Demo
		List<List<Object>> data = new ArrayList<>();
		data.add(Arrays.asList("John", 25, "Engineer"));
		data.add(Arrays.asList("Jane", 30, "Designer"));
		data.add(Arrays.asList("Paul", 35, "Manager"));

		String[] columnNames = { "Name", "Age", "Occupation" };

		CustomTableModel model = new CustomTableModel(data, columnNames);
		JTable table = new JTable(model);

		// Customizing JTable
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		table.setFillsViewportHeight(true);
		table.setRowHeight(25);

		// Setting Header Style
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.DARK_GRAY);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("SansSerif", Font.BOLD, 14));

		JFrame tableFrame = new JFrame("Custom JTable Model Example");
		tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tableFrame.setSize(600, 400);
		tableFrame.setLayout(new BorderLayout());
		tableFrame.add(new JScrollPane(table), BorderLayout.CENTER);
		tableFrame.setVisible(true);
	}
}
