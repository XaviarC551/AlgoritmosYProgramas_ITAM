package libs;

/*
 * 
 * "Pair"
 * 
 * Por Javier Prieto 
 * 
 * Esta clase sirve para ordenar las
 * posiciones en la cola de prioridad
 * de la búsqueda. La función compareTo del
 * Pair solo considera el valor de K (la
 * llave genérica).
 * 
 * */

public class Pair <K extends Comparable,V> implements Comparable<Pair>{
	private K key;
	private V value;
	public Pair(K key,V value) {
		this.key=key;
		this.value=value;
	}
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public int compareTo(Pair p) {
		return key.compareTo(p.key);
	}

	@Override
	public String toString() {
		return "Pair [key=" + key + ", value=" + value + "]";
	}
	
}
