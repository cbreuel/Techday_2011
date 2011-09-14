import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CandlestickUsage {


	public static void main(String[] args) {
		String [] acoes = new String[]{"PETR4", "VALUE5", "CSNA3"};
		Random random = new Random(System.nanoTime());
		List<Trade> trades = new ArrayList<Trade>();
		for(String acao : acoes){
			for(int i = 0; i < 5; i++){
				for(int j = 0; j < 5; j++){
					trades.add(Trade.createTrade(acao, 
							Math.abs(random.nextDouble() * 100.0), 
							Math.abs(random.nextInt() % 100)));
				}
			}
		}
		System.out.println("List of trades:");
		for(Trade n : trades){
			System.out.println(n);
		}
		System.out.println("List of candles:");
		List<Candlestick> candles = Candlestick.candlesticks(trades);
		for(Candlestick c : candles){
			System.out.println(c);
		}
	}
}
