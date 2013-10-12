package br.com.cast.paginas.pesquisa;

import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.cast.model.Cidade;
import br.com.cast.model.Estado;
import br.com.cast.model.Usuario;
import br.com.cast.paginas.usuario.HomePage;
import br.com.cast.service.AuxiliarService;
import br.com.cast.service.CidadeService;
import br.com.cast.service.ContatoService;
import br.com.cast.service.EmailService;
import br.com.cast.service.EstadoService;
import br.com.cast.service.TelefoneService;
import br.com.cast.service.UsuarioService;
import br.com.cast.to.DistanciaTO;

public class UsuarioPesquisaPage extends WebPage {

	private static final long serialVersionUID = 3387399512450845570L;

	@SpringBean
	private UsuarioService serviceUsuario;
	@SpringBean
	private EstadoService serviceEstado;
	@SpringBean
	private CidadeService serviceCidade;
	@SpringBean
	private ContatoService serviceContato;
	@SpringBean
	private TelefoneService serviceTelefone;
	@SpringBean
	private EmailService serviceEmail;
	@SpringBean
	private AuxiliarService serviceAux;
	private Link<Void> linkSair;
	
	public UsuarioPesquisaPage(){
		
		linkSair = new Link<Void>("linkSair"){

			private static final long serialVersionUID = 8554224543828088336L;

			public void onClick() {
				getSession().invalidate();
				setResponsePage(HomePage.class);
			}
		};
		add(linkSair);
		
		add(new UsuarioPesquisaForm("formularioPesquisa"){

			private static final long serialVersionUID = -3259097283110199137L;
			
			@Override
			protected Cidade recuperarCidadeUsuario(Usuario usuario) {
				return serviceCidade.recuperarCidadeUsuario(usuario);
			}

			@Override
			protected List<Cidade> buscarListaCidades(Estado estado) {
				return serviceCidade.buscarListaCidadesBanco(estado);
			}

			@Override
			protected List<Estado> buscarListaEstados() {
				return serviceEstado.buscarListaEstadosbanco();
			}

			@Override
			protected DistanciaTO calcularDistanciaUsuario(Usuario usuarioGrid, Usuario usuarioSessao) {
				return serviceAux.calcularDistanciaWebService(usuarioGrid, usuarioSessao);
			}

			@Override
			protected List<Usuario> buscarListaUsuarios() {
				return serviceUsuario.buscarListaUsuarioBanco();
			}

			@Override
			protected List<Usuario> buscarUsuarioFiltro(String nomePesquisa,
			Cidade cidadePesquisa, Estado estadoPesquisa, Double distMaxima) {
				return serviceUsuario.recuperarListaUsuarioFiltrado(
				nomePesquisa, cidadePesquisa, estadoPesquisa, distMaxima);
			}
			
		});
	}
	
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
	}
}
