package br.com.cast.persistencia.generico;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

public class JPAGenericoDao<T extends Serializable> implements GenericoDao<T> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> persistentClass;

	public JPAGenericoDao(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public JPAGenericoDao(Class<T> persistentClass, EntityManager entityManager) {
		this(persistentClass);
		this.entityManager = entityManager;
	}

	protected Class<T> getPersistentClass() {
		if (this.persistentClass == null)
			throw new IllegalStateException("PersistentClass has not been set on DAO before usage");
		return this.persistentClass;
	}

	protected void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	protected EntityManager getEntityManager() {
		if (this.entityManager == null)
			throw new IllegalStateException("EntityManager has not been set on DAO before usage");
		return this.entityManager;
	}

	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected Query criarQuery(String JPQL, Object... parans) {
		Query query = this.getEntityManager().createQuery(JPQL);
		int i = 0;
		for (Object object : parans) {
			query.setParameter(++i, object);
		}
		return query;
	}

	public List<?> buscarPorJpql(String jpql, Object... parans) {
		return this.criarQuery(jpql, parans).getResultList();
	}

	@Override
	@Transactional
	public void salvar(T t) {
		this.getEntityManager().persist(t);
	}

	@Override
	@Transactional
	public void atualizar(T t) {
		this.getEntityManager().merge(t);
	}

	@Override
	@Transactional
	public void remover(Serializable id) {
		this.getEntityManager().remove(this.getEntityManager().getReference(getPersistentClass(), id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> buscarTodos() {
		return this.getEntityManager().createQuery("FROM " + getPersistentClass().getSimpleName()).getResultList();
	}
}