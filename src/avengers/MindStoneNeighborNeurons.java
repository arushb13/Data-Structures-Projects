package avengers;

/**
 * Given a Set of Edges representing Vision's Neural Network, identify all of the 
 * vertices that connect to the Mind Stone. 
 * List the names of these neurons in the output file.
 * 
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * MindStoneNeighborNeuronsInputFile name is passed through the command line as args[0]
 * Read from the MindStoneNeighborNeuronsInputFile with the format:
 *    1. v (int): number of neurons (vertices in the graph)
 *    2. v lines, each with a String referring to a neuron's name (vertex name)
 *    3. e (int): number of synapses (edges in the graph)
 *    4. e lines, each line refers to an edge, each line has 2 (two) Strings: from to
 * 
 * Step 2:
 * MindStoneNeighborNeuronsOutputFile name is passed through the command line as args[1]
 * Identify the vertices that connect to the Mind Stone vertex. 
 * Output these vertices, one per line, to the output file.
 * 
 * Note 1: The Mind Stone vertex has out degree 0 (zero), meaning that there are 
 * no edges leaving the vertex.
 * 
 * Note 2: If a vertex v connects to the Mind Stone vertex m then the graph has
 * an edge v -> m
 * 
 * Note 3: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut:
 *     StdOut.setFile(outputfilename);
 *     //Call StdOut.print() for EVERY neuron (vertex) neighboring the Mind Stone neuron (vertex)
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/MindStoneNeighborNeurons mindstoneneighborneurons.in mindstoneneighborneurons.out
 *
 * @author Yashas Ravi
 * 
 */


public class MindStoneNeighborNeurons {
    
    public static void main (String [] args) {
        
    	if ( args.length < 2 ) {
            StdOut.println("Execute: java MindStoneNeighborNeurons <INput file> <OUTput file>");
            return;
        }
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        int n = StdIn.readInt();
        String[] name = new String[n];
        int[] count = new int[n];
        int i = 0;
        while (i < n) {
            String p = StdIn.readString();
            count[i] = i;
            name[i] = p;
            i++;
        }
        int num = StdIn.readInt();
        int[][] s = new int[n][n];
        i = 0;
        while (i < num) {
            String from = StdIn.readString(), to = StdIn.readString();
            int rr = 0;
            int cc = 0;
            for (int j = 0; j < n; j++) {
                if (name[j].equals(from)) {
                    rr = count[j];
                }
                if (name[j].equals(to)) {
                    cc = count[j];
                }
            }

            s[rr][cc] = 1;
            i++;
        }
        int nill = -1;
        i = 0;
        while (i < n) {
            int f = 0;
            int j = 0;
            while (j < n) {
                if (s[i][j] == 1) {
                    f++;
                }
                j++;
            }
            if (f == 0) {
                nill = i;
                break;
            }
            i++;
        }
        i = 0;
        while (i < n) {
            if (s[i][nill] == 1) {
                StdOut.println(name[i]);
            }
            i++;
        }
    }
}