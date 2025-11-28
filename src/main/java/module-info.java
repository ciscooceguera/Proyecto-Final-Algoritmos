module com.example.proyectofinalalgoritmos {
    requires javafx.controls;
    requires javafx.fxml;

    opens UI to javafx.fxml;
    exports UI;
    exports Debug;
}
