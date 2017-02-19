package com.connected.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/persons")
public class PersonsController {
	@Autowired
	PersonRepository repo;
	
	@RequestMapping(method = RequestMethod.GET)
	public Resources<PersonResource> getPersons() {
		List<PersonResource> persons = new ArrayList<PersonResource>();
		repo.findAll().forEach(p -> {
						persons.add(new PersonResource(p));
		});
		return new Resources<>(persons);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addKid(@RequestBody Person person) {
		Person newPerson = repo.save(person);
		return ResponseEntity.created(
					ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(newPerson.getId()).toUri())
				.build();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public PersonResource getPerson(@PathVariable Long id) {
		Person p = repo.findOne(id);
		return new PersonResource(p);
	}
}
