package killiandebacker.com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import killiandebacker.com.BruteForce;
import killiandebacker.com.Graph;
import killiandebacker.com.Greedy;
import killiandebacker.com.DynamicProgramming;
import killiandebacker.com.MatrixFile;
import killiandebacker.com.Permute;

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

	@Test
	public void GreedyTest2(){
		Greedy test = new Greedy();
		double[][] matrix;

		try{
            matrix = MatrixFile.read("./bin/matrix.txt");
        }
        catch(Exception e){
			fail("Error loading testing file");
			return;
		}

		double cost = test.TSP(matrix);
		
		Assert.assertEquals(309, cost, .01);
	}

	@Test
	public void GreedyTest3(){
		Greedy test = new Greedy();
		double[][] matrix;

		try{
            matrix = MatrixFile.read("./bin/matrix_5.txt");
        }
        catch(Exception e){
			fail("Error loading testing file");
			return;
		}

		double cost = test.TSP(matrix);
		
		Assert.assertEquals(25, cost, .01);
	}

	@Test
	public void BruteForceTest(){
		BruteForce test = new BruteForce();
		double[][] matrix;

		try{
            matrix = MatrixFile.read("./bin/matrix_euclidean.txt");
        }
        catch(Exception e){
			fail("Error loading testing file");
			return;
		}

		Graph g = new Graph(matrix);
		test.init(matrix.length);
		double cost = test.TSP(g);
		
		Assert.assertEquals(261.5, cost, .09);
	}

	@Test
	public void BruteForceTest2(){
		BruteForce test = new BruteForce();
		double[][] matrix;

		try{
            matrix = MatrixFile.read("./bin/matrix.txt");
        }
        catch(Exception e){
			fail("Error loading testing file");
			return;
		}

		Graph g = new Graph(matrix);
		test.init(matrix.length);
		double cost = test.TSP(g);
		
		Assert.assertEquals(237, cost, 0);
	}

	@Test
	public void BruteForceTest3(){
		BruteForce test = new BruteForce();
		double[][] matrix;

		try{
            matrix = MatrixFile.read("./bin/matrix_5.txt");
        }
        catch(Exception e){
			fail("Error loading testing file");
			return;
		}

		Graph g = new Graph(matrix);
		test.init(matrix.length);
		double cost = test.TSP(g);
		
		Assert.assertEquals(14, cost, 0);
	}

	@Test
	public void DynamicProgrammingTest(){
		DynamicProgramming test = new DynamicProgramming();
		double[][] matrix;

		try{
            matrix = MatrixFile.read("./bin/matrix_euclidean.txt");
        }
        catch(Exception e){
			fail("Error loading testing file");
			return;
		}

		double cost = test.TSP(matrix);
		
		Assert.assertEquals(261.5, cost, .09);
	}

	@Test
	public void DynamicProgrammingTest2(){
		DynamicProgramming test = new DynamicProgramming();
		double[][] matrix;

		try{
            matrix = MatrixFile.read("./bin/matrix.txt");
        }
        catch(Exception e){
			fail("Error loading testing file");
			return;
		}

		double cost = test.TSP(matrix);
		
		Assert.assertEquals(237, cost, 0);
	}

	@Test
	public void DynamicProgrammingTest3(){
		DynamicProgramming test = new DynamicProgramming();
		double[][] matrix;

		try{
            matrix = MatrixFile.read("./bin/matrix_5.txt");
        }
        catch(Exception e){
			fail("Error loading testing file");
			return;
		}

		double cost = test.TSP(matrix);
		
		Assert.assertEquals(14, cost, 0);
	}

	@Test
	public void PermuteTest(){
		int n = 5;
		ArrayList<Integer> vertices = new ArrayList<>();

        for(int i = 0; i < n; i++){
            vertices.add(i);
        }

        List<ArrayList<Integer>> perms = new ArrayList<ArrayList<Integer>>();
		Permute.permute(perms, vertices, 0);
		
		assertEquals(Permute.generateFactorial(n), perms.size());
	}

	@Test
	public void PermuteTest2(){
		int n = 5;
        int[] vertices = new int[n];

        for(int i = 0; i < n; i++){
            vertices[i] = i;
		}

        List<ArrayList<Integer>> perms = new ArrayList<ArrayList<Integer>>();
		perms = Permute.permute2(vertices);
		
		assertEquals(Permute.generateFactorial(n), perms.size());
	}

	@Test
	public void FactorialTest(){
		Assert.assertEquals(120, Permute.generateFactorial(5));
	}

	@Test
	public void PermuteIntegersTest(){
		ArrayList<Integer> perms = Permute.combinations(2, 3);

        assertTrue(
			perms.contains(3) &&
			perms.contains(5) &&
			perms.contains(6)
		);
	}
}

