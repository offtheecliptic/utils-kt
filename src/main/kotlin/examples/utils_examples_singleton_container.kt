//// =====================================================================================
//// ExampleSingleton, with JVM Name SingletonContainerUtils01.
////
//// This is test case for the SingletonContainer class, which turns a regular class 
//// into a singleton whne that class references the SingletonContainer through the
//// companion object declaration in that class.
////
//// The SingletonContainer is diffeent than a regular Kotlin singleton, which can 
//// be created using "obj SingletonName { .... }" in that it creates a singleton 
//// from a class that accepts a single argument in its constructor.
////
//// For example, in this ExampleSingleton, the argument is a context of type Context. 
//// This is defined with the following declaration in ExampleSingleton:
////
////    companion object : SingletonContainer<ExampleSingleton, Context>(::ExampleSingleton)
////
//// The singleton can then be referenced as follows:
////
////    val singleton: ExampleSingleton = ExampleSingleton.getInstance(context)
////
//// The singleton's functions, lazy anf thread-safe, are all static and can be invoked 
//// as follows:
//// 
////    ExampleSingleton.getInstance(context).getContext()
////    ExampleSingleton.getInstance(context).getValue(key)
////    ....
//// 
//// --------------------------------------------------------------------------------------
//// MAVEN:
//// To package with Maven:
////    mvn clean package
//// To run with Maven:
////    kotlin -cp target/utils-0.0.3-jar-with-dependencies.jar offtheecliptic.utils.examples.singleton.SingletonContainerUtils01
//// GRADLE:
//// To package with Gradle:
////    ./gradlew clean uberjar
//// To run:
////    kotlin -cp build/libs/utils-all-0.0.3.jar offtheecliptic.utils.examples.singleton.SingletonContainerUtils01
//// Run with
////    kotlin -cp .  offtheecliptic.utils.examples.singleton.SingletonContainerUtils01
//// =====================================================================================
@file:JvmName("SingletonContainerUtils01") 

package offtheecliptic.utils.examples.singleton

import offtheecliptic.utils.singleton.SingletonContainer
import java.util.Properties

/**
 * Interface for the argument that will be passed to the ExampleSingleton class
 * defined below, to test the SingletonContainer class.
 */
interface Context {
    fun asMap(): Map<String, Any>
}

/**
 * Implementation of the interface above, for the argument that will be passed to 
 * the ExampleSingleton class defined below, to test the SingletonContainer class.
 */
data class ExampleContext(
    val initProperties: Properties
) : Context {
    override fun asMap(): Map<String,Any> {
        val propsAsMap = HashMap<String,Any>()
        for (name in initProperties.stringPropertyNames())
            propsAsMap.put(name, initProperties.getProperty(name))  
        return propsAsMap
    }
}

/**
 * This is a regular class that will be turned into a singleton using the 
 * SingletonContainer class, through the companion object declaration in
 * this class.
 *
 * The singleton may now be invoked using the following syntax and its 
 * initialization will be lazy and thread-safe:
 * 
 *      ExampleSingleton.getInstance(context).getContext()
 *      ExampleSingleton.getInstance(context).getValue(key)
 *      ....
 */
class ExampleSingleton private constructor(context: Context) {
    val wellKnownValue: String
    val contextValue: Context
    init {
        // Init using context argument; these are just to prove the context arg went through
        wellKnownValue = context.asMap().getOrDefault("WellKnownValue", 
            "Context map did not contain the key of the well-known value!") as String
        contextValue = context
    }
    fun getContext(): Context { return contextValue }
    fun getValue(key: String): Any { return contextValue.asMap().getOrDefault(key, "Not found!") }
    fun getWellKnownValue(): Any { return wellKnownValue }
    companion object : SingletonContainer<ExampleSingleton, Context>(::ExampleSingleton)
}

/// --------------------------------------------------------------------------------------

/**
 * Test program for SingletonContainer, which when referenced by a class in its 
 * companion object turns that class into a singleton.
 */
fun main(args: Array<String>) {
    println("\nEXECUTION ON: ${java.time.LocalDateTime.now()}")

    val map = mapOf("KeyL"  to "Luv ya lots!",     "KeyB"  to "Love my fam!",
                    "KeyM"  to "You are special!", "KeyD"  to "Miss you!", 
                    "KeyG1" to "Way to go, girl!", "KeyG2" to "You rock, mon!",
                    "WellKnownValue" to "This is the well-known value!")

    if (args.isEmpty()) {
        println("\nPlease enter the command line parameters:")
        println("1) A key in the map to look up...")
        map.forEach(::println)
        return
    }
    println("\nCommand line arguments:")
    for (arg in args) {
        println("  Arg: ${arg}")
    }

    // Define a context to configure the singleton, using the map above
    val props = map.toProperties()    
    val context: Context = ExampleContext(props)

    // Configure the example class to be a singleton, initialized with context
    val example: ExampleSingleton = ExampleSingleton.getInstance(context)

    // Call the methods on the example class as if it were a singleton    
    println("\nExampleSingleton.getContext():\n  ${example.getContext()}")    
    println("\nExampleSingleton.getWellKnownValue():\n  ${example.getWellKnownValue()}")

    println("\nExampleSingleton.getValue(<input arg>)...")
    for (arg in args) {
        println("  ExampleSingleton.getValue(${arg}): ${example.getValue(arg)}")
    }

    println("\nFinito!\n")
}

// Example of using this idiom when a singleton implementation is generated by an 
// external library and the builder requires an argument. Here’s an example using 
// the Room Persistence Library for Android:

// @Database(entities = arrayOf(User::class), version = 1)
// abstract class UsersDatabase : RoomDatabase() {

//     abstract fun userDao(): UserDao

//     companion object : SingletonHolder<UsersDatabase, Context>({
//         Room.databaseBuilder(it.applicationContext,
//                 UsersDatabase::class.java, "Sample.db")
//                 .build()
//     })
// }