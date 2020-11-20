package model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean isUserWrote;
	private LocalDate date;
	private String content;

	@ManyToOne
	private Conversation conversation;

	public Message() {

	}

	public Message(boolean isUserWrote, Conversation conversation, String content) {
		super();
		this.isUserWrote = isUserWrote;
		this.date = LocalDate.now();
		this.conversation = conversation;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isUserWrote() {
		return isUserWrote;
	}

	public void setUserWrote(boolean isUserWrote) {
		this.isUserWrote = isUserWrote;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
