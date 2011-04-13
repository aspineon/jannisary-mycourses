<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Senior Project (Scheduler)</title>
</head>
<body>
<f:view>

	<ui:composition xmlns="http://www.w3.org/1999/xhtml"
    xmlns:ui="http://java.sun.com/jsf/facelets"
    xmlns:h="http://java.sun.com/jsf/html"
    xmlns:f="http://java.sun.com/jsf/core"
    xmlns:a4j="http://richfaces.org/a4j"
    xmlns:rich="http://richfaces.org/rich">

    <style>
    .pic {
        margin-bottom: -4px;
        margin-right: 2px;
    }
    .search .rich-menu-item-hover{
        background-color: transparent;
        border-color: transparent;
    }
    </style>
    
    <h:form>

        <rich:toolBar>

            <rich:dropDownMenu>
                <f:facet name="label"> 
                    <h:panelGroup>                       
                        <h:outputText value="Operations"/>
                    </h:panelGroup>
                </f:facet>
                <rich:menuItem submitMode="ajax" value="New Lecture"
                    action="#{ddmenu.doNew}" icon="/images/icons/create_doc.gif">
                </rich:menuItem>
                <rich:menuItem submitMode="ajax" value="Open Lecture"
                    action="#{ddmenu.doOpen}" icon="/images/icons/open.gif" />
                <rich:menuGroup value="Save As...">
                    <rich:menuItem submitMode="ajax" value="Save" 
                        action="#{ddmenu.doSave}" />
                    <rich:menuItem submitMode="ajax" value="Save All"
                        action="#{ddmenu.doSaveAll}">
                        <!--<f:facet name="icon">
                            <h:graphicImage value="/images/icons/save_all.gif" />
                        </f:facet>-->
                    </rich:menuItem>
                </rich:menuGroup>
                <rich:menuItem submitMode="ajax" value="Close"
                    action="#{ddmenu.doClose}" />
                <rich:menuSeparator id="menuSeparator11" />
                <rich:menuItem submitMode="ajax" value="Exit"
                    action="#{ddmenu.doExit}" />

            </rich:dropDownMenu>

			<rich:dropDownMenu>

                <f:facet name="label">
                    <h:panelGrid cellpadding="0" cellspacing="0" columns="2"
                        style="vertical-align:middle">
                        <h:outputText value="Scheduling" />
                    </h:panelGrid>
                </f:facet>

                <rich:menuItem submitMode="none"
                    onclick="document.location.href='./manual_scheduling.jsp'" target="myframe">
                    <h:outputLink value="./manual_scheduling.jsp">
                        <h:outputText value="Manual Scheduling"></h:outputText>
                    </h:outputLink>
                </rich:menuItem>

                <rich:menuItem submitMode="none"
                    onclick="document.location.href='./auto_scheduling.jsp'" target="myframe">
                    <h:outputLink
                        value="./auto_scheduling.jsp">
                        <h:outputText value="Auto Scheduling"></h:outputText>
                    </h:outputLink>
                </rich:menuItem>

            </rich:dropDownMenu>

            <rich:dropDownMenu>

                <f:facet name="label">
                    <h:panelGrid cellpadding="0" cellspacing="0" columns="2"
                        style="vertical-align:middle">
                        <h:outputText value="Links" />
                    </h:panelGrid>
                </f:facet>

                <rich:menuItem submitMode="none"
                    onclick="document.location.href='http://labs.jboss.com/jbossrichfaces/'">
                    <h:outputLink value="http://labs.jboss.com/jbossrichfaces/">
                        <h:outputText value="RichFaces Home Page"></h:outputText>
                    </h:outputLink>
                </rich:menuItem>

                <rich:menuItem submitMode="none"
                    onclick="document.location.href='http://jboss.com/index.html?module=bb&amp;op=viewforum&amp;f=261'">
                    <h:outputLink
                        value="http://jboss.com/index.html?module=bb&amp;op=viewforum&amp;f=261">
                        <h:outputText value="RichFaces Forum"></h:outputText>
                    </h:outputLink>
                </rich:menuItem>

            </rich:dropDownMenu>
            
            <rich:dropDownMenu>

                <f:facet name="label">
                    <h:panelGrid cellpadding="0" cellspacing="0" columns="2"
                        style="vertical-align:middle">
                        <h:outputText value="Admin Panel" />
                    </h:panelGrid>
                </f:facet>
                <rich:menuItem submitMode="none"
                    onclick="./course.jsp'" target="myframe">
                    <h:outputLink
                        value="./course.jsp" target="myframe">
                        <h:outputText value="Course Operation"></h:outputText>
                    </h:outputLink>
                </rich:menuItem>

                <rich:menuItem submitMode="none"
                    onclick="./lecturer.jsp'" target="myframe">
                    <h:outputLink
                        value="./lecturer.jsp" target="myframe">
                        <h:outputText value="Lecturer Operation"></h:outputText>
                    </h:outputLink>
                </rich:menuItem>
                
                <rich:menuItem submitMode="none"
                    onclick="./SysUser.jsp'" target="myframe">
                    <h:outputLink
                        value="./SysUser.jsp" target="myframe">
                        <h:outputText value="User Operation"></h:outputText>
                    </h:outputLink>
                </rich:menuItem>

				<rich:menuItem submitMode="none"
                    onclick="./Syllabus.jsp'" target="myframe">
                    <h:outputLink
                        value="./Syllabus.jsp" target="myframe">
                        <h:outputText value="Syllabus Operation"></h:outputText>
                    </h:outputLink>
                </rich:menuItem>

            </rich:dropDownMenu>
            
            
            <rich:toolBarGroup location="right">
                <rich:dropDownMenu value="Search" direction="bottom-left" jointPoint="br" styleClass="search">
                    <rich:menuItem submitMode="none">
                        <h:inputText value="" id="searchinput"/>
                        <button type="button">Search</button>
                    </rich:menuItem>
                    <rich:menuItem value="Enter your search criteria here" disabled="true"></rich:menuItem>
                </rich:dropDownMenu>
            </rich:toolBarGroup>
        </rich:toolBar>
    </h:form>
    <rich:spacer width="1" height="5"/>
    <!-- <a4j:outputPanel ajaxRendered="true">
        <h:outputText value="Current Selection: "></h:outputText>
        <h:outputText style="font-weight:bold" value="#{ddmenu.current}"></h:outputText>
    </a4j:outputPanel>
    -->
    <br/>  <br/>
    <iframe name="myframe" style="width:98%;height: 75%;" align="middle"></iframe>
    <br />
    <rich:spacer width="1" height="25" />

</ui:composition>
</f:view>
</body>
</html>