@echo off
REM This script uses maven to download the dependencies and then copies them to the lib folder
REM Use latest versions
REM mvn versions:display-property-updates -Dversions.allowSnapshots=true -DallowMinorUpdates=false -DallowMajorUpdates=false -DallowIncrementalUpdates=false
REM mvn versions:update-properties -Dversions.allowSnapshots=true -DallowMinorUpdates=false -DallowMajorUpdates=false -DallowIncrementalUpdates=false

rm -rf lib/
call mvn clean package

REM Add the new dependencies to the classpath. Crate then entry list in a separate file since modifying the .classpath file breakes eclipse
rm -f jarEntries.tmp
SetLocal EnableDelayedExpansion
for /r lib/ %%f in (*.jar) do (
    set name=%%~nf
    set fullname=%%~dpnf
    echo.!name! | findstr /C:"source" 1>nul
    if errorlevel 1 (
        REM echo. Not a source file. Looking for source
        if exist !fullname!-sources.jar (
            rem file exists
            echo     ^<classpathentry exported="true" kind="lib" path="lib/!name!.jar" sourcepath="lib/!name!-sources.jar"/^> >> jarEntries.tmp
        ) else (
            rem file doesn't exist
            echo     ^<classpathentry exported="true" kind="lib" path="lib/!name!.jar"/^> >> jarEntries.tmp
        )
      
    )
    REM 
    )

exit /b