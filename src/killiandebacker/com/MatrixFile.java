package killiandebacker.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatrixFile {

    public static double[][] read(String fileName) throws FileNotFoundException {
        // Used to get the size of the array
        Scanner scanner = new Scanner(new File(fileName));
        String firstLine = scanner.nextLine();
        String[] data = firstLine.split(" "); // *First number on first line of the file must not have a "space"*
        int N = data.length;
        scanner.close();

        // Copy the graph to the graph array
        scanner = new Scanner(new File(fileName));
        double[][] matrix = new double[N][N];
        for(int r=0; r < N; r++)
        {
            for(int c=0; c < N; c++)
                matrix[r][c] = scanner.nextDouble();
        }

        return matrix;
    }
}
