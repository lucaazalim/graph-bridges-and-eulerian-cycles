import br.pucminas.Graph;
import br.pucminas.bridge.BridgeIdentifier;
import br.pucminas.bridge.NaiveBridgeIdentifier;
import br.pucminas.bridge.TarjanBridgeIdentifier;
import br.pucminas.fleury.FleuryEulerTourFinder;
import br.pucminas.fleury.GraphEulerClassification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FleuryEulerTourFinderTest {

    private static final Map<String, BridgeIdentifier> bridgeIdentifiers = new HashMap<>();

    @BeforeAll
    static void setUp() {
        bridgeIdentifiers.put("Naive", new NaiveBridgeIdentifier());
        bridgeIdentifiers.put("Tarjan", new TarjanBridgeIdentifier());
    }

    @Test
    public void testGetClassification_LinearGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        bridgeIdentifiers.forEach((name, bridgeIdentifier) -> {
            FleuryEulerTourFinder finder = new FleuryEulerTourFinder(graph, bridgeIdentifier);
            assertEquals(GraphEulerClassification.SEMI_EULERIAN, finder.getClassification(), name + " should have classified the graph as semi-eulerian.");
        });

    }

    @Test
    public void testGetClassification_CircularGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);

        bridgeIdentifiers.forEach((name, bridgeIdentifier) -> {
            FleuryEulerTourFinder finder = new FleuryEulerTourFinder(graph, bridgeIdentifier);
            assertEquals(GraphEulerClassification.EULERIAN, finder.getClassification(), name + " should have classified the graph as eulerian.");
        });

    }

    @Test
    public void testGetClassification_TGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        bridgeIdentifiers.forEach((name, bridgeIdentifier) -> {
            FleuryEulerTourFinder finder = new FleuryEulerTourFinder(graph, bridgeIdentifier);
            assertEquals(GraphEulerClassification.NON_EULERIAN, finder.getClassification(), name + " should have classified the graph as non-eulerian.");
        });

    }

}
