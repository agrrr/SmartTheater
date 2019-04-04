<%@ page import="model.User" import="controller.Control" import="model.Movie" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <!-- Title Page-->
    <title>Add Movie Form</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Icons font CSS-->
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Vendor CSS-->
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">
    <!-- Main CSS-->
    <link href="css/addmovie.css" rel="stylesheet" media="all">
    
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
    <a href="#addmovie" onclick="w3_close()" class="w3-bar-item w3-button w3-padding"><i class="fa fa-list fa-fw w3-margin-right"></i>Add Movie</a>  
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
  
    <div class="page-wrapper p-t-130 p-b-100 font-poppins">
        <div class="wrapper wrapper--w680">
            <div class="card card-4">
                <div class="card-body" id="addmovie">
                    <h2 class="title">Add Movie Form</h2>
                    <form method="POST" name="add_movie">
                           <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Movie name</label>
                                    <input class="input--style-4" type="text" name="movie_name" required>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Genre</label>
                                    <input class="input--style-4" type="text" name="genre" required>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Directors</label>
                                    <input class="input--style-4" type="text" name="directors" required>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Year</label>
                                    <input class="input--style-4" type="number" name="year" required>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Actors</label>
                                    <div class="input-group-icon">
                                        <input class="input--style-4" type="text" name="actors" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Length</label>
                                    <div class="input-group-icon">
                                        <input class="input--style-4" type="number" name="length" required>
                                    </div>
                                </div>
                            </div>
                             <div class="input-group">
                                    <label class="label">Review</label>
                                    <div class="input-group-icon">                                    
                                        <textarea class="input--style-5" name="review" rows="6" cols="50" required> </textarea>
                                    </div>
                                </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Trailer</label>
                                    <input class="input--style-4" type="text" name="trailer" required>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Picture link</label>
                                    <input class="input--style-4" type="text" name="picture" required>
                                </div>
                            </div>
                        </div>
                        <div class="p-t-15">
                            <button class="btn btn--radius-2 btn--blue" type="submit" formaction="AddMovie">Submit</button> 
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Jquery JS-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="vendor/select2/select2.min.js"></script>
    <script src="vendor/datepicker/moment.min.js"></script>
    <script src="vendor/datepicker/daterangepicker.js"></script>
    <!-- Main JS-->
    <script src="js/global.js"></script>

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
