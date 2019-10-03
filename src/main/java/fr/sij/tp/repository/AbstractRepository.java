package fr.sij.tp.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import fr.sij.tp.entity.GenericEntity;

public class AbstractRepository<T extends GenericEntity> extends SimpleJpaRepository<T, Integer> {
	
	private EntityManager em;
	Class<T> domainClass;

	public AbstractRepository(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
		this.domainClass = domainClass;
	}
	
	public T getById(int id) {
		return em.find(domainClass, id);
	}
	
	public void delete(int id) {
		T t = getById(id);
		em.remove(t);
	}
	
	public void delete(T entity) {
		em.remove(entity);
	}
	
	public void persist(T entity) {
		em.persist(entity);
	}
	public T merge(T entity) {
		return em.merge(entity);
	}
	
	public T saveEntity(T entity) {
		if(entity.id>0) return merge(entity);
		else {
			persist(entity);
			return entity;
		}
	}

}
