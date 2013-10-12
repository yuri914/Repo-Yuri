package br.com.cast.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.model.Telefone;
import br.com.cast.persistencia.interfaces.IDaoTelefone;

@Service
public class TelefoneService implements Serializable {

	private static final long serialVersionUID = 4249843387568654730L;
	@Autowired
	private IDaoTelefone daoTelefone;

	public void salvarTelefoneBanco(Telefone telefone){
		daoTelefone.salvar(telefone);
	}
}
