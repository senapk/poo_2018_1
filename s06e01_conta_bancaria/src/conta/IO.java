package conta;

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
	int numero;
	ArrayList<Operacao> extrato;
	
	public ContaBancaria(int numero){
		this.numero = numero;
		this.saldo = 0;
		this.extrato = new ArrayList<Operacao>();
	}
	
	public String toString(){
		return " " + this.numero + " " + this.saldo;
	}
	
	public boolean saque(float value) {
		if(value > this.saldo) 
			return false;
		if(value <= 0)
			throw new RuntimeException("deixe de ser gaiato");
		this.saldo -= value;
		this.extrato.add(new Operacao("saque", -value, this.saldo));
		return true;
	}
	
	public void deposito(float value) {
		this.saldo += value;
		this.extrato.add(new Operacao("deposito", value, this.saldo));
	}
	
	public String extrato() {
		String saida = "";
		for(Operacao op : this.extrato)
			saida += op + "\n";
		return saida;
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
            return "show, init _numero";
        else if(ui[0].equals("init"))
            conta = new ContaBancaria(Integer.parseInt(ui[1]));
        else if(ui[0].equals("show"))
            return "" + conta;
        else if(ui[0].equals("saque")) {
        	if(!conta.saque(Float.parseFloat(ui[1]))) 
        		return "saldo insuficiente";
        }
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




