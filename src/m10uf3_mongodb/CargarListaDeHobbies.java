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
import org.bson.Document;

import java.util.Collections;

import static java.util.Collections.*;

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
            String tmpNombreSelecionado = FXMLDocumentController.nombresSelecionados;
            tmpNombreSelecionado = tmpNombreSelecionado.replaceAll("\\s", "");

            BasicDBObject tmpBuscar= new BasicDBObject();
            tmpBuscar.put("nom",tmpNombreSelecionado);
            MongoCursor<Document> cursor = collection.find(tmpBuscar).iterator();
            
            try {
                String tmpCursor;
                String[] tmp2Cursor;
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
    
    
     public void filtrarHobbies(String user){
        
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("bigdata");
            MongoCollection<Document> collection = db.getCollection("usuaris");
            MongoCursor<Document> cursor = collection.find().iterator();
            FindIterable<Document> iterable = db.getCollection("usuaris").find(eq("hobbis", user));
            
            
            iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
         });
    
    }
    
    
    
    
    }
