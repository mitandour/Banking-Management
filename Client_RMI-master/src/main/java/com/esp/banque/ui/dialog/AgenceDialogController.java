package com.esp.banque.ui.dialog;

import com.esp.banque.dto.AgenceDto;
import com.esp.banque.service.AgenceService;
import com.esp.banque.tools.DialogTools;
import com.esp.banque.ui.AbstractController;
import com.esp.banque.ui.AgenceTabController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class AgenceDialogController extends AbstractDialogController {

    private AgenceService agenceService;

    @FXML private TextField numeroField;
    @FXML private TextField nomField;
    @FXML private TextField adresseField;
    @FXML private Button cancelButton;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;

    private boolean updateMode = false;
    private AgenceDto dto;

    @Override
    public void setConfig(Stage stage, AbstractController parentController) {
        super.setConfig(stage, parentController);
        numeroField.setDisable(true);
        if (updateMode)
            stage.setTitle("Fiche Agence");
        else {
            stage.setTitle("Nouvelle Agence");
            deleteButton.setVisible(false);
        }
    }

    public void setService(AgenceService agenceService) {
        this.agenceService = agenceService;
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
        ((AgenceTabController) parentController).fetchAgences();
        dismissDialog();
    }

    public void deleteAction() {
        agenceService.delete(dto.getNumero());
        refreshParentAndClose();
    }

    public void loadDto(AgenceDto dto) {
        this.dto = dto;
        this.numeroField.setText(dto.getNumero().toString());
        this.nomField.setText(dto.getNom());
        this.adresseField.setText(dto.getAdresse());
    }

    private void doSave() {
        if (updateMode) {
            dto.setNom(nomField.getText());
            dto.setAdresse(adresseField.getText());
        } else
            dto = new AgenceDto(nomField.getText(), adresseField.getText());
        agenceService.save(dto, updateMode);
        refreshParentAndClose();
    }

    private boolean isFormValid() {
        if (nomField.getText().trim().length() <= 0)
            return false;
        if (adresseField.getText().trim().length() <= 0)
            return false;
        return true;
    }
}
