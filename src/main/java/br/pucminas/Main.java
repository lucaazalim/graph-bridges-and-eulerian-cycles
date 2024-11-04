package br.pucminas;

import br.pucminas.bridge.NaiveBridgeIdentifier;
import br.pucminas.bridge.TarjanBridgeIdentifier;
import br.pucminas.fleury.FleuryEulerTourFinder;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter the graph file name: ");
            String fileName = scanner.nextLine();

            System.out.println("Which method do you want to use?");
            System.out.println("1. Naive");
            System.out.println("2. Tarjan");

            System.out.println("Enter the method number: ");
            int method = Integer.parseInt(scanner.nextLine());

            System.out.println("How many times do you want to run the method?");
            int times = Integer.parseInt(scanner.nextLine());

            System.out.println("Loading graph...");

            Graph graph = Graph.fromResourceFile(fileName);

            System.out.println("Graph loaded with " + graph.getNumberOfNodes() + " nodes and " + graph.getNumberOfEdges() + " edges.");

            long elapsedTime = 0;

            for (int i = 0; i < times; i++) {

                long startTime = System.currentTimeMillis();

                var finder = new FleuryEulerTourFinder(
                        graph,
                        method == 1 ? new NaiveBridgeIdentifier() : new TarjanBridgeIdentifier()
                ).find();

                System.out.println("Graph classified as " + finder.getClassification() + ".");

                elapsedTime += System.currentTimeMillis() - startTime;

            }

            System.out.println("Total elapsed time: " + elapsedTime + " milliseconds.");
            System.out.println("Average elapsed time: " + elapsedTime / times + " milliseconds.");

        }

    }

}
