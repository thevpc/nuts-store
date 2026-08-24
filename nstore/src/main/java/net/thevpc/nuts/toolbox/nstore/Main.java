package net.thevpc.nuts.toolbox.nstore;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import net.thevpc.nuts.Nuts;
import net.thevpc.nuts.app.NApplication;
import net.thevpc.nuts.app.NApp;
import net.thevpc.nuts.app.NAppRun;
import net.thevpc.nuts.command.NExecutionException;
import net.thevpc.nuts.text.NMsg;

/**
 * JavaFX App
 */
@NApp
public class Main extends Application {

    private static String[] appArgs;

    public void init() throws Exception {
        NApplication.builder().instance(this)
                        .nutsArgs("--share")
                                .args(appArgs)
                .run();
        switch (NApplication.of().mode()) {
            case INSTALL:
            case UNINSTALL:
            case UPDATE:
            case COMPLETE: {
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
            stage.setTitle("Nuts Store - "+ Nuts.version()+".0");
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

    @NAppRun
    public void run() {

    }
}
