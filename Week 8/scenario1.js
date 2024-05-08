conn = new Mongo("localhost:27017");
db = conn.getDB("College1");

scenario1Coll = db.getCollection("OneToFew");

// students
ivona = {
    uid: 123456789, 
    firstName: "Ivona", 
    lastName: "Bok", 
    year: 3
}

ivan = {
    uid: 234567890, 
    firstName: "Ivan", 
    lastName: "Smith", 
    year: 4
}

sally = {
    uid: 345678901, 
    firstName: "Sally", 
    lastName: "Struthers", 
    year: 3
}

// sections
doc = {
    sectionID: "ISTE12301", 
    title: "My Database Course", 
    creditHours: 3, 
    room: "GOL-2650",
    students: [ivona, ivan]
}
scenario1Coll.insertOne(doc)

doc = {
    sectionID: "ISTE23401", 
    title: "My Other Database Course", 
    creditHours: 4, 
    room: "GOL-2620",
    students: [ivan, sally]
}
scenario1Coll.insertOne(doc)