package m10uf3_mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by mrtwe_000 on 17/04/2016.
 */
public class AñadirHobbies extends Thread {

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    String tmpCursor;
    String[] tmp2Cursor;

    public void run(String usuari, String hobbie1, String hobbie2, String hobbie3) {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("bigdata");
            MongoCollection<Document> collection = db.getCollection("usuaris");
            List<String> b = new ArrayList<>();
            List<String> a = new ArrayList();
            hobbie1 = hobbie1.replaceAll("\\s","");
            hobbie2 = hobbie2.replaceAll("\\s","");
            hobbie3 = hobbie3.replaceAll("\\s","");
            usuari = usuari.replaceAll("\\s","");
            MongoCursor<Document> cursor = collection.find(eq("nom", usuari)).iterator();
            try {
                String tmp3Cursor;
                String[] tmp4Cursor;

                //int i = 0;
                while (cursor.hasNext()) {
                    tmpCursor = cursor.next().toJson();
                    tmp3Cursor = tmpCursor;
                    tmp2Cursor = tmpCursor.split(",");
                    tmp2Cursor = tmp2Cursor[1].split(":");
                    tmpCursor = tmp2Cursor[1];
                    tmpCursor = tmpCursor.replace("\"", "");
                    FXMLDocumentController.usuaris.add(tmpCursor);


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

                }//asdasd

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
                collection.deleteMany(eq("nom",usuari));
                collection.insertOne(doc);

            }catch(Exception e){
            }mongoClient.close();
        }catch(Exception e){
        }if(!this.isInterrupted()){
            this.interrupt();
            System.out.println("FIL ATURAT.");

        }else{ System.out.println("FIL NO ATURAT.");}

    }
}
