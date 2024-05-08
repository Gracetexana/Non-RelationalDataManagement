import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.*;

public class Regex{
    MongoDatabase sampleDB = null;
    MongoClient client = null;
    MongoCollection<Document> collection = null;
    MongoCursor<Document> cursor = null;
    MongoIterable<String> dbList = null;
    MongoIterable<String> collList = null;

    public static void main(String[] args){
        Regex app = new Regex();
        boolean success = app.connections();

        if(success){
            app.searchMongo("text", "\\bgood\\b.*\\bdish\\b", "\\bdish\\b.*\\bgood\\b", false);
            app.closeMongo();
        }
    }

    /**
     * returns true if connections are successful
     * returns false if not
     * @return whether connections are successful or not
     */
    public boolean connections(){
        boolean connSuccess = false;

        // connect to Mongo database
        client = MongoClients.create("mongodb://localhost:27017");

        System.out.println("\nConnection to server completed\n");

        // try-catch to always close database using finally 
        try{
            // list of databases on connection
            dbList = client.listDatabaseNames();
            String dbName = "SampleSocial";

            // database exists? 
            // returns true if database exists
            if(objectExists(dbList, dbName)){
                System.out.println("\nDatabase found\n");
                sampleDB = client.getDatabase(dbName);
                System.out.println("\nConnection to database completed\n");
                connSuccess = true;

            // returns false if database does not exist
            } else {
                System.out.println("\nDATABASE NOT FOUND\n");
                sampleDB = null;
                connSuccess = false;
                closeMongo();
                return connSuccess;
            }

            // collections in the database
            collList = sampleDB.listCollectionNames();
            String colName = "Tweets";

            // collection exists?
            // returns true if collection exists
            if(objectExists(collList, colName)){
                System.out.println("\nCollection found\n");
                collection = sampleDB.getCollection(colName);
                System.out.println("\nCollection connection completed\n");
            
            // returns false if collection does not exist
            } else {
                System.out.println("\nCOLLECTION NOT FOUND\n");
                collection = null;
                connSuccess = false;
                client.close();
                return connSuccess;
            }

        } catch(Exception e){
            e.printStackTrace();
        } 

        return connSuccess;
    }
    
    private void searchMongo(String field, String searchFor, boolean caseSensitive){
        String option = caseSensitive ? "" : "i";

        cursor = collection.find(regex(field, searchFor, option)).iterator();

        int count = 0;

        while(cursor.hasNext()){
            Document d = cursor.next();

            System.out.println(d.getString("text"));

            count ++;
        }

        System.out.println("\nThe count is " + count + "\n");
    }

private void searchMongo(String field, String searchFor, String alsoSearchFor, boolean caseSensitive){
    String option = caseSensitive ? "" : "i";

    cursor = collection.find(or(regex(field, searchFor, option), regex(field, alsoSearchFor, option))).iterator();

    int count = 0;

    while(cursor.hasNext()){
        Document d = cursor.next();

        System.out.println(d.getString("text"));

        count ++;
    }

    System.out.println("\nThe count is " + count + "\n");
}

    /**
     * returns true if object exists
     * returns false if object does not exist
     * @param objectList the list through which to search for objectName
     * @param objectName the name for which to search
     * @return whether or not object exists
     */
    private boolean objectExists(MongoIterable<String> objectList, String objectName) {
        for(String name : objectList){
            if(name.equals(objectName)){
                return true;
            }
        }

        return false;
    }

    private void closeMongo(){
        client.close();
    }
}