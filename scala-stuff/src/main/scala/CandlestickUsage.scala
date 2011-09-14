import scala.util.Random
import Candlestick._

object CandlestickUsage extends App {
	val stocks = List("PETR4", "VALE5", "CSNA3")
	val random = new Random(System.nanoTime)
	val trades = {
		for(symbol <- stocks; 
			howMuch <- 1 to 5; 
			value <- 1 to 5)
			yield Trade(stock = symbol, 
						  quantity = math.abs(random.nextInt % 1000), 
						  price = math.abs(random.nextDouble * 100.0))
	}
	println("List of stocks:")
	trades.foreach(println)
	println("List of Candlesticks:")
	candlesticks(trades).foreach(println)	
}