conn = new Mongo("localhost:27017");
db = conn.getDB("College2");

sections = db.getCollection("Sections");
students = db.getCollection("Students")

// sections
iste12301 = new ObjectId()
doc = {
    _id: iste12301,
    sectionID: "ISTE12301", 
    title: "My Database Course", 
    creditHours: 3, 
    room: "GOL-2650"
}
sections.insertOne(doc)

iste23401 = new ObjectId()
doc = {
    _id: iste23401,
    sectionID: "ISTE23401", 
    title: "My Other Database Course", 
    creditHours: 4, 
    room: "GOL-2620"
}
sections.insertOne(doc)

// students
doc = {
    uid: 123456789, 
    firstName: "Ivona", 
    lastName: "Bok", 
    year: 3,
    mySections: iste12301
}
students.insertOne(doc)

doc = {
    uid: 234567890, 
    firstName: "Ivan", 
    lastName: "Smith", 
    year: 4,
    mySections: [iste12301, iste23401]
}
students.insertOne(doc)

doc = {
    uid: 345678901, 
    firstName: "Sally", 
    lastName: "Struthers", 
    year: 3,
    mySections: iste23401
}
students.insertOne(doc)