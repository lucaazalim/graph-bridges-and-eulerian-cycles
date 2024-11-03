import br.pucminas.*;
import br.pucminas.bridge.BridgeFinder;
import br.pucminas.bridge.NaiveBridgeFinder;
import br.pucminas.bridge.TarjanBridgeFinder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

public class BridgeFinderTest {

    private static final Map<String, BridgeFinder> bridgeFinders = new HashMap<>();

    @BeforeAll
    static void setUp() {
        bridgeFinders.put("Naive", new NaiveBridgeFinder());
        bridgeFinders.put("Tarjan", new TarjanBridgeFinder());
    }

    @Test
    void testFindBridges_LinearGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        testEachBridgeFinder(graph, (name, bridges) ->
                assertEquals(3, bridges.size(), name + " method should have identified all edges as bridges.")
        );

        testEachBridgeFinder(graph, (name, bridges) ->
                graph.getEdges().forEach(edge ->
                        assertTrue(bridges.contains(edge), name + " method should have found the bridge " + edge + ".")
                )
        );

    }

    @Test
    void testFindBridges_CircularGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);

        testEachBridgeFinder(graph, (name, bridges) ->
                assertEquals(0, bridges.size(), name + " method should have found no bridges in a circular graph.")
        );

    }

    @Test
    void testFindBridges_ButterflyGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 2);
        graph.addEdge(3, 2);
        graph.addEdge(4, 2);
        graph.addEdge(3, 4);

        testEachBridgeFinder(graph, (name, bridges) ->
                assertEquals(0, bridges.size(), name + " method should have found no bridges in a butterfly graph.")
        );

    }

    @Test
    void testFindBridges_TieGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 3);
        graph.addEdge(0, 3);

        testEachBridgeFinder(graph, (name, bridges) ->
                assertEquals(1, bridges.size(), name + " method should have found one bridge in a tie graph.")
        );

        testEachBridgeFinder(graph, (name, bridges) ->
                assertTrue(bridges.contains(new Edge(0, 3)), name + " method should have found the bridge (0, 3).")
        );

    }

    void testEachBridgeFinder(Graph graph, BiConsumer<String, List<Edge>> consumer) {
        bridgeFinders.forEach((name, bridgeFinder) -> consumer.accept(name, bridgeFinder.findBridges(graph)));
    }

}
