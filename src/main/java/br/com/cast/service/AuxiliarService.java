package br.com.cast.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;

import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.model.Cidade;
import br.com.cast.model.Usuario;
import br.com.cast.to.DistanciaTO;
import br.com.cast.to.EnderecoTO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Esta classe representa um Service Auxiliar para o consumo de WebServices.
 * 
 * @author Yuri Cavalcante {yuricavalcante914@hotmail.com}
 */

@Service
public class AuxiliarService implements Serializable {

	private static final long serialVersionUID = 1465712083543021816L;

	@Autowired
	private ContatoService serviceContato;

	/**
	 * Este método recupera o endereço do usuario consumindo um webservice
	 * ultilizando o cep
	 * 
	 * @author Yuri Cavalcante {yuricavalcante914@hotmail.com}
	 * @return retorna um objeto EnderecoTO com os dados do endereço.
	 */
	public EnderecoTO recuperarEndereco(Integer cep) {
		Gson gson = new Gson();
		String json = jsonBuilder("http://cep.paicon.com.br/json/" + cep);
		return gson.fromJson(json, new TypeToken<EnderecoTO>() {
		}.getType());
	}

	public String jsonBuilder(String caminho) {
		try {
			URL url = new URL(caminho);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String result;
			String json = "";
			while ((result = br.readLine()) != null) {
				json = json + result;
			}
			br.close();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebServiceException(e.getMessage());
		}
	}

	/**
	 * Este método calcula a distancia entre os usuarios e retorna o valor em
	 * um objeto DistanciaTO.
	 * 
	 * @author Yuri Cavalcante {yuricavalcante914@hotmail.com}
	 * @return retorna um objeto DistanciaTO com a distancia entre os usuarios.
	 */
	public DistanciaTO calcularDistanciaWebService(Usuario usuarioGrid, Usuario usuarioSessao) {
		Cidade cidadeUsuarioGrid = serviceContato.recuperarContatoUsuario(usuarioGrid).getCidade();
		Cidade cidadeUsuarioSessao = serviceContato.recuperarContatoUsuario(usuarioSessao).getCidade();
		Gson gson = new Gson();

		StringBuilder sb = new StringBuilder();
		sb.append("http://localhost:8080/WebServiceDistanciaCerta/jersey/distancia/calcularDistancia/");
		sb.append(cidadeUsuarioGrid.getLatitude() + "/" + cidadeUsuarioGrid.getLongitude() + "/");
		sb.append(cidadeUsuarioSessao.getLatitude() + "/" + cidadeUsuarioSessao.getLongitude());

		String json = jsonBuilder(sb.toString());
		return gson.fromJson(json, new TypeToken<DistanciaTO>() {
		}.getType());
	}
}
