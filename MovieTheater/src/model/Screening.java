package model;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Screening implements Comparable<Screening> {
	public int movieId;
	public int id;
	public Movie movie;
	public String date;
	public String time;
	public int sold=0;
	public int allTickets=100;
	
	public Screening(Movie movie,String date,String time){
		this.movie=movie;
		this.date=date;
		this.time = time;
		// we need to check that the date is ok, no overlap..
		// update the screening database..
		this.movie.screenings.addElement(this);
	}
	Screening(ResultSet res){
		try {
			id=res.getInt("id");
			date= res.getString("date");
			time= res.getString("time");
			sold = res.getInt("sold");
		movieId =res.getInt("movieId");	
	} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	public int getId() {return id;}
	public void sellTicket() {
		// we will add ticket at the ticket db
		// the history will be updated at the next sight in;
		sold++;
	}
	@Override
	public int compareTo(Screening o) {
		// TODO Auto-generated method stub
		return this.movie.compareTo(o.movie);
	}
	public String toString() {
	return (date+" || "+time+" || "+movie) ;
	}

}
