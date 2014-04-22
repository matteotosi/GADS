#! /bin/bash

# classpath 
GADS_HOME="."
# main file of jpintp 
MAIN=com.gads.view.Main 


## BASE CLASSES
CP="$GADS_HOME/bin/"
CP=$CP":$GADS_HOME/lib/antlr-4.0-complete.jar"
CP=$CP":$GADS_HOME/lib/jta26.jar"
CP=$CP":$GADS_HOME/lib/sqlite-jdbc-3.7.15-M1.jar"


java -cp "$CP" "$MAIN" $*
