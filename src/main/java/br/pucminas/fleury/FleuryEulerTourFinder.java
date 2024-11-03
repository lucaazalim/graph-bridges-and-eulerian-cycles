package br.pucminas.fleury;

import br.pucminas.Edge;
import br.pucminas.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FleuryEulerTourFinder {

    private final Graph originalGraph, auxiliaryGraph;
    private List<Edge> eulerTour;
    private GraphEulerClassification classification;

    public FleuryEulerTourFinder(Graph graph) {
        this.originalGraph = graph;
        this.auxiliaryGraph = graph.clone();
        find();
        classify();
    }

    public List<Edge> getEulerTour() {
        return eulerTour;
    }

    public GraphEulerClassification getClassification() {
        return classification;
    }

    public void classify() {

        if (eulerTour.size() == originalGraph.getNumberOfEdges()) {

            var firstEdge = eulerTour.getFirst();
            var lastEdge = eulerTour.getLast();

            if (firstEdge.node1() == lastEdge.node2()) {
                classification = GraphEulerClassification.EULERIAN;
            } else {
                classification = GraphEulerClassification.SEMI_EULERIAN;
            }

        } else {
            classification = GraphEulerClassification.NON_EULERIAN;
        }

    }

    public void find() {

        if (eulerTour != null) {
            return;
        }

        int u = 0;
        for (int i = 0; i < auxiliaryGraph.getNumberOfNodes(); i++) {
            if (auxiliaryGraph.getNeighbors(i).size() % 2 == 1) {
                u = i;
                break;
            }
        }

        eulerTour = new ArrayList<>();
        getEulerTour(u);

    }

    private void getEulerTour(Integer u) {

        Stack<Integer> stack = new Stack<>();
        stack.push(u);

        while (!stack.isEmpty()) {

            int currentNode = stack.peek();
            List<Integer> neighbors = auxiliaryGraph.getNeighbors(currentNode);
            boolean foundValidEdge = false;

            for (int i = 0; i < neighbors.size(); i++) {

                Integer v = auxiliaryGraph.getNeighbors(currentNode).get(i);

                if (isValidNextEdge(currentNode, v)) {
                    eulerTour.add(new Edge(currentNode, v));
                    auxiliaryGraph.removeEdge(currentNode, v);
                    stack.push(v);
                    foundValidEdge = true;
                    break;
                }

            }

            if (!foundValidEdge) {
                stack.pop();
            }

        }

    }

    private boolean isValidNextEdge(Integer u, Integer v) {

        if (auxiliaryGraph.getNeighbors(u).size() == 1) {
            return true;
        }

        List<Integer> visited = new ArrayList<>();
        int count1 = dfsCount(u, visited);

        auxiliaryGraph.removeEdge(u, v);
        visited = new ArrayList<>();
        int count2 = dfsCount(u, visited);

        auxiliaryGraph.addEdge(u, v);
        return count1 <= count2;

    }

    private int dfsCount(Integer startNode, List<Integer> visited) {

        int count = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);

        while (!stack.isEmpty()) {

            Integer node = stack.pop();

            if (visited.contains(node)) {
                continue;
            }

            visited.add(node);
            count++;

            for (int adj : auxiliaryGraph.getNeighbors(node)) {
                if (!visited.contains(adj)) {
                    stack.push(adj);
                }
            }

        }

        return count;

    }
}
