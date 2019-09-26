package fr.sij.tp.model;

import java.util.Date;

public class TodoListSummaryDto extends GenericDto  {
	public String color;
	public  String title;
	public  String owner;
	public  Date dueDate;
	
	public TodoListSummaryDto(TodoListDto dto) {
		this.id = dto.id;
		this.color = dto.color;
		this.title = dto.title;
		this.owner = dto.owner;
		this.dueDate = dto.dueDate;
	}

}
