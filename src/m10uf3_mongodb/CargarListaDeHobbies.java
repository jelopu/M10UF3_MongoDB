/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m10uf3_mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author mrtwe_000
 */
public class CargarListaDeHobbies extends Thread{
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;
    
    @Override
    public void run() {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("bigdata");
            MongoCollection<Document> collection = db.getCollection("usuaris");
            MongoCursor<Document> cursor = collection.find().iterator();
            
            String tmpNombreSelecionado = FXMLDocumentController.nombresSelecionados;
            
            
            
            
            
            try {
                String tmpCursor;
                String[] tmp2Cursor;
                
                    while (cursor.hasNext()) {
                    tmpCursor = cursor.next().toJson();
                    tmp2Cursor = tmpCursor.split(",");
                    tmp2Cursor = tmp2Cursor[1].split(":");
                    tmpCursor = tmp2Cursor[1];
                    tmpCursor = tmpCursor.replace("\"", "");
                   
                    // FXMLDocumentController.hobbies.add(tmpCursor);
                }
                
            } catch (Exception e) {
            } finally{
                cursor.close();
            }
            mongoClient.close();
        } catch (Exception e) {

        }
         if(!this.isInterrupted()){
            this.interrupt();
            System.out.println("FIL ATURAT.");
            
        }else{ System.out.println("FIL NO ATURAT.");}
    }
    }
