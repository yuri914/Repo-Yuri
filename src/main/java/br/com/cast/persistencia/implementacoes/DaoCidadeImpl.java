package br.com.cast.persistencia.implementacoes;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cast.model.Cidade;
import br.com.cast.model.Estado;
import br.com.cast.persistencia.generico.JPAGenericoDao;
import br.com.cast.persistencia.interfaces.IDaoCidade;

@Repository
public class DaoCidadeImpl extends JPAGenericoDao<Cidade> implements IDaoCidade, Serializable {
	private static final long serialVersionUID = 1L;

	public DaoCidadeImpl() {
		super(Cidade.class);
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> buscarListaCidadesBanco(Estado estado) {
		String jpql = "From Cidade c where c.estado.id = ?";
		return (List<Cidade>) super.buscarPorJpql(jpql, estado.getId());
	}

}
