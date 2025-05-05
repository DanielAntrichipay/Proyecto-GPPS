import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AltaDetalleProyectoController {

    public static final String MENSAJE_ERROR = "Error al guardar el detalle del proyecto: ";
    public static final String MENSAJE_EXITO = "El detalle del proyecto se ha guardado correctamente.";
    public static final String ERROR = "Error";
    public static final String EXITO = "Éxito";
    @FXML
    private Button aceptarDetalleProyectoBoton;

    @FXML
    private TextArea aptitudesProyectoAreaTexto;

    @FXML
    private TextArea areasInteresAreaDeTexto;

    @FXML
    private Button cancelarDetallePryoyectoBoton;

    @FXML
    private TextArea descripcionAreaDeTexto;

    @FXML
    private TextArea objetivosProyectoAreaTexto;

    @FXML
    private TextField tituloCampoDeTexto;

    @FXML
    private Label tituloDelProyecto;

    private static void mostrarMensajeError(String mensaje, RuntimeException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText(null);
        alert.setContentText(MENSAJE_ERROR);
        Console.log(e.getMessage());
        alert.showAndWait();
    }

    private static void mostrarMensajeExito(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(EXITO);
        alert.setHeaderText(null);
        alert.setContentText(MENSAJE_EXITO);
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
            api.cargarDetalleProyecto(detalleProyectoDTO);
            // Mostrar mensaje de éxito
            mostrarMensajeExito(MENSAJE_EXITO);
        } catch (RuntimeException e) {
            mostrarMensajeError(MENSAJE_ERROR, e);
        }
    }

    @FXML
    void botonCancelarPresionado(ActionEvent event) {
        // Cerrar la ventana actual
        Stage stage = (Stage) cancelarDetallePryoyectoBoton.getScene().getWindow();
        stage.close();

    }

}
