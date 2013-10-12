package br.com.cast.persistencia.implementacoes;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.cast.model.Estado;
import br.com.cast.persistencia.generico.JPAGenericoDao;
import br.com.cast.persistencia.interfaces.IDaoEstado;

@Repository
public class DaoEstadoImpl extends JPAGenericoDao<Estado> implements IDaoEstado, Serializable {
	private static final long serialVersionUID = 1L;

	public DaoEstadoImpl() {
		super(Estado.class);
	}

}
