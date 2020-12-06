package killiandebacker.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Graph {

	public int[][] matrix;
	public static int[] vertices;
	int size;
	final int nil = -1;

	public Graph(int in_size) {
		size = in_size;
		matrix = new int[size][size];
		vertices = new int[size];


		initalizeVertices();
		clearGraph();
	}

	public void clearGraph() {
		for (int[] row : matrix) {
			Arrays.fill(row, nil);
		}
	}

	public void addEdge(int start, int end, int cost) {
		matrix[start][end] = cost;
		matrix[end][start] = cost;
	}

	public void removeEdge(int start, int end) {
		matrix[start][end] = nil;
		matrix[end][start] = nil;
	}

	public void generateRandomCostMatrix(int max) {
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				if (i == j) {
					continue;
				}
				int cost = random.nextInt(max);
				addEdge(i, j, cost);
			}
		}
	}

	public int[][] generateRandomEuclideanCostMatrix(int maxXY) {
		// this should be squared really 
		if(maxXY < size){
			// you stupid
			maxXY = size;
		}
		int[][] cords = new int[size][2];

		Random random = new Random();
		HashMap<Integer, Integer> usedCords = new HashMap<>(); 

		// generate cords on a plane
		for(int i = 0; i < size; i++){
			int[] cord = new int[2];

			do{
				cord[0] = random.nextInt(maxXY);
				cord[1] = random.nextInt(maxXY);
			}while( usedCords.containsKey(cord[0]) && usedCords.get(cord[0]) == cord[1] );

			usedCords.put(cord[0], cord[1]);
			cords[i] = cord;
		}

		// Generate matrix from cords
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				if (i == j) {
					continue;
				} 
				int[] cord1 = cords[i];
				int[] cord2 = cords[j];

				int x1 = cord1[0];
				int y1 = cord1[1];
				int x2 = cord2[0];
				int y2 = cord2[1];

				int d = distance(x1, y1, x2, y2);
				addEdge(i, j, d);
			}
		}

		return cords;

	}

	// FOR TESTING ONLY 
	// THIS FUNCTION SUCKS
	public int[][] generateRandomSquareGraphCostMatrix(int diameter){
		randomizeVertices();

		int totalLocations = 4*diameter;
		
		if(4 * diameter < size){
			diameter = size;
		}

		int step = (int)Math.ceil(totalLocations / size);

		int[][] cords = new int[size][2];

		int count = 0;
		int count1 = 0;
		System.out.println("Expected");
		for(int i = 0; i < size; i++){
			int vertex = vertices[i];
			System.out.print(vertex + " -> ");

			int[] cord = new int[2];

			int loc = i * step;
			
			if( loc < diameter){
				cord[0] = loc;
				cord[1] = 0;
			}
			else if( (loc + (step * 2)) < diameter * 2){
				cord[0] = diameter - 1;
				cord[1] = (loc % diameter) + 1;
			}
			else if( (loc + (step * 2)) < (diameter * 3)){
				cord[0] = diameter - count - 1;
				cord[1] = diameter - 1;
				count++;
			}
			else{
				cord[0] = 0;
				cord[1] = (diameter - 2) - count1;
				count1++;
			}

			cords[vertex] = cord;
		}
		System.out.println();

		// Generate matrix from cords
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				if (i == j) {
					continue;
				} 
				int[] cord1 = cords[i];
				int[] cord2 = cords[j];

				int x1 = cord1[0];
				int y1 = cord1[1];
				int x2 = cord2[0];
				int y2 = cord2[1];

				int d = distance(x1, y1, x2, y2);
				addEdge(i, j, d);
			}
		}

		return cords;
	}

	public void initalizeVertices(){
		for (int i = 1; i < size; i++) {
			vertices[i] = i;
		}
		printVertices();
	}

	public void randomizeVertices(){
		printVertices();
		Random random = new Random();

		// keeping the first vertex always the root should simplify some things
		for (int i = 1; i < size; i++) {
			int swapIndex = random.nextInt(size);
			
			if(swapIndex == 0){
				continue;
			}

			int temp = vertices[swapIndex];
			vertices[swapIndex] = vertices[i];
			vertices[i] = temp;
		}
	}

	// There will be a loss of precision here but this should be fine
	public int distance(int x1, int y1, int x2, int y2) {
		return (int)Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}

	public double getAdjDistance(double[] num) {
		double min = num[0];
		for (int i = 1; i < num.length; i++) {
			if (num[i] < min && num[i] != 0) {
				min = num[i];
			}
		}
		return min;
	}

	public void print() {
		printGraph();
		printEdges();
	}

	public void printGraph() {
		System.out.println("Graph");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.format("%5d", matrix[i][j]);
			}
			System.out.println();
		}
	}

	public void printCords(int[][] cords) {
		System.out.println("Cords");
		for (int i = 0; i < size; i++) {
			System.out.println("x: " + cords[i][0] + "  y: " + cords[i][1]);
		}
	}

	public void printCordsOrdered(int[][] cords) {
		System.out.println("Cords");
		for (int i = 0; i < size; i++) {
			int curr = vertices[i];
			System.out.println("x: " + cords[curr][0] + "  y: " + cords[curr][1]);
		}
	}

	public void printEdges() {
		System.out.println("Vertex : Connections");
		for (int i = 0; i < size; i++) {
			System.out.print(i + " : ");
			for (int j = 0; j < size; j++) {
				if (i == j || matrix[i][j] == nil) {
					continue;
				}
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}

	public void printVertices() {
		System.out.println("Order : Number");
		for(int i = 0; i < size; i++){
			System.out.println(i + " : " + vertices[i]);
		}
	}

}