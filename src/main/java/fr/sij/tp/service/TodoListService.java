package fr.sij.tp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sij.tp.entity.GenericEntity;
import fr.sij.tp.entity.TodoList;
import fr.sij.tp.repository.AbstractRepository;
import fr.sij.tp.repository.TodoListRepository;

@Service
public class TodoListService extends AbstractService<TodoList> {
	
	@Autowired TodoListRepository todoListRepository;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected AbstractRepository<GenericEntity> getRepo() {
		return (AbstractRepository)todoListRepository;
	}



}
