/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m10uf3_mongodb;

import java.awt.*;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jenifer
 */
public class EditarController implements Initializable {

    @FXML public Button btnGuardar;
    @FXML public Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void handleButtonActionGuardar () {

    }
    @FXML
    public void handleButtonActionCancelar () {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
