<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<f:loadBundle basename="jsfks.bundle.messages" var="msg"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>
	<h:outputText style="font-family:Tahoma; font-size:23px; color:blue" value="Add New Lecture"/>
	<br><br>
	<table bgcolor="lightgray">
	<tr>
	<tr style=" height : 22px;">
			<td></td>
		
	</tr>
		<tr>
			<td style=" width : 50px;"> </td>
			<td>
					<rich:comboBox id="classComboBoxForAddingLecture"  value="Select a Class" width="127">		
						<f:selectItem itemValue="1"/>
						<f:selectItem itemValue="2"/>
						<f:selectItem itemValue="3"/>
						<f:selectItem itemValue="4"/>					
			 		</rich:comboBox>
			</td>
		
		</tr>
		<tr style=" height : 22px;">
			<td></td>
		</tr>
		<tr>
			
			<td style=" width : 16px;">
			</td>
			<td>
				 <h:outputText style="font-family:Tahoma; font-size:14px; color:black" >Technical Lectures : </h:outputText>
				 <h:inputText value=""> </h:inputText>
			 </td>
			 <td style=" width : 23px;">
			 
			 </td>
			 <td>
			 	<rich:comboBox id="TypeOfLectureComboBox"  value="Select a Type" width="127">		
						<f:selectItem itemValue="Department Lecture"/>
						<f:selectItem itemValue="Social Elective"/>
						<f:selectItem itemValue="Elective"/>
						<f:selectItem itemValue="Technical Elective"/>					
			 		</rich:comboBox>
			 </td>
			 <td style=" width : 41px;"></td>
			 <td>
			 	<h:outputText > </h:outputText>
				 <rich:comboBox id="lectureCreditComboBox"  value="Teoric Credit" width="127">		
						<f:selectItem itemValue="1"/>
						<f:selectItem itemValue="2"/>
						<f:selectItem itemValue="3"/>
						<f:selectItem itemValue="4"/>
						<f:selectItem itemValue="5"/>
						<f:selectItem itemValue="6"/>
						<f:selectItem itemValue="7"/>
						<f:selectItem itemValue="8"/>
						<f:selectItem itemValue="9"/>					
			 		</rich:comboBox>
			 </td>
			 <td style=" width : 39px;">
			 </td>
			 <td>
			 	<h:outputText > </h:outputText>
				 <rich:comboBox id="lecturePracticeCreditComboBox"  value="Practice Credit" width="127">		
						<f:selectItem itemValue="1"/>
						<f:selectItem itemValue="2"/>
						<f:selectItem itemValue="3"/>
						<f:selectItem itemValue="4"/>
						<f:selectItem itemValue="5"/>
						<f:selectItem itemValue="6"/>
						<f:selectItem itemValue="7"/>
						<f:selectItem itemValue="8"/>
						<f:selectItem itemValue="9"/>					
			 		</rich:comboBox>
			 </td>
			 <td style=" width : 62px;"></td>
		 </tr>
		 <tr style=" height : 18px;">
			 <td>
			 </td>
			 <td>
			 </td>
			 <td>
			 </td>
			 <td>
			 </td>
		 </tr>
		 <tr>
		 <td></td>
		 	<td>		 	
		 		<h:inputTextarea style="font-family:Tahoma; width : 284px;"></h:inputTextarea>
		 	</td>
		 	<td>
		 	</td>
		 	<td>
		 	</td> 	
		 </tr>
		 <tr>
		 	<td>
		 	</td>
		 </tr>
	 </table>
</f:view>
</body>
</html>