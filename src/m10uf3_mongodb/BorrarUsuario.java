package m10uf3_mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by mrtwe_000 on 17/04/2016.
 */
public class BorrarUsuario extends Thread {
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    public void run(String usuari) {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("bigdata");
            MongoCollection<Document> collection = db.getCollection("usuaris");
            usuari = usuari.replaceAll("\\s","");

            try {
                collection.deleteMany(eq("nom",usuari));
            }catch (Exception e){
            }mongoClient.close();
        }catch (Exception e){
        }
        if(!this.isInterrupted()){
            this.interrupt();
            System.out.println("FIL ATURAT.");

        }else{ System.out.println("FIL NO ATURAT.");}
    }


}
