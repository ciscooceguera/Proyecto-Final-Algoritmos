module com.example.proyectofinalalgoritmos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectofinalalgoritmos to javafx.fxml;
    exports com.example.proyectofinalalgoritmos;
}