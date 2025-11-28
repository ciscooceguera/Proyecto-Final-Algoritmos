package EstructurasDeDatos.Arbol;

public class ArbolBinarioBusqueda {
    public NodoABB raiz;
    public void insertar(int valor) {
        raiz = insertarAux(raiz,valor);
    }
    private NodoABB insertarAux(NodoABB nodo, int valor) {
        if (nodo == null) {
            return new NodoABB(valor);
        }
        if (valor<nodo.getID()){
            nodo.setHijoIzq(insertarAux(nodo.getHijoIzq(),valor));
        }else if (valor>nodo.getID()){
            nodo.setHijoDer(insertarAux(nodo.getHijoDer(),valor));
        }
        return nodo;
    }
    public boolean find(int valor) {
        return findAux(raiz, valor);
    }
    private boolean findAux(NodoABB nodo, int valor) {
        if (nodo == null) {
            return false;
        }
        if (valor == nodo.getID()) {
            return true;
        }
        if (valor<nodo.getID()) {
            return findAux(nodo.getHijoIzq(), valor);
        }else{
            return findAux(nodo.getHijoDer(), valor);
        }
    }
    public void inOrden(){
        inOrdenAux(raiz);
    }
    private void inOrdenAux(NodoABB nodo) {
        if (nodo == null) {
            return;
        }
        inOrdenAux(nodo.getHijoIzq());
        System.out.println(nodo);
        inOrdenAux(nodo.getHijoDer());
    }
    public void preOrden(){
        preOrdenAux(raiz);
    }
    private void preOrdenAux(NodoABB nodo) {
        if (nodo == null) {
            return;
        }
        System.out.println(nodo);
        preOrdenAux(nodo.getHijoIzq());
        preOrdenAux(nodo.getHijoDer());
    }
    public void postOrden(){
        postOrdenAux(raiz);
    }
    private void postOrdenAux(NodoABB nodo) {
        if (nodo == null) {
            return;
        }
        postOrdenAux(nodo.getHijoIzq());
        postOrdenAux(nodo.getHijoDer());
        System.out.println(nodo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Árbol binario de búsqueda:\n");
        printTree(raiz, sb, "", true);
        return sb.toString();
    }

    private void printTree(NodoABB nodo, StringBuilder sb, String prefijo, boolean esUltimo) {
        if (nodo == null) {
            sb.append(prefijo)
                    .append(esUltimo ? "└── " : "├── ")
                    .append("null\n");
            return;
        }
        sb.append(prefijo)
                .append(esUltimo ? "└── " : "├── ")
                .append(nodo.getID())
                .append("\n");
        String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");
        if (nodo.getHijoIzq() != null || nodo.getHijoDer() != null) {
            printTree(nodo.getHijoIzq(), sb, nuevoPrefijo, false);
            printTree(nodo.getHijoDer(), sb, nuevoPrefijo, true);
        }
    }


}
