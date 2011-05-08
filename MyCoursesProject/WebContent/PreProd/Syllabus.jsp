<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

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
            <rich:dataTable value="#{syllabusBean.allSyllabusList}"
                var="category" rows="20" rowKeyVar="row"
                ajaxKeys="#{syllabusBean.keys}" id="table"
                onRowContextMenu="if (row) row.style.backgroundColor='#{a4jSkin.tableBackgroundColor}';
                this.style.backgroundColor='#F1F1F1'; row=this;
                return false;">
                <f:facet name="header">
                    <h:outputText value="Syllabus Operation" />
                </f:facet>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Semester" />
                    </f:facet>
                    <h:outputText value="#{category.semester}" id="semester" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Year" />
                    </f:facet>
                    <h:outputText value="#{category.year}" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="CourseName" />
                    </f:facet>
                    <h:outputText value="#{category.course.courseName}" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="LecturerName" />
                    </f:facet>
                    <h:outputText value="#{category.lecturer.lecturerName}" />
                </rich:column>
                
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Classroom" />
                    </f:facet>
                    <h:outputText value="#{category.classroom.classroomCode}" />
                </rich:column>
                
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="SectionNo" />
                    </f:facet>
                    <h:outputText value="#{category.sectionNo}" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        Actions
                    </f:facet>
                    <a4j:commandLink ajaxSingle="true" id="editlink"
                        oncomplete="#{rich:component('editPanel')}.show()">
                        <h:graphicImage value="../images/icons/edit.ico" style="border:0" />
                        <f:setPropertyActionListener value="#{category}"
                            target="#{syllabusBean.currentItem}" />
                        <f:setPropertyActionListener value="#{row}"
                            target="#{syllabusBean.currentRow}" />
                    </a4j:commandLink>
                    <rich:toolTip for="editlink" value="Edit" />
                    <a4j:commandLink ajaxSingle="true" id="deletelink"
                        oncomplete="#{rich:component('deletePanel')}.show()">
                        <h:graphicImage value="../images/icons/delete.ico" style="border:0" />
                        <f:setPropertyActionListener value="#{row}"
                            target="#{syllabusBean.currentRow}" />
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
            <h:outputText value="Edit Current Syllabus" />
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
                    
                        <h:outputText value="Semester" />
                        <h:inputText value="#{syllabusBean.currentItem.semester}" />
                        
                        <h:outputText value="Year" />
                        <h:inputText value="#{syllabusBean.currentItem.year}" />
                        
                        <h:outputText value="Course Code" />
                        <rich:comboBox id="courseEditId" value="Select Course Code" valueChangeListener="#{syllabusBean.selectionChangedCourseEditCombo}">
                        <f:selectItems value="#{syllabusBean.courseCodeList}"/>
                        </rich:comboBox>
                        
                        <h:outputText value="Lecture Name" />
                        <rich:comboBox id="lecturerEditId" value="Select Lecturer Name" valueChangeListener="#{syllabusBean.selectionChangedLecturerEditCombo}">
                        	<f:selectItems value="#{syllabusBean.lecturerNameList}"/>
                        </rich:comboBox>
                        
                        <h:outputText value="Classroom Code" />
                        <rich:comboBox id="classroomEditId" value="Select Classroom" valueChangeListener="#{syllabusBean.selectionChangedClassroomEditCombo}">
                        	<f:selectItems value="#{syllabusBean.classroomList}"/>
                        </rich:comboBox>
                        
                        <h:outputText value="SectionNo" />
                        <h:inputText value="#{syllabusBean.currentItem.sectionNo}" />
                        
                    </h:panelGrid>
                    <rich:message showSummary="true" showDetail="false" for="price" />
                </a4j:outputPanel>
                <a4j:commandButton value="Store"
                    action="#{syllabusBean.store}"
                    reRender="table"
                    oncomplete="if (#{facesContext.maximumSeverity==null}) #{rich:component('editPanel')}.hide();" />
            </h:panelGrid>
        </h:form>
    </rich:modalPanel>
    <rich:modalPanel id="deletePanel" autosized="true" width="200">
        <f:facet name="header">
            <h:outputText value="Delete this Syllabus from list?"
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
                        <td align="center" width="50%"><a4j:commandButton value="Yes"
                            ajaxSingle="true" action="#{syllabusBean.delete}"
                            oncomplete="#{rich:component('deletePanel')}.hide();"
                            reRender="table" />
                        </td>
                        <td align="center" width="50%"><a4j:commandButton
                            value="Cancel"
                            onclick ="#{rich:component('deletePanel')}.hide();return false;" />
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
     <rich:panel>
     	<h:panelGrid columns="1">
     	<rich:comboBox id="selectCourseCode" value="Select Course Code" valueChangeListener="#{syllabusBean.selectionChangedCourseAddCombo}">
				<f:selectItems value="#{syllabusBean.courseCodeList}"/>
		</rich:comboBox>
     	
     	
     	<rich:comboBox id="selectYearId" value="Select Year" valueChangeListener="#{syllabusBean.selectionChangedYearCombo}">
			<f:selectItems value="#{syllabusBean.yearList}"/>
		</rich:comboBox>
     	
     	<rich:comboBox id="selectSemesterId" value="Select Semester" valueChangeListener="#{syllabusBean.selectionChangedSemesterCombo}">
			<f:selectItems value="#{syllabusBean.semesterList}"/>
		</rich:comboBox>
     	
     	<rich:comboBox id="selectLecturerId" value="Select Lecturer Name" valueChangeListener="#{syllabusBean.selectionChangedLectureAddCombo}">
			<f:selectItems value="#{syllabusBean.lecturerNameList}"/>
		</rich:comboBox>
     	
     	
     	<h:inputText value="#{syllabusBean.currentItem.sectionNo}">
	    	<f:validateLength minimum="1" maximum="20"/>
	    </h:inputText>
	    
	    <rich:comboBox id="selectClassroomId" value="Select Classroom" valueChangeListener="#{syllabusBean.selectionChangedClassroomAddCombo}">
			<f:selectItems value="#{syllabusBean.classroomList}"/>
		</rich:comboBox>
     	
     	<h:commandButton value="Add Syllabus" action="#{syllabusBean.addSyllabus}" style="width : 110px; height : 24px;">
			<a4j:support event="onclick" reRender="table"/>
		</h:commandButton>
     	
     	</h:panelGrid>
     
     </rich:panel>
    </h:form>
    

</f:view>
</body>
</html>