import java.util.ArrayList;
import java.util.List;
public class Vertex {
    private String lable;
    private List<Edge> adj;

    public Vertex(String lable){
        this.lable = lable;
        this.adj = new ArrayList<>();
    }
    public void addEdge(Edge e){
        this.adj.add(e);
    }

    public String toString(){
        return this.lable;
    }

    public String getLable(){return this.lable;}
    public List<Edge> getAdj(){return this.adj;}

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return lable.equals(vertex.getLable());
    }

    @Override
    public int hashCode(){return lable.hashCode();}
}

