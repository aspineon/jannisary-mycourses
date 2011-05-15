<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Course Administration</title>

<style>
.active-row {
    background-color: #FFEBDA;
}
</style>

</head>
<body>
<f:view>


    <h:form>
        <script type="text/javascript">
            var row;
        </script>
        <a4j:region>
            <rich:dataTable value="#{courseBean.allCourses}"
                var="category" rows="20" rowKeyVar="row"
                ajaxKeys="#{courseBean.keys}" id="table"
                onRowContextMenu="if (row) row.style.backgroundColor='#{a4jSkin.tableBackgroundColor}';
                this.style.backgroundColor='#F1F1F1'; row=this;
                return false;" style="height : 142px; width : 634px;">
                <f:facet name="header">
                    <h:outputText value="User Operation" />
                </f:facet>
                
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Code" />
                    </f:facet>
                    <h:outputText value="#{category.courseCode}" id="courseCode" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Name" />
                    </f:facet>
                    <h:outputText value="#{category.courseName}" id="courseName" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Teoric Hours" />
                    </f:facet>
                    <h:outputText value="#{category.teoricLectureHours}" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Practice Hours" />
                    </f:facet>
                    <h:outputText value="#{category.practiceLectureHourse}" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Attendance" />
                    </f:facet>
                    <h:outputText value="#{category.attendance}" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Grade" />
                    </f:facet>
                    <h:outputText value="#{category.grade}" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Type of Course" />
                    </f:facet>
                    <h:outputText value="#{category.typeofCourse.typeofCourse}" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Department" />
                    </f:facet>
                    <h:outputText value="#{category.department.deptCode}" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Precondition" />
                    </f:facet>
                    <h:outputText value="#{category.precondition}" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Course Description" />
                    </f:facet>
                    <h:outputText value="#{category.courseDescription}" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        Actions
                    </f:facet>
                    <a4j:commandLink ajaxSingle="true" id="editlink"
                        oncomplete="#{rich:component('editPanel')}.show()">
                        <h:graphicImage value="../images/icons/edit.ico" style="border:0" />
                        <f:setPropertyActionListener value="#{category}"
                            target="#{courseBean.currentItem}" />
                        <f:setPropertyActionListener value="#{row}"
                            target="#{courseBean.currentRow}" />
                    </a4j:commandLink>
                    <rich:toolTip for="editlink" value="Edit" />
                    <a4j:commandLink ajaxSingle="true" id="deletelink"
                        oncomplete="#{rich:component('deletePanel')}.show()">
                        <h:graphicImage value="../images/icons/delete.ico" style="border:0" />
                        <f:setPropertyActionListener value="#{row}"
                            target="#{courseBean.currentRow}" />
                    </a4j:commandLink>
                    <rich:toolTip for="deletelink" value="Delete" />
                </rich:column>
                <f:facet name="footer">
                    <rich:datascroller renderIfSinglePage="false" maxPages="5" />
                </f:facet>
            </rich:dataTable>
        </a4j:region>
    </h:form>

    <rich:modalPanel id="editPanel" autosized="true" width="450">
        <f:facet name="header">
            <h:outputText value="Edit Current Course" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="../images/modal/close.ico" id="hidelink"
                    styleClass="hidelink" />
                <rich:componentControl for="editPanel" attachTo="hidelink"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <rich:messages style="color:red;"></rich:messages>
            <h:panelGrid columns="1">
                <a4j:outputPanel ajaxRendered="true">
                    <h:panelGrid columns="2">
                        <h:outputText value="Code" />
                        <h:inputText value="#{courseBean.currentItem.courseCode}" style="width:130px;"/>
                        <h:outputText value="Name" />
                        <h:inputText value="#{courseBean.currentItem.courseName}" style="width:130px;"/>
                        <h:outputText value="Teoric H." />
                        <h:inputText value="#{courseBean.currentItem.teoricLectureHours}"
                            label="Password" immediate="true" style="width:130px;"/>
                        <h:outputText value="Practice H." />
                        <h:inputText value="#{courseBean.currentItem.practiceLectureHourse}" style="width:130px;"/>
                        <h:outputText value="Attendance" />
                        <h:inputText value="#{courseBean.currentItem.attendance}" style="width:130px;"/>
                        <h:outputText value="Grade" />
                        <h:inputText value="#{courseBean.currentItem.grade}" style="width:130px;"/>
                        <h:outputText value="Type" />
                        <rich:comboBox id="selectTypeofCourseIdForEditPanel" width="130px" value="Select Type of Course" valueChangeListener="#{courseBean.selectionChangedTypeofCourseCombo}">
							<f:selectItems value="#{courseBean.selectItemsForTypeofCourses}"/>
						</rich:comboBox>
                        <h:outputText value="Department" />
                        <rich:comboBox id="selectDepartmentForEditPanel" width="130px" value="Select Department" valueChangeListener="#{courseBean.selectionChangedDepartmentCombo}">
							<f:selectItems value="#{courseBean.selectItemsForDepartments}"/>
						</rich:comboBox>
                        <h:outputText value="Precondition" />
                        <h:inputText value="#{courseBean.currentItem.precondition}" style="width:130px;"/>
                        <h:outputText value="Description" />
                        <h:inputText value="#{courseBean.currentItem.courseDescription}" style="width:130px;"/>
                    </h:panelGrid>
                    <rich:message showSummary="true" showDetail="false" for="price" />
                </a4j:outputPanel>
                <a4j:commandButton value="Store"
                    action="#{courseBean.store}"
                    reRender="table"
                    oncomplete="if (#{facesContext.maximumSeverity==null}) #{rich:component('editPanel')}.hide();" />
            </h:panelGrid>
        </h:form>
    </rich:modalPanel>
    <rich:modalPanel id="deletePanel" autosized="true" width="200">
        <f:facet name="header">
            <h:outputText value="Delete this User from list?"
                style="padding-right:15px;" />
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="../images/modal/close.ico"
                    styleClass="hidelink" id="hidelink2" />
                <rich:componentControl for="deletePanel" attachTo="hidelink2"
                    operation="hide" event="onclick" />
            </h:panelGroup>
        </f:facet>
        <h:form>
            <table width="100%">
                <tbody>
                    <tr>
                        <td align="center" width="50%"><a4j:commandButton value="Yes" ajaxSingle="true" action="#{courseBean.delete}"
                            oncomplete="#{rich:component('deletePanel')}.hide();"
                            reRender="table" />
                        </td>
                        <td align="center" width="50%"><a4j:commandButton
                            value="Cancel"
                            onclick="#{rich:component('deletePanel')}.hide();return false;" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </h:form>
    </rich:modalPanel>
    <a4j:status onstart="#{rich:component('wait')}.show()"
        onstop="#{rich:component('wait')}.hide()" />
    <rich:modalPanel id="wait" autosized="true" width="200" height="120"
        moveable="false" resizeable="false">
        <f:facet name="header">
            <h:outputText value="Processing" />
        </f:facet>
        <h:outputText value="Wait Please..." />
    </rich:modalPanel>
    
    <h:form>
    
    <h:panelGrid id="coursePanel" columns="3" border="0">
    	
    	<h:outputLabel value="Course Code : " style="FONT-SIZE: small; FONT-FAMILY: 'Verdana';"/>	
	    <h:inputText label="Course Code" value="#{courseBean.currentItem.courseCode}" id="courseCode" style="FONT-SIZE: small; FONT-FAMILY: 'Verdana'; width : 145px;">
	    	<rich:ajaxValidator event="onblur"/>
	    </h:inputText>
	    <rich:message for="courseCode" style="color:red;FONT-SIZE: small; FONT-FAMILY: 'Verdana';"></rich:message>
	    
	    <!-- ******************************************************** -->
	   	<h:outputLabel value="Course Name : " style="FONT-SIZE: small; FONT-FAMILY: 'Verdana';"/>
	   	<h:inputText label="Course Name" value="#{courseBean.currentItem.courseName}" id="courseName" style="FONT-SIZE: small; FONT-FAMILY: 'Verdana'; width : 145px;">
	    		<rich:ajaxValidator event="onblur"/>
	   		</h:inputText>
	   	<rich:message for="courseName" style="color:red;FONT-SIZE: small; FONT-FAMILY: 'Verdana';"></rich:message>
	   				
    	<!-- ******************************************************** -->
    	
    	<h:outputLabel value="Theoric Hours : " style="FONT-SIZE: small; FONT-FAMILY: 'Verdana';"/>
    	<h:inputText  value="#{courseBean.currentItem.teoricLectureHours}" id="teoricLecture" style="FONT-SIZE: small; FONT-FAMILY: 'Verdana';width : 145px;">
	   	</h:inputText>
	   	<rich:message for="teoricLecture" style="color:red;FONT-SIZE: small; FONT-FAMILY: 'Verdana';"></rich:message>
	   	
	   	<h:outputLabel value="Practice Hours : " style="FONT-SIZE: small; FONT-FAMILY: 'Verdana';"/>
    	<h:inputText  value="#{courseBean.currentItem.practiceLectureHourse}" id="practiceLecture" style="FONT-SIZE: small; FONT-FAMILY: 'Verdana'; width : 145px;">
	   	</h:inputText>
	   	<rich:message for="practiceLecture" style="color:red;FONT-SIZE: small; FONT-FAMILY: 'Verdana';"></rich:message>
	   	<!-- ******************************************************** -->
	   	
	   	<h:outputLabel value="Attendance : " style="FONT-SIZE: small; FONT-FAMILY: 'Verdana';"/>
	   	<rich:comboBox id="selectAttendanceId" value="Select Attendance" valueChangeListener="#{courseBean.selectionChangedAttendanceCombo}">
				<f:selectItems value="#{courseBean.selectItemsForAttendance}"/>
		</rich:comboBox>
	   	<h:outputText value="" />
	   	
	   		<h:outputLabel value="Grade : " style="FONT-SIZE: small; FONT-FAMILY: 'Verdana';"/> 	
	   	<rich:comboBox id="selectGradeId" value="Select Grade" valueChangeListener="#{courseBean.selectionChangedGradeCombo}">
				<f:selectItems value="#{courseBean.selectItemsGrades}"/>
		</rich:comboBox>
	   	<rich:message for="gradeText" style="color:red;FONT-SIZE: small; FONT-FAMILY: 'Verdana';"></rich:message>
	   	
	   	<h:outputLabel value="" />
	   	<rich:comboBox id="selectTypeofCourseId" value="Select Type of Course" valueChangeListener="#{courseBean.selectionChangedTypeofCourseCombo}">
				<f:selectItems value="#{courseBean.selectItemsForTypeofCourses}"/>
			</rich:comboBox>
		<h:outputLabel value="" />
			
			<h:outputLabel value="" />
	   	<rich:comboBox id="selectDepartmentCode" value="Select Department" valueChangeListener="#{courseBean.selectionChangedDepartmentCombo}">
				<f:selectItems value="#{courseBean.selectItemsForDepartments}"/>
		</rich:comboBox>
	   	<h:outputLabel value="" />
	   	
	   	<h:outputLabel value="Precondition : " style="FONT-SIZE: small; FONT-FAMILY: 'Verdana';"/>
	   	<rich:comboBox id="selectPreconditionCourseCode" value="Select Course Code" valueChangeListener="#{courseBean.selectionChangedPreconditionCombo}">
				<f:selectItems value="#{courseBean.selectItemsForPreconditions}"/>
		</rich:comboBox>
	   	<rich:message for="preconditionText" style="color:red;FONT-SIZE: small; FONT-FAMILY: 'Verdana';"></rich:message>	
	   	
	   	<!-- ********************************************************************* -->
	   	
	   	<h:outputLabel value="Course Description : " style="FONT-SIZE: small; FONT-FAMILY: 'Verdana'; width : 145px;"/>
	   	<h:inputText  value="#{courseBean.currentItem.courseDescription}" id="courseDesc">
	    		<rich:ajaxValidator event="onblur"/>
	   		</h:inputText>
	   	<rich:message for="courseDesc" style="color:red;FONT-SIZE: small; FONT-FAMILY: 'Verdana';"></rich:message>	
	   	
	   		<!-- ********************************************************************* -->
	   		<h:outputLabel value="" />
	   		<h:commandButton value="Add Course" action="#{courseBean.addCourse}" style="width : 110px; height : 24px;">
				<a4j:support event="onclick" reRender="table"/>
			</h:commandButton>
	   	
	   	
    </h:panelGrid>
  
    </h:form>
        
</f:view>
</body>
</html>