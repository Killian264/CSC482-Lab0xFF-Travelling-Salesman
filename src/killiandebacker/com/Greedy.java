package killiandebacker.com;

import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class Greedy {
    public Stack<Integer> path = new Stack<>();
    // Basically just grabs the shortest path every time 
    public double TSP(double[][] matrix){
        HashMap<Integer, Boolean> visited = new HashMap<>();

        visited.put(0, true);
        path.push(0);
        double cost = 0;

        int current = 0;

        int size = matrix.length;

        for(int i = 0; i < size - 1; i++){

            double[] row = matrix[current];
            for(int k = 0; k < size; k++){
                if(visited.containsKey(k)){
                    row[k] = -1;
                }
                if(k == current){
                    row[k] = -1;
                }
            }

            double min = Integer.MAX_VALUE;
            int next = -1;

            for(int k = 0; k < size; k++){
                double current_cost = row[k];

                if( current_cost == -1){
                    continue;
                }

                if( current_cost < min){
                    min = current_cost;
                    next = k;
                }
            }

            visited.put(current, true);
            current = next;
            path.push(current);
            cost += min;

        }

        path.push(0);

        cost += matrix[current][0];

        // Print path
        // Collections.reverse(path);
        // for(int i = 0; i < n + 1; i++){
        //     System.out.print(path.pop() + " ->");
        // }

        return cost;
    }
}
