package br.com.cast.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.model.Email;
import br.com.cast.persistencia.interfaces.IDaoEmail;

@Service
public class EmailService implements Serializable {

	private static final long serialVersionUID = 5605946694743226025L;
	@Autowired
	private IDaoEmail daoEmail;

	public void salvarEmailBanco(Email email){
		daoEmail.salvar(email);
	}
}
