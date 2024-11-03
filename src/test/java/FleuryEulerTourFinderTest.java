import br.pucminas.fleury.FleuryEulerTourFinder;
import br.pucminas.Graph;
import br.pucminas.fleury.GraphEulerClassification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FleuryEulerTourFinderTest {

    @Test
    public void testGetClassification_LinearGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        FleuryEulerTourFinder finder = new FleuryEulerTourFinder(graph);

        assertEquals(GraphEulerClassification.SEMI_EULERIAN, finder.getClassification());

    }

    @Test
    public void testGetClassification_CircularGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);

        FleuryEulerTourFinder finder = new FleuryEulerTourFinder(graph);

        assertEquals(GraphEulerClassification.EULERIAN, finder.getClassification());

    }

    @Test
    public void testGetClassification_TGraph() {

        Graph graph = new Graph();

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        FleuryEulerTourFinder finder = new FleuryEulerTourFinder(graph);

        assertEquals(GraphEulerClassification.NON_EULERIAN, finder.getClassification());

    }

}
