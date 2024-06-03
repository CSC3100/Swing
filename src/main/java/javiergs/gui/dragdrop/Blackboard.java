package javiergs.gui.dragdrop;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Blackboard class is a singleton that stores the nodes of the graph.
 * It also provides a method to save the data to a file and to read the data from a file.
 *
 * @author javiergs
 * @version 1.0
 */
public class Blackboard {
	
	private static Blackboard instance;
	
	private ArrayList<Node> nodes = new ArrayList<>();
	
	protected Blackboard() {
	}
	
	public static Blackboard getInstance() {
		if (instance == null) {
			instance = new Blackboard();
		}
		return instance;
	}
	
	public void add(Node node) {
		nodes.add(node);
	}
	
	public Node get(int index) {
		return nodes.get(index);
	}
	
	public int size() {
		return nodes.size();
	}
	
	public void clear() {
		nodes.clear();
	}
	
	public int[] travelingOrder() {
		Set<Integer> visited = new HashSet<>();
		int[] order = new int[nodes.size()];
		int size = nodes.size();
		int currentIndex = 0;
		while (visited.size() < nodes.size()) {
			order[visited.size()] = currentIndex;
			Node currentNode = nodes.get(currentIndex);
			double minDistance = Double.POSITIVE_INFINITY;
			int minDistanceIndex = -1;
			for (int j = 0; j < size; j++) {
				if (!visited.contains(j)) {
					double distance = Math.hypot(
						Math.abs(nodes.get(j).getX() - currentNode.getX()),
						Math.abs(nodes.get(j).getY() - currentNode.getY())
					);
					if (distance < minDistance) {
						minDistanceIndex = j;
						minDistance = distance;
					}
				}
			}
			visited.add(currentIndex);
			currentIndex = minDistanceIndex;
		}
		return order;
	}
	
	public void saveToFile(String path) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("NAME: userCreated" + nodes.size() + "\n");
		sb.append("COMMENT: " + nodes.size() + " locations" + "\n");
		sb.append("COMMENT: created by the user" + "\n");
		sb.append("TYPE: TSP" + "\n");
		sb.append("DIMENSION: " + nodes.size() + "\n");
		sb.append("EDGE_WEIGHT_TYPE : EUC_2D" + "\n");
		sb.append("NODE_COORD_SECTION" + "\n");
		for (int i = 0; i < nodes.size(); i++) {
			sb.append("" + (i + 1) + " " +
				nodes.get(i).getX() + " " +
				nodes.get(i).getY() + " " +
				nodes.get(i).getLabel() + "\n");
		}
		sb.append("EOF");
		FileOutputStream saveFile = new FileOutputStream(path);
		ObjectOutputStream saveObject = new ObjectOutputStream(saveFile);
		saveObject.writeBytes(sb.toString());
		saveObject.close();
	}
	
	public void readFile(String path) throws IOException {
		nodes.clear();
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		boolean started = false;
		while ((str = br.readLine()) != null) {
			if (str.startsWith("1")) {
				started = true;
			} else if (str.startsWith("EOF")) {
				return;
			}
			if (started) {
				String[] data = str.split(" ");
				int x = Integer.parseInt(data[1].trim());
				int y = Integer.parseInt(data[2].trim());
				String label = data[3];
				nodes.add(new Node(label, x, y));
			}
		}
	}
	
}