package com.esp.banque.ui;

import com.esp.banque.dto.AgenceDto;
import com.esp.banque.service.AgenceService;
import com.esp.banque.service.ClientService;
import com.esp.banque.ui.dialog.AgenceDialogController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgenceTabController extends AbstractController {

    private final AgenceService agenceService;

    private ObservableList<AgenceDto> agences;

    @FXML private TableView tableView;
    @FXML private TableColumn<AgenceDto, Integer> numeroColumn;
    @FXML private TableColumn<AgenceDto, String> nomColumn;
    @FXML private TableColumn<AgenceDto, String> adresseColumn;

    @Autowired
    public AgenceTabController(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    public void init(MainController mainController) {
        super.init(mainController);
        agences = FXCollections.observableArrayList();
        setupTable();
        fetchAgences();
    }


    private void setupTable() {
        tableView.setItems(agences);
        numeroColumn.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getNumero()).asObject());
        nomColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNom()));
        adresseColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAdresse()));
    }

    public void nouvelleAgenceAction() {
        mainController.showDialog("agenceDialog", "", (loader, stage) -> {
            AgenceDialogController controller = loader.getController();
            controller.setUpdateMode(false);
            controller.setService(agenceService);
            controller.setConfig(stage, this);
        });
    }

    public void fetchAgences() {
        agences.clear();
        agences.addAll(agenceService.listAll());
    }

    public void tableClickAction() {
        mainController.showDialog("agenceDialog", "", (loader, stage) -> {
            AgenceDialogController controller = loader.getController();
            controller.setUpdateMode(true);
            controller.setService(agenceService);
            controller.loadDto((AgenceDto) tableView.getSelectionModel().getSelectedItem());
            controller.setConfig(stage, this);
        });
    }
}
