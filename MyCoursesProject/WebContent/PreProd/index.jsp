<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich" %>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedule Main Page</title>
</head>
<body>
<f:view>
	<h:form>
		<rich:toolBar>
			<rich:dropDownMenu>
				<f:facet name="label">
					<h:outputText value="Scheduling"/>
				</f:facet>
				<rich:menuItem submitMode="none" >									
						<h:outputLink value="../Tests/TestScheduling.jsp" target="centerFrame">
							<h:outputText value="Manual Scheduling" />
						</h:outputLink>										
				</rich:menuItem>
				<rich:menuItem submitMode="none" >									
						<h:outputLink value="../Tests/autoscheduling.jsp" target="centerFrame">
							<h:outputText value="Auto Scheduling" />
						</h:outputLink>										
				</rich:menuItem>
			</rich:dropDownMenu>
			
			<rich:dropDownMenu>
				<f:facet name="label">
					<h:outputText value="Admin Operations"/>
				</f:facet>
				<rich:menuItem submitMode="none" >									
						<h:outputLink value="../PreProd/Classroom.jsp" target="centerFrame">
							<h:outputText value="Classroom" />
						</h:outputLink>										
				</rich:menuItem>
				<rich:menuItem submitMode="none" >									
						<h:outputLink value="../PreProd/Course.jsp" target="centerFrame">
							<h:outputText value="Course" />
						</h:outputLink>										
				</rich:menuItem>
				<rich:menuItem submitMode="none" >									
						<h:outputLink value="../PreProd/Deparment.jsp" target="centerFrame">
							<h:outputText value="Department" />
						</h:outputLink>										
				</rich:menuItem>
				<rich:menuItem submitMode="none" >									
						<h:outputLink value="../PreProd/Lecturer.jsp" target="centerFrame">
							<h:outputText value="Lecturer" />
						</h:outputLink>										
				</rich:menuItem>
				<rich:menuItem submitMode="none" >									
						<h:outputLink value="../PreProd/Syllabus.jsp" target="centerFrame">
							<h:outputText value="Syllabus" />
						</h:outputLink>										
				</rich:menuItem>
				<rich:menuItem submitMode="none" >									
						<h:outputLink value="../PreProd/SysUser.jsp" target="centerFrame">
							<h:outputText value="SysUser" />
						</h:outputLink>										
				</rich:menuItem>
				<rich:menuItem submitMode="none" >									
						<h:outputLink value="../PreProd/TypeofCourse.jsp" target="centerFrame">
							<h:outputText value="TypeofCourse" />
						</h:outputLink>										
				</rich:menuItem>
			</rich:dropDownMenu>
			<rich:dropDownMenu>
				<f:facet name="label">
					<h:outputText value="Help"/>
				</f:facet>
				<rich:menuItem submitMode="none" >									
						<h:outputLink value="#" target="centerFrame">
							<h:outputText value="About" />
						</h:outputLink>										
				</rich:menuItem>
			</rich:dropDownMenu>
		</rich:toolBar>	
		<br/>
		
		</h:form>
	<center><iframe name="centerFrame" width="1330px" height="600px" align="middle"></iframe></center>
</f:view>


</body>
</html>