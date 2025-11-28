package Algoritmos;

public class FloydWarshall {

    //Metodo que ejecuta la logica del algoritmo FloydWarshall
    public static void ejecutarFloydWarshall(int [][]distancia) {
        int vertice = distancia.length;

        //Vertice intermedio en donde se prueba si pasar por K mejora el camino
        for (int k = 0; k < vertice; k++) {
            //Se recorren todas las posibles ciudades u origenes
            for (int i = 0; i < vertice; i++) {
                //Se recorren todas las posibles ciudades y destinos
                for (int j = 0; j < vertice; j++) {
                    //Se verifica el recorrido y se actualiza la matriz poniento el valor de la distancia minima
                    if(distancia[i][k] != 1e8 && distancia[k][j]!= 1e8)
                        distancia[i][j] = Math.min(distancia[i][j],distancia[i][k] + distancia[k][j]);
                }
            }
        }
    }
}
