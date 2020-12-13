# ASL_interpreter

You need Java 11 and Maven in your classpath https://maven.apache.org/

## Build instructions
### Windows
```
:: clone git repo
git clone https://github.com/Baklanozavr/ASL_interpreter.git

:: move to project directory
cd ASL_interpreter

:: generate lexer and parser
mvn jflex:generate
mvn cup:generate

:: generate jar
mvn jar:jar


```
