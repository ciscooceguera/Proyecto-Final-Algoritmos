package EstructurasDeDatos.Grafo;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CargarGrafo {
    private GrafoDirigidoPonderado grafo;

    public GrafoDirigidoPonderado abrirArchivo(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona un archivo CSV");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos CSV", "*.csv")
        );

        File archivo = fileChooser.showOpenDialog(stage);
        if (archivo != null) {
            leerCSV(archivo);
        }
        return grafo;
    }

    private void leerCSV(File archivo) {
        grafo = new GrafoDirigidoPonderado();
        Map<String, Integer> mapaNodos = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean esPrimera = true;
            while ((linea = br.readLine()) != null) {
                if (esPrimera) {
                    System.out.println("Encabezado: " + linea);
                    esPrimera = false;
                    continue;
                }
                String[] partes = linea.split(",");
                String origen = partes[0];
                String destino = partes[1];
                double costo = Double.parseDouble(partes[2]);

                if (!mapaNodos.containsKey(origen)) {
                    mapaNodos.put(origen, grafo.agregarNodo(origen));
                }
                if (!mapaNodos.containsKey(destino)) {
                    mapaNodos.put(destino, grafo.agregarNodo(destino));
                }

                int idOrigen = mapaNodos.get(origen);
                int idDestino = mapaNodos.get(destino);

                grafo.agregarArista(idOrigen, idDestino, costo);
                grafo.agregarArista(idDestino, idOrigen, costo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}