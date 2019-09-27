package fr.sij.tp.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Task extends GenericEntity{
	
	public String content;
	public String status;
	
	@ManyToOne
	@JoinColumn(name="id_list")
	public TodoList list;

}
