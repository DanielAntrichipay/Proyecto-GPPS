package frontend;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AltaDetalleProyecto extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AltaDetalleProyecto.fxml"));
        Scene scene = new Scene(root); //Adjunta la referencia al nodo raiz (layer GridPage) de la gui a la escena
        stage.setTitle("Alta Detalle Proyecto"); // Titulo de la ventana-escenario
        stage.setScene(scene); // Asigna la escena a la ventana (stage)
        stage.show();
    }
    public static void main(String[] args) {
        //Crea un objeto AltaDetalleProyecto y llama a start
        launch(args);
    }

}
