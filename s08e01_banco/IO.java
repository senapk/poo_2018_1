import java.util.ArrayList;
import java.util.Scanner;

class Operacao{
	String descricao;
	float value;
	float saldo;
	public Operacao(String d, float v, float s) {
		descricao = d;
		value = v;
		saldo = s;
	}
	public String toString() {
		return "" + this.descricao + " " + this.value + " " + this.saldo;
	}
}

class ContaBancaria{
	float saldo;
	String id;
	ArrayList<Operacao> extrato;
    boolean ativa;
	
	public ContaBancaria(String id){
		this.id = id;
		this.saldo = 0;
		this.extrato = new ArrayList<Operacao>();
        this.ativa = true;
	}
    
    public void encerrar(){
        if(!this.ativa)
            throw new RuntimeException("Conta ja esta fechada");
        this.ativa = false;
    }
	
	public String toString(){
		return " " + this.numero + " " + this.saldo;
	}
	
	public boolean saque(float value, String label) {
		if(value > this.saldo) 
            throw new RuntimeException("saldo insuficiente");
		if(value <= 0)
			throw new RuntimeException("valor invalido");
		this.saldo -= value;
		this.extrato.add(new Operacao(label, -value, this.saldo));
		return true;
	}
	
	public void deposito(float value, String label) {
        if(value <= 0)
			throw new RuntimeException("valor invalido");
		this.saldo += value;
		this.extrato.add(new Operacao(label, value, this.saldo));
	}
    
    public void transferencia(Conta other, float value) {
        if(conta == null)
            throw new RuntimeException("conta nula");
        if(value <= 0)
            throw new RuntimeException("valor invalido");
        this.saque(value, "transferencia para " + other.numero);
        this.other.deposito(value, "transferencia de " + this.numero);
    }
	
	public String extrato() {
		String saida = "";
		for(Operacao op : this.extrato)
			saida += op + "\n";
		return saida;
	}
}

class Cliente {
    int id;
    ArrayList<ContaBancaria> contas;
    public Cliente(int id){
        this.id = id;
        this.contas = new ArrayList<ContaBancaria>();
    }
    
    public addConta(conta: Conta) {
        contas.add(conta);
    }
    
}

class Controller{

    ContaBancaria conta;
    
    public Controller() {
        conta = new ContaBancaria(0);
    }
    
    //nossa funcao oraculo que recebe uma pergunta e retorna uma resposta
    public String oracle(String line){
        String ui[] = line.split(" ");

        if(ui[0].equals("help"))
            return "show, init _numero, saque _value, deposito _value, extrato, ";
        else if(ui[0].equals("init"))
            conta = new ContaBancaria(Integer.parseInt(ui[1]));
        else if(ui[0].equals("show"))
            return "" + conta;
        else if(ui[0].equals("saque"))
        	conta.saque(Float.parseFloat(ui[1])); 
        else if(ui[0].equals("deposito"))
        	conta.deposito(Float.parseFloat(ui[1]));
        else if(ui[0].equals("extrato"))
        	return conta.extrato();
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




