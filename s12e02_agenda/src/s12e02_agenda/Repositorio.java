package s12e02_agenda;

import java.util.ArrayList;
import java.util.TreeMap;

abstract class Repositorio <T>{	
	protected TreeMap<String, T> mapa;
	protected String typename;
	
	public Repositorio(String typename) {
		mapa = new TreeMap<String, T>();
		this.typename = typename;
	}
	
	T get(String key){
		if(!mapa.containsKey(key))
			throw new RuntimeException("fail: " + typename + " " + key + " nao existe");
		return mapa.get(key);
	}
	
	void remove(String key){
		if(!mapa.containsKey(key))
			throw new RuntimeException("fail: " + typename + " " + key + " nao existe");
		mapa.remove(key);
	}
	
	ArrayList<T> getAll(){
		ArrayList<T> all = new ArrayList<T>();
		for(T elem: mapa.values())
			all.add(elem);
		return all;
	}
	
	ArrayList<String> getKeys(){
		ArrayList<String> all = new ArrayList<String>();
		for(String elem: mapa.keySet())
			all.add(elem);
		return all;
	}
	
	public String toString() {
		String saida = "";
		for(T elem: mapa.values())
			saida += elem + "\n";
		return saida;
	}
}

class RepIndexado<T> extends Repositorio<T> {

	public RepIndexado(String typename) {
		super(typename);
	}
	public void add(String key, T t) {
		if(mapa.containsKey(key))
			throw new RuntimeException("fail: " + typename + " " + key + " ja existe");
		mapa.put(key, t);
	}	
}

class RepContinuo<T> extends Repositorio<T>{
	private int nextId = 0;
	public RepContinuo(String typename) {
		super(typename);
	}

	public void add(T t) {
		String key = "" + nextId;
		nextId += 1;
		mapa.put(key, t);
	}
}


