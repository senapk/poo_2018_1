package calculadora;

import java.util.Scanner;

public class Calculadora {
	static final int bateriaInicial = 2;
	static final int bateriaMaxima = 5;
	
	int bateria;
	
	public Calculadora() {
		this.bateria = Calculadora.bateriaInicial;
	}
	
	void charge(int value){
		if(value < 0)
			return;
		this.bateria += value;
		if(this.bateria > Calculadora.bateriaMaxima)
			this.bateria = Calculadora.bateriaMaxima;
	}
	void gastarBateria() {
		if(this.bateria == 0)
			throw new RuntimeException("fail: bateria insuficiente");
		this.bateria -= 1;
	}
	
	float soma(float a, float b){
		this.gastarBateria();
		this.bateria -= 1;
		return a + b;
	}
	
	float divisao(float num, float den){
		this.gastarBateria();
		if(den == 0f)
			throw new RuntimeException("fail: divisao por 0");
		return num/den;
	}
	
	public String toString(){
		return "bateria: " + this.bateria;
	}
}


class Controller{
	Calculadora calc;
	
	public Controller() {
		calc = new Calculadora();
	}
	
	//recebe uma string e tenta converter em float
	private float toFloat(String s) {
		return Float.parseFloat(s);
	}
	
	//nossa funcao oraculo que recebe uma pergunta e retorna uma resposta
	public String oracle(String line){
		String ui[] = line.split(" ");

		if(ui[0].equals("help"))
			return "sum _a _b\nshow\ndiv _a _b\ncharge _value";
		else if(ui[0].equals("show"))
			return "" + calc;
		else if(ui[0].equals("charge"))
			calc.charge(Integer.parseInt(ui[1]));
		else if(ui[0].equals("soma"))
			return "= " + calc.soma(toFloat(ui[1]), toFloat(ui[2]));
		else if(ui[0].equals("div"))
			return "= " + calc.divisao(toFloat(ui[1]), toFloat(ui[2]));
		else
			return "comando invalido";
		return "done";
	}
}

class IO {
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





