package Algoritmos;

import EstructurasDeDatos.Grafo.GrafoDirigidoPonderado;
import EstructurasDeDatos.Grafo.NodoG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultadoDijkstra {
    private final double[] distancias;
    private final int[] predecesores;

    public ResultadoDijkstra(double[] distancias, int[] predecesores) {
        this.distancias = distancias;
        this.predecesores = predecesores;
    }

    public double[] getDistancias() {
        return distancias;
    }

    public int[] getPredecesores() {
        return predecesores;
    }

    public double getDistanciaA(int destino) {
        return distancias[destino];
    }

    // Regresa la ruta como lista de nombres de nodos
    public List<String> reconstruirRuta(GrafoDirigidoPonderado grafo, int destino) {
        List<String> ruta = new ArrayList<>();
        int actual = destino;
        if (Double.isInfinite(distancias[destino])) {
            return ruta;
        }
        while (actual != -1) {
            NodoG nodo = grafo.getNodo(actual);
            ruta.add(nodo.getNombre());
            actual = predecesores[actual];
        }
        Collections.reverse(ruta);
        return ruta;
    }
}
