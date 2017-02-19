package com.connected.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

public class PersonResource extends ResourceSupport {
	private Person person;
	
	public PersonResource(Person person) {
		this.person = person;
		this.add(linkTo(methodOn(PersonsController.class).getPerson(person.getId())).withSelfRel());
		this.add(linkTo(PersonsController.class).withRel("persons"));
	}
	
	public Person getPerson() {
		return person;
	}
}
