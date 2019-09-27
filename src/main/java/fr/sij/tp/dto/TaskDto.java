package fr.sij.tp.dto;

import fr.sij.tp.entity.Task;

public class TaskDto {
	
	public String content;
	public String status;
	public int id;
	

	public TaskDto() {
		super();
		
	}
	
	public TaskDto(Task t) {
		super();
		this.id = t.id;
		this.status = t.status;
		this.content = t.content;
	}
	

}
