package net.thevpc.nuts.toolbox.nstore;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.thevpc.nuts.*;

import java.io.IOException;
import net.thevpc.nuts.util.NMsg;

/**
 * JavaFX App
 */
@NAppDefinition
public class Main extends Application {

    private static String[] appArgs;

    public void init() throws Exception {
        NApp.builder().instance(this)
                        .setNutsArgs("--share")
                                .args(appArgs)
                .run();
        switch (NApp.of().getMode()) {
            case INSTALL:
            case UNINSTALL:
            case UPDATE:
            case AUTO_COMPLETE: {
                throw new NExecutionException(NMsg.ofC("exit"), 0);
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = javafx.fxml.FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene scene = new Scene(root, 800, 600);
            stage.setTitle("Nuts Store - 0.8.6.0");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        appArgs = args;
        launch();
    }

    @NAppRunner
    public void run() {

    }
}
