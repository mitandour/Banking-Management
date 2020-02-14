package com.esp.banque.tools;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DialogTools {

    public static void alert(String title, String header, String content, Alert.AlertType type, Stage stage) {
        Alert alert = new Alert(type);
        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
