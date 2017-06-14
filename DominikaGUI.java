package CzytanieExcela;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DominikaGUI extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DominikaGUI() {
		super("Sprawdzenie NIPu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300,300);
		
		JFileChooser jfc = new JFileChooser();
	    jfc.showDialog(null,"Wybierz plik");
	    jfc.setVisible(true);
	    Dominika.plikznipami = jfc.getSelectedFile();
	   
	    
	    
	}
	
	public void closeWindow() {
		setVisible(false);
		dispose();
	}
	
	public void displayInfo(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	

}
