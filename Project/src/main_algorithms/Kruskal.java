package main_algorithms;

import java.util.*;

/**
 * La clase MST contiene los métodos necesarios para
 * calcular el árbol de expansión minima usando los algoritmos de Kruskal o Prim
 * El de kruskal retornará un grafo, el de prim solo imprime información
 */
public class Kruskal {
    private static final int INF = Integer.MAX_VALUE;
    // seguimiento del padre de cada nodo en el MST
    private static int[] parent;
    // seguimiento del rango de cada nodo en el MST
    private static int[] rank;
    //peso de la arista que conecta cada vértice con su padre en el MST
    private static int[] weights;
    //realiza un seguimiento de los vertices que se han agregado al MST
    private static boolean[] added;

    //Subclase edge, usada para representar una arista
    public static class Edge implements Comparable<Edge>{
        // nodo de inicio, nodo de llegada y peso de la arista
        public int start;
        public int end;
        public int weight;

        //Constructor de la clase Edge
        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        //compara dos aristas según su peso
        public int compareTo(Edge compareEdge){
            return this.weight - compareEdge.weight;
        }

        // imprime los campos de la arista
        public String toString(){
            return start + "," + end + "," + weight;
        }
    }

    /**
     * Calcula un árbol de expansion minima main_algorithms.MST con el algoritmo de Kruskal
     * @param graph a partir del cual se crea el main_algorithms.MST
     */
    public static Graph kruskalMST(Graph graph){
        var mst = new Graph(graph.getVertices());
        var queue = kruskalList(graph);
        String line;
        String[] args;
        int node1, node2, weight;
        for (Edge edge : queue){
            line = edge.toString();
            System.out.println(line);
            args = line.split(",");
            node1 = Integer.parseInt(args[0]);
            node2 = Integer.parseInt(args[1]);
            weight = Integer.parseInt(args[2]);
            mst.addEdge(node1, node2, weight);
        }
        return mst;
    }


    public static Queue<Edge> kruskalList(Graph graph){
        //número de vertices que tiene el grafo
        int vertices = graph.getVertices();
        //matriz de adyacencia
        int[][] adj = graph.getAdj();
        var edges = new ArrayList<Edge>();

        //Agrega todas las aristas del grafo a las List creada
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (adj[i][j] != 0) {
                    edges.add(new Edge(i, j, adj[i][j]));
                }
            }
        }

        // Ordena las aristas según su peso
        Collections.sort(edges);
        parent = new int[vertices];
        rank = new int[vertices];
        Queue<Edge> mst = new LinkedList<>();

        //crea conjuntos a partir de cada nodo
        makeSet(vertices);

        //agrega las arista a la cola mst
        for (Edge edge : edges) {
            int startParent = find(edge.start);
            int endParent = find(edge.end);
            if (startParent != endParent) {
                mst.add(edge);
                union(startParent, endParent);
            }
        }
        return mst;
    }

    /**
     * Crea conjuntos para cada nodo
     * @param vertices totales del grafo
     */
    private static void makeSet(int vertices){
        for (int i = 0; i < vertices; i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * Encuentra el nodo padre de otro nodo
     * @param vertex nodo del cual se busca el padre
     * @return raíz del conjunto al cual pertenece vertex
     */
    private static int find(int vertex){
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent[vertex]);
        }
        return parent[vertex];
    }

    /**
     * une dos conjuntos
     * @param start primer nodo para unir
     * @param end nodo al cual se unirá el primero
     */
    private static void union(int start, int end){
        int startParent = find(start);
        int endParent = find(end);

        if (rank[startParent] < rank[endParent]) {
            parent[startParent] = endParent;
        } else if (rank[startParent] > rank[endParent]) {
            parent[endParent] = startParent;
        } else {
            parent[endParent] = startParent;
            rank[startParent]++;
        }
    }

}