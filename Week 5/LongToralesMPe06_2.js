conn = new Mongo("localhost:27017");
db = conn.getDB("SampleSocial");
coll = db.getCollection("Tweets");

coll.createIndex({loc: "2dsphere"});

docs = coll.getIndexes();
//printjson(docs);

cursor = coll.find({
    loc: {
        $near: {
            $geometry: {
                type: "Point",
                coordinates: [-95.0949, 29.5075]
            },
            $maxDistance: 30000
        }
    }
},
{_id: 0, id: 1, fromUserName: 1, loc: 1}
)

while (cursor.hasNext()){
    printjson(cursor.next());
}