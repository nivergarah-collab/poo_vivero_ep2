module cl.duoc.vivero {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens cl.duoc.vivero.controller to javafx.fxml;
    opens cl.duoc.vivero.model to com.google.gson, javafx.base;

    exports cl.duoc.vivero;
}