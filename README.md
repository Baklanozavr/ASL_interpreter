# ASL_interpreter

## Run instructions

[Current version](https://drive.google.com/file/d/1bYGZY6iaa6MXMGgedT0JWXqR7i7gQcIB/view?usp=sharing) of ASL interpreter
as .jar file with all dependencies.  
To run this interpreter you need [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) in your
system with valid JAVA_HOME system variable.

### Run on Windows

Open command prompt in the directory with the downloaded interpreter file

```
:: execute some ASL-code from a file MyFile in the same directory
java -jar ASL-0.2.jar MyFile

:: start REPL mode of ASL-executor
java -jar ASL-0.2.jar
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

In target directory there will be a file ASL-0.2-jar-with-dependencies.jar

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
