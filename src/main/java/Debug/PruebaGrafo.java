package Debug;

import EstructurasDeDatos.Grafo.CreadorGrafo;
import EstructurasDeDatos.Grafo.GrafoDirigidoPonderado;
import LecturaArchivo.CargarArchivo;
import javafx.application.Application;
import javafx.stage.Stage;

public class PruebaGrafo extends Application {
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


    }
}
