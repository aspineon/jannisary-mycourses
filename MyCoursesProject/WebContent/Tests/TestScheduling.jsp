<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <style>
        .panelc {
            width:25%;
            vertical-align:top;         
        }
        
        .dropTargetPanel {
            width: 80%;
        }

        .footerClass {
            text-align: center;
            pacategorying-top: 5px;
        }

    </style>
</head>
<body>
<f:view>
<rich:dragIndicator id="indicator"/>


 <rich:panel style="pacategorying:0" headerClass="outpanelHeader">
        <f:facet name="header">
            <rich:spacer height="20"/>
        </f:facet>
        <h2 align="center"><h:outputText value="Manual Scheduling" /></h2>
        <h:panelGrid columns="3" columnClasses="gridContent">
       
       <h:form>
        <h:panelGrid columns="2">
            <a4j:outputPanel id="label">
                <h:outputText value="Choose Grade:"/>
            </a4j:outputPanel>  
            <a4j:outputPanel id="items">
                <rich:inplaceSelect value="#{manuelSchedulingUtilBean.strGrade}" valueChangeListener="#{manuelSchedulingUtilBean.selectionChangedGradeCombo}" defaultLabel="Click here to select">
                    <f:selectItems value="#{manuelSchedulingUtilBean.listGrade}"/>
                    <a4j:support event="onviewactivated" reRender="items, label" ajaxSingle="true"/>
                </rich:inplaceSelect>
            </a4j:outputPanel>
             <h:outputText value="Choose Semester:"/>
            <rich:inplaceSelect value="#{manuelSchedulingUtilBean.semester}" valueChangeListener="#{manuelSchedulingUtilBean.selectionChangedSemesterCombo}" defaultLabel="Click here to select">
                <f:selectItems value="#{manuelSchedulingUtilBean.listSemester}"/>
                <a4j:support event="onviewactivated" reRender="items, label" ajaxSingle="true"/>
            </rich:inplaceSelect>
        </h:panelGrid>
    </h:form>
       
       <h:form id="form1">
	        <rich:panel style="width:133px"> 
	                <h:panelGrid columns="1" style=" width : 86px;">
	                
	                	
	                <h:dataTable id="src" value="#{manuelSchedulingUtilBean.allSyllabuses}" var="category" footerClass="footerClass">
	                    <h:column>
	                        <a4j:outputPanel style="width:100px;border:1px solid gray;pacategorying:2px" layout="block">
	                            <rich:dragSupport dragIndicator=":indicator" dragType="Test" dragValue="#{category}">
	                                <rich:dndParam name="label" value="#{category.course.courseName}" />
	                            </rich:dragSupport>
	                            <h:outputText value="#{category.course.courseName}"></h:outputText>
	                        </a4j:outputPanel>
	                    </h:column>
	                </h:dataTable>
	                </h:panelGrid>
	                
	                <f:facet name="header">
	                    <h:outputText value="Course List" />
	                </f:facet>
	        </rich:panel>
       </h:form>
       <h:form id="form">
            <h:panelGrid columns="5" columnClasses="gridContent">
            <rich:panel bodyClass="dropTargetPanel">
            <f:facet name="header">
                    <h:outputText value="Monday" />
                </f:facet>
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            <f:facet name="header">
                    <h:outputText value="Tuesday" />
                </f:facet>
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            <f:facet name="header">
                    <h:outputText value="Wednesday" />
                </f:facet>
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            <f:facet name="header">
                    <h:outputText value="Thursday" />
                </f:facet>
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            <f:facet name="header">
                    <h:outputText value="Friday" />
                </f:facet>
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            	<f:facet name="header">
                    <h:outputText value="08:00-09:00" />
                </f:facet>
                
                <rich:dropSupport id="MondayDropSupportId00" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="phptable, src" actionListener="#{demoDragDropBean.testAction}">
                </rich:dropSupport>

                <h:dataTable id="phptable" value="#{demoDragDropBean.containerCME}"  var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
                <f:facet name="header">
                    <h:outputText value="08:00-09:00" />
                </f:facet>
                    
                <rich:dropSupport id="TuesdayDropSupportId" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="TuesdayTable, src">
                </rich:dropSupport>

                <h:dataTable id="TuesdayTable" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
                <f:facet name="header">
                    <h:outputText value="08:00-09:00" />
                </f:facet>
                    
                    <rich:dropSupport id="WednesdayDropSupportId" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="WednesdayTable, src">
                </rich:dropSupport>

                <h:dataTable id="WednesdayTable" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
             <rich:panel bodyClass="dropTargetPanel">
                <f:facet name="header">
                    <h:outputText value="08:00-09:00" />
                </f:facet>
                    
                    <rich:dropSupport id="ThursdayDropSupportId" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="ThursdayTable, src">
                </rich:dropSupport>

                <h:dataTable id="ThursdayTable" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="08:00-09:00" />
                </f:facet>
                    
                    <rich:dropSupport id="FridayDropSupportId" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="FridayTable, src">
                </rich:dropSupport>

                <h:dataTable id="FridayTable" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="09:00-10:00" />
                </f:facet>
                    
                    <rich:dropSupport id="MondayDropSupportId1" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="MondayTable1, src">
                </rich:dropSupport>

                <h:dataTable id="MondayTable1" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="09:00-10:00" />
                </f:facet>
                    
                    <rich:dropSupport id="TuesdayDropSupportId1" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="TuesdayTable1, src">
                </rich:dropSupport>

                <h:dataTable id="TuesdayTable1" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="09:00-10:00" />
                </f:facet>
                    
                    <rich:dropSupport id="WednesdayDropSupportId1" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="WednesdayTable1, src">
                </rich:dropSupport>

                <h:dataTable id="WednesdayTable1" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="09:00-10:00" />
                </f:facet>
                    
                    <rich:dropSupport id="ThursdayDropSupportId1" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="ThursdayTable1, src">
                </rich:dropSupport>

                <h:dataTable id="ThursdayTable1" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="09:00-10:00" />
                </f:facet>
                    
                    <rich:dropSupport id="FridayDropSupportId1" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="FridayTable1, src">
                </rich:dropSupport>

                <h:dataTable id="FridayTable1" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="10:00-11:00" />
                </f:facet>
                    
                    <rich:dropSupport id="MondayDropSupportId2" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="MondayTable2, src">
                </rich:dropSupport>

                <h:dataTable id="MondayTable2" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="10:00-11:00" />
                </f:facet>
                    
                    <rich:dropSupport id="TuesdayDropSupportId2" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="TuesdayTable2, src">
                </rich:dropSupport>

                <h:dataTable id="TuesdayTable2" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="10:00-11:00" />
                </f:facet>
                    
                    <rich:dropSupport id="WednesdayDropSupportId2" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="WednesdayTable2, src">
                </rich:dropSupport>

                <h:dataTable id="WednesdayTable2" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="10:00-11:00" />
                </f:facet>
                    
                    <rich:dropSupport id="ThursdayDropSupportId2" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="ThursdayTable2, src">
                </rich:dropSupport>

                <h:dataTable id="ThursdayTable2" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="10:00-11:00" />
                </f:facet>
                    
                    <rich:dropSupport id="FridayDropSupportId2" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="FridayTable2, src">
                </rich:dropSupport>

                <h:dataTable id="FridayTable2" value="#{demoDragDropBean.containerCME}" var="category">
                    <h:column>
                        <h:outputText value="#{category.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            </h:panelGrid>
        </h:form>
        </h:panelGrid>
    </rich:panel>
</f:view>
</body>
</html>