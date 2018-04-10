package trem;

public class Pessoa{
	private String id;
	
	public Pessoa(String id){
		this.id = id;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String toString(){
		return id;
	}
}
