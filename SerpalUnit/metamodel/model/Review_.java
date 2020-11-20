package model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-20T14:18:39.613+0800")
@StaticMetamodel(Review.class)
public class Review_ {
	public static volatile SingularAttribute<Review, Integer> id;
	public static volatile SingularAttribute<Review, String> comment;
	public static volatile SingularAttribute<Review, Book> book;
	public static volatile SingularAttribute<Review, Rating> rating;
}
