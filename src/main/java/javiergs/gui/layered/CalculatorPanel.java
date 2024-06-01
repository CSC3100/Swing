package javiergs.gui.layered;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for calculator buttons
 *
 * @author javiergs
 * @version 1.0
 */
public class CalculatorPanel extends JPanel {
	
	public CalculatorPanel() {
		setLayout(new BorderLayout());
		// calculator display
		JTextField displayField = new JTextField();
		displayField.setEditable(false); // Make it non-editable
		add(displayField, BorderLayout.NORTH);
		// buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 4));
		String[] buttonLabels = {
			"7", "8", "9", "/",
			"4", "5", "6", "*",
			"1", "2", "3", "-",
			"0", ".", "=", "+"
		};
		for (String label : buttonLabels) {
			JButton button = new JButton(label);
			buttonPanel.add(button);
		}
		add(buttonPanel, BorderLayout.CENTER);
	}
	
}