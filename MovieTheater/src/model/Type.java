package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Type {
	String name;
	Vector <Movie> movie;
	int weight=0;
	Type(String name)
	{
		this.name=name;
		weight=1;
		this.movie= new Vector<Movie>(1,2);
	}
	Type(String name,Movie movie){
		this(name);
		addNewMovie(movie);
	}
	Type(ResultSet res) throws SQLException
	{
		name=res.getString("type");
		weight=res.getInt("avg");
	}
	public void makeType(String name,int weight) {
		this.name=name;
		this.weight=weight;
	}
	public void addNewMovie(Movie movie) {
		this.movie.addElement(movie);
		weight++;
	}
	public void spreadWeights() {
		// We run on all of the movies in the vector and add to their weight this.weight;
	}
	public void updatedb(User user) {
		// we will backup this Type and its weight according to the user id, at the backdb
	}
}
