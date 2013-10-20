package br.com.cast.paginas.usuario;

import java.util.Arrays;
import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import br.com.cast.model.Usuario;
import br.com.cast.paginas.contato.ContatoCadastroPage;
import br.com.cast.paginas.pesquisa.UsuarioPesquisaPage;
import br.com.cast.util.enumerations.EnumGenero;

public abstract class HomeForm extends Form<Usuario> {

	private static final long serialVersionUID = 1434251727526599430L;
	
	private FeedbackPanel feedback;
	private TextField<String> loginTxtField;
	private PasswordTextField senhaTxtField;
	private AjaxButton btEntrar;
	private TextField<String> nome;
	private RadioChoice<EnumGenero> genero;
	private DateTextField nascimento;
	private TextField<Long> cpf;
	private TextField<String> loginCadastro;
	private TextField<String> senhaCadastro;
	private AjaxButton btCadastrar;
	Usuario usuarioLogin;
	Usuario usuarioCadastro;

	public HomeForm(String id) {
		super(id);
		
		feedback = new FeedbackPanel("mensagem");
		feedback.setOutputMarkupId(true);
		add(feedback);
		
		loginTxtField = new TextField<String>("login");
		loginTxtField.setModel(new PropertyModel<String>(getUsuarioLogin(), "login"));
		add(loginTxtField);
		
		senhaTxtField = new PasswordTextField("senha");
		senhaTxtField.setModel(new PropertyModel<String>(getUsuarioLogin(), "senha"));
		senhaTxtField.setRequired(false);
		add(senhaTxtField);
		
		btEntrar = new AjaxButton("entrar") {

			private static final long serialVersionUID = -7629953510484947562L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Usuario usuarioEncontrado = autenticarUsuario(getUsuarioLogin());
				if(usuarioEncontrado != null){
					getSession().setAttribute("usuarioSessao", usuarioEncontrado);
					setResponsePage(UsuarioPesquisaPage.class);
				} else {
					error("Usuario ou senha inválidos!");
					target.add(feedback);
				}
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
		};
		add(btEntrar);
		
		nome = new TextField<String>("nome");
		nome.setModel(new PropertyModel<String>(getUsuarioCadastro(), "nome"));
		add(nome);
		
		genero = new RadioChoice<EnumGenero>("genero");
		genero.setChoices(Arrays.asList(EnumGenero.values()));
		genero.setChoiceRenderer(new ChoiceRenderer<EnumGenero>("descricao"));
		genero.setModel(new PropertyModel<EnumGenero>(getUsuarioCadastro(), "genero"));
		genero.setSuffix("");
		add(genero);
		
		nascimento = new DateTextField("dataNascimento", 
		new PropertyModel<Date>(getUsuarioCadastro(), "dataNascimento"), new StyleDateConverter("S-", true));
		DatePicker datePicker = new DatePicker();
		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		nascimento.add(datePicker);
		add(nascimento);
		
		cpf = new TextField<Long>("cpf");
		cpf.setModel(new PropertyModel<Long>(getUsuarioCadastro(), "cpf"));
		add(cpf);
		
		loginCadastro = new TextField<String>("loginCadastro");
		loginCadastro.setModel(new PropertyModel<String>(getUsuarioCadastro(), "login"));
		add(loginCadastro);
		
		senhaCadastro = new TextField<String>("senhaCadastro");
		senhaCadastro.setModel(new PropertyModel<String>(getUsuarioCadastro(), "senha"));
		add(senhaCadastro);
		
		btCadastrar = new AjaxButton("cadastrar") {

			private static final long serialVersionUID = -4309959029136917439L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				salvarUsuario(getUsuarioCadastro());
				getSession().setAttribute("usuarioSessao", getUsuarioCadastro());
				setResponsePage(ContatoCadastroPage.class);
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
			
		};
		add(btCadastrar);
	}

	protected abstract Usuario autenticarUsuario(Usuario usuarioLogin);
	protected abstract void salvarUsuario(Usuario usuario);

	public Usuario getUsuarioLogin() {
		if (usuarioLogin == null) {
			usuarioLogin = new Usuario();
		}
		return usuarioLogin;
	}

	public Usuario getUsuarioCadastro() {
		if (usuarioCadastro == null) {
			usuarioCadastro = new Usuario();
		}
		return usuarioCadastro;
	}

}
