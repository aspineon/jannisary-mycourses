<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich" %>
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
            			<rich:dataTable id="myDataTable" value="#{deanBean.list}" var="item" style=" width : 700px;">
            				
	        				<f:facet name="header">
	                			<h:outputText value="Schedule Table" />
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
		<rich:tab label="Dean Lectures">
			<rich:panel style="background-color:lightgray; width : 933px; height : 500px;">
			<h2 style="font-family:tahoma; color:blue;"> Locking Dean Courses </h2>
			
			<table>
				<tr>
					<td>
					
							
			<table style="background-color: graytext; color:white; width : 267px; height : 242px;">
								
				<tr style=" height : 3px;">
				<td>
				</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr style=" height : 1px;">
							<td></td>
							</tr>
							<tr>
							<td>
							</td>
								<td>
									<h:outputText value="Course : "></h:outputText>
								</td>
								<td>
								
									<rich:comboBox id="deanCourseComboBox" value="Choose Dean's Course" valueChangeListener="#{deanBean.selectionChanged}" onchange="submit()">
										<f:selectItems value="#{deanBean.selectItemListDeanCourse}"/>
									</rich:comboBox>
								</td>
							</tr>
							<tr style=" height : 7px;">
							<td></td>
							</tr>
							<tr>
							<td>
							</td>
								<td>
									<h:outputText value="Lecturer : "></h:outputText>
								
								</td>
								<td>
									<h:form>
									<rich:comboBox id="deanLecturerNameComboBox" value="Choose Lecturer">
										<f:selectItems value="#{deanBean.deanLecturerList}"/>
									</rich:comboBox>
									</h:form>
								</td>
							</tr>
							<tr style=" height : 17px;">
							<td></td>
							</tr>
							<tr style="background-color: gray">
								<td>
								</td>
								<td>
									<h:outputText value="Theorical "></h:outputText>
								</td>
								<td>
								</td>
							</tr>
							<tr style=" height : 7px;">
							<td></td>
							</tr>
							<tr style="background-color: gray">
								<td>
								</td>
								<td>
									<h:outputText value="Day : "></h:outputText>
								</td>
								
								<td>
									<rich:comboBox id="deanTheoDayComboBox" value="Choose Day">
										<f:selectItem itemValue="Choose Day"/>
										<f:selectItem itemValue="Monday"/>
										<f:selectItem itemValue="Tuesday"/>
										<f:selectItem itemValue="Wednesday"/>
										<f:selectItem itemValue="Thursday"/>
										<f:selectItem itemValue="Friday"/>
									</rich:comboBox>
								</td>
							</tr>
							<tr style=" height : 7px;">
								<td>
								</td>
							</tr>
							
							<tr style="background-color: gray">
								<td>
								</td>
								<td>
									<h:outputText value="Start Hour : "></h:outputText>
								</td>
								<td>
									<rich:comboBox id="deanTheoStartHourComboBox" value="Choose Start Hour">
										<f:selectItem itemValue="1"/>
										<f:selectItem itemValue="2"/>
										<f:selectItem itemValue="3"/>
										<f:selectItem itemValue="4"/>
										<f:selectItem itemValue="5"/>
										<f:selectItem itemValue="6"/>
										<f:selectItem itemValue="7"/>
										<f:selectItem itemValue="8"/>
									</rich:comboBox>
								</td>
							</tr>
							<tr style=" height : 7px;">
								<td>
								</td>
							</tr>
							<tr style="background-color: gray">			
							<td style=" width : 22px;">
							</td>
								<td>
									<h:outputText value="Credit : "></h:outputText>
								</td>
								<td>									
									<h:inputText disabled="true" style=" width : 52px;"></h:inputText>	
								</td>
							</tr>
							<tr style=" height : 17px;">
								<td>
								</td>
							</tr>
							<tr style=" height : 10px;">
								<td>
								</td>
								<td>
									<h:outputText value="Practice"></h:outputText>
								</td>
								<td>
								</td>
							</tr>
							<tr style=" height : 7px;">
								<td>
								</td>
							</tr>
							<tr >
								<td>
								</td>
								<td>
									<h:outputText value="Day : "></h:outputText>
								</td>
								<td>
									<rich:comboBox disabled="true" id="deanPracDayComboBox" value="Choose Day">
										<f:selectItem itemValue="Choose Day"/>
										<f:selectItem itemValue="Monday"/>
										<f:selectItem itemValue="Tuesday"/>
										<f:selectItem itemValue="Wednesday"/>
										<f:selectItem itemValue="Thursday"/>
										<f:selectItem itemValue="Friday"/>
									</rich:comboBox>
								</td>
							</tr>
							<tr style=" height : 7px;">
								<td>
								</td>
							</tr>
							<tr >
								<td>
								</td>
								<td>
									<h:outputText value="Start Hour : "></h:outputText>
								</td>
								<td>
									<rich:comboBox disabled="true" id="deanPracHoursComboBox" value="Choose Start Hour">
										<f:selectItem itemValue="1"/>
										<f:selectItem itemValue="2"/>
										<f:selectItem itemValue="3"/>
										<f:selectItem itemValue="4"/>
										<f:selectItem itemValue="5"/>
										<f:selectItem itemValue="6"/>
										<f:selectItem itemValue="7"/>
										<f:selectItem itemValue="8"/>
									</rich:comboBox>
								</td>
							</tr>
							<tr style=" height : 7px;">
								<td>
								</td>
							</tr>
							<tr style="background-color: gray">			
							<td style=" width : 22px;">
							</td>
								<td>
									<h:outputText value="Credit : "></h:outputText>
								</td>
								<td>									
									<h:inputText disabled="true" style=" width : 52px;"></h:inputText>	
								</td>
							</tr>
							<tr style=" height : 7px;">
								<td>
								</td>
							</tr>
							<tr style=" height : 33px;">
								<td>
								
								</td>
								<td>
									
								</td>
								
								<td style=" width : 115px;">
									<h:commandButton id="deanSubmitButton" value="Submit"></h:commandButton>
									<h:commandButton disabled="true" id ="deanApplyButton" value="Apply All"></h:commandButton>
									
								</td>
								<td>
																	</td>
								<td>
								</td>
							</tr>
						</table>					
					</td>
									
				</tr>
				
				<tr style="width: 20px; height : 33px;">
					<td>
					</td>
				</tr>				
								
			</table>
					
					</td>
					<td style=" width : 46px;">
					</td>
					<td style="background-color: silver; width : 513px;">
						<rich:dataTable id="deanScheduleTable" value="#{deanBean.list}" var="item" style="width:600px; height : 53px;">
							<f:facet name="header">
	                			<h:outputText value="Schedule Table" />
	        				</f:facet>
        				
	        				<rich:column style=" width : 180px; height: 19px">
	        					<f:facet name="header">
	        						<h:outputText value="Hours / Days" />
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
			</table>
			
		
			
				
			</rich:panel>
		</rich:tab>		
	</rich:tabPanel>
	</h:form>
</f:view>
</body>
</html>