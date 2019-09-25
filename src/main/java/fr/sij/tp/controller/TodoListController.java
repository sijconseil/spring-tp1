package fr.sij.tp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.sij.tp.model.TaskDto;
import fr.sij.tp.model.TodoListDto;
import fr.sij.tp.repository.MockRepository;

@RestController
@RequestMapping("todolists")
public class TodoListController {
	
	@Autowired MockRepository repo;
	
	// localhost:8080/todolists
	@GetMapping
	public List<TodoListDto> getAll(){
		return repo.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TodoListDto> get(@PathVariable int id) {
		TodoListDto list = repo.get(id);
		if(list==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<TodoListDto>(list, HttpStatus.OK);
		
		
	}

	@PostMapping
	public void addList(@RequestBody TodoListDto list)  {
		if(repo.get(list.id)!=null) throw new Exception("List already created");
		repo.add(list);
	}
	
	@PutMapping("/{id}")
	public void updateList(@RequestBody TodoListDto list, @PathVariable int id) throws Exception {
		// on verifie la coherence de l'id dans le body et dans le paramètre
		if(id!=list.id) throw new Exception("Non consistent id ");
		
		// on recupère la liste et si elle est nulle elle n'est pas présente
		// donc on leve une erreur car cela correspond à un post / create. 
		TodoListDto current = repo.get(id);
		if(current==null) throw new Exception("List not existing");
		
		// on met à jour les champs de la liste plutot qu'écraser l'objet pour éviter de modifier les taches
		current.color = list.color;
		current.dueDate = list.dueDate;
		current.owner = list.owner;
		current.title = list.title;
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteList(@PathVariable int id) throws Exception {
		TodoListDto current = repo.get(id);
		if(current==null) throw new Exception("List not existing");
		repo.remove(id);
	}
	
	@GetMapping("/{idList}/tasks")
	public List<TaskDto> getTasks(@PathVariable int idList) throws Exception {
		TodoListDto list = repo.get(idList);
		if(list==null) throw new Exception("List not existing");
		return list.tasks;
	}
	
	@GetMapping("/{idList}/tasks/{idTask}")
	public ResponseEntity<TaskDto> getTask(@PathVariable int idList, @PathVariable int idTask) {
		TodoListDto list = repo.get(idList);
		if(list==null) return ResponseEntity.notFound().build();
		
		int indexTask = list.getIndex(idTask);
		if(indexTask<0) return ResponseEntity.notFound().build();
			
		return ResponseEntity.ok().body(list.tasks.get(indexTask));
	}
	
	@PostMapping("/{idList}/tasks")
	public void addTaskToList(@RequestBody TaskDto dto,  @PathVariable int idList)throws Exception {
		TodoListDto list = repo.get(idList);
		if(list==null) throw new Exception("List not existing");
		
		int indexTask = list.getIndex(dto.id);
		if(indexTask>=0) throw new Exception("Task already existing");
		
		list.tasks.add(dto);
	}
	
	@PutMapping("/{idList}/tasks/{idTask}")
	public void updateTaskToList(@RequestBody TaskDto dto,  @PathVariable int idList, @PathVariable int idTask) throws Exception {
		TodoListDto list = repo.get(idList);
		if(list==null) throw new Exception("List not existing");
		
		if(idTask!=dto.id) throw new Exception ("Inconsistent task ids");
		
		int indexTask = list.getIndex(dto.id);
		if(indexTask<0) throw new Exception("Task not existing");
		
		list.tasks.set(indexTask, dto);
	}
	
	@DeleteMapping("/{idList}/tasks/{idTask}")
	public ResponseEntity<String> deleteTask(@PathVariable int idList, @PathVariable int idTask)  {
		TodoListDto list = repo.get(idList);
		if(list==null) return new ResponseEntity<>("list not found",HttpStatus.NOT_FOUND);
		
		int indexTask = list.getIndex(idTask);
		if(indexTask<0) return new ResponseEntity<>("Task not existing", HttpStatus.NOT_FOUND);
		
		list.tasks.remove(indexTask);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
}
