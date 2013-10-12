package br.com.cast.persistencia.interfaces;

import java.util.List;

import br.com.cast.model.Cidade;
import br.com.cast.model.Estado;
import br.com.cast.model.Usuario;
import br.com.cast.persistencia.generico.GenericoDao;

public interface IDaoUsuario extends GenericoDao<Usuario> {

	public List<Usuario> autenticarUsuarioSistema(Usuario usuarioLogin);
	public List<Usuario> recuperarListaUsuarioFiltroBanco(String nomePesquisa,
	Cidade cidadePesquisa, Estado estadoPesquisa, Double distMaxima);
}
