#!/bin/bash

mvn package

CLASSPATH=`mvn dependency:build-classpath \
    -DincludeTypes=jar \-Dmdep.outputFile=/dev/stderr \
    2>&1 >/dev/null`:target/classes \
    jshell -start DEFAULT -start PRINTING -start ./bootstrap.jsh
