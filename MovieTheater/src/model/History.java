package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class History {
	public Date date;// formallity we will cast to string no need to update this fielf
	public int movieId;
	public int rating;
	public Movie movie;
	
	//public int rating; // at the end!
	History(ResultSet res){
		  try {
			date = res.getDate("date");
			movieId =res.getInt("movieId");
			rating = res.getInt("rating");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  finally{
			  
		  }
	  }
}