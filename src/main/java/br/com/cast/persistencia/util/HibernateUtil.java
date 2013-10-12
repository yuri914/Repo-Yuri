package br.com.cast.persistencia.util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class HibernateUtil<T> implements Serializable {

	private static final long serialVersionUID = 5176257436388304284L;
	
	public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("distancia_certa_unit");
	public static EntityManager entityManager = factory.createEntityManager();
	
	private Class<T> persistentClass;
	
	public boolean salvar(Object objeto){
		boolean isSucessoSalvar = false;
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(objeto);
			entityManager.getTransaction().commit();
			isSucessoSalvar = true;
		} catch(Exception e){
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return isSucessoSalvar;
	}
	
	public static <T> List<T> buscarTodos(String tipo){
		return entityManager.createQuery("from " + tipo).getResultList();
	}
	
	 public Class<T> getPersistentClass()
	   {
	      if (persistentClass == null)
	      {
	         throw new RuntimeException(
	            "É necessário invocar o método setPersistentClass(Class<T> clazz)");
	      }
	      return persistentClass;
	   }
	
}
