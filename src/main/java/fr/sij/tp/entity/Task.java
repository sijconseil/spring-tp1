package fr.sij.tp.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task extends GenericEntity{
	
	public String content;
	public String status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_list")
	public TodoList list;
	
//	@Column(name="id_list") public int idList; // on utilise l'attribut FK au lieu du mapping objet ci-dessus

}
