package killiandebacker.com;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class Main {

    public static void main(String[] args) {

        // // // Graph g = new Graph(112);

        // // int radius = 3;

        // // // g.generateRandomCostMatrix(100);

        // // // g.generateRandomEuclideanCostMatrix(10);

        // // // g.generateRandomEuclideanCostMatrix(5);


        // // // g.generateRandomSquareGraphCostMatrix(4);

        // double[][] matrix;
        // // // g.generateCircleGraphCostMatrix(4);
        // // // matrix = g.matrix;

        // System.out.println("Working Directory = " + System.getProperty("user.dir"));

        // try{
        //     matrix = MatrixFile.read("./src/matrix_euclidean.txt");
        // }
        // catch(Exception e){
        //     System.out.println("fail");
        //     return;
        // }

        // // Graph g = new Graph(matrix);

        // DynamicProgramming test = new DynamicProgramming();
        // double cost = test.TSP(matrix);
        // System.out.println("COST: " + cost);

        // // // BruteForce test = new BruteForce();
        // // // double cost = test.TSP(g);

        // // // System.out.println("COST: " + cost);

        // // // int n = g.size;

        // // // ArrayList<Integer> vertices = new ArrayList<>();

        // // // for(int i = 0; i < n; i++){
        // // //     vertices.add(i);
        // // // }
        // // BruteForce test = new BruteForce();
        // // Graph g = new Graph(matrix);
        // // double cost = test.TSP(g);
        // // System.out.println("COST: " + cost);

        // // Greedy test2 = new Greedy();
        // // cost = test2.TSP(matrix);
        // // System.out.println("COST: " + cost);
        
        // // g.print();

        // performanceDP();
        // performanceGreedy();
        performanceBF();
    }

    public static double DPExpected(int n, int prev){
        return  ( Math.pow(n, 2) * Math.pow(2,n) ) / ( Math.pow(prev, 2) * Math.pow(2,prev) );
    }

    public static double BFExpected(int n, int prev){
        return Permute.generateFactorial(n) / Permute.generateFactorial(prev);
    }

    public static void performanceBF(){
        long timeStampBefore;
        long timeStampAfter;
        long time;
        double expected = 4;
        long prevTime = 1;

        /**Print Column Headings**/
        padder("N");
        padder("Time");
        padder("Actual DR");
        padder("Expected DR");
        System.out.println();

        // Ram go bye bye at 13
        for ( int N = 4; N <= 12; N++){

            Graph g = new Graph(N);
            g.generateRandomCostMatrix(100);

            BruteForce BF = new BruteForce();
            
            timeStampBefore = getCpuTime();
            BF.init(N);
            BF.TSP(g);

            timeStampAfter = getCpuTime();
            time = timeStampAfter - timeStampBefore;
            double actual = (time / (double)prevTime);
            prevTime = time;

            // N
            padder(Integer.toString(N));
            // Actual Time
            numberPadder(time);
            // Expected Growth Actual
            padder(Double.toString(actual) + "x");
            // Expected Growth Expected
            expected = BFExpected(N, N-1);
            padder(Double.toString(expected) + "x");

            System.out.println();
        }

    }

    public static void performanceDP(){
        long timeStampBefore;
        long timeStampAfter;
        long time;
        double expected = 4;
        long prevTime = 1;

        /**Print Column Headings**/
        padder("N");
        padder("Time");
        padder("Actual DR");
        padder("Expected DR");
        System.out.println();


        for ( int N = 4; N <= 1000; N++){

            Graph g = new Graph(N);
            g.generateRandomCostMatrix(100);
            double[][] m = g.matrix;

            timeStampBefore = getCpuTime();

            DynamicProgramming DP = new DynamicProgramming();
            DP.TSP(m);

            timeStampAfter = getCpuTime();
            time = timeStampAfter - timeStampBefore;
            double actual = (time / (double)prevTime);
            prevTime = time;

            // N
            padder(Integer.toString(N));
            // Actual Time
            numberPadder(time);
            // Expected Growth Actual
            padder(Double.toString(actual) + "x");
            // Expected Growth Expected
            expected = DPExpected(N, N-1);
            padder(Double.toString(expected) + "x");

            System.out.println();
        }

    }

    public static void performanceGreedy(){
        long timeStampBefore;
        long timeStampAfter;
        long time;
        double expected = 4;
        long prevTime = 1;

        /**Print Column Headings**/
        padder("N");
        padder("Time");
        padder("Actual DR");
        padder("Expected DR");
        System.out.println();


        for ( int N = 1000; N <= 1000000000000L; N*=2){

            Graph g = new Graph(N);
            g.generateRandomCostMatrix(100);
            double[][] m = g.matrix;

            timeStampBefore = getCpuTime();

            Greedy greedy = new Greedy();
            greedy.TSP(m);

            timeStampAfter = getCpuTime();
            time = timeStampAfter - timeStampBefore;
            double actual = (time / (double)prevTime);
            prevTime = time;

            // N
            padder(Integer.toString(N));
            // Actual Time
            numberPadder(time);
            // Expected Growth Actual
            padder(Double.toString(actual) + "x");
            // Expected Growth Expected
            expected = 4;
            padder(Double.toString(expected) + "x");

            System.out.println();
        }

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