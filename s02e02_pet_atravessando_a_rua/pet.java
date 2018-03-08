import java.util.ArrayList;

public class Pet {
    
    static int qtd = 0;
	String nome;
	String raca;
	int idade;
	boolean isAlive;
	int vidas;
	
	
	public Pet(String nome, String raca, int idade){
		this.nome = nome;
		this.raca = raca;
		this.idade = idade;
		this.isAlive = true;
		this.vidas = 1;
		if (this.raca.equals("Cat"))
			this.vidas = 7;
		Pet.qtd += 1;
	}
	
	public void fazer_barulho(){
		if (this.isAlive){
			if (this.raca.equals("Dog"))
				System.out.println ("au au");
			else if (this.raca.equals("Cat"))
				System.out.println ("miau");
			else
				System.out.println ("iiiiiiiiii");
		} else
			System.out.println("RIP");
	}
			
	public String toString(){
		String result = "Animal " + this.nome + " " + this.raca;
		result += " " + this.idade + " Vivo: " + this.isAlive;
		return result;
	}

	public void atravessar_a_rua(){
		if (this.isAlive){
			this.vidas -= 1;
			if (this.vidas == 0)
				this.isAlive = false;
		}
	}
	
    public static void main(String args[]) {
        
        ArrayList<Pet> zoo = new ArrayList<Pet>();
        zoo.add(new Pet("Dog", "Dog", 5));
        zoo.add(new Pet("Xaninha", "Cat", 7));
        zoo.add(new Pet("Giba", "Soin", 2));
	
        boolean tem_vivo = true;	
        while(tem_vivo){
        	tem_vivo = false;
        	for (Pet animal : zoo){
        		animal.fazer_barulho();
        		animal.atravessar_a_rua();
        		if(animal.isAlive)
        			tem_vivo = true;
        		System.out.println(animal);
        	}
        }
    }
}
