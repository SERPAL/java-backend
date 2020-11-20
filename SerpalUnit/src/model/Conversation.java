package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private User fromUser;

	private int toID;

	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
	private List<Message> messages = new ArrayList<>();

	public Conversation() {

	}

	public Conversation(User fromUser, int toID) {
		super();
		this.fromUser = fromUser;
		this.toID = toID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public int getToID() {
		return toID;
	}

	public void setToID(int toID) {
		this.toID = toID;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(Message message) {
		this.messages.add(message);
	}

}
