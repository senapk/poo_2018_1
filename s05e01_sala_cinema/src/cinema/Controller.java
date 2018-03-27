package cinema;

import java.util.ArrayList;
import java.util.Scanner;

class Pessoa {
    public String id;
    public String fone;
    
    public Pessoa(String id, String fone) {
        this.id = id;
        this.fone = fone;
    }
    public String toString() {
        return "" + this.id + ":" + this.fone;
    }    
}

class Sala {
    ArrayList<Pessoa> cadeiras;
    
    public Sala(int capacidade) {
        cadeiras = new ArrayList<Pessoa>();
        for(int i = 0; i < capacidade; i++)
            cadeiras.add(null);
    }
    
    public void reservar(Pessoa pessoa, int indice) {
        if(cadeiras.get(indice) != null)
            throw new RuntimeException("ja esta reservado");
        cadeiras.set(indice, pessoa);
    }
    
    public void cancelar(String idPessoa) {
        for(int i = 0; i < cadeiras.size(); i++)
            if(cadeiras.get(i) != null && cadeiras.get(i).id.equals(idPessoa)) {
                cadeiras.set(i, null);
                return;
            }
        throw new RuntimeException("Pessoa nao encontrada");
    }

    public String toString() {
        String saida = "";
        for(Pessoa p : cadeiras) {
            if(p == null)
                saida += "- ";
            else
                saida += p + " ";
        }
        return saida;
    }
}

public class Controller{
    Sala sala;
    Scanner sca;
    static final int DEFAULT_CAPACITY = 5;
    
    Controller(){
        sala = new Sala(DEFAULT_CAPACITY);
        sca = new Scanner(System.in);
    }
    
    public String query(String line) {
        String[] ui = line.split(" ");
        if(ui[0].equals("show"))
            return "" + sala;
        else if(ui[0].equals("init"))
            sala = new Sala(Integer.parseInt(ui[1]));
        else if(ui[0].equals("reservar"))
            sala.reservar(new Pessoa(ui[1], ui[2]), 
                    Integer.parseInt(ui[3]));
        else if(ui[0].equals("cancelar"))
            sala.cancelar(ui[1]);
        return "done";
    }
    
    public void shell() {
        while(true) {
            String line = sca.nextLine();
            try {
                System.out.println(query(line));
            }catch(RuntimeException re){
                System.out.println(re.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        Controller c = new Controller();
        c.shell();
    }
}
