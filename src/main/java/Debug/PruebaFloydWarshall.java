package Debug;

import EstructurasDeDatos.Grafo.CreadorGrafo;
import EstructurasDeDatos.Grafo.GrafoDirigidoPonderado;
import EstructurasDeDatos.Grafo.MatrizAdyacencia;
import LecturaArchivo.CargarArchivo;
import javafx.application.Application;
import javafx.stage.Stage;

import static Algoritmos.FloydWarshall.ejecutarFloydWarshall;

public class PruebaFloydWarshall extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Prueba Lectura Archivo");
        primaryStage.show();

        CargarArchivo cargarArchivo = new CargarArchivo();
        cargarArchivo.abrirArchivo(primaryStage);
        String[][] matrizDatos =  cargarArchivo.getMatriz();
        CreadorGrafo creadorGrafo = new CreadorGrafo();
        GrafoDirigidoPonderado grafo = creadorGrafo.matrizStringToGrafo(matrizDatos);
        System.out.println(grafo);


        MatrizAdyacencia matriz = new MatrizAdyacencia(grafo);
        matriz.inicializarMatriz();
        matriz.cargarDesdeGrafo(grafo);
        int[][] dist = matriz.getMatriz();
        matriz.imprimir();
        ejecutarFloydWarshall(dist);
        System.out.println("\nMatriz de distancias mínimas:");
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                if (dist[i][j] == 1e8) System.out.print("INF ");
                else System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }


    }
}
