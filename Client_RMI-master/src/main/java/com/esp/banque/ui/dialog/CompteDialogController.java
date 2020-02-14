package com.esp.banque.ui.dialog;

import com.esp.banque.dto.CompteDto;
import com.esp.banque.service.CompteService;
import com.esp.banque.tools.DialogTools;
import com.esp.banque.ui.AbstractController;
import com.esp.banque.ui.CompteTabController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CompteDialogController extends AbstractDialogController {

    private CompteService compteService;

    @FXML private TextField numeroField;
    @FXML private TextField libelleField;
    @FXML private TextField sensField;
    @FXML private TextField soldeField;
    @FXML private TextField clientField;
    @FXML private Button cancelButton;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;

    private boolean updateMode = false;
    private CompteDto dto;

    @Override
    public void setConfig(Stage stage, AbstractController parentController) {
        super.setConfig(stage, parentController);
        numeroField.setDisable(true);
        if (updateMode) {
            stage.setTitle("Fiche Compte");
            clientField.setDisable(true);
            soldeField.setDisable(true);
            sensField.setDisable(true);
        } else {
            stage.setTitle("Nouveau Compte");
            deleteButton.setVisible(false);
        }
    }

    public void setService(CompteService compteService) {
        this.compteService = compteService;
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
        ((CompteTabController) parentController).fetchComptes();
        dismissDialog();
    }


    public void deleteAction() {
        compteService.delete(dto.getNumero());
        refreshParentAndClose();
    }

    public void loadDto(CompteDto dto) {
        this.dto = dto;
        this.numeroField.setText(dto.getNumero().toString());
        this.libelleField.setText(dto.getLibelle());
        this.sensField.setText(dto.getSens());
        this.soldeField.setText(dto.getSolde().toString());
        this.clientField.setText(dto.getClient());
    }

    private void doSave() {
        if (updateMode) {
            dto.setLibelle(libelleField.getText());
            compteService.editLibelle(dto.getNumero(), libelleField.getText());
        } else {
            dto = new CompteDto(libelleField.getText(), sensField.getText(), Integer.valueOf(soldeField.getText()));
            compteService.save(dto, Integer.valueOf(clientField.getText()));
        }
        refreshParentAndClose();
    }

    private boolean isFormValid() {
        if (libelleField.getText().trim().length() <= 0)
            return false;
        if (sensField.getText().trim().length() <= 0)
            return false;
        if (soldeField.getText().trim().length() <= 0)
            return false;
        if (clientField.getText().trim().length() <= 0)
            return false;
        return true;
    }
}

