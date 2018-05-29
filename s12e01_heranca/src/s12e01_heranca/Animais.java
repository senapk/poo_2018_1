package s12e01_heranca;

import java.util.ArrayList;

abstract class Rabifero {
	public abstract String morderRabo();
}

class Dog extends Rabifero{

	public String morderRabo() {
		return "auai";
	}
}

class Cat extends Rabifero{
	protected String nome;

	public Cat(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String miar() {
		return "miau";
	}
	
	public String toString() {
		return "Cat " + this.nome;
	}

	@Override
	public String morderRabo() {
		return this.miar() + "ai";
		
	}
}

class CatRusso extends Cat{
	private String cor;
	public CatRusso(String nome, String cor) {
		super(nome);
		this.cor = cor;
	}
	public CatRusso() {
		super("Thaik");
		this.cor = "branco";
	}
	public String getCor() {
		return cor;
	}
	
	//sobrescrita do metodo
	public String miar() {
		return "miovisk";
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	//sobrescrita
	public String toString() {
		return super.toString()+ "ovisk cor: " + this.cor;
	}
}

class GatoRussoUndead extends CatRusso{
	private int npatas;
	public GatoRussoUndead(String nome, int npatas) {
		super(nome, "vermelho");
		this.npatas = npatas;
	}
	
	public GatoRussoUndead() {
		super();
		this.npatas = 0;
	}
	
	public void morderGato(Cat comida) {
		if(comida instanceof GatoRussoUndead) {
			GatoRussoUndead undead = (GatoRussoUndead) comida;
			if(undead.npatas > 0)
				undead.npatas --;
		}
		comida.setNome("^" + comida.getNome());
	}
	public String toString() {
		return "Undead: " + super.toString() + " " 
				+ this.npatas + " patas ";
	}
}

public class Animais {
	public static void main(String[] args) {
		
		ArrayList<Cat> saco = new ArrayList<Cat>();
		
		saco.add(new Cat("Magrelo"));
		saco.add(new CatRusso("vitas", "azul"));
		saco.add(new CatRusso());
		GatoRussoUndead wlad = new GatoRussoUndead("Wladmir", 1);
		saco.add(new GatoRussoUndead());
		saco.add(wlad);
		
		GatoRussoUndead valdemir = new GatoRussoUndead("Valdemir", 8);
		
		for(Cat cat : saco)
			wlad.morderGato(cat);
		
		for(Cat cat : saco)
			if (cat instanceof GatoRussoUndead) {
				GatoRussoUndead undead = (GatoRussoUndead) cat;
				undead.morderGato(valdemir);
			}
		
		for(Cat cat : saco)
			System.out.println(cat.miar());
		
		System.out.println(valdemir);
		
		ArrayList<Rabifero> camburao = new ArrayList<Rabifero>(saco);
		camburao.add(new Dog());
		camburao.add(new Dog());
		
		for(Rabifero rab : camburao)
			System.out.println(rab.morderRabo());
	}
}











