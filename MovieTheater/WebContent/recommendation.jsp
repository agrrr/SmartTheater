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

.btn{
  padding: 0px;
  border: none;
  background: none;
}
</style>

<%try{
	if(!(session.getAttribute("login").equals(1))){
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
    <a href="#recommendedmovies" onclick="w3_close()" class="w3-bar-item w3-button w3-padding"><i class="fa fa-th-large fa-fw w3-margin-right"></i>Recommended Movies</a> 
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
   	  		<button class="w3-button w3-khaki w3-round-large" type="submit" formaction="homepage.jsp"><i class="fa fa-home w3-margin-right"></i>Home</button>
      		<button class="w3-button w3-khaki w3-round-large" type="submit" formaction="AccInfo.jsp"><i class= "fa fa-address-card w3-margin-right"></i>Account Info</button>
      		<button class="w3-button w3-khaki w3-round-large" type="submit" formaction="myhistory.jsp"><i class="fa fa-history w3-margin-right"></i>My History</button>
      		<button class="w3-button w3-khaki w3-round-large" type="submit" formaction="recommendation.jsp"><i class="fa fa-thumbs-o-up w3-margin-right"></i>Recommendation</button>
    	</form>

    </div>
    </div>
  </header>

<hr id="recommendedmovies">
<h1 style="text-align: center;">Recommended Movies</h1><br>
<% try{
	User u = (User)session.getAttribute("User"); %>
<form method="POST" action ="MovieInfo">

  <!-- First Photo Grid-->
  <div class="w3-row-padding">
   	<div class="w3-third w3-container w3-margin-bottom">
   	<button class="btn" name="movie" value="<%=u.movieAfterAlgo.elementAt(0).getId()%>"><img src="<%=u.movieAfterAlgo.elementAt(0).getPicture()%>" style="width:100%" 
   			class="w3-hover-opacity" alt="<%=u.movieAfterAlgo.elementAt(0).getName()%>"></button> 
     	<p><b><%=u.movieAfterAlgo.elementAt(0).getName()%></b><br><%=u.movieAfterAlgo.elementAt(0).getYear()%></p>
    </div>
    <div class="w3-third w3-container w3-margin-bottom">
         	<button class="btn" name="movie" value="<%=u.movieAfterAlgo.elementAt(1).getId()%>"><img src="<%=u.movieAfterAlgo.elementAt(1).getPicture()%>" style="width:100%" 
   			class="w3-hover-opacity" alt="<%=u.movieAfterAlgo.elementAt(1).getName()%>"></button> 
     	<p><b><%=u.movieAfterAlgo.elementAt(1).getName()%></b><br><%=u.movieAfterAlgo.elementAt(1).getYear()%></p>
    </div>
    <div class="w3-third w3-container">
         	<button class="btn" name="movie" value="<%=u.movieAfterAlgo.elementAt(2).getId()%>"><img src="<%=u.movieAfterAlgo.elementAt(2).getPicture()%>" style="width:100%" 
   			class="w3-hover-opacity" alt="<%=u.movieAfterAlgo.elementAt(2).getName()%>"></button> 
      	<p><b><%=u.movieAfterAlgo.elementAt(2).getName()%></b><br><%=u.movieAfterAlgo.elementAt(2).getYear()%></p>
    </div>
  </div>
  
  <!-- Second Photo Grid-->
  <div class="w3-row-padding">
    <div class="w3-third w3-container w3-margin-bottom">
         	<button class="btn" name="movie" value="<%=u.movieAfterAlgo.elementAt(3).getId()%>"><img src="<%=u.movieAfterAlgo.elementAt(3).getPicture()%>" style="width:100%" 
   			class="w3-hover-opacity" alt="<%=u.movieAfterAlgo.elementAt(3).getName()%>"></button> 
      	<p><b><%=u.movieAfterAlgo.elementAt(3).getName()%></b><br><%=u.movieAfterAlgo.elementAt(3).getYear()%></p>
    </div>
    <div class="w3-third w3-container w3-margin-bottom">
         	<button class="btn" name="movie" value="<%=u.movieAfterAlgo.elementAt(4).getId()%>"><img src="<%=u.movieAfterAlgo.elementAt(4).getPicture()%>" style="width:100%" 
   			class="w3-hover-opacity" alt="<%=u.movieAfterAlgo.elementAt(4).getName()%>"></button> 
      	<p><b><%=u.movieAfterAlgo.elementAt(4).getName()%></b><br><%=u.movieAfterAlgo.elementAt(4).getYear()%></p>
    </div>
    <div class="w3-third w3-container">
         	<button class="btn" name="movie" value="<%=u.movieAfterAlgo.elementAt(5).getId()%>"><img src="<%=u.movieAfterAlgo.elementAt(5).getPicture()%>" style="width:100%" 
   			class="w3-hover-opacity" alt="<%=u.movieAfterAlgo.elementAt(5).getName()%>"></button> 
     	<p><b><%=u.movieAfterAlgo.elementAt(5).getName()%></b><br><%=u.movieAfterAlgo.elementAt(5).getYear()%></p>
     	<%}catch(Exception e){} %>
    </div>
  </div>
</form>

<hr>

  <!-- Footer -->
  <div class="w3-black w3-center w3-padding-24">© Avihay Itay Matan and <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></div>

<!-- End page content -->
</div>

<script>
// Script to open and close sidebar
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}
</script>

</body>
</html>
