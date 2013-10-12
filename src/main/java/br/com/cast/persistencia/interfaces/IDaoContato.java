package br.com.cast.persistencia.interfaces;

import java.util.List;

import br.com.cast.model.Contato;
import br.com.cast.model.Usuario;
import br.com.cast.persistencia.generico.GenericoDao;

public interface IDaoContato extends GenericoDao<Contato> {

	public List<Contato> recuperarContatoUsuario(Usuario usuario);
	
}
