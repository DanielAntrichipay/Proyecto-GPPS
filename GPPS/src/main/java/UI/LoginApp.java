package frontend;

import backend.Api;
import backend.IApi;
import javafx.application.Application;
import javafx.stage.Stage;

public class LoginApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        IApi api = new Api();
        Login.abrirVentana(api);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
