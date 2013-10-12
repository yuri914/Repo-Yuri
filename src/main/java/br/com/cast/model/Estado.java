package br.com.cast.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_estado")
public class Estado implements Serializable {

	private static final long serialVersionUID = -8869732624272917694L;

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="sigla")
	private String sigla;
	
	@Column(name="nome")
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
