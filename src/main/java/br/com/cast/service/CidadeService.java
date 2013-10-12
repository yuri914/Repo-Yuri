package br.com.cast.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.model.Cidade;
import br.com.cast.model.Contato;
import br.com.cast.model.Estado;
import br.com.cast.model.Usuario;
import br.com.cast.persistencia.interfaces.IDaoCidade;

@Service
public class CidadeService implements Serializable {

	private static final long serialVersionUID = 7749217118646594952L;
	@Autowired
	private IDaoCidade daoCidade;
	@Autowired
	private ContatoService serviceContato;

	public List<Cidade> buscarListaCidadesBanco(Estado estado){
		return daoCidade.buscarListaCidadesBanco(estado);
	}
	
	public Cidade recuperarCidadeUsuario(Usuario usuario){
		Contato contatoUsuario = serviceContato.recuperarContatoUsuario(usuario);
		Cidade cidadeUsuario = contatoUsuario.getCidade();
		return cidadeUsuario;
	}
}
