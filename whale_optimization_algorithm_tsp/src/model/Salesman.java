package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Salesman {

    static final int X = 0;
    static final int Y = 1;

    private String name;
    private String comment;
    public int nNodes;
    private int[][] nodesCoord;
    public int[][] matrix;
    public int[] sol;

    public Salesman(String fileName) {
	BufferedReader file = null;

	try {
	    file = new BufferedReader(new FileReader(fileName));

	    name = file.readLine().substring(7);
	    comment = file.readLine().substring(10);
	    file.readLine();
	    nNodes = Integer.parseInt(file.readLine().substring(12));
	    matrix = new int[nNodes][nNodes];
	    nodesCoord = new int[nNodes][2];
	    sol = new int[nNodes];
	    file.readLine();
	    file.readLine();

	    for (int i = 0; i < nNodes; i++) {
		String values[] = file.readLine().split(" ");
		nodesCoord[i][X] = Integer.parseInt(values[1]);
		nodesCoord[i][Y] = Integer.parseInt(values[2]);
	    }

	} catch (FileNotFoundException e) {
	    System.out.println("File not found: " + fileName);

	} catch (IOException e) {
	    System.out.println("File reading error: " + fileName);
	}

	for (int i = 0; i < nNodes; i++) {
	    for (int j = i; j < nNodes; j++) {
		if (i == j) {
		    matrix[i][j] = 0;
		} else {
		    matrix[i][j] = matrix[j][i] = (int) Math
			    .round(Math.sqrt(Math.pow(nodesCoord[i][X] - nodesCoord[j][X], 2)
				    + Math.pow(nodesCoord[i][Y] - nodesCoord[j][Y], 2)));
		}
	    }
	}

    }

    public Salesman(String fileName, String solutionFileName) {
	this(fileName);

	BufferedReader file = null;

	try {
	    file = new BufferedReader(new FileReader(solutionFileName));
	    file.readLine();
	    file.readLine();
	    file.readLine();
	    file.readLine();
	    file.readLine();
	    for (int i = 0; i < nNodes; i++) {
		sol[i] = Integer.parseInt(file.readLine()) - 1;
	    }
	} catch (FileNotFoundException e) {
	    System.out.println("File not found: " + solutionFileName);

	} catch (IOException e) {
	    System.out.println("File reading error: " + solutionFileName);
	}
    }

    public String getName() {
	return name;
    }

    public String getComment() {
	return comment;
    }

    public void printAdjacencyMatrix() {
	for (int i = 0; i < nNodes; i++) {
	    for (int j = 0; j < nNodes; j++) {
		if (matrix[i][j] == Integer.MAX_VALUE)
		    System.out.print("INF\t");
		else
		    System.out.print(matrix[i][j] + "\t");
	    }
	    System.out.println();
	}
	System.out.println();
    }

    public void printSolution() {
	for (int i : sol)
	    System.out.print(i + 1 + "-");
	System.out.println(sol[0] + 1);
    }

    public int solutionCost() {
	int solutionCost = 0;

	for (int i = 0; i < sol.length - 1; i++) {
	    solutionCost += matrix[sol[i]][sol[i + 1]];
	}
	solutionCost += matrix[sol[0]][sol[sol.length - 1]];

	return solutionCost;
    }

    public void createSVG() {
	String filename = name + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd hh.mm.ss"))+ ".svg";
	File svg = new File(filename);
	BufferedWriter bw;

	try {
	    bw = new BufferedWriter(new FileWriter(svg));

	    bw.write("<svg xmlns='http://www.w3.org/2000/svg' version='2.0' width='2000' height='1500'> ");

	    for (int i = 0; i < sol.length - 1; i++) {
		bw.write("<line x1='" + nodesCoord[sol[i]][X] / 10 + "' y1='" + nodesCoord[sol[i]][Y] / 10 + "' x2='"
			+ nodesCoord[sol[i + 1]][X] / 10 + "' y2='" + nodesCoord[sol[i + 1]][Y] / 10
			+ "' stroke='black' />");
	    }
	    bw.write("<line x1='" + nodesCoord[sol[0]][X] / 10 + "' y1='" + nodesCoord[sol[0]][Y] / 10 + "' x2='"
		    + nodesCoord[sol[sol.length - 1]][X] / 10 + "' y2='" + nodesCoord[sol[sol.length - 1]][Y] / 10
		    + "' stroke='black' />");

	    for (int i = 0; i < nNodes; i++) {
		bw.write("<circle cx='" + nodesCoord[i][X] / 10 + "' cy='" + nodesCoord[i][Y] / 10
			+ "' r='10' fill='red' />");
		bw.write("<text x='" + nodesCoord[i][X] / 10 + "' y='" + nodesCoord[i][Y] / 10
			+ "'  stroke-width='1' text-anchor='middle' alignment-baseline='middle'>" + i + "</text>");
	    }

	    bw.write("</svg>");
	    bw.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }
}
