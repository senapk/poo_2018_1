package calculadora;

import java.util.ArrayList;

class Resposta{
	boolean deuCerto;
	float resultado;
	
}

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
	
	float soma(float a, float b) throws Exception{
		if(this.bateria == 0)
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
	
	public String toString(){
		return "bateria: " + this.bateria;
	}
}

class Controller{
	
	public static void main(String[] args) {
		Calculadora calc = new Calculadora();
		
		while(true){
			String line = Poo.input("Digite um comando:");
			System.out.println(line);
			ArrayList<String> ui = Poo.split(line, " ");

			try {
				if(ui.get(0).equals("show"))
					Poo.print("" + calc);
				else if(ui.get(0).equals("charge"))
					calc.charge(Integer.parseInt(ui.get(1)));
				else if(ui.get(0).equals("soma")) {
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







