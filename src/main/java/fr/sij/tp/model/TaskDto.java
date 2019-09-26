package fr.sij.tp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TaskDto extends GenericDto {
	
	public String status;
	public String content;
	@JsonIgnore public TodoListDto parentList;
	

	public JsonNode toJson() {
		ObjectNode result = JsonNodeFactory.instance.objectNode();
		result.put("id",id);
		result.put("status", status);
		result.put("content", content);
		return result;
	}
	
}
