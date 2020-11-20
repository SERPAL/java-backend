package model;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value="Dali", date="2020-11-20T14:18:39.612+0800")
@StaticMetamodel(Conversation.class)
public class Conversation_ {
	public static volatile SingularAttribute<Conversation, Integer> id;
	public static volatile SingularAttribute<Conversation, User> fromUser;
	public static volatile SingularAttribute<Conversation, Integer> toID;
	public static volatile ListAttribute<Conversation, Message> messages;
}
