package br.pucminas.bridge;

import br.pucminas.Edge;
import br.pucminas.Graph;

import java.util.*;

public class TarjanBridgeFinder extends BridgeFinder {

    private int time = 0;

    public List<Edge> findBridges(Graph graph) {

        this.graph = graph;

        int v = graph.getNumberOfNodes();

        int[] disc = new int[v];
        int[] low = new int[v];
        int[] parent = new int[v];

        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);

        List<Edge> bridges = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            if (disc[i] == -1) {
                depthFirstSearch(i, disc, low, parent, bridges);
            }
        }

        time = 0;

        return bridges;

    }

    private void depthFirstSearch(int start, int[] disc, int[] low, int[] parent, List<Edge> bridges) {

        Stack<Integer> stack = new Stack<>();
        Stack<Iterator<Integer>> adjStack = new Stack<>();

        stack.push(start);
        adjStack.push(graph.getNeighbors(start).iterator());

        disc[start] = low[start] = ++time;

        while (!stack.isEmpty()) {

            int u = stack.peek();
            Iterator<Integer> adj = adjStack.peek();

            if (adj.hasNext()) {

                int v = adj.next();

                if (disc[v] == -1) {
                    parent[v] = u;
                    disc[v] = low[v] = ++time;
                    stack.push(v);
                    adjStack.push(graph.getNeighbors(v).iterator());

                } else if (v != parent[u]) {
                    low[u] = Math.min(low[u], disc[v]);
                }

            } else {

                stack.pop();
                adjStack.pop();

                if (!stack.isEmpty()) {

                    int parentU = stack.peek();
                    low[parentU] = Math.min(low[parentU], low[u]);

                    if (low[u] > disc[parentU]) {
                        bridges.add(new Edge(parentU, u));
                    }

                }

            }

        }

    }

}
