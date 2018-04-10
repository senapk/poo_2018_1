package trem;

import java.util.ArrayList;

public class Vagao{
	private ArrayList<Pessoa> cadeiras;
	
	public Vagao(int capacidade){
		cadeiras = new ArrayList<Pessoa>();
		for(int i = 0 ; i < capacidade ; i++){
			cadeiras.add(null);
		}
	}
	
	public Pessoa find(String id){
		for(Pessoa pessoa : cadeiras){
			if(pessoa != null && pessoa.getId().equals(id)){
				return pessoa;
			}
		}
		return null;
	}
	
	public boolean embarcar(Pessoa pessoa){
		if(this.find(pessoa.getId()) != null){
			return false;
		}
		for(int i = 0 ; i < cadeiras.size();i++){
			if(cadeiras.get(i) == null){
				cadeiras.set(i, pessoa);
				return true;
			}
		}
		return false;
	}
	
	public boolean desembarcar(String id){
		for(int i = 0 ; i < cadeiras.size();i++){
			if(cadeiras.get(i) != null && cadeiras.get(i).getId().equals(id)){
				cadeiras.set(i, null);
				return true;
			}
		}
		return false;
	}
	
	public String toString(){
		String saida = "[ ";
		for(Pessoa pessoa : cadeiras){
			if(pessoa == null){
				saida += "- ";
			}else{
				saida += pessoa + " ";
			} 
		}
		return saida + "]";
	}
}
