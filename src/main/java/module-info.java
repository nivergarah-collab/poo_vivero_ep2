module cl.duoc.vivero {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens cl.duoc.vivero.controller to javafx.fxml;
    opens cl.duoc.vivero.model to com.google.gson, javafx.base;

    exports cl.duoc.vivero;

}