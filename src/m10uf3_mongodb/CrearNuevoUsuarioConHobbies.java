package m10uf3_mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by mrtwe_000 on 16/04/2016.
 */
public class CrearNuevoUsuarioConHobbies extends Thread {

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    @Override
    public void run() {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("bigdata");

        } catch (Exception e){

        }

    }
}
