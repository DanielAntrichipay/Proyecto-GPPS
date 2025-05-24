package frontend;

import backend.IApi;
import backend.dto.UsuarioDTO;
import commons.UsuarioEnum;
import backend.dto.UsuarioDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    public static final String ERROR_CAMPOS_INCOMPLETOS = "Complete los campos vacios.";
    public static final String ERROR_NO_AUTORIZADO = "Todavía no está autorizado para ingresar a la GPPS";
    public static final String INGRESO_EXITOSO = "Ingreso exitoso.";

    @FXML
    private TextField dniTextField;

    @FXML
    private Button ingresarButton;

    @FXML
    private Button registrarmeButtton;

    private IApi api;

    public void setApi(IApi api) {
        this.api = api;
    }

    @FXML
    void botonIngresarPresionado(ActionEvent event) {
        String dniIngresado = dniTextField.getText();
        try {
            if (dniIngresado == null || dniIngresado.trim().isEmpty()) {
                mostrarMensajeError(ERROR_CAMPOS_INCOMPLETOS);
                return;
            }
            UsuarioDTO usuario = api.iniciarSesion(dniIngresado); //necesito este metodo en api
            if (usuario == null) {
                mostrarMensajeError("Usuario no encontrado.");
                return;
            }
            if (!api.estaHabilitado(usuario)) { //necesito este método en api
                mostrarMensajeError(ERROR_NO_AUTORIZADO);
                return;
            }
            mostrarMensajeExito();
            abrirVentanaPrincipalSegunRol(usuario);
            cerrarVentanaActual();

        } catch (RuntimeException e) {
            mostrarMensajeError(e.getMessage());
        }
    }

    @FXML
    void botonRegistrarmePresionado(ActionEvent event) {

    }


    private void abrirVentanaPrincipalSegunRol(UsuarioDTO usuario) {
        try {
            UsuarioEnum tipo = usuario.getTipo();
            Stage stage = new Stage();
            FXMLLoader loader;
            Parent root;

            switch (tipo) {
                case ESTUDIANTE:
                    loader = new FXMLLoader(getClass().getResource("VentanaPrincipalAlumno.fxml"));
                    root = loader.load();
                    VentanaPrincipalAlumnoController alumnoController = loader.getController();
                    alumnoController.setApi(api);
                    alumnoController.setUsuario(usuario);
                    stage.setTitle("Ventana Principal Alumno");
                    break;

                case ENTIDAD:
                    loader = new FXMLLoader(getClass().getResource("VentanaPrincipalEntidad.fxml"));
                    root = loader.load();
                    VentanaPrincipalEntidadController entidadController = loader.getController();
                    entidadController.setApi(api);
                    entidadController.setUsuario(usuario);
                    stage.setTitle("Ventana Principal Entidad");
                    break;

                default:
                    mostrarMensajeError("Tipo de usuario no soportado: " + tipo);
                    return;
            }
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            mostrarMensajeError("Error al cargar la pantalla.");
        }
    }


    private void cerrarVentanaActual() {
        Stage stage = (Stage) ingresarButton.getScene().getWindow();
        stage.close();
    }

    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarMensajeExito() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inicio de sesion exitoso");
        alert.setHeaderText(null);
        alert.setContentText(LoginController.INGRESO_EXITOSO);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}