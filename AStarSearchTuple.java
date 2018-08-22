
public class AStarSearchTuple implements Comparable<AStarSearchTuple>{

    Node currentNode = null;
    Node fromNode = null;
    double costFromStart = 0;
    double totalCost = 0;

    AStarSearchTuple(Node currentNode, Node fromNode, double costFromStart, double totalCost) {
        this.currentNode = currentNode;
        this.fromNode = fromNode;
        this.costFromStart = costFromStart;
        this.totalCost = totalCost;
    }


	@Override
    public int compareTo(AStarSearchTuple other) {
        if (this.totalCost < other.totalCost) { return -1; }
        else if (this.totalCost > other.totalCost) { return 1; }
        else { return 0; }
    }

	
}