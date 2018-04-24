package s09e01_ordenacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

interface IGato {
	public String mia();
}

public class Pessoa implements Comparable<Pessoa>{
	String nome;
	int idade;
	public Pessoa(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}
	
	public String toString() {
		return nome + ":" + idade;
	}
	
	public static void main(String[] args) {
		
		System.out.println(new IGato() {
			public String mia() {
				return "miuuuuuuuuuuuuu";
			}
		}.mia());
		
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas.add(new Pessoa("Bob", 10));
		pessoas.add(new Pessoa("Ana", 10));
		pessoas.add(new Pessoa("Ana", 8));
		pessoas.add(new Pessoa("Cid", 18));
		
		Collections.sort(pessoas, new Comparator<Pessoa>(){
			public int compare(Pessoa o1, Pessoa o2) {
				return o1.idade - o2.idade;
			}
		});
		
		for(Pessoa p : pessoas)
			System.out.println(p);
		
	}

	public int compareTo(Pessoa other) {
		if(this.nome.compareTo(other.nome) == 0)
			return this.idade - other.idade;
		return this.nome.compareTo(other.nome);
	}
}













