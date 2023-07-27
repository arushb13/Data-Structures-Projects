package avengers;
/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {
	
    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }
    
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        int n = StdIn.readInt();
        int[] gen = new int[n];
        int[][] edge = new int[n][n];
        double[] func = new double[n];
        int i = 0;
        while (i < n) {
            gen[i] = StdIn.readInt();
            func[i] = StdIn.readDouble();
            i++;
        }
        int row = 0;
        while (row < n) {
            int col = 0;
            while (col < n) {
                edge[row][col] = (int)(StdIn.readInt()/(func[row]*func[col]));
                col++;
            }
            row++;
        }
        int[] min = dijkstra(edge);
        StdOut.print(min[n - 1]);
    }
    private static int[] dijkstra(int[][] edge) {
        int numN = edge.length;
        boolean[] set = new boolean[numN];
        int[] min = new int[numN];
        int i = 1;
        while (i < numN) {
            min[i] = Integer.MAX_VALUE;
            i++;
        }
        int j = 0;
        while (j < numN) {
            int c = getMin(min, set);
            set[c] = true;
            int k = 0;
            while (k < numN) {
                int sum = min[c] + edge[c][k];
                if (edge[c][k] != 0 && !set[k] && sum < min[k]) {
                    min[k] = sum;
                }
                k++;
            }
            j++;
        }

        return min;
    }
    private static int getMin(int[] min, boolean[] v) {
        int mini = Integer.MAX_VALUE;
        int mNode = -1;
        int i = 0;
        while (i < min.length) {
            if (!v[i] && min[i] < mini) {
                mini = min[i];
                mNode = i;
            }
            i++;
        }
        return mNode;
    }
}