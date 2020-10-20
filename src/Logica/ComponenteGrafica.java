package Logica;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

public class ComponenteGrafica {
	private String [] imagenes= new String[9];
	private int imagen;
	private ImageIcon image;
	private Logger logger;
	
	public ComponenteGrafica(){
		imagen=-1;
		image= null;
		inicializar();
		Handler handler= new ConsoleHandler();
		handler.setLevel(Level.INFO);
		logger = Logger.getLogger(ComponenteGrafica.class.getName());
		logger.addHandler(handler);
		logger.setLevel(Level.INFO);
		
	}
	
	private void inicializar() {
		imagenes[0]="src\\Logica\\Images\\pj1.png";
		imagenes[1]="src\\Logica\\Images\\pj2.png";
		imagenes[2]="src\\Logica\\Images\\pj3.png";
		imagenes[3]="src\\Logica\\Images\\pj4.png";
		imagenes[4]="src\\Logica\\Images\\pj5.png";
		imagenes[5]="src\\Logica\\Images\\pj6.png";
		imagenes[6]="src\\Logica\\Images\\pj7.png";
		imagenes[7]="src\\Logica\\Images\\pj8.png";
		imagenes[8]="src\\Logica\\Images\\pj9.png";
	}
	
	public void siguienteImg() {
		if(imagen==8) {
			imagen=0;
		}else {
			imagen++;
		}
		image= new ImageIcon(imagenes[imagen]);
		logger.info("La imagen se actualizo a la siguiente.");
	}
	
	public ImageIcon getImg() {
		return image;
	}
	
	public void setImagen(int n) {
		imagen=(n-1)%9;
		image= new ImageIcon(imagenes[imagen]);
	}
}
