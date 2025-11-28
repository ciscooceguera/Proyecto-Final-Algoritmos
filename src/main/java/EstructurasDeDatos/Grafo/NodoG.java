package EstructurasDeDatos.Grafo;

import java.util.ArrayList;
import java.util.List;

public class NodoG {
    private final int id;
    private final String nombre;
    private final List<AristaG> adyacentes;

    public NodoG(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
        this.adyacentes = new ArrayList<AristaG>();
    }
    public void agregarArista(int destino, double peso){
        adyacentes.add(new AristaG(destino, peso));
    }
    public List<AristaG> getAristas(){
        return adyacentes;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String toString(){
        return nombre;
    }

}
