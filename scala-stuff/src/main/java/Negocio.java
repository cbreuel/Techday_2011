
public class Negocio {
	private String acao;
	private double preco;
	private int quantidade;
	
	public Negocio(String acao, double preco, int quantidade){
		if(acao == null) throw new IllegalArgumentException("acao nao pode ser nulo");
		if(preco < 0.0) throw new IllegalArgumentException("preco deve ser positivo");
		if(quantidade < 0) throw new IllegalArgumentException("quantidade deve ser positiva");
		
		this.acao = acao;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	public double getVolume(){
		return preco * quantidade;
	}
	
	@Override
	public String toString(){
		return String.format("acao: %s, preco: %.2f, quantidade: %d", acao, preco, quantidade);
	}
	
	public String getAcao() {
		return acao;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public static Negocio apply(String acao, Double preco, Integer quantidade){
		if(acao == null) acao = "";
		if(preco == null) preco = new Double(0.0);
		if(quantidade == null) quantidade = new Integer(0);		
		return new Negocio(acao, preco, quantidade);
	}
}