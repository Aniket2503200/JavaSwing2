package Part2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Question10 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Tabbed Interface");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 600);

			JTabbedPane tabbedPane = new JTabbedPane();

			// Form Tab
			JPanel formPanel = new JPanel();
			formPanel.setLayout(new GridLayout(3, 2));
			formPanel.add(new JLabel("Name:"));
			formPanel.add(new JTextField());
			formPanel.add(new JLabel("Email:"));
			formPanel.add(new JTextField());
			formPanel.add(new JLabel("Password:"));
			formPanel.add(new JPasswordField());
			tabbedPane.addTab("Form", formPanel);

			// Table Tab
			String[] columnNames = { "Name", "Age", "Grade" };
			Object[][] data = { { "John", 20, "A" }, { "Jane", 22, "B" } };
			JTable table = new JTable(data, columnNames);
			JScrollPane tableScrollPane = new JScrollPane(table);
			tabbedPane.addTab("Table", tableScrollPane);

			// Drawing Canvas Tab
			JPanel canvasPanel = new DrawingCanvasPanel();
			tabbedPane.addTab("Drawing Canvas", canvasPanel);

			frame.add(tabbedPane);
			frame.setVisible(true);
		});
	}
}

class DrawingCanvasPanel extends JPanel {
	private static final int CIRCLE_DIAMETER = 50;

	public DrawingCanvasPanel() {
		setPreferredSize(new Dimension(800, 400));
		setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillOval(100, 100, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
	}
}
