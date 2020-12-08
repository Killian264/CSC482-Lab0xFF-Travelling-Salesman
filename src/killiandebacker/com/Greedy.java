package killiandebacker.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Greedy {
    ArrayList<Integer> path = new ArrayList<Integer>();
    // Basically just grabs the shortest path every time 
    public double TSP(double[][] matrix){
        HashMap<Integer, Boolean> visited = new HashMap<>();

        int n = matrix.length;

        visited.put(0, true);
        path.add(0);
        double cost = 0;

        int current = 0;

        int size = matrix.length;

        for(int i = 0; i < size - 1; i++){

            double[] row = Arrays.copyOf(matrix[current], n);
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
            path.add(current);
            cost += min;

        }

        path.add(0);

        cost += matrix[current][0];

        // Print path
        // for(int vertex : path){
        //     System.out.print(vertex + " ->");
        // }


        return cost;
    }
}
