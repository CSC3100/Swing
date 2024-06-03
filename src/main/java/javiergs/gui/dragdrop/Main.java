package javiergs.gui.dragdrop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class creates a window with a menu bar and a DrawPanel where user can draw cities and their connections
 * and save or load the data.
 *
 * @version 1.0
 * @author javiergs
 */
public class Main extends JFrame implements ActionListener {
	
	public static void main(String[] args) {
		// look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error", e);
		}
		// main
		Main main = new Main();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setTitle("Example of Drag and Drop in Java Swing");
		main.setSize(800, 600);
		main.setLocationRelativeTo(null);
		main.setResizable(false);
		main.setVisible(true);
		// instructions
		showInstructions(main);
	}
	
	public Main() {
		DrawPanel drawPanel = new DrawPanel();
		add(drawPanel);
		createMenuBar();
	}
	
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		JMenuItem clear = new JMenuItem("Clear");
		JMenuItem save = new JMenuItem("Save...");
		JMenuItem load = new JMenuItem("Load...");
		JMenuItem instructions = new JMenuItem("Instructions...");
		menuBar.add(file);
		menuBar.add(help);
		file.add(clear);
		file.add(save);
		file.add(load);
		help.add(instructions);
		setJMenuBar(menuBar);
		clear.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		instructions.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear")) {
			Blackboard.getInstance().clear();
			repaint();
		} else if (e.getActionCommand().equals("Save...")) {
			save();
		} else if (e.getActionCommand().equals("Load...")) {
			load();
			repaint();
		} else if (e.getActionCommand().equals("Instructions...")) {
			showInstructions(this);
		}
	}
	
	private void load() {
		// file chooser to select directory and name
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to load");
		int userSelection = fileChooser.showOpenDialog(this);
		if (userSelection != JFileChooser.APPROVE_OPTION) {
			return;
		}
		// load
		String path = fileChooser.getSelectedFile().getAbsolutePath();
		if (!path.isEmpty()) {
			try {
				Blackboard.getInstance().readFile(path);
			} catch (IOException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error", ex);
			}
		}
	}
	
	private void save() {
		// file chooser to select directory and name
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		int userSelection = fileChooser.showSaveDialog(this);
		if (userSelection != JFileChooser.APPROVE_OPTION) {
			return;
		}
		// save
		String path = fileChooser.getSelectedFile().getAbsolutePath();
		if (!path.endsWith(".dat")) {
			path += ".dat";
		}
		try {
			Blackboard.getInstance().saveToFile(path);
		} catch (IOException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error", ex);
		}
	}
	
	private static void showInstructions(Main main) {
		JOptionPane.showMessageDialog(main,
			"<html><body><p>"
				+ "<b>Instructions:</b><br/><br/>"
				+ "To get started, click on the screen to add cities.<br/>"
				+ "Save and reload your data clicking on the 'File' menu to save or load a file.<br/>"
				+ "Clear the screen by clicking on 'File' -> 'Clear'.<br/><br/>"
				+ "Enjoy!<br/>"
				+ "</p></body></html>",
			"Welcome", JOptionPane.INFORMATION_MESSAGE);
	}
	
}