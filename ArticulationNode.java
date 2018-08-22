import java.util.Queue;
public class ArticulationNode{

	Node node;
	 double reachback;
	ArticulationNode parent;
	 double depth;
	 Queue<Node> children;

	public ArticulationNode(Node node, double reachback, ArticulationNode parent) {
		this.node = node;
		this.reachback = reachback;
		this.parent = parent;
		this.depth = reachback;

	}

	
}
