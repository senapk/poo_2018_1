package main;

import java.util.ArrayList;
import java.util.Scanner;

class EmbJaExisteException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public EmbJaExisteException(String msg) {
		super(msg);
	}
}

interface Embarcavel {
	public String getId();
}

class Pessoa implements Embarcavel {
	private String cpf;
	public Pessoa(String cpf) {
		this.cpf = cpf;
	}
	public String toString() {
		return "P:" + this.getId();
	}
	public String getId() {
		return cpf;
	}
}

class Carga implements Embarcavel {
	private String id;
	private int peso;
	public Carga(String id, int peso) {
		this.id = id;
		this.peso = peso;
	}
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String toString() {
		return "P:" + this.getId();
	}

	@Override
	public String getId() {
		return id;
	}
}

interface Vagao {
	void embarcar(Embarcavel carga);
	void desembarcar(String id);
	boolean exists(String id);
}

class VagaoPessoas implements Vagao {
	ArrayList<Pessoa> cadeiras;
	
	public VagaoPessoas(int capacidade) {
		cadeiras = new ArrayList<Pessoa>();
		for(int i = 0; i < capacidade; i++)
			cadeiras.add(null);
	}

	public void embarcar(Embarcavel carga) {
		if(this.exists(carga.getId()))
			throw new EmbJaExisteException("Passageiro ja existe");
		if(carga instanceof Pessoa == false)
			throw new RuntimeException("nao eh passageiro");
		Pessoa pass = (Pessoa) carga;
		for(int i = 0; i < cadeiras.size(); i++) {
			if(cadeiras.get(i) == null) {
				cadeiras.set(i, pass);
				return;
			}
		}
		throw new RuntimeException("vagao cheio");
	}

	@Override
	public void desembarcar(String id) {
		for(int i = 0; i < cadeiras.size(); i++) {
			if(cadeiras.get(i) != null && cadeiras.get(i).getId().equals(id)) {
				cadeiras.set(i, null);
				return;
			}
		}
		throw new RuntimeException("vagao cheio");
	}

	public boolean exists(String id) {
		for(int i = 0; i < cadeiras.size(); i++) {
			if(cadeiras.get(i) != null && cadeiras.get(i).getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String saida = "[ ";
		for(int i = 0; i < cadeiras.size(); i++) {
			if(cadeiras.get(i) == null) {
				saida += "- ";
			}else {
				saida +=  cadeiras.get(i);
			}
		}
		return saida + "]";
	}
}

class VagaoCarga implements Vagao {
	RepIndexado<Carga> repo;
	int maxPeso;
	int pesoAtual;
	
	public VagaoCarga(int maxPeso) {
		repo = new RepIndexado<Carga>("bagagem");
		this.maxPeso = maxPeso;
		this.pesoAtual = 0;
	}
	@Override
	public void embarcar(Embarcavel carga) {
		if(carga instanceof Carga == false) 
			throw new RuntimeException("nao eh bagagem");
		Carga bagagem = (Carga) carga;
		if(this.pesoAtual + bagagem.getPeso() > this.maxPeso)
			throw new RuntimeException("ta lotado");
		this.repo.add(bagagem.getId(), bagagem);
		this.pesoAtual += bagagem.getPeso();
	}

	@Override
	public void desembarcar(String id) {
		this.pesoAtual -= this.repo.get(id).getPeso();
		this.repo.remove(id);
	}

	@Override
	public boolean exists(String id) {
		return this.repo.exists(id);
	}

	public String toString() {
		String saida = "( ";
		for(Embarcavel emb : repo.getAll())
			saida += emb.getId() + " ";
		return saida + "_" + (this.maxPeso - this.pesoAtual) + ")";
	}
}

class Trem {
	ArrayList<Vagao> vagoes;
	RepIndexado<Embarcavel> embarcados;
	public Trem() {
		vagoes = new ArrayList<Vagao>();
		embarcados = new RepIndexado<Embarcavel>("emb");
	}
	void add(Vagao cont) {
		vagoes.add(cont);
	}
	void desembarcar(String id) {
		for(Vagao vagao : vagoes)
			try {
				vagao.desembarcar(id);
				return;
			}catch(RuntimeException e) {
				
			}
		throw new RuntimeException("passageiro nao encontrado");
	}
	void embarcar(Embarcavel emb) {
		
		for(Vagao vagao : vagoes) {
			try {
				System.out.println("tentei um vagao");
				vagao.embarcar(emb);
				return;
			}catch(EmbJaExisteException e) {
				throw e;
			}catch(RuntimeException e) {
				
			}
		}
		throw new RuntimeException("trem lotado");
	}
	public String toString() {
		String saida = "";
		for(Vagao cont : vagoes)
			saida += cont + " ";
		return saida;
	}
}


class Controller {
	Trem trem;
	public Controller() {
		trem = new Trem();
	}
			
	public String oracle(String line){
		String ui[] = line.split(" ");
		if(ui[0].equals("help"))
			return "addvp _cap; addvc _pesoMax; embp _nome; embB _id _peso; show";
		else if(ui[0].equals("addvp"))
			trem.add(new VagaoPessoas(Integer.parseInt(ui[1])));
		else if(ui[0].equals("addvc"))
			trem.add(new VagaoCarga(Integer.parseInt(ui[1])));
		else if(ui[0].equals("embp"))
			trem.embarcar(new Pessoa(ui[1]));
		else if(ui[0].equals("embc"))
			trem.embarcar(new Carga(ui[1], Integer.parseInt(ui[2])));
		else if(ui[0].equals("des"))
			trem.desembarcar(ui[1]);
		else if(ui[0].equals("show"))
			System.out.println(trem);
		else
			return "comando nao encontrado";
		return "done";
	}
}

public class UI {
    //cria um objeto scan para ler strings do teclado
    static Scanner scan = new Scanner(System.in);
    
    //aplica um tab e retorna o texto tabulado com dois espaços
    static private String tab(String text){
        return "  " + String.join("\n  ", text.split("\n"));
    }
    
    public static void main(String[] args) {
        Controller cont = new Controller();
        System.out.println("Digite um comando ou help:");
        while(true){
            String line = scan.nextLine();
            if(line == "" || line.indexOf(0) == ' ')
            	continue;
            //System.out.println(line);
            try {
                //se não der problema, faz a pergunta e mostra a resposta
                System.out.println(tab(cont.oracle(line)));
            }catch(Exception e) {
                //se der problema, mostre o erro que deu
                System.out.println(tab(e.getMessage()));
            }
        }
    }

}
















