/**
 *  @file App.java 
 * 
 * Questa è la classe che viene invocata per prima, poiché contiene il metodo main.
 * Il metodo istanzia gli oggetti di tipo Controller, Model, View per connettere l'interfaccia grafica
 * alla logica del programma, permettendone la comunicazione.
 */

import controller.Controller;
import view.View;
import model.Database;
import model.esami.*;

public class App {
	public static void main(String args[]) {
		
		// Linking tra controller e view.
		// In view sono definiti table e tableModel, mentre in controller è definito database. 
		Database database = new Database();
		Controller controller = new Controller();
		controller.setDatabase(database);

		View view = new View(controller);
		controller.setView(view);
		
		// Mostra l'interfaccia grafica all'utente
		view.setVisible(true);
	}
}
