package br.com.cast.persistencia.interfaces;

import java.util.List;

import br.com.cast.model.Cidade;
import br.com.cast.model.Estado;
import br.com.cast.persistencia.generico.GenericoDao;

public interface IDaoCidade extends GenericoDao<Cidade> {

	public List<Cidade> buscarListaCidadesBanco(Estado estado);
	
}
