package fr.sij.tp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TodoListDto  extends GenericDto {

	public String color;
	public  String title;
	public  String owner;
	public  Date dueDate;

	public  List<TaskDto> tasks = new ArrayList<>();

	public int getIndex(int idTask) {
		for(int i=0;i<tasks.size();i++) {
			if(tasks.get(i).id==idTask) return i;
		}
		return -1;
	}
	
	public JsonNode toShortJsonNode() {
		ObjectNode result = JsonNodeFactory.instance.objectNode();
		result.put("id",id);
		result.put("color", color);
		result.put("owner", owner);
		result.put("title", title);
		result.put("dueDate", dueDate.getTime());// stocker la date en timestamp de java.util.Date = nb ms depuis 01/01/1900
		return result;
	}
	
	public JsonNode toJson() {
		ObjectNode result = JsonNodeFactory.instance.objectNode();
		result.put("id",id);
		result.put("color", color);
		result.put("owner", owner);
		result.put("title", title);
		result.put("dueDate", dueDate.getTime());// stocker la date en timestamp de java.util.Date = nb ms depuis 01/01/1900
		ArrayNode array = result.putArray("tasks");
		for(TaskDto t: this.tasks) {
			array.add(t.toJson());
		}
		return result;
	}
}
