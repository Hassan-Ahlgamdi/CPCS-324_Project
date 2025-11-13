import java.util.*;
import java.io.*;
public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;

    public Graph(){
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    // Helper method to find a vertex by label so we don't create duplicates
    private Vertex findVertex(String lable){
        for(Vertex v : this.vertices){
            if(v.toString().equals(lable)){
                return v;
            }
        }
        return null;
    }
    public List<Vertex> getVertices(){return this.vertices;}
    public List<Edge> getEdges(){return this.edges;}

    public void addEdge(String uLabel, String vLabel, int weight){
        // Find or create vertices
        Vertex u = findVertex(uLabel);
        Vertex v = findVertex(vLabel);

        if(u == null) {
            u = new Junction(uLabel); // 1. Creates Junction 'A'
            vertices.add(u); // 2. Adds Junction 'A' to the list
        }
        if (v == null){
            v = new Junction(vLabel); // 3. Creates Junction 'C'
            vertices.add(v); // 4. Adds Junction 'C' to the list
        }

        // Create the Edge (Pipe)
        Pipe p = new Pipe(u,v,weight);
        edges.add(p);

        // Undirected graph: add to both adjacency lists
        v.addEdge(p);
        u.addEdge(p);
    }

    public void readGraphFromFile(String filename) throws FileNotFoundException {
        Scanner read = new Scanner(new File(filename));
        if(!read.hasNext()){
            read.close();
            return;
        }
        int numVertices = read.nextInt();
        int numEdges = read.nextInt();
        for(int i = 0; i < numEdges; i++){
            String uLabel = read.next();
            String vLabel = read.next();
            int weight = read.nextInt();
            addEdge(uLabel, vLabel, weight);
        }
        read.close();
        System.out.println("Graph Loaded successfully from " + filename);
        System.out.println("Number of vertices: " + numVertices + " Number of edges: " + numEdges);
    }
}
