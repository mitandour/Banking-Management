package com.esp.banque.ui;

import com.esp.banque.dto.ClientDto;
import com.esp.banque.service.ClientService;
import com.esp.banque.ui.dialog.ClientDialogController;
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
public class ClientTabController extends AbstractController {

    private final ClientService clientService;

    private ObservableList<ClientDto> clients;

    @FXML private TableView tableView;
    @FXML private TableColumn<ClientDto, Integer> numeroColumn;
    @FXML private TableColumn<ClientDto, String> nomColumn;
    @FXML private TableColumn<ClientDto, String> prenomColumn;
    @FXML private TableColumn<ClientDto, String> agenceColumn;

    @Autowired
    public ClientTabController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void init(MainController mainController) {
        super.init(mainController);
        clients = FXCollections.observableArrayList();
        setupTable();
        fetchClients();
    }

    private void setupTable() {
        tableView.setItems(clients);
        numeroColumn.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getNumero()).asObject());
        nomColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNom()));
        prenomColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPrenom()));
        agenceColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAgence()));
    }

    public void nouveauClientAction() {
        mainController.showDialog("clientDialog", "", (loader, stage) -> {
            ClientDialogController controller = loader.getController();
            controller.setUpdateMode(false);
            controller.setService(clientService);
            controller.setConfig(stage, this);
        });
    }

    public void fetchClients() {
        clients.clear();
        clients.addAll(clientService.listAll());
    }

    public void tableClickAction() {
        mainController.showDialog("clientDialog", "", (loader, stage) -> {
            ClientDialogController controller = loader.getController();
            controller.setUpdateMode(true);
            controller.setService(clientService);
            controller.loadDto((ClientDto) tableView.getSelectionModel().getSelectedItem());
            controller.setConfig(stage, this);
        });
    }
}
