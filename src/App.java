/**
 *  @file App.java 
 * 
 * Questa è la classe che viene invocata per prima, poiché contiene il metodo main.
 * Il metodo istanzia gli oggetti di tipo Controller, Model, View per connettere l'interfaccia grafica
 * alla logica del programma, permettendone la comunicazione.
 */

import controller.Controller;
import model.ModelINUSE;
import view.View;

public class App {
	public static void main(String args[]) {
		
		// Linking tra controller, model e view
		// a controller viene collegato anche table e tableModel
		// per non dovere ridefinire i due oggetti costantemente all'interno dei metodi.
		Controller controller = new Controller();
		
		ModelINUSE model = new ModelINUSE();
		controller.setModel(model);
		controller.setTableModel(model.getTableModel());
		
		View view = new View(controller);
		controller.setView(view);
		controller.setTable(view.getTable());
		
		// Mostra l'interfaccia grafica all'utente
		view.setVisible(true);
	}
}
