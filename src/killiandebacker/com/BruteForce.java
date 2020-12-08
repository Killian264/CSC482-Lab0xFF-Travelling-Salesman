package killiandebacker.com;

import java.util.ArrayList;
import java.util.List;

public class BruteForce {

    public ArrayList<Integer> path = new ArrayList<Integer>();
    List<ArrayList<Integer>> perms = new ArrayList<ArrayList<Integer>>();

    public void init(int n){
        int[] vertices = new int[n];

        for(int i = 0; i < n; i++){
            vertices[i] = i;
		}

		perms = Permute.permute2(vertices);
    }

    public double TSP(Graph g){

        double[][] matrix = g.matrix;

        double min = Integer.MAX_VALUE;

        
        for(ArrayList<Integer> perm : perms){
            perm.add(0, 0);
            perm.add(perm.size(), 0);
            double cost = 0;
            int curr = 0;
            int prev = 0;
            
            for(int i = 1; i < perm.size(); i++){
                curr = perm.get(i);
                prev = perm.get(i - 1);
                cost += matrix[prev][curr];
            }

            if( cost < min){
                path = perm;
                min = cost;
            }
        }

        // Print path
        // for(int vertex : path){
        //     System.out.print(vertex + " ->");
        // }

        return min;
    }
    
}
