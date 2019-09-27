package fr.sij.tp.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import fr.sij.tp.entity.GenericEntity;

public class AbstractRepository<T extends GenericEntity> extends SimpleJpaRepository<T, Integer> {

	public AbstractRepository(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}
	
	public T getById(int id) {
		Optional<T> opt = findById(id);
		return opt.isPresent()?opt.get():null;
	}

}
