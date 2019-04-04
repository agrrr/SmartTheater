<%@ page import="model.User" import="controller.Control" import="model.Movie" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>Movie Theater</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Script -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src='select2/dist/js/select2.min.js' type='text/javascript'></script>

<!-- CSS -->
<link href='select2/dist/css/select2.min.css' rel='stylesheet' type='text/css'>
<meta charset="windows-1255">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}

table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  text-align: left;
  padding: 8px;
}

.signoutbtn {
  position: absolute;
  top: 16px;
  right: 32px;
  font-size: 18px;
}

.sobtn {
  background: none;
}

.btn {
  padding: 0px;
  border: none;
  background: none;
}

.moviesfilter {
  margin-left: 100px;
  margin-bottom: 20px;
}

* {
  box-sizing: border-box;
}

#myInput {
  background-image: url('/css/searchicon.png');
  background-position: 10px 12px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 16px;
  padding: 10px 10px 10px 10px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
  margin-top:20px;
  width: 20%;
  height: 30px;
}

#myUL {
  list-style-type: none;
  padding: 0;
  margin: 0;
  width: 50%;
  margin-top:20px;
  margin-bottom:20px;
}

#myUL li a {
  border: 1px solid #ddd;
  margin-top: -1px; /* Prevent double borders */
  background-color: #f6f6f6;
  padding: 6px;
  text-decoration: none;
  font-size: 18px;
  color: black;
  display: block
}

#myUL li a:hover:not(.header) {
  background-color: #eee;
}
</style>

<%try{
	 User u = (User)session.getAttribute("User");
	 String user = u.getUserName();
	if((!(session.getAttribute("login").equals(1))) || !(user.equals("admin"))  ){
		String nextHtml = response.encodeRedirectURL("homepagein.jsp");
		response.sendRedirect(nextHtml);
		}
	}catch(Exception e){
		String nextHtml = response.encodeRedirectURL("homepagein.jsp");
		response.sendRedirect(nextHtml);
	} 
%>

<body class="w3-light-grey w3-content" style="max-width:1600px">

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-black w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
  <div class="w3-container">
    <a href="#" onclick="w3_close()" class="w3-hide-large w3-right w3-jumbo w3-padding w3-hover-grey" title="close menu">
      <i class="fa fa-remove"></i>
    </a>
    <h4><b>Movie Theater</b></h4>
    <p class="w3-text-grey">Movie Theater</p>
  </div>
  <div class="w3-bar-block">
  	<a href="#main" onclick="w3_close()" class="w3-bar-item w3-button w3-padding w3-text-teal"><i class="fa fa-bars fa-fw w3-margin-right"></i>Menu</a> 
    <a href="#setscreenings" onclick="w3_close()" class="w3-bar-item w3-button w3-padding"><i class="fa fa-list fa-fw w3-margin-right"></i>Set Screenings</a> 
  </div>
  <div class="w3-panel w3-large">
    <i class="fa fa-facebook-official w3-hover-opacity"></i>
    <i class="fa fa-instagram w3-hover-opacity"></i>
    <i class="fa fa-snapchat w3-hover-opacity"></i>
    <i class="fa fa-pinterest-p w3-hover-opacity"></i>
    <i class="fa fa-twitter w3-hover-opacity"></i>
    <i class="fa fa-linkedin w3-hover-opacity"></i>
  </div>
</nav>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main w3-padding w3-dark-grey" style="margin-left:300px" id="main">

  <!-- Header -->
  <header>
	
    <span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey" onclick="w3_open()"><i class="fa fa-bars"></i></span>
    <div class="w3-padding w3-black">
    <h1 style="text-align:center;"><b >Movie Theater</b></h1>
    <div class="w3-section w3-bottombar w3-padding-16" style="text-align:center;">
    	<div class="signoutbtn">
    		<form method="POST" action="SignOut">
        		<button class="sobtn w3-button w3-round-large" type="submit">Sign Out<i class="fa fa-sign-out w3-margin-left"></i></button>
    	 	</form>
    	</div>
    <form>
      <button class="w3-button w3-dark-grey w3-round-large" type="submit" formaction="homemanager.jsp"><i class="fa fa-home w3-margin-right"></i>Home</button>
      <button class="w3-button w3-dark-grey w3-round-large" type="submit" formaction="AccInfoManager.jsp"><i class= "fa fa-address-card w3-margin-right"></i>Account Info</button>
      <button class="w3-button w3-dark-grey w3-round-large" type="submit" formaction="addmovie.jsp">Add Movies</button>
      <button class="w3-button w3-dark-grey w3-round-large" type="submit" formaction="setscreenings.jsp"><i></i>Set Screenings</button>
    </form>

    </div>
    </div>
  </header>
  
	<!-- Set Screenings -->
	<h1 id="setscreenings" style="text-align:center;">Set Screenings</h1>
	<div style="margin-left:100px;">
		<form method="POST">
			<div style="margin-bottom:20px;">			 
			 Screening Date:
 			 	<input type="date" name="date">
 			</div>		 
			<div style="margin-bottom:20px;">
			 Screening Time:
 			 	<select id="selMovie" style='width: 200px;' name="time">
					<option value='0'>Select Time</option> 
					<option value="09:00">09:00</option>
					<option value="12:00">12:00</option> 
					<option value="15:00">15:00</option> 
					<option value="18:00">18:00</option> 
					<option value="21:00">21:00</option>
				</select>	 
 			 </div>
		<!-- Movies Filter -->
		<!-- Dropdown --> 
		<select id="selMovie" style='width: 200px;' name="movie">
		<option value='0'>Select Movie</option> 
		<% try{
		 	User u = (User)session.getAttribute("User"); 
		 	int i;
		 	for(i=0;i<(u.movie.size());i++){ %>
				<option value="<%=i%>"><%=u.movie.elementAt(i).getName()%></option> 
  		<%	}
  		   }catch(Exception e){} %>
		</select>
		<br>
		<br>
		<button type="submit" formaction=SetScreening>Submit</button>
	</form>

<br/>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
</div>

  <!-- Footer -->
  <div class="w3-black w3-center w3-padding-24">© Avihay Itay Matan and <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></div>

<!-- End page content -->
</div>

<script>
$(document).ready(function(){
	 
	  // Initialize select2
	  $("#selUser").select2();

	  // Read selected option
	  $('#but_read').click(function(){
	    var username = $('#selUser option:selected').text();
	  });
	});
</script>

</body>
</html>
