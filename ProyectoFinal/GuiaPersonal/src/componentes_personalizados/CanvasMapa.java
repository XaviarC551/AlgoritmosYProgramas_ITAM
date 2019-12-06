package componentes_personalizados;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;


/*
 * 
 * "Canvas Mapa"
 * 
 * Por Javier Prieto
 * 
 * Esta clase permite modificar y mostrar
 * el mapa en en el editor y la vista de
 * búsqueda de rutas.
 * 
 * */

public class CanvasMapa extends Canvas{
	
	public static final int PARED=0;
	public static final int PISO=1;
	public static final int CAMINO=2;
	public static final int LUGAR=3;
	
	private Image imagen;
	private Graphics2D g2;
	
	public CanvasMapa() {
		imagen=null;
		g2=null;
		
		
	}
	
	public void pintar(int tipo,int x,int y,int cuadro) {
		if(imagen==null) {
			int w=getSize().width;
			int h=getSize().height;
			imagen=createImage(w,h);
		    g2=(Graphics2D) imagen.getGraphics();
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    g2.setColor(Color.WHITE);
		    g2.fillRect(0, 0, w, h);
		}
		if(tipo>=0&&tipo<=3) {
			if(tipo==PARED)
				g2.setColor(Color.BLACK);
			else if(tipo==PISO)
				g2.setColor(Color.WHITE);
			else if(tipo==CAMINO)
				g2.setColor(Color.GREEN);
			else
				g2.setColor(Color.BLUE);
			
			g2.fillRect(x, y, cuadro, cuadro);
		}
	}
	
	public void limpiar() {
		int w=getSize().width;
		int h=getSize().height;
		imagen=createImage(w,h);
	    g2=(Graphics2D) imagen.getGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    g2.fillRect(0, 0, w, h);
	}

	@Override
	public void paint(Graphics g) {
		if(imagen==null) {
			int w=getSize().width;
			int h=getSize().height;
			imagen=createImage(w,h);
		    g2=(Graphics2D) imagen.getGraphics();
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    g2.setColor(Color.WHITE);
		    g2.fillRect(0, 0, w, h);
		}
		g.drawImage(imagen, 0, 0, null);
	}
}
