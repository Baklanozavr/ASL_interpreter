# ASL_interpreter

## Run instructions

[Current version](https://github.com/Baklanozavr/ASL_interpreter/blob/master/ASL.jar) of ASL interpreter as .jar file
with all dependencies.  
To run this interpreter, you need [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) in
your system with the valid JAVA_HOME system variable.

Possible command line arguments:    
__--source__, __-s__ _source-path_  -- an .asl-file to perform, or a directory where all .asl-files will be
performed;      
__--debug__, __-de__  -- enable the debug-mode, when all parsed statements will be printed.

### Run on Windows

Open command prompt in the directory with the downloaded interpreter file

```
:: execute some ASL-code from a file in the same directory
java -jar ASL.jar -s <file-name>

:: execute all .asl-files from a directory
java -jar ASL.jar -s <path-to-directory>

:: start REPL mode of ASL-executor
java -jar ASL.jar
```

## Build instructions

You need [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
and [Maven](https://maven.apache.org/) in your classpath

### Windows

```
:: clone git repo
git clone https://github.com/Baklanozavr/ASL_interpreter.git

:: move to project directory
cd ASL_interpreter

:: generate executable jar
mvn package
```

There will be a file ASL.jar

## Run example
### Windows

```
:: move to target directory from project directory
cd target

:: execute some ASL-code from a file MyFile in the same directory
java -jar ASL-0.2-jar-with-dependencies.jar MyFile

:: start REPL mode of ASL-executor
java -jar ASL-0.2-jar-with-dependencies.jar
```
