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

/**
 *
 * @author jenifer
 */
public class FXMLDocumentController implements Initializable {

    static ObservableList<String> usuaris = FXCollections.observableArrayList();
    @FXML
    private Label label;
    @FXML
    private ListView listViewIzquierda;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
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
                                listViewIzquierda.setItems(usuaris);
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