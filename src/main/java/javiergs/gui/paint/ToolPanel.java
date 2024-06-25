package paint;

import javax.swing.*;
import java.awt.*;

public class ToolPanel extends JPanel {
	// add a selection menu and a group of radio buttons to the panel
	public ToolPanel() {
		String[] colors = {"Black", "Red", "Blue", "Green", "Yellow", "Orange", "Pink"};
		JComboBox colorMenu = new JComboBox(colors);
		JRadioButton rectangleRadio = new JRadioButton("Rectangle");
		JRadioButton circleRadio = new JRadioButton("Circle");
		JRadioButton arcRadio = new JRadioButton("Arc");
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rectangleRadio);
		buttonGroup.add(circleRadio);
		buttonGroup.add(arcRadio);
		setLayout(new GridLayout(7, 1));
		add(colorMenu);
		add(rectangleRadio);
		add(circleRadio);
		add(arcRadio);
	}

}
