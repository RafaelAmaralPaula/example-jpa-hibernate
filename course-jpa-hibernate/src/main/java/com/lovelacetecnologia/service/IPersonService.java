package com.lovelacetecnologia.service;

import java.util.List;

import com.lovelacetecnologia.model.Person;

public interface IPersonService {

	public void save(Person person);

	public void change(Person person);

	public void remove(Person person);

	public List<Person> findAll();

	public Person findById(Long id);

}
