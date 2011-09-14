class Trade(val stock: String, val price: Double, val quantity: Int){
	require(stock != null)
	require(price >= 0.0)
	require(quantity >= 0)

	def volume = price * quantity

	override def toString = "stock: %s, price: %.2f, quantity: %d".format(stock,price,quantity)
}

object Trade {
	def apply(stock: String = "", price: Double = 0.0, quantity: Int = 0) = 
		new Trade(stock, price, quantity)	
}

class Candlestick(val stock: String, val open: Double, val close: Double, val lower: Double, 
					val high: Double, val volume: Double){
	require(stock != null)
	require(open >= 0.0)
	require(close >= 0.0)
	require(lower >= 0.0)
	require(high >= 0.0)
	require(volume >= 0.0)

	def high_? = open < close
	
	def low_? = !high_?

	override def toString = "stock: %s open %.2f, close: %.2f, lower: %.2f, high: %.2f, volume: %.2f".format(
		stock, open, close, lower, high, volume)
}

object Candlestick {
	private lazy val nullCandlestick = newCandlestick()	

	def candlesticks(trades: List[Trade]): List[Candlestick] = {
		trades.groupBy(_.stock).map {
			case (_, list) => Candlestick(list)
		}.toList
	}

	def apply(trades: List[Trade]): Candlestick = {
		require(sameStock(trades))
		trades match {
			case Nil => newCandlestick()
			case list => {
				val high = trades.map(_.price).max
				val low = trades.map(_.price).min
				val volume = trades.map(_.volume).sum				
				newCandlestick(list.head.stock,
								list.head.price,
								list.last.price,
								high,low,volume)
			}
		}	
	}

	private def sameStock(trades: List[Trade]): Boolean = {
		trades.groupBy(_.stock).size == 1
	}

	private def newCandlestick(stock: String = "", 
								open: Double = 0.0,
								close: Double = 0.0, 
								high: Double = 0.0,
								lower: Double = 0.0, 
								volume: Double = 0.0): Candlestick = {
		new Candlestick(stock = stock, 
						high = high, 
						lower = lower, 
						close = close, 
						open = open, 
						volume = volume)
	}
}


