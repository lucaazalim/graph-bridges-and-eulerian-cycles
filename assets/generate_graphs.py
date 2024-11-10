import networkx as nx


# Generates a cycle graph
def generate_eulerian_graph(n: int) -> nx.Graph:
    return nx.cycle_graph(n)


# Generates a linear graph with two additional edges
def generate_semieulerian_graph(n: int) -> nx.Graph:
    graph = nx.Graph()
    graph.add_nodes_from(range(n))
    for j in range(n - 1):
        graph.add_edge(j, j + 1)
    graph.add_edge(0, n - 1)
    graph.add_edge(1, n - 1)
    return graph


# Generates a linear graph with additional edges
def generate_non_eulerian_graph(n: int) -> nx.Graph:
    graph = nx.Graph()
    graph.add_nodes_from(range(n))
    for j in range(n - 1):
        graph.add_edge(j, j + 1)
    for k in range(11):
        graph.add_edge(k, n - k)
    return graph


# Writes the graph to a file
def write_graph(graph: nx.Graph, filename: str):
    nx.write_edgelist(graph, filename, data=False)


N = 10

for i in range(4):
    N *= 10

    # Generates and writes Eulerian graph
    G = generate_eulerian_graph(N)
    assert nx.is_eulerian(G) and not nx.is_semieulerian(G)
    write_graph(G, f'eulerian_{N}_graph.txt')

    # Generates and writes semi-Eulerian graph
    G = generate_semieulerian_graph(N)
    assert not nx.is_eulerian(G) and nx.is_semieulerian(G)
    write_graph(G, f'semi_eulerian_{N}_graph.txt')

    # Generates and writes non-Eulerian graph
    G = generate_non_eulerian_graph(N)
    assert not nx.is_eulerian(G) and not nx.is_semieulerian(G)
    write_graph(G, f'non_eulerian_{N}_graph.txt')
