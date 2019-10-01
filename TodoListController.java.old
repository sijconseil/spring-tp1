//package fr.sij.tp.controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//
//import fr.sij.tp.entity.TaskDto;
//import fr.sij.tp.entity.TodoListDto;
//import fr.sij.tp.repository.MockRepository;
//
//@RestController
//@RequestMapping("todolists")
//public class TodoListController {
//	
//	@Autowired MockRepository repo;
//	
//	// localhost:8080/todolists
//	@GetMapping("")
//	public List<TodoListDto> getAllFull(){
//		return repo.getAll();
//	}
//	@GetMapping("full2")
//	public List<JsonNode> getAllFull2(){
//		ArrayList<JsonNode> result = new ArrayList<>();
//		for(TodoListDto todolist: repo.getAll()) {
//			result.add(todolist.toJson());
//		}
//		return result;
//	}
//	@GetMapping("/short1")
//	public String getAllShort1(){
//		ObjectMapper mapper = new ObjectMapper();
//		ArrayNode array = mapper.createArrayNode();
//		for(TodoListDto dto: repo.getAll()) {
//			array.add(dto.toShortJsonNode());
//		}
//		
//		try {
//			return mapper.writeValueAsString(array);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return null;
//		}
//	
//	}
//	
//	@GetMapping("/short2")
//	public JsonNode getAllShort2(){
//		ObjectMapper mapper = new ObjectMapper();
//		ArrayNode array = mapper.createArrayNode();
//		for(TodoListDto dto: repo.getAll()) {
//			array.add(dto.toShortJsonNode());
//		}
//		return array;
//	}
//	
//	
//	@GetMapping("/short3")
//	public List<JsonNode> getAllShort3(){
//		ArrayList<JsonNode> al = new ArrayList<>();
//		for(TodoListDto dto: repo.getAll()) {
//			al.add(dto.toShortJsonNode());
//		}
//		return al;
//	}
//	
//	
////	@GetMapping("/jackson")
////	public ResponseEntity<String> getObject() {
////		ObjectMapper mapper = new ObjectMapper();
////		
////		String jsonIn ="{ \"age\" : 12, \"nom\" : \"Patrick\"}";
////		try {
////			 // TaskDto dto = mapper.readValue(jsonIn, TaskDto.class); 
////			  JsonNode node = mapper.readTree(jsonIn);
////			  node.isArray();
////			  node.isObject();
////			  int age = node.get("age").asInt();
////			  String nom = node.get("nom").asText();
////			
////			
////				String result = mapper.writeValueAsString(new String("toto"));
////			return new ResponseEntity<String>(result, HttpStatus.OK);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return ResponseEntity.badRequest().build();
////		}
////		
////	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<JsonNode> get(@PathVariable int id) {
//		TodoListDto list = repo.get(id);
//		if(list==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		
//		return new ResponseEntity<JsonNode>(list.toJson(), HttpStatus.OK);
//		
//		
//	}
//
//	@PostMapping
//	public ResponseEntity<Integer> addList(@RequestBody TodoListDto list)  {
//		if(repo.get(list.id)!=null) return ResponseEntity.badRequest().build();
//		repo.add(list);
//		return new ResponseEntity<>(list.id, HttpStatus.CREATED);
//	}
//	
//	@SuppressWarnings("rawtypes")
//	@PutMapping("/{id}")
//	public ResponseEntity updateList(@RequestBody TodoListDto list, @PathVariable int id)  {
//		// on verifie la coherence de l'id dans le body et dans le paramètre
//		if(id!=list.id) return ResponseEntity.badRequest().build();
//		
//		// on recupère la liste et si elle est nulle elle n'est pas présente
//		// donc on leve une erreur car cela correspond à un post / create. 
//		TodoListDto current = repo.get(id);
//		if(current==null) return ResponseEntity.notFound().build();
//		
//		// on met à jour les champs de la liste plutot qu'écraser l'objet pour éviter de modifier les taches
//		current.color = list.color;
//		current.dueDate = list.dueDate;
//		current.owner = list.owner;
//		current.title = list.title;
//		return ResponseEntity.ok().build();
//		
//	}
//	
//	@PutMapping("/jackson/{id}")
//	public ResponseEntity updateListJackson1(@RequestBody JsonNode node, @PathVariable int id) {
//		
//		node.get("color").asText();
//		return null;
//	}
//
//	@PutMapping("/jackson2/{id}")
//	public ResponseEntity updateListJackson2(@RequestBody String json, @PathVariable int id) {
//		try {
//			TodoListDto dto = repo.get(id);
//			JsonNode node = new ObjectMapper().readTree(json);
//			dto.color = node.get("color").asText();
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
//
//	
//	@DeleteMapping("/{id}")
//	public void deleteList(@PathVariable int id) throws Exception {
//		TodoListDto current = repo.get(id);
//		if(current==null) throw new Exception("List not existing");
//		repo.remove(id);
//	}
//	
//	@GetMapping("/{idList}/tasks")
//	public ResponseEntity<List<TaskDto>> getTasks(@PathVariable int idList)  {
//		TodoListDto list = repo.get(idList);
//		if(list==null) return ResponseEntity.notFound().build();
//		return ResponseEntity.ok().body(list.tasks);
//	}
//	
//	@GetMapping("/{idList}/tasks/{idTask}")
//	public ResponseEntity<TaskDto> getTask(@PathVariable int idList, @PathVariable int idTask) {
//		TodoListDto list = repo.get(idList);
//		if(list==null) return ResponseEntity.notFound().build();
//		
//		int indexTask = list.getIndex(idTask);
//		if(indexTask<0) return ResponseEntity.notFound().build();
//			
//		return ResponseEntity.ok().body(list.tasks.get(indexTask));
//	}
//	
//	@PostMapping("/{idList}/tasks")
//	public void addTaskToList(@RequestBody TaskDto dto,  @PathVariable int idList)throws Exception {
//		TodoListDto list = repo.get(idList);
//		if(list==null) throw new Exception("List not existing");
//		
//		int indexTask = list.getIndex(dto.id);
//		if(indexTask>=0) throw new Exception("Task already existing");
//		
//		list.tasks.add(dto);
//	}
//	
//	@PutMapping("/{idList}/tasks/{idTask}")
//	public void updateTaskToList(@RequestBody TaskDto dto,  @PathVariable int idList, @PathVariable int idTask) throws Exception {
//		TodoListDto list = repo.get(idList);
//		if(list==null) throw new Exception("List not existing");
//		
//		if(idTask!=dto.id) throw new Exception ("Inconsistent task ids");
//		
//		int indexTask = list.getIndex(dto.id);
//		if(indexTask<0) throw new Exception("Task not existing");
//		
//		list.tasks.set(indexTask, dto);
//	}
//	
//	@DeleteMapping("/{idList}/tasks/{idTask}")
//	public ResponseEntity<String> deleteTask(@PathVariable int idList, @PathVariable int idTask)  {
//		TodoListDto list = repo.get(idList);
//		if(list==null) return new ResponseEntity<>("list not found",HttpStatus.NOT_FOUND);
//		
//		int indexTask = list.getIndex(idTask);
//		if(indexTask<0) return new ResponseEntity<>("Task not existing", HttpStatus.NOT_FOUND);
//		
//		list.tasks.remove(indexTask);
//		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
//	}
//	
//}
