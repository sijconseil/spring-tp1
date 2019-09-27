package fr.sij.tp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sij.tp.entity.TodoList;
import fr.sij.tp.repository.TodoListRepository;

@Service
public class TodoListService {

	@Autowired TodoListRepository repo;
	
	public TodoList getById(int id) {
		return repo.getById(id);
	}

	public int create(TodoList list) {
		TodoList updatedList = repo.save(list);
		return updatedList.id;
	}
}
