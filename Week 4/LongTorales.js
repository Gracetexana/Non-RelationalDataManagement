connection =  new Mongo("localhost:27017");
db = connection.getDB("SampleSocial")
tweetsCollection = db.getCollection("Tweets");

//clear previous runs
tweetsCollection.deleteMany({id: 20200916});

//problem 1
tweet = ({
    id:20200916,
    fromUser:"Grace Long Torales",
    text:"This is a test of insert",
    cnt:1
});

tweetsCollection.insertOne(tweet);

//problem 2
result = tweetsCollection.find({id: 20200916});
printjson(result);

//problem 3
tweetsCollection.updateOne({id: 20200916}, {$set: {text: "This is the updated text"}});

//problem 4
result = tweetsCollection.findOne({id: 20200916});
print(result.id);
print(result.text);

//problem 5
tweetsCollection.updateOne({id: 20200916}, {$inc: {cnt: 1}});

//problem 6
result = tweetsCollection.find({id: 20200916});
printjson(result);

//problem 7
cursor = tweetsCollection.find({$and: [{id: {$gt: 129744}}, {id: {$lt: 129757}}]});

while (cursor.hasNext()){
    doc = cursor.next();
    print(doc.text);
    print();
}

//problem 8
tweetsCollection.deleteOne({id: 20200916});
result = tweetsCollection.find({id: 20200916});
printjson(result);