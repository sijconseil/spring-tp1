package fr.sij.tp.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import fr.sij.tp.entity.Task;
import fr.sij.tp.entity.TodoList;

public class TodoListDto {
	
	public String title;
	public String color;
	public Date dueDate;
	public int id;
	public int idOwner;
	public String username;
	public List<TaskDto> taskIds = new ArrayList<>();
	

	public TodoListDto() {
		super();
		
	}
	
	public TodoListDto(TodoList list) {
		super();
		this.title = list.title;
		this.color = list.color;
		this.dueDate = list.dueDate;
		this.id = list.id;
		this.idOwner = list.owner.id;
		this.username = list.owner.firstname+" "+ list.owner.lastname;
		for(Task task : list.tasks)taskIds.add(new TaskDto(task));
	}
	

}
