<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
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
		//	DnD.CLIENT_VALIDATION_OFF = true;
		</script>

		<f:view>
			<h:form id="form">
                <h:selectOneRadio binding="#{skinBean.component}" />
                <h:commandLink action="#{skinBean.change}" value="set skin" />
			</h:form>
			<h:panelGrid columns="3">
				<h:form>
					<fieldset>
						<legend>Tables</legend>
					
						<h:panelGroup id="dragValueText">
							<h:outputText value="#{bean.dragValue}" />
						</h:panelGroup>
		
		               	<h:panelGrid columns="3">
			                <h:dataTable var="type" value="#{bean.types}">
								<h:column>
									<h:panelGrid styleClass="dropzoneDecoration" id="drop1A">
										<h:outputText value="#{type} - drop" />
			
										<rich:dropSupport reRender="dragValueText" action="#{bean.dropAction}" acceptedTypes="#{type}" dropListener="#{bean.processDrop}" dropValue="#{type} - value">
											<a4j:actionparam value="#{type} - test drop param" assignTo="#{bean.testParam}" />
										</rich:dropSupport>
									</h:panelGrid>
								</h:column>
			                </h:dataTable>
			                
			                <h:dataTable var="type" value="#{bean.types}">
								<h:column>
									<h:panelGrid styleClass="dropzoneDecoration" id="drag1">
										<h:outputText value="#{type} - drag" />
				
										<rich:dragSupport dragType="#{type}" dragValue="#{type} - value" action="#{bean.dragAction}" dragListener="#{bean.processDrag}">
											<a4j:actionparam value="#{type} - test drag param" assignTo="#{bean.testParam}" />
										</rich:dragSupport>
									</h:panelGrid>
								</h:column>
			                </h:dataTable>
		
			                <h:dataTable var="type" value="#{bean.types}">
								<h:column>
									<h:panelGrid styleClass="dropzoneDecoration" id="drop1B">
										<h:outputText value="#{type} - drop" />
				
										<rich:dropSupport reRender="dragValueText" action="#{bean.dropAction}" acceptedTypes="#{type}" dropListener="#{bean.processDrop}" dropValue="#{type} - value">
										</rich:dropSupport>
									</h:panelGrid>
								</h:column>
			                </h:dataTable>
		               	</h:panelGrid>
					</fieldset>
				</h:form>
				<h:form>	
					<fieldset>
						<legend>Regions</legend>
					
						<h:panelGroup id="regionsDragValueText">
							<h:outputText value="#{bean.dragValue}" />
						</h:panelGroup>
		               	<h:panelGrid columns="3">
			                <h:dataTable var="type" value="#{bean.types}">
								<h:column>
									<a4j:region>
										<h:panelGrid styleClass="dropzoneDecoration" id="drop2A">
											<h:outputText value="#{type} - drop" />
					
											<rich:dropSupport ajaxSingle="true" reRender="regionsDragValueText" action="#{bean.dropAction}" acceptedTypes="#{type}" dropListener="#{bean.processDrop}" dropValue="#{type} - value">
												<a4j:actionparam value="#{type} - test drop param" assignTo="#{bean.testParam}" />
											</rich:dropSupport>
										</h:panelGrid>
									</a4j:region>
								</h:column>
			                </h:dataTable>
			                
			                <h:dataTable var="type" value="#{bean.types}">
								<h:column>
									<a4j:region>
										<h:panelGrid styleClass="dropzoneDecoration" id="drag2">
											<h:outputText value="#{type} - drag" />
											<rich:dragSupport dragType="#{type}" dragValue="#{type} - value" action="#{bean.dragAction}" dragListener="#{bean.processDrag}">
												<a4j:actionparam value="#{type} - test drag param" assignTo="#{bean.testParam}" />
											</rich:dragSupport>
										</h:panelGrid>
									</a4j:region>
								</h:column>
			                </h:dataTable>
		
			                <h:dataTable var="type" value="#{bean.types}">
								<h:column>
									<a4j:region>
										<h:panelGrid styleClass="dropzoneDecoration" id="drop2B">
											<h:outputText value="#{type} - drop" />
					
											<rich:dropSupport ajaxSingle="true" reRender="regionsDragValueText" action="#{bean.dropAction}" acceptedTypes="#{type}" dropListener="#{bean.processDrop}" dropValue="#{type} - value">
												<a4j:actionparam value="#{type} - test drop param" assignTo="#{bean.testParam}" />
											</rich:dropSupport>
										</h:panelGrid>
									</a4j:region>
								</h:column>
			                </h:dataTable>
		               	</h:panelGrid>
					</fieldset>
				</h:form>
				
				<h:panelGroup>
					<fieldset>
						<legend>Forms</legend>
					
						<h:panelGroup id="formsDragValueText">
							<h:outputText value="#{bean.dragValue}" />
						</h:panelGroup>
		               	<h:panelGrid columns="3">
			                <h:dataTable var="type" value="#{bean.types}">
								<h:column>
									<h:form>
										<h:panelGrid styleClass="dropzoneDecoration" id="drag3A">
											<h:outputText value="#{type} - drop" />
					
											<rich:dropSupport ajaxSingle="true" reRender="formsDragValueText" action="#{bean.dropAction}" acceptedTypes="#{type}" dropListener="#{bean.processDrop}" dropValue="#{type} - value">
												<a4j:actionparam value="#{type} - test drop param" assignTo="#{bean.testParam}" />
											</rich:dropSupport>
										</h:panelGrid>
									</h:form>
								</h:column>
			                </h:dataTable>
			                
			                <h:dataTable var="type" value="#{bean.types}">
								<h:column>
									<h:form>
										<h:panelGrid styleClass="dropzoneDecoration" id="drag3">
											<h:outputText value="#{type} - drag" />
					
											<rich:dragSupport dragType="#{type}" dragValue="#{type} - value" action="#{bean.dragAction}" dragListener="#{bean.processDrag}">
												<a4j:actionparam value="#{type} - test drag param" assignTo="#{bean.testParam}" />
											</rich:dragSupport>
										</h:panelGrid>
									</h:form>
								</h:column>
			                </h:dataTable>
		
			                <h:dataTable var="type" value="#{bean.types}">
								<h:column>
									<h:form>
										<h:panelGrid styleClass="dropzoneDecoration" id="drop3B">
											<h:outputText value="#{type} - drop" />
					
											<rich:dropSupport ajaxSingle="true" reRender="formsDragValueText" action="#{bean.dropAction}" acceptedTypes="#{type}" dropListener="#{bean.processDrop}" dropValue="#{type} - value">
												<a4j:actionparam value="#{type} - test drop param" assignTo="#{bean.testParam}" />
											</rich:dropSupport>
										</h:panelGrid>
									</h:form>
								</h:column>
			                </h:dataTable>
		               	</h:panelGrid>
					</fieldset>
				</h:panelGroup>
			</h:panelGrid>

			<h:form id="form2">
				<rich:dragIndicator  id="indicator" acceptClass="accept" rejectClass="reject" style="width: 500px;">
					<f:facet name="single">
						<f:verbatim>
							{marker} <b>{testDrag}</b> {label}
						</f:verbatim>
					</f:facet>
					
					<rich:dndParam name="accept" value="ACCEPT:" />
					
					<rich:dndParam name="reject">
						<f:verbatim>
							<i style="text-decoration: line-through;">REJECT:</i>
						</f:verbatim>
					</rich:dndParam>
				</rich:dragIndicator>

				<h:panelGrid columns="1" style="position: relative; left: 140px;">
					<h:panelGrid columns="1" style="position: absolute; top: 30px; left: 300px;">
						<rich:dragIndicator  id="defaultIndicator" style="width: 400px;">
						</rich:dragIndicator>
					</h:panelGrid>				
				</h:panelGrid>

				<h:panelGrid columns="4" cellspacing="20">
					<h:panelGrid styleClass="dropzoneDecoration" id="grid1">
						<f:verbatim>
							Accepts file & folder... Customizes
						</f:verbatim>

						<rich:dropSupport id="zone1" ondrop="var zone = document.getElementById('form:grid1'); zone.style.borderColor= 'red'; setTimeout( function() { this.style.borderColor= 'navy'; }.bind(zone), 300);" acceptedTypes="file, folder" typeMapping="{file: testDrop}">
							<rich:dndParam name="testDrop">
								<h:graphicImage height="16" width="16" value="/images/file-manager.png" />
							</rich:dndParam>
	
						</rich:dropSupport>
					</h:panelGrid>
					

					<h:panelGrid styleClass="dropzoneDecoration" id="grid2">
						<f:verbatim>
							Accepts none
						</f:verbatim>

						<rich:dropSupport>
						</rich:dropSupport>
					</h:panelGrid>

					<h:panelGrid styleClass="dropzoneDecoration" id="grid3">
						<f:verbatim>
							Accepts none... Customizes
						</f:verbatim>

						<rich:dropSupport typeMapping="{file: testDrop}">
							<rich:dndParam name="testDrop">
								<h:graphicImage height="16" width="16" value="/images/file-manager-reject.png" />
							</rich:dndParam>
	
						</rich:dropSupport>
					</h:panelGrid>

					<h:panelGrid styleClass="dropzoneDecoration" id="grid4">
						<f:verbatim>
							Accepts file & folder
						</f:verbatim>
						<rich:dropSupport acceptedTypes="file, folder">
							<rich:dndParam name="testDrop" value="testDropValue" />
	
						</rich:dropSupport>
						<h:selectOneMenu>
							<f:selectItem itemValue="test1" itemLabel="TEST1"/>
							<f:selectItem itemValue="test2" itemLabel="TEST2"/>
							<f:selectItem itemValue="test3" itemLabel="TEST3"/>
							<f:selectItem itemValue="test4" itemLabel="TEST4"/>
							<f:selectItem itemValue="test5" itemLabel="TEST5"/>
						</h:selectOneMenu>
					</h:panelGrid>

					<h:panelGrid id="grid5">
						<rich:dragSupport dragType="file">
							<rich:dndParam name="label" value="Label" />
							<rich:dndParam name="testDrag" value="testDragValue" />
	
						</rich:dragSupport>
						<f:verbatim>File Draggable - no indicator</f:verbatim>
					</h:panelGrid>

					<h:panelGrid id="grid6">
						<rich:dragSupport dragType="file" dragIndicator="indicator">
							<rich:dndParam name="label" value="Label" />
							<rich:dndParam name="testDrag" value="testDragValue" />
	
						</rich:dragSupport>
						<f:verbatim>File Draggable with indicator</f:verbatim>
					</h:panelGrid>

					<h:panelGrid id="grid7">
						<rich:dragSupport dragType="folder" dragIndicator="indicator">
							<rich:dndParam name="label" value="Label" />
							<rich:dndParam name="testDrag" value="testDragValue for Folder" />
	
						</rich:dragSupport>
						<f:verbatim>Folder Draggable with indicator</f:verbatim>
					</h:panelGrid>

					<h:outputText />

					<h:panelGrid id="grid8">
	                    <rich:dragSupport dragType="folder">
	                        <rich:dndParam name="label" value="Label" />
	                        <rich:dndParam name="testDrag" value="testDragValue for Folder" />
	
	                    </rich:dragSupport>
                        <f:verbatim>Folder Draggable - no indicator</f:verbatim>
					</h:panelGrid>

					<h:panelGrid id="grid9">
						<rich:dragSupport dragType="file" dragIndicator="defaultIndicator">
							<rich:dndParam name="testDrag" type="drop" value="testDragValue" />
	
							<rich:dndParam name="marker" value="testMarkerValue" />
							<rich:dndParam name="label" value="testDragValue" />
	
						</rich:dragSupport>
						<f:verbatim>File Draggable with defaultIndicator</f:verbatim>
					</h:panelGrid>

					<h:panelGrid id="grid10">
						<rich:dragSupport dragType="folder" dragIndicator="defaultIndicator">
							<rich:dndParam name="label" value="testDragValue for Folder" />
	
						</rich:dragSupport>
						<f:verbatim>Folder Draggable with defaultIndicator</f:verbatim>
					</h:panelGrid>

					<h:outputText />

				</h:panelGrid>
				
				<h:selectOneMenu>
					<f:selectItem itemValue="test1" itemLabel="TEST1"/>
					<f:selectItem itemValue="test2" itemLabel="TEST2"/>
					<f:selectItem itemValue="test3" itemLabel="TEST3"/>
					<f:selectItem itemValue="test4" itemLabel="TEST4"/>
					<f:selectItem itemValue="test5" itemLabel="TEST5"/>
				</h:selectOneMenu>

				<h:panelGrid id="renderedId">
					<rich:dragSupport dragType="file" dragIndicator="defaultIndicator">
						<rich:dndParam name="marker" value="testMarkerValue" />
						<rich:dndParam name="label" value="testDragValue" />
					</rich:dragSupport>
					
					<h:graphicImage id="dragImage" value="/images/file-manager.png" width="48" />
					<f:verbatim>
						dragSupport
					</f:verbatim>
				</h:panelGrid>
				
				<h:panelGroup id="group">
					<f:verbatim>
					PanelGroup					
					</f:verbatim>
					<rich:dropSupport acceptedTypes="file" dropListener="#{bean.processDrop}" />
				</h:panelGroup>
				
				<h:panelGrid id="renderedIdII" style="border: 1px solid red;">
					<rich:dropSupport acceptedTypes="file" dropListener="#{bean.processDrop}" />
					<f:verbatim>
						<div style="margin: 40px; border: 1px solid green;">
							dropSupport
						</div>
					</f:verbatim>
				</h:panelGrid>

				<a4j:status startText="...request..." stopText="stop" />
				<a4j:outputPanel ajaxRendered="true">
					<h:messages />
				</a4j:outputPanel>

				<h:outputText>
					<rich:dropSupport acceptedTypes="file" />
				</h:outputText>

				<h:outputText>
					<rich:dragSupport dragType="file" />
				</h:outputText>

			
			</h:form>
		</f:view>
	</body>
</html>