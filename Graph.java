/**
 * La clase Graph representa un grafo en su forma básica, el cual
 *     se creará con una cantidad fija de vertices y se le podrán agregar
 *     aristas desde un vértice a otro representando un peso.
 */

import java.util.*;

public class Graph {

    //cantidad de vertices que tendrá el grafo creado
    private final int vertices;

    //matriz de enteros que representará los pesos de las aristas
    private static int[][] adj;

    //cantidad de aristas creadas
    private int edges;


    /**
     * Constructor de la clase Graph
     * @param vertices representa la cantidad de vertices que tendrá el nuevo grafo.
     */
    public Graph(int vertices) {
        this.vertices = vertices;
        adj = new int[vertices][vertices];
        edges = 0;
    }


    /**
     * addEdge permite conectar dos vertices del nodo
     * @param v1 vértice de inicio
     * @param v2 vértice de destino
     * @param weight peso de la arista que conecta los vertices
     */
    public void addEdge(int v1, int v2, int weight){
        adj[v1][v2] = weight;
        adj[v2][v1] = weight;
        edges++;
    }


    //Imprime una matriz de pesos asociada al grafo creado
    public void print() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adj[i][j] + "\t");
            }
            System.out.println();
        }
    }


    /**
     *Obtener la cantidad de vertices con los que se creó el grafo
     * @return numero de vertices del grafo
     */
    public int getVertices() {
        return vertices;
    }


    /**
     * Obtener la matriz adjunta al grafo
     * @return matriz de adyacencia
     */
    public int[][] getAdj(){
        return adj;
    }

    /**
     * Recorrido DFS del grafo
     * @param v vértice de inicio del recorrido
     */
    public void DFS(int v) {
        Dfs(v, new boolean[vertices]);
    }


    /**
     * Recorrido DFS del grafo
     * @param v el vértice de inicio
     * @param visited lleva control de los vertices visitados
     */
    private void Dfs(int v, boolean[] visited){
        visited[v] = true;
        System.out.print(v + " ");
        for (int i = 0; i < vertices; i++) {
            if (adj[v][i] != 0 && !visited[i]) {
                Dfs(i, visited);
            }
        }
    }


    /**
     * recorrido BFS del grafo
     * @param v vértice de inicio
     */
    public void BFS(int v) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.add(v);
        while (!queue.isEmpty()) {
            int current = queue.remove();
            System.out.print(current + " ");
            for (int i = 0; i < vertices; i++) {
                if (adj[current][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    //obtener cantidad total de aristas creadas
    public int getEdges() {
        return edges;
    }
}
