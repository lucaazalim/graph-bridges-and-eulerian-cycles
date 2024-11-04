import networkx as nx

# Generates a cycle graph, which is always Eulerian
def generate_eulerian_graph(n: int) -> nx.Graph:
    n -= 1
    graph = nx.Graph()
    graph.add_nodes_from(range(n + 1))
    for j in range(n):
        graph.add_edge(j, j + 1)
    graph.add_edge(n, 0)
    return graph

# Generates a linear graph, which is always semi-Eulerian
def generate_semieulerian_graph(n: int) -> nx.Graph:
    graph = nx.Graph()
    graph.add_nodes_from(range(n))
    for j in range(n - 1):
        graph.add_edge(j, j + 1)
    return graph

# Generates a linear graph with an additional edge, making it non-Eulerian
def generate_non_eulerian_graph(n: int) -> nx.Graph:
    graph = generate_semieulerian_graph(n)
    if n > 3:
        graph.add_edge(1, 3)  # Adding an edge to make the graph non-Eulerian
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
