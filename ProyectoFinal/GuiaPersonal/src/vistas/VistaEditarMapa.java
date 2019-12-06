package vistas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import componentes_personalizados.CanvasMapa;

/*
 * 
 * "Vista Buscar Rutas"
 * Por Javier Prieto
 * 
 * Esta vista sirve para modificar el mapa
 * y agregar o eliminar lugares del arreglo
 * de lugares de interés.
 * 
 * */

public class VistaEditarMapa extends JFrame{
	private JLabel l1,l2;
	
	protected JButton guardar, buscarRutas, abrirMapa, pared, piso, agregar, eliminar;
	protected JList listaDeLugares;
	protected CanvasMapa canvas;
	
	private final int HEIGHT=850;
	private final int WIDTH=1200;
	
	public VistaEditarMapa(String titulo) {
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
		
		JPanel panelDeControl=new JPanel();
		panelDeControl.setBackground(Color.WHITE);
		
		panelDeControl.setAlignmentX(LEFT_ALIGNMENT);
		
		l1=new JLabel("Editor de Mapas");
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setFont(fuenteTitulo);
		
		guardar=new JButton("GUARDAR");
		guardar.setFont(fuente);
		
		buscarRutas=new JButton("BUSCAR RUTAS");
		buscarRutas.setFont(fuente);
		
		abrirMapa=new JButton("ABRIR MAPA");
		abrirMapa.setFont(fuente);
		
		pared=new JButton("Pared");
		pared.setFont(fuente);
		
		piso=new JButton("Piso");
		piso.setFont(fuente);
		
		canvas=new CanvasMapa();
		
		l2=new JLabel("Lugares");
		l2.setHorizontalAlignment(JLabel.CENTER);
		l2.setFont(fuente);
		
		
		listaDeLugares=new JList();
		listaDeLugares.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDeLugares.setLayoutOrientation(JList.VERTICAL);
		//listaDeLugares.setVisibleRowCount(-1);

		JScrollPane lugaresSP=new JScrollPane(listaDeLugares);
		
		agregar=new JButton("Agregar");
		agregar.setFont(fuente);
		
		eliminar=new JButton("Eliminar");
		eliminar.setFont(fuente);
		
		JPanel panelDeLugares=new JPanel();
		
		panelDeControl.add(pared);
		panelDeControl.add(piso);
		
		panelDeLugares.add(agregar);
		panelDeLugares.add(eliminar);
		
		
		pane.add(l1);
		pane.add(guardar);
		pane.add(buscarRutas);
		pane.add(abrirMapa);
		pane.add(panelDeControl);
		pane.add(canvas);
		pane.add(l2);
		pane.add(lugaresSP);
		pane.add(panelDeLugares);
		
		
		
		// Posicionamiento
		Dimension dim=l1.getPreferredSize();
        l1.setBounds(WIDTH/2-dim.width/2,10,dim.width, dim.height);
        
        dim=guardar.getPreferredSize();
        guardar.setBounds(10,80,dim.width, dim.height);
		
        dim=buscarRutas.getPreferredSize();
        buscarRutas.setBounds(120,80,dim.width, dim.height);
        
        dim=abrirMapa.getPreferredSize();
        abrirMapa.setBounds(280,80,dim.width, dim.height);
        
        
        dim=panelDeControl.getPreferredSize();
        panelDeControl.setBounds(0,130,WIDTH, dim.height);
		
        canvas.setBounds(10,185,600,600);
        
        dim=l2.getPreferredSize();
        l2.setBounds(860,185,dim.width, dim.height);
        
        lugaresSP.setBounds(700,220,400, 500);
        
        dim=panelDeLugares.getPreferredSize();
        panelDeLugares.setBounds(700,740,dim.width, dim.height);
        
	}
}
