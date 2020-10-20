package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Logica.Casillero;
import Logica.FileException;
import Logica.Sudoku;
import Logica.Task;

import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Sudoku.png"));
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelReloj = new JPanel();
		panelReloj.setBounds(0, 0, 742, 57);
		contentPane.add(panelReloj);
		JLabel [] reloj= new JLabel[5];
		for(int i=0;i<reloj.length;i++){
			reloj[i]= new JLabel();
			panelReloj.add(reloj[i]);
		}
		Timer t=new Timer();
		Task task=new Task(t,reloj);
		t.schedule(task,0,1000);	
		Sudoku juego= new Sudoku();
		try {
			juego.inicializarSudoku();
		} catch (FileException e2) {}
		try {
			juego.inicializarSudoku();
		} catch (FileException e1) {}
		JPanel panelSudoku = new JPanel();
		panelSudoku.setBounds(0, 57, 742, 563);
		contentPane.add(panelSudoku);
		panelSudoku.setLayout(new GridLayout(9, 9, 0, 0));
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				Casillero c = juego.getCasillero(i, j);
				ImageIcon grafico =c.getGrafico().getImg();
				JLabel label= new JLabel();
				label.setOpaque(true);
				panelSudoku.add(label);
				this.reDimensionar(label, grafico);
				label.setIcon(grafico);
				if(!c.esModificable()) {
					if(c.esValida()) {
						label.setBackground(Color.green);
					}else {
						label.setBackground(Color.red);
					}
				}
				label.setBorder(new LineBorder(Color.black));				
				label.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						reDimensionar(label, grafico);
						label.setIcon(grafico);
					}
				});				
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(c.esModificable()) {	
							juego.actualizar(c);
							label.setIcon(c.getGrafico().getImg());
							reDimensionar(label,c.getGrafico().getImg());
							if(c.esValida()) {
								label.setBackground(Color.green);
							}else {
								label.setBackground(Color.red);
							}
							label.repaint();
						}
					}
				});				
			}
		}	
		
	}
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		if(grafico!=null) {
			Image img= grafico.getImage().getScaledInstance(100,70, java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(img);
			label.repaint();
		}
	}
}
