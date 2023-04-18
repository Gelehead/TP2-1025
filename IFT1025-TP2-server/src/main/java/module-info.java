module Server {
    requires javafx.controls;
    requires javafx.fxml;
    opens server.models to javafx.fxml;

    opens client.JavaFx to javafx.fxml;
    exports client.JavaFx;
    exports server.models;
}