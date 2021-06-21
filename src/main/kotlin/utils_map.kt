//// =====================================================================================
//// Utilities. Map functions for map-like structures.
////
//// KOTLIN CLI:
//// Compile with
////      kotlinc utils_map.kt
//// Run with
////      kotlin -cp . offtheecliptic.utils.MapUtils
//// =====================================================================================
//// @file:JvmName("MapUtils")  

package offtheecliptic.utils.map

/**
 * Converts a Map with list values to a string
 */
fun <T> convertMapToString(map: Map<String, List<T>>): String {
    val mapAsString = StringBuilder("")
    for (key in map.keys) {
        mapAsString.append(key + "=" + map[key] + ", ")
    }
    mapAsString.delete(mapAsString.length - 2, mapAsString.length).append("")
    return mapAsString.toString()
}

/**
 * Turns a map representation of a model structure into a form that is consistent with how 
 * Kotlin outputs this info from toString.  E.g., for an object such as Constraints, with 
 * multiple fields, this is how it would work:
 *
 *    > asStringifiedObject("Constraints", constraintsMap
 *
 *    > Constraints(typeRef=String, cardinality=ONE, isUnique=false, isIndexed=true, ...)
 */
fun asStringifiedObject(objectName: String, mapToObjectStrings: Map<String,String>): String { 
    val objectString: String = mapToObjectStrings.toString().replace( "{", "(").replace("}", ")" )
    return objectName + objectString
}

/**
 * Merge two maps with custom reduce function.
 *
 * Ref: https://gist.github.com/cy6erGn0m/97ecdc7191364572a94a
 */
fun <K, V> Map<K, V>.mergeReduce(other: Map<K, V>, reduce: (V, V) -> V = { _, b -> b }): Map<K, V> =
            this.toMutableMap().apply { other.forEach { merge(it.key, it.value, reduce) } }
