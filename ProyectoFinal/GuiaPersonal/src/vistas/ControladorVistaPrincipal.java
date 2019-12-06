package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import libs.Mapa;

/*
 * 
 * "Controlador Vista Principal"
 * 
 * Por Javier Prieto 
 * 
 * Agrega los Listeners a los 2 botones
 * para cambiar de vista cuando el usuario
 * los presione.
 * El bot�n de "abrir mapa" abre
 * una caja de di�logo que le pide al usuario
 * el nombre del mapa que quiere utilizar.
 * 
 * */

public class ControladorVistaPrincipal extends VistaPrincipal{
	public ControladorVistaPrincipal() {
		super("Gu�a Personal");
		this.nuevoMapa.addActionListener(new EscuchaNuevoMapa());
		this.abrirMapa.addActionListener(new EscuchaAbrirMapa());
	}
	
	public class EscuchaNuevoMapa implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			new ControladorVistaEditarMapa();
		}
	}
	public JFrame getFrame() {
		return this;
	}
	public class EscuchaAbrirMapa implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String s = (String)JOptionPane.showInputDialog(
					"Escribe el nombre del mapa:");
			if(s!=null && !s.equals("")) {
				Mapa mapa=new Mapa();
				if(mapa.importarMapa(s)) {
					setVisible(false);
					new ControladorVistaBuscarRutas(mapa,s);
				}
				else
					JOptionPane.showMessageDialog(getFrame(),
							"No se encontr� el archivo especificado","Error!",
							JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
