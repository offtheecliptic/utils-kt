//// ======================================================================================
//// Utility Functions.  List Joining.  Example Program.
////
//// --------------------------------------------------------------------------------------
//// To package with Maven:
////    mvn clean package
//// To run with Maven:
////    kotlin -cp target/utils-0.0.3-jar-with-dependencies.jar offtheecliptic.utils.examples.list.ListJoinUtils01
//// To package with Gradle:
////    ./gradlew clean uberjar
//// To run:
////    kotlin -cp build/libs/utils-all-0.0.3.jar offtheecliptic.utils.examples.list.ListJoinUtils01
//// ======================================================================================
@file:JvmName("ListJoinUtils01") 

package offtheecliptic.utils.examples.list

import offtheecliptic.utils.list.*

/// --------------------------------------------------------------------------------------
/// TEST CLASSES FOR EXAMPLE 1

data class A(val a_id: String, val a_field: String)
data class B(val b_id: String, val b_field: String, val b_aref: String)

fun genA(): List<A> {
    val listA  = listOf( 
        A("a11", "a21"), A("a12", "a22"), A("a13", "a23")
    )
    return listA
}
fun genB(): List<B> {
    val listB  = listOf( 
        B("b11", "bx", "a11"), B("b12", "bx", "a11"), B("b13", "bx", "a12"),
        B("b14", "bz", "a11"), B("b15", "bx", "a13"), B("b16", "by", "a12"),
        B("b17", "bz", "a12"), B("b18", "bx", "a11"), B("b19", "by", "a13")
    )
    return listB
}

/// --------------------------------------------------------------------------------------
/// TEST CLASSES FOR EXAMPLE 2

data class Artist(val name: String, val genre: String)
data class Song(val name: String, val album: String, val year: Int, 
                val artist: String, val rating: Double)

fun genArtists(): List<Artist> {
    val artists = listOf(Artist("Michael Buble", "Easy Listening"),
                         Artist("Train", "Pop"), 
                         Artist("Adele", "Pop")
                        )
    return artists
}
fun genSongs(): List<Song> {
    val songs = listOf( Song("Forever Now", "Love", 2018, "Michael Buble", 5.0),
                        Song("When I Fall in Love", "Love", 2018, "Michael Buble", 3.5),
                        Song("Help Me Make it Through the Night", "Love", 2018, "Michael Buble", 4.5),
                        Song("Feeling Good", "It's Time", 2020, "Michael Buble", 4.5),
                        Song("Hello", "25", 2015, "Adele", 4.5),
                        Song("When We Were Young", "25", 2015, "Adele", 5.0),
                        Song("Remedy", "25", 2015, "Adele", 4.5),
                        Song("Water Under The Bridge", "25", 2015, "Adele", 4.0),
                        Song("River Lea", "25", 2015, "Adele", 4.0),
                        Song("Love In The Dark", "25", 2015, "Adele", 4.0),
                        Song("Million Years Ago", "25", 2015, "Adele", 5.0),
                        Song("All I Ask", "25", 2015, "Adele", 4.5),
                        Song("Sweetest Devotion", "25", 2015, "Adele", 3.5),
                        Song("Rumour Has It", "21", 2011, "Adele", 4.0),
                        Song("Turning Tables", "21", 2011, "Adele", 3.5),
                        Song("Don't You Remember", "21", 2011, "Adele", 4.5),
                        Song("Set Fire to the Rain", "21", 2011, "Adele", 3.5),
                        Song("He Won't Go", "21", 2011, "Adele", 3.5),
                        Song("Take It All", "21", 2011, "Adele", 3.5),
                        Song("I'll Be Waiting", "21", 2011, "Adele", 3.5),
                        Song("One And Only", "21", 2011, "Adele", 3.5),
                        Song("Lovesong", "21", 2011, "Adele", 3.5),
                        Song("Someone Like You", "21", 2011, "Adele", 3.5),
                        Song("Make You Feel My Love", "19", 2009, "Adele", 4.0),
                        Song("Hometown Glory", "19", 2009, "Adele", 3.5),
                        Song("Chasing Pavements", "19", 2009, "Adele", 3.5)
                      )
    return songs
}

/// --------------------------------------------------------------------------------------
/// TEST CLASS

fun main() {
    println("\nTest cases for intersect() extension function.")

    println("\n--------------------------------------------------\nEXAMPLE 1...")
    val listA = genA()
    val listB = genB()

    // This basically returns all the listA elements, since there are no additional constraints
    val joinAB = listA.intersect(listB) { 
        a, b -> a.a_id == b.b_aref 
    }
    val joinABwithBx = listA.intersect(listB) { 
        a, b -> a.a_id == b.b_aref && b.b_field == "bx" 
    }

    println("\nList A joined with List B...")
    joinAB.forEach(::println)
    println("\nList A joined with List B with B field set to bx...")
    joinABwithBx.forEach(::println)

    // This basically returns all the listB elements
    val joinBA = listB.intersect(listA) { 
        b, a -> b.b_aref == a.a_id
    }
    val joinBAwithBx = listB.intersect(listA) { 
        b, a -> b.b_aref == a.a_id && b.b_field == "bx" 
    }

    println("\nList B joined with List A...")
    joinBA.forEach(::println)
    println("\nList B joined with List A with B field set to bx...")
    joinBAwithBx.forEach(::println)

    println("\n--------------------------------------------------\nEXAMPLE 2...")
    val artists = genArtists()
    val songs   = genSongs()

    val songsByArtistHighRatings = songs.intersect(artists) {
        song, artist -> song.artist == artist.name && song.rating >= 4
    }
    val songsByAdeleVeryHighRatings = songs.intersect(artists) {
        song, artist -> song.artist == artist.name && song.rating >= 4.5
    }

    println("\nSongs by all artists that are rated 4 or above...")
    songsByArtistHighRatings.forEach(::println)
    println("\nSongs by Adele that are rated 4.5 or above...")
    songsByAdeleVeryHighRatings.forEach(::println)
}