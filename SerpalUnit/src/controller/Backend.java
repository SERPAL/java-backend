package controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.User;
import model.User_;

public class Backend {

	private final EntityManager em;
	private final CriteriaBuilder cb;
	private final String NUMBER_REGEX = "-?\\d+(\\.\\d+)?";
	private final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public Backend(EntityManager em) {
		this.em = em;
		this.cb = em.getCriteriaBuilder();
	}

	private byte[] hashPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		return f.generateSecret(spec).getEncoded();
	}

	private boolean constantTimeEquals(byte[] a, byte[] b) {
		int result = 0;
		if (a.length != b.length) {
			System.out.println(a.length + " " + b.length + "s");
			return false;
		}
		for (int i = 0; i < a.length; i++) {
			result |= a[i] ^ b[i];
		}
		return result == 0;
	}

	public boolean authUser(String username, char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		CriteriaQuery<User> userQuery = cb.createQuery(User.class);
		Root<User> user = userQuery.from(User.class);
		userQuery.where(cb.equal(user.get(User_.username), username));
		userQuery.select(user);

		List<User> us = em.createQuery(userQuery).getResultList();
		if (us.size() == 0) {
			return false;
		}
		User u = us.get(0);
		byte[] hash = hashPassword(password, u.getSalt());
		if (!constantTimeEquals(hash, u.getPassword())) {
			return false;
		}
		return true;
	}

	public boolean usernameIsTaken(String username) {
		CriteriaQuery<User> userQuery = cb.createQuery(User.class);
		Root<User> user = userQuery.from(User.class);
		userQuery.where(cb.equal(user.get(User_.username), username));
		userQuery.select(user);
		List<User> us = em.createQuery(userQuery).getResultList();
		if (us.size() == 0) {
			return false;
		}
		return true;
	}

	public User getUser(String username) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> userQuery = cb.createQuery(User.class);
		Root<User> user = userQuery.from(User.class);
		userQuery.where(cb.equal(user.get(User_.username), username));
		userQuery.select(user);
		return em.createQuery(userQuery).getSingleResult();
	}

	public User registerUser(String fullName, String username, String email, char[] password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = new byte[32];
		new Random().nextBytes(salt);
		User user = null;
		if (!usernameIsTaken(username) && validEmail(email)) {
			user = new User(fullName, username, hashPassword(password, salt), salt, email);
		}
		return user;
	}

	public boolean validEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public boolean isStringInt(String s) {
		return s.matches(NUMBER_REGEX);
	}

}