import java.util.*;

class KruskalAlg extends MSTAlgorithm {

    @Override
    public void findMST(Graph graph) {
        MSTResultList.clear();

        // 1. Create a list of all edges and sort them by weight (Greedy step)
        List<Edge> allEdges = new ArrayList<>(graph.getEdges());
        Collections.sort(allEdges);

        // 2. Initialize Disjoint Set for all vertices
        DisjointSet dsu = new DisjointSet();
        for (Vertex v : graph.getVertices()) {
            dsu.makeSet(v);
        }

        // 3. Iterate through sorted edges
        int edgesCount = 0;
        for (Edge edge : allEdges) {
            // If including this edge doesn't cause a cycle
            if (dsu.findSet(edge.getU()) != dsu.findSet(edge.getV())) {
                MSTResultList.add(edge);
                dsu.union(edge.getU(), edge.getV());
                edgesCount++;
            }
            // Optimization: Stop if we have V-1 edges
            if (edgesCount == graph.getVertices().size() - 1) {
                break;
            }
        }
    }

    // --- Helper Inner Class for Union-Find ---
    private class DisjointSet {
        private Map<Vertex, Vertex> parent = new HashMap<>();

        public void makeSet(Vertex v) {
            parent.put(v, v);
        }

        // Find the representative of the set (with path compression for efficiency)
        public Vertex findSet(Vertex v) {
            if (v != parent.get(v)) {
                parent.put(v, findSet(parent.get(v)));
            }
            return parent.get(v);
        }

        // Union two sets
        public void union(Vertex u, Vertex v) {
            Vertex rootU = findSet(u);
            Vertex rootV = findSet(v);
            if (rootU != rootV) {
                parent.put(rootU, rootV);
            }
        }
    }
}