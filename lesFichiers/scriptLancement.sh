#!/bin/bash

javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls *.java

java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls  Executable
