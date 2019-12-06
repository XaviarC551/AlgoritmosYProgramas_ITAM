package vistas;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import componentes_personalizados.CanvasMapa;

/*
 * 
 * "Vista Buscar Rutas"
 * Por Javier Prieto
 * 
 * Esta vista pide 2 lugares al usuario
 * y muestra la ruta que debe seguir para
 * llegar a donde desea.
 * 
 * */

public class VistaBuscarRutas extends JFrame{
	private JLabel l1,l2,l3,l4;
	
	protected JButton editarMapa, nuevoMapa, buscarRuta;
	protected JComboBox puntoA, puntoB;
	protected JList listaDeLugares;
	protected CanvasMapa canvas;
	
	private final int HEIGHT=850;
	private final int WIDTH=1200;
	
	public VistaBuscarRutas(String titulo) {
		super(titulo);
		
		
		init(this.getContentPane());
		
		
		
		this.setBounds(100, 100, WIDTH, HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void init(Container pane) {
		pane.setLayout(null);
		Font fuenteTitulo=new Font("Monospaced",Font.BOLD,30);
		Font fuente=new Font("Monospaced",Font.BOLD,16);
		Font fuente2=new Font("Monospaced",Font.PLAIN,16);
		
		
		
		l1=new JLabel("Guía Personal");
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setFont(fuenteTitulo);
		
		editarMapa=new JButton("EDITAR MAPA");
		editarMapa.setFont(fuente);
		
		nuevoMapa=new JButton("NUEVO MAPA");
		nuevoMapa.setFont(fuente);
		
		
		canvas=new CanvasMapa();
		
		l2=new JLabel("Lugares");
		l2.setHorizontalAlignment(JLabel.CENTER);
		l2.setFont(fuente);
		
		
		l3=new JLabel("Punto A");
		l3.setHorizontalAlignment(JLabel.CENTER);
		l3.setFont(fuente2);
		puntoA=new JComboBox();
		l4=new JLabel("Punto B");
		l4.setHorizontalAlignment(JLabel.CENTER);
		l4.setFont(fuente2);
		puntoB=new JComboBox();
		
		listaDeLugares=new JList();
		listaDeLugares.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDeLugares.setLayoutOrientation(JList.VERTICAL);
		//listaDeLugares.setVisibleRowCount(-1);

		JPanel lugaresSP=new JPanel();
		lugaresSP.setLayout(new GridLayout(4,1));
		
		
		buscarRuta=new JButton("Buscar Ruta");
		buscarRuta.setFont(fuente);
		
		
		JPanel panelDeLugares=new JPanel();
		
		
		lugaresSP.add(l3);
		lugaresSP.add(puntoA);
		lugaresSP.add(l4);
		lugaresSP.add(puntoB);
		
		panelDeLugares.add(buscarRuta);
		
		
		pane.add(l1);
		pane.add(editarMapa);
		pane.add(nuevoMapa);
		pane.add(canvas);
		pane.add(l2);
		pane.add(lugaresSP);
		pane.add(panelDeLugares);
		
		
		
		// Posicionamiento
		Dimension dim=l1.getPreferredSize();
        l1.setBounds(WIDTH/2-dim.width/2,10,dim.width, dim.height);
        
        dim=editarMapa.getPreferredSize();
        editarMapa.setBounds(10,80,dim.width, dim.height);
		
        dim=nuevoMapa.getPreferredSize();
        nuevoMapa.setBounds(180,80,dim.width, dim.height);
        
		
        canvas.setBounds(10,185,600,600);
        
        dim=l2.getPreferredSize();
        l2.setBounds(860,185,dim.width, dim.height);
        
        lugaresSP.setBounds(700,220,400, 200);
        
        dim=panelDeLugares.getPreferredSize();
        panelDeLugares.setBounds(700,740,dim.width, dim.height);
        
	}

}
