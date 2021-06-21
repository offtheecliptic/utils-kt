//// ======================================================================================
//// Utility Functions.  Partial Application.
////
//// These utilities enable 'partial application', where a function of N arguments can
//// be turned into a function of N-m arguments by supplying m of the aregument values.
////
//// These functions are derived from reference [1]. The original partial'd out the second
//// argument, but these versions partial out the first, like clojure does.
////
//// References:
////    [1] https://stackoverflow.com/a/52711758 
//// ======================================================================================
//// @file:JvmName("PartialUtils") 

package offtheecliptic.utils.partial

/**
 * This creates a partial for a 2-arg function, turning it into a 1-arg function.
 * The type of the two arguments is the same for this function.
 * 
 * Example: fun add(a: Int, b:Int): Int; val add1: (Int) -> Int = partial2(::add, 1)
 *
 * Usage: 
 *    fun <T> f2(a:T, b:T): T                       // 2-arg fn
 *    val f1: (T) -> T = partial2(aValue, f2)       // Turns 2-arg fn into 1-arg fn
 *    val x: T = f1(bValue)                         // Invoke the partial'd fn
 */
fun <D> partial21(a: D, f: (a:D, b:D) -> D): (b:D) -> D {
    return { b: D -> f(a, b)}
}

/**
 * This creates a partial for a 2-arg function, turning it into a 1-arg function.
 * This differs from partial2 in that the type of the 1st argument (the one being 
 * partial'd) is different than the type of the second one (the one that remains 
 * after the partial application).
 * 
 * Example: 
 *    fun quadratic(p: List<Int>, x: Int): Int { return p[0] + p[1]*x + p[2]*x*x }
 *    val quadWithParams: (Int) -> Int = partial22(::quadratic, listOf(2,3,4))
 *    val y: Int = quadWithParams(10)  ==> 432
  */
fun <A,B> partial22(a: A, f: (a:A, b: B) -> B): (b: B) -> B {
    return { b: B -> f(a, b)}
}
