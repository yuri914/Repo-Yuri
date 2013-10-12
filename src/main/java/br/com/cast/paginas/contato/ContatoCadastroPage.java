package br.com.cast.paginas.contato;

import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.cast.model.Cidade;
import br.com.cast.model.Contato;
import br.com.cast.model.Email;
import br.com.cast.model.Estado;
import br.com.cast.model.Telefone;
import br.com.cast.service.AuxiliarService;
import br.com.cast.service.CidadeService;
import br.com.cast.service.ContatoService;
import br.com.cast.service.EmailService;
import br.com.cast.service.EstadoService;
import br.com.cast.service.TelefoneService;
import br.com.cast.to.EnderecoTO;

public class ContatoCadastroPage extends WebPage {

	private static final long serialVersionUID = -2008399313074287120L;
	
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
	
	public ContatoCadastroPage(){
		add(new ContatoCadastroForm("formularioContato"){

			private static final long serialVersionUID = 6406620238201599379L;

			protected List<Estado> buscarListaEstados() {
				return serviceEstado.buscarListaEstadosbanco();
			}

			protected List<Cidade> buscarListaCidades(Estado estado) {
				return serviceCidade.buscarListaCidadesBanco(estado);
			}

			protected void salvarContato(Contato contato) {
				serviceContato.salvarContatoBanco(contato);
			}

			protected void salvarTelefone(Telefone telefone) {
				serviceTelefone.salvarTelefoneBanco(telefone);
			}

			protected void salvarEmail(Email email) {
				serviceEmail.salvarEmailBanco(email);
			}

			protected EnderecoTO consultarCep(Integer cep) {
				return serviceAux.recuperarEndereco(cep);
			}
			
		});
	}
	
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
	}
}
