package UI;
import Modelo.DetalleProyectoDTO;
import Modelo.PlanDeTrabajoDTO;
import Modelo.EntidadDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AltaProyectoController implements Initializable {
    public static final String MENSAJE_EXITO_GUARDAR_PROYECTO = "El proyecto se ha guardado correctamente.";
    public static final String ERROR = "Error";
    public static final String EXITO = "Éxito";
    private EntidadDTO entidadDTO;
    private UsuarioDTO usuarioSistema;
    private List<EntidadDTO> entidades = new ArrayList<>();
    private List<PlanDeTrabajoDTO> planesDeTrabajoDTO = new ArrayList<>();
    private IApi api;


    @FXML
    private Button aceptarDetalleProyectoBoton;

    @FXML
    private Button agregarPlanDeTrabajoBoton;

    @FXML
    private TextArea aptitudesProyectoAreaTexto;

    @FXML
    private TextArea areasInteresAreaDeTexto;

    @FXML
    private Button cancelarProyectoBoton;

    @FXML
    private TextArea descripcionAreaDeTexto;

    @FXML
    private ComboBox<String> entidadesComboBox;

    @FXML
    private TextArea objetivosProyectoAreaTexto;

    @FXML
    private TextField tituloCampoDeTexto;

    @FXML
    private Label tituloDelProyecto;

    private static void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private static void mostrarMensajeExito(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(EXITO);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void botonAceptarPresionado(ActionEvent event) {
        try {
            DetalleProyectoDTO detalleProyectoDTO = new DetalleProyectoDTO(
                    this.tituloCampoDeTexto.getText(),
                    this.descripcionAreaDeTexto.getText(),
                    this.aptitudesProyectoAreaTexto.getText(),
                    this.areasInteresAreaDeTexto.getText(),
                    this.objetivosProyectoAreaTexto.getText()
            );


            api.cargarProyecto(detalleProyectoDTO, entidadDTO, planesDeTrabajoDTO);
            // Mostrar mensaje de éxito
            mostrarMensajeExito(MENSAJE_EXITO_GUARDAR_PROYECTO);
        } catch (RuntimeException e) {
            mostrarMensajeError(e.getMessage());
        }

    }

    @FXML
    void botonCancelarPresionado(ActionEvent event) {
        Stage stage = (Stage) cancelarProyectoBoton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void botonPlanDeTrabajoPresionado(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AltaPlanDeTrabajo.fxml"));
            Parent root = loader.load();
            PlanDeTrabajoController controller = loader.getController();
            controller.setUsuario(usuarioSistema);
            //TODO necesito un método en el controller
            // para obtener
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Alta Plan de Trabajo");
            stage.setScene(scene);
            stage.showAndWait();

            planesDeTrabajoDTO  = controller.obtenerPlanDeTrabajo();

        } catch (IOException e) {
            mostrarMensajeError("Error al cargar la ventana");
        }
    }


    @FXML
    void entidadComboBoxElegida(ActionEvent event) {
        //Obtener la entidad seleccionada
        String nombreEntidad = entidadesComboBox.getSelectionModel().getSelectedItem();
        entidadDTO = api.obtenerEntidadPorNombre(nombreEntidad);
    }


    public void setUsuario(UsuarioDTO usuario) {
        this.usuarioSistema = usuario;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            entidades = api.obtenerEntidades(usuarioSistema);
            for (EntidadDTO entidad : entidades) {
                entidadesComboBox.getItems().add(entidad.nombre());
            }
        } catch (RuntimeException e) {
            mostrarMensajeError(e.getMessage());
        }

    }

    public void setApi(IApi api) {
        this.api = api;
    }
}
