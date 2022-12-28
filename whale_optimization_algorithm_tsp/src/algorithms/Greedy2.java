package algorithms;

import java.util.ArrayList;
import java.util.Collections;

import model.Component;
import model.Edge;
import model.Salesman;

public class Greedy2 {

    public static void solve(Salesman salesman) {
	ArrayList<Edge> edges = new ArrayList<Edge>();
	for (int i = 0; i < salesman.matrix.length - 1; i++) {
	    for (int j = i + 1; j < salesman.matrix.length; j++) {
		edges.add(new Edge(i, j, salesman.matrix[i][j]));
	    }
	}
	Collections.sort(edges);

	Component component = new Component(salesman.nNodes);
	ArrayList<Edge> solutionEdges = new ArrayList<Edge>();
	int[] edgesPerNode = new int[salesman.nNodes];

	for (Edge edge : edges) {
	    if (component.getComponent(edge.sourceNode) == component.getComponent(edge.targetNode)) {
		continue;
	    }
	    if (edgesPerNode[edge.sourceNode] == 2 || edgesPerNode[edge.targetNode] == 2) {
		continue;
	    }
	    solutionEdges.add(edge);
	    component.mergeComponents(edge.sourceNode, edge.targetNode);
	    edgesPerNode[edge.sourceNode] += 1;
	    edgesPerNode[edge.targetNode] += 1;

	    if (component.onlyOneConnectedComponent()) {
		break;
	    }
	}
	for (int i = 0; i < edgesPerNode.length; i++) {
	    if (edgesPerNode[i] == 1) {
		for (int j = i + 1; j < edgesPerNode.length; j++) {
		    if (edgesPerNode[j] == 1) {
			solutionEdges.add(new Edge(i, j, 0));
			break;
		    }
		}
		break;
	    }

	}

	salesman.sol[0] = 0;
	int i = 1;
	while (salesman.sol[salesman.sol.length - 1] == 0) {
	    for (Edge edge : solutionEdges) {
		if (edge.sourceNode == salesman.sol[i - 1]) {
		    salesman.sol[i] = edge.targetNode;
		    solutionEdges.remove(edge);
		    i++;
		    break;
		}
		if (edge.targetNode == salesman.sol[i - 1]) {
		    salesman.sol[i] = edge.sourceNode;
		    solutionEdges.remove(edge);
		    i++;
		    break;
		}
	    }
	}
    }
}
