package EstructurasDeDatos.Grafo;

public class MatrizAdyacencia {
    //Se declaran los atributos de la clase
    private int[][] matriz;
    private int size;

    //Se utiliza una variable para representar el infinito en caso de que no haya una ruta de un nodo a otro
    private final int INF = (int)1e8;

    //Constructor de la clase donde se inicializa la matriz vacia
    public MatrizAdyacencia(GrafoDirigidoPonderado grafo) {
        size = grafo.getSize();
        matriz = new int[size][size];
    }

    //Metodo para inicializar la clase con 0 o INF en caso de que no haya una ruta
    public void inicializarMatriz() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriz[i][j] = (i == j) ? 0 : INF;
            }
        }
    }

    //Metodo para llenar la matriz con las aristar del grafo
    public void cargarDesdeGrafo(GrafoDirigidoPonderado grafo) {
        for (NodoG nodo : grafo.getNodos()) {
            for (AristaG arista : nodo.getAristas()) {
                matriz[nodo.getId()][arista.getDestino()] = (int) arista.getPeso();
            }
        }
    }

    //Metodo getter para obtener la matriz de adyacencia
    public int[][] getMatriz() {
        return matriz;
    }

    public void imprimir() {
        System.out.println("\nMatriz de adyacencia:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matriz[i][j] == INF) System.out.print("INF ");
                else System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
