package com.lovelacetecnologia.person_service_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.lovelacetecnologia.model.Person;
import com.lovelacetecnologia.service.IPersonService;
import com.lovelacetecnologia.service.impl.PersonService;

public class PersonServiceTest {

	private IPersonService service;

	@Before
	public void init() {
		service = new PersonService();
	}

	@Test
	public void should_Allow_People_ToBe_Added_To_The_DataBase() {

		int before  = service.findAll().size();

		Person person = new Person();
		person.setName("Filipe Bastos");
		person.setEmail("");

		service.save(person);

		int after = service.findAll().size();

		assertEquals(before + 1, after);

	}
	
	@Test
	public void should_Allow_People_ToBe_Remove_To_The_DataBase() {


		Person person = new Person();
		person.setName("Filipe Bastos");
		person.setEmail("");
		service.save(person);
		
		int before = service.findAll().size();
		service.remove(person);
		int after = service.findAll().size();
		
		
		assertEquals(before - 1, after);

	}
	
	

	@Test
	public void should_Allow_Name_Of_People_ToBe_Change_In_DataBase() {

		String nameOne = "filipe";
		String nameTwo = "rafael";

		Person person = new Person();
		person.setName(nameOne);
		person.setEmail("filipe@filipebastos");

		service.save(person);

		assertNotNull(person.getId());
		assertTrue(person.getId().longValue() > 0);

		Person foundOne = service.findById(person.getId());

		assertEquals(foundOne.getName(), person.getName());

		foundOne.setName(nameTwo);
		foundOne.setEmail("contato@renato");

		service.change(foundOne);

		Person foundTwo = service.findById(person.getId());

		assertEquals(foundTwo.getName(), nameTwo);
		assertNotEquals(foundTwo.getName(), nameOne);

	}
	
	@Test
	public void should_Allow_Email_Of_People_ToBe_Change_In_DataBase() {

		String emailOne = "filipe@contatoig.com";
		String emailTwo = "rafael@contatohotmail.com";

		Person person = new Person();
		person.setName("Teste02");
		person.setEmail(emailOne);

		service.save(person);

		assertNotNull(person.getId());
		assertTrue(person.getId().longValue() > 0);

		Person found = service.findById(person.getId());

		assertEquals(found.getEmail(), person.getEmail());

		found.setName("Teste03");
		found.setEmail(emailTwo);

		service.change(found);

		Person foundTwo = service.findById(person.getId());

		assertEquals(foundTwo.getEmail(), emailTwo);
		assertNotEquals(foundTwo.getName(), emailOne);

	}
	

	@Test
	public void should_Allow_People_ToBe_Searched_By_Id() {
		Person person = new Person();
		person.setEmail("filipe@filipebastos");
		person.setName("Filipe Bastos");

		service.save(person);
		
	    assertNotNull(service.findById(person.getId()).getId());

	}
}
