package EstructurasDeDatos.Grafo;

import java.util.ArrayList;
import java.util.List;

public class GrafoDirigidoPonderado {
    private final List<NodoG> nodos;
    public GrafoDirigidoPonderado() {
        nodos = new ArrayList<NodoG>();
    }
    public int agregarNodo(String nombre) {
        int id = nodos.size();
        nodos.add(new NodoG(id, nombre));
        return id;
    }
    public void agregarArista(int origen, int destino, double peso){
        nodos.get(origen).agregarArista(destino, peso);
    }
    public NodoG getNodo(int id){
        return nodos.get(id);
    }
    public int getSize(){
        return nodos.size();
    }
    public List<NodoG> getNodos(){
        return nodos;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Grafo dirigido ponderado:\n");
        // Recorre nodos
        for (NodoG nodo : nodos) {
            sb.append(nodo.getNombre()).append(" -> ");
            if (nodo.getAristas().isEmpty()) {
                sb.append("(sin aristas)");
            } else {
                // Recorre Aristas
                for (int i = 0; i < nodo.getAristas().size(); i++) {
                    AristaG arista = nodo.getAristas().get(i);
                    // Obtiene el destino de la arista y concatena el nombre
                    String nombreDestino = nodos.get(arista.getDestino()).getNombre();
                    // Concatena el peso
                    sb.append(nombreDestino)
                            .append(" (")
                            .append(arista.getPeso())
                            .append(")");
                    if (i < nodo.getAristas().size() - 1) {
                        sb.append(", ");
                    }
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
