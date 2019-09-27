package fr.sij.tp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sij.tp.entity.TodoList;
import fr.sij.tp.repository.TodoListRepository;

@Service
public class TodoListService {

	@Autowired TodoListRepository repo;
	
//	public void foo(int id) {
//		// solution 1 à préférer avec les optional
//		Optional<TodoList> opt = repo.findById(id);
//		if(opt.isPresent()) {
//			// retourer l'objet au contraire, faire le traitement du service
//			TodoList list = opt.get();
//		}else {
//			// equivalent à findById return null;
//		}
//		
//		// solution 2 si on est certain que l'entité existe ou doit exister pour l'id donné
//		TodoList list = repo.getOne(id);
//		if(list==null) {
//			// normalement le getOne leve une exception s'il ne trouve pas l'id
//		}else {
//			
//		}
//	}

	public TodoList getById(int id) {
		Optional<TodoList> opt = repo.findById(id);
		return opt.isPresent()?opt.get():null;
		
		/*if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}*/
	}
}
