package killiandebacker.com.tests;

import org.junit.Test;
import org.junit.Assert;

import killiandebacker.com.*;

public class GraphGenerationTests {


	@Test
    public void test(){
		Graph g = new Graph(10);
		int[][] cords = g.generateRandomEuclideanCostMatrix(10);
	
		int[] cord1 = cords[2];
		int[] cord2 = cords[4];
	
		int[][] matrix = g.matrix;
	
		int dist = g.distance(cord1[0], cord1[1], cord2[0], cord2[1]);
        Assert.assertEquals(dist,matrix[2][4]);
    }
	
}



// Graph g = new Graph(12);
// g.generateRandomSquareGraphCostMatrix(4);
