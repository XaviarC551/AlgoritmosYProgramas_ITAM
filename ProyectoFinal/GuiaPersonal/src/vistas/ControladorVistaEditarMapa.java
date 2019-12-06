package vistas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import componentes_personalizados.CanvasMapa;
import libs.Mapa;
import libs.Posicion;

/*
 * 
 * "Controlador Vista Editar Mapa"
 * 
 * Por Javier Prieto 
 * 
 * Agrega los Listeners a los botones,
 * el mapa y la lista para modificar
 * el objeto mapa.
 * Cuando el usario cambia de vista,
 * se transfiere el mapa como argumento
 * al constructor del siguiente
 * controlador.
 * 
 * */

public class ControladorVistaEditarMapa extends VistaEditarMapa{
	private boolean seleccionandoLugar;
	private Posicion lugarAnt;
	private boolean esPared;
	private String nombre;
	private String nombreAnt;
	private Mapa mapa;
	
	
	public ControladorVistaEditarMapa() {
		super("Guía Personal");
		mapa=new Mapa();
		esPared=true;
		seleccionandoLugar=false;
		
		this.canvas.addMouseListener(new CanvasMouseAdapter());
		
		this.listaDeLugares.setListData(new String[] {});
		
		this.pared.setBackground(Color.GRAY);
		this.piso.setBackground(Color.WHITE);
		this.pared.addActionListener(new EscuchaPared());
		this.piso.addActionListener(new EscuchaPiso());
		this.agregar.addActionListener(new EscuchaAgregar());
		this.listaDeLugares.addListSelectionListener(new EscuchaListaDeLugares());
		this.eliminar.addActionListener(new EscuchaEliminar());
		this.buscarRutas.addActionListener(new EscuchaBuscarRutas());
		this.guardar.addActionListener(new EscuchaGuardar());
		this.abrirMapa.addActionListener(new EscuchaAbrirMapa());
	}
	public ControladorVistaEditarMapa(Mapa mapa,String nombre) {
		this();
		this.mapa=mapa;
		for(int i=0;i<30;i++)
			for(int j=0;j<30;j++) {
				if(mapa.getTipo(j, i)==Mapa.PARED)
					canvas.pintar(CanvasMapa.PARED, j*20, i*20, 20);
			}
		String[] opciones= mapa.getNombres().toArray(new String[mapa.getNumLugares()]);
		listaDeLugares.setListData(opciones);
		listaDeLugares.repaint();
		if(opciones.length>0)
			listaDeLugares.setSelectedIndex(0);
		nombreAnt=nombre;
	}
	
	public JFrame getFrame() {
		return this;
	}
	
	public class CanvasMouseAdapter extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			int x=e.getX()/20;
			int y=e.getY()/20;
			if(!seleccionandoLugar) {
				if(mapa.estaOcupado(x, y)) {
					JOptionPane.showMessageDialog(getFrame(),
							"Seleccionaste la ubicación de un lugar","Error!",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(esPared) {
						canvas.pintar(CanvasMapa.PARED,x*20,y*20,20);
						mapa.setPosicion(Mapa.PARED, x,y);
					}
					else {
						canvas.pintar(CanvasMapa.PISO,x*20, y*20,20);
						mapa.setPosicion(Mapa.PISO, x, y);
					}
					canvas.repaint();
				}
			}
			else {
				
				if(mapa.esPared(x,y)) {
					JOptionPane.showMessageDialog(getFrame(),
							"Ubicación inválida","Error!",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					mapa.altaLugar(nombre, x, y);
					String[] opciones= mapa.getNombres().toArray(new String[mapa.getNumLugares()]);
					listaDeLugares.setListData(mapa.getNombres().toArray());
					listaDeLugares.setSelectedIndex(opciones.length-1);
					listaDeLugares.repaint();
					
				}
				agregar.setEnabled(true);
				eliminar.setEnabled(true);
				seleccionandoLugar=false;
			}
	    	  
	    }
	}
	public class EscuchaPared implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			pared.setBackground(Color.GRAY);
			piso.setBackground(Color.WHITE);
			esPared=true;
		}
	}
	public class EscuchaPiso implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			pared.setBackground(Color.WHITE);
			piso.setBackground(Color.GRAY);
			esPared=false;
		}
	}
	public class EscuchaAgregar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String s = (String)JOptionPane.showInputDialog(
					"Escribe el nombre del lugar:");
			if(s!=null) {
				if(s.equals(""))
					JOptionPane.showMessageDialog(getFrame(),
							"Nombre inválido","Error!",
							JOptionPane.ERROR_MESSAGE);
				else {
					if(!mapa.existeLugar(s)) {
						JOptionPane.showMessageDialog(getFrame(),
								"Selecciona la ubicación en el mapa","Ubicación",
								JOptionPane.PLAIN_MESSAGE);
						seleccionandoLugar=true;
						nombre=s;
						agregar.setEnabled(false);
						eliminar.setEnabled(false);
					}
					else {
						JOptionPane.showMessageDialog(getFrame(),
								"Ya existe un lugar con ese nombre","Error!",
								JOptionPane.ERROR_MESSAGE);
					}
				}
					
			}
		}
	}
	public class EscuchaListaDeLugares implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			int idx=listaDeLugares.getSelectedIndex();
			if(lugarAnt!=null)
				canvas.pintar(CanvasMapa.PISO, lugarAnt.getX()*20, lugarAnt.getY()*20,20);
			lugarAnt=mapa.getPosicion(idx);
			if(lugarAnt!=null)
				canvas.pintar(CanvasMapa.LUGAR, lugarAnt.getX()*20, lugarAnt.getY()*20, 20);
			canvas.repaint();
		}
		
	}
	public class EscuchaEliminar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int idx=listaDeLugares.getSelectedIndex();
			lugarAnt=mapa.getPosicion(idx);
			if(idx>=0) {
				canvas.pintar(CanvasMapa.PISO, lugarAnt.getX()*20, lugarAnt.getY()*20, 20);
				mapa.bajaLugar(idx);
				String[] opciones= mapa.getNombres().toArray(new String[mapa.getNumLugares()]);
				listaDeLugares.setListData(opciones);
				listaDeLugares.repaint();
				if(opciones.length>0)
					listaDeLugares.setSelectedIndex(opciones.length-1);
				canvas.repaint();
			}
			
		}
	}
	public class EscuchaBuscarRutas implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			new ControladorVistaBuscarRutas(mapa,nombreAnt);
		}
	}
	public class EscuchaGuardar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String s=null;
			if(nombreAnt!=null)
				s = (String)JOptionPane.showInputDialog(
					"Escribe el nombre del mapa:",nombreAnt);
			else
				s = (String)JOptionPane.showInputDialog(
						"Escribe el nombre del mapa:");
			if(s!=null && !s.equals("")){
				nombreAnt=s;
			}
		}
	}
	public class EscuchaAbrirMapa implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Object[] options = {"Sí",
                    "No"};
			int n = JOptionPane.showOptionDialog(getFrame(),
			    "Los cambios no guardados se perderán."+
			    "\n¿Deseas continuar?",
			    "Guarda tus Cambios",
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[1]);
			if(n==0) {
				String s = (String)JOptionPane.showInputDialog(
						"Escribe el nombre del mapa:");
				if(s!=null && !s.equals("")) {
					Mapa mapa=new Mapa();
					if(mapa.importarMapa(s)) {
						setVisible(false);
						new ControladorVistaEditarMapa(mapa,s);
					}
					else
						JOptionPane.showMessageDialog(getFrame(),
								"No se encontró el archivo especificado","Error!",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
