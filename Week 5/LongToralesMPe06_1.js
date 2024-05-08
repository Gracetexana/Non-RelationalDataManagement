conn = new Mongo("localhost:27017");
db = conn.getDB("SampleSocial");
coll = db.getCollection("Tweets");

cursor = coll.find().limit(5);
while (cursor.hasNext()){
    printjson(cursor.next());
}