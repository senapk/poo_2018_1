package s09e01_segredos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Segredo{
	String texto;
	int nivel;
	public Segredo(String texto, int nivel) {
		super();
		this.texto = texto;
		this.nivel = nivel;
	}
	public String toString() {
		return texto + " " + nivel;
	}
}

class User implements Comparable<User>{
	private String password;
	private String username;
	private Segredo segredo;
	
	public Segredo getSegredo() {
		return segredo;
	}
	public void setSegredo(String texto, int nivel) {
		this.segredo.texto = texto;
		this.segredo.nivel = nivel;
	}
	public User(String username, String password) {
		this.password = password;
		this.username = username;
		this.segredo = new Segredo("", 0);
		
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
		return username + " " + this.segredo.texto + " " + this.segredo.nivel;
	}
	public int compareTo(User o) {
		return this.username.compareTo(o.username);
	}
}

class GerenciadorDeLogin{
	private Repositorio<User> usuarios;
	private User user;
	
	public GerenciadorDeLogin(Repositorio<User> usuarios) {
		this.usuarios = usuarios;
		user = null;
	}
	
	void login(String username, String senha){
		if(user != null)
			throw new RuntimeException("fail: ja existe alguem logado");
		if(!usuarios.get(username).matchPassword(senha))
			throw new RuntimeException("fail: password invalido");
		this.user = usuarios.get(username);
	}
	
	void logout(){
		if(user == null)
			throw new RuntimeException("fail: ninguem logado");
		user = null;
	}
	
	public User getUser(){
		if(user == null)
			throw new RuntimeException("fail: ninguem logado");
		return user;
	}
}

class Controller{
	Repositorio<User> usuarios;
	GerenciadorDeLogin gerLogin;
	
	public Controller() {
		usuarios = new Repositorio<User>("usuario");
		usuarios.add("admin", new User("admin", "admin"));
		usuarios.get("admin").setSegredo("i see dead people", 10);
		gerLogin = new GerenciadorDeLogin(usuarios);
	}

    //nossa funcao oraculo que recebe uma pergunta e retorna uma resposta
    public String oracle(String line){
        String ui[] = line.split(" ");

        if(ui[0].equals("help"))
            return "showUsernames, addUser _username _password, login _username _password\n" + 
                   "logout, rmAccount, changePass _old _new, showUser\n" + 
                   "setSegredo _nivel _segredo, showSecrets";
        else if(ui[0].equals("addUser"))
        	usuarios.add(ui[1], new User(ui[1], ui[2]));
        else if(ui[0].equals("login"))
        	gerLogin.login(ui[1], ui[2]);
        else if(ui[0].equals("logout"))
        	gerLogin.logout();
        else if(ui[0].equals("showUser"))
        	return "" + gerLogin.getUser();
        else if(ui[0].equals("changePass")) {
        	User user = gerLogin.getUser();
        	if(user.matchPassword(ui[1]))
        		user.setPassword(ui[2]);
        }
        else if(ui[0].equals("setSegredo")) {
        	User user = gerLogin.getUser();
        	String texto = "";
        	for(int i = 2; i < ui.length; i++)
        		texto += ui[i] + " ";
        	user.setSegredo(texto, Integer.parseInt(ui[1]));
        }
        else if(ui[0].equals("rmAccount")) {
        	String nome = gerLogin.getUser().getUsername();
        	usuarios.remove(nome);
        	gerLogin.logout();
        }
        
        else if(ui[0].equals("showUsernames")) {
        	String saida = "";
        	for(User user : usuarios.getAll())
        		saida += user.getUsername() + "\n";
        	return saida;
        }
//
        else if(ui[0].equals("showSecrets")) {
        	if(!gerLogin.getUser().getUsername().equals("admin"))
        		throw new RuntimeException("usuario nao eh admin");
        	ArrayList<User> users = usuarios.getAll();
        	Collections.sort(users, new Comparator<User>() {
				public int compare(User arg0, User arg1) {
					return - arg0.getSegredo().nivel + arg1.getSegredo().nivel;
				}
        	});
        	String saida = "";
        	for(User user : users)
        		saida += user.getUsername() + " " + user.getSegredo() + "\n";
        	return saida;
        }

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

/*
login admin admin
setSegredo 6 gosto de assitir filme de terror
logout
addUser david sena
login david sena
setSegredo 8 gosto de comer hot dog com carne de cachorro
logout
addUser alberto pessoa
login alberto pessoa
setSegredo 7 fumo escondido do papai
logout
login admin admin
showSecrets
 */








