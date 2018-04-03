package cinema;

import java.util.ArrayList;
import java.util.Scanner;

class Pessoa{
	public String nome;
	public String fone;
	
	public Pessoa(String nome, String fone) {
		this.nome = nome;
		this.fone = fone;
	}
	
	public Pessoa() {
		this.nome = "";
		this.fone = "";
	}
	public String toString() {
		return "" + this.nome + ":" + this.fone;
	}
	
}

class Sala{
	ArrayList<Pessoa> cadeiras;
	float lucro = 0.f;
	float valorIngresso = 0.f;
	
	public Sala(float ingresso, int capacidade) {
		
		this.valorIngresso = ingresso;
		cadeiras = new ArrayList<Pessoa>();
		for(int i=0; i< capacidade; i++) 
			cadeiras.add(null);				
	}
	public void reservar(Pessoa pessoa, int indice) {
		for(Pessoa p : cadeiras) 
			if((p != null) && p.nome.equals(pessoa.nome)) 
				throw new RuntimeException("Pessoa ja esta no cinema");
		if((indice < 0) || (indice >= cadeiras.size()))
			throw new RuntimeException("Numero de poltrona não existe");
		if(cadeiras.get(indice) != null)
			throw new RuntimeException("Cadeira ja esta reservada");
		cadeiras.set(indice, pessoa);
		lucro += valorIngresso;
	}
	public void cancelar(String nome) {
		for(int i = 0; i < cadeiras.size(); i++) { 
			Pessoa p = cadeiras.get(i);
			if((p != null) && p.nome.equals(nome)) {
				cadeiras.set(i, null);
				lucro -= valorIngresso / 2;
				return;
			}
		}
		throw new RuntimeException("Pessoa nao encontrada");
	}
	public String toString() {
		String saida = "";
		for(Pessoa p : cadeiras) {
			if(p != null)
			    saida += p + " ";
			else
				saida += "- ";
		}
		return saida;
	}
	 
 }

class Controller{
    Sala sala;
    static final int DEFAULT_CADEIRAS = 4;
    static final float DEFAULT_PRECO = 8;
    public Controller() {
        sala = new Sala(DEFAULT_PRECO, DEFAULT_CADEIRAS);
    }
    
    //nossa funcao oraculo que recebe uma pergunta e retorna uma resposta
    public String oracle(String line){
        String ui[] = line.split(" ");

        if(ui[0].equals("help"))
            return "show, init _preco _cadeiras, reservar _nome _tel _ind, cancelar _nome";
        else if(ui[0].equals("init"))
            sala = new Sala(Float.parseFloat(ui[1]), Integer.parseInt(ui[2]));
        else if(ui[0].equals("show"))
            return "" + sala;
        else if(ui[0].equals("reservar"))
            sala.reservar(new Pessoa(ui[1], ui[2]), Integer.parseInt(ui[3]));
        else if(ui[0].equals("cancelar"))
            sala.cancelar(ui[1]);
        else
            return "comando invalido";
        return "done";
    }
}

public class IO {
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


	

