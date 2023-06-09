#!/bin/bash

CLASSPATH=`mvn dependency:build-classpath \
    -DincludeTypes=jar \-Dmdep.outputFile=/dev/stderr \
    2>&1 >/dev/null`:target/classes jshell
