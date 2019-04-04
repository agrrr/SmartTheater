package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import model.Movie;
import model.Screening;
import model.User;
public class Database {
	private static String url = "jdbc:mysql://localhost/theaterdb";
    private static String user = "root";
    private static String password =""; 
	private static Connection con;
	private static ResultSet res;
	private static Statement stt;
	private static PreparedStatement pstt;
	static {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			con = DriverManager.getConnection(url, user, password);
			stt = con.createStatement();
		} catch(Exception e) {}
	}
	public static boolean isValid(String us, String psw) {
		try {
			res = stt.executeQuery("SELECT * FROM User");
			while(res.next()) {
				if((us.equals(res.getString("userName"))) && (psw.equals(res.getString("password")))){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static int addUser(User u) {
		try {
			res = stt.executeQuery("SELECT * FROM User");
			while(res.next()) {
				if((u.getUserName().equals(res.getString("userName"))) )
					return 1;
			}
			pstt = (PreparedStatement) con.prepareStatement("INSERT INTO theaterdb.user (userName,password,firstName,lastName,email,phoneNumber,birthday) VALUES (?,?,?,?,?,?,?)");	
			pstt.setString(1,u.getUserName());
			pstt.setString(2,u.getPassword());
			pstt.setString(3,u.getfirstName());
			pstt.setString(4,u.getlastName());
			pstt.setString(5,u.getEmail());
			pstt.setString(6,u.getPhone());
			pstt.setString(7,u.getBirthday());
			pstt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static int addNewMovie(Movie m) {
		try {
			res = stt.executeQuery("SELECT * FROM movie");
			while(res.next()) {
				if((m.getName().equals(res.getString("name"))) )
					return 1;
			}
			pstt = (PreparedStatement) con.prepareStatement("INSERT INTO theaterdb.movie (name,genre,directors,year,actors,review,trailer,picture,length) VALUES (?,?,?,?,?,?,?,?,?)");	
			pstt.setString(1,m.getName());
			pstt.setString(2,m.getGenre());
			pstt.setString(3,m.getDirectors());
			pstt.setInt(4,m.getYear());
			pstt.setString(5,m.getActors());
			pstt.setString(6,m.getReview());
			pstt.setString(7,m.getTrailer());
			pstt.setString(8,m.getPicture());
			pstt.setInt(9,m.getLength());
			pstt.executeUpdate();
			res=stt.executeQuery("SELECT * FROM movie WHERE name='"+m.getName()+"'");
		    res.next();
			m.id=res.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/*public static int numOfMovies() {
		int counter = 0;
		try {
			res = stt.executeQuery("SELECT * FROM movie");
			while (res.next()) {
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}*/
	
	public static User setUser(String userName){
		User u = null;
		try {
			res = stt.executeQuery("SELECT * FROM User");
			res.next();
			while(!userName.equals(res.getString("userName"))) {
				res.next();
			}
			int id = res.getInt("id");
			String us = res.getString("userName");
			String pw = res.getString("password");
			String fn = res.getString("firstName");
			String ln = res.getString("lastName");
			String email = res.getString("email");
			String bday = res.getString("birthday");
			String phone = res.getString("phoneNumber");
			u = new User(id,us,pw,fn,ln,email,bday,phone);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	/*public static Movie setMovie(int id){
		Movie m = null;
		try {
			res = stt.executeQuery("SELECT * FROM movie");
			res.next();
			while(id!=(res.getInt("id"))) {
				res.next();
			}
			String name = res.getString("name");
			String gen = res.getString("genre");
			String drc = res.getString("directors");
			int year = res.getInt("year");
			String act = res.getString("actors");
			String rvw = res.getString("review");
			String trl = res.getString("trailer");
			String pic = res.getString("picture");
			int len = res.getInt("length");
			m = new Movie(id,name,gen,drc,year,act,rvw,trl,pic,len);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}*/
	
	public static void updateInfo(String un, String newPsw) {
		try {
			res = stt.executeQuery("SELECT * FROM User");
			stt.execute("UPDATE user SET password = '"+newPsw+"' WHERE userName = '"+un+"'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*public static Movie[] getRecent() {
		Movie recent[] = new Movie[6];
		int n = numOfMovies();
		int j = 0;
		try {
			res = stt.executeQuery("SELECT * FROM movie");
			for (int i=n;i>(n-6);i--) {
				recent[j]=setMovie(i);
				j++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recent;
	}*/

	public static Movie getMovie(int id) {
		ResultSet re=null;
		try {
			re= stt.executeQuery("SELECT* FROM movie WHERE id='"+id+"'");
			re.next();
			return new Movie(re);
		}
	catch (SQLException e) {
		e.printStackTrace();
		return null;
		} 
	}
 	public static ResultSet getMoviedb() {
		ResultSet res=null;
		//******* executing the query******
		try {
			res= stt.executeQuery("SELECT* FROM movie");
			return res;
		}
	catch (SQLException e) {
		e.printStackTrace();
		return res=null;
		} 
	}
	public static ResultSet getScreeningdb() {
		ResultSet res=null;
		//******* executing the query******
		try {
			res= stt.executeQuery("SELECT * FROM screening ");
			return res;
		}
	catch (SQLException e) {
		e.printStackTrace();
		return res=null;
		} 
	}
	public static ResultSet getHistorydb(int id) {
		ResultSet res=null;
		//******* executing the query******
		try {
			res= stt.executeQuery("SELECT * FROM ticket WHERE userId='"+id+"'");
			return res;
		}
	catch (SQLException e) {
		e.printStackTrace();
		return res=null;
		} 	
	}
	public static int addNewScreen(Screening s) {
		try {
			res = stt.executeQuery("SELECT * FROM screening");
			while(res.next()) {
				
				if((s.date.equals(res.getString("date"))) && (s.time.equals(res.getString("time"))))
					return 1;
			}
			pstt = (PreparedStatement) con.prepareStatement("INSERT INTO theaterdb.screening (date,time,movieId) VALUES (?,?,?)");	
			pstt.setString(1,s.date);
			pstt.setString(2,s.time);
			pstt.setInt(3,s.movie.id);
			pstt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;		
	}
	public static ResultSet getType() 
	{
		ResultSet res=null;
		//******* executing the query******
		try {
			res= stt.executeQuery("SELECT * FROM weights ");
			return res;
		}
	catch (SQLException e) {
		e.printStackTrace();
		return res=null;
		} 	
	}
	public static void addTicket(int uId,int mId,int sId) {
		try {
			pstt = (PreparedStatement) con.prepareStatement("INSERT INTO theaterdb.ticket (movieId,userId,screeningId) VALUES (?,?,?)");	
			pstt.setInt(1,mId);
			pstt.setInt(2,uId);
			pstt.setInt(3,sId);
			pstt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

