package LecturaArchivo;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CargarArchivo {
    String[][] matrizDatos;
    String[] encabezados;
    public void abrirArchivo(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona un archivo CSV");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos CSV", "*.csv")
        );
        File archivo = fileChooser.showOpenDialog(stage);
        if (archivo != null) {
            leerCSV(archivo);
        }
    }

    private String[][] leerCSV(File archivo) {
        List<String[]> filas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean esPrimera = true;
            while ((linea = br.readLine()) != null) {
                if (esPrimera) {
                    encabezados = linea.split(",");
                    esPrimera = false;
                    continue;
                }
                String[] columnas = linea.split(",");
                filas.add(columnas);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        matrizDatos = new String[filas.size()][filas.getFirst().length];
        for (int i = 0; i < filas.size(); i++) {
            matrizDatos[i] = filas.get(i);
        }
        return matrizDatos;
    }
    public String[][] getMatriz(){
        return matrizDatos;
    }
    public String[] getEncabezados(){
        return encabezados;
    }
}
