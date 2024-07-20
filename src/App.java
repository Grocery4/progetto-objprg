/**
 *  @file App.java 
 *  @author Judd Russel Kalalo
 * 
 * Questa è la classe che viene invocata per prima, poiché contiene il metodo main.
 * Il metodo istanzia gli oggetti di tipo Controller, Database, View per connettere l'interfaccia grafica
 * alla logica del programma, permettendone l'interazione.
 */

import controller.Controller;
import view.View;
import model.Database;

public class App {
	public static void main(String args[]) {
		Database database = new Database();
		Controller controller = new Controller();
		controller.setDatabase(database);

		View view = new View(controller);
		controller.setView(view);
		
		view.setVisible(true);
	}
}
