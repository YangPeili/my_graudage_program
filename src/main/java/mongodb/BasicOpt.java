package mongodb;

import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.mongodb.util.JSON;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by yang on 2018/1/21.
 */
public class BasicOpt {
    static final String DATABASE = "final";
    static MongoClient mongoClient = new MongoClient("localhost", 27017);
    static MongoDatabase database = getConection();
    static DB db = getDB();

    public static MongoDatabase getConection() {
        database = mongoClient.getDatabase(DATABASE);
        return database;
    }

    public static DB getDB() {
        return mongoClient.getDB(DATABASE);
    }

    public static void saveFileIntoGFS(String fPath) {
        File f = new File(fPath);
        GridFS gridFS = new GridFS(db);
        try {
            GridFSInputFile gridFSInputFile = gridFS.createFile(f);
            gridFSInputFile.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void inser(String json, String collectionName) {
        MongoCollection<DBObject> coll = database.getCollection(collectionName, DBObject.class);
        DBObject bson = (DBObject) JSON.parse(json);
        coll.insertOne(bson);
    }

    public static void inQuery(List<String> list, String collectionName) {
        MongoCollection<Document> doc = database.getCollection(collectionName);
        long s= System.currentTimeMillis();
        FindIterable<Document> iter = doc.find(Filters.in("version", list)); //doc.find(new Document("version",new Document("$in",list)));
        iter.forEach(new Block<Document>() {
            public void apply(Document document) {
                //System.out.println(document.toJson());
            }
        });
        long e=System.currentTimeMillis();
        System.out.println("in 用时："+(e-s));
    }

    public static void findParentVersion(String version, String modelName, String collectionName){
        MongoCollection<Document> doc = database.getCollection(collectionName);
        long s= System.currentTimeMillis();
        String pVersion;
        while(!version.equals("0")){
            FindIterable<Document> iter = doc.find(Filters.and(Filters.eq("version", version),Filters.eq("modelName",modelName)));
            MongoCursor<Document> cousor=iter.iterator();
            while(cousor.hasNext()){
                pVersion=cousor.next().getString("pVersion");
                System.out.println(pVersion);
                version=pVersion;
            }
        }
        long e= System.currentTimeMillis();
        System.out.println("p 用时："+(e-s));
    }
}
