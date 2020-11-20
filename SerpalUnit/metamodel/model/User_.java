package model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-20T14:18:39.614+0800")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SetAttribute<User, Book> books;
	public static volatile ListAttribute<User, Conversation> conversation;
	public static volatile SingularAttribute<User, String> fullname;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, byte[]> password;
	public static volatile SingularAttribute<User, byte[]> salt;
	public static volatile SingularAttribute<User, String> email;
}
