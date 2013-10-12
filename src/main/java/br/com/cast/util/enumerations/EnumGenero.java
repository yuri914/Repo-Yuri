package br.com.cast.util.enumerations;

public enum EnumGenero {

	M("Masculino"),
	F("Feminino");
	
	private String descricao;

	private EnumGenero(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
