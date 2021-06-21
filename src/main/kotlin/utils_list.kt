//// =====================================================================================
//// Utilities. List Operations.
////
//// KOTLIN CLI:
//// Compile with
////      kotlinc utils_map.kt
//// Run with
////      kotlin -cp . offtheecliptic.utils.ListUtils
//// =====================================================================================
//// @file:JvmName("ListUtils")  

package offtheecliptic.utils.list

/**
 * This extension function effectively joins two lists together based on some 
 * criteria defined by the predicate.  From https://stackoverflow.com/a/51782849.
 * 
 * Example:
 *    data class A(val field_a1: String, val field_a2: String)
 *    data class B(val field_b1: String, val field_b2: String, val field_aref: String)
 *    val listA  = listOf( A(), A(), ...); val listB = listOf( B(), B(), ...)
 *    val joinAB = listA.intersect(listB) { a, b -> a.field_a1 == b.field_aref }
 */
fun <T, U> List<T>.intersect(uList: List<U>, filterPredicate : (T, U) -> Boolean) 
    = filter { m -> uList.any { filterPredicate(m, it)} }

