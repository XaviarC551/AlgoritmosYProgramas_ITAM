package vistas;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*
 * 
 * "Vista Principal"
 * Por Javier Prieto
 * 
 * Esta vista sirve para navegar rápidamente y de
 * manera intutiva y amigable a las otras 2 vistas
 * del programa.
 * 
 * */

public class VistaPrincipal extends JFrame{
	private JLabel l1;
	protected JButton nuevoMapa, abrirMapa;
	protected JPanel panel;
	public VistaPrincipal(String titulo) {
		super(titulo);
		
		Font fuenteTitulo=new Font("Monospaced",Font.BOLD,30);
		Font fuente=new Font("Monospaced",Font.BOLD,20);
		
		l1=new JLabel("Guía Personal");
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setFont(fuenteTitulo);
		
		nuevoMapa=new JButton("Nuevo Mapa");
		nuevoMapa.setFont(fuente);
		
		abrirMapa=new JButton("Abrir Mapa");
		abrirMapa.setFont(fuente);
		
		
		panel=new JPanel();
		Border b=BorderFactory.createEmptyBorder(10, 10, 10, 10);
		panel.setBorder(b);
		panel.setLayout(new GridLayout(3,1));
		
		panel.add(l1);
		panel.add(nuevoMapa);
		panel.add(abrirMapa);
		
		this.add(panel);
		this.setBounds(300, 300, 450, 425);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
