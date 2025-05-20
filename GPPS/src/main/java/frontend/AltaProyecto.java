package frontend;

import backend.dto.UsuarioDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AltaProyecto {
    private static final Logger logger = Logger.getLogger(AltaProyecto.class.getName());


    public static void abrirVentana(IApi api, UsuarioDTO usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(AltaProyecto.class.getResource("/AltaProyecto.fxml"));

            Parent root = loader.load();
            AltaProyectoController controller = loader.getController();
            controller.setApi(api);
            controller.setUsuario(usuario);

            Stage stage = new Stage();
            Scene scene = new Scene(root); //Adjunta la referencia al nodo raiz (layer GridPage) de la gui a la escena
            stage.setTitle("Alta Detalle Proyecto"); // Titulo de la ventana-escenario
            stage.setScene(scene); // Asigna la escena a la ventana (stage)
            stage.show();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

}
