package model;

import java.time.LocalDate;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-20T14:18:39.612+0800")
@StaticMetamodel(Message.class)
public class Message_ {
	public static volatile SingularAttribute<Message, Integer> id;
	public static volatile SingularAttribute<Message, Boolean> isUserWrote;
	public static volatile SingularAttribute<Message, LocalDate> date;
	public static volatile SingularAttribute<Message, String> content;
	public static volatile SingularAttribute<Message, Conversation> conversation;
}
