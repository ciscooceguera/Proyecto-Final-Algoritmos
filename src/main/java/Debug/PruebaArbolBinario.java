package Debug;

import EstructurasDeDatos.Arbol.ArbolBinarioBusqueda;
import EstructurasDeDatos.Arbol.CreadorArbol;
import EstructurasDeDatos.Grafo.CreadorGrafo;
import EstructurasDeDatos.Grafo.GrafoDirigidoPonderado;
import LecturaArchivo.CargarArchivo;
import javafx.application.Application;
import javafx.stage.Stage;

public class PruebaArbolBinario extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Prueba Arbol Binario");
        primaryStage.show();

        CargarArchivo cargarArchivo = new CargarArchivo();
        cargarArchivo.abrirArchivo(primaryStage);
        String[][] matrizDatos =  cargarArchivo.getMatriz();

        ArbolBinarioBusqueda abb = new ArbolBinarioBusqueda();
        CreadorArbol creador = new CreadorArbol();
        abb = creador.matrizStringToABB(matrizDatos);

        System.out.println(abb);
        System.out.println("inOrden: \n");
        abb.inOrden();
        System.out.println("postOrden: \n");
        abb.postOrden();
        System.out.println("preOrden: \n");
        abb.preOrden();

    }
}
