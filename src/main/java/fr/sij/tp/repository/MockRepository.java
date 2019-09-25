package fr.sij.tp.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import fr.sij.tp.model.TaskDto;
import fr.sij.tp.model.TodoListDto;


@Component
public class MockRepository {

		public HashMap<Integer, TodoListDto> lists = new HashMap<>();
	
		public MockRepository() {
			TodoListDto list = new TodoListDto();
			list.id = 1;
			lists.put(list.id, list);
			list.title = " What to do ";
			list.dueDate = new Date(new Date().getTime()+ 24*1000*3600*5);
			list.owner = "Fabien J";
			
			TaskDto t = new TaskDto();
			t.id = 1001;
			list.tasks.add(t);
			t.parentList = list;
			t.content = "My first task in the list";
		}
		
		
		
		public void add(TodoListDto list) {
			lists.put(list.id, list);
		}
		
		public TodoListDto get(int id) {
			return lists.get(id);
		}
		
		public List<TodoListDto> getAll() {
			return new ArrayList<>(lists.values());
		}
		
		public boolean remove(int id) {
			if(lists.containsKey(id)) {
				lists.remove(id);
				return true;
			}
			return false;
		}
		
		public boolean remove(int idList, int idTask) {
			TodoListDto list = lists.get(idList);
			if(list==null)return false;
			
			int index=-1;
			for(int i=0;i<list.tasks.size();i++) {
				if(list.tasks.get(i).id==idTask) {
					index = i;
					break;
				}
			}
			list.tasks.remove(index);
			return index>=0;
			
		}
		
		
		
		
}
