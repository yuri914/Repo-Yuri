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
@Table(name = "tb_email")
public class Email implements Serializable {

	private static final long serialVersionUID = 4726110853741375871L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_contato")
	private Contato contato;

	@Column(name = "email")
	private String email;

	@Column(name = "primario")
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
	}*/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getPrimario() {
		return primario;
	}

	public void setPrimario(Boolean primario) {
		this.primario = primario;
	}

}
