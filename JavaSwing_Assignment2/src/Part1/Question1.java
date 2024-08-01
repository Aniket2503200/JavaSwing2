package Part1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Question1 extends JComponent {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// Gradient background
		LinearGradientPaint gradient = new LinearGradientPaint(0, 0, getWidth(), getHeight(),
				new float[] { 0.0f, 1.0f }, new Color[] { Color.CYAN, Color.MAGENTA });
		g2d.setPaint(gradient);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		// Draw a rounded rectangle with a shadow effect
		g2d.setColor(Color.DARK_GRAY);
		g2d.fill(new RoundRectangle2D.Double(20, 20, 150, 100, 20, 20));

		g2d.setColor(Color.RED);
		g2d.fill(new RoundRectangle2D.Double(15, 15, 150, 100, 20, 20));

		// Draw text with custom styling
		g2d.setColor(Color.WHITE);
		g2d.setFont(g2d.getFont().deriveFont(24f));
		FontRenderContext frc = g2d.getFontRenderContext();
		TextLayout layout = new TextLayout("Custom Drawing", g2d.getFont(), frc);
		layout.draw(g2d, 30, 90);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Custom Painting Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.add(new Question1());
		frame.setVisible(true);
	}
}
