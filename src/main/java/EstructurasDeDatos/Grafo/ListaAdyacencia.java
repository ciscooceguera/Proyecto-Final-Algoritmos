package EstructurasDeDatos.Grafo;

import java.util.ArrayList;
import java.util.List;

public class ListaAdyacencia {
    private List<List<AristaG>> listas;

    public ListaAdyacencia() {
        listas = new ArrayList<List<AristaG>>();
    }
    public int agregarNodo(){
        listas.add(new ArrayList<>());
        return listas.size() - 1;
    }
    public void agregarArista(int origen, int destino, double peso){
        listas.get(origen).add(new AristaG(destino, peso));
    }
    public List<List<AristaG>> getListaAdyacencia(){
        return listas;
    }
    public int getSize(){
        return listas.size();
    }
    public void imprimirListaAdyacencia(GrafoDirigidoPonderado grafo){
        System.out.println("Lista de adyacencia:");

        for (NodoG nodo : grafo.getNodos()) {
            System.out.print(nodo.getNombre() + " (" + nodo.getId() + ") -> ");

            List<AristaG> aristas = nodo.getAristas();
            if (aristas.isEmpty()) {
                System.out.println("(sin salidas)");
            } else {
                for (int i = 0; i < aristas.size(); i++) {
                    AristaG arista = aristas.get(i);
                    NodoG destino = grafo.getNodo(arista.getDestino());
                    System.out.print(destino.getNombre()
                            + " [" + arista.getPeso() + "]");
                    if (i < aristas.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }
    }
}
