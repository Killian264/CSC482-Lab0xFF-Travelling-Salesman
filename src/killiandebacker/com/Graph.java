package killiandebacker.com;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Graph {

	public double[][] matrix;
	public int[] vertices;
	int size;
	final int nil = -1;

	public Graph(int in_size) {
		size = in_size;
		matrix = new double[size][size];
		vertices = new int[size];


		initializeVertices();
		clearGraph();
	}

	public Graph(double[][] matrix) {
		size = matrix.length;
		this.matrix = matrix;
		vertices = new int[size];

		initializeVertices();
	}

	public void clearGraph() {
		for (double[] row : matrix) {
			Arrays.fill(row, nil);
		}
	}

	public void addEdge(int start, int end, double cost) {
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
				double cost = random.nextDouble() * random.nextInt(max);
				addEdge(i, j, cost);
			}
		}
	}

	// Points on the matrix are always on a int x and int y
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

				double d = distance(x1, y1, x2, y2);
				addEdge(i, j, d);
			}
		}

		return cords;

	}

	public double generateCircleGraphCostMatrix(int radius, double[][] cords){
		randomizeVertices();

		// Using code from the one note
        double stepAngle = (2 * Math.PI) / size;


        for (int step = 0; step < size; step++) {
            cords[step][0] = radius * Math.sin(step * stepAngle);
			cords[step][1] = radius * Math.cos(step* stepAngle);
		}
		// printCords(cords);
		
		// the minimum value will be the value * n that the equals the TSP cost
		double min = Integer.MAX_VALUE;

		// Generate matrix from cords
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				if (i == j) {
					continue;
				} 
				double[] cord1 = cords[i];
				double[] cord2 = cords[j];

				double x1 = cord1[0];
				double y1 = cord1[1];
				double x2 = cord2[0];
				double y2 = cord2[1];

				double d = distance(x1, y1, x2, y2);
				addEdge(i, j, d);

				if( min > d ){
					min = d;
				}
			}
		}

		return min;
	}

	// FOR TESTING ONLY 
	// THIS FUNCTION SUCKS
	// public int[][] generateRandomSquareGraphCostMatrix(int diameter){
	// 	randomizeVertices();

	// 	int totalLocations = 4*diameter;
		
	// 	if(4 * diameter < size){
	// 		diameter = size;
	// 	}

	// 	int step = (int)Math.ceil(totalLocations / size);

	// 	int[][] cords = new int[size][2];

	// 	int count = 0;
	// 	int count1 = 0;
	// 	System.out.println("Expected");
	// 	for(int i = 0; i < size; i++){
	// 		int vertex = vertices[i];
	// 		System.out.print(vertex + " -> ");

	// 		int[] cord = new int[2];

	// 		int loc = i * step;
			
	// 		if( loc < diameter){
	// 			cord[0] = loc;
	// 			cord[1] = 0;
	// 		}
	// 		else if( (loc + (step * 2)) < diameter * 2){
	// 			cord[0] = diameter - 1;
	// 			cord[1] = (loc % diameter) + 1;
	// 		}
	// 		else if( (loc + (step * 2)) < (diameter * 3)){
	// 			cord[0] = diameter - count - 1;
	// 			cord[1] = diameter - 1;
	// 			count++;
	// 		}
	// 		else{
	// 			cord[0] = 0;
	// 			cord[1] = (diameter - 2) - count1;
	// 			count1++;
	// 		}

	// 		cords[vertex] = cord;
	// 	}
	// 	System.out.println();

	// 	// Generate matrix from cords
	// 	for (int i = 0; i < size; i++) {
	// 		for (int j = i; j < size; j++) {
	// 			if (i == j) {
	// 				continue;
	// 			} 
	// 			int[] cord1 = cords[i];
	// 			int[] cord2 = cords[j];

	// 			int x1 = cord1[0];
	// 			int y1 = cord1[1];
	// 			int x2 = cord2[0];
	// 			int y2 = cord2[1];

	// 			double d = distance(x1, y1, x2, y2);
	// 			addEdge(i, j, d);
	// 		}
	// 	}

	// 	return cords;
	// }

	public void initializeVertices(){
		for (int i = 1; i < size; i++) {
			vertices[i] = i;
		}
	}

	public void randomizeVertices(){
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
	public double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
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
				System.out.format("%7.1f", matrix[i][j]);
			}
			System.out.println();
		}
	}

	public void printCords(double[][] cords) {
		System.out.println("Cords");
		for (int i = 0; i < size; i++) {
			System.out.format("x: %7.1f  | y: %7.1f\n", cords[i][0], cords[i][1]);
		}
	}

	public void printCordsOrdered(double[][] cords) {
		System.out.println("Cords");
		for (int i = 0; i < size; i++) {
			int curr = vertices[i];
			System.out.format("x: %7.1f  | y: %7.1f\n", cords[curr][0], cords[curr][1]);
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
