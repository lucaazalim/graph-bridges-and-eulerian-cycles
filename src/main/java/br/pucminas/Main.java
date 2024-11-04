package br.pucminas;

import br.pucminas.bridge.BridgeIdentifier;
import br.pucminas.bridge.NaiveBridgeIdentifier;
import br.pucminas.bridge.TarjanBridgeIdentifier;
import br.pucminas.fleury.FleuryEulerTourFinder;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    /*public static void main(String[] args) throws IOException {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter the graph file name: ");
            String fileName = scanner.nextLine();

            System.out.println("Which algorithm do you want to use?");
            System.out.println("1. Naive");
            System.out.println("2. Tarjan");
            System.out.println("3. Fleury");

            System.out.println("Enter the algorithm number: ");
            int algorithm = Integer.parseInt(scanner.nextLine());

            System.out.println("How many times do you want to run the algorithm?");
            int times = Integer.parseInt(scanner.nextLine());

            System.out.println("Loading graph...");

            Graph graph = Graph.fromResourceFile(fileName);

            System.out.println("Graph loaded with " + graph.getNumberOfNodes() + " nodes and " + graph.getNumberOfEdges() + " edges.");

            long elapsedTime = 0;

            for (int i = 0; i < times; i++) {

                long startTime = System.currentTimeMillis();

                if (algorithm == 3) {
                    findEulerTour(graph);
                } else {
                    findBridges(graph, algorithm == 1 ? new NaiveBridgeIdentifier() : new TarjanBridgeIdentifier());
                }

                elapsedTime += System.currentTimeMillis() - startTime;

            }

            System.out.println("Total elapsed time: " + elapsedTime + " milliseconds.");
            System.out.println("Average elapsed time: " + elapsedTime / times + " milliseconds.");

        }

    }

    private static void findBridges(Graph graph, BridgeIdentifier bridgeIdentifier) {

        System.out.println("Finding bridges...");

        List<Edge> bridges = bridgeIdentifier.isBridge(graph, );

        System.out.println("Found " + bridges.size() + " bridges.");

    }

    private static void findEulerTour(Graph graph) {

        System.out.println("Finding Euler tour...");

        FleuryEulerTourFinder eulerTourFinder = new FleuryEulerTourFinder(graph);

        System.out.println("Graph classified as " + eulerTourFinder.getClassification() + ".");

    }*/

}
