package s12e02_agenda;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
class TelefoneInvalidoException extends RuntimeException{
	public TelefoneInvalidoException(String number) {
		super("fail: numero " + number + " invalido");
	}
}

class Fone{
	private String id;
	private String number;
	public Fone(String id, String number) {
		this.id = id;
		this.number = number;
		validar();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
		validar();
	}
	private void validar() {
		String validos = "0123456789()-.";
		for(int i = 0; i < this.number.length(); i++) {
			boolean achei = false;
			for(int j = 0; j < validos.length(); j++) {
				if(this.number.charAt(i) == validos.charAt(j)) {
					achei = true;
					break;
				}
			}
			if(!achei)
				throw new TelefoneInvalidoException(this.number);
		}
	}
	public String toString() {
		return this.id + ":" + this.number;
	}
}

class Contato{
	RepContinuo<Fone> fones;
	String id;
	boolean favorited;
	public Contato(String id) {
		this.id = id;
		this.favorited = false;
		fones = new RepContinuo<Fone>("fone");
	}
	public RepContinuo<Fone> getFones() {
		return fones;
	}
	public void setFones(RepContinuo<Fone> fones) {
		this.fones = fones;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isFavorited() {
		return favorited;
	}
	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}
	public String toString() {
		String saida = this.favorited ? "@ " : "- ";
		saida += this.id + " ";
		ArrayList<Fone> afones = fones.getAll();
		ArrayList<String> aind = fones.getKeys();
		
		for(int i = 0; i < afones.size(); i++)
			saida += "[" + aind.get(i)+ ":" + afones.get(i) + "]";
		return saida;
	}
}

class Agenda {
	private RepIndexado<Contato> contatos;
	private RepIndexado<Contato> favoritos;
	
	public Agenda() {
		contatos = new RepIndexado<Contato>("contato");
		favoritos = new RepIndexado<Contato>("favorito");
	}
	
	public void addContato(Contato contato) {
		contatos.add(contato.getId(), contato);
	}
	
	public Contato getContato(String id) {
		return contatos.get(id);
	}
	
	public void rmContato(String id) {
		Contato contato = contatos.get(id);
		if(contato.isFavorited()) {
			favoritos.remove(id);
			contato.setFavorited(false);
		}
		contatos.remove(id);
	}
	
	public ArrayList<Contato> getContatos(){
		return contatos.getAll();
	}
	
	public void favoritar(String id) {
		Contato contato = contatos.get(id);
		if(contato.isFavorited())
			return;
		contato.setFavorited(true);
		favoritos.add(id, contato);
	}
	
	public void desfavoritar(String id) {
		Contato contato = contatos.get(id);
		if(!contato.isFavorited())
			return;
		contato.setFavorited(false);
		favoritos.remove(id);
	}
}

class Controller {
	Agenda agenda;
	public Controller() {
		agenda = new Agenda();
	}
			
	public String oracle(String line){
		String ui[] = line.split(" ");
		if(ui[0].equals("help"))
			return "addC _nome _id:_fone _id:_fone ...\nshow";
		else if(ui[0].equals("addC")) {
			String saida = "";
			
			Contato contato;
			try {
				contato = agenda.getContato(ui[1]);
			}catch(Exception e) {
				contato = new Contato(ui[1]);
				agenda.addContato(contato);
			}
			
			for(int i = 2; i < ui.length; i++) {
				String dados[] = ui[i].split(":");
				try {
					Fone fone = new Fone(dados[0], dados[1]);
					contato.getFones().add(fone);
				}catch(TelefoneInvalidoException e) {
					saida += e.getMessage() + "\n";
				}
			}
			if(saida == "")
				return "done";
			return saida;
		}
		else if(ui[0].equals("rmC"))
			agenda.rmContato(ui[1]);
		else if(ui[0].equals("fav"))// _fav _id
			agenda.favoritar(ui[1]);
		else if(ui[0].equals("desfav"))
			agenda.desfavoritar(ui[1]);
		else if(ui[0].equals("show")) {
			String saida = "";
			for(Contato contato : agenda.getContatos())
				saida += contato + "\n";
			return saida;
		}
		else if(ui[0].equals("ninja"))
			return agenda.getContatos().stream().map(Object::toString).collect(Collectors.joining("\n"));
		else if(ui[0].equals("rmFone"))//rmF _id _ind
			agenda.getContato(ui[1]).getFones().remove(ui[2]);
		else
			return "comando nao encontrado";
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
















