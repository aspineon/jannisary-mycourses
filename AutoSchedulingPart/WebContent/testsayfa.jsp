<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>
	<rich:panel header="Friends">
				<a4j:form>
					<rich:dataTable value="#{friendBean.friends}" var="friend"
						ajaxKeys="#{friendBean.rowsToUpdate}">

						<a4j:support event="onRowClick"
							action="#{friendBean.rowClickAction}" reRender="checkBox">
							<f:setPropertyActionListener
								target="#{friendBean.selectedFriend}" value="#{friend}" />
						</a4j:support>

						<rich:column id="selected">
							<h:selectBooleanCheckbox id="checkBox" value="#{friend.selected}"></h:selectBooleanCheckbox>
						</rich:column>

						<rich:column id="firstName">
							<f:facet name="header">
								<h:outputText value="First Name" />
							</f:facet>
							<h:outputText value="#{friend.firstName}" />
						</rich:column>

						<rich:column id="lastName">
							<f:facet name="header">
								<h:outputText value="Last Name" />
							</f:facet>
							<h:outputText value="#{friend.lastName}" />
						</rich:column>

					</rich:dataTable>
				</a4j:form>
			</rich:panel>
</f:view>
</body>
</html>