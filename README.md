# Library Utils

## Introduction

This is a collection of utilities, each in its own sub-package under offtheecliptic.utils.

## Contents

The following utilities are provided:

    string          functions that operate on strings
    map             functions that operate on maps
    map.structure   interfce that adds functions to objects that can be represented as maps\
    partial         functions that allow partial application of functions
    stack           pseudo-type for a LIFO stack, with push, pop, etc functions


## Usage

group:      offtheecliptic
artifact:   utils
version:    0.0.1

### Dependency Specification

These assume that you have installed the library in your local Maven repository.   

### Maven

In your pom.xml file, put in the following dependency under dependencies/dependency.

    <groupId>offtheecliptic</groupId>
    <artifactId>utils</artifactId>
    <version>${lib.version}</version>

### Gradle

You should also have the following under repositories.

    mavenLocal()

Put the following under dependencies.

    implementation "offtheecliptic:utils:${data_pipeline.version}"

### Imports

import offtheecliptic.utils.<sub-package>.*


## Details on Each Utility

### utils.partial

### utils.map


### utils.block

Read in blocks of configuration info.  File format is:
 
   Title: Some title
   Definition: 
       <Type>: <Name1>
           <FieldName1>: <FieldValue11>
           <FieldName1>: <FieldValue12>
           <FieldName2>: <FieldValue21>
   Definition: 
       <Type>: <Name2>
           <FieldName1>: <FieldValue11>
           <FieldName2>: <FieldValue21>
           <FieldName2>: <FieldValue22>
           <FieldName3>: <FieldValue31>
   ....

Algorithm:
   readLinesIntoSeq(filename: String): List<String>
   splitSeqIntoChunks(list: List<String>): List<List<String>>
       //Partitions the input sequence on lines with the word 'Definition:'
   chunksIntoBlocks(chunks: List<List<String>>): BlockSet
       blocks = List>Block>



## Development

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

