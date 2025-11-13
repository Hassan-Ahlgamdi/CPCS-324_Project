import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GraphGenerator {

    /**
     * Generates a connected, undirected random weighted graph.
     *
     * @param n Number of vertices
     * @param m Number of edges
     * @return A new Graph object
     */
    public static Graph make_graph(int n, int m) {
        Graph graph = new Graph();
        Random rand = new Random();
        int MAX_WEIGHT = 10000; // Arbitrary max weight for random edges

        // 0. Check if a connected graph is possible
        if (m < n - 1) {
            System.err.println("Error: Cannot create a connected graph. Edges (m) must be at least Vertices (n) - 1.");
            return graph;
        }

        // To track existing edges to avoid duplicates
        // We store "J1-J2" to check both directions
        Set<String> existingEdges = new HashSet<>();
        int edgesAdded = 0;

        // --- Step 1: Create a simple path to guarantee connectivity ---
        // Connects J0-J1, J1-J2, ..., J(n-2)-J(n-1)
        for (int i = 0; i < n - 1; i++) {
            String uLabel = "J" + i;
            String vLabel = "J" + (i + 1);
            int weight = rand.nextInt(MAX_WEIGHT) + 1; // Random weight 1-100

            graph.addEdge(uLabel, vLabel, weight);
            // Store a sorted key to easily check for "J1-J2" or "J2-J1"
            existingEdges.add(i + "-" + (i + 1));
            edgesAdded++;
        }

        // --- Step 2: Add remaining m - (n-1) random edges ---
        while (edgesAdded < m) {
            int uIndex = rand.nextInt(n);
            int vIndex = rand.nextInt(n);

            // Ensure uIndex < vIndex for consistent key storage
            if (uIndex > vIndex) {
                int temp = uIndex;
                uIndex = vIndex;
                vIndex = temp;
            }

            String uLabel = "J" + uIndex;
            String vLabel = "J" + vIndex;
            String edgeKey = uIndex + "-" + vIndex;

            // Check for self-loop (u == v) or duplicate edge
            if (uIndex == vIndex || existingEdges.contains(edgeKey)) {
                continue; // Try again
            }

            int weight = rand.nextInt(MAX_WEIGHT) + 1;
            graph.addEdge(uLabel, vLabel, weight);
            existingEdges.add(edgeKey);
            edgesAdded++;
        }

        return graph;
    }
}
