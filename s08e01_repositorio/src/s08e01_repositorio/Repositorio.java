package s08e01_repositorio;

import java.util.ArrayList;

//collection - Java
//container - C++

class Sapato {
	int numero;
	String cor;
	public Sapato(int n, String cor) {
		this.numero = n;
		this.cor = cor;
	}
	public String toString() {
		return "Sapato no= " + this.numero + " cor=" + this.cor;
	}
}

class Ostra <Tipo> {
	private Tipo value;
	private boolean isOpened;
	
	public Ostra(Tipo value) {
		this.value = value;
		this.isOpened = false;
	}
	
	public void abrir(){
		if(isOpened)
			throw new RuntimeException("fail: ela esta aberta abestado");
		isOpened = true;
	}
	
	public boolean isOpen() {
		return this.isOpened;
	}
	
	Tipo getValue(){
		if(!isOpened)
			throw new RuntimeException("fail: ostra esta fechada");
		return value;
	}
	
	void removeValue() {
		if(value == null)
			throw new RuntimeException("fail: nao existe valor atribuido");
		this.value = null;
	}
	public static void main(String[] args) {
		Ostra<Sapato> ostra = new Ostra<Sapato>(new Sapato(42, "branco"));
		try {
			ostra.getValue();//excessao
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(ostra.isOpen() ? "ostra aberta" : "ostra fechada");
		ostra.abrir();
		try {
			ostra.abrir();//excessao
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(ostra.getValue());
		ostra.removeValue();
		System.out.println(ostra.getValue());
	}
}


class Elemento<T>{
	public String key;
	public T value;
	
	public Elemento(String key, T value) {
		this.key = key;
		this.value = value;
	}
	public String toString() {
		return "" + key + ":" + value;
	}
}

public class Repositorio <T>{	
	private ArrayList<Elemento<T>> vet;
	private String typename;
	private int nextId = 0;
	
	public Repositorio(String typename) {
		vet = new ArrayList<Elemento<T>>();
		this.typename = typename;
	}
	
	public void add(String key, T t) {
		for(Elemento<T> elem : vet)
			if(elem.key.equals(key))
				throw new RuntimeException("fail: " + typename + " " + key + " ja existe");
		vet.add(new Elemento<T>(key, t));
	}
	
	public void add(T t) {
		String key = "" + nextId;
		nextId += 1;
		
		for(Elemento<T> elem : vet)
			if(elem.key.equals(key)) {
				elem.value = t;
				return;
			}
		vet.add(new Elemento<T>(key, t));
	}
	
	T get(String key){
		for(Elemento<T> elem : vet)
			if(elem.key.equals(key))
				return elem.value;
		throw new RuntimeException("fail: " + typename + " " + key + " nao existe");
	}
	
	void remove(String key){
		for(int i = 0; i < vet.size(); i++) {
			if(vet.get(i).key.equals(key)) {
				vet.remove(i);
				return;
			}
		}
		throw new RuntimeException("fail: " + typename + " " + key + " nao existe");
	}
	
	ArrayList<T> getAll(){
		ArrayList<T> all = new ArrayList<T>();
		for(Elemento<T> elem : vet)
			all.add(elem.value);
		return all;
	}
	
	public String toString() {
		String saida = "";
		for(Elemento<T> elem : vet)
			saida += elem + "\n";
		return saida;
	}
	
	public static void main(String[] args) {
		Repositorio<Sapato> sapataria = new Repositorio<Sapato>("sapato");
		sapataria.add("zibia", new Sapato(35, "rosa xok"));
		sapataria.add("edson", new Sapato(40, "marrom"));
		try {
			sapataria.add("zibia", new Sapato(14, "galinha pintadinha"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		sapataria.add("david", new Sapato(42, "verde"));
		sapataria.add("romenia", new Sapato(40, "nude"));
		
		System.out.println(sapataria);
		
		Repositorio<String> apelidos = new Repositorio<String>("apelido");
		apelidos.add("davizinho");
		apelidos.add("abestado");
		apelidos.add("chuck noriz");
		apelidos.add("mosca");
		apelidos.add("seninha");
		System.out.println(apelidos);
	}
}











