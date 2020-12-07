package killiandebacker.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        // Graph g = new Graph(112);

        int radius = 3;

        // g.generateRandomCostMatrix(100);

        // g.generateRandomEuclideanCostMatrix(10);

        // g.generateRandomEuclideanCostMatrix(5);


        // g.generateRandomSquareGraphCostMatrix(4);

        double[][] matrix;
        // g.generateCircleGraphCostMatrix(4);
        // matrix = g.matrix;

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        try{
            matrix = MatrixFile.read("./src/matrix_euclidean.txt");
        }
        catch(Exception e){
            System.out.println("fail");
            return;
        }

        // Graph g = new Graph(matrix);

        // BruteForce test = new BruteForce();
        // double cost = test.TSP(g);

        // System.out.println("COST: " + cost);

        // int n = g.size;

        // ArrayList<Integer> vertices = new ArrayList<>();

        // for(int i = 0; i < n; i++){
        //     vertices.add(i);
        // }
        BruteForce test = new BruteForce();
        Graph g = new Graph(matrix);
        double cost = test.TSP(g);
        System.out.println("COST: " + cost);

        Greedy test2 = new Greedy();
        cost = test2.TSP(matrix);
        System.out.println("COST: " + cost);
        
        g.print();
    }

    private static void padder(String str){
        int maxPadding = 20;
        String padding = "";
        for(int i = str.length(); i < maxPadding; i++){
            padding += " ";
        }
        System.out.print("|" + str + padding + "|");
    }

    private static void numberPadder(long number){
        String appended = "";
        if(number < 8000000000000L && number > 8000000000L){
            appended = "s";
            number /= 1000000000;
        }
        else if(number < 8000000000L && number > 8000000){
            appended = "ms";
            number /= 1000000;
        }
        else if(number < 8000000 && number > 8000){
            appended = "us";
            number /= 1000;
        }
        else if(number < 8000){
            appended = "ns";
        }
        padder(Long.toString(number) + appended);
    }

    public static long getCpuTime(){
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ? bean.getCurrentThreadCpuTime() : 0L;
    }
}