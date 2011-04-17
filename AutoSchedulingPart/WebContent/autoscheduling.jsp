<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich" %>
<%@taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Auto Scheduling</title>
</head>
<body>
<f:view>
<h:form>
	<h:outputLabel value="Auto Scheduling" style="color:blue;font-family:Tahoma;font-style:bold;" />
	<br/>
	<br/>
	<table>
		<tr>
		<td>
			<h:outputLabel value="Department : " style="color:black;font-family:Tahoma;font-style:bold;font-size:small" />
		</td>
		<td>
		
		</td>
		<td>			
				<rich:comboBox id="departmentComboId" value="Choose Department">
					<f:selectItems value="#{departmentBean.selectItemList}"/>					
				</rich:comboBox>			
		</td>
		</tr>
	</table>
	<br/>
	<rich:tabPanel switchType="Client">
		<rich:tab label="Freshman">
            <rich:panel style=" height : 450px;">
            <table>
            	<tr>
            		<td>
            			 <rich:orderingList style=" width : 280px;">
	            	       	<rich:column  width="300">
					            <f:facet name="header">
					                <h:outputText value="Lecture Name " style="width:30%" />
					            </f:facet> 
	                		<h:outputText value="Theory of Automata"></h:outputText>
	            			</rich:column>
	            			<rich:column  width="300">
					            <f:facet name="header">
					                <h:outputText value="Lecturer Name "/>
					            </f:facet> 
	                			<h:outputText value="Süleyman SEVINC"></h:outputText>
	            			</rich:column>			   					   		
            	  		</rich:orderingList>
            		</td>
            		<td style=" width : 100px;">
            			
            		</td>
            		<td>
            			<rich:dataTable id="deanDataTable" value="#{deanBean.list}" var="item" style=" width : 700px;">
            				
	        				<f:facet name="header">
	                			<h:outputText value="Schedule Table For Dean Course" />
	        				</f:facet>
        				
	        				<rich:column style=" width : 180px; height: 19px">
	        					<f:facet name="header">
	        						<h:outputText value="Hours/Days" />
	        					</f:facet>
	        					<h:outputText value="#{item}" />
	        					
	        				</rich:column>
                			        				        				
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Monday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Tuesday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Wednesday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Thursday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 500px;">
                			<f:facet name="header">
                				<h:outputText value="Friday"/>
                			</f:facet>
        				 </rich:column>
      				
            			</rich:dataTable>
            		</td>
            	</tr>
            	<tr>
            		<td>
            			
            	  <table>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Course Info :"></h:outputText>
            	  		</td>
            	  		<td>
            	  			<h:inputText id="courseInfoTextField" disabled="true" style=" width : 251px;"></h:inputText>
            	  		</td>
            	  	</tr>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Lecturer :"></h:outputText>
            	  		</td>
            	  		<td>
            	  			<h:inputText id="lecturerNameTextField" disabled="true"></h:inputText>
            	  		</td>
            	  	</tr>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Credits :"></h:outputText>
            	  		</td>
            	  		<td>
            	  			<table>
            	  				<tr>
            	  				<td>
            	  					<h:inputText id="theoricCreditTextField" disabled="true" style=" width : 60px;"></h:inputText>
            	  				</td>
            	  				<td>
            	  					<h:outputText value="+"></h:outputText>
            	  				</td>
            	  				<td>
            	  					<h:inputText id="practiceCreditTextField" disabled="true" style=" width : 58px;"></h:inputText>
            	  				</td>
            	  				</tr>
            	  			</table>
            	  		</td>
            	  	</tr>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Room : "></h:outputText>
            	  		</td>
            	  		<td style=" width : 146px;">
            	  			<rich:comboBox  id="roomComboBox" value = "Choose Room" width="99">
            	  				<f:selectItems id="fresmanRoom" value="#{classroomBean.selectItemListClassroom }"/>
            	  			</rich:comboBox>
            	  		</td>
            	  		
            	  	</tr>           	  	
            	  </table>
            		</td>
            	</tr>
            </table>
        	  
	      </rich:panel>
        </rich:tab>
        <rich:tab label="Sophomore">
            <rich:panel style=" height : 450px;">
            	   <table>
            	<tr>
            		<td>
            			 <rich:orderingList style=" width : 280px;">
	            	       	<rich:column  width="300">
					            <f:facet name="header">
					                <h:outputText value="Lecture Name " style="width:30%" />
					            </f:facet> 
	                		<h:outputText value="Theory of Automata"></h:outputText>
	            			</rich:column>
	            			<rich:column  width="300">
					            <f:facet name="header">
					                <h:outputText value="Lecturer Name "/>
					            </f:facet> 
	                			<h:outputText value="Süleyman SEVINC"></h:outputText>
	            			</rich:column>			   					   		
            	  		</rich:orderingList>
            		</td>
            		<td style=" width : 100px;">
            			
            		</td>
            		<td>
            			<rich:dataTable id="myDataTableSomophore" value="#{deanBean.list}" var="item" style=" width : 700px;">
            				
	        				<f:facet name="header">
	                			<h:outputText value="Schedule Table" />
	        				</f:facet>
        				
	        				<rich:column style=" width : 180px; height: 19px">
	        					<f:facet name="header">
	        						<h:outputText value="Hours/Days " />
	        					</f:facet>
	        					<h:outputText style="font-align:center;" value="#{item}" />	
	        					
	        				</rich:column>
                			
        				        				
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Monday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Tuesday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Wednesday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Thursday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 500px;">
                			<f:facet name="header">
                				<h:outputText value="Friday"/>
                			</f:facet>
        				 </rich:column>
      				
            			</rich:dataTable>
            		</td>
            	</tr>
            	<tr>
            		<td>
            			
            	  <table>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Course Info :"></h:outputText>
            	  		</td>
            	  		<td>
            	  			<h:inputText id="courseInfoTextFieldSomophore" disabled="true" style=" width : 251px;"></h:inputText>
            	  		</td>
            	  	</tr>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Lecturer :"></h:outputText>
            	  		</td>
            	  		<td>
            	  			<h:inputText id="lecturerNameTextFieldSomophore" disabled="true"></h:inputText>
            	  		</td>
            	  	</tr>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Credits :"></h:outputText>
            	  		</td>
            	  		<td>
            	  			<table>
            	  				<tr>
            	  				<td>
            	  					<h:inputText id="theoricCreditTextFieldSomophore" disabled="true" style=" width : 60px;"></h:inputText>
            	  				</td>
            	  				<td>
            	  					<h:outputText value="+"></h:outputText>
            	  				</td>
            	  				<td>
            	  					<h:inputText id="practiceCreditTextFieldSomophore" disabled="true" style=" width : 58px;"></h:inputText>
            	  				</td>
            	  				</tr>
            	  			</table>
            	  		</td>
            	  	</tr>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Room : "></h:outputText>
            	  		</td>
            	  		<td style=" width : 146px;">
            	  			<rich:comboBox  id="roomComboBoxSomophore" value = "Choose Room" width="99">
            	  				<f:selectItems id="somophoreRoom" value="#{classroomBean.selectItemListClassroom}"/>
            	  			</rich:comboBox>
            	  		</td>
            	  		
            	  	</tr>           	  	
            	  </table>
            		</td>
            	</tr>
            </table>			    				   	
            </rich:panel>
        </rich:tab>
        <rich:tab label="Junior">
            <rich:panel style=" height : 450px;">
            	   <table>
            	<tr>
            		<td>
            			 <rich:orderingList style=" width : 280px; ">
	            	       	<rich:column  width="300">
					            <f:facet name="header">
					                <h:outputText value="Lecture Name " style="width:30%" />
					            </f:facet> 
	                		<h:outputText value="Theory of Automata"></h:outputText>
	            			</rich:column>
	            			<rich:column  width="300">
					            <f:facet name="header">
					                <h:outputText value="Lecturer Name "/>
					            </f:facet> 
	                			<h:outputText value="Süleyman SEVINC"></h:outputText>
	            			</rich:column>			   					   		
            	  		</rich:orderingList>
            		</td>
            		<td style=" width : 100px;">
            			
            		</td>
            		<td>
            			<rich:dataTable id="myDataTableJunior" value="#{deanBean.list}" var="item" style=" width : 700px;">
            				
	        				<f:facet name="header">
	                			<h:outputText value="Schedule Table" />
	        				</f:facet>
        				
	        				<rich:column style=" width : 180px; height: 19px">
	        					<f:facet name="header">
	        						<h:outputText value="Hours/Days " />
	        					</f:facet>
	        					<h:outputText style="font-align:center;" value="#{item}" />	
	        					
	        				</rich:column>
                			
        				        				
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Monday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Tuesday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Wednesday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Thursday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 500px;">
                			<f:facet name="header">
                				<h:outputText value="Friday"/>
                			</f:facet>
        				 </rich:column>
      				
            			</rich:dataTable>
            		</td>
            	</tr>
            	<tr>
            		<td>
            			
            	  <table>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Course Info :"></h:outputText>
            	  		</td>
            	  		<td>
            	  			<h:inputText id="courseInfoTextFieldJunior" disabled="true" style=" width : 251px;"></h:inputText>
            	  		</td>
            	  	</tr>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Lecturer :"></h:outputText>
            	  		</td>
            	  		<td>
            	  			<h:inputText id="lecturerNameTextFieldJunior" disabled="true"></h:inputText>
            	  		</td>
            	  	</tr>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Credits :"></h:outputText>
            	  		</td>
            	  		<td>
            	  			<table>
            	  				<tr>
            	  				<td>
            	  					<h:inputText id="theoricCreditTextFieldJunior" disabled="true" style=" width : 60px;"></h:inputText>
            	  				</td>
            	  				<td>
            	  					<h:outputText value="+"></h:outputText>
            	  				</td>
            	  				<td>
            	  					<h:inputText id="practiceCreditTextFieldJunior" disabled="true" style=" width : 58px;"></h:inputText>
            	  				</td>
            	  				</tr>
            	  			</table>
            	  		</td>
            	  	</tr>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Room : "></h:outputText>
            	  		</td>
            	  		<td style=" width : 146px;">
            	  			<rich:comboBox  id="roomComboBoxJunior" value = "Choose Room" width="99">
            	  				<f:selectItems id="juniorRoom" value="#{classroomBean.selectItemListClassroom}"/>
            	  			</rich:comboBox>
            	  		</td>
            	  		
            	  	</tr>           	  	
            	  </table>
            		</td>
            	</tr>
            </table>			    				   	
            </rich:panel>
        </rich:tab>
        <rich:tab label="Senior">
            <rich:panel style=" height : 450px;">
            	   <table>
            	<tr>
            		<td>
            			 <rich:orderingList style=" width : 280px;">
	            	       	<rich:column  width="300">
					            <f:facet name="header">
					                <h:outputText value="Lecture Name " style="width:30%" />
					            </f:facet> 
	                		<h:outputText value="Theory of Automata"></h:outputText>
	            			</rich:column>
	            			<rich:column  width="300">
					            <f:facet name="header">
					                <h:outputText value="Lecturer Name "/>
					            </f:facet> 
	                			<h:outputText value="Süleyman SEVINC"></h:outputText>
	            			</rich:column>			   					   		
            	  		</rich:orderingList>
            		</td>
            		<td style=" width : 100px;">
            			
            		</td>
            		<td>
            			<rich:dataTable id="myDataTableSenior" value="#{deanBean.list}" var="item" style=" width : 700px;">
            				
	        				<f:facet name="header">
	                			<h:outputText value="Schedule Table" />
	        				</f:facet>
        				
	        				<rich:column style=" width : 180px; height: 19px">
	        					<f:facet name="header">
	        						<h:outputText value="Hours/Days " />
	        					</f:facet>
	        					<h:outputText style="font-align:center;" value="#{item}" />	
	        					
	        				</rich:column>
                			
        				        				
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Monday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Tuesday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Wednesday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Thursday"/>
                			</f:facet>
        				 </rich:column>
        				 <rich:column style=" width : 500px;">
                			<f:facet name="header">
                				<h:outputText value="Friday"/>
                			</f:facet>
        				 </rich:column>
      				
            			</rich:dataTable>
            		</td>
            	</tr>
            	
            	<tr>
            		<td>
            			
            	  <table>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Course Info :"></h:outputText>
            	  		</td>
            	  		<td>
            	  			<h:inputText id="courseInfoTextFieldSenior" disabled="true" style=" width : 251px;"></h:inputText>
            	  		</td>
            	  	</tr>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Lecturer :"></h:outputText>
            	  		</td>
            	  		<td>
            	  			<h:inputText id="lecturerNameTextFieldSenior" disabled="true"></h:inputText>
            	  		</td>
            	  	</tr>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Credits :"></h:outputText>
            	  		</td>
            	  		<td>
            	  			<table>
            	  				<tr>
            	  				<td>
            	  					<h:inputText id="theoricCreditTextFieldSenior" disabled="true" style=" width : 60px;"></h:inputText>
            	  				</td>
            	  				<td>
            	  					<h:outputText value="+"></h:outputText>
            	  				</td>
            	  				<td>
            	  					<h:inputText id="practiceCreditTextFieldSenior" disabled="true" style=" width : 58px;"></h:inputText>
            	  				</td>
            	  				</tr>
            	  			</table>
            	  		</td>
            	  	</tr>
            	  	<tr>
            	  		<td>
            	  			<h:outputText value="Room : "></h:outputText>
            	  		</td>
            	  		<td style=" width : 146px;">
            	  			<rich:comboBox  id="roomComboBoxSenior" value = "Choose Room" width="99">
            	  				<f:selectItems id="seniorRooms" value="#{classroomBean.selectItemListClassroom}"/>
            	  			</rich:comboBox>
            	  		</td>
            	  		
            	  	</tr>           	  	
            	  </table>
            		</td>
            	</tr>
            </table>			    				   	
            </rich:panel>
        </rich:tab>
			
		<rich:tab label="Dean Courses">
			<rich:panel style=" height : 450px;">
				<table style="background-color: gray; height : 178px;">
					<tr style=" height : 12px;">
						<td>
						</td>
					</tr>
					<tr style=" height : 48px;">
					<td style=" width : 309px; background-color: dark">						
						<h:form prependId="false">
					        <h:panelGrid columns="2">
					        <h:outputText value="Courses : " style="color:white"/>
					            
					            <h:selectOneMenu id="deanCourses"
								    value="#{deanCourseBean.selectedDeanCourse}" onchange="submit();"
								    valueChangeListener="#{deanCourseBean.handleValueChange}" style=" width : 194px;">
								    <f:selectItem itemValue="" itemLabel="Choose Course" />
								    <f:selectItems value="#{deanCourseBean.deanCourseList}" />
								</h:selectOneMenu>
								
								<h:outputText value="Lecturer" style="color:white"/>
					           
					            <h:selectOneMenu value="#{deanCourseBean.selectedDeanLecturer}" style=" width : 195px;">
					                <f:selectItems value="#{deanCourseBean.deanLecturerList}" />
					            </h:selectOneMenu>
			            		
			            		<h:outputText style="color:white" value="Theoric Hour : " />
			            		<h:inputText disabled="true" value="#{deanCourseBean.creditValueTheo}" />
			            		
			            		<h:outputText style="color:white" value="Prac. Hour : " />
			            		<h:inputText disabled="true" value="#{deanCourseBean.creditValuePrac}" />
			            		
			            		</h:panelGrid>
					            
					            
					            <table bgcolor="#566D7E">
					            	<tr >
					            	<td>
					            	</td>
					            		<td>
					            			
					            			<h:outputText value="Locking Operations" style="color:white;"></h:outputText>					            								            		
					            		</td>
					            		<td>
					            		</td>
					            		<td>
					            		</td>
					            	</tr>
					            	<tr style=" height : 22px;">
						            	<td>
						            	</td>
					            	</tr>	
					            	<tr>
					            	<td style=" width : 17px;">
					            	</td>
						            	<td>
						            		 <h:outputText value="Type : " style="color:white"/>
						            	</td>
						            	<td>
						            		
							            		<h:selectOneMenu id="operationTypeComboBox" 
							            		style=" width : 151px;" onchange="submit()" value="#{deanCourseBean.selectedOperation}"
							            		valueChangeListener="#{deanCourseBean.handleValueOperationChange}">
									            	<f:selectItem itemValue="Choose Operation" />
									            	<f:selectItem itemValue="Theory Operation"/>
									            	<f:selectItem itemValue="Practice Operation" />
								            	</h:selectOneMenu>
							            	
						            	</td>
						            	<td style=" width : 30px;">
						            	</td>
					            	</tr>
					            	<tr style=" height : 5px;">
					            	<td>
					            	</td>
					            	</tr>
					            	<tr style=" height : 9px;">
						            	<td>
						            	</td>
						            	<td>
						            		<h:outputLabel value="Days : " style="color:white">						            			
						            		</h:outputLabel>
						            	</td>
						            	<td>
						            		<h:selectOneMenu id="deanDaysComboBox"
						            			value="#{deanCourseBean.selectedDeanDay}"
						            		 	onchange="submit()" valueChangeListener="#{deanCourseBean.handleValueDayChange}">
						            			<f:selectItem itemValue="Choose Days" />
						            			<f:selectItem itemValue="Monday" />
						            			<f:selectItem itemValue="Tuesday" />
						            			<f:selectItem itemValue="Wednesday" />
						            			<f:selectItem itemValue="Thursday" />
						            			<f:selectItem itemValue="Friday" />
						            		</h:selectOneMenu>
						            	</td>
					            	</tr>
					            	<tr style=" height : 6px;">
						            	<td>
						            	</td>
					            	</tr>
					            	<tr>
						            	<td>
						            	</td>
						            	<td style=" width : 62px;">
						            		<h:outputLabel value="Start Hour : " style="color:white"></h:outputLabel>
						            	</td>
						            	<td>
						            		<h:selectOneMenu id="deanStartHour" value="#{deanCourseBean.selectedStartHour}"
						            		onchange="submit()" valueChangeListener="#{deanCourseBean.handleValueStartHourChange}">
						            			<f:selectItem itemValue="Choose Start Hour"/>
						            			<f:selectItem itemValue="1"/>
						            			<f:selectItem itemValue="2"/>
						            			<f:selectItem itemValue="3"/>
						            			<f:selectItem itemValue="4"/>
						            			<f:selectItem itemValue="5"/>
						            			<f:selectItem itemValue="6"/>
						            			<f:selectItem itemValue="7"/>
						            			<f:selectItem itemValue="8"/>
						            		</h:selectOneMenu>
						            	</td>
					            	</tr>
					            	<tr style=" height : 6px;">
						            	<td>
						            	</td>
					            	</tr>
					            	<tr>
					            		<td>
					            		</td>
					            		<td>
					            			<h:outputLabel value="End Hour : " style="color:white"></h:outputLabel>
					            		</td>
					            		<td>
					            			<h:selectOneMenu id="deanEndHour" value="#{deanCourseBean.selectedEndHour}" 
					            			onchange="submit()" valueChangeListener="#{deanCourseBean.handleValueEndHourChange}">
						            			<f:selectItem itemValue="Choose End Hour"/>
						            			<f:selectItem itemValue="1"/>
						            			<f:selectItem itemValue="2"/>
						            			<f:selectItem itemValue="3"/>
						            			<f:selectItem itemValue="4"/>
						            			<f:selectItem itemValue="5"/>
						            			<f:selectItem itemValue="6"/>
						            			<f:selectItem itemValue="7"/>
						            			<f:selectItem itemValue="8"/>
						            		</h:selectOneMenu>
					            		</td>
					            	</tr>
					            	<tr style=" height : 7px;">
						            	<td>
						            	</td>
					            	</tr>
					            	<tr>
						            	<td>
						            	</td>
						            	<td>
						            		<h:outputLabel value="Rooms :" style="color:white;"></h:outputLabel>
						            	</td>
						            	<td>
						            		<h:selectOneMenu id="deanRoomComboBox" style=" width : 127px;"
						            		value="#{deanCourseBean.selectedRoom}" 
						            		valueChangeListener="#{deanCourseBean.handleValueRoomChange}" onchange="submit()">
						            			<f:selectItem itemValue="Choose Room"/>
						            			<f:selectItems value="#{classroomBean.selectItemListClassroom}"/>
						            		</h:selectOneMenu>
						            	</td>
					            	</tr>
					            	<tr style=" height : 16px;">
						            	<td>
						            	</td>
					            	</tr>
					            	<tr bgcolor="#817679">
						            	<td>
						            	</td>
						            	<td>
						            		
						            	</td>
						            	<td>						            		
							            	<h:commandButton value="Submit" action="#{deanCourseBean.initDeanCourseTable}" onclick="submit()"></h:commandButton>
							            	    		
						            	</td>
						            	<td>
						            	</td>
					            	</tr>
					            	<tr style=" height : 17px;">
						            	<td>
						            	</td>
					            	</tr>				            
					            </table>
		            		</h:form>	     					
					</td>
					<td>
					</td>
					<td style=" width : 367px;background-color: lightgray">
						<rich:panel header="Table" style=" width : 650px; height:350px">						
						
						
							<rich:dataTable id="deanWeekTable" value="#{deanBean.list}" 
							style="width:600px; height : 53px;" var="item">
								<f:facet name="header">
		                			<h:outputText value="Schedule Table" />
		        				</f:facet>
	        				
		        				<rich:column style=" width : 180px; height: 19px">
		        					<f:facet name="header">
		        						<h:outputText value="Hours / Days" />
		        					</f:facet>
		        					<h:outputLabel value="#{item}" />	        					
		        				</rich:column>
	                			
	        				        				
	        				 <rich:column style=" width : 250px;">
	                			<f:facet name="header">
	                				<h:outputText value="Monday"/>
	                			</f:facet>
	                			<h:outputText value=""/>
	                			<!--<h:selectBooleanCheckbox value=""></h:selectBooleanCheckbox>-->
	                			                			            			
	        				 </rich:column>
	        				 <rich:column style=" width : 250px;">
	                			<f:facet name="header">
	                				<h:outputText value="Tuesday"/>
	                			</f:facet>
	                			<!--<h:selectBooleanCheckbox value="" />-->
	        				 </rich:column>
	        				 <rich:column style=" width : 250px;">
	                			<f:facet name="header">
	                				<h:outputText value="Wednesday"/>
	                			</f:facet>
	                			<!--<h:selectBooleanCheckbox value="" />-->
	        				 </rich:column>
	        				 <rich:column style=" width : 250px;">
	                			<f:facet name="header">
	                				<h:outputText value="Thursday"/>
	                			</f:facet>
	                			<!--<h:selectBooleanCheckbox value="" />-->
	        				 </rich:column>
	        				 <rich:column style=" width : 400px;">
	                			<f:facet name="header">
	                				<h:outputText value="Friday"/>
	                			</f:facet>
	                			<!--<h:selectBooleanCheckbox value="" />-->
	        				 </rich:column>
							</rich:dataTable>
							
							</rich:panel>
					</td>
						
											
					</tr>
				</table>
			</rich:panel>
		</rich:tab>
	</rich:tabPanel>
	</h:form>
</f:view>
</body>
</html>