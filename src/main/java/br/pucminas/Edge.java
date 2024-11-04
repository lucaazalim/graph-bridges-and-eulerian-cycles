package br.pucminas;

import java.util.Objects;

public record Edge(int node1, int node2) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge edge)) return false;
        return (node1 == edge.node1 && node2 == edge.node2) ||
                (node1 == edge.node2 && node2 == edge.node1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.min(node1, node2), Math.max(node1, node2));
    }

    @Override
    public String toString() {
        return node1 + "-" + node2;
    }
}
