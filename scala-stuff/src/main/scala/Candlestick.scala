class Negocio(val acao: String, val preco: Double, val quantidade: Int){
	require(acao != null)
	require(preco >= 0.0)
	require(quantidade >= 0)

	def volume = preco * quantidade

	override def toString = "acao: %s, preco: %.2f, quantidade: %d".format(						acao, preco, quantidade)
}

object Negocio {
	def apply(acao: String = "", 
				preco: Double = 0.0, 
				quantidade: Int = 0	): Negocio = {
		new Negocio(acao, preco, quantidade)
	}
}

class Candlestick(val acao: String, 
					val abertura: Double, 
					val fechamento: Double,
					val minimo: Double, 
					val maximo: Double, 
					val volume: Double){
	require(acao != null)
	require(abertura >= 0.0)
	require(fechamento >= 0.0)
	require(minimo >= 0.0)
	require(maximo >= 0.0)
	require(volume >= 0.0)

	def alta_? = abertura < fechamento
	
	def baixa_? = !alta_?

	override def toString = 
	"acao: %s abertura %.2f, fechamento: %.2f, minimo: %.2f, maximo: %.2f, volume: %.2f".format(acao, abertura, fechamento, minimo, maximo, volume)
}

object Candlestick {
	private lazy val nullCandlestick = newCandlestick()	

	def candlesticks(negocios: List[Negocio]): List[Candlestick] = {
		negocios.groupBy(_.acao).map {
			case (_, lista) => Candlestick(lista)
		}.toList
	}

	def apply(negocios: List[Negocio]): Candlestick = {
		require(mesmaAcao(negocios))
		negocios match {
			case Nil => newCandlestick()
			case lista => {
				val maior = negocios.map(_.preco).max
				val menor = negocios.map(_.preco).min
				val volume = negocios.map(_.volume).sum				
				newCandlestick(
					lista.head.acao,
					lista.head.preco,
					lista.last.preco,
					maior,menor,volume)
			}
		}	
	}

	private def mesmaAcao(negocios: List[Negocio]): Boolean = {
		negocios.groupBy(_.acao).size == 1
	}

	private def newCandlestick(acao: String = "", 
								abertura: Double = 0.0,
								fechamento: Double = 0.0, 
								maximo: Double = 0.0,
								minimo: Double = 0.0,
								volume: Double = 0.0): Candlestick = {
		new Candlestick(acao = acao, 
						maximo = maximo, 
						minimo = minimo,
						fechamento = fechamento, 
						abertura = abertura,
						volume = volume)
	}
}