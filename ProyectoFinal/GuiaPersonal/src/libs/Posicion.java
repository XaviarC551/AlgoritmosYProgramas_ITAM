package libs;

/*
 * 
 * "Posicion"
 * 
 * Por Javier Prieto 
 * 
 * Esta clase sirve para agrupar
 * las x's y y's en el mapa de los
 * lugares de interés. 
 * 
 * */

public class Posicion {
	private int x;
	private int y;
	
	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Posicion clone() {
		return new Posicion(x,y);
	}
	
	@Override
	public String toString() {
		StringBuilder str=new StringBuilder();
		str.append("Posicion[x: ");
		str.append(this.x);
		str.append(", y: ");
		str.append(this.y);
		str.append("]");
		return str.toString();
	}
	@Override
	public boolean equals(Object obj) {
		Posicion p=(Posicion)obj;
		return x==p.x&&y==p.y;
	}

}
