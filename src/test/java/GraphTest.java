import br.pucminas.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    public void testGetNumberOfNodesAndEdges() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertEquals(4, graph.getNumberOfNodes());
        assertEquals(3, graph.getNumberOfEdges());

    }

    @Test
    public void testIsConnected() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertTrue(graph.isConnected());

        graph.removeEdge(1, 2);

        assertFalse(graph.isConnected());

    }

}
