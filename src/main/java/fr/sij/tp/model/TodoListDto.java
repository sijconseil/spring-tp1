package fr.sij.tp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TodoListDto {

	public int id;
	public String color;
	public  String title;
	public  String owner;
	public  Date dueDate;

	public  List<TaskDto> tasks = new ArrayList<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public int getIndex(int idTask) {
		for(int i=0;i<tasks.size();i++) {
			if(tasks.get(i).id==idTask) return i;
		}
		return -1;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TodoListDto other = (TodoListDto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public JsonNode toShortJsonNode() {
		ObjectNode result = JsonNodeFactory.instance.objectNode();
		result.put("id",id);
		result.put("color", color);
		result.put("owner", owner);
		result.put("title", title);
		result.put("dueDate", dueDate.getTime());
		return result;
	}
	
}
