package com.esp.banque.ui.dialog;

import com.esp.banque.dto.OperationDto;
import com.esp.banque.service.OperationService;
import com.esp.banque.tools.DialogTools;
import com.esp.banque.ui.AbstractController;
import com.esp.banque.ui.OperationTabController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MouvementDialogController extends AbstractDialogController {

    private OperationService operationService;

    @FXML private TextField numeroField;
    @FXML private TextField libelleField;
    @FXML private TextField montantField;
    @FXML private TextField compteField;
    @FXML private TextField dateField;

    @FXML private Button cancelButton;
    @FXML private Button saveButton;

    private boolean creationMode = false;
    private String sens;
    private OperationDto dto;

    @Override
    public void setConfig(Stage stage, AbstractController parentController) {
        super.setConfig(stage, parentController);
        numeroField.setDisable(true);
        dateField.setDisable(true);
        if (!creationMode) {
            stage.setTitle("Fiche Opération");
            montantField.setDisable(true);
            libelleField.setDisable(true);
            compteField.setDisable(true);
            saveButton.setVisible(false);
        }
    }

    public void setSens(String sens) {
        this.sens = sens;
        switch (sens) {
            case "CR": stage.setTitle("Nouveau Versement"); break;
            case "DB": stage.setTitle("Nouveau Retrait"); break;
        }
    }

    public void setService(OperationService operationService) {
        this.operationService = operationService;
    }

    public void setCreationMode(boolean creationMode) {
        this.creationMode = creationMode;
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
        ((OperationTabController) parentController).fetchOperations();
        dismissDialog();
    }

    public void loadDto(OperationDto dto) {
        this.dto = dto;
        this.numeroField.setText(dto.getNumero().toString());
        this.libelleField.setText(dto.getLibelle());
        this.compteField.setText(dto.getCompte().toString());
        this.montantField.setText(dto.getMontant().toString());
        this.dateField.setText(dto.getDate());
        this.sens = dto.getSens();
        stage.setTitle(stage.getTitle()+" - "+sens);
    }

    private void doSave() {
            dto = new OperationDto(sens, libelleField.getText(), Integer.valueOf(montantField.getText()));
        try {
            operationService.mouvement(dto, Integer.valueOf(compteField.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        refreshParentAndClose();
    }

    private boolean isFormValid() {
        if (libelleField.getText().trim().length() <= 0)
            return false;
        if (compteField.getText().trim().length() <= 0)
            return false;
        if (montantField.getText().trim().length() <= 0)
            return false;
        return true;
    }
}
