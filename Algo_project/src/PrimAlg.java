import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class PrimAlg extends MSTAlgorithm {

    @Override
    public void findMST(Graph graph) {
        MSTResultList.clear();

        if (graph.getVertices().isEmpty()) {
            return; // Handle empty graph
        }

        // Min-Heap to store edges crossing the cut
        // Java's PriorityQueue is a Min-Heap by default
        PriorityQueue<Edge> minHeap = new PriorityQueue<>();

        // Set to keep track of vertices already in the MST
        Set<Vertex> visited = new HashSet<>();

        // 1. Start from an arbitrary vertex (e.g., the first one)
        Vertex startVertex = graph.getVertices().get(0);
        visited.add(startVertex);

        // Add all incident edges of the start vertex to the heap
        for (Edge edge : startVertex.getAdj()) { // Assuming 'adj' is accessible
            minHeap.add(edge);
        }

        // 2. Loop until we have V-1 edges (or heap is empty)
        while (!minHeap.isEmpty() && MSTResultList.size() < graph.getVertices().size() - 1) {

            // 3. Greedily extract the min-weight edge from the heap
            Edge minEdge = minHeap.poll();

            Vertex u = minEdge.getU();
            Vertex v = minEdge.getV();

            // 4. Check if this edge connects to an unvisited vertex
            Vertex newVertex = null;
            if (visited.contains(u) && !visited.contains(v)) {
                newVertex = v;
            } else if (!visited.contains(u) && visited.contains(v)) {
                newVertex = u;
            }

            // If newVertex is null, both ends are already in the MST (a cycle)
            if (newVertex != null) {
                // 5. Add edge to MST and mark vertex as visited
                MSTResultList.add(minEdge);
                visited.add(newVertex);

                // 6. Add all new crossing edges from the newVertex to the heap
                for (Edge incidentEdge : newVertex.getAdj()) {
                    // Check if the other end of the edge is NOT yet visited
                    Vertex otherEnd = (incidentEdge.getU() == newVertex) ? incidentEdge.getV() : incidentEdge.getU();
                    if (!visited.contains(otherEnd)) {
                        minHeap.add(incidentEdge);
                    }
                }
            }
        }
    }
}
