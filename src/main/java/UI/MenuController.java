package UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

public class MenuController {

    @FXML private Button btnEjecutar;
    @FXML private Button btnCreditos;
    @FXML private Button btnSalir;
    @FXML
    private void initialize() {
        btnEjecutar.setOnAction(e -> {
            System.out.println("Ejecutar");
        });

        btnCreditos.setOnAction(e -> {
            System.out.println("Créditos");
            mostrarVentanaCreditos();
        });

        btnSalir.setOnAction(e -> {
            System.out.println("Salir");
            System.exit(0);
        });
    }

    private void mostrarVentanaCreditos() {
        VBox root = crearVentanaBase();

        Label titulo = new Label("Proyecto Final de Algoritmos");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2e3a45;");

        Label nombre1 = new Label("Diego Erik Alfonso Montoya");
        nombre1.setStyle("-fx-font-size: 18px; -fx-text-fill: #37474f;");
        Label matricula1 = new Label("Matrícula: 1198520");
        matricula1.setStyle("-fx-font-size: 16px; -fx-text-fill: #455a64;");

        Label nombre2 = new Label("Francisco Javier Oceguera Gutierrez");
        nombre2.setStyle("-fx-font-size: 18px; -fx-text-fill: #37474f;");
        Label matricula2 = new Label("Matrícula: 1175585");
        matricula2.setStyle("-fx-font-size: 16px; -fx-text-fill: #455a64;");

        Label nombre3 = new Label("Jose Ramon Suffo Peimbert");
        nombre3.setStyle("-fx-font-size: 18px; -fx-text-fill: #37474f;");
        Label matricula3 = new Label("Matrícula: 1197397");
        matricula3.setStyle("-fx-font-size: 16px; -fx-text-fill: #455a64;");

        Button cerrar = crearBotonCerrar();

        root.getChildren().addAll(
                titulo,
                nombre1, matricula1,
                nombre2, matricula2,
                nombre3, matricula3,
                cerrar
        );

        mostrarVentana("Créditos", root, 400, 420);
    }

    private VBox crearVentanaBase() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffffff, #e8eef3);" +
                        "-fx-background-radius: 15;"
        );
        root.setEffect(new DropShadow(18, Color.rgb(0, 0, 0, 0.25)));
        return root;
    }

    private Button crearBotonCerrar() {
        Button cerrar = new Button("Cerrar");
        cerrar.setStyle(
                "-fx-background-color: #6d8fa8;" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 8 18;" +
                        "-fx-background-radius: 8;" +
                        "-fx-font-size: 14px;"
        );
        cerrar.setOnAction(e -> {
            Stage ventana = (Stage) cerrar.getScene().getWindow();
            ventana.close();
        });
        return cerrar;
    }

    private void mostrarVentana(String titulo, VBox contenido, int w, int h) {
        Stage ventana = new Stage();
        ventana.setTitle(titulo);
        ventana.setScene(new Scene(contenido, w, h));
        ventana.show();
    }
}
