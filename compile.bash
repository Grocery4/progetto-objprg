
# -d: specifica dove piazzare i file .class generati dalla compilazione
# -cp specifica dove trovare le dipendenze del programma (.jar)
# <A.java> <B.java>.. : specifica quali sono i file target della compilazione
javac -d ./bin -cp ./lib/*:. src/App.java src/controller/Controller.java src/view/View.java src/view/components/*.java src/view/errors/*.java src/model/*.java src/model/esami/*.java
