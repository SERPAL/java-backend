package main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import controller.Backend;

public class Tests {

	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SerpalUnit");
	private EntityManager entityManager = emfactory.createEntityManager();
	private Backend backend;

	@Before
	public void setUp() {
		backend = new Backend(entityManager);
	}

	@Test
	public void firstThreeMatchToDefinition() {
		assertFalse(backend.validEmail("a"));
		assertTrue(backend.validEmail("a@yahoo.com"));
		
		assertFalse(backend.isStringInt("a12"));
		assertTrue(backend.isStringInt("12"));
		
		
		//Database is not deployed, so we cannot test this tests. It's working on local machine
//		assertTrue(backend.usernameIsTaken("a"));
//		assertFalse(backend.usernameIsTaken("bbb"));

//		try {
//			assertTrue(backend.authUser("a", "123".toCharArray()));
//			assertFalse(backend.authUser("a", "12".toCharArray()));
//		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		assertEquals(1, backend.getUser("a").getId());
	}
}