package br.com.cast.to;

/**
 * Esta classe representa a estrutura retornada por um WebService de Endereços
 * 
 * @author Yuri Cavalcante {yuricavalcante914@hotmail.com}
 * 
 */
public class EnderecoTO {

	private String logradouro;
	private String bairro;
	private String cidade;
	private String uf;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
