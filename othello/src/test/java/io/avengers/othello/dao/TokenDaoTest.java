package io.avengers.othello.dao;

import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import io.avengers.othello.domain.Token;
import io.avengers.othello.jpa.EmFactory;

public class TokenDaoTest {

	EntityManager em;
	TokenDao dao;
	
	Token t = new Token(1, true, 2, 5, null);
	Token t1 = new Token(0, true, 0, 0, null);
	
	@Before
	public void setUp(){
		em = EmFactory.createEntityManager();
		dao = new TokenDao(em);
		em.persist(t);
	}
	
	@After
	public void tearDown(){
		if (em.isOpen())
			em.close();
	}
	
	@AfterClass
	public static void close() {
		EmFactory.getInstance().close();
	}
	
	@Test
	public void create(){
		em.getTransaction().begin();
		
		dao.create(t1);
		assertTrue(t1.getId()>0);
		
		dao.delete(t1);
		//assertEquals(dao.findById(t1.getId()));
		
		em.getTransaction().commit();
	}
}
