package Logica;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Task extends TimerTask {
		Timer timer;
		int cont;
		int contMinutos=0;
		JLabel [] reloj;
		String [] imagenes;
		public Task(Timer t,JLabel [] r){
			timer=t;
			cont=0;
			reloj=r;
			imagenes= new String [10];
			inicializarArreglo();
		}
		private void inicializarArreglo() {
			imagenes[0]="src\\Logica\\Images\\0.png";
			imagenes[1]="src\\Logica\\Images\\1.png";
			imagenes[2]="src\\Logica\\Images\\2.png";
			imagenes[3]="src\\Logica\\Images\\3.png";
			imagenes[4]="src\\Logica\\Images\\4.png";
			imagenes[5]="src\\Logica\\Images\\5.png";
			imagenes[6]="src\\Logica\\Images\\6.png";
			imagenes[7]="src\\Logica\\Images\\7.png";
			imagenes[8]="src\\Logica\\Images\\8.png";
			imagenes[9]="src\\Logica\\Images\\9.png";
			
		}
		public void run(){
			if (cont==60) {
				 cont=0;
				 contMinutos++;
			}else {
				cont++;			
			}
			int contMinutosAux=contMinutos;
			int contAux=cont;
			reloj[4].setIcon(reDimensionar(reloj[4],new ImageIcon(imagenes[contAux%10])));
			reloj[3].setIcon(reDimensionar(reloj[3],new ImageIcon(imagenes[contAux/10])));
			reloj[2].setIcon(reDimensionar(reloj[2],new ImageIcon("src\\Logica\\Images\\2puntos.png")));
			reloj[1].setIcon(reDimensionar(reloj[1],new ImageIcon(imagenes[contMinutosAux%10])));
			reloj[0].setIcon(reDimensionar(reloj[0],new ImageIcon(imagenes[contMinutosAux/10])));
		}
		
		private ImageIcon reDimensionar(JLabel label, ImageIcon grafico) {
			if(grafico!=null) {
				Image img= grafico.getImage().getScaledInstance(50,50, java.awt.Image.SCALE_SMOOTH);
				grafico.setImage(img);
			}
			return grafico;
		}
}

