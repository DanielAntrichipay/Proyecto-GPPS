package frontend;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VentanaPrincipalAlumnoController implements Initializable {

    @FXML private AnchorPane pane1; // Fondo oscuro
    @FXML private AnchorPane pane2; // Menú lateral
    @FXML private AnchorPane panelContenido; // Contenido central
    @FXML private Label drawerImage; // Icono del menú ☰

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane1.setVisible(false);

        TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.3), pane2);
        slideOut.setToX(-220);
        slideOut.play();

        drawerImage.setOnMouseClicked(event -> abrirMenu());

        pane1.setOnMouseClicked(event -> cerrarMenu());
    }

    private void abrirMenu() {
        pane1.setVisible(true);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), pane1);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(0.4);
        fadeIn.play();

        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.3), pane2);
        slideIn.setToX(0);
        slideIn.play();
    }

    private void cerrarMenu() {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), pane1);
        fadeOut.setFromValue(0.4);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> pane1.setVisible(false));
        fadeOut.play();

        TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.3), pane2);
        slideOut.setToX(-220);
        slideOut.play();
    }

    private void cargarVista(String nombreFXML) {
        try {
            Node vista = FXMLLoader.load(getClass().getResource("/vistas/" + nombreFXML + ".fxml"));
            panelContenido.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace(); // podés poner un alert si querés
        }
        cerrarMenu();
    }

    // Métodos para los botones del menú
    @FXML public void handleVerProyectos() {
        cargarVista("VistaMisProyectos");
    }

    @FXML public void handlePostulaciones() {
        cargarVista("VistaPostulaciones");
    }

    @FXML public void handlePlanTrabajo() {
        cargarVista("VistaPlanTrabajo");
    }

    @FXML public void handleActividades() {
        cargarVista("VistaActividades");
    }

    @FXML public void handleInformeParcial() {
        cargarVista("VistaInformeParcial");
    }

    @FXML public void handleInformeFinal() {
        cargarVista("VistaInformeFinal");
    }
}
