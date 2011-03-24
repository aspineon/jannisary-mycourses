<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Tom@Lwis (http://www.lwis.net/free-css-drop-down-menu/)" />
<meta name="keywords" content=" css, dropdowns, dropdown menu, drop-down, menu, navigation, nav, horizontal, vertical left-to-right, vertical right-to-left, horizontal linear, horizontal upwards, cross browser, internet explorer, ie, firefox, safari, opera, browser, lwis" />
<meta name="description" content="Clean, standards-friendly, modular framework for dropdown menus" />
<link href="css/dropdown/themes/flickr.com/helper.css" media="screen" rel="stylesheet" type="text/css" />

<!-- Beginning of compulsory code below -->

<link href="css/dropdown/dropdown.css" media="screen" rel="stylesheet" type="text/css" />
<link href="css/dropdown/themes/flickr.com/default.ultimate.css" media="screen" rel="stylesheet" type="text/css" />
<title>Demo Project</title>
</head>
<body>
<f:view>
<h1><img src="css/dropdown/themes/flickr.com/images/icon.png" alt="flickr" /> Scheduling System</h1>

<!-- Beginning of compulsory code below -->

<ul id="nav" class="dropdown dropdown-horizontal">
	<li><a href="Main.jsp">Home</a></li>
	<li><span class="dir">Scheduling</span>
		<ul>
			<li><a href="inputname.jsp" target="frame1">Manual Schedule</a></li>
			<li><a href="ThirdPage.jsp" target="frame1">Automatic Schedule</a></li>
			<!-- <li><span class="dir">The Team</span>
				<ul>
					<li><a href="./index.jsp">Brigita</a></li>
					<li><a href="./">John</a></li>
					<li><a href="./">Michael</a></li>
					<li><a href="./">Peter</a></li>
					<li><a href="./">Sarah</a></li>
				</ul>
			</li> -->
			<!-- <li><a href="./">Clients</a></li>
			<li><a href="./">Testimonials</a></li>
			<li><a href="./">Press</a></li>
			<li><a href="./">FAQs</a></li>-->
			<li class="divider"><a href="./">More...</a></li>
		</ul>
	</li>
	
	<li><span class="dir">Lectures</span>
		<ul>
			<li><a class="dir">Operations</a>
				<ul>
					<li><a href="./addNewLecture.jsp" target="frame1">Add New Lecture</a></li>
					<li><a href="./">Update Lecture</a></li>
					<li><a href="./">Delete Lecture</a></li>
				</ul>
			</li>
			<li><a href="./" class="dir">Schedules</a>
				<ul>
					<li><a href="./">See All Schedules</a></li>
					<li><a href="./">See Schedules as Classes</a></li>
					<!--  <li><a href="./">Private Use</a></li>-->
				</ul>
			</li>
			<li><a href="./">Featured</a></li>
			<li><a href="./">Top Rated</a></li>
			<li><a href="./">Prices</a></li>
			<li class="divider"><a href="./">More...</a></li>
		</ul>
	</li>
	<li><a href="./">Gallery</a></li>
	<li><a href="./">Events</a></li>
	<!-- <li><a href="./">Careers</a></li>-->
	<li><a class="dir">Help</a>
		<ul>
			<li><a href="./">Contact Us</a></li>
			<li><a href="./">How to Use This System</a></li>
			<li><a href="./">Your Feedback</a></li>
			<li class="divider"><a href="./">More...</a></li>
		</ul>
	</li>
</ul>
<br />

<br><br>
	<!--   <h:outputText>hello world</h:outputText> -->
	
	<br/><br/><br/>
	
	<iframe name="frame1" style="position:absolute; left: 32px; top: 146px; width: 1136px; height: 514px;"></iframe>
	
	
</f:view>
</body>
</html>