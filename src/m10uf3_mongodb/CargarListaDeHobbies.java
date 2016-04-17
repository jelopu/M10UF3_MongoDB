/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m10uf3_mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.scene.control.Alert;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.*;

/**
 *
 * @author mrtwe_000
 */
public class CargarListaDeHobbies extends Thread{
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    String tmpCursor;
    String[] tmp2Cursor;

    @Override
    public void run() {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("bigdata");
            MongoCollection<Document> collection = db.getCollection("usuaris");
            String tmpNombreSelecionado = FXMLDocumentController.nombresSelecionados;
            tmpNombreSelecionado = tmpNombreSelecionado.replaceAll("\\s", "");

            BasicDBObject tmpBuscar= new BasicDBObject();
            tmpBuscar.put("nom",tmpNombreSelecionado);
            MongoCursor<Document> cursor = collection.find(tmpBuscar).iterator();
            
            try {
                tmpCursor = cursor.next().toJson();
                tmp2Cursor = tmpCursor.split("\\[");
                tmpCursor = tmp2Cursor[1];
                tmpCursor = tmpCursor.replaceAll("]", "");
                tmpCursor = tmpCursor.replaceAll("}", "");
                tmpCursor = tmpCursor.replaceAll("\"", "");
                tmpCursor = tmpCursor.replaceAll("\\s", "");
                tmp2Cursor = tmpCursor.split(",");
                addAll(FXMLDocumentController.hobbies,tmp2Cursor);
            } catch (Exception e) {
            } finally{
                cursor.close();
                System.out.println("Cursor cerrado");
            }
            mongoClient.close();
            System.out.println("Base de datos cerrada");
        } catch (Exception e) {

        }
         if(!this.isInterrupted()){
            this.interrupt();
            System.out.println("FIL ATURAT.");
            
        }else{ System.out.println("FIL NO ATURAT.");}
    }
    
    
     public void filtrarHobbies(String text){
         
            System.out.println("Entra hobbies");
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("bigdata");
            MongoCollection<Document> collection = db.getCollection("usuaris");
            /*MongoCursor<Document> cursor = collection.find().iterator();
            FindIterable<Document> iterable = db.getCollection("usuaris").find(eq("hobbis", text));*/
            text = text.replaceAll("\\s","");
            MongoCursor<Document> cursor = collection.find(eq("hobbis", text)).iterator();

            String tmp3Cursor;
            String[] tmp4Cursor;
            ArrayList<String> a = new ArrayList();
            int i = 0;
            if (cursor.hasNext() == true ){
                try {
                    while (cursor.hasNext()) {
                        tmpCursor = cursor.next().toJson();
                        tmp3Cursor = tmpCursor;
                        tmp2Cursor = tmpCursor.split(",");
                        tmp2Cursor = tmp2Cursor[1].split(":");
                        tmpCursor = tmp2Cursor[1];
                        tmpCursor = tmpCursor.replace("\"", "");
                        FXMLDocumentController.usuaris.add(tmpCursor);

/*
                     tmp4Cursor = tmp3Cursor.split("\\[");
                     tmp3Cursor = tmp4Cursor[1];
                     tmp3Cursor = tmp3Cursor.replaceAll("]", "");
                     tmp3Cursor = tmp3Cursor.replaceAll("}", "");
                     tmp3Cursor = tmp3Cursor.replaceAll("\"", "");
                     tmp3Cursor = tmp3Cursor.replaceAll("\\s", "");
                     tmp4Cursor = tmp3Cursor.split(",");

                     for (int i = 0; i < tmp4Cursor.length; i++){
                            a.add(tmp4Cursor[i]);
                     }
                     for (int i = 0; i < a.size(); i++){
                         if (a.get(i)== text){
                             System.out.println(a.get(i));
                         }
                     }*/
                        if (i == 0) {
                            FXMLDocumentController.hobbies.add(text);
                            i++;
                        }


                    }
                }catch (Exception e) {
                }}else{
                this.interrupt();
                CargarListaDeUsuarios reload = new CargarListaDeUsuarios();
                reload.run();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("No hay coincidencias");
                alert.showAndWait();

            }


           /* iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
         });*/
            mongoClient.close();


        }

     catch (Exception e) {

         }
         if(!this.isInterrupted()){
             this.interrupt();
             System.out.println("FIL ATURAT.");

         }else{ System.out.println("FIL NO ATURAT.");

         }


     }

    
    
    
    
    
    }
