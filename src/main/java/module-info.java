module org.ascii_paint {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.ascii_paint.controller to javafx.fxml;
    opens org.ascii_paint to javafx.fxml;
    exports org.ascii_paint.controller;
    exports org.ascii_paint;
}