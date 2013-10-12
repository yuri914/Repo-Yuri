package br.com.cast.persistencia.implementacoes;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cast.model.Cidade;
import br.com.cast.model.Estado;
import br.com.cast.model.Usuario;
import br.com.cast.persistencia.generico.JPAGenericoDao;
import br.com.cast.persistencia.interfaces.IDaoUsuario;

@Repository
public class DaoUsuarioImpl extends JPAGenericoDao<Usuario> implements IDaoUsuario {

	public DaoUsuarioImpl() {
		super(Usuario.class);
	}

	/**
	 * Este método tem a função de verificar se há algum usuario com login e
	 * senha informados e retorna-lo.
	 * 
	 * @author Yuri Cavalcante
	 * @return Lista de Usuarios Encontrados
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> autenticarUsuarioSistema(Usuario usuarioLogin) {
		String jpql = " From Usuario u where u.login = ? and u.senha = ?";
		return (List<Usuario>) super.criarQuery(jpql, usuarioLogin.getLogin(), usuarioLogin.getSenha());
	}

	/**
	 * Este método tem a função de retornar uma lista de usuarios encontrados
	 * com os filtros informados.
	 * 
	 * @author Yuri Cavalcante
	 * @return Lista de Usuarios Filtrados
	 */
	public List<Usuario> recuperarListaUsuarioFiltroBanco(String nomePesquisa, Cidade cidadePesquisa, Estado estadoPesquisa, Double distMaxima) {
		StringBuilder sb = new StringBuilder();
		sb.append(" From Usuario u where 1=1");

		if (nomePesquisa != null) {
			sb.append(" and u.nome = ");
			sb.append(nomePesquisa);
		}
		if (cidadePesquisa != null) {
			sb.append(" and u.");
		}
		return null;
	}

}
