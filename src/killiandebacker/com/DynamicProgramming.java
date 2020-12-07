package killiandebacker.com;

import java.util.ArrayList;

// Refactored code
// Original Psuedocode found here https://www.youtube.com/watch?v=cY4HiiFHO1o

public class DynamicProgramming {

	public ArrayList<Integer> shortestPath = new ArrayList<>(); // List containing the ids of the vertices that forms the shortest path for the TSP problem
	public double cost = -1;

	public double TSP(double[][] matrix){
		int n = matrix.length;

		// for clarity only, start value will always be 0
		int start = 0;

		double[][] memo = new double[n][1 << n];

		// Set the inital values, (the shortest costs from this node to its neighbors)
		memoInit(matrix, memo, n, start);
		getShortestPathCost(matrix, memo, n, start);
		findMinCost(matrix, memo, start, n);
		findOptimalTour(matrix, memo, start, n);

		// for(int vertex : shortestPath){
		// 	System.out.print(vertex + " ->");
		// }

		return cost;
	}
	
	
	// Initalize values of start node
	// These are the values of size 2
	void memoInit(double[][] matrix, double[][] memo, int n, int start){
		for (int i = 1; i < n; i++){
			memo[i][1 << start | (1 << i) ] = matrix[start][i];
		}
	}



	void getShortestPathCost(double[][] matrix, double[][] memo, int n, int start){

		// Count is the tour size that is currently being added
		// We have already added 2 from the memoInit
		// So we start with three
		for (int count = 3; count <= n; count++) {
			// All possible tours of count see combinations function for more details
			for (int subset : Permute.combinations(count, n)){
				// The tour must include the starting node or it is invalid
				if (notIn(start, subset)){
					continue;
				}

				// All possible next values
				for (int next = 0; next < n; next++) {
					// not including those that are not in the subset or the start node9
					if (next == start || notIn(next, subset)){
						continue;
					}
					
					// Each previous iteration has generated the cheapest values from the starting location to the current point
					// This iteration will generate the cheapest values to the next node
					// Thus get the previous cost index ie. the subset not including the next node
					int previousCostIndex = subset ^ (1 << next);

					double min = Integer.MAX_VALUE;
					// End being the end node of the current tour
					// not the end of the best matrix route
					for (int end = 0; end < n; end++) {
						// it of course cannot be start the next node and must be in the subset
						if (end == start || end == next || notIn(end, subset)) {
							continue;
						}
						// current is the cost of the current tour
						double current = memo[end][previousCostIndex] + matrix[end][next];
						// minimize the cost to the next node
						if (current < min) {
							min = current;
						}
					}
					// set the newest cheapest cost to the next node
					memo[next][subset] = min;
				}
			}
		}

	}

	// returns the optimal tour cost
	void findMinCost(double[][] matrix, double[][] memo, int start, int n){
		double cost = Integer.MAX_VALUE;
		int end = (1 << matrix.length) - 1;

		for (int i = 0; i < n; i++) {
			if (i == start){
				continue;
			} 
			double current = memo[i][end] + matrix[i][start];
			if (current < cost) {
				cost = current;
			}
		}
		this.cost = cost;
	}

	// Sets the optimal tour
	void findOptimalTour(double[][] matrix, double[][] memo, int start, int n){
		int prevIndex = start;
		int state = (1 << matrix.length) - 1;
		shortestPath.add(start);

		for (int i = 1; i < n; i++) {

			int index = -1;
			for (int j = 0; j < n; j++) {
				if (j == start || notIn(j, state)){
					continue;
				}

				if (index == -1){
					index = j;
				}
				double prevDist = memo[index][state] + matrix[index][prevIndex];
				double newDist  = memo[j][state] + matrix[j][prevIndex];
				if (newDist < prevDist) {
					index = j;
				}
			}

			shortestPath.add(index);
			state = state ^ (1 << index);
			prevIndex = index;
		}

		shortestPath.add(start);
	}

	// Check if the node in the subset
	// visited check basically with some int witchcraft
	private static boolean notIn(int index, int subset) {
		return ((1 << index) & subset) == 0;
	}

}
