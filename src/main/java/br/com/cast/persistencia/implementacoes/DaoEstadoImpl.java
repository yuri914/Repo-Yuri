package br.com.cast.persistencia.implementacoes;

import org.springframework.stereotype.Repository;

import br.com.cast.model.Estado;
import br.com.cast.persistencia.generico.JPAGenericoDao;
import br.com.cast.persistencia.interfaces.IDaoEstado;

@Repository
public class DaoEstadoImpl extends JPAGenericoDao<Estado> implements IDaoEstado {


}
