import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Candlestick {
	private String acao;
	private double abertura;
	private double fechamento;
	private double minimo;
	private double maximo;
	private double volume;
	
	public Candlestick(String acao, double abertura, double fechamento, double minimo, double maximo, double volume){
		if(acao == null) throw new IllegalArgumentException("acao nao pode ser nulo");
		if(abertura < 0.0) throw new IllegalArgumentException("abertura deve ser positivo");
		if(fechamento < 0.0) throw new IllegalArgumentException("fechamento deve ser positivo");
		if(minimo < 0.0) throw new IllegalArgumentException("minimo deve ser positivo");
		if(maximo < 0.0) throw new IllegalArgumentException("maximo deve ser positivo");
		if(volume < 0.0) throw new IllegalArgumentException("volume deve ser positivo");
		this.acao = acao;
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
	}
	
	public boolean isAlta(){
		return abertura < fechamento;
	}
	
	public boolean isBaixa(){
		return !isAlta();
	}
	
	public String toString(){
		return String.format("acao: %s, abertura: %.2f, fechamento: %.2f, minimo: %.2f, maximo: %.2f, volume: %.2f", acao, abertura, fechamento, minimo, maximo, volume);
	}
	
	public String getAcao() {
		return acao;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public static Candlestick candlestick(List<Negocio> negocios){
		if(!mesmaAcao(negocios)) throw new IllegalArgumentException("negocios devem ser da mesma acao");
		if(negocios == null || negocios.isEmpty()){
			return new Candlestick("", 0.0, 0.0, 0.0, 0.0, 0.0);
		}
		else{
			double maior = Double.MIN_VALUE;
			double menor = Double.MAX_VALUE;
			double volume = 0.0;
			for(Negocio n: negocios){				
				if(n.getPreco() > maior){
					maior = n.getPreco();
				}
				if(n.getPreco() < menor){
					menor = n.getPreco();
				}
				volume += n.getVolume();
			}
			return new Candlestick(negocios.get(0).getAcao(), negocios.get(0).getPreco(), negocios.get(negocios.size() - 1).getPreco(), menor, maior, volume);
		}
	}
	
	public static List<Candlestick> candlesticks(List<Negocio> negocios){
		Map<String, List<Negocio>> mapa = new HashMap<String, List<Negocio>>();
		for(Negocio n: negocios){
			List<Negocio> lista = mapa.get(n.getAcao());
			if(lista == null){
				lista = new ArrayList<Negocio>();
				mapa.put(n.getAcao(), lista);
			}
			lista.add(n);			
		}		
		List<Candlestick> resultado = new ArrayList<Candlestick>();
		for(List<Negocio> grupoNegocios: mapa.values()){
			resultado.add(candlestick(grupoNegocios));
		}
			
		return resultado;	
	}
	
	private static boolean mesmaAcao(List<Negocio> negocios){
		Set<String> acoes = new HashSet<String>();
		for(Negocio n: negocios){
			acoes.add(n.getAcao());
		}
		return acoes.size() == 1;
	}		
}