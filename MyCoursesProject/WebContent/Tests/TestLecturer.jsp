<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>INSERT COURSE</title>
</head>
<body>

<f:view>

<h:form>
<rich:tabPanel switchType="ajax">
	<rich:tab label="Add Lecturer">
		<h:inputText value="#{lecturerBean.lecturerName}">
			<f:validateLength minimum="1" maximum="50"/>
		</h:inputText>	
		
		<h:commandButton value="Add Lecturer" action="#{lecturerBean.AddLecturer}">
			<a4j:support event="onclick"/>
		</h:commandButton>
	</rich:tab>
	<rich:tab label="DataTable TestTab">
		
	</rich:tab>
	<rich:tab label="Delete Lecturer">
		
	</rich:tab>
</rich:tabPanel>
</h:form>
</f:view>


</body>
</html>