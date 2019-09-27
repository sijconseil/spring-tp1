package fr.sij.tp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.sij.tp.dto.TodoListDto;
import fr.sij.tp.entity.TodoList;
import fr.sij.tp.service.TodoListService;
import fr.sij.tp.service.UsrService;

@RestController
@RequestMapping("todolists")
public class TodoListController {
	
	@Autowired TodoListService todolistService;
	@Autowired UsrService usrService;
	
	@GetMapping("/{id}")
	public ResponseEntity<TodoListDto> getById(@PathVariable int id){
		TodoList list = todolistService.getById(id);
		if(list==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new TodoListDto(list),HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<Integer> createTodoList(@RequestBody ObjectNode body) {
		TodoList list = new TodoList();
		list.color = body.get("color").asText();
		String dateStr = body.get("dueDate").asText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
		try {
			list.dueDate = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int idOwner = body.get("idOwner").asInt();
		list.owner = usrService.getById(idOwner);
		//list.owner = 
		list.title = body.get("title").asText();
		int id = todolistService.create(list);
		return new ResponseEntity<Integer>(id,HttpStatus.CREATED);
	}
	
	
}
