//// =====================================================================================
//// Utilities.  String.
////
//// =====================================================================================
//// @file:JvmName("StringUtils")  

package offtheecliptic.utils.string

/// --------------------------------------------------------------------------------------
/// UTILITY FUNCITONS

// /**
//  * Function used for logging to the console, with the name of the thread prefixing
//  * each message.
//  */
// fun log2(v: Any) = println("[${Thread.currentThread().name}] $v")

/**
 * Utility to generate a randon string of specified length, or 10 characters by
 * default.
 */
object RandomString {
    val ALPHANUM : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    /**
     * Generates a random string of characters and numbers of the specified length.
     */
    fun gen(len: Int = 10): String {
        val randomString = (1..len)
                .map { _ -> kotlin.random.Random.nextInt(0, ALPHANUM.size) }
                .map(ALPHANUM::get)
                .joinToString("")
        return randomString
    }
    fun gen(charPool: List<Char>, len: Int = 10): String {
        val randomString = (1..len)
                .map { _ -> kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
        return randomString
    }
}
