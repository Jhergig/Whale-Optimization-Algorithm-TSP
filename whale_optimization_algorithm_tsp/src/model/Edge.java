package model;

public class Edge implements Comparable<Edge> {
    public int sourceNode; // edge source node
    public int targetNode; // edge target node
    public int cost; // edge cost

    public Edge(int sourceNode, int targetNode, int cost) {
	this.sourceNode = sourceNode;
	this.targetNode = targetNode;
	this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
	return this.cost - o.cost;
    }

}
