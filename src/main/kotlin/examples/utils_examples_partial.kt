//// ======================================================================================
//// Utility Functions.  Partial Application.  Example Program.
////
//// --------------------------------------------------------------------------------------
//// To package with Gradle:
////    ./gradlew clean uberjar
//// To run:
////    kotlin -cp build/libs/utils-all-0.0.3.jar offtheecliptic.utils.examples.partial.PartialPartial01 5 25
//// ======================================================================================
@file:JvmName("PartialUtils01") 

package offtheecliptic.utils.examples.partial

import offtheecliptic.utils.partial.*

/// --------------------------------------------------------------------------------------
/// TEST CLASSES

/**
 * Demomstrates use of extra parameter which is of the same type as the 
 * other parameter.
 */
data class FunctionProcessorWithScalarParam<Data>(
    val argToPartial: Data,
    val nodeFunction2Arg: (Data, Data) -> Data
): (Data) -> Data {
    val nodeFunction1Arg: (Data) -> Data = partial21(argToPartial,nodeFunction2Arg)
    override operator fun invoke(inputData: Data): Data = nodeFunction1Arg(inputData)
}

/**
 * Demomstrates use of extra parameter which is not the same type as the 
 * other parameter.
 */
data class FunctionProcessorWithListParam<Data>(
    val argToPartial: List<Data>,
    val nodeFunction2Arg: (List<Data>, Data) -> Data
): (Data) -> Data {
    val nodeFunction1Arg: (Data) -> Data = partial22(argToPartial,nodeFunction2Arg)
    override operator fun invoke(inputData: Data): Data = nodeFunction1Arg(inputData)
}

/// --------------------------------------------------------------------------------------
/// TEST FUNCTIONS

fun addI(a: Int, b: Int): Int {
    return a + b
}

fun addD(a: Double, b: Double): Double {
    return a + b
}

fun scaledSquare(a: Int, x: Int): Int {
    //println("x = $x")
    val y = a * x * x
    //println("x updated = $y")
    return y
}
fun quadratic(params: List<Int>, x: Int): Int {
    //println("x = $x")
    val y = params[0] + params[1]*x + params[2]*x*x
    //println("a + bx + cx^x = $y")
    return y
}

/// --------------------------------------------------------------------------------------
/// TEST PROGRAM

/**
 * Main program. Takes 2 arguments (optional), uses the first to partial out 
 * the first of 2-argument functions that are tested.
 *
 * Note that the test functions involve integers and division, where the first
 * argument input is the divisor (dividend), so the second number should be a 
 * multiplier of the first. E.g., enter 5 10, or 2 16.
 */
fun main(args: Array<String>) {
    println("\nExecuting PartialUtils01 on ${java.util.Calendar.getInstance().time}")

    var valI1: Int = 5
    var valI2: Int = 25

    if (args.size > 0) { valI1 = args[0].toInt() }
    if (args.size > 1) { valI2 = args[1].toInt() }

    var valD1: Double = valI1.toDouble()
    var valD2: Double = valI2.toDouble()

    // ----------------------
    // Simple 2-Arg Functions
    // ----------------------

    // Turns 2-arg fn into 1-arg fn
    val fdPlusConst: (Double) -> Double = partial21(valD1, ::addD)   

    val xd: Double = fdPlusConst(valD2)

    println("\nadd fn with 1st arg partial'd with $valD1 and called with $valD2 = $xd")

    // Turns 2-arg fn into 1-arg fn
    val fiPlusConst: (Int) -> Int = partial21(valI1, ::addI)   

    val xi: Int = fiPlusConst(valI2)

    println("\nadd fn with 1st arg partial'd with $valI1 and called with $valI2 = $xi")

    // --------------------------------------------------
    // Function Processors with 2-Arg Anonymous Functions
    // --------------------------------------------------

    val fdi = FunctionProcessorWithScalarParam<Int>(valI1, { y, x -> x / y } )
    val fmi = FunctionProcessorWithScalarParam<Int>(valI1, { y, x -> x * y } )
    
    val fdd = FunctionProcessorWithScalarParam<Double>(valD1) { y, x -> x / y }
    val fmd = FunctionProcessorWithScalarParam<Double>(valD1) { y, x -> x * y }

    val di = fdi(valI2)
    println("\nFunctionProcessorWithScalarParam<Int> with 1st arg partial'd with $valI1 and called with $valI2 = $di")
    val mi = fmi(valI2)
    println("\nFunctionProcessorWithScalarParam<Int> with 1st arg partial'd with $valI1 and called with $valI2 = $mi")
    
    val dd = fdd(valD2)
    println("\nFunctionProcessorWithScalarParam<Int> with 1st arg partial'd with $valD1 and called with $valD2 = $dd")
    val md = fmd(valD2)
    println("\nFunctionProcessorWithScalarParam<Int> with 1st arg partial'd with $valD1 and called with $valD2 = $md")

    // ----------------------------------------
    // Function Processors with 2-Arg Functions
    // ----------------------------------------

    val fssi = FunctionProcessorWithScalarParam<Int>(valI1, ::scaledSquare )

    val lqdi = listOf(4*valI1, 2*valI1, 1*valI1)
    val fqdi = FunctionProcessorWithListParam<Int>(lqdi, ::quadratic )

    val ssi = fssi(valI2)
    println("\nFunctionProcessorWithScalarParam<Int> with 1st arg partial'd with $valI1 and called with $valI2 = $ssi")
    
    val qdi = fqdi(valI2)
    println("\nFunctionProcessorWithListParam<Int> with 1st arg partial'd with $lqdi and called with $valI2 = $qdi")

}
