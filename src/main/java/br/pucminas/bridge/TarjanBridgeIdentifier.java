package br.pucminas.bridge;

import br.pucminas.Edge;
import br.pucminas.Graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class TarjanBridgeIdentifier extends BridgeIdentifier {

    private int time = 0;

    public boolean isBridge(Graph graph, Edge edge) {

        time = 0;

        int v = graph.getNumberOfNodes();
        int[] disc = new int[v];
        int[] low = new int[v];
        int[] parent = new int[v];

        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);

        return depthFirstSearchForBridge(graph, edge.node1(), edge.node2(), disc, low, parent);

    }

    private boolean depthFirstSearchForBridge(Graph graph, int start, int targetNode, int[] disc, int[] low, int[] parent) {

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

                if (disc[v] == -1) { // If `v` is not visited
                    parent[v] = u;
                    disc[v] = low[v] = ++time;
                    stack.push(v);
                    adjStack.push(graph.getNeighbors(v).iterator());
                } else if (v != parent[u]) { // Update the low-link value for back edges
                    low[u] = Math.min(low[u], disc[v]);
                }
            } else {
                stack.pop();
                adjStack.pop();

                if (!stack.isEmpty()) {
                    int parentU = stack.peek();
                    low[parentU] = Math.min(low[parentU], low[u]);

                    // Check if the specific edge (u, parentU) is a bridge
                    if (low[u] > disc[parentU] &&
                            ((parentU == start && u == targetNode) || (parentU == targetNode && u == start))) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

}
