package br.pucminas.fleury;

import br.pucminas.Edge;
import br.pucminas.Graph;
import br.pucminas.bridge.BridgeIdentifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Adapted from <a href="https://www.geeksforgeeks.org/fleurys-algorithm-for-printing-eulerian-path/">Fleury’s Algorithm for printing Eulerian Path or Circuit</a>.
 */
public class FleuryEulerTourFinder {

    private final Graph originalGraph, auxiliaryGraph;
    private final BridgeIdentifier bridgeIdentifier;

    private List<Edge> eulerTour;
    private GraphEulerClassification classification;

    public FleuryEulerTourFinder(Graph graph, BridgeIdentifier bridgeIdentifier) {
        this.originalGraph = graph;
        this.auxiliaryGraph = graph.clone();
        this.bridgeIdentifier = bridgeIdentifier;
    }

    public List<Edge> getEulerTour() {
        return eulerTour;
    }

    public GraphEulerClassification getClassification() {
        return classification;
    }

    public FleuryEulerTourFinder find() {

        if (eulerTour != null) {
            return this;
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

        classify();
        return this;

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

        return !bridgeIdentifier.isBridge(originalGraph, new Edge(u, v));

    }

    private void classify() {

        int oddDegreeCount = 0;

        for (int i = 0; i < originalGraph.getNumberOfNodes(); i++) {
            if (originalGraph.getNeighbors(i).size() % 2 != 0) {
                oddDegreeCount++;
            }
        }

        if (oddDegreeCount == 0) {
            classification = GraphEulerClassification.EULERIAN;
        } else if (oddDegreeCount == 2) {
            classification = GraphEulerClassification.SEMI_EULERIAN;
        } else {
            classification = GraphEulerClassification.NON_EULERIAN;
        }
    }


}
