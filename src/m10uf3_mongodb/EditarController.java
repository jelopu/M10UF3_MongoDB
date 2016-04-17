/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m10uf3_mongodb;

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

  
    public EditarController() {
    }
    
    @FXML public Button btnGuardar;
    @FXML public Button btnCancelar;
    @FXML public TextField field_usuari;
    @FXML public TextField field_hobbie1;
    @FXML public TextField field_hobbie2;
    @FXML public TextField field_hobbie3;
    

    /**
     * Initializes the controller class.
     */
    @FXML
    public void handleButtonActionGuardar () {
        String usuari = field_usuari.getText().toString();
        String hobbie1 = field_hobbie1.getText().toString();
        String hobbie2 = field_hobbie2.getText().toString();
        String hobbie3 = field_hobbie3.getText().toString();
        System.out.println(usuari);
        System.out.println(hobbie1);
        AñadirDocumento doc = new AñadirDocumento();
        doc.run(usuari, hobbie1, hobbie2, hobbie3);
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
        CargarListaDeUsuarios recargar = new CargarListaDeUsuarios();
        FXMLDocumentController.hobbies.removeAll();
        FXMLDocumentController.usuaris.removeAll();
        recargar.run();


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
