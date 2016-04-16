/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m10uf3_mongodb;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

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



    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void handleMouseClick(MouseEvent arg0) {
        try {
            CargarListaDeHobbies fil2 = new CargarListaDeHobbies();
            System.out.println("clicked on " + listview_nom.getSelectionModel().getSelectedItem());
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
