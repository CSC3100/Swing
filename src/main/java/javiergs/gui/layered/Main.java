package javiergs.gui.layered;

import javax.swing.*;
import java.awt.*;

/**
 * Example of a LayeredPane showing a background, two clouds and a calculator panel
 *
 * @author javiergs
 * @version 1.0
 */
public class Main extends JFrame {
	
	public static void main(String[] args) {
		Main main = new Main();
		main.setTitle("Example - LayeredPane");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setSize(800, 600);
		main.setVisible(true);
	}
	
	public Main() {
		// Create background panel
		BackgroundPanel backgroundPanel = new BackgroundPanel();
		backgroundPanel.setOpaque(false);
		backgroundPanel.setBounds(0, 0, 800, 600);
		// Create cloud panel
		CloudPanel cloudPanelGray = new CloudPanel(0, 100, Color.GRAY);
		cloudPanelGray.setOpaque(false);
		cloudPanelGray.setBounds(0, 0, 800, 300);
		// Create another cloud panel
		CloudPanel cloudPanelWhite = new CloudPanel(50, 100, Color.WHITE);
		cloudPanelWhite.setOpaque(false);
		cloudPanelWhite.setBounds(0, 100, 800, 300);
		// Create calculator panel
		CalculatorPanel calculatorPanel = new CalculatorPanel();
		calculatorPanel.setOpaque(true);
		calculatorPanel.setBounds(250, 100, 300, 400);
		calculatorPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		// layeredPane
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(800, 600));
		layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(cloudPanelGray, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(calculatorPanel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(cloudPanelWhite, JLayeredPane.PALETTE_LAYER);
		add(layeredPane);
	}
	
}