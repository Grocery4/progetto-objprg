/** @file App.java */

import controller.Controller;
import model.Model;
import view.View;

public class App {
	public static void main(String args[]) {
		Controller controller = new Controller();
		Model model = new Model();
		controller.setModel(model);
		View view = new View(controller);
		controller.setView(view);
		
		//linking tra controller, model e view

		view.setVisible(true);
	}
}
