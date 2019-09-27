package fr.sij.tp.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import fr.sij.tp.entity.TodoList;

@Repository
public class TodoListRepository extends SimpleJpaRepository<TodoList, Integer>{

	public TodoListRepository(EntityManager em) {
		super(TodoList.class, em);
	}

}
