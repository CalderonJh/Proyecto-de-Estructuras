package main_algorithms;

import java.util.*;

public class Dijkstra {

    /**
     * Java Record Class Node
     * @param node identificador del vértice al que hace referencia el objeto
     * @param dist distancia desde el vértice inicial hasta la del vértice que se referencia
     */
    private record Node(int node, int dist) implements Comparable<Node> {
        //compara el dist del nodo actual con otro objeto node
        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }

    }

    /**
     * Encuentra el camino más corto entre dos vertices específicos usando el algoritmo de main_algorithms.Dijkstra
     * @param graph grafo que contiene los vertices
     * @param start vértice de partida
     * @param end vértice de llegada
     */
    public static void shortestPath(Graph graph, int start, int end) {
        int len = graph.getVertices();
        int[][] adj = graph.getAdj();
        int[] predecessor = new int[len];
        int[] dist = new int[len];
        boolean[] visited = new boolean[len];

        //Queue para guardar ordenadamente según su distancia los nodos a visitar
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            dist[i] = Integer.MAX_VALUE;
            predecessor[i] = -1;
        }
        dist[start] = 0;
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            int curr = queue.poll().node();
            if (!visited[curr]) {
                visited[curr] = true;
                for (int i = 0; i < len; i++) {
                    if (adj[curr][i] != 0) {
                        int newDist = dist[curr] + adj[curr][i];
                        if (newDist < dist[i]) {
                            dist[i] = newDist;
                            queue.add(new Node(i, dist[i]));
                            predecessor[i] = curr;
                        }
                    }
                }
            }
            if (curr == end) {
                break;
            }
        }

        //imprime el camino más corto encontrado
        System.out.println(start + " - " + end + ": " + dist[end]);
        int curr = end;
        Stack<Integer> stack = new Stack<>();

        while (curr != -1) {
            stack.add(curr);
            curr = predecessor[curr];
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    public static void allShortestPath(Graph graph, int start){
        for (int i = 0; i < graph.getVertices(); i++) {
            if(start == i) continue;
            shortestPath(graph,start,i);
            System.out.println();
        }
    }

}
