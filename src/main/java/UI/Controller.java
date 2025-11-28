package UI;

import Algoritmos.*;
import EstructurasDeDatos.Arbol.ArbolBinarioBusqueda;
import EstructurasDeDatos.Arbol.CreadorArbol;
import EstructurasDeDatos.Grafo.*;
import LecturaArchivo.CargarArchivo;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Controller {

    @FXML private TableView<ObservableList<String>> tablaArchivo;

    @FXML private ComboBox<String> comboAlgoritmo;
    @FXML private ComboBox<String> comboOrigen;
    @FXML private ComboBox<String> comboDestino;
    @FXML private Button btnCargar;
    @FXML private Button btnEjecutar;
    @FXML private Button btnSalir;

    @FXML private TextArea areaResultados;
    @FXML private TableView<ObservableList<String>> tablaResultados;

    private String[][] matrizDatos;
    private GrafoDirigidoPonderado grafo;
    private ArbolBinarioBusqueda arbol;

    private final ObservableList<ObservableList<String>> datosTabla =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        comboAlgoritmo.setItems(FXCollections.observableArrayList(
                "Dijkstra (iterativo)",
                "Dijkstra (recursivo)",
                "Prim",
                "Floyd-Warshall",
                "Árbol binario de búsqueda",
                "ABB - Inserción",
                "ABB - Búsqueda",
                "ABB - inOrden (Ascendente)",
                "ABB - preOrden",
                "ABB - postOrden"
        ));

        tablaArchivo.setItems(datosTabla);
        areaResultados.setText("Cargue un archivo CSV para comenzar.\n");

        comboAlgoritmo.valueProperty().addListener((obs, oldV, newV) -> {
            boolean usaOD = newV != null && (
                    newV.startsWith("Dijkstra") ||
                            newV.equals("Prim") ||
                            newV.equals("Floyd-Warshall")
            );
            comboOrigen.setDisable(!usaOD);
            comboDestino.setDisable(!usaOD);
        });
    }

    @FXML
    private void onCargarArchivo() {
        Stage stage = (Stage) tablaArchivo.getScene().getWindow();

        CargarArchivo loader = new CargarArchivo();
        loader.abrirArchivo(stage);
        matrizDatos = loader.getMatriz();

        if (matrizDatos == null || matrizDatos.length == 0) {
            areaResultados.setText("No se cargó ningún archivo.");
            return;
        }

        mostrarTabla(matrizDatos);
        construirEstructuras();
        areaResultados.setText("Archivo cargado correctamente.\n");
    }

    private void mostrarTabla(String[][] matriz) {
        tablaArchivo.getColumns().clear();
        datosTabla.clear();

        int columnas = matriz[0].length;

        for (int col = 0; col < columnas; col++) {
            final int c = col;
            TableColumn<ObservableList<String>, String> tc =
                    new TableColumn<>("Col " + (col + 1));
            tc.setCellValueFactory(v ->
                    new SimpleStringProperty(v.getValue().get(c))
            );
            tablaArchivo.getColumns().add(tc);
        }

        for (String[] fila : matriz) {
            datosTabla.add(FXCollections.observableArrayList(Arrays.asList(fila)));
        }
    }

    private void construirEstructuras() {
        grafo = CreadorGrafo.matrizStringToGrafo(matrizDatos);
        arbol = CreadorArbol.matrizStringToABB(matrizDatos);

        comboOrigen.getItems().clear();
        comboDestino.getItems().clear();

        if (grafo != null) {
            for (NodoG nodo : grafo.getNodos()) {
                comboOrigen.getItems().add(nodo.getNombre());
                comboDestino.getItems().add(nodo.getNombre());
            }
        }
    }

    @FXML
    private void onEjecutar() {

        if (matrizDatos == null) {
            areaResultados.setText("Primero cargue un archivo CSV.");
            return;
        }

        String alg = comboAlgoritmo.getValue();
        if (alg == null) {
            areaResultados.setText("Seleccione un algoritmo.");
            return;
        }

        switch (alg) {
            case "Dijkstra (iterativo)" -> ejecutarDijkstra(false);
            case "Dijkstra (recursivo)" -> ejecutarDijkstra(true);
            case "Prim" -> ejecutarPrim();
            case "Floyd-Warshall" -> ejecutarFloyd();
            case "Árbol binario de búsqueda" -> mostrarArbolEstructura();
            case "ABB - Inserción" -> ejecutarInsercionABB();
            case "ABB - Búsqueda" -> ejecutarBusquedaABB();
            case "ABB - inOrden (Ascendente)" -> ejecutarRecorridoABB("inorden");
            case "ABB - preOrden" -> ejecutarRecorridoABB("preorden");
            case "ABB - postOrden" -> ejecutarRecorridoABB("postorden");
            default -> areaResultados.setText("Opción no reconocida.");
        }
    }

    private void ejecutarDijkstra(boolean rec) {

        if (grafo == null) {
            areaResultados.setText("No se ha generado el grafo.");
            return;
        }

        String origen = comboOrigen.getValue();
        String destino = comboDestino.getValue();

        if (origen == null || destino == null) {
            areaResultados.setText("Seleccione origen y destino.");
            return;
        }

        int idO = grafo.buscarIdPorNombre(origen);
        int idD = grafo.buscarIdPorNombre(destino);

        if (idO < 0 || idD < 0) {
            areaResultados.setText("Origen o destino no encontrados en el grafo.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        long tiempoInicio = System.nanoTime();
        ResultadoDijkstra r = rec
                ? Dijkstra.algoritmoRecursivo(grafo, idO)
                : Dijkstra.algoritmo(grafo, idO);
        long tiempoFin = System.nanoTime();
        long tiempo = tiempoFin - tiempoInicio;
        sb.append("Tiempo de ejecucion: " + tiempo/1e9 + "\n");
        double costo = r.getDistanciaA(idD);
        List<String> ruta = r.reconstruirRuta(grafo, idD);

        sb.append("Algoritmo: ").append(rec ? "Dijkstra Recursivo" : "Dijkstra Iterativo").append("\n");
        sb.append("Origen: ").append(origen).append("\n");
        sb.append("Destino: ").append(destino).append("\n\n");
        sb.append("Costo mínimo: ").append(costo).append("\n");
        sb.append("Ruta: ").append(ruta).append("\n\n");

        areaResultados.setText(sb.toString());
    }

    private void ejecutarPrim() {
        if (grafo == null) {
            areaResultados.setText("No se ha generado el grafo.");
            return;
        }

        String origen = comboOrigen.getValue();
        if (origen == null) {
            areaResultados.setText("Seleccione un nodo de origen para Prim.");
            return;
        }

        int id = grafo.buscarIdPorNombre(origen);
        if (id < 0) {
            areaResultados.setText("Nodo no encontrado en el grafo.");
            return;
        }

        List<AristaG> mst = Prim.prim(grafo, id);

        StringBuilder sb = new StringBuilder("Árbol de expansión mínima (Prim):\n\n");
        double costo = 0;

        for (AristaG a : mst) {
            sb.append(grafo.getNodo(a.getOrigen()).getNombre())
                    .append(" -> ")
                    .append(grafo.getNodo(a.getDestino()).getNombre())
                    .append(" (").append(a.getPeso()).append(")\n");
            costo += a.getPeso();
        }

        sb.append("\nCosto total: ").append(costo);

        areaResultados.setText(sb.toString());
    }

    private void ejecutarFloyd() {

        if (grafo == null) {
            areaResultados.setText("No se ha generado el grafo.");
            return;
        }

        int n = grafo.getSize();
        int INF = (int)1e8;
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;

            for (AristaG a : grafo.getNodo(i).getAristas()) {
                dist[i][a.getDestino()] = (int)a.getPeso();
            }
        }

        FloydWarshall.ejecutarFloydWarshall(dist);
        tablaResultados.getColumns().clear();
        ObservableList<ObservableList<String>> filas = FXCollections.observableArrayList();
        TableColumn<ObservableList<String>, String> colNodo = new TableColumn<>("Nodo");
        colNodo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        tablaResultados.getColumns().add(colNodo);

        for (int j = 0; j < n; j++) {
            final int col = j + 1;

            TableColumn<ObservableList<String>, String> tc =
                    new TableColumn<>(grafo.getNodo(j).getNombre());

            tc.setCellValueFactory(data ->
                    new SimpleStringProperty(data.getValue().get(col)));

            tablaResultados.getColumns().add(tc);
        }
        for (int i = 0; i < n; i++) {
            ObservableList<String> fila = FXCollections.observableArrayList();
            fila.add(grafo.getNodo(i).getNombre());

            for (int j = 0; j < n; j++) {
                fila.add(dist[i][j] == INF ? "INF" : String.valueOf(dist[i][j]));
            }

            filas.add(fila);
        }

        tablaResultados.setItems(filas);
        areaResultados.setText("Resultado de Floyd-Warshall mostrado en tabla.");
    }

    private void mostrarArbolEstructura() {
        if (arbol == null) {
            areaResultados.setText("No se ha generado el árbol binario de búsqueda.");
            return;
        }
        areaResultados.setText(arbol.toString());
    }

    private void ejecutarInsercionABB() {
        if (arbol == null) {
            areaResultados.setText("No se ha generado el árbol binario de búsqueda.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Inserción en ABB");
        dialog.setHeaderText("Inserción en Árbol Binario de Búsqueda");
        dialog.setContentText("Ingresa un valor entero a insertar:");

        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty()) {
            return;
        }

        try {
            int valor = Integer.parseInt(result.get());
            arbol.insertar(valor);
            areaResultados.setText("Se insertó el valor " + valor + " en el ABB.\n\n" +
                    "Recorrido inOrden actual:\n" + arbol.inOrden());
        } catch (NumberFormatException e) {
            areaResultados.setText("Valor inválido. Debe ser un entero.");
        }
    }

    private void ejecutarBusquedaABB() {
        if (arbol == null) {
            areaResultados.setText("No se ha generado el árbol binario de búsqueda.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Búsqueda en ABB");
        dialog.setHeaderText("Búsqueda en Árbol Binario de Búsqueda");
        dialog.setContentText("Ingresa un valor entero a buscar:");

        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty()) {
            return;
        }

        try {
            int valor = Integer.parseInt(result.get());
            boolean encontrado = arbol.find(valor);
            if (encontrado) {
                areaResultados.setText("El valor " + valor + " SÍ se encuentra en el ABB.");
            } else {
                areaResultados.setText("El valor " + valor + " NO se encuentra en el ABB.");
            }
        } catch (NumberFormatException e) {
            areaResultados.setText("Valor inválido. Debe ser un entero.");
        }
    }

    private void ejecutarRecorridoABB(String tipo) {
        if (arbol == null) {
            areaResultados.setText("No se ha generado el árbol binario de búsqueda.");
            return;
        }

        String res;
        switch (tipo) {
            case "inorden" -> res = "Recorrido inOrden (Ascendente):\n" + arbol.inOrden();
            case "preorden" -> res = "Recorrido preOrden:\n" + arbol.preOrden();
            case "postorden" -> res = "Recorrido postOrden:\n" + arbol.postOrden();
            default -> res = "Tipo de recorrido no válido.";
        }

        areaResultados.setText(res);
    }

    @FXML
    private void onSalir() {
        Platform.exit();
    }
}
