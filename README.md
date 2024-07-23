# progetto-objprg

## About
Project created for the Object Oriented Programming exam at the University of Modena and Reggio Emilia (UNIMORE).

## Building

**Requirements:**
For the program to function properly, it is necessary to include the .jar files dedicated to the creation and management of graphics.
The needed files are: jcommon-1.0.23.jar</li, freechart-1.0.19.jar, jfreechart-1.0.19-swt.jar, all available through the library's official website https://jfree.org/jfreechart/ .
In the repo these files are located inside the lib/ directory.

**Execution:**
In order to run the program it's necessary to compile all .java files.

```
javac -d ./bin -cp ./lib/*:. src/App.java src/controller/Controller.java src/view/View.java src/view/components/*.java src/view/errors/*.java src/model/*.java src/model/esami/*.java
```
It's possible to do so either by opening the project on an IDE - in my case Eclipse - and clicking the run button, or by manually typing the command on the CLI.
In the latter case, the bash script 'compile.bash' will do the job for you.
```
java -cp bin/:./lib/*:. App
```
After compiling, run the program either manually or through the 'execute.bash' script.

## Documentation
The documentation is available in the documentation/ directory.
As for the main page, it is contained in the index.html file, and includes almost the same information as this README.md file.
