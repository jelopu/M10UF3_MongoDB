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
import org.bson.Document;

/**
 *
 * @author jenifer
 */
public class AñadirDocumento {
    
    
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

            Document doc;
            doc = new Document("nom", usuari).append("hobbis", hobbie1);
                    
            collection.insertOne(doc);

          

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " +e.getMessage());
        }
    }
            
           
    
}

