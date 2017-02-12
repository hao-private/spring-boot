package hello;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<Person, Long>{
	Person findByName(String name);
}
