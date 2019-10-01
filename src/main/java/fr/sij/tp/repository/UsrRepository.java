package fr.sij.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.sij.tp.entity.Usr;

public interface UsrRepository extends JpaRepository<Usr, Integer>{

	@Query("select u from Usr u where u.login like :prefix% ") // en sql where login like 'xxxxxx%'
	// utilisation d'un paramètre nommé. 
	public List<Usr> findUserByPrefix(@Param("prefix") String prefix) ;

	@Query("select u from Usr u where u.login like ?1% ") // utilisation de la position du paramètre dans les arguments
	// de la méthode
	public List<Usr> findUserByPrefix2(String prefix) ;

}
