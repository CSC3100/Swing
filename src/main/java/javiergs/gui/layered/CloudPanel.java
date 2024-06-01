package javiergs.gui.layered;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel for drawing clouds
 *
 * @author javiergs
 * @version 1.0
 */
public class CloudPanel extends JPanel implements ActionListener {
	
	private int x;
	private int y;
	private Color color;
	
	public CloudPanel(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		// A timer for the animation part
		Timer timer = new Timer(50, this);
		timer.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw cloud
		g.setColor(color);
		g.fillOval(x, y + 30, 120, 80);
		g.fillOval(x + 30, y, 150, 90);
		g.fillOval(x + 70, y + 40, 160, 80);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		x = (x > 800) ? -50 : x + 5;
		repaint();
	}
	
}