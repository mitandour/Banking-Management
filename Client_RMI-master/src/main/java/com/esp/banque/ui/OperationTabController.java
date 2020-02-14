package com.esp.banque.ui;

import com.esp.banque.dto.OperationDto;
import com.esp.banque.service.OperationService;
import com.esp.banque.ui.dialog.MouvementDialogController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationTabController extends AbstractController {
    private final OperationService operationService;

    private ObservableList<OperationDto> operations;

    @FXML
    private TableView tableView;
    @FXML private TableColumn<OperationDto, Integer> numeroColumn;
    @FXML private TableColumn<OperationDto, String> libelleColumn;
    @FXML private TableColumn<OperationDto, String> sensColumn;
    @FXML private TableColumn<OperationDto, Integer> montantColumn;
    @FXML private TableColumn<OperationDto, String> dateColumn;
    @FXML private TableColumn<OperationDto, String> compteColumn;

    @Autowired
    public OperationTabController(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public void init(MainController mainController) {
        super.init(mainController);
        operations = FXCollections.observableArrayList();
        setupTable();
        fetchOperations();

    }

    private void setupTable() {
        tableView.setItems(operations);
        numeroColumn.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getNumero()).asObject());
        libelleColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLibelle()));
        sensColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSens()));
        montantColumn.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMontant()).asObject());
        dateColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDate().toString()));
    }

    public void nouveauVersementAction() {
        nouveauMouvement("CR");
    }

    public void nouveauRetraitAction() {
        nouveauMouvement("DB");
    }

    private void nouveauMouvement(String sens) {
        mainController.showDialog("mouvementDialog", "", (loader, stage) -> {
            MouvementDialogController controller = loader.getController();
            controller.setCreationMode(true);
            controller.setService(operationService);
            controller.setConfig(stage, this);
            controller.setSens(sens);
        });
    }

    public void fetchOperations() {
        operations.clear();
        operations.addAll(operationService.listAll());
    }

    public void tableClickAction() {
        mainController.showDialog("mouvementDialog", "", (loader, stage) -> {
            MouvementDialogController controller = loader.getController();
            controller.setConfig(stage, this);
            controller.setCreationMode(false);
            controller.setService(operationService);
            controller.loadDto((OperationDto) tableView.getSelectionModel().getSelectedItem());
        });
    }

    public void nouveauVirementAction(ActionEvent actionEvent) {
    }
}
