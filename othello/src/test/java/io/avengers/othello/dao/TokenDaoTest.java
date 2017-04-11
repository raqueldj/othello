package io.avengers.othello.dao;

import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import io.avengers.othello.IntegrationTest;
import io.avengers.othello.domain.Token;
import io.avengers.othello.jpa.EmFactory;

public class TokenDaoTest implements IntegrationTest {

	EntityManager em;
	TokenDao dao;

	Token t = new Token(null, true, 2, 5, null);
	Token t1 = new Token(null, true, 0, 0, null);
	Token t2 = new Token(null, false, 8, 0, null);

	@Before
	public void setUp() {
		em = EmFactory.createEntityManager();
		dao = new TokenDao(em);
		em.persist(t);
	}

	@After
	public void tearDown() {
		if (em.isOpen())
			em.close();
	}

	@AfterClass
	public static void close() {
		EmFactory.getInstance().close();
	}

	@Test
	public void create() {
		em.getTransaction().begin();

		dao.create(t1);
		assertTrue(t1.getId() > 0);

		dao.delete(t1);
		// assertEquals(dao.findById(t1.getId()));

		em.getTransaction().commit();
	}

	@Test
	public void update() {
		em.getTransaction().begin();

		em.persist(t2);

		if (!em.contains(t2)) {
			System.out.println("need to merge t2");
			t2 = em.merge(t2);
		}

		t2.setY(6);
		dao.update(t2);
		
		em.getTransaction().commit();
		
		em.getTransaction().begin();

		boolean found = dao.findById(t2.getY()).equals(6);
		
		em.getTransaction().commit();
	}
}
