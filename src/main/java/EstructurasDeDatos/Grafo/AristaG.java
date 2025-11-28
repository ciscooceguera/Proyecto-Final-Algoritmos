package EstructurasDeDatos.Grafo;

public class AristaG {
    private final int destino;
    private final double peso;
    private int origen;

    public AristaG(int destino, double peso, int origen) {
        this.destino = destino;
        this.peso = peso;
        this.origen = origen;
    }

    public AristaG(int destino, double peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public int getDestino() {
        return destino;
    }
    public double getPeso() {
        return peso;
    }
    public String toString() {
        return peso + "";
    }
    public int getOrigen() { return origen; }
}
