package algorithms;

import model.Salesman;

public class Greedy1 {
    
    public static void solve(Salesman salesman) {
	solve(salesman, 0);
    }
    
    public static void solve(Salesman salesman, int sourceNode) {
	salesman.sol[0] = sourceNode;
	
	int i = 0;
	while (i < salesman.nNodes - 1) {
		int thisNode = salesman.sol[i];
		int nextNode = -1;
		
		int min = Integer.MAX_VALUE;
		
		for (int candidate = 0; candidate < salesman.nNodes; candidate++) {
			
			if (thisNode == candidate) {
				continue;
			}
			
			boolean candidateInSolution = false;
			for(int j = 0; j < i; j++) {
				if (salesman.sol[j] == candidate) {
					candidateInSolution = true;
					break;
				}
			}
			if (candidateInSolution) {
				continue;
			}
			
			if (salesman.matrix[thisNode][candidate] < min) {
				min = salesman.matrix[thisNode][candidate];
				nextNode = candidate;
			}
		}
		
		salesman.sol[i+1] = nextNode;
		i++;
	}
	
    }

}
