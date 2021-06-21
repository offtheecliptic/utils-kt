//// =====================================================================================
//// Utilities. Map functions for map-like structures.
////
//// KOTLIN CLI:
//// Compile with
////      kotlinc utils_map_structure.kt
//// Run with
////      kotlin -cp . offtheecliptic.utils.MapStructureUtils
//// =====================================================================================
//// @file:JvmName("MapStructureUtils")  

package offtheecliptic.utils.map.structure

/**
 * This provides a common root for structures that are essentially maps; i.e.,
 * they consist of a simple set of keys (attributes) and values.  This interface
 * can be extended by such structures in order to get some free functions to access
 * the data as maps.
 */
interface MapStructure {

    fun asMap(): Map<String,Any?>

    fun asMapWithStringValues(): Map<String,String> = 
            asMap()
                .mapValues { it.value.toString() }

    fun asMapNoNulls() : Map<String,Any>  = 
            asMap()
                .mapNotNull { it.value?.let { value -> it.key to value} }
                .toMap()

    fun asStringListOfMapEntries() : String = 
            asMapNoNulls()
                .filterValues(predicateMatchingBooleanTrue())
                .toString()
                .replace( "{", "(" )
                .replace( "}", ")" )

    fun asStringifiedObject(name: String? = "") : String { 
        return name + asStringListOfMapEntries() 
    }
    // fun asStringifiedObject() : String { 
    //     return /* "SomeHardCodedName" + */ asStringListOfMapEntries() 
    // }

    private fun predicateMatchingBooleanTrue(): (Any) -> Boolean = 
            { it !is Boolean || it == true }
}
