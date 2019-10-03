package fr.sij.tp.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import fr.sij.tp.entity.GenericEntity;
import fr.sij.tp.repository.AbstractRepository;

public abstract class AbstractService <T extends GenericEntity>{
	
	protected abstract AbstractRepository<GenericEntity> getRepo();
	
	@SuppressWarnings("unchecked")
	public T getById(int id) {
		return (T) getRepo().getById(id);
	}

	public int create(T list) {
		T updatedList = getRepo().save(list);
		return updatedList.id;
	}

	@Transactional
	public void createMulti(ArrayList<T> arrayList) {
		for(T list: arrayList)getRepo().save(list);
	}

}
