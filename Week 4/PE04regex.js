connection =  new Mongo("localhost:27017");
db = connection.getDB("SampleSocial")
tweetsCollection = db.getCollection("Tweets");

//problem 1
cursor = tweetsCollection.find({$or: [{text: /\bdish\b.*\bgood\b/i}, {text: /\bgood\b.*\bdish\b/i}]});

print(cursor.count());

while (cursor.hasNext()){
    doc = cursor.next();
    print(doc.text);
    print();
}

//problem 2
cursor = tweetsCollection.find({$and: [{text: /\bdish\b/i}, {text: {$not: /\bgood\b/i}}]});

print(cursor.count());

while (cursor.hasNext()){
    doc = cursor.next();
    print(doc.text);
    print();
}