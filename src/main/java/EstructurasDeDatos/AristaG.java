package EstructurasDeDatos;

public class AristaG {
    private final int destino;
    private final double peso;

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
}
