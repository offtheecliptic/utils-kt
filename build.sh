#!/bin/bash

sh clean.sh
case $1 in
    1) # Comming Soon
        kotlinc -verbose -Xplugin="lib\kotlinx-serialization-compiler-plugin.jar" \
                         -cp "lib\kotlinx-serialization-json-jvm-1.0.1.jar;lib\kotlinx-serialization-core-jvm-1.0.1.jar" \
                          main.kt
        ;;
    2) # Working
        kotlinc main.kt -Xplugin="lib\kotlinx-serialization-compiler-plugin.jar" \
                        -cp "lib\kotlinx-serialization-json-jvm-1.0.1.jar;lib\kotlinx-serialization-core-jvm-1.0.1.jar" \
                        -include-runtime -d main.jar
                         jar ufm Main.jar ManifestAdditions.txt lib
                         kotlin main.jar
        ;;
    3) # Comming Soon
        kotlinc-native main.kt -verbose -Xplugin="lib\kotlinx-serialization-compiler-plugin.jar" \
                        -cp "lib\kotlinx-serialization-json-jvm-1.0.1.jar;lib\kotlinx-serialization-core-jvm-1.0.1.jar" \
                        -manifest ManifestAddition.txt -o main
        ;;
esac