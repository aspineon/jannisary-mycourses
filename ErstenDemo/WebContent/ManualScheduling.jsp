<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j" %>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manual Scheduling</title>
</head>
<body>

	<ui:composition xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core">

    <style>
        .panelc {
            width:25%;
            vertical-align:top;         
        }
        
        .dropTargetPanel {
            width: 90%;
        }

        .footerClass {
            text-align: center;
            padding-top: 5px;
        }

    </style>

    <rich:dragIndicator id="indicator" />

    <h:form id="form">

        <h:panelGrid columnClasses="panelc" columns="4" width="100%">

            <rich:panel style="width:133px"> 
                <f:facet name="header">
                    <h:outputText value="Source List" />
                </f:facet>
                <h:dataTable id="src" columns="1" value="#{dndBean.frameworks}"
                    var="fm" footerClass="footerClass">

                    <h:column>
                        <a4j:outputPanel style="width:100px;border:1px solid gray;padding:2px"
                            layout="block">
                            <rich:dragSupport dragIndicator=":indicator"
                                dragType="#{fm.family}" dragValue="#{fm}">
                                <rich:dndParam name="label" value="#{fm.name}" />
                            </rich:dragSupport>
                            <h:outputText value="#{fm.name}"></h:outputText>
                        </a4j:outputPanel>
                    </h:column>
                    <f:facet name="footer">
                        <a4j:commandButton action="#{dndBean.reset}" value="Start Over" reRender="src,phptable,cftable,dnettable"/>
                    </f:facet>
                </h:dataTable>
            </rich:panel>

            <rich:panel styleClass="dropTargetPanel">
                <f:facet name="header">
                    <h:outputText value="PHP Frameworks" />
                </f:facet>
                <rich:dropSupport id="php" acceptedTypes="PHP" dropValue="PHP"
                    dropListener="#{eventBean.processDrop}" reRender="phptable, src">
                </rich:dropSupport>

                <h:dataTable id="phptable" columns="1" value="#{dndBean.containerPHP}" var="fm">
                    <h:column>
                        <h:outputText value="#{fm.name}"></h:outputText>
                    </h:column>
                </h:dataTable>


            </rich:panel>

            <rich:panel styleClass="dropTargetPanel">
                <f:facet name="header">
                    <h:outputText value=".NET Frameworks" />
                </f:facet>
                <rich:dropSupport id="dnet" acceptedTypes="DNET" dropValue="DNET"
                    dropListener="#{eventBean.processDrop}" reRender="dnettable, src">
                </rich:dropSupport>

                <h:dataTable id="dnettable" columns="1" value="#{dndBean.containerDNET}" var="fm">
                    <h:column>
                        <h:outputText value="#{fm.name}"></h:outputText>
                    </h:column>
                </h:dataTable>


            </rich:panel>

            <rich:panel styleClass="dropTargetPanel">
                <f:facet name="header">
                    <h:outputText value="ColdFusion Frameworks" />
                </f:facet>
                <rich:dropSupport id="cf" acceptedTypes="CF" dropValue="CF"
                    dropListener="#{eventBean.processDrop}" reRender="cftable, src">
                </rich:dropSupport>

                <h:dataTable id="cftable" columns="1" value="#{dndBean.containerCF}" var="fm">
                    <h:column>
                        <h:outputText value="#{fm.name}"></h:outputText>
                    </h:column>
                </h:dataTable>
            </rich:panel>
        </h:panelGrid>

    </h:form>
     <a4j:outputPanel ajaxRendered="true">
        <h:messages></h:messages>
    </a4j:outputPanel>
</ui:composition>

</body>
</html>