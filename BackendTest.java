package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

public class BackendTest {

	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Serpal");
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

		assertTrue(backend.usernameIsTaken("a"));
		assertFalse(backend.usernameIsTaken("bbb"));

		try {
			assertTrue(backend.authUser("a", "123".toCharArray()));
			assertFalse(backend.authUser("a", "12".toCharArray()));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, backend.getUser("a").getId());
	}
}