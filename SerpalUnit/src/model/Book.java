package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();

	@ManyToMany()
	private List<User> users = new ArrayList<>();

	private String name;
	private LocalDate date;
	private String author;
	private List<String> linesOfDescription = new ArrayList<>();

	public Book() {

	}

	public Book(String name, LocalDate date, String author, List<String> linesOfDescription) {
		super();
		this.name = name;
		this.date = date;
		this.author = author;
		this.linesOfDescription = linesOfDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Review review) {
		this.reviews.add(review);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(User user) {
		this.users.add(user);
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<String> getLinesOfDescription() {
		return linesOfDescription;
	}

	public void setLinesOfDescription(List<String> linesOfDescription) {
		this.linesOfDescription = linesOfDescription;
	}

}
