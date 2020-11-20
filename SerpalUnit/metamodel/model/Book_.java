package model;

import java.time.LocalDate;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value="Dali", date="2020-11-20T14:18:39.586+0800")
@StaticMetamodel(Book.class)
public class Book_ {
	public static volatile SingularAttribute<Book, Integer> id;
	public static volatile ListAttribute<Book, Review> reviews;
	public static volatile ListAttribute<Book, User> users;
	public static volatile SingularAttribute<Book, String> name;
	public static volatile SingularAttribute<Book, LocalDate> date;
	public static volatile SingularAttribute<Book, String> author;
}
