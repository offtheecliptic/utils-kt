//// =====================================================================================
//// Utility. Stack.
////
//// Defines a generic Stack<> as an alias of MutableList<T>, and supporting the 
//// following functions:
////
////    push(item)  add the item to the stack; increments the stack size by 1
////    pop()       remove the item last pushed onto the stack; stack size decrements 1
////    peek()      returns the item last pushed onto the stack, but does not remove it
////    update(val) updates the item last pushed to the stack with the provided value
////    hasMore()   returns true if there are items on the stack, otherwise false
////
//// References
////   - https://stackoverflow.com/questions/46900048/how-can-i-use-stack-in-kotlin
//// =====================================================================================
//// From directory this file is in, compile with
////    kotlinc -cp . utils_stack.kt
//// From the directory this file is in, run with 
////    kotlin offtheecliptic.utils.StackUtils
//// =====================================================================================
//// @file:JvmName("StackUtils")  

package offtheecliptic.utils.stack

/**
 * Stack as type alias of Mutable List
 */
typealias Stack<T> = MutableList<T>

/**
 * Pushes item onto the stack. This increments the stack size by 1.
 * @param item Item to be pushed
 */
fun <T> Stack<T>.push(item: T) = add(item)

/**
 * Pops (removes and returns) the last item pushed onto the stack.  This
 * decrements the stack size by 1.
 * @return item Last item if [Stack] is not empty, null otherwise
 */
fun <T> Stack<T>.pop(): T? = if (isNotEmpty()) removeAt(lastIndex) else null

/**
 * Peeks (returns) the last item pushed onto the stack. This returns the item,
 * the same as pop(), but unlike pop(), does not decrement the stack size.
 * @return item Last item if [Stack] is not empty, null otherwise
 */
fun <T> Stack<T>.peek(): T? = if (isNotEmpty()) this[lastIndex] else null

/**
 * Updates the value of the item that was last pushed onto the stack.  This does 
 * not change the size of the stack.
 */
fun <T> Stack<T>.update(newVal: T): T? {
    if (isNotEmpty()) {
        this[lastIndex] = newVal
        return this[lastIndex]
    } else {
        //return this[lastIndex]
        return null
    }
}

/**
 * Returns true if the stack has any items in it; otherwise false.
 */
fun <T> Stack<T>.hasMore() = this.count() > 0 

/// --------------------------------------------------------------------------------------

/**
* Simple example program demonstrating use.
 */
fun main() {
    println("\nExecuting StackUtils on ${java.util.Calendar.getInstance().time}")

    val stack1: Stack<Int> = ArrayList<Int>()
    stack1.push(0)
    println("stack1.peek: ${stack1.peek()}")

    val stack: Stack<Int> = ArrayList<Int>()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    //stack.pop()
    println("peek: ${stack.peek()}")
    println("pop: ${stack.pop()}" )
    //val v = stack!!.pop()
    val v = stack.pop()
    println("type of pop: ${v!!::class}")
    println("peek: ${stack.peek()}")
    stack.push(4)
    println(stack)
    stack.update(44)
    println(stack)
    println("peek: ${stack.peek()}")
    println(stack)
}