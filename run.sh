#!/bin/bash

read -p "Do you want to recompile the Java file? (y/n): " choice
if [ "$choice" = "y" ]; then
    javac src/morch/App.java -d build/classes/
fi

cd build/classes/
java morch/App