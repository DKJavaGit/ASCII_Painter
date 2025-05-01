package org.ascii_paint.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import org.ascii_paint.utils.fxutils.Transport;
import org.ascii_paint.utils.painting.Detail;

import java.io.File;

public class ChoiceController {
    @FXML private TextField lengthInput;
    @FXML private TextField outputFileName;
    @FXML private ChoiceBox<String> qualityChoice;
    @FXML private Label status_lbl;

    public static File file = null;

    private static final double NANO = 1000000000.0;
    private static final String goodImgStatus = "Изображение найдено";
    private static final String goodImgColor = "#00FF00"; // Green
    private static final String badImgStatus = "Не определено";
    private static final String badImgColor = "#FF3F38"; // Red
    private static final String baseWidth = "100";

    public void selectFile() {
        FileChooser chooser = new FileChooser();
        File chosenFile = chooser.showOpenDialog(status_lbl.getScene().getWindow());

        if (chosenFile != null) {
            if (isImage(chosenFile)) {
                setData(chosenFile, goodImgStatus, goodImgColor, true, true);
                file = chosenFile;
            } else {
                setData(chosenFile, badImgStatus, badImgColor, false, false);
                file = null;
            }
        } else {
            setData(chosenFile, badImgStatus, badImgColor, false, false);
            file = null;
        }
    }

    private boolean isImage(File chosenFile) {
        String type = chosenFile
                .getName()
                .substring(chosenFile.getName().length() - 4)
                .replace(".", "")
                .toLowerCase();

        return type.equals("png") || type.equals("jpg") || type.equals("jpeg");
    }

    private void setData(File chosenFile, String text, String color, boolean name, boolean length) {
        status_lbl.setText(text);
        status_lbl.setTextFill(Paint.valueOf(color));
        if (name && chosenFile != null) {
            outputFileName.setText(chosenFile
                    .getName()
                    .trim()
                    .substring(0, chosenFile.getName().length() - 4)
                    .replace(".", "") + "-ASCII");
        } else {
            outputFileName.setText("");
        }

        if (length) {
            lengthInput.setText(baseWidth);
        } else {
            lengthInput.setText("");
        }
    }

    public void convert() {
        if (file != null) {
            if (Integer.parseInt(lengthInput.getText()) > 10) {
                if (!outputFileName.getText().isEmpty()) {
                    String parent = file.getParent();
                    String name = outputFileName.getText();
                    String out = parent + "/" + name;
                    int width = Integer.parseInt(lengthInput.getText());

                    org.ascii_paint.utils.painting.Paint paint =
                            new org.ascii_paint.utils.painting.Paint(file.getAbsolutePath(),
                                    out,
                                    Detail.getDetail(qualityChoice.getValue()),
                                    width);

                    long time1 = System.nanoTime();
                    paint.convertToAscii_File();
                    long time2 = System.nanoTime();

                    StatusController.name = name + ".txt";
                    StatusController.place = parent;
                    StatusController.format = "ASCII Art";
                    StatusController.time = String.format("%.2f", (time2 - time1) / NANO).replace(",", ".");
                    StatusController.size = paint.getSize();
                    showSuccessDialog();
                }
            }
        }
    }

    public void showSuccessDialog() {
        Transport.openInNewStage("scenes/status-scene.fxml", true, "Успех", false, false);
    }
}