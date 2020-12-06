package killiandebacker.com.tests;

import org.junit.Test;

import static org.junit.Assert.fail;

import org.junit.Assert;

import killiandebacker.com.*;

public class GraphGenerationTests {


	@Test
    public void EuclideanTest(){
		Graph g = new Graph(10);
		int[][] cords = g.generateRandomEuclideanCostMatrix(10);
	
		int[] cord1 = cords[2];
		int[] cord2 = cords[4];
	
		double[][] matrix = g.matrix;
	
		double dist = g.distance(cord1[0], cord1[1], cord2[0], cord2[1]);
        Assert.assertEquals(dist,matrix[2][4], .001);
	}
	
	@Test
	public void CircleGraphTest(){
		Graph g = new Graph(10);
		double[][] cords = g.generateCircleGraphCostMatrix(10);
	
		double[] cord1 = cords[2];
		double[] cord2 = cords[4];
	
		double[][] matrix = g.matrix;
	
		double dist = g.distance(cord1[0], cord1[1], cord2[0], cord2[1]);
        Assert.assertEquals(dist,matrix[2][4], .001);
	}
	

	@Test
	public void GreedyTest(){
		Greedy test = new Greedy();
		double[][] matrix;

		try{
            matrix = MatrixFile.read("./bin/matrix_euclidean.txt");
        }
        catch(Exception e){
			fail("Error loading testing file");
			return;
		}

		
		double cost = test.TSP(matrix);
		
		Assert.assertEquals(271.5, cost, .01);
	}
}

