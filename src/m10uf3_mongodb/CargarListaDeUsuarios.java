package m10uf3_mongodb;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Indexes.ascending;
import javafx.scene.control.Alert;
//import static m10uf3_mongodb.FXMLDocumentController.usuaris;
import org.bson.Document;

public class CargarListaDeUsuarios extends Thread {

    public CargarListaDeUsuarios() {
    }

   // FXMLDocumentController controlador = new FXMLDocumentController();
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
            MongoCursor<Document> cursor = collection.find().iterator();
          
            try {
                
                
                    while (cursor.hasNext()) {
                    tmpCursor = cursor.next().toJson();
                    tmp2Cursor = tmpCursor.split(",");
                    tmp2Cursor = tmp2Cursor[1].split(":");
                    tmpCursor = tmp2Cursor[1];
                    tmpCursor = tmpCursor.replace("\"", "");
                    FXMLDocumentController.usuaris.add(tmpCursor);
                    
                   
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
   
    
    public void filtrarUsuarios(String user){
            
             System.out.println("Entra usuaris");
        try{
            
       
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("bigdata");
            MongoCollection<Document> collection = db.getCollection("usuaris");
            //MongoCursor<Document> cursor = collection.find().iterator();
            //FindIterable<Document> iterable = db.getCollection("usuaris").find(eq("nom", user));
              MongoCursor<Document> cursor = collection.find(eq("nom", user)).iterator();
              if (cursor.hasNext() == true ){
                  try {
                  while (cursor.hasNext()) {
                    tmpCursor = cursor.next().toJson();
                    tmp2Cursor = tmpCursor.split(",");
                    tmp2Cursor = tmp2Cursor[1].split(":");
                    tmpCursor = tmp2Cursor[1];
                    tmpCursor = tmpCursor.replace("\"", "");
                    FXMLDocumentController.usuaris.add(tmpCursor);
                
                    } 
                    }catch (Exception e) {
                    }           
              }else{
                  this.interrupt();
                  start();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("No hay coincidencias");
                alert.showAndWait();
                  
              }
              
    mongoClient.close();
    }catch (Exception e) {  
        }
        if(!this.isInterrupted()){
            this.interrupt();
            System.out.println("FIL ATURAT.");
            
        }else{ System.out.println("FIL NO ATURAT.");}
 }
    
    public void ordenarAsc(){

            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("bigdata");
            MongoCollection<Document> collection = db.getCollection("usuaris");
            MongoCursor<Document> cursor = collection.find().iterator();
            FindIterable<Document> iterable = db.getCollection("usuaris").find().sort(ascending("nom", null));
            
            
            iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
         });
        mongoClient.close();
    }
    
    
   
    
    
    }

