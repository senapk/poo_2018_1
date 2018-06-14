package mercantil;

import java.util.List;

interface Item {
	String getId();
	String getDescricao();
	float getPreco();
}

class Produto implements Item {
	private String id;
	private float preco;
	public String getId(){
		return id;
	}
	public String getDescricao(){
		return id;
	}
	public float getPreco(){
		return preco;
	}
}

class Pacote implements Item {
	List<Item> itens;
	String id;
	public Pacote(String id) {
		this.id = id;
	}
	public void addItem(Item item) {
		this.itens.add(item);
	}
	public String getDescricao(){
		String descricao = "id:" + id + "Pacote:";
		for(Item item : itens)
			descricao += item.getDescricao() + "\n";
		return descricao;
	}
	public float getPreco() {
		float total = 0;
		for(Item item : itens)
			total += item.getPreco();
		return total;
	}
	@Override
	public String getId() {
		return id;
	}
}

class ItemDesconto implements Item{
	Item item;
	String id;
	float desconto;
	public ItemDesconto(String id, Item item, float desconto) {
		this.item = item;
		this.id = id;
		this.desconto = desconto;
	}
	
	public float getPreco() {
		return item.getPreco() * (1 - desconto/100.0);
	}
	public String getDescricao() {
		return "id:" + id + "ItemDesc: " + item.getDescricao() + 
				" desc:" + this.desconto; 
	}
	
}



