<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Syllabus</title>
</head>
<body>
<f:view>
	<table bgcolor="lightblue">
	<tr style=" height : 19px;">
		<td>
		</td>
	</tr>
	<tr>
	<td style=" width : 29px;"></td>
		<td>
			<rich:comboBox value="Lecturer">
					<f:selectItem/>
			</rich:comboBox>
				
		</td>
		<td style=" width : 57px;">
		
		</td>
		<td>
			<rich:comboBox value="Course">
					<f:selectItem />
			</rich:comboBox>
		</td>
		<td style=" width : 21px;">
		</td>
		<td>
			<a4j:commandButton value="Submit" style=" width : 88px;">				
			</a4j:commandButton>
		</td>
		<td style=" width : 25px;">
		</td>
	</tr>
	<tr style=" height : 17px;">
		<td></td>
	</tr>
</table>
</f:view>
</body>
</html>