import scala.util.Random
import Candlestick._

object CandlestickUsage extends App {
	val acoes = List("PETR4", "VALE5", "CSNA3")
	val random = new Random(System.nanoTime)
	val negocios = {
		for(simbolo <- acoes; 
			quantas <- 1 to 5; 
			valor <- 1 to 5)
			yield Negocio(acao = simbolo, 
						  quantidade = math.abs(random.nextInt % 1000), 
						  preco = math.abs(random.nextDouble * 100.0))
	}
	println("Lista de negocios:")
	negocios.foreach(println)
	println("Lista de Candlesticks:")
	candlesticks(negocios).foreach(println)	
}