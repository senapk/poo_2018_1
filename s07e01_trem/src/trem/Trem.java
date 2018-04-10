package trem;

import java.util.ArrayList;

public class Trem {
	private ArrayList<Vagao> vagoes;
	private int capacidade;
	
	public Trem(int capacidade){
		this.capacidade = capacidade;
		this.vagoes = new ArrayList<Vagao>();
	}
	
	void addVagao(Vagao vagao){
		if(vagoes.size() < this.capacidade)
			vagoes.add(vagao);
		else
			throw new RuntimeException("Limite de vagoes atingido");
	}
	
	boolean find(String id){
		for(Vagao vagao : vagoes){
			if(vagao.find(id) != null){
				return true;
			}
		}
		return false;
	}
	
	void embarcar(Pessoa pessoa){
		if(this.find(pessoa.getId()))
			throw new RuntimeException("Pessoa ja esta no trem");
		for(Vagao vagao : vagoes){
			if(vagao.embarcar(pessoa)){
				return;
			}
		}
		throw new RuntimeException("Trem lotado");
	}
	void desembarcar(String id) {
		for(Vagao vagao : vagoes)
			if(vagao.desembarcar(id))
				return;
		throw new RuntimeException("Passageiro nao encontrado");
	}
	
	public String toString() {
		String saida = "";
		for(Vagao vagao : vagoes)
			saida +=  vagao + " ";
		return saida;
	}
}
