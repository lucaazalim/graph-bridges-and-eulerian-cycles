package br.pucminas.bridge;

import br.pucminas.Edge;
import br.pucminas.Graph;

public class NaiveBridgeIdentifier extends BridgeIdentifier {

    @Override
    public boolean isBridge(Graph graph, Edge edge) {

        if (!graph.isConnected()) {
            throw new IllegalStateException("Graph is not connected");
        }

        graph.removeEdge(edge.node1(), edge.node2());

        boolean isBridge = !graph.isConnected();

        graph.addEdge(edge.node1(), edge.node2());

        return isBridge;

    }

}
