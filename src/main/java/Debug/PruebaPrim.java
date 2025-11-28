package Debug;

import Algoritmos.Prim;
import EstructurasDeDatos.Grafo.AristaG;
import EstructurasDeDatos.Grafo.CargarGrafo;
import EstructurasDeDatos.Grafo.GrafoDirigidoPonderado;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class PruebaPrim extends Application {
    @Override
    public void start(Stage stage) {
        CargarGrafo cargador = new CargarGrafo();
        GrafoDirigidoPonderado grafo = cargador.abrirArchivo(stage);

        System.out.println("Grafo cargado:");
        System.out.println(grafo);

        List<AristaG> mst = Prim.prim(grafo, 0);

        double costoTotal = 0;
        System.out.println("\nÁrbol de Expansión Mínimo (Prim):");
        for (AristaG arista : mst) {
            String origen = grafo.getNodo(arista.getOrigen()).getNombre();
            String destino = grafo.getNodo(arista.getDestino()).getNombre();
            System.out.println(origen + " -> " + arista.getPeso() + " -> " + destino);
            costoTotal += arista.getPeso();
        }
        System.out.println("Costo total: " + costoTotal);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
