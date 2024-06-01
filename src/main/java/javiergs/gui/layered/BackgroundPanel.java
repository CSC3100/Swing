package javiergs.gui.layered;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with a background image
 *
 * @version 1.0
 * @autor javiergs
 */
public class BackgroundPanel extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g2d) {
		super.paintComponent(g2d);
		// Draw sky
		g2d.setColor(new Color(135, 206, 235));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		// Draw sun
		g2d.setColor(Color.YELLOW);
		g2d.fillOval(50, 50, 80, 80);
		// Draw grass
		g2d.setColor(new Color(34, 139, 34));
		g2d.fillRect(0, getHeight() - 100, getWidth(), 100);
		// Draw tree trunk
		g2d.setColor(new Color(239, 118, 31));
		g2d.fillRect(150, getHeight() - 200, 40, 100);
		// Draw tree foliage
		g2d.setColor(new Color(34, 139, 34));
		g2d.fillOval(120, getHeight() - 250, 100, 100);
		g2d.fillOval(140, getHeight() - 280, 60, 60);
		g2d.fillOval(160, getHeight() - 250, 100, 100);
	}
	
}