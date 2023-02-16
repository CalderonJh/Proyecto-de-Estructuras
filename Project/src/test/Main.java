package test;

import main_algorithms.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*
        var giron = createGraph(16,"./Project/Rutas/giron.txt");
        MST.prim(giron);
        var mst = MST.kruskalMST(giron);
        System.out.println(mst.getVertices());
        System.out.println(mst.getEdges());
        System.out.println(mst.getTotalWeight());
         */
        Graph graph = new Graph(9);
        graph.addEdge(0,1,4);
        graph.addEdge(0,7,8);
        graph.addEdge(1,2,8);
        graph.addEdge(1,7,11);
        graph.addEdge(2,3,7);
        graph.addEdge(2,5,4);
        graph.addEdge(2,8,2);
        graph.addEdge(3,4,9);
        graph.addEdge(3,5,14);
        graph.addEdge(4,5,10);
        graph.addEdge(5,6,2);
        graph.addEdge(6,7,1);
        graph.addEdge(6,8,6);
        graph.addEdge(7,8,7);
        System.out.println(Kruskal.kruskalList(graph));

    }




    /**
     * Usa una lista de Strings para crear un grafo
     * @param size tamaño del grafo
     * @param dir dirección del archivo txt
     * @return grafo creado
     * */
    public static Graph createGraph(int size, String dir){
        var list = read_txt(dir);
        var graph = new Graph(size);
        int node1, node2, weight;
        String line;
        while (!list.isEmpty()){
            line = list.poll();
            var args = line.split(",");
            node1 = Integer.parseInt(args[0]);
            node2 = Integer.parseInt(args[1]);
            weight = Integer.parseInt(args[2]);
            graph.addEdge(node1, node2, weight);
        }
        return graph;
    }

    /**
     * Lee el archivo de texto plano con la información del grafo
     * @return Lista de Strings con cada una de las líneas del txr
     */
    public static Queue<String> read_txt(String dir) {
        Queue<String> list = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dir));
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