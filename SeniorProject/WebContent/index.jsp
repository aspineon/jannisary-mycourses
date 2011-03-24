<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TEST</title>
</head>
<body>
<f:view>
<rich:comboBox>
		<f:selectItem itemValue="Oregon"/>            
        <f:selectItem itemValue="Pennsylvania"/>
        <f:selectItem itemValue="Rhode Island"/>
        <f:selectItem itemValue="South Carolina"/>
        <f:selectItem itemValue="Washington"/>
        <f:selectItem itemValue="NewYork"/>
        <f:selectItem itemValue="Corci Danilds 87"/>
</rich:comboBox>
</f:view>
</body>
</html>