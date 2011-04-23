<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>

<html>
	<head>
		<title></title>
		<style type="text/css">
			.dropzoneDecoration {
				width: 100px; height: 100px; border: 2px dotted navy;
			}
			
			.accept {
				border: 3px dotted green;
				padding: 10px;
			}

			.reject {
				border: 3px dashed red;
				padding: 10px;
			}
		</style>
	</head>
	<body>
		<script type="text/javascript">
		//	rich.CLIENT_VALIDATION_OFF = true;
		</script>

		<f:view>
		<h:form>
			<h:panelGrid columnClasses="panelc" columns="4" width="100%">

            <rich:panel style="width:133px"> 
                <f:facet name="header">
                    <h:outputText value="Course List" />
                </f:facet>
                <h:panelGrid columns="2">
                <h:dataTable id="src" value="#{demoDragDropBean.allCourses}"
                    var="dd" footerClass="footerClass">

                    <h:column>
                        <a4j:outputPanel style="width:100px;border:1px solid gray;padding:2px"
                            layout="block">
                            <rich:dragSupport dragIndicator=":indicator"
                                dragType="#{dd.precondition}" dragValue="#{dd}">
                                <rich:dndParam name="label" value="#{dd.courseName}" />
                            </rich:dragSupport>
                            <h:outputText value="#{dd.courseName}"></h:outputText>
                        </a4j:outputPanel>
                    </h:column>
                </h:dataTable>
                </h:panelGrid>
            </rich:panel>

            <rich:panel styleClass="dropTargetPanel">
                <f:facet name="header">
                    <h:outputText value="CME Frameworks" />
                </f:facet>
                
                <rich:dropSupport id="Test" acceptedTypes="Test" dropValue="Test" action="#{demoDragDropBean.dropAction}"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="phptable, src">
                </rich:dropSupport>

                <h:dataTable id="phptable" value="#{demoDragDropBean.containerCME}" var="dd">
                    <h:column>
                        <h:outputText value="#{dd.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>


            </rich:panel>

            <rich:panel styleClass="dropTargetPanel">
                <f:facet name="header">
                    <h:outputText value="MSI Frameworks" />
                </f:facet>
                <rich:dropSupport id="asd" acceptedTypes="asd" dropValue="asd"
                    dropListener="#{demoDragDropBean.processDrop}" reRender="dnettable, src">
                </rich:dropSupport>

                <h:dataTable id="dnettable"  value="#{demoDragDropBean.containerMSI}" var="dd">
                    <h:column>
                        <h:outputText value="#{dd.courseName}"></h:outputText>
                    </h:column>
                </h:dataTable>


            </rich:panel>
            
        </h:panelGrid>

			</h:form>
			<a4j:outputPanel ajaxRendered="true">
        <h:messages></h:messages>
    </a4j:outputPanel>
		</f:view>
	</body>
</html>