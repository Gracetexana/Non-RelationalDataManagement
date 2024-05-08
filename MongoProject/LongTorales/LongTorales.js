conn = new Mongo("localhost:27017");
db = conn.getDB("Fandango");
coll = db.getCollection("LongTorales");

// Cinemark Tinseltown Rochester and IMAX
meanGirls = {
    title: "Mean Girls",
    releaseYear: 2024,
    maturityRating: "PG-13",
    length: "1:58",
    genre: [
        "comedy", 
        "music/performing arts"
    ],
    showings: [
        {showtime: "10:45", amenities: ["Luxury Loungers"]},
        {showtime: "13:35", amenities: ["Luxury Loungers"]},
        {showtime: "16:25", amenities: ["Luxury Loungers"]},
        {showtime: "18:25", amenities: ["Luxury Loungers"]},
        {showtime: "19:15", amenities: ["Luxury Loungers"]},
        {showtime: "22:05", amenities: ["Luxury Loungers"]},
        {showtime: "10:45", amenities: ["D-Box", "Luxury Loungers"]},
        {showtime: "13:35", amenities: ["D-Box", "Luxury Loungers"]},
        {showtime: "16:25", amenities: ["D-Box", "Luxury Loungers"]},
        {showtime: "19:15", amenities: ["D-Box", "Luxury Loungers"]},
        {showtime: "22:05", amenities: ["D-Box", "Luxury Loungers"]}
    ]
}

beekeeper = {
    title: "The Beekeeper",
    releaseYear: 2024,
    maturityRating: "R",
    length: "1:45",
    genre: [
        "action/adventure", 
        "mystery and thriller", 
        "suspense/thriller"
    ],
    showings: [
        {showtime: "16:00", amenities: ["IMAX"]},
        {showtime: "18:50", amenities: ["IMAX"]},
        {showtime: "11:00", amenities: ["D-Box"]},
        {showtime: "13:55", amenities: ["D-Box"]},
        {showtime: "16:40", amenities: ["D-Box"]},
        {showtime: "19:25", amenities: ["D-Box"]},
        {showtime: "22:15", amenities: ["D-Box"]},
        {showtime: "11:00", amenities: ["Luxury Loungers"]},
        {showtime: "13:55", amenities: ["Luxury Loungers"]},
        {showtime: "16:40", amenities: ["Luxury Loungers"]},
        {showtime: "19:25", amenities: ["Luxury Loungers"]},
        {showtime: "22:15", amenities: ["Luxury Loungers"]}
    ]
}

barbie = {
    title: "Barbie",
    releaseYear: 2023,
    maturityRating: "PG-13",
    length: "1:54",
    genre: ["comedy"],
    showings: [
        {showtime: "15:25", amenities: ["Luxury Loungers"]},
        {showtime: "22:45", amenities: ["Luxury Loungers"]}
    ]
}

doc = {
    name: "Cinemark Tinseltown Rochester and IMAX",
    address: {
        number: 2291,
        street: "Buffalo Road",
        city: "Rochester",
        state: "NY",
        zipcode: 14624
    },
    roster: [meanGirls, beekeeper, barbie]
}
coll.insertOne(doc)


// Apple Cinemas Pittsford
fighter = {
    title: "Fighter",
    releaseYear: 2024,
    maturityRating: "N/A",
    length: "2:40",
    genre: [
        "action/adventure", 
        "mystery and thriller", 
        "suspense/thriller"
    ],
    showings: [
        {showtime: "17:00", amenities: ["recliners", "Hindi language"]},
        {showtime: "20:40", amenities: ["recliners", "Hindi language"]}
    ]
}

meanGirls = {
    title: "Mean Girls",
    releaseYear: 2024,
    maturityRating: "PG-13",
    length: "1:58",
    genre: [
        "comedy", 
        "music/performing arts"
    ],
    showings: [
        {showtime: "13:00", amenities: ["Dolby Atmos", "recliners"]},
        {showtime: "16:00", amenities: ["Dolby Atmos", "recliners"]},
        {showtime: "19:00", amenities: ["Dolby Atmos", "recliners"]},
        {showtime: "21:40", amenities: ["Dolby Atmos", "recliners"]},
        {showtime: "18:00", amenities: ["recliners"]},
    ]
}

wonka = {
    title: "Wonka",
    releaseYear: 2023,
    maturityRating: "PG",
    length: "1:56",
    genre: ["comedy"],
    showings: [
        {showtime: "12:30", amenities: ["recliners"]},
        {showtime: "15:30", amenities: ["recliners"]},
        {showtime: "18:30", amenities: ["recliners"]},
        {showtime: "21:10", amenities: ["recliners"]}
    ]
}

doc = {
    name: "Apple Cinemas Pittsford",
    address: {
        number: 3349,
        street: "Monroe Avenue",
        city: "Pittsford",
        state: "NY",
        zipcode: 14618
    },
    roster: [fighter, meanGirls, wonka]
}
coll.insertOne(doc)

// Regal Eastview Mall
carmen = {
    title: "The Metropolitan Opera: Carmen",
    releaseYear: 2024,
    maturityRating: "N/A",
    length: "4:00",
    genre: [
        "concert/special events", 
        "drama", 
        "music/performing arts"
    ],
    showings: [
        {showtime: "12:55", amenities: ["recliners", "No passes", "The Met Opera"]}
    ]
}

godzilla = {
    title: "Godzilla Minus One Minus Color",
    releaseYear: 2024,
    maturityRating: "PG-13",
    length: "2:05",
    genre: [
        "action/adventure", 
        "sci-fi/fantasy"
    ],
    showings: [
        {showtime: "18:50", amenities: ["recliners", "English subtitles", "Japanese language"]}
    ]
}

nightSwim = {
    title: "Night Swim",
    releaseYear: 2024,
    maturityRating: "PG-13",
    length: "1:38",
    genre: [
        "horror", 
        "mystery and thriller",
        "suspense/thriller"
    ],
    showings: [
        {showtime: "21:10", amenities: ["recliners"]}
    ]
}

doc = {
    name: "Regal Eastview Mall",
    address: {
        number: 70,
        street: "Eastview Mall Drive",
        city: "Victor",
        state: "NY",
        zipcode: 14564
    },
    roster: [carmen, godzilla, nightSwim]
}
coll.insertOne(doc)