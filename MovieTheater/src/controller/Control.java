package controller;
import database.Database;
import model.Movie;
import model.Screening;
import model.User;

public class Control {
	public static boolean isValid(String un, String psw) {
		if(Database.isValid(un, psw)) {
			return true;
		}
		else
			return false;
	}
	public static int addNewuser(String un, String psw,String fn,String ln,String email, String bday,String phone) {
		User u = new User(0,un,psw,fn,ln,email,bday,phone);
		if (Database.addUser(u) == 1)  //User already exists
			return 1;
		else //User added successfully
			return 0;	
	}
	public static int addNewMovie(User u, String mn, String gnr,String drc, int year,String act,String rvw, String trl, String pic, int len) {
		Movie m = new Movie(0,mn,gnr,drc,year,act,rvw,trl,pic,len);
		if (Database.addNewMovie(m) == 1)  //Movie already exists
			return 1;
		else //Movie added successfully
			u.movie.addElement(m);
			return 0;
	}
	public static User setUser(String userName){
		User u = Database.setUser(userName);
		return u;	
	}
	public static void updateInfo(String userName, String newPsw) {
		Database.updateInfo(userName, newPsw);
	}
	/*public static int numOfMovies() {
		return Database.numOfMovies();
	}
	public static Movie[] getRecent() {
		Movie recent[] = Database.getRecent();
		return recent;
	}*/
	public static int addNewScreen(Movie m, String date,String time,User u) {
		Screening s = new Screening(m,date,time);
		if (Database.addNewScreen(s) == 1)  //screening already exists
			return 1;
		else //screening added successfully
			u.screening.addElement(s);
			return 0;
	}
	public static void purchase(int uId,int mId,int sId) {
		Database.addTicket(uId, mId, sId);	
	}
}
