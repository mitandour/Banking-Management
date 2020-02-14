package com.esp.banque.tools;

import javafx.fxml.FXMLLoader;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class Loader {

    public static FXMLLoader load(String path) throws IOException {
        return new FXMLLoader(new ClassPathResource("fxml/"+path).getURL());
    }
}
