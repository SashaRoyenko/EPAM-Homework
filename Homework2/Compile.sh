#!/bin/bash

# Create out folder
mkdir -p out

# Compile .java files 
javac -cp ./src/main/java ./src/main/java/com/epam/*.java -d ./out/ -classpath ./libs/*.jar


cd ./out

# Extract .jar files into out
jar xf ../libs/*.jar

cd ../

# Pack all files into Main.jar
jar cvfm Main.jar ./src/main/resources/META-INF/MANIFEST.MF -C out/ .

# Run Main.jar
java -jar ./Main.jar
SLEEP 60


