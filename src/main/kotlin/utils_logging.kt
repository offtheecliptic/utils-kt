//// =====================================================================================
//// Utilities. Logging.
////
//// KOTLIN CLI:
//// Compile with
////      kotlinc utils_logging.kt
//// Run with
////      kotlin -cp . offtheecliptic.utils.LoggingUtils
//// =====================================================================================
//// @file:JvmName("LoggingUtils")  

package offtheecliptic.utils.logging

/// --------------------------------------------------------------------------------------
/// UTILITY FUNCITONS

/**
 * Function used for logging to the console, with the name of the thread prefixing
 * each message.
 */
fun log2(v: Any) {
    println("[${Thread.currentThread().name}] $v")
}
//fun log2(v: Any) = println("[${Thread.currentThread().name}] $v")
