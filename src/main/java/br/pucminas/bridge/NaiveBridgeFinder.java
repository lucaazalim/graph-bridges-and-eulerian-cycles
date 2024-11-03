package br.pucminas.bridge;

import br.pucminas.Edge;
import br.pucminas.Graph;

import java.util.ArrayList;
import java.util.List;

public class NaiveBridgeFinder extends BridgeFinder {

    @Override
    public List<Edge> findBridges(Graph graph) {

        if (!graph.isConnected()) {
            throw new IllegalStateException("Graph is not connected");
        }

        List<Edge> bridges = new ArrayList<>();

        for (Edge edge : graph.getEdges()) {
            graph.removeEdge(edge.node1(), edge.node2());

            if (!graph.isConnected()) {
                bridges.add(edge);
            }

            graph.addEdge(edge.node1(), edge.node2());
        }

        return bridges;

    }

}
