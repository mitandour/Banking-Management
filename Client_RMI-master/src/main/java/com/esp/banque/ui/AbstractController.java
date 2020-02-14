package com.esp.banque.ui;

import com.esp.banque.service.AgenceService;
import com.esp.banque.service.ClientService;

public abstract class AbstractController {

    protected MainController mainController;

    public void init(MainController mainController) {
        this.mainController = mainController;
    }
}
