package Debug;

import LecturaArchivo.CargarArchivo;
import javafx.application.Application;
import javafx.stage.Stage;

public class PruebaArchivo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Prueba Lectura Archivo");
        primaryStage.show();

        CargarArchivo cargarArchivo = new CargarArchivo();
        cargarArchivo.abrirArchivo(primaryStage);
        String[][] matrizDatos =  cargarArchivo.getMatriz();

        for (int i = 0; i < matrizDatos.length; i++) {
            for (int j = 0; j < matrizDatos[i].length; j++) {
                System.out.print(matrizDatos[i][j] + "\n");
            }
        }
    }
    //C:\Users\joser\IdeaProjects\Proyecto-Final-Algoritmos\src\main\java\LecturaArchivo
}
