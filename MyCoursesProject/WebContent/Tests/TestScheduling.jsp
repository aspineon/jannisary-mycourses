
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
  
  		.footerPanelStyle{
  			width:300px;
  			height:150px;
  			vertical-align:top;	
  		}
  
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
        
          .div_near_spacer {
                border-top-width: 0px;
                border-left-width: 0px;
                font-size: 0px;
                border-bottom-width: 0px;
                height: 2px;
                border-right-width: 0px
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
        <h:outputText value="#{manuelSchedulingUtilBean.errorLabel}" />
        <h:panelGrid columns="4" columnClasses="gridContent">
       
       
       <h:panelGrid bgcolor="Navy">
       <h:form id="form1">
       
       <rich:panel  id="PanelGradeAndSemesterSelectPanel" style="width:130px">
       		<h:form id="FormGradeAndSemesterSelectPanel" >
        		<h:panelGrid columns="1">
            		<h:outputText value="Add Operation" />
            		
            		<rich:comboBox id="yearToSaveForAddOperation" value="Select Year" valueChangeListener ="#{manuelSchedulingUtilBean.selectionChangedNextTwoYearComboForAddOperation}" width="110">
			        				<f:selectItems value="#{manuelSchedulingUtilBean.listNextTwoYearsToSave}"/>
			        				<a4j:support event="onselect" ajaxSingle="true"/>
			        			</rich:comboBox>
            		<rich:comboBox id="courseGradeComboBoxId" value="Select Course Grade" valueChangeListener ="#{manuelSchedulingUtilBean.selectionChangedGradeCombo}" width="110">
	        				<f:selectItems value="#{manuelSchedulingUtilBean.listGrade}"/>
	        				<a4j:support event="onselect" ajaxSingle="true"/>
	        		</rich:comboBox>
	        		<rich:comboBox id="courseSemesterComboBoxId" value="Select Course Semester" valueChangeListener ="#{manuelSchedulingUtilBean.selectionChangedSemesterCombo}" width="110">
	        				<f:selectItems value="#{manuelSchedulingUtilBean.listSemester}"/>
	        				<a4j:support event="onselect" ajaxSingle="true"/>
	        		</rich:comboBox>
            		<h:commandButton value="Get Course" action="#{manuelSchedulingUtilBean.clickGetCoursesButton}" style=" width : 110px; height : 20px;">
						<a4j:support event="onclick" reRender="outputPanelLabListId,outPutPanelCourseListId"/>
					</h:commandButton>
        		</h:panelGrid>
        	</h:form>
        		
       </rich:panel>
       <!--
       		Paneller arasına boşluklar eklendi.
       -->
       <div class="div_near_spacer" ></div>
         	<rich:spacer  width="1" height="30"/>
        <div class="div_near_spacer" ></div>
       </h:form>
       <h:form>
       		<rich:panel>
       			<h:form id="FormGradeAndSemesterSelectPanel2" >
	        	<h:panelGrid>
	        		<h:outputText value="Edit Operation" />
		       		<rich:comboBox id="yearComboBoxIdForEdit" value="Select Year" valueChangeListener ="#{manuelSchedulingUtilBean.selectionChangedYearComboForEdit}" width="110">
			        				<f:selectItems value="#{manuelSchedulingUtilBean.listYear}"/>
			        				<a4j:support event="onselect" ajaxSingle="true"/>
			        </rich:comboBox>
			        <rich:comboBox id="semesterComboBoxIdForEdit" value="Select Semester" valueChangeListener ="#{manuelSchedulingUtilBean.selectionChangedSemesterCombo}" width="110">
			        		<f:selectItems value="#{manuelSchedulingUtilBean.listSemester}"/>
			        		<a4j:support event="onselect" ajaxSingle="true"/>
			        </rich:comboBox>
			        <rich:comboBox id="gradeComboBoxIdForEdit" value="Select Grade" valueChangeListener ="#{manuelSchedulingUtilBean.selectionChangedGradeCombo}" width="110">
			        				<f:selectItems value="#{manuelSchedulingUtilBean.listGrade}"/>
			        				<a4j:support event="onselect" ajaxSingle="true"/>
			        </rich:comboBox>
			        <h:commandButton id="buttonForEditSchedule" value="Start Editing" action="#{manuelSchedulingUtilBean.clickGetCoursesButtonForEdit}" style=" width : 110px; height : 20px;">
								<a4j:support event="onclick" reRender="outputPanelLabListId,outPutPanelCourseListId"/>
					</h:commandButton>
		       </h:panelGrid>
   	 		</h:form>
       		</rich:panel>
       </h:form>
       
       <h:form>
       		<rich:panel>
       			<h:form id="FormGradeAndSemesterSelectPanel3">
	        	<h:panelGrid>
	        		<h:outputText value="Reset Operation"/>
		       		<rich:comboBox id="dayComboForReset" value="Select Day" valueChangeListener ="#{manuelSchedulingUtilBean.selectionChangedDayComboForReset}" width="110">
			        				<f:selectItems value="#{manuelSchedulingUtilBean.listDay}"/>
			        				<a4j:support event="onselect" ajaxSingle="true"/>
			        </rich:comboBox>
			        <rich:comboBox id="hourComboForReset" value="Select Hour" valueChangeListener ="#{manuelSchedulingUtilBean.selectionChangedHourComboForReset}" width="110">
			        		<f:selectItems value="#{manuelSchedulingUtilBean.listHour}"/>
			        		<a4j:support event="onselect" ajaxSingle="true"/>
			        </rich:comboBox>
			        <h:commandButton id="buttonForResetCoordinate" value="Reset" action="#{manuelSchedulingUtilBean.clickResetCoordinateButton}" style=" width : 110px; height : 20px;">
								<a4j:support event="onclick" reRender="outputPanelLabListId,outPutPanelCourseListId"/>
					</h:commandButton>
		       </h:panelGrid>
   	 		</h:form>
       		</rich:panel>
       </h:form>
       
       <rich:panel id="courseListPanel" style="width:133px"> 
	                <h:panelGrid columns="1" style=" width : 86px;">
	                
	                <h:dataTable id="src" value="#{manuelSchedulingUtilBean.courseList}" var="courseList" footerClass="footerClass">
	                    <h:column>
	                        <a4j:outputPanel id="outPutPanelCourseListId" style="width:100px;border:1px solid gray;pacategorying:2px" layout="block">
	                            <rich:dragSupport dragIndicator=":indicator" dragType="Test" dragValue="#{courseList}">
	                                <rich:dndParam name="label" value="#{courseList.courseTheoricOrPraticName}" />
	                            </rich:dragSupport>
	                            <h:outputText value="#{courseList.courseTheoricOrPraticName}"></h:outputText>
	                        </a4j:outputPanel>
	                    </h:column>
	                </h:dataTable>
	                </h:panelGrid>
	                
	                <f:facet name="header">
	                    <h:outputText value="Course List" />
	                </f:facet>
	        </rich:panel>
        <!--
       		Paneller arasına boşluklar eklendi.
       -->
        <div class="div_near_spacer" ></div>
         	<rich:spacer  width="1" height="30" title="Here is a spacer..."/>
        <div class="div_near_spacer" ></div>
       
       <h:form id="form2">
	        <rich:panel id="labListPanel" style="width:133px"> 
	                <h:panelGrid columns="1" style=" width : 86px;">
	                
	                	
	                <h:dataTable id="src" value="#{manuelSchedulingUtilBean.labList}" var="labList" footerClass="footerClass">
	                    <h:column>
	                        <a4j:outputPanel id="outputPanelLabListId" style="width:100px;border:1px solid gray;pacategorying:2px" layout="block">
	                            <rich:dragSupport dragIndicator=":indicator" dragType="Test" dragValue="#{labList}">
	                                <rich:dndParam name="label" value="#{labList.courseTheoricOrPraticName}" />
	                            </rich:dragSupport>
	                            <h:outputText value="#{labList.courseTheoricOrPraticName}"></h:outputText>
	                        </a4j:outputPanel>
	                    </h:column>
	                </h:dataTable>
	                </h:panelGrid>
	                
	                <f:facet name="header">
	                    <h:outputText value="Lab List" />
	                </f:facet>
	        	</rich:panel>
	        	
	        	<!--
       		Paneller arasına boşluklar eklendi.
       -->
        <div class="div_near_spacer" ></div>
         	<rich:spacer  width="1" height="30" title="Here is a spacer..."/>
        <div class="div_near_spacer" ></div>
	     <h:form id="form3">  	
	        	
	     </h:form>
       		</h:form>
       </h:panelGrid>
       
      
       
       <h:form id="form">
       <h:panelGrid>
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
                
                <rich:dropSupport id="MondayDropSupportId00" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="phptable, src, errorPanel" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="phptable" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt11}"></h:outputText>
                    </h:column>
                </h:dataTable>
            </rich:panel>
          
        	
            <rich:panel bodyClass="dropTargetPanel">
                <f:facet name="header">
                    <h:outputText value="08:00-09:00" />
                </f:facet>
                    
                <rich:dropSupport id="TuesdayDropSupportId10" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="TuesdayTable, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="TuesdayTable" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt21}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
                <f:facet name="header">
                    <h:outputText value="08:00-09:00" />
                </f:facet>
                    
                    <rich:dropSupport id="WednesdayDropSupportId20" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="WednesdayTable, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="WednesdayTable" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt31}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
             <rich:panel bodyClass="dropTargetPanel">
                <f:facet name="header">
                    <h:outputText value="08:00-09:00" />
                </f:facet>
                    
                    <rich:dropSupport id="ThursdayDropSupportId30" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="ThursdayTable, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="ThursdayTable" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt41}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="08:00-09:00" />
                </f:facet>
                    
                    <rich:dropSupport id="FridayDropSupportId40" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="FridayTable, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="FridayTable" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt51}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="09:00-10:00" />
                </f:facet>
                    
                    <rich:dropSupport id="MondayDropSupportId01" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="MondayTable1, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="MondayTable1" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt12}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="09:00-10:00" />
                </f:facet>
                    
                    <rich:dropSupport id="TuesdayDropSupportId11" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="TuesdayTable1, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="TuesdayTable1" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt22}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="09:00-10:00" />
                </f:facet>
                    
                    <rich:dropSupport id="WednesdayDropSupportId21" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="WednesdayTable1, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="WednesdayTable1" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt32}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="09:00-10:00" />
                </f:facet>
                    
                    <rich:dropSupport id="ThursdayDropSupportId31" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="ThursdayTable1, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="ThursdayTable1" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt42}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="09:00-10:00" />
                </f:facet>
                    
                    <rich:dropSupport id="FridayDropSupportId41" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="FridayTable1, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="FridayTable1" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt52}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="10:00-11:00" />
                </f:facet>
                    
                    <rich:dropSupport id="MondayDropSupportId02" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="MondayTable2, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="MondayTable2" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt13}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="10:00-11:00" />
                </f:facet>
                    
                    <rich:dropSupport id="TuesdayDropSupportId12" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="TuesdayTable2, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="TuesdayTable2" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt23}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="10:00-11:00" />
                </f:facet>
                    
                    <rich:dropSupport id="WednesdayDropSupportId22" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="WednesdayTable2, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="WednesdayTable2" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt33}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="10:00-11:00" />
                </f:facet>
                    
                    <rich:dropSupport id="ThursdayDropSupportId32" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="ThursdayTable2, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="ThursdayTable2" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt43}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="10:00-11:00" />
                </f:facet>
                    
                    <rich:dropSupport id="FridayDropSupportId42" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="FridayTable2, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="FridayTable2" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt53}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="11:00-12:00" />
                </f:facet>
                    
                    <rich:dropSupport id="MondayDropSupportId03" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="MondayTable3, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="MondayTable3" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt14}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="11:00-12:00" />
                </f:facet>
                    
                    <rich:dropSupport id="TuesdayDropSupportId13" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="TuesdayTable3, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="TuesdayTable3" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt24}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="11:00-12:00" />
                </f:facet>
                    
                    <rich:dropSupport id="WednesdayDropSupportId23" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="WednesdayTable3, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="WednesdayTable3" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt34}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="11:00-12:00" />
                </f:facet>
                    
                    <rich:dropSupport id="ThursdayDropSupportId33" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="ThursdayTable3, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="ThursdayTable3" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt44}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="11:00-12:00" />
                </f:facet>
                    
                    <rich:dropSupport id="FridayDropSupportId43" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="FridayTable3, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="FridayTable3" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt54}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="13:00-14:00" />
                </f:facet>
                    
                    <rich:dropSupport id="MondayDropSupportId04" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="MondayTable4, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="MondayTable4" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt15}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="13:00-14:00" />
                </f:facet>
                    
                    <rich:dropSupport id="TuesdayDropSupportId14" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="TuesdayTable4, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="TuesdayTable4" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt25}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="13:00-14:00" />
                </f:facet>
                    
                    <rich:dropSupport id="WednesdayDropSupportId24" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="WednesdayTable4, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="WednesdayTable4" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt35}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="13:00-14:00" />
                </f:facet>
                    
                    <rich:dropSupport id="ThursdayDropSupportId34" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="ThursdayTable4, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="ThursdayTable4" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt45}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="13:00-14:00" />
                </f:facet>
                    
                    <rich:dropSupport id="FridayDropSupportId44" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="FridayTable4, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="FridayTable4" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt55}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
             <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="14:00-15:00" />
                </f:facet>
                    
                    <rich:dropSupport id="MondayDropSupportId05" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="MondayTable5, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="MondayTable5" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt16}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="14:00-15:00" />
                </f:facet>
                    
                    <rich:dropSupport id="TuesdayDropSupportId15" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="TuesdayTable5, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="TuesdayTable5" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt26}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="14:00-15:00" />
                </f:facet>
                    
                    <rich:dropSupport id="WednesdayDropSupportId25" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="WednesdayTable5, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="WednesdayTable5" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt36}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="14:00-15:00" />
                </f:facet>
                    
                    <rich:dropSupport id="ThursdayDropSupportId35" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="ThursdayTable5, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="ThursdayTable5" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt46}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="14:00-15:00" />
                </f:facet>
                    
                    <rich:dropSupport id="FridayDropSupportId45" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="FridayTable5, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="FridayTable5" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt56}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="15:00-16:00" />
                </f:facet>
                    
                    <rich:dropSupport id="MondayDropSupportId06" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="MondayTable6, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="MondayTable6" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt17}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="15:00-16:00" />
                </f:facet>
                    
                    <rich:dropSupport id="TuesdayDropSupportId16" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="TuesdayTable6, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="TuesdayTable6" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt27}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="15:00-16:00" />
                </f:facet>
                    
                    <rich:dropSupport id="WednesdayDropSupportId26" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="WednesdayTable6, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="WednesdayTable6" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt37}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="15:00-16:00" />
                </f:facet>
                    
                    <rich:dropSupport id="ThursdayDropSupportId36" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="ThursdayTable6, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="ThursdayTable6" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt47}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="15:00-16:00" />
                </f:facet>
                    
                    <rich:dropSupport id="FridayDropSupportId46" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="FridayTable6, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="FridayTable6" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt57}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="16:00-17:00" />
                </f:facet>
                    
                    <rich:dropSupport id="MondayDropSupportId07" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="MondayTable7, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="MondayTable7" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt18}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="16:00-17:00" />
                </f:facet>
                    
                    <rich:dropSupport id="TuesdayDropSupportId17" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="TuesdayTable7, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="TuesdayTable7" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt28}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="16:00-17:00" />
                </f:facet>
                    
                    <rich:dropSupport id="WednesdayDropSupportId27" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="WednesdayTable7, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="WednesdayTable7" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt38}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="16:00-17:00" />
                </f:facet>
                    
                    <rich:dropSupport id="ThursdayDropSupportId37" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="ThursdayTable7, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="ThursdayTable7" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt48}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            <rich:panel bodyClass="dropTargetPanel">
            
                <f:facet name="header">
                    <h:outputText value="16:00-17:00" />
                </f:facet>
                    
                    <rich:dropSupport id="FridayDropSupportId47" acceptedTypes="Test" dropValue="Test" action="#{manuelSchedulingUtilBean.dropAction}"
                    dropListener="#{manuelSchedulingUtilBean.processDrop}" reRender="FridayTable7, src" actionListener="#{manuelSchedulingUtilBean.getIdAction}">
                </rich:dropSupport>

                <h:dataTable id="FridayTable7" value="#{manuelSchedulingUtilBean}" style="width:186px;height:50px;" var="category">
                    <h:column>
                        <h:outputText value="#{manuelSchedulingUtilBean.valueForDt58}"></h:outputText>
                    </h:column>
                </h:dataTable>
                    
            </rich:panel>
            
            	</h:panelGrid>
            	
            	<rich:panel id="errorPanel">
            	<h:panelGrid columns="3">
            		<rich:panel id="SaveSchedulePanelId" bodyClass="footerPanelStyle">
            			<rich:messages for="form" layout="list">
                        	<h:panelGrid columns="2">
	                        	<h:graphicImage value="/images/modal/error.gif"/> 
	                        	<h:outputText value="#{manuelSchedulingUtilBean.errorLabel}"></h:outputText>        	
                        	</h:panelGrid>
                   		</rich:messages>	
            		</rich:panel>
	        		
                   <rich:panel id="UpdatePanelId" bodyClass="footerPanelStyle">
                   	<h:panelGrid columns="1" id="UpdatePanelGridId">
                   	   	<f:facet name="header">
                   	   		<h:outputText value="Select year and semester" />
                   	   	</f:facet>
							
							<h:form id="UpdateFormId">
								<h:panelGrid columns="2">	
								
								<rich:comboBox id="yearToSave" value="Select Year" valueChangeListener ="#{manuelSchedulingUtilBean.selectionChangedSavedYearCombo}" width="110">
			        				<f:selectItems value="#{manuelSchedulingUtilBean.listNextTwoYearsToSave}"/>
			        				<a4j:support event="onselect" ajaxSingle="true"/>
			        			</rich:comboBox>
								
								<rich:comboBox id="semesterToSave" value="Select Semester" valueChangeListener ="#{manuelSchedulingUtilBean.selectionChangedSavedSemesterCombo}" width="110">
			        				<f:selectItems value="#{manuelSchedulingUtilBean.listSemesterToSave}"/>
			        				<a4j:support event="onselect" ajaxSingle="true"/>
			        			</rich:comboBox>

								<rich:comboBox id="archiveToSave" value="Save Archive" valueChangeListener ="#{manuelSchedulingUtilBean.selectionChangedSavedArchiveCombo}" width="110">
			        				<f:selectItems value="#{manuelSchedulingUtilBean.listArchiveToSave}"/>
			        				<a4j:support event="onselect" ajaxSingle="true"/>
			        			</rich:comboBox>
								
								<h:inputText id="versionName" value="#{manuelSchedulingUtilBean.savedVersionName}" >
									<rich:ajaxValidator event="onblur"/>
								</h:inputText>
								
								<h:commandButton value="Save Schedule" action="#{manuelSchedulingUtilBean.clickSave}" style=" width : 110px; height : 20px;">
									<a4j:support event="onclick" reRender="form"/>
								</h:commandButton>
								
							</h:panelGrid>		
							</h:form>
                   	   	<f:facet name="footer">
                   	   		<h:outputText value="footer" />
                   	   	</f:facet>                   	   	   
                   	   </h:panelGrid>
                   </rich:panel>
                   </h:panelGrid>
	        	</rich:panel>
            	</h:panelGrid>
        </h:form>
        </h:panelGrid>
    </rich:panel>
</f:view>
</body>
</html>