package m10uf3_mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static m10uf3_mongodb.FXMLDocumentController.usuaris;
import org.bson.Document;

public class CargarListaDeUsuarios {

    FXMLDocumentController controlador = new FXMLDocumentController();
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    public void run() {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("bigdata");
            MongoCollection<Document> collection = db.getCollection("usuaris");
            MongoCursor<Document> cursor = collection.find().iterator();
            try {
                String tmpCursor;
                String[] tmp2Cursor;
                
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
    }
}
