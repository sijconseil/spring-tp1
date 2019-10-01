package fr.sij.tp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sij.tp.entity.Usr;
import fr.sij.tp.repository.UsrRepository;

@Service
public class UsrService {
	
	@Autowired UsrRepository repo;
	
	public Usr getById(int id) {
		return repo.getOne(id);
	}

}
