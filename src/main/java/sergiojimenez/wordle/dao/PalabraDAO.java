package sergiojimenez.wordle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sergiojimenez.wordle.model.Palabra;

@Repository
public interface PalabraDAO extends JpaRepository<Palabra, String> {

	@Query(value = "SELECT * FROM wordle.palabra ORDER BY rand() LIMIT 1;", nativeQuery = true)
	public Palabra findRandom();

}
