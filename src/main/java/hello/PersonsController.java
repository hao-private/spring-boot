package hello;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonsController {
	@Autowired
	PersonRepository repo;
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Person> getPersons() {
		Collection<Person> persons = new ArrayList<Person>();
		repo.findAll().forEach(persons::add);
		return persons;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addKid(@RequestBody Person person) {
		repo.save(person);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
}
