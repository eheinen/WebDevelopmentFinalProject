package com.fiap.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpacom");
	
	public static EntityManagerFactory getEmFactory(){
		return emf;
	}
}