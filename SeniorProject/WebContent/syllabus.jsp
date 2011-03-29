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
	<rich:tabPanel switchType="Client">
	<rich:tab label="Add Syllabus">
            <rich:panel>
            	<table bgcolor="silver">
					<tr style=" height : 19px;">
						<td>
						</td>
					</tr>
					<tr>
					<td style=" width : 29px;"></td>
						<td>
							<rich:comboBox id="lecturerSyllabusComboBox" value="Lecturer">
									<f:selectItem/>
							</rich:comboBox>								
						</td>
						<td style=" width : 57px;">					
						</td>
						<td>
							<rich:comboBox id="courseSyllabusComboBox" value="Course">
									<f:selectItem />
							</rich:comboBox>
						</td>
						<td style=" width : 21px;">
						</td>
						<td>
							<a4j:commandButton action="#{syllabusBean}" value="Submit" style=" width : 88px;">				
							</a4j:commandButton>
						</td>
						<td style=" width : 25px;">
						</td>
					</tr>
					<tr style=" height : 17px;">
						<td>
						</td>
						<td style=" width : 44px;">
						</td>
						<td>
						</td>
						<td>
						</td>
						<td>
						</td>
						<td>
						</td>
						<td>
						</td>
					</tr>
					
					<tr style=" height : 17px;">
						<td>
						</td>
						<td style=" width : 44px;">
						</td>
						<td>
						</td>
						<td>
						</td>
						<td>
						</td>
						<td>
						</td>
						<td>
						</td>
					</tr>
				</table>
            </rich:panel>
        </rich:tab>
        
        <rich:tab label="Cancel Syllabus">
            <table>
	            <tr>
		            <td>
						<h:outputLabel value="Lecture Name : "></h:outputLabel>	            	
		            </td>
		            <td>
		            </td>
		            <td>
		            	<h:inputText id="syllabusChangeLectureTextField" ></h:inputText>
		            </td>
	            </tr>
	            <tr>
		            <td>
		            </td>
	            </tr>
	            <tr>
		            <td>
						<h:outputLabel value="Lecturer Name : "></h:outputLabel>	     						  	
		            </td>
		            <td>
		            </td>
		            <td>
		            	<h:inputText id="syllabusChangeTeacherTextField"></h:inputText>
		            </td>
	            </tr>
	            <tr>
		            <td>
		            </td>
	            </tr>
	            <tr>
		            <td>
		            	<h:commandButton action="#{syllabusBean.cancelSyllabus}" id="changeSyllabusSubmitButton" value=	"Submit">
		            	</h:commandButton>
		            </td>
	            </tr>
            </table>
        </rich:tab>
</rich:tabPanel>

</f:view>
</body>
</html>