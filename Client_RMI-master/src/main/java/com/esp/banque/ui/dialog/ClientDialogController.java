package com.esp.banque.ui.dialog;

import com.esp.banque.dto.ClientDto;
import com.esp.banque.service.ClientService;
import com.esp.banque.tools.DialogTools;
import com.esp.banque.ui.AbstractController;
import com.esp.banque.ui.ClientTabController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class ClientDialogController extends AbstractDialogController {

    private ClientService clientService;

    @FXML private TextField numeroField;
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField agenceField;
    @FXML private Button cancelButton;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;

    private boolean updateMode = false;
    private ClientDto dto;

    @Override
    public void setConfig(Stage stage, AbstractController parentController) {
        super.setConfig(stage, parentController);
        numeroField.setDisable(true);
        if (updateMode) {
            stage.setTitle("Fiche Client");
            agenceField.setDisable(true);
        } else {
            stage.setTitle("Nouveau Client");
            deleteButton.setVisible(false);
        }
    }

    public void setService(ClientService clientService) {
        this.clientService = clientService;
    }

    public void setUpdateMode(boolean updateMode) {
        this.updateMode = updateMode;
    }

    public void saveAction() {
        if (isFormValid()) {
            doSave();
        } else {
            DialogTools.alert(
                    "Erreur",
                    "Formulaire incomplet",
                    "Merci de remplir tous les champs éditables du formulaire.",
                    Alert.AlertType.WARNING, stage);
        }
    }

    private void refreshParentAndClose() {
        ((ClientTabController) parentController).fetchClients();
        dismissDialog();
    }


    public void deleteAction() {
        clientService.delete(dto.getNumero());
        refreshParentAndClose();
    }

    public void loadDto(ClientDto dto) {
        this.dto = dto;
        this.numeroField.setText(dto.getNumero().toString());
        this.nomField.setText(dto.getNom());
        this.prenomField.setText(dto.getPrenom());
        this.agenceField.setText(dto.getAgence());
    }

    private void doSave() {
        if (updateMode) {
            dto.setNom(nomField.getText());
            dto.setPrenom(prenomField.getText());
        } else
            dto = new ClientDto(nomField.getText(), prenomField.getText());
        clientService.save(updateMode, dto, Integer.valueOf(agenceField.getText()));
        refreshParentAndClose();
    }

    private boolean isFormValid() {
        if (nomField.getText().trim().length() <= 0)
            return false;
        if (prenomField.getText().trim().length() <= 0)
            return false;
        if (agenceField.getText().trim().length() <= 0)
            return false;
        return true;
    }


}
