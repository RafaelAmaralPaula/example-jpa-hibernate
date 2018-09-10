package com.lovelacetecnologia.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionoUtil {

	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
	
	
	public EntityManager getConnection() {
		return emf.createEntityManager();
	}
	
	
}
