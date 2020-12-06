package killiandebacker.com;

import killiandebacker.com.*;

public class Main {

    public static void main(String[] args) {

        Graph g = new Graph(12);

        int r = 3;

        g.generateRandomCostMatrix(100);

        g.generateRandomEuclideanCostMatrix(10);

        g.generateRandomEuclideanCostMatrix(5);


        g.generateRandomSquareGraphCostMatrix(4);




    }
}
