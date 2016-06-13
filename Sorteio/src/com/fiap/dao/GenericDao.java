package com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fiap.entity.User;



public class GenericDao <E>{
	private Class<E> entityClass;

	public GenericDao(Class<E> entityClass){
		this.entityClass = entityClass;
	}

	public void persist(E entity) throws Exception{
		EntityManager em = JpaUtil.getEmFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		finally{
			if(em.isOpen()) em.close();
		}
	}

	public void remove(E entity) {
		EntityManager em = JpaUtil.getEmFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally{
			if(em.isOpen()) em.close();
		}
	}

	public void update(E entity){
		EntityManager em = JpaUtil.getEmFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}finally {
			if(em.isOpen()) em.close();
		}
	}

	public E findById(int id){
		EntityManager em = JpaUtil.getEmFactory().createEntityManager();
		E entity = em.find(entityClass, id);
		if(em.isOpen()) em.close();
		return entity;
	}
	
	public List<User> getAll(){
		EntityManager em = JpaUtil.getEmFactory().createEntityManager();
		Query query = em.createQuery("select t from User t", entityClass);
		
		@SuppressWarnings("unchecked")
		List<User> listResult = query.getResultList();
		
		if(em.isOpen()) em.close();
		return listResult;
	}

	@SuppressWarnings("unchecked")
	public E login(String cpf, String password){
		EntityManager em = JpaUtil.getEmFactory().createEntityManager();
		try {
			Query query = em.createQuery("select u from User u where cpf = :cpf and password = :password", entityClass);
			query.setParameter("cpf", cpf);
			query.setParameter("password", password);
			return (E) query.getSingleResult();
		} catch (Exception e) {
			throw e;
		}finally{
			if(em.isOpen()) em.close();
		}
		
	}
}
