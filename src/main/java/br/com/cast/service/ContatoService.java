package br.com.cast.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.model.Contato;
import br.com.cast.model.Usuario;
import br.com.cast.persistencia.interfaces.IDaoContato;

@Service
public class ContatoService implements Serializable {

	private static final long serialVersionUID = 7216378092008210835L;
	@Autowired
	private IDaoContato daoContato;

	public void salvarContatoBanco(Contato contato){
		daoContato.salvar(contato);
	}
	
	public Contato recuperarContatoUsuario(Usuario usuario){
		List<Contato> listaContato = daoContato.recuperarContatoUsuario(usuario);
		return listaContato.get(0);
	}
}
