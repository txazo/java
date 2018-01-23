#!/bin/bash

cd src/main/java
rm -rf org/txazo/java/annotation/processing/*.class
javac org/txazo/java/annotation/processing/*

cd ../../..

rm -rf tmp
mkdir -p tmp/anno-process/META-INF/services
mkdir -p tmp/anno-process/org/txazo/java/annotation/processing
cp src/main/resources/META-INF/services/javax.annotation.processing.Processor.bak tmp/anno-process/META-INF/services/javax.annotation.processing.Processor
cp src/main/java/org/txazo/java/annotation/processing/*.class tmp/anno-process/org/txazo/java/annotation/processing

cd tmp/anno-process
jar -cvf anno-process.jar *
cp anno-process.jar ../../lib
