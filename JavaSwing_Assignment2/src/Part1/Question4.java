package Part1;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Model
class CounterModel {
	private int count;
	private final List<CounterListener> listeners = new ArrayList<>();

	public int getCount() {
		return count;
	}

	public void increment() {
		count++;
		notifyListeners();
	}

	public void decrement() {
		count--;
		notifyListeners();
	}

	public void addCounterListener(CounterListener listener) {
		listeners.add(listener);
	}

	private void notifyListeners() {
		for (CounterListener listener : listeners) {
			listener.countChanged(count);
		}
	}
}

// Listener
interface CounterListener {
	void countChanged(int newCount);
}

// View
class CounterView extends JPanel {
	private final JLabel countLabel;
	private final JButton incrementButton;
	private final JButton decrementButton;

	public CounterView() {
		countLabel = new JLabel("0", JLabel.CENTER);
		incrementButton = new JButton("Increment");
		decrementButton = new JButton("Decrement");

		setLayout(new BorderLayout());
		add(countLabel, BorderLayout.CENTER);
		add(incrementButton, BorderLayout.NORTH);
		add(decrementButton, BorderLayout.SOUTH);
	}

	public void setCount(int count) {
		countLabel.setText(String.valueOf(count));
	}

	public void addIncrementListener(ActionListener listener) {
		incrementButton.addActionListener(listener);
	}

	public void addDecrementListener(ActionListener listener) {
		decrementButton.addActionListener(listener);
	}
}

// Controller
class CounterController {
	private final CounterModel model;
	private final CounterView view;

	public CounterController(CounterModel model, CounterView view) {
		this.model = model;
		this.view = view;

		view.addIncrementListener(e -> model.increment());
		view.addDecrementListener(e -> model.decrement());

		model.addCounterListener(view::setCount);
	}
}

// Main
public class Question4 {
	public static void main(String[] args) {
		// Instantiate model and view
		CounterModel model = new CounterModel();
		CounterView view = new CounterView();

		// Instantiate controller
		new CounterController(model, view);

		// Setup frame
		JFrame frame = new JFrame("MVC Counter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 150);
		frame.add(view);
		frame.setVisible(true);
	}
}
