package paint;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	private Stack<Shape> shapes;
	private String shape;
	private String color;
	private boolean drawing;
	private int x, y, x2, y2;
	
	public DrawPanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		shapes = new Stack<Shape>();
		drawing=false;
	}
	
	public void setShape(String shape) {
		this.shape = shape;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void undo() {
		if(!shapes.isEmpty()) shapes.pop();
		repaint();
	}
	
	public void erase() {
		if(!shapes.isEmpty()) shapes.clear();
		repaint();
	}
	
	public void paintComponent (Graphics g) {
		setBackground(Color.LIGHT_GRAY);
		super.paintComponent(g);
		if(shapes!=null) {
			for(Shape shape: shapes) shape.draw(g);
		}
		g.setColor(Color.GRAY);
		if (drawing)
			g.drawRect(
				(x2>x)?x:x2,
				(y2>y)?y:y2,
				(x2>x)?x2-x:x-x2,
				(y2>y)?y2-y:y-y2
			);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		drawing=true;
		x=e.getX();
		y=e.getY();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		drawing=false;
		x2 = e.getX();
		y2 = e.getY();
		if(shape.equals("paint.Rectangle")) {shapes.push(new Rectangle(x,y,x2,y2,color)); }
		if(shape.equals("paint.Circle")) {shapes.push(new Circle(x,y,x2,y2,color)); }
		if(shape.equals("paint.Arc")) {shapes.push(new Arc(x,y,x2,y2,color)); }
		repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		x2=e.getX();
		y2=e.getY();
		repaint();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
}