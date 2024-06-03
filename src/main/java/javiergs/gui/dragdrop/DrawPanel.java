package javiergs.gui.dragdrop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * DrawPanel is a JPanel where the user can draw nodes and their connections
 *
 * @author javiergs
 * @version 1.0
 */
public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	int preX, preY;
	int pressOut = -1;
	
	public DrawPanel() {
		setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw grid as background
		g.setColor(new Color(250, 250, 250, 255));
		for (int i = 0; i < getWidth(); i += 20) {
      g.drawLine(i, 0, i, getHeight());
    }
		    for (int i = 0; i < getHeight(); i += 20) {
      g.drawLine(0, i, getWidth(), i);
    }
		// draw nodes and connections
		Graphics2D g2 = (Graphics2D) g;
		// this is a problem
		int[] order = Blackboard.getInstance().travelingOrder();
		g2.setColor(new Color(74, 136, 98, 255));
		for (int i = 0; i < Blackboard.getInstance().size(); i++) {
			if (i == Blackboard.getInstance().size() - 1) {
				Blackboard.getInstance().get(
					order[i]).drawConnect(Blackboard.getInstance().get(0), g2);
			} else {
				Blackboard.getInstance().get(
					order[i]).drawConnect(Blackboard.getInstance().get(order[i + 1]), g2);
			}
			Blackboard.getInstance().get(i).draw(g2);
		}
	}
	
	private int citySelected(MouseEvent e) {
		int citySelected = -1;
		for (int i = 0; i < Blackboard.getInstance().size(); i++) {
			if (Blackboard.getInstance().get(i).contains(e.getX(), e.getY())) {
				citySelected = i;
			}
		}
		return citySelected;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (citySelected(e) == -1) {
			String name = "unnamed"+Blackboard.getInstance().size();
			Node newNode = new Node(name, e.getX(), e.getY(), 10, 10);
			String result = (String) JOptionPane.showInputDialog(
				e.getComponent(),
				"Type the name of the new city",
				"City Name",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				name
			);
			if (result != null && result.length() > 0) {
				Blackboard.getInstance().add(newNode);
				newNode.setLabel(result);
			}
			repaint();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		pressOut = citySelected(e);
		if (pressOut == -1) return;
		Node node = Blackboard.getInstance().get(pressOut);
		preX = node.getX() - e.getX();
		preY = node.getY() - e.getY();
		node.move(preX + e.getX(), preY + e.getY());
		repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (pressOut == -1) return;
		Node node = Blackboard.getInstance().get(pressOut);
		node.move(preX + e.getX(), preY + e.getY());
		repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (pressOut == -1) return;
		Node node = Blackboard.getInstance().get(pressOut);
		node.move(preX + e.getX(), preY + e.getY());
		repaint();
		pressOut = citySelected(e);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
}