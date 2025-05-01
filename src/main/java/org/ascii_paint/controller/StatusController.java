package org.ascii_paint.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StatusController implements Initializable {
    @FXML private Label name_lbl;
    @FXML private Label place_lbl;
    @FXML private Label format_lbl;
    @FXML private Label size_lbl;
    @FXML private Label time_lbl;

    public static String name;
    public static String place;
    public static String format;
    public static String size;
    public static String time;

    public static void setValues (String name, String place,
                                  String format, String size,
                                  String time) {
        StatusController.name = name+".txt";
        StatusController.place = place;
        StatusController.format = format;
        StatusController.time = time;
        StatusController.size = size;
    }

    @FXML
    public void close() {
        Stage stage = (Stage) name_lbl.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_lbl.setText(String.format("Имя: %s", name));
        place_lbl.setText(String.format("Расположение: %s", place));
        format_lbl.setText(String.format("Тип: %s", format));
        time_lbl.setText(String.format("Время: %s с", time));
        size_lbl.setText(String.format("Размер: %s", size));
    }
}
