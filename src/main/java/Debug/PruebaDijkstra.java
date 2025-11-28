package Debug;

import Algoritmos.Dijkstra;
import Algoritmos.ResultadoDijkstra;
import EstructurasDeDatos.Grafo.CreadorGrafo;
import EstructurasDeDatos.Grafo.GrafoDirigidoPonderado;
import EstructurasDeDatos.Grafo.NodoG;
import LecturaArchivo.CargarArchivo;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class PruebaDijkstra extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Prueba Grafo");
        primaryStage.show();

        CargarArchivo cargarArchivo = new CargarArchivo();
        cargarArchivo.abrirArchivo(primaryStage);
        String[][] matrizDatos =  cargarArchivo.getMatriz();
        CreadorGrafo creadorGrafo = new CreadorGrafo();
        GrafoDirigidoPonderado grafo = creadorGrafo.matrizStringToGrafo(matrizDatos);
        System.out.println(grafo);

        int idOrigen = grafo.buscarIdPorNombre("CD-Mexicali");
        int idDestino = grafo.buscarIdPorNombre("CD-CDMX");

        ResultadoDijkstra resultadoDijkstra = Dijkstra.algoritmoRecursivo(grafo, idOrigen);

        double costo = resultadoDijkstra.getDistanciaA(idDestino);
        List<String> ruta = resultadoDijkstra.reconstruirRuta(grafo, idDestino);
        System.out.println("Costo minimo: " + costo);
        System.out.println("Ruta: " + ruta);

        for(int i = 0; i < grafo.getSize(); i++){
            if (idOrigen != i) {

                resultadoDijkstra = Dijkstra.algoritmoRecursivo(grafo, idOrigen);

                costo = resultadoDijkstra.getDistanciaA(i);
                ruta = resultadoDijkstra.reconstruirRuta(grafo, i);
                System.out.println("Costo minimo: " + costo);
                System.out.println("Ruta: " + ruta);

            }
        }




    }
}
