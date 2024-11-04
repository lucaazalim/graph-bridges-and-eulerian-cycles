import br.pucminas.Edge;
import br.pucminas.Graph;
import br.pucminas.bridge.BridgeIdentifier;
import br.pucminas.bridge.NaiveBridgeIdentifier;
import br.pucminas.bridge.TarjanBridgeIdentifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

public class BridgeIdentifierTest {

    private static final Map<String, BridgeIdentifier> bridgeIdentifiers = new HashMap<>();

    @BeforeAll
    static void setUp() {
        bridgeIdentifiers.put("Naive", new NaiveBridgeIdentifier());
        bridgeIdentifiers.put("Tarjan", new TarjanBridgeIdentifier());
    }

    @Test
    void testIsBridge_LinearGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        bridgeIdentifiers.forEach((name, bridgeIdentifier) -> {

            graph.getEdges().forEach(edge ->
                    assertTrue(bridgeIdentifier.isBridge(graph, edge), name + " should have identified the edge " + edge + " as a bridge.")
            );

            assertFalse(bridgeIdentifier.isBridge(graph, new Edge(0, 2)), name + " should not have identified (0, 2) as a bridge because this edge does not exist.");

        });


    }

    @Test
    void testIsBridge_CircularGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);

        bridgeIdentifiers.forEach((name, bridgeIdentifier) -> {

            graph.getEdges().forEach(edge ->
                    assertFalse(bridgeIdentifier.isBridge(graph, edge), name + " should NOT have identified the edge " + edge + " as a bridge.")
            );

            assertFalse(bridgeIdentifier.isBridge(graph, new Edge(0, 2)), name + " should not have identified (0, 2) as a bridge because this edge does not exist.");

        });

    }

    @Test
    void testIsBridge_ButterflyGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 2);
        graph.addEdge(3, 2);
        graph.addEdge(4, 2);
        graph.addEdge(3, 4);

        bridgeIdentifiers.forEach((name, bridgeIdentifier) -> graph.getEdges().forEach(edge ->
                assertFalse(bridgeIdentifier.isBridge(graph, edge), name + " should NOT have identified the edge " + edge + " as a bridge.")
        ));

    }

    @Test
    void testIsBridge_TieGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 3);
        graph.addEdge(0, 3); // bridge

        bridgeIdentifiers.forEach((name, bridgeIdentifier) -> graph.getEdges().forEach(edge -> {
            assertEquals(edge.equals(new Edge(0, 3)), bridgeIdentifier.isBridge(graph, edge), name + " should have identified " + edge + " as a bridge.");
        }));

    }

}
