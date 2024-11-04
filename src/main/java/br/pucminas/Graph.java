package br.pucminas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Graph implements Cloneable {

    private final Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

    private int numberOfEdges;

    public static Graph fromResourceFile(String fileName) throws IOException {

        Graph graph = new Graph();

        try (InputStream inputStream = Graph.class.getClassLoader().getResourceAsStream(fileName)) {

            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + fileName);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    String[] edge = line.split(" ");
                    graph.addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
                }

            }

        }

        return graph;

    }

    public int getNumberOfNodes() {
        return adjacencyList.size();
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public void addEdge(int v1, int v2) {
        adjacencyList.computeIfAbsent(v1, _ -> new ArrayList<>()).add(v2);
        adjacencyList.computeIfAbsent(v2, _ -> new ArrayList<>()).add(v1);
        numberOfEdges++;
    }

    public void removeEdge(int v1, int v2) {
        adjacencyList.get(v1).remove((Integer) v2);
        adjacencyList.get(v2).remove((Integer) v1);
        numberOfEdges--;
    }

    public Set<Edge> getEdges() {

        Set<Edge> edges = new HashSet<>();

        adjacencyList.forEach((key, value) ->
                value.forEach(node -> edges.add(new Edge(key, node)))
        );

        return edges;

    }

    public List<Integer> getNeighbors(int node) {
        return adjacencyList.getOrDefault(node, new ArrayList<>());
    }

    public boolean isConnected() {
        if (adjacencyList.isEmpty()) {
            return true;
        }

        Set<Integer> visited = new HashSet<>();
        Integer startNode = adjacencyList.keySet().iterator().next();

        depthFirstSearchIterative(startNode, visited);

        return visited.size() == adjacencyList.size();
    }

    private void depthFirstSearchIterative(int startNode, Set<Integer> visited) {

        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);

        while (!stack.isEmpty()) {

            int node = stack.pop();

            if (visited.contains(node)) {
                continue;
            }

            visited.add(node);

            for (int neighbor : getNeighbors(node)) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }

        }

    }

    @Override
    public Graph clone() {

        Graph clonedGraph = new Graph();
        clonedGraph.numberOfEdges = this.numberOfEdges;

        this.adjacencyList.forEach((key, value) ->
                clonedGraph.adjacencyList.put(key, new ArrayList<>(value))
        );

        return clonedGraph;
    }

}
