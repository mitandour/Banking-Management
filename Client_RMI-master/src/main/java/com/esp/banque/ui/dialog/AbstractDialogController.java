package com.esp.banque.ui.dialog;

import com.esp.banque.ui.AbstractController;
import javafx.stage.Stage;

public abstract class AbstractDialogController {

    Stage stage;
    AbstractController parentController;

    public void setConfig(Stage stage, AbstractController controller) {
        this.stage = stage;
        this.parentController = controller;
    }

    public void dismissDialog() {
        stage.close();
    }
}
