package Algoritmos;

import EstructurasDeDatos.Grafo.AristaG;
import EstructurasDeDatos.Grafo.GrafoDirigidoPonderado;

import java.util.*;

public class Prim {
    public static List<AristaG> prim(GrafoDirigidoPonderado grafo, int inicio) {
        int n = grafo.getSize();
        boolean[] visitado = new boolean[n];
        List<AristaG> mst = new ArrayList<>();

        PriorityQueue<AristaG> pq = new PriorityQueue<>(Comparator.comparingDouble(AristaG::getPeso));

        visitado[inicio] = true;
        for (AristaG arista : grafo.getNodo(inicio).getAristas()) {
            pq.add(new AristaG(arista.getDestino(), arista.getPeso(), inicio));
        }

        while (!pq.isEmpty() && mst.size() < n - 1) {
            AristaG actual = pq.poll();
            int destino = actual.getDestino();

            if (!visitado[destino]) {
                mst.add(actual);
                visitado[destino] = true;

                for (AristaG arista : grafo.getNodo(destino).getAristas()) {
                    if (!visitado[arista.getDestino()]) {
                        pq.add(new AristaG(arista.getDestino(), arista.getPeso(), destino));
                    }
                }
            }
        }

        return mst;
    }
}
