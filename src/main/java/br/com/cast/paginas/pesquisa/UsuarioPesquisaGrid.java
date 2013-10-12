package br.com.cast.paginas.pesquisa;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import br.com.cast.model.Cidade;
import br.com.cast.model.Usuario;
import br.com.cast.to.DistanciaTO;

/**
 * Esta classe representa a Grid de consulta dos usuarios. 
 * 
 * @author Yuri Cavalcante {yuricavalcante914@hotmail.com}
 */
public abstract class UsuarioPesquisaGrid extends Panel {

	private static final long serialVersionUID = 2083657827160079470L;
	
	public UsuarioPesquisaGrid(String id) {
		super(id);
	}

	public void getGridUsuario(List<Usuario> listaUsuarios){
		DataView<Usuario> repetidor = new DataView<Usuario>("usuarios",
				new ListDataProvider<Usuario>(listaUsuarios), 3) {

					private static final long serialVersionUID = -6536490772368790638L;

					protected void populateItem(Item<Usuario> item) {
						Usuario usuario = item.getModelObject();
						item.add(new Label("nome", usuario.getNome()));
						item.add(new Label("idade", usuario.getIdade()));
						item.add(new Label("cidade", buscarCidadeUsuario(usuario).getNome()));
						item.add(new Label("distancia", calcularDistancia(usuario).getDistanciaFormatada()));
					}
		};
		addOrReplace(new PagingNavigator("paginacao", repetidor));
		addOrReplace(repetidor);
	}

	protected abstract DistanciaTO calcularDistancia(Usuario usuario);
	protected abstract Cidade buscarCidadeUsuario(Usuario usuario);
}
