# Data Processing System

This project is written in **Java 17** and uses **JUnit 5** for testing.

## Requirements
- Java: Version 17 or above
- Testing Framework: JUnit 5


If Java of version 17 and above is not already installed:

## 1. Install Java 17

- [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

## 2. Unzip the file and navigate to the project in a terminal 

## 3. Get JUnit 5
Create a folder named "lib" in the project directory.

Download the JUnit Console Standalone JAR:

- [Download junit-platform-console-standalone-1.10.0.jar](https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.0/junit-platform-console-standalone-1.10.0.jar)

And place it in the created "lib" folder.

## 4. Compile the Code

By running the following line of code in your terminal:
>`javac -d out -cp "lib/*" src/*.java`

## 5. Run the program

You can run the code with 4 different arguments:

>`-params`: the name of the file with input parameters<br>
>`-channels`: the name of the file with input channels<br>
>`-resname`: the desired name of the file in which results should be displayed<br>
>`-res`: the names of desired varibles(metrics/channels) that should be included in the result file<br>

An example of use:

>`java -cp out Main -params params.txt -channels foldername/c.txt -resname res.txt -res A Y b`

The code can also be executed without any or all of the arguments:

>`java -cp out Main`

In which case the not included arguments within the program will be set to default values:

>`-params` = testing-inputs/parameters.txt<br>
>`-channels` = testing-inputs/channels.txt<br>
>`-resname` = results.txt<br>
>`-res` = b<br>

## 6. Run the Tests

The tests can be executed by running the following line of code in your terminal:
>`java -jar lib/junit-platform-console-standalone-1.10.0.jar -cp out --scan-classpath`

### Information on certain design choices is available in the inline comments of ChannelProcessingSystem.java

