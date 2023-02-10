import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

    public class Main {
        public static void main(String[] args) {
            Graph graph = new Graph(9);
            try {
                BufferedReader reader = new BufferedReader(new FileReader("/home/jhonc/IdeaProjects/Proyecto-de-Estructuras/Proyecto-Estructuras/src/inputGrafo.txt"));
                String line = reader.readLine();
                while (line != null) {
                    String[] input = line.split(",");
                    graph.addEdge(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]), input[3]);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FloydWarshall.floydWarshall(graph);
        }

    }