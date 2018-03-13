package calculadora;

import java.util.ArrayList;


//Ponto ninja: O que é OO? O que é a vida? Onde o encontro o amor?
//quantas classes vou criar?

public class Calculadora {
	static final int bateriaInicial = 2;
	static final int bateriaMaxima = 5;
	
	int bateria;

	//Ponto 1 - Construtor
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
	//sobrecarga de métodos
	//polimorfismo de métodos
	void charge() {
		this.charge(1);
	}
	
	float soma(float a, float b) throws Exception{
		if(this.bateria == 0)//Ponto 4 - o que é um throw
			throw new Exception("bateria insuficiente");
		this.bateria -= 1;
		return a + b;
	}
	
	float divisao(float num, float den) throws Exception{
		if(this.bateria == 0)
			throw new Exception("bateria insuficiente");
		if(den == 0f)
			throw new Exception("divisao por 0");
		this.bateria -= 1;
		return num/den;
	}
	
	//Ponto 3: como funciona toString
	public String toString(){
		return "bateria: " + this.bateria;
	}
}

class Controller{
	public static void main(String[] args) {
		Calculadora calc = new Calculadora();
		
		while(true){
			//Ponto n: revisar o fluxo de console
			String line = Poo.input("Digite um comando:");
			System.out.println(line);
			ArrayList<String> ui = Poo.split(line, " ");

			//Ponto 2: try catch excessoes
			try {
				//Ponto n+2: equals vem de onde?
				if(ui.get(0).equals("show"))
					Poo.print("" + calc);
				else if(ui.get(0).equals("charge"))
					calc.charge(Integer.parseInt(ui.get(1)));
				else if(ui.get(0).equals("soma")) {
												//Ponto n+1: toFloat???
					Poo.print( "= " + calc.soma(Poo.toFloat(ui.get(1)),
												Poo.toFloat(ui.get(2))));
				}
				else if(ui.get(0).equals("div")) {
					Poo.print( "= " + calc.divisao(Poo.toFloat(ui.get(1)),
												   Poo.toFloat(ui.get(2))));
				}else if(ui.get(0).equals("fim"))
					break;
			}catch(Exception e) {
				Poo.print(e.getMessage());
			}
		}
	}
}







