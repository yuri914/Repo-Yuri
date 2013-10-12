package br.com.cast.to;

/**
 * Esta classe representa a estrutura retornada por um webservice que calcula a distancia entre usuarios. 
 * 
 * @author Yuri Cavalcante {yuricavalcante914@hotmail.com}
 */
public class DistanciaTO {

	private String distancia;
	
	
	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}
	
	/**
	 * 
	 * Este método tem a função de retirar as casas decimais da distancia retornando apenas o número inteiro. 
	 *
	 * @author Yuri Cavalcante
	 * @return
	 */
	public String getDistanciaFormatada(){
		Double valorDistancia = Double.valueOf(getDistancia());
		String valorFormatado = String.format("%.0f", valorDistancia) + " Km";
		return valorFormatado;
	}
	
}
