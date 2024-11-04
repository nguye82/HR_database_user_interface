
build: gui.class

Database.class: Database.java
	javac Database.java

gui.class: Database.class gui.java
	javac gui.java

run: gui.class
	java -cp .:mssql-jdbc-11.2.0.jre18.jar gui

