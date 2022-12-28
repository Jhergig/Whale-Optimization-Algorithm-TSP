package model;

public class Component {
    private int nNodes; // number of nodes in the graph
    private int[] p; // stores the name of the component (a number) for each of the nodes

    public Component(int nNodes) {
	this.nNodes = nNodes;
	p = new int[nNodes];
	// initially there are no edges and all nodes are disconnected
	for (int i = 0; i < nNodes; i++)
	    p[i] = i; // e.g., node 0 is in component 0, node 1 is in component 1 and so on...
    }

    /**
     * Merge nodes into a single partition by drawing a new edge between a and b
     * 
     * @param a A partition
     * @param b Another partition
     */
    public void mergeComponents(int pa, int pb) {
	int temp;
	int a = p[pa];
	int b = p[pb];
	// if I have component 4 with nodes 4 and 5, and now I want to include node 3
	// (component 3) to that component => all share component 3 (a = 3; b = 4)
	// if I have component 4 with nodes 4 and 5, and now I want to include node 6
	// (component 6) to that component => all share component 4 (a = 4; b = 6)
	if (a > b) { // just to store the smallest value. E.g. node 4 and node 5 connected => all
		     // share partition 4
	    temp = a;
	    a = b;
	    b = temp;
	}

	for (int i = 0; i < nNodes; i++) {
	    if (p[i] == b)
		p[i] = a;
	}
    }

    /**
     * Check if there is only one connected component in the graph
     * 
     * @return True if the is only one connected component
     */
    public boolean onlyOneConnectedComponent() {
	for (int i = 0; i < nNodes; i++)
	    if (p[i] != 0)
		return false;
	return true;
    }

    /**
     * Get a component of the graph
     * 
     * @param i Number of node
     * @return The value of the component of that node
     */
    public int getComponent(int i) {
	return p[i];
    }
}
