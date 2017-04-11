package io.avengers.othello.dao;

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

		//assertTrue(user.getId() > 0);
		
		em.getTransaction().commit();
	}

}
