package br.com.cast.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_telefone")
public class Telefone implements Serializable {

	private static final long serialVersionUID = -6196399449961668937L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_contato")
	private Contato contato;
	
	@Column(name="ddi")
	private Integer ddi;
	
	@Column(name="ddd")
	private Integer ddd;
	
	@Column(name="numero")
	private Integer numero;
	
	@Column(name="primario")
	private Boolean primario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
*/
	public Integer getDdi() {
		return ddi;
	}

	public void setDdi(Integer ddi) {
		this.ddi = ddi;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getPrimario() {
		return primario;
	}

	public void setPrimario(Boolean primario) {
		this.primario = primario;
	}
	
}
