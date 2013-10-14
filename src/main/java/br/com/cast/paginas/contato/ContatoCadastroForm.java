package br.com.cast.paginas.contato;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.cast.model.Cidade;
import br.com.cast.model.Contato;
import br.com.cast.model.Email;
import br.com.cast.model.Estado;
import br.com.cast.model.Telefone;
import br.com.cast.model.Usuario;
import br.com.cast.paginas.pesquisa.UsuarioPesquisaPage;
import br.com.cast.to.EnderecoTO;

public abstract class ContatoCadastroForm extends Form<Contato> {

	private static final long serialVersionUID = -4026405758311652824L;
	private Usuario usuario;
	private Contato contato;
	private Email email;
	private Telefone telefone;
	private FeedbackPanel feedback;
	private TextField<Integer> cep;
	private TextField<String> logradouro;
	private TextField<String> bairro;
	private TextField<String> complemento;
	private TextField<String> emailTxtField;
	private AjaxButton btNovoEmail;
	private TextField<Integer> telefoneTxtField;
	private AjaxButton btNovoNumero;
	private AjaxButton btConfirmar;
	private DropDownChoice<Estado> estadoDropDown;
	private DropDownChoice<Cidade> cidadeDropDown;
	private DropDownChoice<Integer> ddd;
	private List<Email> listaEmails;
	private List<Telefone> listaTelefones;
	
	public ContatoCadastroForm(String id) {
		super(id);
		
		setUsuario((Usuario) getSession().getAttribute("usuarioSessao"));
		
		feedback = new FeedbackPanel("mensagem");
		feedback.setOutputMarkupId(true);
		add(feedback);
		
		cep = new TextField<Integer>("cep");
		cep.setRequired(true);
		cep.setModel(new PropertyModel<Integer>(getContato(), "cep"));
		cep.add(new AjaxFormComponentUpdatingBehavior("onblur") {
			
			private static final long serialVersionUID = -5980431044207455543L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				EnderecoTO enderecoEncontrado = consultarCep(cep.getModelObject());
				logradouro.setModel(Model.of(enderecoEncontrado.getLogradouro()));
				bairro.setModel(Model.of(enderecoEncontrado.getBairro()));
				target.add(logradouro, bairro);
			}
		});
		add(cep);
		
		logradouro = new TextField<String>("logradouro");
		logradouro.setRequired(true);
		logradouro.setModel(new PropertyModel<String>(getContato(), "logradouro"));
		add(logradouro);
		
		bairro = new TextField<String>("bairro");
		bairro.setRequired(true);
		bairro.setModel(new PropertyModel<String>(getContato(), "bairro"));
		add(bairro);
		
		complemento = new TextField<String>("complemento");
		complemento.setModel(new PropertyModel<String>(getContato(), "complemento"));
		add(complemento);
		
		emailTxtField = new TextField<String>("email");
		emailTxtField.setRequired(true);
		emailTxtField.setModel(new PropertyModel<String>(getEmail(), "email"));
		getEmail().setPrimario(true);
		listaEmails.add(getEmail());
		add(emailTxtField);
		
		estadoDropDown = new DropDownChoice<Estado>("estado");
		estadoDropDown.setChoiceRenderer(new ChoiceRenderer<Estado>("nome"));
		estadoDropDown.setModel(new Model<Estado>());
		estadoDropDown.setRequired(true);
		estadoDropDown.setChoices(buscarListaEstados());
		estadoDropDown.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			
			private static final long serialVersionUID = -5980431044207455543L;

			protected void onUpdate(AjaxRequestTarget target) {
				List<Cidade> listaCidades = buscarListaCidades(estadoDropDown.getModelObject());
				cidadeDropDown.setChoices(listaCidades);
				target.add(cidadeDropDown);
			}
		});
		add(estadoDropDown);
		
		cidadeDropDown = new DropDownChoice<Cidade>("cidade");
		cidadeDropDown.setChoiceRenderer(new ChoiceRenderer<Cidade>("nome"));
		cidadeDropDown.setModel(new PropertyModel<Cidade>(getContato(), "cidade"));
		cidadeDropDown.setRequired(true);
		add(cidadeDropDown);
		
		btNovoEmail = new AjaxButton("novoEmail") {

			private static final long serialVersionUID = 2740087915303507582L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
			}
		};
		add(btNovoEmail);
		
		ddd = new DropDownChoice<Integer>("ddd");
		List<Integer> listaDdd = new ArrayList<Integer>(Arrays.asList(21, 11, 71, 85, 51, 31));
		ddd.setModel(new PropertyModel<Integer>(getTelefone(), "ddd"));
		ddd.setChoices(listaDdd);
		ddd.setRequired(true);
		add(ddd);
		
		getContato().setUsuario(getUsuario());
		
		telefoneTxtField = new TextField<Integer>("numero");
		telefoneTxtField.setRequired(true);
		telefoneTxtField.setModel(new PropertyModel<Integer>(getTelefone(), "numero"));
		getTelefone().setDdi(55);
		getTelefone().setPrimario(true);
		listaTelefones.add(getTelefone());
		add(telefoneTxtField);
		
		btNovoNumero = new AjaxButton("novoNumero") {

			private static final long serialVersionUID = -1828597654788788397L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
			}
		};
		add(btNovoNumero);
		
		btConfirmar = new AjaxButton("confirmar"){

			private static final long serialVersionUID = -8512551000762802515L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				getContato().setLogradouro(logradouro.getModelObject());
				getContato().setBairro(bairro.getModelObject());
				getContato().setEmails(listaEmails);
				getContato().setTelefones(listaTelefones);
				salvarContato(getContato());
				setResponsePage(UsuarioPesquisaPage.class);
			}
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
		};
		add(btConfirmar);
	}

	protected abstract EnderecoTO consultarCep(Integer cep);
	protected abstract void salvarEmail(Email email);
	protected abstract void salvarTelefone(Telefone telefone);
	protected abstract void salvarContato(Contato contato);
	protected abstract List<Cidade> buscarListaCidades(Estado estado);
	protected abstract List<Estado> buscarListaEstados();

	public Contato getContato() {
		if (contato == null) {
			contato = new Contato();
		}
		return contato;
	}

	public Email getEmail() {
		if (email == null) {
			email = new Email();
		}
		return email;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Telefone getTelefone() {
		if (telefone == null) {
			telefone = new Telefone();
		}
		return telefone;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
