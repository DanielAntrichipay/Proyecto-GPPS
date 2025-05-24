package frontend;

import backend.IApi;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaPrincipalAlumno {
        public static void abrirVentana(IApi api) {
            try {
                FXMLLoader loader = new FXMLLoader(frontend.Login.class.getResource("/VentanaPrincipalAlumno.fxml"));
                Parent root = loader.load();
                LoginController controller = loader.getController();
                controller.setApi(api);

                Stage stage = new Stage();
                stage.setTitle("GPPS - Ventana Principal");
                stage.setScene(new Scene(root, 300, 250));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

