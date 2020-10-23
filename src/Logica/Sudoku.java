package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sudoku {
	private Casillero [][] matrizSudoku;
	private Logger logger;
	
	public Sudoku(){
		matrizSudoku= new Casillero [9][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				matrizSudoku[i][j]= new Casillero(i,j,true);
			}
		}
		Handler handler= new ConsoleHandler();
		handler.setLevel(Level.INFO);
		logger = Logger.getLogger(Sudoku.class.getName());
		logger.addHandler(handler);
		logger.setLevel(Level.INFO);
	}	
	
	public void inicializarSudoku() throws FileException{			
			File archivo = new File("src\\Logica\\sudoku.txt");
			FileReader fr = null;
			try {
				fr = new FileReader(archivo);
			} catch (FileNotFoundException e) {}
			BufferedReader br = new BufferedReader(fr);
			for(int j=0;j<9;j++) {
				int contador=0;
				String str =null;
				try {
					str =br.readLine();
				} catch (IOException e) {}
				str=str.trim();
				if(str.length()!=17) {
					throw new FileException("Archivo inválido.");
				}
				for(int i=0;i<str.length();i++) {
					char c=str.charAt(i);
					if(i%2==0) {
						if(c>='0'&&c<='9') {
							int m= c-'0';
							if(m!=0) {
								matrizSudoku[j][contador].setElement(m);
								matrizSudoku[j][contador].setModificable(false);
								this.verificar(matrizSudoku[j][contador]);
							}
							contador++;
						}else {
							throw new FileException("Archivo inválido.");
						}
					
					}else {
						if(c!=' ') {
							throw new FileException("Archivo inválido");
						}
					}
				}
			}
	}

	public void actualizar(Casillero c) {
			c.actualizar();
			logger.info("El casillero "+c.getFila()+"|"+c.getColumna()+" fue actualizado.");
			verificar(c);
	}
	
	private void verificar(Casillero c) {
		boolean encontre=false;
		Casillero  casillero=c;
		for(int i=0;i<matrizSudoku.length&&!encontre;i++) {
			if(i!=casillero.getFila()) {
				if(matrizSudoku[i][c.getColumna()].getElement()==casillero.getElement()) {
					c.setEsValida(false);// Si está repetido setea como falso posición válida.
					encontre=true;
					logger.info("El casillero "+casillero.getFila()+"|"+casillero.getColumna()+" no es válido.");
				}

			}
		}
		for(int i=0;i<matrizSudoku.length&&!encontre;i++) {
			if(i!=casillero.getColumna()) {
				if(matrizSudoku[c.getFila()][i].getElement()==casillero.getElement()){
					c.setEsValida(false);// Si está repetido setea como falso posición válida.
					encontre=true;
					logger.info("El casillero "+c.getFila()+"|"+c.getColumna()+" no es válido.");
				}
			}
		}
		int f=casillero.getFila()/3;
		int c1=casillero.getColumna()/3;
		for(int i=f*3;i<f+3&&!encontre;i++) {
			for(int j=c1*3;j<c1+3&&!encontre;j++){
				if(i!=casillero.getFila()&&i!=casillero.getColumna()) {
					if(matrizSudoku[i][j].getElement()==casillero.getElement()) {
						c.setEsValida(false); // Si está repetido setea como falso posición válida.
						encontre=true;
						logger.info("El casillero "+casillero.getFila()+"|"+casillero.getColumna()+" no es válido.");
					}
				}
			}	
		}	
		if(!encontre) {
			c.setEsValida(true); // Si no está repetido setea como verdadero posición válida.
			logger.info("El casillero "+casillero.getFila()+"|"+casillero.getColumna()+" es válido.");
		}
	}	
	public Casillero getCasillero(int f,int c) {
		return matrizSudoku[f][c];
	}
	
	public int getCantFilas() {
		return matrizSudoku.length;
	}
	public boolean verificarSolucion() {
		for(int i=0;i<matrizSudoku.length;i++) {
			for(int j=0;j<matrizSudoku.length;j++) {
				if(!matrizSudoku[i][j].esValida()) {
					return false;
				}
			}
		}
		return true;
	}
}
