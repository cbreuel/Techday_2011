import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Candlestick {
	private String stock;
	private double open;
	private double close;
	private double low;
	private double high;
	private double volume;
	
	public Candlestick(String stock, double open, double close, double low, double high, double volume){
		if(stock == null) throw new IllegalArgumentException("stock cannot be null");
		if(open < 0.0) throw new IllegalArgumentException("open must be positive");
		if(close < 0.0) throw new IllegalArgumentException("close must be positive");
		if(low < 0.0) throw new IllegalArgumentException("low must be positive");
		if(high < 0.0) throw new IllegalArgumentException("high must be positive");
		if(volume < 0.0) throw new IllegalArgumentException("volume must be positive");
		this.stock = stock;
		this.open = open;
		this.close = close;
		this.low = low;
		this.high = high;
		this.volume = volume;
	}
	
	public boolean isHigh(){
		return open < close;
	}
	
	public boolean isLow(){
		return !isHigh();
	}
	
	public String toString(){
		return String.format("stock: %s, open: %.2f, close: %.2f, low: %.2f, high: %.2f, volume: %.2f, is high? %s", stock, open, close, low, high, volume, isHigh() ? "true" : false);
	}
	
	public String getStock() {
		return stock;
	}

	public double getOpen() {
		return open;
	}

	public double getClose() {
		return close;
	}

	public double getLow() {
		return low;
	}

	public double getHigh() {
		return high;
	}

	public double getVolume() {
		return volume;
	}

	public static Candlestick candlestick(List<Trade> trades){
		if(!mesmaStock(trades)) throw new IllegalArgumentException("trades must by of the same stock");
		if(trades == null || trades.isEmpty()){
			return new Candlestick("", 0.0, 0.0, 0.0, 0.0, 0.0);
		}
		else{
			double high = Double.MIN_VALUE;
			double low = Double.MAX_VALUE;
			double volume = 0.0;
			for(Trade n: trades){				
				if(n.getPrice() > high){
					high = n.getPrice();
				}
				if(n.getPrice() < low){
					low = n.getPrice();
				}
				volume += n.getVolume();
			}
			return new Candlestick(trades.get(0).getStock(), trades.get(0).getPrice(), trades.get(trades.size() - 1).getPrice(), low, high, volume);
		}
	}
	
	public static List<Candlestick> candlesticks(List<Trade> trades){
		Map<String, List<Trade>> map = new HashMap<String, List<Trade>>();
		for(Trade n: trades){
			List<Trade> list = map.get(n.getStock());
			if(list == null){
				list = new ArrayList<Trade>();
				map.put(n.getStock(), list);
			}
			list.add(n);			
		}		
		List<Candlestick> resultado = new ArrayList<Candlestick>();
		for(List<Trade> groupTrades: map.values()){
			resultado.add(candlestick(groupTrades));
		}
			
		return resultado;	
	}
	
	private static boolean mesmaStock(List<Trade> trades){
		Set<String> stocks = new HashSet<String>();
		for(Trade n: trades){
			stocks.add(n.getStock());
		}
		return stocks.size() == 1;
	}		
}