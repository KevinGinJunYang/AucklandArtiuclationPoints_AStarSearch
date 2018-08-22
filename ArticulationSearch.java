import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ArticulationSearch {

	private Stack<ArticulationNode> stack = new Stack<>();
	static Set<Node> APoints = new HashSet<Node>();
	private ArticulationNode rootNode;
	
	
	protected void ArtPoint() {
		boolean pickStartNode = true;
		Node startNode = null;
		int numSubtrees = 0;

		for (Node n : Mapper.unvisitedNodes) {
			n.depth = Double.POSITIVE_INFINITY;
			if (pickStartNode == true) {
				startNode = n;
				pickStartNode = false;
			}
		}
		startNode.depth = 0;
		if (Mapper.unvisitedNodes.contains(startNode)) {
			Mapper.unvisitedNodes.remove(startNode);
		}
		for (Node n : startNode.getNeighbours()) {
			if (n.depth == Double.POSITIVE_INFINITY) {
				if (Mapper.unvisitedNodes.contains(n)) {
					Mapper.unvisitedNodes.remove(n);
				}			
					iterAPoints(n, startNode);
				numSubtrees++;
			}
		}
		if (numSubtrees > 1) {
			ArticulationSearch.APoints.add(startNode);
		}
		Mapper.graph.setArtPoints(ArticulationSearch.APoints);


		Mapper.resetDraw = true;

	};

	public void iterAPoints(Node firstNode, Node root) {
		rootNode = new ArticulationNode(root, 0, null);
		ArticulationNode ap = new ArticulationNode(firstNode,1,rootNode);
		stack.push(ap);
		
		while (!stack.isEmpty()) {
			ArticulationNode current = stack.peek();
			Node node = current.node;
			if (current.children == null) {
				node.depth = current.depth;
				current.reachback = current.depth;
				current.children = new ArrayDeque<Node>();
				for (Node n : node.getNeighbours()) {

					if (Mapper.unvisitedNodes.contains(n)) {
						Mapper.unvisitedNodes.remove(n);
					}
					if (n != current.parent.node) {
						current.children.offer(n);
					}
				}
			} else if (!current.children.isEmpty()) {
				Node child = current.children.poll();
				if (child.depth < Double.POSITIVE_INFINITY) {
					current.reachback = Math.min(current.reachback, child.depth);
				} else {
					stack.push(new ArticulationNode(child, node.depth + 1, current));
				}
			} else {
				if (node != firstNode) {
					if (current.reachback >= current.parent.depth) {
						APoints.add(current.parent.node);
					}
					current.parent.reachback = Math.min(current.parent.reachback, current.reachback);
				}
				stack.pop();
			}
		}
	}

}