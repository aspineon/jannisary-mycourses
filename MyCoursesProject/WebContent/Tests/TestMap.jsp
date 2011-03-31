<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<f:view>

 <rich:virtualEarth  style="width:800px;" id="vm" lat="48.833" lng="2.40" dashboardSize="Normal"  zoom="11" mapStyle="Hybrid" var="map" />

<h:form>
<h:inputText value="#{personTest.name}">
	<a4j:support event="onkeyup" reRender="test" limitToList="true">
	
</a4j:support>
</h:inputText>
<h:outputText id="test" value="#{personTest.name}"/>

</h:form>

</f:view>

</body>
</html>