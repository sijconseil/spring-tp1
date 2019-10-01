package fr.sij.tp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
	
	private TodoList parseTodoList(JsonNode node) {
		TodoList list = new TodoList();
		list.color = node.get("color").asText();
		String dateStr = node.get("dueDate").asText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
		try {
			list.dueDate = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(node.has("idOwner")) {
			int idOwner = node.get("idOwner").asInt();
			list.owner = usrService.getById(idOwner);
		}// else {			list.owner = null;		} // si absent, list.owner est dans tous les cas null
		list.title = node.get("title").asText();
		return list;
	}
	
	@PostMapping
	public ResponseEntity<Integer> createTodoList(@RequestBody ObjectNode body) {
		int id = todolistService.create(parseTodoList(body));
		return new ResponseEntity<Integer>(id,HttpStatus.CREATED);
	}
	
	@PostMapping("/multi")
	public ResponseEntity createMultipleTodoList(@RequestBody ArrayNode lists){
		ArrayList<TodoList> arrayList = new ArrayList<>();
		for(JsonNode list: lists) {
			arrayList.add(parseTodoList(list));
		}
		todolistService.createMulti(arrayList);
		return new ResponseEntity(HttpStatus.CREATED);
	
	}
	
	
}
