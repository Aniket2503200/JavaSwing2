package Part2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Question11 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Look and Feel Switcher");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 300);
			frame.setLayout(new BorderLayout());

			// Create a combo box for look-and-feel options
			String[] looks = { "System Look and Feel", "Nimbus", "Metal", "Motif" };
			JComboBox<String> lookAndFeelCombo = new JComboBox<>(looks);
			frame.add(lookAndFeelCombo, BorderLayout.NORTH);

			// Add an action listener to the combo box
			lookAndFeelCombo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String selectedLook = (String) lookAndFeelCombo.getSelectedItem();
					try {
						switch (selectedLook) {
						case "Nimbus":
							UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
							break;
						case "Metal":
							UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
							break;
						case "Motif":
							UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
							break;
						case "System Look and Feel":
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							break;
						}
						SwingUtilities.updateComponentTreeUI(frame);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

			// Add a sample component to the frame
			JButton sampleButton = new JButton("Sample Button");
			frame.add(sampleButton, BorderLayout.CENTER);

			frame.setVisible(true);
		});
	}
}
