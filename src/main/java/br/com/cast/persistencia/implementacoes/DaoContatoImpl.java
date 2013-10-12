package br.com.cast.persistencia.implementacoes;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cast.model.Contato;
import br.com.cast.model.Usuario;
import br.com.cast.persistencia.generico.JPAGenericoDao;
import br.com.cast.persistencia.interfaces.IDaoContato;

@Repository
public class DaoContatoImpl extends JPAGenericoDao<Contato> implements IDaoContato {

	private static final long serialVersionUID = 678333785861636365L;

	@SuppressWarnings("unchecked")
	public List<Contato> recuperarContatoUsuario(Usuario usuario){
			String jpql = "FROM Contato c WHERE c.usuario.id = ?";
			return (List<Contato>) super.buscarPorJpql(jpql, usuario.getId());
		}
		
	}
