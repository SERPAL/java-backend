package main;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Book;
import model.Conversation;
import model.Message;
import model.Rating;
import model.Review;
import model.User;

public class DatabaseFiller {

	public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SerpalUnit");
		EntityManager entityManager = emfactory.createEntityManager();
		entityManager.getTransaction().begin();

		byte[] salt = new byte[32];
		new Random().nextBytes(salt);

		User user1 = new User("Usukhbayar Purevdorj", "Usukh", hash("123".toCharArray(), salt), salt,
				"Usukhbayar.Purevdorj@gmail.com");
		User user2 = new User("Mushvig", "Mushvig", hash("hey".toCharArray(), salt), salt, "mushvig@gmail.com");

		ArrayList<String> lines = new ArrayList<>();
		lines.add("111");
		lines.add("222");
		lines.add("333");

		Book book1 = new Book("The three musketeers", LocalDate.now(), "A.Dumas", lines);

		user1.setBooks(book1);
		book1.setUsers(user1);

		Review review1 = new Review(user1, "Nice book", book1, Rating.Good);

		Conversation conversation1 = new Conversation(user1, user2.getId());

		Message message1 = new Message(true, conversation1, "Hey!");
		Message message2 = new Message(false, conversation1, "howdy");
		Message message3 = new Message(true, conversation1, "Good, You?");
		Message message4 = new Message(false, conversation1, "Mighty fine. Thanks for asking");
		

		entityManager.persist(user1);
		entityManager.persist(user2);
		entityManager.persist(book1);
		entityManager.persist(review1);
		entityManager.persist(conversation1);
		entityManager.persist(message1);
		entityManager.persist(message2);
		entityManager.persist(message3);
		entityManager.persist(message4);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	private static byte[] hash(char[] password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
		KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		return f.generateSecret(spec).getEncoded();
	}

}
