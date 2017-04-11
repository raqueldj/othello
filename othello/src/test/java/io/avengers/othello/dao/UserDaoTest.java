package io.avengers.othello.dao;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import io.avengers.othello.IntegrationTest;
import io.avengers.othello.domain.User;
import io.avengers.othello.jpa.EmFactory;

public class UserDaoTest implements IntegrationTest {
	
	User user = new User("bartac", "pass", 5, 5);
	User user2 = new User("clementine");
	User user3 = new User("bartac", "pass", 5, 5);
	User user4 = new User("bartac", "pass", 5, 5);

	EntityManager em;
	UserDao dao;

	@Before
	public void setUp() {
		em = EmFactory.createEntityManager();
		dao = new UserDao(em);
	}

	@After
	public void tearDown() {
		if (em.isOpen()) {
			em.close();
		}
	}

	@AfterClass
	public static void close() {
		EmFactory.getInstance().close();
	}

	@Test
	public void create() {

		em.getTransaction().begin();
		dao.create(user);
		dao.create(user3);
		dao.create(user4);
		

		assertTrue(user.getId() > 0);
		
		//dao.delete(user.getId());
		
		em.getTransaction().commit();
	}
	
	@Test
	public void update(){
		em.getTransaction().begin();
		em.persist(user);
		
		dao.update(user.getId(), user2);
		em.getTransaction().commit();
		
		assertTrue(user.getName().equals("clementine"));
	}

}
