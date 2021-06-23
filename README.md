# Library Utils

## Introduction

This is a collection of utilities, each in its own sub-package under offtheecliptic.utils.

## Library Specification

group:      offtheecliptic
artifact:   utils
version:    0.0.3

## Contents

The following utilities are provided:

* list           functions that operate on lists
* logging        functions for logging to console
* map            functions that operate on maps
* map.structure  interface that adds functions to objects that can be represented as maps
* partial        functions that allow partial application of functions
* singleton      class that transforms a regular class with a single-arg constructor into a singleton; used for configuring a singleton
* stack          pseudo-type for a LIFO stack, with push, pop, etc
* string         functions that operate on strings

## Usage

See Development section below for build information.
### Imports

import offtheecliptic.utils.<sub-package>.*

For example:

import offtheecliptic.utils.map.structure.*
import offtheecliptic.utils.singleton.SingletonContainer

## Library Contents

### utils.list

### utils.logging

### utils.map
### utils.map.structure

### utils.partial

import offtheecliptic.utils.partial

Provides functions for partial application of Kotlin functions.  Not nearly as nice as Clojure's partial function, but better than nothing.

Functions:
    partial21
    partial22

#### partial21

Creates a partial for a 2-arg function, turning it into a 1-arg function. The type of the two arguments is the same for this function.
 
Example function to partial out: 
    fun add(a: Int, b:Int): Int; val add1: (Int) -> Int = partial2(::add, 1)
 
Usage: 
    fun <T> f2(a:T, b:T): T                  // 2-arg fn
    val f1: (T) -> T = partial2(aValue, f2)  // Turns 2-arg fn into 1-arg fn
    val x: T = f1(bValue)                    // Invoke the partial'd fn

#### partial22

Creates a partial for a 2-arg function, turning it into a 1-arg function. This differs from partial2 in that the type of the 1st argument (the one being partial'd) is different than the type of the second one (the one that remains after the partial application).

Example: 
    fun quadratic(p: List<Int>, x: Int): Int { return p[0] + p[1]*x + p[2]*x*x }
    val quadWithParams: (Int) -> Int = partial22(::quadratic, listOf(2,3,4))
    val y: Int = quadWithParams(10)  ==> 432
  
### utils.singleton

import offtheecliptic.utils.singleton.SingletonContainer

Defines a class that can be extended by a class with a single private argument, turning that class into a configurable singleton.

class SingletonContainer
    Extend this class to turn a single-arg class into a singleton.

    For example:
    class Example private constructor(arg: ArgClass) {
        // local variables
        init {
            // Initialize local variables using 'arg' argument
        }
        // The following will act as static functions
        fun doSomeStuff(...): <ReturnType> { ... }  
        fun doMoreStuff(...): <ReturnType> { ... }  
        
        // The name of this class must be specified in the generic.
        // The argument to SingletonContainer can be either a:
        //    1-arg fn, or 
        //    ref to the singleton
        companion object : SingletonContainer<Example, ArgClass>(::Example)
    }

    The singleton may now be invoked using the following syntax; its initialization will be lazy and thread-safe:

    Example.getInstance(context).doSomeStuff()
 
    Reference:
        https://bladecoder.medium.com/kotlin-singletons-with-argument-194ef06edd9e


### utils.map.stack

### utils.map.string



## Development


### Dependency Specification

These assume that you have installed the library in your local Maven repository.   

#### Maven

In your pom.xml file, put in the following dependency under dependencies/dependency.

    <groupId>offtheecliptic</groupId>
    <artifactId>utils</artifactId>
    <version>${lib.version}</version>

#### Gradle

You should also have the following under repositories.

    mavenLocal()

Put the following under dependencies.

    implementation "offtheecliptic:utils:${utils.version}"


### Build and Unit Test

#### Maven (Sucks)

To compile with maven:
 
> mvn clean
> mvn compile

#### Gradle

To build (compile and test) with gradle:

> ./gradlew build

### Package

Package the software with its dependencies to yield a runnable JAR file.

#### Maven (Sucks)

To package (into an uberjar) with maven:
  
> mvn package

This leaves a file named utils-all-<version>-jar-with-dependencies.jar in the target directory.

#### Gradle

To create an uberjar with gradle:

> ./gradlew uberJar
   
This leaves a file named utils-all-<version>.jar in the build/libs directory.

### Install the Library Into Local Repository

#### Maven

> mvn install


#### Gradle

In your build.gradle file, add the following line:

    apply plugin: "maven"       
    
That allows use of ./gradlew install to put in maven local repo.

Then at the command line:

> gradle install


### Run Examples

You can run using the uberjar. 

#### Maven (Sucks)

To run from a maven-generated uberjar, you can do either of these:
    java  -jar target/utils-0.0.1-jar-with-dependencies.jar
    kotlin -cp target/utils-0.0.1-jar-with-dependencies.jar <main-fully-qualified>

The first only runs the primary program defined by the main.class in the pom.xml file.

The second can be used to run any class (primary or not).
    kotlin -cp target/utils-0.0.1-jar-with-dependencies.jar offtheecliptic.utils.<Specific Name>

#### Gradle

To run from a gradle-generated uberjar:
    java  -jar build/libs/utils-all-0.0.1.jar
    kotlin -cp build/libs/utils-all-0.0.1.jar <main-fully-qualified>

The first only runs the primary program defined by the main.class in the pom.xml file.

The second can be used to run any class (primary or not).
    kotlin -cp build/libs/utils-all-0.0.1.jar  offtheecliptic.utils.<Specific Name>


#### Simple Test Cases


#### Mapping Test Cases


## Contributions

## License

Copyright © 2021 OffTheEcliptic

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

