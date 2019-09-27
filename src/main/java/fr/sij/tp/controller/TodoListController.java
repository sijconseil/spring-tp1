package fr.sij.tp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.sij.tp.dto.TodoListDto;
import fr.sij.tp.entity.TodoList;
import fr.sij.tp.service.TodoListService;

@RestController
@RequestMapping("todolists")
public class TodoListController {
	
	@Autowired TodoListService todolistService;
	
	@GetMapping("/{id}")
	public ResponseEntity<TodoListDto> getById(@PathVariable int id){
		TodoList list = todolistService.getById(id);
		if(list==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new TodoListDto(list),HttpStatus.OK);
	}
	
}
