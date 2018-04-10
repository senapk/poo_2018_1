package trem;

public class Controller{
    Trem trem;
    public final static int DEFAULT_CAPACITY = 3; 
    public Controller() {
        trem = new Trem(DEFAULT_CAPACITY);
        trem.addVagao(new Vagao(1));
        trem.addVagao(new Vagao(3));
        trem.embarcar(new Pessoa("mary"));
        trem.embarcar(new Pessoa("jonh"));
        trem.embarcar(new Pessoa("paul"));
    }
    
    //nossa funcao oraculo que recebe uma pergunta e retorna uma resposta
    public String oracle(String line){
        String ui[] = line.split(" ");

        if(ui[0].equals("help"))
            return "help, init _capacidade, addVagao _capacidade, in _id, out _id, show";
        else if(ui[0].equals("init"))
            trem = new Trem(Integer.parseInt(ui[1]));
        else if(ui[0].equals("show"))
            return "" + trem;
        else if(ui[0].equals("addVagao"))
        	trem.addVagao(new Vagao(Integer.parseInt(ui[1])));
        else if(ui[0].equals("in")) {
        	trem.embarcar(new Pessoa(ui[1]));
        }else if(ui[0].equals("out"))
        	trem.desembarcar(ui[1]);
        else
            return "comando invalido";
        return "done";
    }
}
