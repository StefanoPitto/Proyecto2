package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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

@SuppressWarnings("serial")
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
					frame.setResizable(false);
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
		JLabel etiquetaPanelInicial = new JLabel();
		etiquetaPanelInicial.setForeground(Color.CYAN);
		etiquetaPanelInicial.setVerticalAlignment(SwingConstants.TOP);
		etiquetaPanelInicial.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 32));
		etiquetaPanelInicial.setText("Presione para iniciar");
		etiquetaPanelInicial.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaPanelInicial.setBounds(0, 0, 743, 631);
		ImageIcon imagen= new ImageIcon("src\\Logica\\Images\\InicioImg");
		etiquetaPanelInicial.setIcon(imagen);
		reDimensionar(etiquetaPanelInicial,imagen);
		etiquetaPanelInicial.repaint();
		contentPane.add(etiquetaPanelInicial);
		etiquetaPanelInicial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				etiquetaPanelInicial.setVisible(false);
				etiquetaPanelInicial.setEnabled(false);
				iniciarJuego();
			}
		});
	}
	private void iniciarJuego() {
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
		} catch (FileException e1) {
			JOptionPane infomsg= new JOptionPane();
			infomsg.showMessageDialog(contentPane, "Archivo inválido.");
			System.exit(0);
			
		}
		JPanel panelSudoku = new JPanel();
		panelSudoku.setBounds(0, 57, 742, 563);
		contentPane.add(panelSudoku);
		panelSudoku.setLayout(new GridLayout(9, 9, 0, 0));
	
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				Casillero c=juego.getCasillero(i, j);
				ImageIcon grafico =c.getGrafico().getImg();
				JLabel label= new JLabel();
				label.setOpaque(true);
				panelSudoku.add(label);
				this.reDimensionarLabels(label, grafico);
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
					reDimensionarLabels(label, grafico);
					label.setIcon(grafico);
				}
			});				
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
						if(c.esModificable()) {	
							juego.actualizar(c);
							label.setIcon(c.getGrafico().getImg());
							reDimensionarLabels(label,c.getGrafico().getImg());
							if(c.esValida()) {
							label.setBackground(Color.green);
							}else {
								label.setBackground(Color.red);
							}
							label.repaint();
						}
						if(juego.verificarSolucion()) {
							panelReloj.setEnabled(false);
							panelReloj.setVisible(false);
							panelSudoku.setEnabled(false);
							panelSudoku.setVisible(false);
							JLabel etiquetaFinal= new JLabel();
							etiquetaFinal.setForeground(Color.CYAN);
							etiquetaFinal.setVerticalAlignment(SwingConstants.TOP);
							etiquetaFinal.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 32));
							etiquetaFinal.setText("Ganaste");
							etiquetaFinal.setHorizontalAlignment(SwingConstants.CENTER);
							etiquetaFinal.setBounds(0, 0, 743, 631);
							ImageIcon imagen= new ImageIcon("src\\Logica\\Images\\InicioImg");
							etiquetaFinal.setIcon(imagen);
							reDimensionar(etiquetaFinal,imagen);
							etiquetaFinal.repaint();
							contentPane.add(etiquetaFinal);
						}
					}
				});				
			}
		}
	}
	
		
	private void reDimensionarLabels(JLabel label, ImageIcon grafico) {
		if(grafico!=null) {
			Image img= grafico.getImage().getScaledInstance(100,70, java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(img);
			label.repaint();
		}
	}
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		if(grafico!=null) {
			Image img= grafico.getImage().getScaledInstance(label.getWidth(),label.getHeight(), java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(img);
			label.repaint();
		}
	}
}
