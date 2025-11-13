import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract class MSTAlgorithm {
    protected List<Edge> MSTResultList = new ArrayList<>();

    // Abstract method as per UML
    public abstract void findMST(Graph graph);

    // Concrete method as per UML to calculate total cost
    public int totalWeight() {
        int total = 0;
        for (Edge e : MSTResultList) {
            total += e.getWeight();
        }
        return total;
    }

    // Inside MSTAlgorithm.java
    public void printMST(String algoName) {
        // Sort the list by weight to match the project's example output
        Collections.sort(MSTResultList);

        System.out.println("MST (" + algoName + "):");
        for (Edge e : MSTResultList) {
            System.out.println(e.getU().getLable() + " — " + e.getV().getLable() + " : " + e.getWeight());
        }
        System.out.println("Total = " + totalWeight());
    }
}