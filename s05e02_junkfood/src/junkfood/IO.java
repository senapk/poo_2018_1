package junkfood;

import java.util.ArrayList;
import java.util.Scanner;

class Espiral{
	public String toString() {
		return "[- : 0 U : 0 RS]";
	}
}

class Maquina{
	ArrayList<Espiral> espirais;
	public Maquina(int qtdEspirais, int maxProdutos){
		this.espirais = new ArrayList<Espiral>();
		for(int i = 0; i < qtdEspirais; i++)
			this.espirais.add(new Espiral());
	}

	public String toString() {
		String saida = "";
		for(int i = 0; i < espirais.size(); i++)
			saida += i + " " + espirais.get(i) + "\n";
		return saida;
	}
}

class Controller{
    Maquina maq;
    static final int DEFAULT_ESPIRAIS = 3;
    static final int DEFAULT_MAX = 5;
    public Controller() {
        maq = new Maquina(DEFAULT_ESPIRAIS, DEFAULT_MAX);
    }
    
    //recebe uma string e tenta converter em float
    private float toFloat(String s) {
        return Float.parseFloat(s);
    }
    
    //nossa funcao oraculo que recebe uma pergunta e retorna uma resposta
    public String oracle(String line){
        String ui[] = line.split(" ");

        if(ui[0].equals("help"))
            return "show, init _espirais _maximo";
        else if(ui[0].equals("init"))
            maq = new Maquina(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
        else if(ui[0].equals("show"))
            return "" + maq;
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





