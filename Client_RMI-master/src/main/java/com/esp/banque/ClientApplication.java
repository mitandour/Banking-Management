package com.esp.banque;

import com.esp.banque.service.AgenceService;
import com.esp.banque.service.ClientService;
import com.esp.banque.tools.Loader;
import com.esp.banque.ui.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
public class ClientApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(ClientApplication.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = Loader.load("main.fxml");
        loader.setControllerFactory(context::getBean);
        Scene scene = new Scene(new StackPane(), 1000, 650) ;
        scene.setRoot(loader.load());

        MainController controller = loader.getController();
        controller.init(primaryStage, context);

        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }
}
