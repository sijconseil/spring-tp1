package fr.sij.tp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.sij.tp.entity.Usr;
import fr.sij.tp.repository.UsrRepository;

@RestController
@RequestMapping("/users")
public class UsrController {

	@Autowired UsrRepository repository;
	
	@GetMapping("/prefix/{prefix}")
	public List<Usr> searchUserByPrefix(@PathVariable String prefix) {
		return repository.findUserByPrefix(prefix);
		
		
	}
	
}
