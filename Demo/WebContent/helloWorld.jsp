<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html 	xmlns:h = "http://java.sun.com/jsf/html"
		xmlns:f = "http://java.sun.com/jsf/core" 
		xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en"
       xmlns:rich="http://richfaces.org/rich"
       xmlns:s="http://jboss.com/products/seam/taglib"
       xmlns:a4j="http://richfaces.org/a4j">
<head>
<title>JAnissary</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Tom@Lwis (http://www.lwis.net/free-css-drop-down-menu/)" />
<meta name="keywords" content=" css, dropdowns, dropdown menu, drop-down, menu, navigation, nav, horizontal, vertical left-to-right, vertical right-to-left, horizontal linear, horizontal upwards, cross browser, internet explorer, ie, firefox, safari, opera, browser, lwis" />
<meta name="description" content="Clean, standards-friendly, modular framework for dropdown menus" />
<link href="css/helper.css" media="screen" rel="stylesheet" type="text/css" />

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "entities.DOMAIN.TestUser" %>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<!-- Beginning of compulsory code below -->

<link href="css/dropdown.css" media="screen" rel="stylesheet" type="text/css" />
<link href="css/default.ultimate.css" media="screen" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.dropdown.js"></script>

<!-- / END -->

</head>
<body>

<h1><img src="images/icon.png" alt="flickr" /> JANISSARY SENIOR PROJECT</h1>

<!-- Beginning of compulsory code below -->


		

<ul id="nav" class="dropdown dropdown-horizontal">
	<li><a href="./">Home</a></li>
	<li><span class="dir">About Us</span>
		<ul>
			<li><a href="./">History</a></li>
			<li><a href="./">Our Vision</a></li>
			<li><span class="dir">The Team</span>
				<ul>
					<li><a href="./">Brigita</a></li>
					<li><a href="./">John</a></li>
					<li><a href="./">Michael</a></li>
					<li><a href="./">Peter</a></li>
					<li><a href="./">Sarah</a></li>
				</ul>
			</li>
			<li><a href="./">Clients</a></li>
			<li><a href="./">Testimonials</a></li>
			<li><a href="./">Press</a></li>
			<li><a href="./">FAQs</a></li>
			<li class="divider"><a href="./">More...</a></li>
		</ul>
	</li>
	<li><span class="dir">Services</span>
		<ul>
			<li><a href="./">Product Development</a></li>
			<li><a href="./">Delivery</a></li>
			<li><a href="./">Shop Online</a></li>
			<li><a href="./">Support</a></li>
			<li><a href="./">Training &amp; Consulting</a></li>
			<li class="divider"><a href="./">More...</a></li>
		</ul>
	</li>
	<li><span class="dir">Products</span>
		<ul>
			<li><a href="./" class="dir">New</a>
				<ul>
					<li><a href="./">Corporate Use</a></li>
					<li><a href="./">Private Use</a></li>
				</ul>
			</li>
			<li><a href="./" class="dir">Used</a>
				<ul>
					<li><a href="./">Corporate Use</a></li>
					<li><a href="./">Private Use</a></li>
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
	<li><a href="./">Careers</a></li>
	<li><a href="./" class="dir">Contact Us</a>
		<ul>
			<li><a href="./">Enquiry Form</a></li>
			<li><a href="./">Map &amp; Driving Directions</a></li>
			<li><a href="./">Your Feedback</a></li>
			<li class="divider"><a href="./">More...</a></li>
		</ul>
	</li>
</ul>
<br />

<jsp:useBean id="demoBean" class="demo.web.beans.DemoBean" scope="page" />
		<%		
			  out.print("Welcome: " + session.getAttribute("name"));
		%>
	
	<h1>
		LOGIN SCREEN
	</h1>
	
	
	
	<form method="post" action="LoginControl.jsp">
		UserName:<input type="text" name="userName"/>
		Password:<input type="password" name="userPass" style=" width : 183px;"/>
		
		<p>
		</p>

<button name="btnLogin" type="submit" style="width: 113px; height: 37px;"
	title="Login">
</button>

<f:view>
	<rich:comboBox>
		<f:selectItem itemValue="asd"/>
		<f:selectItem itemValue="das"/>
	</rich:comboBox>
			
	</f:view>

</form>
	
<!-- / END -->

</body>
</html>
