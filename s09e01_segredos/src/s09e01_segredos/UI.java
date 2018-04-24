package s09e01_segredos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import conta.ContaBancaria;
import conta.Controller;

class Segredo{
	String texto;
	int nivel;
	public Segredo(String texto, int nivel) {
		super();
		this.texto = texto;
		this.nivel = nivel;
	}
}

class User implements Comparable<User>{
	private String password;
	private String username;
	Segredo segredo;
	
	public User(String username, String password) {
		this.password = password;
		this.username = username;
		this.segredo = null;
		
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
	public String getUsername() {
		return username;
	}
	public String toString() {
		return username;
	}
	public int compareTo(User o) {
		return this.username.compareTo(o.username);
	}
}

class GerenciadorDeLogin {
	private User current;
	Repositorio<User> usuarios;
	public GerenciadorDeLogin(Repositorio<User> usuarios){
		current = null;
	}
	public void login(String username, String password){
		if(current != null)
			throw new RuntimeException("fail: usuario ja esta logado");
		User user = usuarios.get(username);
		if(!user.matchPassword(password))
			throw new RuntimeException("fail: password invalido");
		this.current = user;
	}
	
	public void logout() {
		if(this.current == null)
			throw new RuntimeException("fail: nenhum usuario logado");
		this.current = null;
	}
	
	public User getUser() {
		if(this.current == null)
			throw new RuntimeException("fail: nenhum usuario logado");
		return this.current;
	}
}

class Controller{
	Repositorio<User> usuarios;
	
	public Controller() {
		usuarios = new Repositorio<User>("usuario");
	}

    //nossa funcao oraculo que recebe uma pergunta e retorna uma resposta
    public String oracle(String line){
        String ui[] = line.split(" ");

        if(ui[0].equals("help"))
            return "show, add _username _password, mostrar";
        else if(ui[0].equals("add"))
        	usuarios.add(ui[1], new User(ui[1], ui[2]));
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










