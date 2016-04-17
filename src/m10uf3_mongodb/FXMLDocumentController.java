/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m10uf3_mongodb;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jenifer
 */
public class FXMLDocumentController implements Initializable {

    public FXMLDocumentController() {
    }
    static ObservableList<String> hobbies = FXCollections.observableArrayList();
    static ObservableList<String> usuaris = FXCollections.observableArrayList();
    // final ListView lv = new ListView();
    public static String nombresSelecionados;


    @FXML public ListView listview_nom;
    @FXML public ListView listview_hobbies;
    @FXML public Label label_selecciona;
    @FXML private Label label;
    @FXML private TextField text_field;
    @FXML private TextField text_field_hobb;
    @FXML private ComboBox combo;



    @FXML
    private void handleButtonActionUsuaris(ActionEvent event) {
        String text = text_field.getText().toString();
        CargarListaDeUsuarios usuarios = new CargarListaDeUsuarios();
        listview_nom.getItems().clear();
        listview_hobbies.getItems().clear();
        usuaris.removeAll();
        usuarios.filtrarUsuarios(text);
       
    }
    
     @FXML
    private void handleButtonActionHobbies(ActionEvent event) {
         String text = text_field_hobb.getText().toString();
         CargarListaDeHobbies hobby = new CargarListaDeHobbies();
         listview_nom.getItems().clear();
         listview_hobbies.getItems().clear();
         usuaris.removeAll();
         hobbies.removeAll();
         hobby.filtrarHobbies(text);
        
    }
     @FXML
    private void handleButtonActionRecargar(ActionEvent event) {
       try {
        CargarListaDeUsuarios hilo = new CargarListaDeUsuarios();
        listview_hobbies.getItems().clear();
        listview_nom.getItems().clear();
           usuaris.removeAll();
           hobbies.removeAll();
        hilo.run();
       } catch (Exception e) {
           
       }
    }
    @FXML
    private void handleButtonActionBorrarUsuario(ActionEvent event) {
        try {
           BorrarUsuario delete = new BorrarUsuario();
           delete.run(nombresSelecionados);
            usuaris.removeAll();
            hobbies.removeAll();
            listview_hobbies.getItems().clear();
            listview_nom.getItems().clear();
            CargarListaDeUsuarios recargar = new CargarListaDeUsuarios();
            recargar.run();

        } catch (Exception e) {

        }
    }
    //AQUI
     @FXML
    private void handleButtonEditar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editar.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("CRUD");
            stage.setScene(new Scene(root1));  
            stage.show();
            //asda
            //((Node)(event.getSource())).getScene().getWindow().hide();//cerrar la primera ventana
        
    }

    @FXML
    private void handleButtonNuevoHobby (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("añadir.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Añadir Hobby");
        stage.setScene(new Scene(root1));
        stage.show();

        //((Node)(event.getSource())).getScene().getWindow().hide();//cerrar la primera ventana

    }



    @FXML
    private void handleMouseClick(MouseEvent arg0) {
        try {
            listview_hobbies.getItems().clear();
            CargarListaDeHobbies fil2 = new CargarListaDeHobbies();
            //System.out.println("clicked on " + listview_nom.getSelectionModel().getSelectedItem());
            nombresSelecionados = listview_nom.getSelectionModel().getSelectedItem().toString();
            hobbies.removeAll();
            fil2.start();
        } catch (Exception e){

        }


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Task task = new Task<Void>() {

                @Override
                public Void call() throws Exception {
                    combo.getItems().addAll("Ascendente","Descendente");
                    int i = 0;
                    while (true) {
                        final int finalI = i;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                listview_nom.setItems(usuaris);
                                listview_hobbies.setItems(hobbies);
                                   
                            }
                        });
                        i++;
                        Thread.sleep(1000);
                    }
                }
            };

            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
