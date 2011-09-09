import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CandlestickUsage {

	public static void main(String[] args) {
		String [] acoes = new String[]{"PETR4", "VALUE5", "CSNA3"};
		Random random = new Random(System.nanoTime());
		List<Negocio> negocios = new ArrayList<Negocio>();
		for(String acao : acoes){
			for(int i = 0; i < 5; i++){
				for(int j = 0; j < 5; j++){
					negocios.add(Negocio.apply(acao, Math.abs(random.nextDouble() * 100.0), Math.abs(random.nextInt() % 100)));
				}
			}
		}
		System.out.println("Lista de negocios:");
		for(Negocio n : negocios){
			System.out.println(n);
		}
		System.out.println("Lista de candles:");
		List<Candlestick> candles = Candlestick.candlesticks(negocios);
		for(Candlestick c : candles){
			System.out.println(c);
		}
	}
}
