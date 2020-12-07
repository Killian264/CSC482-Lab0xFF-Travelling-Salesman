package killiandebacker.com;

import java.util.ArrayList;
import java.util.List;

public class BruteForce {

    public ArrayList<Integer> path = new ArrayList<Integer>();

    public double TSP(Graph g){

        double[][] matrix = g.matrix;

        int n = g.size;


        ArrayList<Integer> vertices = new ArrayList<>();

        for(int i = 1; i < n; i++){
            vertices.add(i);
        }

        List<ArrayList<Integer>> perms = new ArrayList<ArrayList<Integer>>();
        Permute.permute(perms, vertices, 0);


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
        for(int vertex : path){
            System.out.print(vertex + " ->");
        }

        return min;
    }
    
}
