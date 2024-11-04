package br.pucminas.bridge;

import br.pucminas.Edge;
import br.pucminas.Graph;

public abstract class BridgeIdentifier {

    public abstract boolean isBridge(Graph graph, Edge edge);

}
