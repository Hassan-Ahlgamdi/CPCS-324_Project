import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Locale;

public class Main{
    public static volatile int blackHole;
    public static void main(String[] args) throws FileNotFoundException {
        // --- Requirement 1 Test (from sample file) ---
        Graph g = new Graph();
        g.readGraphFromFile("input.txt");
        System.out.println("------------------------------------");

        KruskalAlg kruskal = new KruskalAlg();
        kruskal.findMST(g);
        Collections.sort(kruskal.MSTResultList); // Sort to match example
        kruskal.printMST("Kruskal");

        System.out.println("------------------------------------");

        PrimAlg prim = new PrimAlg();
        prim.findMST(g);
        // Note: We don't sort Prim's to match the project example
        prim.printMST("Min-Heap Prim");


        // --- Requirement 2 Test (Random Graphs) ---
        System.out.println("\n============================================");
        System.out.println("Requirement 2: Performance Comparison");
        System.out.println("============================================");

        int[] n_cases = {1000, 1000, 1000, 5000, 5000, 10000, 10000};
        int[] m_cases = {10000, 15000, 25000, 15000, 25000, 15000, 25000};

        // --- Create arrays to store results ---
        long[] primTimings = new long[n_cases.length];
        long[] kruskalTimings = new long[n_cases.length];

        KruskalAlg kruskal_random = new KruskalAlg();
        PrimAlg prim_random = new PrimAlg();

        System.out.println("Running benchmarks... (this may take a moment)");

        for (int i = 0; i < n_cases.length; i++) {
            int n = n_cases[i];
            int m = m_cases[i];

            Graph randomGraph = GraphGenerator.make_graph(n, m);

            // Sanity check: Print graph size for the first case
            if (i == 0) {
                System.out.println("Sanity Check: Generated graph with " +
                        randomGraph.getVertices().size() + " vertices and " +
                        randomGraph.getEdges().size() + " edges.");
            }

            // --- 2. Test Min-Heap Prim's ---
            long startTimePrim = System.nanoTime();
            prim_random.findMST(randomGraph);
            blackHole = prim_random.totalWeight(); // <-- Force computation into blackHole
            System.out.println("BlackHole: " + blackHole);
            long endTimePrim = System.nanoTime();
            primTimings[i] = (endTimePrim - startTimePrim) / 1_000_000;

            // --- 3. Test Kruskal's ---
            long startTimeKruskal = System.nanoTime();
            kruskal_random.findMST(randomGraph);
            blackHole = kruskal_random.totalWeight(); // <-- Force computation into blackHole
            System.out.println("BlackHole: " + blackHole);
            long endTimeKruskal = System.nanoTime();
            kruskalTimings[i] = (endTimeKruskal - startTimeKruskal) / 1_000_000;
        }

        System.out.println("...Benchmark complete.\n");

        // --- Print all results AFTER the loop is finished ---
        System.out.printf(Locale.US, "%-12s %-12s %-12s %-12s\n", "n", "m", "Prim (ms)", "Kruskal (ms)");
        System.out.println("-----------------------------------------------------");

        for (int i = 0; i < n_cases.length; i++) {
            System.out.printf(Locale.US, "n=%-10d m=%-10d %-12d %-12d\n",
                    n_cases[i], m_cases[i], primTimings[i], kruskalTimings[i]);
        }
    }
}
