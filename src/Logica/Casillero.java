package Logica;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Casillero {
	private int fila, columna;
	private int elemento;
	private ComponenteGrafica grafico;
	private boolean posicionValida;
	private boolean modificable;
	private Logger logger;
	public Casillero(int f,int c,boolean m) {
		fila=f;
		columna=c;
		grafico=new ComponenteGrafica();
		posicionValida=false;
		modificable=m;
		elemento=0;
		Handler handler= new ConsoleHandler();
		handler.setLevel(Level.INFO);
		logger = Logger.getLogger(Casillero.class.getName());
		logger.addHandler(handler);
		logger.setLevel(Level.INFO);
	}
	
	public void setElement(int n) {
		elemento=n;
		this.grafico.setImagen(n);
	}
	public void setModificable(boolean b) {
		modificable=b;
	}
	public void setFila(int f) {
		fila=f;
	}
	
	public void setColumna(int c) {
		columna=c;
	}
	
	public int getFila() {
		return fila;
	}
	
	public int getColumna() {
		return columna;
	}
	
	public ComponenteGrafica getGrafico() {
		return grafico;
	}
	
	public void actualizar() {
		grafico.siguienteImg();
		if(elemento==9) {
			elemento=1;
		}else {
			elemento++;
		}
		logger.info("Se actualizo el numero del casillero.");
	}

	public int getElement() {
		return elemento;
	}
	
	public void setEsValida(boolean b) {
		this.posicionValida=b;
	}
	
	public boolean esValida() {
		return this.posicionValida;
	}
	
	public boolean esModificable() {
		return this.modificable;
	}
}
