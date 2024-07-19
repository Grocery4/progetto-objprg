/**
 * @file FileFilter.java
 * 
 * Classe utilizzata per controllare l'estensione del file in apertura all'interno del JFileChooser.
 */

package view.components;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FileFilterEsame extends FileFilter {

	/**
	 * Il metodo permette esclusivamente l'apertura directory e del file .exam.
	 */
	@Override
	public boolean accept(File f) {
		if(f.isDirectory()) {return true;}
		
		String ext = Utils.getExtension(f);
		if(ext.equals("exam")) { return true;}

		return false;
	}

	@Override
	public String getDescription() {
		return "File Esame (*.exam)";
	}

}
