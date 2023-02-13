package test;

import main_algorithms.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        read_txt();
        Graph graph = createGraph();
        System.out.println(graph.getEdges());
        System.out.println(graph.getVertices());
        System.out.println(graph.getTotalWeight());
        var mst = MST_graph.primMST(graph);
        System.out.println(mst.getEdges());
        System.out.println(mst.getVertices());
        System.out.println(mst.getTotalWeight());
    }

    /**
     * Usa una lista de Strings para crear un grafo
     * @return grafo creado
     * */
    public static Graph createGraph(){
        var list = read_txt();
        var graph = new Graph(18);
        int node1, node2, weight;
        String line;
        while (!list.isEmpty()){
            line = list.poll();
            var args = line.split(",");
            node1 = Integer.parseInt(args[0]);
            node2 = Integer.parseInt(args[1]);
            weight = Integer.parseInt(args[2]);
            graph.addEdge(node1, node2, weight, args[3]);
        }
        return graph;
    }

    /**
     * Lee el archivo de texto plano con la información del grafo
     * @return Lista de Strings con cada una de las líneas del txr
     */
    public static Queue<String> read_txt() {
        Queue<String> list = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./Project/inputGrafo.txt"));
            String line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}