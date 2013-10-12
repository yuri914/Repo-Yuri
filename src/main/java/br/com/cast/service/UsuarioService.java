package br.com.cast.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.model.Cidade;
import br.com.cast.model.Estado;
import br.com.cast.model.Usuario;
import br.com.cast.persistencia.interfaces.IDaoUsuario;

@Service("usuarioService")
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = -1905789314329929056L;
	@Autowired
	private IDaoUsuario daoUsuario;
	
	public void salvarUsuario(Usuario usuario){
		daoUsuario.salvar(usuario);
	}
	
	public List<Usuario> buscarListaUsuarioBanco(){
		return daoUsuario.buscarTodos();
	}
	
	public Usuario autenticarUsuarioBanco(Usuario usuarioLogin) {
		Usuario usuarioEncontrado = null;
		List<Usuario> listaUsuario = daoUsuario.autenticarUsuarioSistema(usuarioLogin);
		if (listaUsuario.size() != 0){
			usuarioEncontrado = listaUsuario.get(0);
		}
		return usuarioEncontrado;
	}

	public List<Usuario> recuperarListaUsuarioFiltrado(String nomePesquisa,
			Cidade cidadePesquisa, Estado estadoPesquisa, Double distMaxima) {
		return daoUsuario.recuperarListaUsuarioFiltroBanco(
		nomePesquisa, cidadePesquisa, estadoPesquisa, distMaxima);
	}
}
