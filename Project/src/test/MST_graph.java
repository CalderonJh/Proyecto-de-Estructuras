package test;
import main_algorithms.Graph;

/*
Usar el método de prim para crear un nuevo grafo
 */
public class MST_graph {
    private static final int INF = Integer.MAX_VALUE;

    // Método para calcular el MST con el algoritmo de Prim
    public static Graph primMST(Graph graph) {
        int vertices = graph.getVertices();
        int[][] adj = graph.getAdj();
        boolean[] mstSet = new boolean[vertices];
        int[] parent = new int[vertices];
        int[] key = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            key[i] = INF;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < vertices - 1; count++) {
            int u = minKey(key, mstSet, vertices);
            mstSet[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (adj[u][v] != 0 && !mstSet[v] && adj[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = adj[u][v];
                }
            }
        }

        Graph mst = new Graph(vertices);
        for (int i = 1; i < vertices; i++) {
            mst.addEdge(parent[i], i, adj[i][parent[i]], "");
        }

        return mst;
    }

    private static int minKey(int[] key, boolean[] mstSet, int vertices) {
        int min = INF, minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }
}
