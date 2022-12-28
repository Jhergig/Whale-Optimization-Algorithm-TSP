package application;

import algorithms.Greedy1;
import algorithms.Greedy2;
import model.Salesman;

public class Main {

    public static void main(String[] args) {
//	Salesman salesman = new Salesman("pr76.tsp", "pr76.opt.tour");
	Salesman salesman = new Salesman("pr76.tsp");

	salesman.printAdjacencyMatrix();

//	Greedy1.solve(salesman);
	Greedy2.solve(salesman);

	System.out.print("Solution: ");
	salesman.printSolution();
	System.out.println("Solution cost: " + salesman.solutionCost());

	salesman.createSVG();
    }

}
