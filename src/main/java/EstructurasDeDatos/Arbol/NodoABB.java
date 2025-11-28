package EstructurasDeDatos.Arbol;

public class NodoABB<T> {
    private int ID;
    private int altura;
    private NodoABB<T> der, izq;

    public NodoABB(int id) {
        this.ID = id;
        altura = 1;
    }
    public int getID() {
        return ID;
    }
    public int getAltura() {
        return altura;
    }
    public NodoABB<T> getHijoIzq() {
        return izq;
    }
    public NodoABB<T> getHijoDer() {
        return der;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }
    public void setHijoIzq(NodoABB<T> izq) {
        this.izq = izq;
    }
    public void setHijoDer(NodoABB<T> der) {
        this.der = der;
    }
    public String toString() {
        return String.valueOf(ID);
    }
}
