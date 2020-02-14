package com.esp.banque.ui;

import com.esp.banque.dto.CompteDto;
import com.esp.banque.service.CompteService;
import com.esp.banque.ui.dialog.CompteDialogController;
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
public class CompteTabController extends AbstractController {

    private final CompteService compteService;

    private ObservableList<CompteDto> comptes;

    @FXML private TableView tableView;
    @FXML private TableColumn<CompteDto, Integer> numeroColumn;
    @FXML private TableColumn<CompteDto, String> libelleColumn;
    @FXML private TableColumn<CompteDto, String> sensColumn;
    @FXML private TableColumn<CompteDto, Integer> soldeColumn;
    @FXML private TableColumn<CompteDto, String> clientColumn;

    @Autowired
    public CompteTabController(CompteService compteService) {
        this.compteService = compteService;
    }

    @Override
    public void init(MainController mainController) {
        super.init(mainController);
        comptes = FXCollections.observableArrayList();
        setupTable();
        fetchComptes();
    }

    private void setupTable() {
        tableView.setItems(comptes);
        numeroColumn.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getNumero()).asObject());
        soldeColumn.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getSolde()).asObject());
        libelleColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLibelle()));
        sensColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSens()));
        clientColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClient()));
    }

    public void nouveauCompteAction() {
        mainController.showDialog("compteDialog", "", (loader, stage) -> {
            CompteDialogController controller = loader.getController();
            controller.setUpdateMode(false);
            controller.setService(compteService);
            controller.setConfig(stage, this);
        });
    }

    public void fetchComptes() {
        comptes.clear();
        comptes.addAll(compteService.listAll());
    }

    public void tableClickAction() {
        mainController.showDialog("compteDialog", "", (loader, stage) -> {
            CompteDialogController controller = loader.getController();
            controller.setUpdateMode(true);
            controller.setService(compteService);
            controller.loadDto((CompteDto) tableView.getSelectionModel().getSelectedItem());
            controller.setConfig(stage, this);
        });
    }
}
