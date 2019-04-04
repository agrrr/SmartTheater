package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Movie implements Comparable<Movie> {
	public int id;
	private String name;
	private String genre;
	private String directors;
	private int year;
	private String actors;
	private String review;
	private String trailer;
	private String picture;
	private int length;
	public int weight=0;
	public Vector <Screening> screenings;
	
	public Movie(int id, String name, String genre, String directors, int year, String actors, String review, String trailer, String picture, int length) {
		this.id=id;
		this.name = name;
		this.genre = genre;
		this.directors = directors;
		this.year = year;
		this.actors = actors;
		this.review = review;
		this.trailer = trailer;
		this.picture = picture;
		this.length = length;
		this.screenings= new Vector<Screening>(1,2);
	}
	 public Movie(ResultSet res)
	  {
		  try {
			id = res.getInt("id");
			name = res.getString("name");
			genre= res.getString("genre");
			directors= res.getString("directors");
			year = res.getInt("year");
			actors = res.getString("actors");
			review = res.getString("review");
			trailer = res.getString("trailer");
			picture = res.getString("picture");
			
			this.screenings= new Vector<Screening>(1,2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	 public int getId() {
		 return id;
	 }
	 public String toString() {
		 return name;
	 }

	public String getName() {
		return name;
	}
	public String getGenre() {
		return genre;
	}
	public String getDirectors() {
		return directors;
	}
	public int getYear() {
		return year;
	}
	public String getActors() {
		return actors;
	}
	public String getReview() {
		return review;
	}
	public String getTrailer() {
		return trailer;
	}
	public String getPicture() {
		return picture;
	}
	public int getLength() {
		return length;
	}
	@Override
	public int compareTo(Movie arg0) {
		if(this.weight > arg0.weight)
			return -1;
		else if(this.weight < arg0.weight)
			return 1;
		else
		return 0;
	}
}
