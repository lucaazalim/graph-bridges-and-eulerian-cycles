package br.pucminas.bridge;

import br.pucminas.Edge;
import br.pucminas.Graph;

import java.util.List;

public abstract class BridgeFinder {

    protected Graph graph;

    public abstract List<Edge> findBridges(Graph graph);

}
