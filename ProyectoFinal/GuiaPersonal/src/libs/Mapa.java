package libs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 
 * "Mapa"
 * 
 * Por Javier Prieto 
 * 
 * La clase mapa maneja prácticamente
 * todos los datos del programa:
 * guarda las paredes del mapa en un
 * arreglo bidimensional, guarda las
 * los nombres de los lugares de interés
 * y su posicionamiento en arreglos paralelos,
 * exporta sus datos a archivos y los importa,
 * realiza búsquedas para asegurar que ningún
 * bloque de pared coincida con la localización
 * de un lugar de interés, realiza la búsqueda
 * y regresa el camino más corto entre los 2
 * puntos elegidos del mapa usando Dijkstra 
 * y contiene los métodos para dar de alta
 * o de baja lugares de interés.
 * 
 * */

public class Mapa {
	public static final int PARED=-1;
	public static final int PISO=0;
	
	private final int MAX_I=2147483647;
	
	private final int MAX_LUGARES=50;
	private final int DIM=30;
	
	
	private Integer[][] mapa;
	
	private String[] nombresLugares;
	private Posicion[] posicionesLugares;
	private int numLugares;
	public Mapa() {
		nombresLugares=new String[MAX_LUGARES];
		posicionesLugares=new Posicion[MAX_LUGARES];
		mapa=new Integer[DIM][DIM];
		for(int i=0;i<DIM;i++) {
			for(int j=0;j<DIM;j++) {
				mapa[i][j]=PISO;
			}
		}
	}
	
	public boolean importarMapa(String nombre) {
		boolean res=true;
		try {
			Scanner sc=new Scanner(new File(nombre+".map"));
			for(int i=0;i<DIM;i++) {
				for(int j=0;j<DIM;j++) {
					mapa[i][j]=sc.nextInt();
				}
			}
			int lugares=sc.nextInt();
			sc.nextLine();
			for(int i=0;i<lugares;i++) {
				int x=sc.nextInt();
				int y=sc.nextInt();
				sc.nextLine();
				String nombreDeLugar=sc.nextLine();
				altaLugar(nombreDeLugar,x,y);
			}
		}
		catch(Exception e) {
			res=false;
		}
		return res;
	}
	public boolean exportarMapa(String nombre) {
		boolean res=true;
		try {
			File archivo = new File(nombre+".map");
			archivo.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
			for(int i=0;i<DIM;i++) {
				for(int j=0;j<DIM;j++) {
					writer.write(""+mapa[i][j]+" ");
				}
				writer.newLine();
			}
			writer.write(""+numLugares);
			for(int i=0;i<numLugares;i++) {
				writer.write("\n"+posicionesLugares[i].getX()+" "+
						posicionesLugares[i].getY());
				writer.write("\n"+nombresLugares[i]);
				
			}
			writer.close();
			
		}
		catch(Exception e) {
			res=false;
		}
		return res;
	}
	public int getNumLugares() {
		return numLugares;
	}
	public boolean setPosicion(int tipo, int x, int y) {
		boolean res=false;
		if(tipo>=-1 && tipo<=0 && x>=0 && x<DIM&& y>=0 && y<DIM) {
			res=true;
			mapa[y][x]=tipo;
		}
		return res;
	}
	public boolean existeLugar(String nombre) {
		boolean res=false;
		if(ManejadorDeArreglosGenerico.busquedaDesordenada(nombresLugares, numLugares, nombre)>=0)
			res=true;
		return res;
	}
	public boolean estaOcupado(int x, int y) {
		boolean res=false;
		int i=0;
		while(i<numLugares && !res) {
			if(posicionesLugares[i].getX()==x &&
					posicionesLugares[i].getY()==y)
				res=true;
			i++;
		}
		return res;
	}
	public boolean esPared(int x, int y) {
		boolean res=true;
		if(x>=0 && x<DIM&& y>=0 && y<DIM)
			res=mapa[y][x]==PARED;
		return res;
	}
	public boolean altaLugar(String nombre,int x,int y) {
		boolean res=false;
		if(numLugares<MAX_LUGARES) {
			if(x>=0 && x<DIM&& y>=0 && y<DIM) {
				if(ManejadorDeArreglosGenerico.busquedaDesordenada(nombresLugares, numLugares, nombre)<0) {
					res=true;
					ManejadorDeArreglosGenerico.insertaValor(nombresLugares, numLugares, nombre);
					numLugares=ManejadorDeArreglosGenerico.insertaValor(posicionesLugares, numLugares, new Posicion(x,y));
				}
			}
		}
		return res;
	}
	public boolean bajaLugar(int indice) {
		boolean res=false;
		if(indice>=0 && indice<numLugares) {
			ManejadorDeArreglosGenerico.corrimientoEliminarDato(nombresLugares, numLugares, indice);
			numLugares--;
		}
		return res;
	}
	public ArrayList<String> getNombres(){
		ArrayList<String> res=new ArrayList<>();
		for(int i=0;i<numLugares;i++)
			res.add(nombresLugares[i]);
		return res;
	}
	
	public Posicion getPosicion(int indice) {
		Posicion res=null;
		if(indice>=0 && indice<numLugares)
			res=posicionesLugares[indice];
		return res;
	}
	public Posicion getPosicion(String nombre) {
		Posicion res=null;
		int idx=ManejadorDeArreglosGenerico.busquedaDesordenada(nombresLugares, numLugares, nombre);
		if(idx>=0)
			res=posicionesLugares[idx];
		return res;
	}
	
	public int getTipo(int x,int y) {
		int res=PARED;
		if(x>=0 && x<DIM&& y>=0 && y<DIM)
			res=mapa[y][x];
		return res;
	}
	
	private boolean estaEnLosLimites(Posicion pos) {
		boolean res=false;
		if(pos.getX()>=0&&pos.getX()<DIM&&
				pos.getY()>=0&&pos.getY()<DIM)
			res=true;
		return res;
	}
	private boolean estaEnLosLimites(int x, int y) {
		return estaEnLosLimites(new Posicion(x,y));
	}
	public ArrayList<Posicion> trazarRuta(String a, String b) {
		ArrayList<Posicion> res=null;
		
		boolean found=false;
		
		int pa,pb;
		
		pa=ManejadorDeArreglosGenerico.busquedaDesordenada(nombresLugares, numLugares, a);
		pb=ManejadorDeArreglosGenerico.busquedaDesordenada(nombresLugares, numLugares, b);
		
		
		Posicion[] posiciones=new Posicion[2];
		posiciones[0]=posicionesLugares[pa];
		posiciones[1]=posicionesLugares[pb];
		
		Integer[][] temp=new Integer[DIM][DIM];
		for(int i=0;i<DIM;i++) {
			for(int j=0;j<DIM;j++) {
				temp[i][j]=MAX_I;
			}
		}
		PriorityQueue<Pair<Integer,Posicion>> queue=new PriorityQueue<>();
		queue.add(new Pair(0,posiciones[0]));
		Pair<Integer,Posicion> p;
		Integer val;
		Posicion pos;
		while(!queue.isEmpty()&&!found) {
			p=queue.peek();
			queue.remove();
			val=p.getKey();
			pos=p.getValue();
			if(estaEnLosLimites(pos)&&
					mapa[pos.getY()][pos.getX()]!=PARED) {
				if(pos.equals(posiciones[1]))
					found=true;
				if(val<temp[pos.getY()][pos.getX()]) {
					temp[pos.getY()][pos.getX()]=val;
					queue.add(new Pair(val+1,new Posicion(pos.getX()+1,pos.getY())));
					queue.add(new Pair(val+1,new Posicion(pos.getX()-1,pos.getY())));
					queue.add(new Pair(val+1,new Posicion(pos.getX(),pos.getY()+1)));
					queue.add(new Pair(val+1,new Posicion(pos.getX(),pos.getY()-1)));
				
				}
			}
		}
		if(found) {
			pos=posiciones[1].clone();
			res=new ArrayList<>();
			
			int idx=temp[pos.getY()][pos.getX()];
			while(idx>1) {
				if(estaEnLosLimites(pos.getX()-1,pos.getY())&&
						temp[pos.getY()][pos.getX()-1]==idx-1)
					pos.setX(pos.getX()-1);
				if(estaEnLosLimites(pos.getX()+1,pos.getY())&&
						temp[pos.getY()][pos.getX()+1]==idx-1)
					pos.setX(pos.getX()+1);
				if(estaEnLosLimites(pos.getX(),pos.getY()-1)&&
						temp[pos.getY()-1][pos.getX()]==idx-1)
					pos.setY(pos.getY()-1);
				if(estaEnLosLimites(pos.getX(),pos.getY()+1)&&
						temp[pos.getY()+1][pos.getX()]==idx-1)
					pos.setY(pos.getY()+1);
				
				res.add(new Posicion(pos.getX(),pos.getY()));
				idx--;
			}
		}
		return res;
	}
	
}
