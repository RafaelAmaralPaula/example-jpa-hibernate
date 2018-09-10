package com.lovelacetecnologia.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.lovelacetecnologia.model.Person;
import com.lovelacetecnologia.service.IPersonService;
import com.lovelacetecnologia.util.ConnectionoUtil;

public class PersonService implements IPersonService {

	
	@Override
	public void save(Person person) {
		EntityManager em = new ConnectionoUtil().getConnection();
		
		try {
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
	}

	@Override
	public void remove(Person person) {
		EntityManager em = new ConnectionoUtil().getConnection();
		
		try {

			Person personFound = em.find(Person.class, person.getId());

			em.getTransaction().begin();
			em.remove(personFound);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

	}

	@Override
	public void change(Person person) {
		EntityManager em = new ConnectionoUtil().getConnection();
		
		try {
			em.getTransaction().begin();
			if (person.getId().equals(null)) {
				em.persist(person);
			} else {
				em.merge(person);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
	}

	@Override
	public List<Person> findAll() {
		
		EntityManager em = new ConnectionoUtil().getConnection();
		List<Person> people = null;

		try {
			people = em.createQuery("from Person", Person.class).getResultList();
		} catch (Exception e) {
			System.out.println("Error ! " + e.getMessage());
		}finally {
			em.close();
		}

		return people;
	}

	@Override
	public Person findById(Long id) {
		
		EntityManager em = new ConnectionoUtil().getConnection();
		Person recoveredPeople = null;

		try {
			recoveredPeople = em.find(Person.class, id);
		} catch (Exception e) {
			System.out.println("Error ! " + e.getMessage());
		} finally {
			em.close();
		}

		return recoveredPeople;
	}

}
