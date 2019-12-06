package libs;

/*
 * 
 * "Manejador de Arreglos Genérico"
 * 
 * Por Javier Prieto 
 * 
 * Esta clase es utilizada para manipular
 * los arreglos paralelos de posiciones
 * y nombres de los lugares de interés.
 * 
 * */

public class ManejadorDeArreglosGenerico {

	//encuentra el elemento más pequeño 
	public static <T extends Comparable<T>> T minArreglo(T[] x, int n) {
		T min=x[0];
		for(int i=1; i<n;i++)
			if(min.compareTo(x[i])>0)
				min=x[i];
		return min;
	}
	public static <T extends Comparable<T>> int minIndArreglo(T[] x, int n) {
		int minIndice=0;
		for(int i=1; i<n;i++)
			if(x[minIndice].compareTo(x[i])>0) {
				minIndice=i;
			}
		return minIndice;
	}
	//encuentra el indice del elemento más grande 
	public static <T extends Comparable<T>> int mayIndArreglo(T[] x, int n) {
		int maxIndice=0;
		for(int i=1; i<n;i++)
			if(x[maxIndice].compareTo(x[i])<0) {
				maxIndice=i;
			}
		return maxIndice;
	}
	//encuentra el indice del elemento más pequeño desde un indice dado 
	public static <T extends Comparable<T>> int minIndArregloPos(T[] x, int j, int n) {
		int indice=j;
		for(int i=j+1; i<n;i++)
			if(x[indice].compareTo(x[i])>0)
				indice=i;
		return indice;
	}
	//intercambiar dos elementos 
	public static <T> void intercambio(T[] x,int ind1, int ind2) {
		T aux;
		aux=x[ind1];
		x[ind1]=x[ind2];
		x[ind2]=aux;
	}
	//ordena un arreglo de elementos de menor a mayor
	public static <T extends Comparable<T>> void ordenaArreglo (T[] x, int n) {
		int y;
		for(int i=0;i<n;i++) {
			y=minIndArregloPos(x,i,n);
			intercambio(x,i,y);
		}
	}
	//suma los datos de un arreglo numerico
	public static <T extends Number> Double sumarArreglo(T[] a,int n) {
		Double sum=0.0;
		for(int i=0;i<n;i++) {
			sum+=a[i].doubleValue();
		}
		return sum;
	}
	//calcula el promedio de un arreglo numerico
	public static <T extends Number> Double promedioArreglo(T[]a,int n) {
		return sumarArreglo(a,n)/n;
	}
	//calcula cuanto datos son mayores a un dato v dado
	public static <T extends Comparable<T>> int getMayoresAV(T[] a,int n,T v) {
		int res=0;
		for(int i=0;i<n;i++) {
			if(a[i].compareTo(v)>0)
				res++;
		}
		return res;
	}
	//calcula cuanto datos son menores a un dato v dado
	public static <T extends Comparable<T>> int getMenoresAV(T[] a,int n,T v) {
		int res=0;
		for(int i=0;i<n;i++) {
			if(a[i].compareTo(v)<0)
				res++;
		}
		return res;
	}
	//corrimiento a la derecha
	public static <T> void corrimientoInsertarDato(T[] datos,int pos, int n){
		for(int i=n;i>pos;i--){
			datos[i]=datos[i-1];
		}
	}
	
	//corrimiento a la izquierda
	public static <T> void corrimientoEliminarDato(T[] datos,int size,int pos){
		for(int i=pos;i<size-1;i++){
			datos[i]=datos[i+1];
		}
	}
	
	//busqueda secuencial de un elemento en un arreglo desordenado
	public static <T> int busquedaDesordenada(T[] datos, int n, T val){
			int i=0;
			while(i<n && !datos[i].equals(val))
				i++;
			if(i==n)
				i=-1;
			return i;
	}
	//busqueda secuencial de un elemento en un arreglo ordenado
	public static <T extends Comparable<T>> int busquedaOrdenada(T[] datos, int n, T val){
		return busquedaDesordenada(datos,n,val);
	}
	
	////busqueda binaria de un elemento en un arreglo ordenado
	public static <T extends Comparable<T>> int busquedaBinaria(T[]arr,T x, int n){
		int inicio, mitad, fin,pos = 0;
		
		inicio =0;
		fin= (n-1);
		mitad =(inicio+fin)/2;
		while(x !=arr[mitad] && inicio<=fin){
			if(arr[mitad].compareTo(x)<0)
				inicio= mitad+1;
			else
				fin=mitad-1;
			mitad= (inicio+fin)/2;
		}
		if(x==arr[mitad])
			pos= mitad;
		else{
			pos= inicio;
		}
		
		return pos;
	}
	//inserta un dato en un arreglo genérico ordenado
	public static <T extends Comparable<T>> int insertaValorOrd(T[]datos,int size,T val){
		int pos;
		if(size<datos.length){
			pos=busquedaOrdenada(datos,size,val);
			if(pos<0){
				pos=-(pos+1);
				corrimientoInsertarDato(datos,pos,size);
				datos[pos]=val;
				size++;
			}
		}
		return size;
	}
	//inserta un dato en un arreglo genérico
		public static <T > int insertaValor(T[]datos,int size,T val){
			int pos;
			if(size<datos.length){
				pos=busquedaDesordenada(datos,size,val);
				if(pos<0){
					datos[size++]=val;
				}
			}
			return size;
		}
	//elimina un dato de un arreglo genérico
	public static <T extends Comparable<T>> int eliminarValor(T[]dato,int size, T val){
		int pos;
		pos= busquedaOrdenada(dato,size,val);
		if(pos>=0){
			corrimientoEliminarDato(dato,size, pos);
			size --;
		}
		return size;
	}
}
