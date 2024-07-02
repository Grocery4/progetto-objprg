/**
 *  @file App.java 
 * 
 * Questa è la classe che viene invocata per prima, poiché contiene il metodo main.
 * Il metodo istanzia gli oggetti di tipo Controller, Model, View per connettere l'interfaccia grafica
 * alla logica del programma, permettendone la comunicazione.
 */

import controller.Controller;
import model.Model;
import view.View;

public class App {
	public static void main(String args[]) {
		
		// Linking tra controller, model e view
		Controller controller = new Controller();
		Model model = new Model();
		controller.setModel(model);
		View view = new View(controller);
		controller.setView(view);
		
		
		// Mostra l'interfaccia grafica all'utente
		view.setVisible(true);
	}
}
