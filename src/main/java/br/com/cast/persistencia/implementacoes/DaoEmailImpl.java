package br.com.cast.persistencia.implementacoes;

import org.springframework.stereotype.Repository;

import br.com.cast.model.Email;
import br.com.cast.persistencia.generico.JPAGenericoDao;
import br.com.cast.persistencia.interfaces.IDaoEmail;

@Repository
public class DaoEmailImpl extends JPAGenericoDao<Email> implements IDaoEmail {


}
