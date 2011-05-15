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
            <rich:dataTable value="#{sysUserBean.allUsers}"
                var="category" rows="20" rowKeyVar="row"
                ajaxKeys="#{sysUserBean.keys}" id="table"
                onRowContextMenu="if (row) row.style.backgroundColor='#{a4jSkin.tableBackgroundColor}';
                this.style.backgroundColor='#F1F1F1'; row=this;
                return false;" style="height : 142px; width : 634px;">
                <f:facet name="header">
                    <h:outputText value="User Operation" />
                </f:facet>
                
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Name" />
                    </f:facet>
                    <h:outputText value="#{category.userName}" id="userName" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Status" />
                    </f:facet>
                    <h:outputText value="#{category.userStatus}" id="status" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Password" />
                    </f:facet>
                    <h:outputText value="#{category.userPassword}" />
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        Actions
                    </f:facet>
                    <a4j:commandLink ajaxSingle="true" id="editlink"
                        oncomplete="#{rich:component('editPanel')}.show()">
                        <h:graphicImage value="../images/icons/edit.ico" style="border:0" />
                        <f:setPropertyActionListener value="#{category}"
                            target="#{sysUserBean.currentItem}" />
                        <f:setPropertyActionListener value="#{row}"
                            target="#{sysUserBean.currentRow}" />
                    </a4j:commandLink>
                    <rich:toolTip for="editlink" value="Edit" />
                    <a4j:commandLink ajaxSingle="true" id="deletelink"
                        oncomplete="#{rich:component('deletePanel')}.show()">
                        <h:graphicImage value="../images/icons/delete.ico" style="border:0" />
                        <f:setPropertyActionListener value="#{row}"
                            target="#{sysUserBean.currentRow}" />
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
            <h:outputText value="Edit Current User" />
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
                        <h:outputText value="User" />
                        <h:inputText value="#{sysUserBean.currentItem.userName}" />
                        <h:outputText value="Status" />
                        <rich:comboBox id="selectStatusIdForEdit" value="Select Status" valueChangeListener="#{sysUserBean.selectionChanged}" style="width : 149px;">
							<f:selectItems value="#{sysUserBean.selectItems}"/>
					</rich:comboBox>
                        <h:outputText value="Password" />
                        <h:inputText value="#{sysUserBean.currentItem.userPassword}"
                            label="Password" immediate="true" />
                    </h:panelGrid>
                    <rich:message showSummary="true" showDetail="false" for="price" />
                </a4j:outputPanel>
                <a4j:commandButton value="Store"
                    action="#{sysUserBean.store}"
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
                        <td align="center" width="50%"><a4j:commandButton value="Yes"
                            ajaxSingle="true" action="#{sysUserBean.delete}"
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
    
    <h:panelGrid id="sysuserPanelGrid" columns="3">
    	<h:outputLabel value="User Name : " style="FONT-SIZE: small; FONT-FAMILY: 'Verdana';"/>
    	<h:inputText  value="#{sysUserBean.currentItem.userName}" id="userName" style="FONT-SIZE: small; FONT-FAMILY: 'Verdana'; width : 144px;">
	    		<rich:ajaxValidator event="onblur"/>
	   	</h:inputText>
	   	<rich:message for="userName" style="color:red;FONT-SIZE: small; FONT-FAMILY: 'Verdana';"></rich:message>
	   	
	   	<h:outputLabel value="User Password : " style="FONT-SIZE: small; FONT-FAMILY: 'Verdana';"/>
    	<h:inputText value="#{sysUserBean.currentItem.userPassword}" id="userPass" style="FONT-FAMILY: 'Verdana'; FONT-SIZE: small; width : 144px;">
	    		<rich:ajaxValidator event="onblur"/>
	    </h:inputText>
	    <rich:message for="userPass" style="color:red;FONT-SIZE: small; FONT-FAMILY: 'Verdana';"></rich:message>
	   	
	   	<h:outputLabel value="" />
	   	<rich:comboBox id="selectStatusId" value="Select Status" valueChangeListener="#{sysUserBean.selectionChanged}" width="146px;">
				<f:selectItems value="#{sysUserBean.selectItems}"/>
		</rich:comboBox>
    	<h:outputLabel value="" />
    
    <h:outputLabel value="" />
    	<h:commandButton value="Add User" action="#{sysUserBean.addUser}" style="width : 110px; height : 25px;">
				<a4j:support event="onclick" reRender="table"/>
			</h:commandButton>
    <h:outputLabel value="" />
    
    </h:panelGrid>
    
    </h:form>

</f:view>
</body>
</html>