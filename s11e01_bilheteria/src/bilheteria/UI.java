package bilheteria;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;



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

class Repositorio <T>{	
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
}

class Pessoa {
	private String nome;
	private int idade;
	private boolean estudante;
	
	public Pessoa(String nome, int idade, String estudante){
		this.nome = nome;
		this.idade = idade;
		this.estudante = estudante.equals("sim");
	}
	
	public String getNome() {
		return nome;
	}
	public int getIdade() {
		return idade;
	}
	public boolean isEstudante() {
		return estudante;
	}
	
	public String toString() {
		return  "<" + this.nome + ", " + this.idade + ", " + this.estudante + ">";
	}
}

class Setor {
	private String nome;
	private double preco;
	
	public Setor(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	public String toString() {
		return "<" + this.nome + " " + this.preco + ">";
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}
	
	public double getPreco() {
		return this.preco;
	}
}

class Evento extends Repositorio<Setor> {
	
	private String nome;
	public Evento(String nome) {
		super("setor");
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return "<" + this.nome + ">";
	}
}

class Venda {
	
	private Pessoa cliente;
	private Evento evento;
	private Setor setor;
	
	public double valor;
	
	public Venda(Pessoa cliente, Evento evento, Setor setor, double valor) {
		this.cliente = cliente;
		this.evento = evento;
		this.setor = setor;
		this.valor = valor;
	}
	
	public double getValor() {
		return this.valor;
	}
	
	public Pessoa getCliente() {
		return cliente;
	}
	public Evento getEvento() {
		return evento;
	}
	public Setor getSetor() {
		return setor;
	}
	
	public String toString() {
		return "<" + this.cliente.getNome() + ", " + this.evento.getNome() + ", " + 
				this.getSetor().getNome() + ", " + this.valor + ">";
	}
}

class Bilheteria{
	Repositorio<Venda> rvendas;
	double caixa = 0.0;
	public Bilheteria() {
		rvendas = new Repositorio<Venda>("venda");
	}
	
	public void vender(Pessoa cliente, Evento evento, Setor setor) {
		double valor = setor.getPreco();
		if(cliente.getIdade() < 2)
			valor = 0.0;
		else if(cliente.getIdade() <= 12 || cliente.isEstudante())
			valor = valor/2;
		rvendas.add(new Venda(cliente, evento, setor, valor));
		this.caixa += valor;
	}
	ArrayList<Venda> getVendas(){
		return rvendas.getAll();
	}
}


class Controller {
	
	Repositorio<Pessoa> rpessoas;
	Repositorio<Evento> reventos;
	Bilheteria bilheteria;
	
	public Controller() {
		rpessoas = new Repositorio<Pessoa>("pessoa");
		reventos = new Repositorio<Evento>("evento");
		bilheteria = new Bilheteria();
	}
		
	
	public String oracle(String line){
		
		String ui[] = line.split(" ");
		if(ui[0].equals("vender")) {
				Evento evento = reventos.get(ui[2]);
				bilheteria.vender(rpessoas.get(ui[1]), evento, evento.get(ui[3]));
		}
		else if(ui[0].equals("addPessoa"))
				rpessoas.add(ui[1], new Pessoa(ui[1], Integer.parseInt(ui[2]), ui[3]));	
		else if(ui[0].equals("addEvento"))
				reventos.add(ui[1], new Evento(ui[1]));
		else if(ui[0].equals("addSetor"))
		{
			Evento evento = reventos.get(ui[1]);
			evento.add(ui[2], new Setor(ui[2], Double.parseDouble(ui[3])));
		}
		else if(ui[0].equals("showP"))		
			return "" + rpessoas.getAll().stream().map(Object::toString).collect(Collectors.joining("\n"));
		else if(ui[0].equals("showE"))		
			return "" + reventos.getAll().stream().map(Object::toString).collect(Collectors.joining("\n"));
		else if(ui[0].equals("showV"))		
			return bilheteria.getVendas().stream().map(Object::toString).collect(Collectors.joining("\n"));
		else if(ui[0].equals("showS"))		
			return reventos.get(ui[1]).getAll().stream().map(Object::toString).collect(Collectors.joining("\n"));
		else
			return "comando invalido";
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