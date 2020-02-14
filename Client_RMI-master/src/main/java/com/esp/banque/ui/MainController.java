package com.esp.banque.ui;

import com.esp.banque.config.properties.ServeurProperties;
import com.esp.banque.service.AgenceService;
import com.esp.banque.service.ClientService;
import com.esp.banque.tools.Loader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainController {

    @Autowired
    private AgenceService agenceService;

    private Stage primaryStage;

    @FXML private TabPane tabPane;
    @FXML private Tab clientTab;

    @FXML private ClientTabController clientTabPageController;
    @FXML private AgenceTabController agenceTabPageController;
    @FXML private CompteTabController compteTabPageController;
    @FXML private OperationTabController operationTabPageController;

    public void init(Stage primaryStage, ConfigurableApplicationContext context) {
        this.primaryStage = primaryStage;

        clientTabPageController.init(this);
        agenceTabPageController.init(this);
        compteTabPageController.init(this);
        operationTabPageController.init(this);

        System.out.println("*** MainController initialized");
    }

    public void showDialog(String file, String title, DialogCreationListener listener) {
        try {
            FXMLLoader loader = Loader.load("dialog/"+file+".fxml");
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            listener.onCreated(loader, dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface DialogCreationListener {
        void onCreated(FXMLLoader loader, Stage stage);
    }
}
