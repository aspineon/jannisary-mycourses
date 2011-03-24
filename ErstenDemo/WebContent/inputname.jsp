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
<title>Manual Scheduling</title>
</head>
<body>
<f:view>
	<h1>
      <h:outputText style="font-family:Tahoma; font-size:23px; color:blue" value="#{msg.inputname_header}"/>

     </h1>
     <p>
       <h:messages style="color:darkred"/>
     </p>
     
          
     <h:form id="helloForm">
       
       <table>
       		<tr>
       			<td>
       				<h:outputText value="Select a Class :"></h:outputText>
       			</td>
       			<td>
	       			<rich:comboBox id="classComboBox"  value="Select a Class" width="127">		
						<f:selectItem itemValue="1"/>
						<f:selectItem itemValue="2"/>
						<f:selectItem itemValue="3"/>
						<f:selectItem itemValue="4"/>					
			 		</rich:comboBox>
       			</td>
       		</tr>
       </table>
       
	<br>

	<h:outputText value="#{msg.social}"></h:outputText>
      <h:inputText value="#{personBean.personName}" />
      <h:commandButton id="button1" action="greeting" value="#{msg.button_text}" />
      <br>
      
     
      
     
     </h:form>
   </f:view>
</body>
</html>