package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import componentes_personalizados.CanvasMapa;
import libs.Mapa;
import libs.Posicion;

/*
 * 
 * "Controlador Vista Buscar Rutas"
 * 
 * Por Javier Prieto 
 * 
 * Agrega los Listeners a los botones,
 * el mapa y la lista para mostrar la ruta
 * indicada por el usuario.
 * Al igual que en el controlador del editor
 * de mapas, cuando el usario cambia de vista,
 * se transfiere el mapa como argumento al
 * constructor del sigueinte controlador.
 * 
 * */

public class ControladorVistaBuscarRutas extends VistaBuscarRutas{
	private Mapa mapa;
	private Posicion aAnt;
	private Posicion bAnt;
	private ArrayList<Posicion> ruta;
	private String nombre;
	public ControladorVistaBuscarRutas(Mapa mapa) {
		super("Guía Personal");
		nombre=null;
		this.mapa=mapa;
		for(int i=0;i<30;i++) {
			for(int j=0;j<30;j++) {
				if(mapa.getTipo(j, i)==Mapa.PARED)
					canvas.pintar(CanvasMapa.PARED, j*20, i*20, 20);
			}
		}
		if(mapa.getNumLugares()<2) {
			JOptionPane.showMessageDialog(getFrame(),
					"No hay suficientes lugares para trazar rutas","Lugares Insuficientes",
					JOptionPane.PLAIN_MESSAGE);
		}
		else {
			String[] opciones= mapa.getNombres().toArray(new String[mapa.getNumLugares()]);
			listaDeLugares.setListData(opciones);
			listaDeLugares.repaint();
			for(int i=0;i<opciones.length;i++)
				this.puntoA.addItem(opciones[i]);
			for(int i=1;i<opciones.length;i++)
				this.puntoB.addItem(opciones[i]);
			this.puntoA.addActionListener(new EscuchaPuntoA());
			this.puntoB.addActionListener(new EscuchaPuntoB());
			this.buscarRuta.addActionListener(new EscuchaBuscarRuta());
		}
		this.editarMapa.addActionListener(new EscuchaEditarMapa());
		this.nuevoMapa.addActionListener(new EscuchaNuevoMapa());
		
	}
	public ControladorVistaBuscarRutas(Mapa mapa,String nombre) {
		this(mapa);
		this.nombre=nombre;
	}
	public JFrame getFrame() {
		return this;
	}
	public class EscuchaPuntoA implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox cb=(JComboBox)e.getSource();
			int selected=cb.getSelectedIndex();
			System.out.println(selected);
			if(mapa.getNumLugares()>=2) {
				puntoB.removeAllItems();
				String[] opciones= mapa.getNombres().toArray(new String[mapa.getNumLugares()]);
				listaDeLugares.setListData(opciones);
				listaDeLugares.repaint();
				for(int i=0;i<mapa.getNumLugares();i++)
					if(i!=selected)
						puntoB.addItem(opciones[i]);
				puntoB.setSelectedItem(0);
				
				puntoB.repaint();
				
			}
		}
	}
	public class EscuchaPuntoB implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox cb=(JComboBox)e.getSource();
			
			
		}
	}
	public class EscuchaBuscarRuta implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(ruta!=null) 
				for(int i=0;i<ruta.size();i++) {
					canvas.pintar(CanvasMapa.PISO, ruta.get(i).getX()*20,
							ruta.get(i).getY()*20, 20);
				}
			if(aAnt!=null)
				canvas.pintar(CanvasMapa.PISO, aAnt.getX()*20, aAnt.getY()*20, 20);
			if(bAnt!=null)
				canvas.pintar(CanvasMapa.PISO, bAnt.getX()*20, bAnt.getY()*20, 20);
			
			ruta=mapa.trazarRuta((String)puntoA.getSelectedItem(),
					(String)puntoB.getSelectedItem());
			
			
			
			if(ruta==null) {
				JOptionPane.showMessageDialog(getFrame(),
						"No hay manera de llegar del punto A al punto B","Ruta no Encontrada",
						JOptionPane.PLAIN_MESSAGE);
			}
			else {
				for(int i=0;i<ruta.size();i++) {
					canvas.pintar(CanvasMapa.CAMINO, ruta.get(i).getX()*20,
							ruta.get(i).getY()*20, 20);
				}
				
				aAnt=mapa.getPosicion((String)puntoA.getSelectedItem());
				canvas.pintar(CanvasMapa.LUGAR, aAnt.getX()*20, aAnt.getY()*20, 20);
				
				
				bAnt=mapa.getPosicion((String)puntoB.getSelectedItem());
				canvas.pintar(CanvasMapa.LUGAR, bAnt.getX()*20, bAnt.getY()*20, 20);
				
				canvas.repaint();
			}
		}
	}
	public class EscuchaEditarMapa implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new ControladorVistaEditarMapa(mapa,nombre);
		}
	}
	public class EscuchaNuevoMapa implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new ControladorVistaEditarMapa();
		}
	}
}
