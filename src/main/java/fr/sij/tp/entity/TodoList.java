package fr.sij.tp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="todolist")
public class TodoList extends GenericEntity{
	
	public String title;
	public String color;
	@Column(name="due_date")  public Date dueDate;
	
	@ManyToOne
	@JoinColumn(name="id_owner")
	public Usr owner;
//	@Column(name="id_owner")public int idOwner; // on utilise cet attribut pour éviter les problèmes de mapping
	
	@OneToMany(mappedBy="list")
	public List<Task> tasks = new ArrayList<>(); // ce mapping est facultatif voir génant (bidirectionnel)

}
