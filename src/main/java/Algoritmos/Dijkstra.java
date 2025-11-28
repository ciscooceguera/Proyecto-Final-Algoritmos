package Algoritmos;

import EstructurasDeDatos.Grafo.AristaG;
import EstructurasDeDatos.Grafo.GrafoDirigidoPonderado;
import EstructurasDeDatos.Grafo.NodoG;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {

    public static ResultadoDijkstra algoritmo(GrafoDirigidoPonderado grafo, int origen) {
        // Número de nodos
        int n = grafo.getSize();
        // Vectores
        double[] dist = new double[n]; // Vector de distancias
        int[] prev = new int[n]; // Vectores visitados
        boolean[] visitado = new boolean[n]; //
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(prev, -1); // Valor inicial o de ruptura
        dist[origen] = 0.0;
        // Comparator que recibe un arreglo, donde usa a[1] (la 2da posicion del arreglo) para ordenar
        // de menor a mayor
        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));
        pq.add(new double[]{origen, 0.0});
        while (!pq.isEmpty()) {
            double[] actual = pq.poll();
            int u = (int) actual[0];
            if (visitado[u]) continue;
            visitado[u] = true;
            NodoG nodoU = grafo.getNodo(u);
            for (AristaG arista : nodoU.getAristas()) {
                int v = arista.getDestino();
                double peso = arista.getPeso();
                double nuevaDist = dist[u] + peso;
                if (nuevaDist < dist[v]) {
                    dist[v] = nuevaDist;
                    prev[v] = u;
                    pq.add(new double[]{v, nuevaDist});
                }
            }
        }
        return new ResultadoDijkstra(dist, prev);
    }
    public static ResultadoDijkstra algoritmoRecursivo(GrafoDirigidoPonderado grafo, int origen) {
        int n = grafo.getSize();
        double[] dist = new double[n];
        int[] prev = new int[n];
        boolean[] visitado = new boolean[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(prev, -1);
        dist[origen] = 0.0;
        dijkstraRec(grafo, dist, prev, visitado);
        return new ResultadoDijkstra(dist, prev);
    }

    private static void dijkstraRec(GrafoDirigidoPonderado grafo, double[] dist, int[] prev, boolean[] visitado) {
        int n = grafo.getSize();
        int u = -1;// Nodo con menor distancia no visitado
        double mejorDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; i++) {
            if (!visitado[i] && dist[i] < mejorDist) {
                mejorDist = dist[i];
                u = i;
            }
        }
        if (u == -1) {
            return;
        }

        visitado[u] = true; // Nodo se marca como visitado

        NodoG nodoU = grafo.getNodo(u);
        for (AristaG arista : nodoU.getAristas()) {
            int v = arista.getDestino();
            double peso = arista.getPeso();

            double nuevaDist = dist[u] + peso;
            if (nuevaDist < dist[v]) {
                dist[v] = nuevaDist;
                prev[v] = u;
            }
        }

        dijkstraRec(grafo, dist, prev, visitado);
    }

}
