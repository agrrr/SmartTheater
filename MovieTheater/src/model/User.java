package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Vector;

import database.Database;

public class User {
	int id;
	public String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String birthday;
	private String phone;
	
	public Vector <Movie> movie;
	public Vector <Movie> movieAfterAlgo;
	public Vector <History> history;
	public Vector <Screening> screening;
	public Vector <Screening> screeningAfterAlgo;
	public Vector <Type> type;
	
	public User() {}
	public User(int id, String un, String psw,String fn,String ln,String mail, String bday,String p) {
		this.id = id;
		userName = un;
		password = psw;
		firstName =fn;
		lastName = ln;
		email = mail;
		birthday = bday;
		phone = p;
	}
	
	@SuppressWarnings("unchecked")
	public void creatLocaldb() {
		ResultSet res;
		Screening s;
		movie = new Vector<Movie>(1,4);
		screening = new Vector<Screening>(1,4);
	//**************************************************************
		res= Database.getMoviedb();/// building local movies db
		try {
		while(res.next()) {
			movie.addElement (new Movie(res));
		}
	//******************creating Screening local db********
		res = Database.getScreeningdb();
		while(res.next()) {
			s=new Screening(res);
			screening.addElement(s);
			s.movie=movie.elementAt(s.movieId-1);//connectivity
			s.movie.screenings.addElement(s);
		}
		screeningAfterAlgo=(Vector<Screening>) screening.clone();
		movieAfterAlgo = (Vector<Movie>) movie.clone();
	}
		catch (SQLException e) {
			e.printStackTrace();
			}
	}
	public void creatPersonaldb() {
		ResultSet res;
		History h;
		history = new Vector<History>(1,3);
		type = new Vector<Type>(1,2);
		try {
	//*********** creating historydb**********
		res = Database.getHistorydb(id);
		while(res.next()) {
			h=new History(res);
			history.addElement(h);
			h.movie=movie.elementAt(h.movieId-1);//connectivity
		}
		// after connecting we want to remove history movies from moviedb
	//************Creating Types*************
		int i=0;
		int j=0;
		int flag=0;
		Movie m;
		while(i<history.size()) {
			m=history.elementAt(i).movie;
			movieAfterAlgo.removeElement(m);
			movieAfterAlgo.removeElement(m);
			screeningAfterAlgo.removeElement(m);
			// Connecting History to Types...making weights
			while(j<type.size()&& (flag!=3)) {
				if((flag!=1)&&(type.elementAt(j).name==m.getGenre())) {
					type.elementAt(j).weight++;
					//type.elementAt(j).movie.addElement(m);
					flag+=1;
				}
					else if((flag!=2)&&(type.elementAt(j).name==m.getDirectors())){
						type.elementAt(j).weight++;
						//type.elementAt(j).movie.addElement(m);
						flag+=2;					
				}
				j++;
			}
			//*** crating new Type if needed
			if(flag==1)
				type.addElement(new Type(m.getDirectors()));
			else if(flag==2)
				type.addElement(new Type(m.getGenre()));
			else if(flag==0) {
				type.addElement(new Type(m.getGenre()));
				type.addElement(new Type(m.getDirectors()));
			}
			j=flag=0;
			i++;
		}		
	}
		catch (SQLException e) {
			e.printStackTrace();
			}
		
	}
	/*creating the Type vector for the manager*/
	public void creatManagerdb()
	{
		ResultSet res;
		type=new Vector<Type>(1,4);
		res= database.Database.getType();
		try {
			while(res.next())
				type.addElement(new Type(res));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/***************
	 * Algorithem
	 * *notes:********now goint to the first part of the algo, at enother funt*****
	 * part 1: now we want to run over all moviedb and connect them to the relevant types
	 *  	if manager we will take the types from the sqldb at this point,all other is the same
	 * 		 spread weights threw type vector
	 * part 2(view): sort the movieVector/screeningVector by weight
	 * part 3:show the results.
	****************************************************************************/
	/********************************************************************************
	 * algoPart1User :connecting the moviedb to the Type already made, and spreadint weights to the movies
	 * made for: regular User that already connected to the system
	 *********************************************************************************/
	public void algoPart1() 
{
		//connectivit between moviedb and Type
		int i=0,j=0,flag=0;
		Movie m;
		while(i<movie.size()) {
			m=movie.elementAt(i);
			// Connecting History to Types...making weights
			while(j<type.size()&& (flag!=3)) {
				if((flag!=1)&&(type.elementAt(j).name==m.getGenre())) {
					type.elementAt(j).movie.addElement(m);//Adding the movie to Type
					m.weight++;//weighting the movie
				}
					else if((flag!=2)&&(type.elementAt(j).name==m.getDirectors())){
						type.elementAt(j).movie.addElement(m);//Adding the movie to Type
						m.weight++;// Weighting the movie
						//type.elementAt(j).movie.addElement(m);				
				}
				j++;
			}
			j=flag=0;
			i++;
		}

	}
	
	/*********************************************************************************
	 * algoPart2 :
	 * connecting the moviedb to the Types...  
	 * spreadint weights to the movies Using algoPart1User.
	 * made for: Manager
	 *********************************************************************************/
	public void algoPart2() 
	{
		Collections.sort(screeningAfterAlgo);
		Collections.sort(movieAfterAlgo);
	}
	public Movie getMovie(int id) {
		int i=0;
		Movie m=null;
		while(i<movie.size()) {
			m=movie.elementAt(i);
			if(m.getId()==id) {
				return  m;
			}
			i++;
		}
		return m;
	}
	public int getId() {
		return id;
	}
 	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getfirstName() {
		return firstName;
	}
	public String getlastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public Screening getScreening(int id) {
		int i=0;
		Screening s=null;
		while(i<screening.size()) {
			s=screening.elementAt(i);
			if(s.getId()==id) {
				return  s;
			}
			i++;
		}
		return s;
	}
}

