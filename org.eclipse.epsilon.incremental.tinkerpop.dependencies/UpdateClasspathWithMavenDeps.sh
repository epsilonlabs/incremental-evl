#!/bin/bash

# Use latest versions
mvn versions:display-property-updates
mvn versions:update-properties 

# This will copy the depedencies to the lib folder
rm -rf lib/
mvn clean package

# Add the new dependencies to the classpath. Copy in a file to copy, since modifying the .classpath file breakes eclipse
rm -f jarEntries.tmp
suff=-sources.jar
for i in lib/*-sources.jar; do
    jar=${i%$suff}    
echo "    <classpathentry exported=\"true\" kind=\"lib\" path=\"$jar.jar\" sourcepath=\"$i\"/>" >> jarEntries.tmp
done
