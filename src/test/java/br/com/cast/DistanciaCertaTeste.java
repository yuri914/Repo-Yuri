package br.com.cast;

import junit.framework.TestCase;
import br.com.cast.model.Estado;
import br.com.cast.model.Usuario;
import br.com.cast.persistencia.implementacoes.DaoContatoImpl;
import br.com.cast.service.CidadeService;


public class DistanciaCertaTeste extends TestCase {
	
	private CidadeService cidadeService = new CidadeService();
	private DaoContatoImpl daoContato = new DaoContatoImpl();
	private Estado estado = new Estado();
	private Usuario usuario = new Usuario();
	
	/*public void testCidade(){
		estado.setId(1);
		assertNotNull(cidadeService.buscarListaCidadesBanco(estado));
	}*/
	
	public void testListaCidades(){
		usuario.setId(33);
		assertNotNull(daoContato.recuperarContatoUsuario(usuario));
	}
	
}
