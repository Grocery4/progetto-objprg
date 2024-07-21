/**
 * @file Utils.java
 * 
 * Classe di utilità che fornisce metodi statici per operazioni comuni sui file.
 * 
 */


package view.components;

import java.io.File;

public class Utils {
	
	/**
	 * Metodo utilizzato per ottenere l'estensione di un file all'interno di un JFileChooser.
	 * 
	 * @param f File da cui ricavare l'estensione
	 * @return Il nome dell'estensione sottoforma di stringa.
	 */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

}
