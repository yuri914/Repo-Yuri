package br.com.cast.persistencia.generico;

import java.io.Serializable;
import java.util.List;

public interface GenericoDao <T extends Serializable> {

	void salvar(T t);
	void atualizar(T t);
	void remover(Serializable id);
	List<T> buscarTodos();
	
}
