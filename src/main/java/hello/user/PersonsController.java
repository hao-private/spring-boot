package hello.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Collection<Person> getPersons() {
		Collection<Person> persons = new ArrayList<Person>();
		repo.findAll().forEach(persons::add);
		return persons;
	}
	
	@RequestMapping(method = RequestMethod.POST)
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addKid(@RequestBody Person person) {
		repo.save(person);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
}
