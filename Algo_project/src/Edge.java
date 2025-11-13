public class Edge implements Comparable<Edge>{
    private Vertex u;
    private Vertex v;
    private int weight;

    public Edge(Vertex u, Vertex v, int weight){
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getWeight(){
        return this.weight;
    }

    public Vertex getU(){return this.u;}
    public Vertex getV(){return this.v;}

    @Override
    public int compareTo(Edge other) {
        // This compares THIS weight to OTHER's weight,
        // resulting in a Min-Heap.
        return Integer.compare(this.getWeight(), other.getWeight());
    }

    @Override
    public String toString(){
        return this.u.toString() + " - " + this.v.toString() + " : " + this.weight;
    }

}
