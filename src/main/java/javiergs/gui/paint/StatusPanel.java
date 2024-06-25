package javiergs.gui.paint;

import javax.swing.*;

public class StatusPanel extends JPanel {
			public StatusPanel() {
				// add two buttons to the panel
				JButton button1 = new JButton("Undo");
				JButton button2 = new JButton("Erase");
				add(button1);
				add(button2);
		}
}
