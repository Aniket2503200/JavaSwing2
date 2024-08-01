package Part1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Question10 {
	public static void main(String[] args) {
		// Initialize JavaFX environment
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Swing and JavaFX Integration");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 600);
			frame.setLayout(new BorderLayout());

			// Create and add JFXPanel to JFrame
			JFXPanel fxPanel = new JFXPanel();
			frame.add(fxPanel, BorderLayout.CENTER);

			// Add a Swing button to JFrame
			JButton swingButton = new JButton("Update JavaFX Label");
			frame.add(swingButton, BorderLayout.SOUTH);

			// Initialize JavaFX content
			SwingUtilities.invokeLater(() -> {
				initFX(fxPanel);
			});

			// Add action listener to Swing button to update JavaFX label
			swingButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateJavaFXLabel();
				}
			});

			// Show JFrame
			frame.setVisible(true);
		});
	}

	private static void initFX(JFXPanel fxPanel) {
		// This method runs on the JavaFX Application Thread
		Application.launch(JavaFXApp.class, new String[] {});
	}

	private static void updateJavaFXLabel() {
		// Update JavaFX Label
		SwingUtilities.invokeLater(() -> {
			JavaFXApp.updateLabelText("Updated from Swing!");
		});
	}

	public static class JavaFXApp extends Application {
		private static Label label;

		@Override
		public void start(Stage primaryStage) {
			// Create JavaFX content
			StackPane root = new StackPane();
			VBox vbox = new VBox(10);
			label = new Label("Initial JavaFX Label");
			Button button = new Button("JavaFX Button");
			vbox.getChildren().addAll(label, button);
			root.getChildren().add(vbox);

			Scene scene = new Scene(root, 800, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("JavaFX Content");
			primaryStage.show();
		}

		public static void updateLabelText(String newText) {
			if (label != null) {
				label.setText(newText);
			}
		}
	}
}
