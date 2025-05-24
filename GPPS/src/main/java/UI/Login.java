package frontend;

import backend.IApi;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class Login {

    public static void abrirVentana(IApi api) {
        try {
            FXMLLoader loader = new FXMLLoader(Login.class.getResource("/Login.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            controller.setApi(api);

            Stage stage = new Stage();
            stage.setTitle("GPPS - Inicio de sesión");
            stage.setScene(new Scene(root, 300, 250));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
