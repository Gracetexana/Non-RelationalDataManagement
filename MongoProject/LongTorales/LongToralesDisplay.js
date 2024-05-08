conn = new Mongo("localhost:27017");
db = conn.getDB("Fandango");
theatersColl = db.getCollection("LongTorales");

theatersCursor = theatersColl.find();
while (theatersCursor.hasNext()){
    theater = theatersCursor.next();
    printjson(theater.name);

    add = theater.address;
    printjson(add.number +" "+ add.street +", "+ add.city +", "+ add.zipcode);
    
    movies = Array.from(theater.roster)
    movies.forEach(movie => {
        print();

        printjson("\t"+ movie.title +" ("+ movie.releaseYear +")");
        printjson("\tRated "+ movie.maturityRating +" | Length: "+ movie.length)

        genres = Array.from(movie.genre)
        genrePrint = "";
        genres.forEach(genre => {
            genrePrint += (genre +" | ");
        });
        printjson("\t"+ genrePrint +"\b\b ");

        showings = Array.from(movie.showings);
        showings.forEach(showing => {
            amenities = Array.from(showing.amenities)
            amenitiesPrint = "";
            amenities.forEach(amenity => {
                amenitiesPrint += (amenity +", ");
            });

            printjson("\t\t"+ showing.showtime +" ("+ amenitiesPrint +"\b\b)")
        });

    });

    print();
    print();
}