module com.example.proyectofinalalgoritmos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens UI to javafx.fxml;
    exports UI;
    exports Debug;
}
