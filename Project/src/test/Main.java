package test;

import main_algorithms.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        var giron = createGraph(16,"./Project/Rutas/giron.txt");
        System.out.println(Kruskal.list(giron));
        var mst = createGraph(giron.getVertices(), Kruskal.list(giron));
        System.out.println(Kruskal.list(mst));
    }

    /**
     * Usa una lista de Strings para crear un grafo
     * @param size tamaño del grafo
     * @param dir dirección del archivo txt
     * @return grafo creado
     * */
    public static Graph createGraph(int size, String dir){
        var list = read_txt(dir);
        return createGraph(size, list);
    }

    public static Graph createGraph(int size, Queue<String> list){
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