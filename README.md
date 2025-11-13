# CPCS 324 Project: Minimum Spanning Tree Algorithms

This project is an implementation of two classic greedy algorithms for finding the Minimum Spanning Tree (MST) of an undirected, weighted graph. The project models the real-world problem of designing a cost-efficient water distribution network.

This was submitted for the **CPCS 324: Algorithms and Data Structures (II)** course project (Fall 2025).

## Features

* **Graph Data Structure:** A graph implementation based on the provided UML diagram, using adjacency lists.
* **Kruskal's Algorithm:** Finds the MST using a Disjoint Set Union (DSU) data structure.
* **Min-Heap Prim's Algorithm:** Finds the MST using Java's `PriorityQueue` as a min-heap.
* **Random Graph Generator:** A utility (`GraphGenerator.java`) to create large, random, connected graphs for testing.
* **Performance Benchmark:** A `main` class that runs and times both algorithms on a variety of graph sizes, as specified in Requirement 2.

## Project Structure

The project is structured in an object-oriented manner in Java:

* **`Graph.java`**: The main class for holding the graph's vertices (`List<Vertex>`) and edges (`List<Edge>`). Includes the `readGraphFromFile` method.
* **`Vertex.java` / `Junction.java`**: Represents a node in the graph (a water junction).
* **`Edge.java` / `Pipe.java`**: Represents a weighted edge (a pipe) connecting two vertices.
* **`MSTAlgorithm.java`**: An abstract base class defining the structure for an MST algorithm.
* **`KruskalAlg.java`**: Implements Kruskal's algorithm, using an inner `DisjointSet` class.
* **`PrimAlg.java`**: Implements the "lazy" Min-Heap Prim's algorithm.
* **`GraphGenerator.java`**: A static utility class to generate random graphs.
* **`Main.java`**: The main driver class that runs both Requirement 1 (sample file) and Requirement 2 (performance benchmark).

## How to Run

### Prerequisites

* Java Development Kit (JDK) 8 or newer.

### Running the Sample Data (Requirement 1)

1.  Create a file named `input.txt` in the root directory of the project.
2.  Copy the following sample data into `input.txt`:

    ```text
    6 7
    J1 J2 9
    J2 J5 7
    J1 J3 12
    J2 J3 4
    J3 J4 6
    J4 J6 3
    J5 J6 8
    ```

3.  Compile all `.java` files:

    ```bash
    javac *.java
    ```

4.  Run the `Main` class:

    ```bash
    java Main
    ```

