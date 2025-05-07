import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class AltaDetalleProyectoController implements Initializable {
    public static final String MENSAJE_ERROR_GUARDAR_PROYECTO = "Error al guardar el proyecto: ";
    public static final String MENSAJE_EXITO_GUARDAR_PROYECTO = "El proyecto se ha guardado correctamente.";
    public static final String ERROR = "Error";
    public static final String EXITO = "Éxito";
    private UsuarioDTO entidad;
    private UsuarioDTO usuarioSistema;
    private List<UsuarioDTO> entidades = new ArrayList<>();
    private List<PlanDeTrabajoDTO> planesDeTrabajoDTO = new ArrayList<>();


    @FXML
    private Button aceptarDetalleProyectoBoton;

    @FXML
    private Button agregarPlanDeTrabajoBoton;

    @FXML
    private TextArea aptitudesProyectoAreaTexto;

    @FXML
    private TextArea areasInteresAreaDeTexto;

    @FXML
    private Button cancelarDetallePryoyectoBoton;

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
            ProyectoDTO proyectoDTO = new ProyectoDTO(detalleProyectoDTO, entidad, false, planesDeTrabajoDTO);
            api.cargarProyecto(proyectoDTO);
            // Mostrar mensaje de éxito
            mostrarMensajeExito(MENSAJE_EXITO_GUARDAR_PROYECTO);
        } catch (RuntimeException e) {
            mostrarMensajeError(MENSAJE_ERROR_GUARDAR_PROYECTO);
        }

    }

    @FXML
    void botonCancelarPresionado(ActionEvent event) {
        Stage stage = (Stage) cancelarDetallePryoyectoBoton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void botonPlanDeTrabajoPresionado(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AltaPlanDeTrabajo.fxml"));
            PlanDeTrabajoController controller = loader.getController();
            controller.setUsuario(usuarioDTO);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Alta Plan de Trabajo");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            mostrarMensajeError("Error al cargar la ventana");
        }
    }


    @FXML
    void entidadComboBoxElegida(ActionEvent event) {
        //Obtener la entidad seleccionada
        String entidadSeleccionada = entidadesComboBox.getSelectionModel().getSelectedItem();
        entidad = api.obtenerEntidadPorNombre(entidadSeleccionada);
    }


    public void setUsuario(UsuarioDTO usuario) {
        this.usuarioSistema = usuario;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            entidad = api.obtenerEntidades();
            for (UsuarioDTO entidad : entidades) {
                entidadesComboBox.getItems().add(entidad.getNombre());
            }
        } catch (RuntimeException e) {
            mostrarMensajeError("Error al cargar las entidades");
        }

    }
}
