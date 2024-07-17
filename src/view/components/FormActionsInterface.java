/**
* @file FormActionsInterface.java
* 
* Interfaccia che definisce le azioni comuni alle classi EsameCompostoPanel e EsameSemplicePanel.
*/

package view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface FormActionsInterface extends ActionListener {
	public void actionPerformed(ActionEvent e);
	public void resetFormContent();
	public void sendForm();
}