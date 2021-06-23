//// =====================================================================================
//// Utilities.  SingletonContainer.
////
//// This utility can be used to turn a regular class into a singleton.  The class should
//// have exactly one constructor argument, specified through a private constructor.
//// Usage is as follows:
////
////    class ExampleSingleton private constructor(arg: ArgClass) {
////        // local variables
////        init {
////            // Initialize local variables using 'arg' argument
////        }
////        fun doStuff(...): ReturnType { ... }  // This will act like a static function
////        ...                                     // As many functions as you want
////        
////        The class name for the target singleton class must be specified in the generic.
////        // The ArgClass for the target singleton class must be specified in the generic
////        // The argument to SingletonContainer can be a 1-arg fn or ref to the singleton:
////        companion object : SingletonContainer<ExampleSingleton, ArgClass>(::ExampleSingleton)
////    }
////
//// The singleton may now be invoked using the following syntax; its initialization 
//// will be lazy and thread-safe:
////
////        ExampleSingleton.getInstance(context).doStuff()
//// 
//// Reference:
////   https://bladecoder.medium.com/kotlin-singletons-with-argument-194ef06edd9e
//// -------------------------------------------------------------------------------------
//// KOTLIN CLI:
//// Compile with
////    kotlinc -cp . SingletonContainer.kt
//// Run with
////    kotlin -cp . offtheecliptic.singleton.SingletonContainer
//// =====================================================================================
package offtheecliptic.utils.singleton

open class SingletonContainer<out T: Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(arg: A): T {
        val instanceCheck1 = instance
        if (instanceCheck1 != null) {
            return instanceCheck1
        }

        return synchronized(this) {
            val instanceCheck2 = instance
            if (instanceCheck2 != null) {
                instanceCheck2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}