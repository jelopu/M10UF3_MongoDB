/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m10uf3_mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static java.util.Collections.addAll;

import javafx.scene.control.Alert;
import org.bson.Document;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jenifer
 */
public class AñadirDocumento extends Thread {
    
    
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;


     public void run(String usuari,String hobbie1,String hobbie2,String hobbie3) {

         try {
            //MongoCredential credential = MongoCredential.createCredential(userName, database, password);
            //MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));
            System.out.println("entra");
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("bigdata");
            MongoCollection<Document> collection = db.getCollection("usuaris");

             try {
                 List<String> a = new ArrayList<>();
                 hobbie1 = hobbie1.replaceAll("\\s","");
                 hobbie2 = hobbie2.replaceAll("\\s","");
                 hobbie3 = hobbie3.replaceAll("\\s","");
                 usuari = usuari.replaceAll("\\s","");

                 if (usuari.isEmpty() == true){
                     Alert alert = new Alert(Alert.AlertType.WARNING);
                     alert.setTitle("");
                     alert.setHeaderText(null);
                     alert.setContentText("No has escrito nada en usuario");
                     alert.showAndWait();
                     return;
                 }

                 if (hobbie1.isEmpty() == false){
                     a.add(hobbie1);
                 }
                 if (hobbie2.isEmpty() == false){
                     a.add(hobbie2);
                 }
                 if (hobbie3.isEmpty() == false){
                     a.add(hobbie3);
                 }


                 Document doc;
                 doc = new Document("nom", usuari).append("hobbis", a);

                 collection.insertOne(doc);
             } catch (Exception e){

             }
                mongoClient.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " +e.getMessage());

        }

         if(!this.isInterrupted()){
             this.interrupt();
             System.out.println("FIL ATURAT.");

         }else{ System.out.println("FIL NO ATURAT.");}

    }
            
           
    
}

