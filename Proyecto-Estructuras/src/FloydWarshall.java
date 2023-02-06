public class FloydWarshall {
    // constante para establecer un valor alto en los vértices no conectados
    private static final int INF = Integer.MAX_VALUE;

    /**
     * encuentra la matriz de costos y de recorridos usando el algoritmo de
     * Floyd-Warshall
     * @param graph grafo al cual se le va a aplicar el algoritmo
     */
    public static void floydWarshall(Graph graph) {
        int n = graph.getVertices();
        int[][] dist = new int[n][n];
        int[][] next = new int[n][n];
        int[][] weightM = graph.getAdj().clone();

        // establecer INF en vertices no conectados
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i != j && weightM[i][j] == 0){
                    weightM[i][j] = INF;
                }
            }
        }

        // Inicializar las matrices "dist" y "next" con los valores iniciales del grafo
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = weightM[i][j];
                if (weightM[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        // Aplicar el algoritmo de Floyd-Warshall
        //Se actualizan los valores de las matrices "dist" y "next"
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF
                            && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // Imprimir la matriz de costos
        System.out.println("Cost Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(dist[i][j] == INF){
                    System.out.print("∞\t");
                }else{
                    System.out.print(dist[i][j] + "\t");
                }

            }
            System.out.println();
        }
        System.out.println();

        // Imprimir la matriz de recorridos
        System.out.println("Path Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(next[i][j] + "\t");
            }
            System.out.println();
        }
    }
}