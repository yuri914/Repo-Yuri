package br.com.cast.paginas.usuario;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.cast.model.Usuario;
import br.com.cast.service.UsuarioService;

public class HomePage extends WebPage {

	private static final long serialVersionUID = 5684923330872633189L;

	@SpringBean
	private UsuarioService serviceUsuario;
	
	public HomePage() {
		add(new HomeForm("formularioPrincipal"){

			private static final long serialVersionUID = 3838202755006038831L;

			protected void salvarUsuario(Usuario usuario) {
				serviceUsuario.salvarUsuario(usuario);
			}

			protected Usuario autenticarUsuario(Usuario usuarioLogin) {
				return serviceUsuario.autenticarUsuarioBanco(usuarioLogin);
			}
		});
    }
	
	public UsuarioService getServiceUsuario() {
		if (serviceUsuario == null) {
			serviceUsuario = new UsuarioService();
		}
		return serviceUsuario;
	}

	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
	}
	
}
