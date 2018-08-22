import java.util.PriorityQueue;

public class AStarSearch {
	
    private PriorityQueue<AStarSearchTuple> fringe = new PriorityQueue<>();
    private Node goal;
	private Node start;
	
    AStarSearch(Node start, Node end) {
    	this.start = start;
        this.goal = end;
        start.visited = true;
        start.estimate = start.getLoc().distance(this.goal.getLoc());
    }

    Node search() {
    	AStarSearchTuple startNode = new AStarSearchTuple(start, null, 0, start.estimate);
        fringe.add(startNode);
        
        while (!fringe.isEmpty()) {
            AStarSearchTuple node;
            node = fringe.poll();
            Node current = node.currentNode;

            if (!current.visited) {
                current.visited = true;
                current.pathFrom = node.fromNode;
                current.costFromStart = node.costFromStart;
            }

            if (current == this.goal) break;

            Node oneEnd = current;

            for (Segment segs : current.getSegmentOut()) {

                Node neighbour = segs.theOtherEnd(oneEnd);

                assert neighbour != null;

                    if (!neighbour.visited) {
                        double costToNeighbour = node.costFromStart + segs.getLength();
                        double estimatedTotal = costToNeighbour + neighbour.getLoc().distance(goal.getLoc());
                        fringe.offer(new AStarSearchTuple(neighbour, current, costToNeighbour, estimatedTotal));
                    
                }
            }
        }
        return this.goal;
    }


    
}


