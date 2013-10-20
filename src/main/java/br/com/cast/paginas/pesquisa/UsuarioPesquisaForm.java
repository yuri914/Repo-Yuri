package br.com.cast.paginas.pesquisa;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import br.com.cast.model.Cidade;
import br.com.cast.model.Estado;
import br.com.cast.model.Usuario;
import br.com.cast.to.DistanciaTO;

/**
 * Esta classe representa o formulario de pesquisa de Usuario. 
 * 
 * @author Yuri Cavalcante {yuricavalcante914@hotmail.com}
 */
public abstract class UsuarioPesquisaForm extends Form<Usuario> {

	private static final long serialVersionUID = -7771255208386313829L;
	private TextField<String> nomePesquisa;
	private Usuario usuarioSessao;
	private DropDownChoice<Estado> dropEstado;
	private WebMarkupContainer containerCidade;
	private DropDownChoice<Cidade> dropCidades;
	private CheckBox opcoesAvancadas;
	private TextField<Double> distanciaMaxima;
	private WebMarkupContainer containerOpcoesAvancadas;
	private UsuarioPesquisaGrid panelUsuarios;
	private AjaxButton btFiltrar;
	
	public UsuarioPesquisaForm(String id) {
		super(id);
		
		setUsuarioSessao((Usuario) getSession().getAttribute("usuarioSessao"));
		
		nomePesquisa = new TextField<String>("nome");
		nomePesquisa.setModel(new Model<String>());
		add(nomePesquisa);
		
		dropEstado = new DropDownChoice<Estado>("estado");
		dropEstado.setModel(new Model<Estado>());
		dropEstado.setChoices(buscarListaEstados());
		dropEstado.setChoiceRenderer(new ChoiceRenderer<Estado>("nome"));
		dropEstado.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			
			private static final long serialVersionUID = -9127400784951487706L;

			protected void onUpdate(AjaxRequestTarget target) {
				dropCidades.setChoices(buscarListaCidades(dropEstado.getModelObject()));
				containerCidade.setVisible(true);
				target.add(containerCidade, dropCidades);
			}
		});
		add(dropEstado);
		
		containerCidade = new WebMarkupContainer("containerCidade");
		containerCidade.setOutputMarkupPlaceholderTag(true);
		containerCidade.setVisible(false);
		add(containerCidade);
		
		dropCidades = new DropDownChoice<Cidade>("cidade");
		dropCidades.setOutputMarkupPlaceholderTag(true);
		dropCidades.setModel(new Model<Cidade>());
		dropCidades.setChoiceRenderer(new ChoiceRenderer<Cidade>("nome"));
		containerCidade.add(dropCidades);
		
		opcoesAvancadas = new CheckBox("opcoesAvc");
		opcoesAvancadas.setModel(new Model<Boolean>());
		opcoesAvancadas.add(new AjaxFormComponentUpdatingBehavior("onclick"){

			private static final long serialVersionUID = -734302242461499113L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(containerOpcoesAvancadas.isVisible())
					containerOpcoesAvancadas.setVisible(false);
				else 
					containerOpcoesAvancadas.setVisible(true);
				
				target.add(containerOpcoesAvancadas);
			}
		});
		add(opcoesAvancadas);
		
		containerOpcoesAvancadas = new WebMarkupContainer("containerDistanciaMax");
		containerOpcoesAvancadas.setOutputMarkupPlaceholderTag(true);
		containerOpcoesAvancadas.setVisible(false);
		add(containerOpcoesAvancadas);
		
		distanciaMaxima = new TextField<Double>("distanciaMaxima");
		distanciaMaxima.setModel(new Model<Double>());
		distanciaMaxima.setOutputMarkupPlaceholderTag(true);
		containerOpcoesAvancadas.add(distanciaMaxima);
		
		panelUsuarios = new UsuarioPesquisaGrid("gridPesquisaUsuario"){

			private static final long serialVersionUID = -192454810455470111L;

			protected Cidade buscarCidadeUsuario(Usuario usuario) {
				return recuperarCidadeUsuario(usuario);
			}

			protected DistanciaTO calcularDistancia(Usuario usuarioGrid) {
				return calcularDistanciaUsuario(usuarioGrid, getUsuarioSessao());
			}
		};
		panelUsuarios.getGridUsuario(buscarListaUsuarios(getUsuarioSessao()));
		panelUsuarios.setOutputMarkupId(true);
		//panelUsuarios.setVisible(false);
		add(panelUsuarios);
		
		btFiltrar = new AjaxButton("filtrar"){

			private static final long serialVersionUID = -6077016995048291036L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				List<Usuario> listaUsuariosFiltrados = buscarUsuarioFiltro(nomePesquisa.getModelObject(), 
				dropCidades.getModelObject(), dropEstado.getModelObject(), distanciaMaxima.getModelObject());
				panelUsuarios.getGridUsuario(listaUsuariosFiltrados);
				target.add(panelUsuarios);
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				
			}
		};
		add(btFiltrar);
	}

	public Usuario getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(Usuario usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}

	protected abstract List<Usuario> buscarUsuarioFiltro(String nomePesquisa,
	Cidade cidadePesquisa, Estado estadoPesquisa, Double distMaxima);
	protected abstract List<Usuario> buscarListaUsuarios(Usuario usuarioLogado);
	protected abstract DistanciaTO calcularDistanciaUsuario(Usuario usuarioGrid, Usuario usuarioSessao);
	protected abstract Cidade recuperarCidadeUsuario(Usuario usuario);
	protected abstract List<Cidade> buscarListaCidades(Estado estado);
	protected abstract List<Estado> buscarListaEstados();

}
