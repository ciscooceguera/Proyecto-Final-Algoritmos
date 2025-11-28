package EstructurasDeDatos;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreadorGrafo {

    public static GrafoDirigidoPonderado matrizStringToGrafo(String[][] datos) {
        GrafoDirigidoPonderado grafo = new GrafoDirigidoPonderado();

        Map<String, Integer> indice = new LinkedHashMap<>();

        // Creacion Nodos
        for (String[] fila: datos){
            String origen = fila[0];
            String destino = fila[1];

            indice.computeIfAbsent(origen, k -> grafo.agregarNodo(origen));
            indice.computeIfAbsent(destino, k -> grafo.agregarNodo(destino));
        }

        // Creacion Aristas
        for (String[] fila: datos){
            String origen = fila[0];
            String destino = fila[1];
            double peso = Double.parseDouble(fila[2]);

            int idxOrigen = indice.get(origen);
            int idxDestino = indice.get(destino);

            grafo.agregarArista(idxOrigen, idxDestino, peso);
        }
        return grafo;
    }
}
