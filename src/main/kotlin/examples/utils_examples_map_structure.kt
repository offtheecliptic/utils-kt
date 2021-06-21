//// ======================================================================================
//// Utility Functions. Map Structure. Example Program.
////
//// --------------------------------------------------------------------------------------
//// To package with Maven:
////    mvn clean package
//// To run:
////    kotlin -cp target/utils-0.0.3-jar-with-dependencies.jar offtheecliptic.utils.examples.map.structure.MapStructureUtils01
//// To package with Gradle:
////    ./gradlew clean uberjar
//// To run:
////    kotlin -cp build/libs/utils-all-0.0.3.jar offtheecliptic.utils.partial.examples.PartialPartial01 5 25
//// ======================================================================================
@file:JvmName("MapStructureUtils01") 

package offtheecliptic.utils.examples.map.structure 

import offtheecliptic.utils.map.structure.MapStructure
import java.time.*

/**
 * This interface demonstrates an object that is treated essentially as a Map
 * structure, and so can extend the MapStructure interface and thereby obtain
 * some useful functions related to treating objects of this interface as a map.
 */
interface ResourceMetadata: MapStructure {
    val updatedTimestamp: Long?
    val updatingEndUser: String?
    val updatingConsumingApp: String?
    val updatingInitiatingApp: String?
    val createdTimestamp: Long?
    val creatingEndUser: String?
    val creatingConsumingApp: String?
    val creatingInitiatingApp: String?
    override fun asMap(): Map<String,Any?> = mapOf(
        "updatedTimestamp"      to updatedTimestamp, 
        "updatingEndUser"       to updatingEndUser, 
        "updatingConsumingApp"  to updatingConsumingApp, 
        "updatingInitiatingApp" to updatingInitiatingApp, 
        "createdTimestamp"      to createdTimestamp,
        "creatingEndUser"       to creatingEndUser, 
        "creatingConsumingApp"  to creatingConsumingApp, 
        "creatingInitiatingApp" to creatingInitiatingApp
    )
    override fun asStringifiedObject(name: String?) : String { 
        val nom = if (name == null || name.isBlank()) "ResourceMetadata" else name 
        return nom + asStringListOfMapEntries() 
    }
}

/**
 * This object implements the Resourcemetadata interfce, which because it extends
 * the MapStructure interface exposes additional functions for interacting with 
 * the object in a map-like fashion.
 */
data class ResourceMetadataData (
    override val updatedTimestamp: Long? = java.time.Instant.now().toEpochMilli(),
    override val updatingEndUser: String? = null,
    override val updatingConsumingApp: String? = null,
    override val updatingInitiatingApp: String? = null,
    override val createdTimestamp: Long? = java.time.Instant.now().toEpochMilli(),
    override val creatingEndUser: String? = null,
    override val creatingConsumingApp: String? = null,
    override val creatingInitiatingApp: String? = null
) : ResourceMetadata {
    override fun toString() : String { return asStringifiedObject() }
}

fun main() {

    println("\nTesting the map structure interface ${java.time.LocalDateTime.now()}...")

    println("\n------------------------------------------------------------------")
    println("\nTesting the map structure with all defaults...")
    val rmd1: ResourceMetadata = ResourceMetadataData()
    testFunctions(rmd1)
    
    println("\n------------------------------------------------------------------")
    println("\nTesting the map structure with no defaults...")
    val rmd2: ResourceMetadata = ResourceMetadataData(
        java.time.Instant.now().toEpochMilli(),
        "Some UpdatingEndUser",
        "Some UpdatingConsumingApp",
        "Some UpdatingInitiatingApp",
        java.time.Instant.now().toEpochMilli(),
        "Some CreatingEndUser",
        "Some CreatingConsumingApp",
        "Some CreatingInitiatingApp"
    )
    testFunctions(rmd2)

    println("\n------------------------------------------------------------------")
}

fun testFunctions(rmd: ResourceMetadata) {
    println("\nrmd = $rmd")

    println("\nTest the map functions...")

    println("\nasMap()...")
    val rmdAsMap: Map<String,Any?> = rmd.asMap()
    println("  asMap()        = $rmdAsMap")
    println("  asMap().keys   = ${rmdAsMap.keys}")
    println("  asMap().values = ${rmdAsMap.values}")
    
    println("\nasMapWithStringValues()...")
    val rmdAsMapWithStringValues: Map<String,String> = rmd.asMapWithStringValues()
    println("  asMapWithStringValues()        = $rmdAsMapWithStringValues")
    println("  asMapWithStringValues().keys   = ${rmdAsMapWithStringValues.keys}")
    println("  asMapWithStringValues().values = ${rmdAsMapWithStringValues.values}")

    println("\nasMapNoNulls()...")
    val rmdAsMapNoNulls: Map<String,Any> = rmd.asMapNoNulls()
    println("  asMapNoNulls()        = $rmdAsMapNoNulls")
    println("  asMapNoNulls().keys   = ${rmdAsMapNoNulls.keys}")
    println("  asMapNoNulls().values = ${rmdAsMapNoNulls.keys}")

    println("\nasStringListOfMapEntries()...")
    val rmdAsStringListOfMapEntries: String = rmd.asStringListOfMapEntries()
    println("  asStringListOfMapEntries() = $rmdAsStringListOfMapEntries")

    println("\nasStringifiedObject()...")
    val rmdAsStringifiedObjectDefaultArg: String = rmd.asStringifiedObject()
    println("  asStringListOfMapEntries() = $rmdAsStringifiedObjectDefaultArg")
    
    println("\nasStringifiedObject('TestName')...")
    val rmdAsStringifiedObjectWithNameArg: String = rmd.asStringifiedObject("TestName")
    println("  asStringListOfMapEntries('TestName') = $rmdAsStringifiedObjectWithNameArg")
}