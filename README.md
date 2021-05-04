# ASL_interpreter

You need Java 11 and Maven in your classpath https://maven.apache.org/

## Build instructions
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
