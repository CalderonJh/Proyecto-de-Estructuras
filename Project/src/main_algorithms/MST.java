package main_algorithms;

import java.util.*;


/**
 * La clase main_algorithms.MST contiene los métodos necesarios para
 * calcular el árbol de expansión minima usando los algoritmos de Kruskal o Prim
 */
public class MST {
    private static final int INF = Integer.MAX_VALUE;

    // seguimiento del padre de cada nodo en el main_algorithms.MST
    private static int[] parent;

    // seguimiento del rango de cada nodo en el main_algorithms.MST
    private static int[] rank;

    //peso de la arista que conecta cada vértice con su padre en el main_algorithms.MST
    private static int[] weights;

    //realiza un seguimiento de los vertices que se han agregado al main_algorithms.MST
    private static boolean[] added;

    //número de vertices que tiene el grafo
    private static int vertices;

    //matriz de adyacencia
    private static int[][] adj;


    //Subclase edge, usada para representar una arista
    public static class Edge implements Comparable<Edge>{
        // nodo de inicio, nodo de llegada y peso de la arista
        int start, end, weight;

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
            return start + " - " + end + "\t" + weight;
        }
    }

    /**
     * Calcula un árbol de expansion minima main_algorithms.MST con el algoritmo de Kruskal
     * @param graph a partir del cual se crea el main_algorithms.MST
     */
    public static void kruskalMST(Graph graph){
        vertices = graph.getVertices();
        adj = graph.getAdj();
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
        for (MST.Edge edge : edges) {
            int startParent = find(edge.start);
            int endParent = find(edge.end);

            if (startParent != endParent) {
                mst.add(edge);
                union(startParent, endParent);
            }
        }

        //imprime el main_algorithms.MST
        int total = 0;
        System.out.println("Edge \tWeight");
        for (Edge edge : mst) {
            System.out.println(edge.toString());
            total += edge.weight;
        }
        System.out.println("Total weight of the main_algorithms.MST: " + total);
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

    // div
    /**
     * Encuentra el main_algorithms.MST
     * @param graph grafo usado para calcular el main_algorithms.MST
     */
    public static void primMST(Graph graph){
        adj = graph.getAdj();
        vertices = graph.getVertices();
        parent = new int[vertices];
        weights = new int[vertices];
        added = new boolean[vertices];

        // asigna el primer vértice como punto de partida del main_algorithms.MST
        initialize(vertices);

        for (int i = 0; i < vertices - 1; i++) {
            int minWeightIx =  findIndex(vertices);
            added[minWeightIx] = true;
            update(minWeightIx);
        }

        //llama al método print para imprimir el main_algorithms.MST
        printMST(vertices);
    }

    /**
     * Establece el primer vértice como punto de partida
     * @param vertices número de vertices del grafo
     */
    private static void initialize(int vertices){
        weights[0] = 0;
        parent[0] = -1;
        for (int i = 1; i < vertices; i++){
            weights[i] = INF;
            added[i] = false;
        }
    }

    /**
     * @param vertices número de vertices del grafo
     * @return indice del vértice de menor peso que no se ha agregado al main_algorithms.MST
     */
    private static int findIndex(int vertices){
        int minW = INF;
        int indexMinW = -1;
        for (int i = 0; i < vertices; i++) {
            if(!added[i] && weights[i] < minW ){
                minW = weights[i];
                indexMinW = i;
            }
        }
        return indexMinW;
    }

    /**
     * Actualiza los pesos y los padres de los vértices adyacentes al vértice
     * que se acaba de agregar al main_algorithms.MST
     * @param index índice del vértice que acaba de ser agregado
     */
    private static void update(int index){
        for (int i = 0; i < vertices; i++) {
            if(adj[index][i] !=0 && !added[i] && adj[index][i] < weights[i]){
                parent[i] = index;
                weights[i] = adj[index][i];
            }
        }
    }

    /**
     * Imprime el main_algorithms.MST y el total del peso
     * @param vertices número de vertices del grafo
     */
    private static void printMST(int vertices) {
        int total = 0;
        System.out.println("Edge \tWeight");

        for (int i = 1; i < vertices; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + adj[i][parent[i]]);
            total += adj[i][parent[i]];
        }
        System.out.println("Total weight of the main_algorithms.MST: " + total + "\n");

    }
}