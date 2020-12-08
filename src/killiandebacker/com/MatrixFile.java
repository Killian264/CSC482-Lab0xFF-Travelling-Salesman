package killiandebacker.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatrixFile {

	// reads in a matrix file
	public static double[][] read(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		int n = getSize(file);

		Scanner r = new Scanner(file);
		double[][] matrix = new double[n][n];
		for(int i = 0; i < n; i++)
		{
			for(int k = 0; k < n; k++)
				matrix[i][k] = r.nextDouble();
		}
		r.close();
		return matrix;
	}

	// gets the size of the matrix file
	private static int getSize(File file) throws FileNotFoundException{
		Scanner r = new Scanner(file);
		int N = r.nextLine().split(" ").length;
		r.close();
		return N;
	}
}
