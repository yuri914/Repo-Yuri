package br.com.cast.persistencia.implementacoes;

import org.springframework.stereotype.Repository;

import br.com.cast.model.Telefone;
import br.com.cast.persistencia.generico.JPAGenericoDao;
import br.com.cast.persistencia.interfaces.IDaoTelefone;

@Repository
public class DaoTelefoneImpl extends JPAGenericoDao<Telefone> implements IDaoTelefone {

	public DaoTelefoneImpl() {
		super(Telefone.class);
	}

}
