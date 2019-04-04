package database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class CreateDataBase {
	@SuppressWarnings("deprecation")
	public static void checkDatabase() throws IllegalAccessException, ClassNotFoundException, SQLException {
		//
		String table1 = "CREATE TABLE user (userName text NOT NULL, password text NOT NULL, firstName text NOT NULL,lastName text NOT NULL,email text NOT NULL,phoneNumber text NOT NULL,id int(20) NOT NULL AUTO_INCREMENT, birthday text NOT NULL,PRIMARY KEY (id))";
		
		String table2 = "CREATE TABLE movie (id int(11) NOT NULL AUTO_INCREMENT,name text NOT NULL,genre text NOT NULL,directors text NOT NULL,year text NOT NULL,actors text NOT NULL,review mediumtext NOT NULL,trailer text NOT NULL,picture text NOT NULL,length int(11) NOT NULL, PRIMARY KEY (id))";

		String table3 = "CREATE TABLE `screening` (`id` int(11) NOT NULL AUTO_INCREMENT,`date` text NOT NULL, `time` text NOT NULL,`movieId` int(11) NOT NULL,`sold` int(11) NOT NULL DEFAULT '0',`capacity` int(11) NOT NULL DEFAULT '100' ,PRIMARY KEY (id),KEY `movieId` (`movieId`))";

		String table4 = "CREATE TABLE `ticket` (`id` int(11) NOT NULL AUTO_INCREMENT,`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,`movieId` int(11) NOT NULL,`userId` int(11) NOT NULL,  `rating` int(11) NOT NULL DEFAULT '3',  `price` int(11) NOT NULL DEFAULT '25',`screeningId` int(11) NOT NULL, PRIMARY KEY (id),KEY `movieId` (`movieId`),KEY `userId` (`userId`),KEY `screeningId` (`screeningId`)) ";
		
		String table5 = "CREATE TABLE `weights` (`type` text NOT NULL,`avg` int(11) NOT NULL DEFAULT '0')";
		
		ResultSet rs = null;
		int flag = 0;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String dbUrl = "jdbc:mysql://localhost";
			String dbUrl2 = "jdbc:mysql://localhost/theaterdb";
			String dbname= "theaterdb";
			Connection conn = null;
			Connection conn2 = null;
			conn = (Connection) DriverManager.getConnection(dbUrl, "root", "");
			rs = conn.getMetaData().getCatalogs();
			
			while(rs.next()) {
				String catalogs = rs.getString(1);
				if(dbname.equals(catalogs)) {
					flag=1;
					System.out.println("DB exists");
				}
			}
			if(flag==0) {// if there is no db we will create the db 
				Statement statement = (Statement) conn.createStatement();
				statement.executeUpdate("CREATE DATABASE theaterdb");
				conn2 = (Connection) DriverManager.getConnection(dbUrl2, "root", "");
				Statement statement2 = (Statement) conn2.createStatement();
				statement2.executeUpdate(table1);
				Statement statement3 = (Statement) conn2.createStatement();
				statement3.executeUpdate(table2);
				Statement statement4 = (Statement) conn2.createStatement();
				statement4.executeUpdate(table3);
				Statement statement5 = (Statement) conn2.createStatement();
				statement5.executeUpdate(table4);
				Statement statement6 = (Statement) conn2.createStatement();
				statement6.executeUpdate(table5);

				
				//initialize the movie table
				Statement statement8 = (Statement) conn2.createStatement();
				statement8.executeUpdate("INSERT INTO `movie` (`id`, `name`, `genre`, `directors`, `year`, `actors`, `review`, `trailer`, `picture`, `length`) VALUES\r\n" + 
						"(1, 'Captain Marvel', 'Adventure', 'Anna Boden', '2019', 'Brie Larson, Gemma Chan, Samuel L. Jackson', 'Carol Danvers becomes one of the universe\\'s most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.', 'https://www.youtube.com/embed/o942gpNCiFY?hd=1&wmode=opaque&controls=1&showinfo=0&autoplay=1', 'https://www.yesplanet.co.il/xmedia-cw/repo/feats/posters/3106S2R-lg.jpg', 0),\r\n" + 
						"(2, 'Green Book', 'Biography ', 'Peter Farrelly', '2018', 'Viggo Mortensen, Mahershala Ali, Linda Cardellini', ' A working-class Italian-American bouncer becomes the driver of an African-American classical pianist on a tour of venues through the 1960s American South.', 'https://www.youtube.com/embed/QkZxoko_HC0', 'https://m.media-amazon.com/images/M/MV5BYzIzYmJlYTYtNGNiYy00N2EwLTk4ZjItMGYyZTJiOTVkM2RlXkEyXkFqcGdeQXVyODY1NDk1NjE@._V1_SY1000_CR0,0,666,1000_AL_.jpg', 130),\r\n" + 
						"(3, 'Us', 'Horror', 'Jordan Peele', '2019', 'Lupita Nyong\\'o, Winston Duke, Elisabeth Moss', ' A family\\'s serenity turns to chaos when a group of doppelg&#228;ngers begins to terrorize them', 'https://www.youtube.com/embed/hNCmb-4oXJA', 'https://m.media-amazon.com/images/M/MV5BZTliNWJhM2YtNDc1MC00YTk1LWE2MGYtZmE4M2Y5ODdlNzQzXkEyXkFqcGdeQXVyMzY0MTE3NzU@._V1_SY1000_CR0,0,631,1000_AL_.jpg', 116),\r\n" + 
						"(4, 'The Lord of the Rings: The Fellowship of the Ring', 'Fantasy ', 'Peter Jackson', '2001', 'Elijah Wood, Ian McKellen, Orlando Bloom', ' A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.', 'https://www.youtube.com/embed/cKEGZ-CvWHk', 'https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_SY999_CR0,0,673,999_AL_.jpg', 178),\r\n" + 
						"(5, 'Pok&#233;mon Detective Pikachu', 'Adventure', 'Rob Letterman', '2019', 'Ryan Reynolds, Suki Waterhouse, Justice Smith', ' In a world where people collect Pok&#233;mon to do battle, a boy comes across an intelligent talking Pikachu who seeks to be a detective.', 'https://www.youtube.com/embed/1roy4o4tqQM', 'https://m.media-amazon.com/images/M/MV5BNDU4Mzc3NzE5NV5BMl5BanBnXkFtZTgwMzE1NzI1NzM@._V1_SY1000_CR0,0,674,1000_AL_.jpg', 123),\r\n" + 
						"(6, 'The Lego Movie 2: The Second Part', 'Animation', 'Mike Mitchell', '2019', 'Chris Pratt, Elizabeth Banks, Will Arnett', ' It\\'s been five years since everything was awesome and the citizens are facing a huge new threat: Lego Duplo invaders from outer space, wrecking everything faster than they can rebuild.', 'https://www.youtube.com/embed/cksYkEzUa7k', 'https://m.media-amazon.com/images/M/MV5BMTkyOTkwNDc1N15BMl5BanBnXkFtZTgwNzkyMzk3NjM@._V1_SY1000_CR0,0,674,1000_AL_.jpg', 107),\r\n" + 
						"(7, 'Native Son', 'Drama ', 'Rashid Johnson', '2019', 'Margaret Qualley, Nick Robinson, KiKi Layne', ' A young African-American living in Chicago enters into a seductive new world of money and power after he is hired as a chauffeur for an affluent businessman.', 'https://www.youtube.com/embed/ghfwH5jWTbc', 'https://m.media-amazon.com/images/M/MV5BZDY0OTM2OTgtYmM1Mi00ZjE4LTkwY2MtMzA3OGNkZDlkZjliXkEyXkFqcGdeQXVyNzc5MjA3OA@@._V1_SY1000_CR0,0,675,1000_AL_.jpg', 108),\r\n" + 
						"(8, 'Destroyer', 'Drama', 'Karyn Kusama', '2018', 'Nicole Kidman, Toby Kebbell, Tatiana Maslany', ' A police detective reconnects with people from an undercover assignment in her distant past in order to make peace.', 'https://www.youtube.com/embed/9g-j4wuEOPo', 'https://m.media-amazon.com/images/M/MV5BODI4MTI2OTAyMV5BMl5BanBnXkFtZTgwNjY3NDY1NjM@._V1_SY1000_CR0,0,675,1000_AL_.jpg', 121),\r\n" + 
						"(9, 'The Conjuring', 'Horror', 'James Wan', '2013', 'Patrick Wilson, Vera Farmiga, Ron Livingston ', 'Paranormal investigators Ed and Lorraine Warren work to help a family terrorized by a dark presence in their farmhouse. ', 'https://www.youtube.com/embed/k10ETZ41q5o', 'https://m.media-amazon.com/images/M/MV5BMTM3NjA1NDMyMV5BMl5BanBnXkFtZTcwMDQzNDMzOQ@@._V1_SY1000_CR0,0,674,1000_AL_.jpg', 112),\r\n" + 
						"(10, 'Furious 7', 'Action', 'James Wan', '2015', 'Vin Diesel, Paul Walker, Dwayne Johnson ', ' Deckard Shaw seeks revenge against Dominic Toretto and his family for his comatose brother.', 'https://www.youtube.com/embed/Skpu5HaVkOc', 'https://m.media-amazon.com/images/M/MV5BMTQxOTA2NDUzOV5BMl5BanBnXkFtZTgwNzY2MTMxMzE@._V1_SY1000_CR0,0,631,1000_AL_.jpg', 137),\r\n" + 
						"(11, 'Dead Silence', 'Horror', 'James Wan', '2007', 'Ryan Kwanten, Amber Valletta, Donnie Wahlberg ', ' A young widower returns to his hometown to search for answers to his wife\\'s murder, which may be linked to the ghost of a murdered ventriloquist.', 'https://www.youtube.com/embed/ibyi3UQu7jE', 'https://m.media-amazon.com/images/M/MV5BMjEyNDQwMTQ3OV5BMl5BanBnXkFtZTcwMTY5MzI0MQ@@._V1_.jpg', 89),\r\n" + 
						"(12, ' The Hobbit: An Unexpected Journey', 'Fantasy', 'Peter Jackson', '2012', 'Martin Freeman, Ian McKellen, Richard Armitage', ' A reluctant Hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home, and the gold within it from the dragon Smaug.', 'https://www.youtube.com/embed/SDnYMbYB-nU', 'https://m.media-amazon.com/images/M/MV5BMTcwNTE4MTUxMl5BMl5BanBnXkFtZTcwMDIyODM4OA@@._V1_SY1000_CR0,0,674,1000_AL_.jpg', 169),\r\n" + 
						"(13, 'The Hobbit: The Battle of the Five Armies', 'Fantasy ', 'Peter Jackson', '2014', 'Ian McKellen, Martin Freeman, Richard Armitage', ' Bilbo and company are forced to engage in a war against an array of combatants and keep the Lonely Mountain from falling into the hands of a rising darkness. ', 'https://www.youtube.com/embed/iVAgTiBrrDA', 'https://m.media-amazon.com/images/M/MV5BODAzMDgxMDc1MF5BMl5BanBnXkFtZTgwMTI0OTAzMjE@._V1_SY1000_SX675_AL_.jpg', 144),\r\n" + 
						"(14, 'The Invitation', 'Horror', 'Karyn Kusama', '2015', 'Logan Marshall-Green, Emayatzy Corinealdi, Michiel Huisman', ' A man accepts an invitation to a dinner party hosted by his ex-wife, an unsettling affair that reopens old wounds and creates new tensions. ', 'https://www.youtube.com/embed/0-mp77SZ_0M', 'https://m.media-amazon.com/images/M/MV5BMTkzODMwNDkzOF5BMl5BanBnXkFtZTgwNDA4NzA1ODE@._V1_SY1000_CR0,0,687,1000_AL_.jpg', 100),\r\n" + 
						"(15, 'Diane', 'Drama', 'Kent Jones', '2018', 'Mary Kay Place, Jake Lacy, Estelle Parsons', ' Diane fills her days helping others and desperately attempting to bond with her drug-addicted son. As these pieces of her existence begin to fade, she finds herself confronting memories she\\'d sooner forget than face.', 'https://www.youtube.com/embed/Yl1WJA0T5II', 'https://m.media-amazon.com/images/M/MV5BMWZkMTliNDItMjRkMC00MTRjLWEzZTItMDkwZGFkOTliMzA1XkEyXkFqcGdeQXVyODY3Nzc0OTk@._V1_SY1000_CR0,0,675,1000_AL_.jpg', 95),\r\n" + 
						"(16, 'Harry Potter and the Deathly Hallows: Part 2', 'Adventure', 'David Yates', '2011', 'Daniel Radcliffe, Emma Watson, Rupert Grint', ' Harry, Ron, and Hermione search for Voldemort\\'s remaining Horcruxes in their effort to destroy the Dark Lord as the final battle rages on at Hogwarts.', 'https://www.youtube.com/embed/mObK5XD8udk', 'https://m.media-amazon.com/images/M/MV5BMjIyZGU4YzUtNDkzYi00ZDRhLTljYzctYTMxMDQ4M2E0Y2YxXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX667_CR0,0,667,999_AL_.jpg', 130),\r\n" + 
						"(17, 'Harry Potter and the Order of the Phoenix', 'Adventure', 'David Yates', '2007', 'Daniel Radcliffe, Emma Watson, Rupert Grint', ' With their warning about Lord Voldemort\\'s return scoffed at, Harry and Dumbledore are targeted by the Wizard authorities as an authoritarian bureaucrat slowly seizes power at Hogwarts.', 'https://www.youtube.com/embed/y6ZW7KXaXYk', 'https://m.media-amazon.com/images/M/MV5BMTM0NTczMTUzOV5BMl5BanBnXkFtZTYwMzIxNTg3._V1_.jpg', 138),\r\n" + 
						"(19, 'Solo: A Star Wars Story', 'Fantasy', 'Ron Howard', '2018', 'Alden Ehrenreich, Woody Harrelson, Emilia Clarke', ' During an adventure into the criminal underworld, Han Solo meets his future co-pilot Chewbacca and encounters Lando Calrissian years before joining the Rebellion.', 'https://www.youtube.com/embed/jPEYpryMp2s', 'https://m.media-amazon.com/images/M/MV5BOTM2NTI3NTc3Nl5BMl5BanBnXkFtZTgwNzM1OTQyNTM@._V1_SY1000_CR0,0,674,1000_AL_.jpg', 135),\r\n" + 
						"(20, ' The Beatles: Eight Days a Week - The Touring Years', 'Documentary', 'Ron Howard', '2016', 'The Beatles, John Lennon, George Harrison', ' A compilation of found footage featuring music, interviews, and stories of The Beatles\\' 250 concerts from 1963 to 1966.', 'https://www.youtube.com/embed/0fFyZzqPDws', 'https://m.media-amazon.com/images/M/MV5BMTc0NDgwMTk2OV5BMl5BanBnXkFtZTgwNjEzODIyOTE@._V1_SY1000_SX675_AL_.jpg', 106),\r\n" + 
						"(21, 'Star Wars: Episode VIII - The Last Jedi', 'Fantasy', 'Rian Johnson', '2017', 'Daisy Ridley, John Boyega, Mark Hamill', ' Rey develops her newly discovered abilities with the guidance of Luke Skywalker, who is unsettled by the strength of her powers. Meanwhile, the Resistance prepares for battle with the First Order.', 'https://www.youtube.com/embed/Q0CbN8sfihY', 'https://m.media-amazon.com/images/M/MV5BMjQ1MzcxNjg4N15BMl5BanBnXkFtZTgwNzgwMjY4MzI@._V1_SY1000_CR0,0,675,1000_AL_.jpg', 152)");
				//initialize the user table
				Statement statement9 = (Statement) conn2.createStatement();
				statement9.executeUpdate("INSERT INTO `user` (`userName`, `password`, `firstName`, `lastName`, `email`, `phoneNumber`, `id`, `birthday`) VALUES\r\n" + 
						"('ma', '12', 'matan', 'weizman', 'matanweiz@gmail.com', '0526018771', 2, '25/06/1991'),\r\n" + 
						"('admin', 'admin', 'admin', 'admin', 'admin@gmail.com', '0512345678', 3, '21/03/2019')");
				//initialize the screening table
				Statement statement10 = (Statement) conn2.createStatement();
				statement10.executeUpdate("INSERT INTO `screening` (`id`, `date`, `time`, `movieId`, `sold`, `capacity`) VALUES\r\n" + 
						"(2, '22-3-2013', '12:00', 1, 0, 100),\r\n" + 
						"(3, '23-3-2019', '12:89', 2, 0, 100),\r\n" + 
						"(4, '22-3-2020', '23:00', 3, 0, 100),\r\n" + 
						"(5, '', '0', 7, 0, 100),\r\n" + 
						"(6, '2019-03-27', '12:00', 7, 0, 100)");
				
				//initialize the ticket table
				Statement statement11 = (Statement) conn2.createStatement();
				statement11.executeUpdate("INSERT INTO `ticket` (`id`, `date`, `movieId`, `userId`, `rating`, `price`, `screeningId`) VALUES\r\n" + 
						"(1, '2019-03-24 19:54:57', 1, 2, 5, 25, 2),\r\n" + 
						"(2, '2019-03-25 20:12:09', 3, 2, 3, 25, 4),\r\n" + 
						"(3, '2019-03-27 10:47:42', 7, 2, 3, 25, 6),\r\n" + 
						"(4, '2019-03-27 10:48:06', 7, 2, 3, 25, 6)");
				//initialize the weight table
				Statement statement12 = (Statement) conn2.createStatement();
				statement12.executeUpdate("INSERT INTO `weights` (`type`, `avg`) VALUES\r\n" + 
						"('action', 0),\r\n" + 
						"('trill', 0);");
					}
		}
		catch (IllegalAccessException | ClassNotFoundException | SQLException | InstantiationException e){
			e.printStackTrace();
			}
	}
}