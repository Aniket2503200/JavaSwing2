package Part2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

public class Question14 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Drag-and-Drop Example");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(600, 400);
			frame.setLayout(new BorderLayout());

			// Create a list with items
			DefaultListModel<String> listModel = new DefaultListModel<>();
			listModel.addElement("Item 1");
			listModel.addElement("Item 2");
			listModel.addElement("Item 3");

			JList<String> list = new JList<>(listModel);
			list.setDragEnabled(true);
			list.setTransferHandler(new ListTransferHandler());

			// Create a panel for dropping items
			JPanel dropPanel = new DropPanel();
			dropPanel.setPreferredSize(new Dimension(300, 300));
			dropPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			// Add components to frame
			frame.add(new JScrollPane(list), BorderLayout.WEST);
			frame.add(dropPanel, BorderLayout.CENTER);

			frame.setVisible(true);
		});
	}
}

class ListTransferHandler extends TransferHandler {
	@Override
	protected Transferable createTransferable(JComponent c) {
		JList<?> list = (JList<?>) c;
		return new StringSelection(list.getSelectedValue().toString());
	}

	@Override
	public int getSourceActions(JComponent c) {
		return COPY;
	}
}

class DropPanel extends JPanel {
	public DropPanel() {
		setDropTarget(new DropTarget(this, new DropTargetAdapter() {
			@Override
			public void drop(DropTargetDropEvent e) {
				try {
					e.acceptDrop(DnDConstants.ACTION_COPY);
					Transferable transferable = e.getTransferable();
					String data = (String) transferable.getTransferData(DataFlavor.stringFlavor);
					Graphics g = getGraphics();
					g.setColor(Color.GREEN);
					g.drawString(data, e.getLocation().x, e.getLocation().y);
					e.dropComplete(true);
				} catch (UnsupportedFlavorException | IOException ex) {
					ex.printStackTrace();
					e.rejectDrop();
				}
			}
		}));
	}
}
