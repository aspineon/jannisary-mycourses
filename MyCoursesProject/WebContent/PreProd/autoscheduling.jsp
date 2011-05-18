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

	<h:outputLabel value="Auto Scheduling" style="color:blue;font-family:Tahoma;font-style:bold;" />
	<br/>
	<br/>
	<table>
		<tr>
		<td>
			<h:outputLabel value="Year : " style="color:black;font-family:Tahoma;font-style:bold;font-size:small" />
		</td>
		<td>
		
		</td>
		<h:form>
		<td>			
		<h:selectOneMenu id="Year" onchange="submit();"
			 			 value="#{deanCourseBean.selectedYear}"
						 valueChangeListener="#{deanCourseBean.yearValueChange}" style=" width : 194px;">
			<f:selectItems value="#{deanCourseBean.yearList}" />
		</h:selectOneMenu>		
		</td>
		</h:form>
		</tr>
		
		<tr>
		<td>
			<h:outputLabel value="Semester : " style="color:black;font-family:Tahoma;font-style:bold;font-size:small" />
		</td>
		<td>
		
		</td>
		<h:form>
		<td>			
		<h:selectOneMenu id="Semester" onchange="submit();"
						 value="#{deanCourseBean.selectedSemester}"
						 valueChangeListener="#{deanCourseBean.semesterValueChange}" style=" width : 194px;">
			<f:selectItems value="#{deanCourseBean.semesterList}" />
		</h:selectOneMenu>		
		</td>
		</h:form>
		</tr>
	</table>
	<br/>
	
	<h:form>
	<rich:tabPanel switchType="Client" selectedTab="dc">
		<rich:tab label="Freshman" id="fr">
            <rich:panel style="height : 750px; background-color:#E0FFFF;">
            <table>
            	<tr style=" height : 167px;">
            		<td>
            			<h:form prependId="false">
            				<h:panelGrid columns="1" style="background-color:#82CAFF; width : 312px;">
		            				
		            				<h:outputLabel value="Freshman Schedule" style="FONT-FAMILY: 'Verdana'; FONT-SIZE: medium; FONT-WEIGHT: bold; color:#ffffff;"/>
		            				
		            				<h:panelGrid columns="2" style="background-color:#AFDCEC; width : 330px;">
		            				
		            				<h:outputLabel value="" />
		            				<table>
		            					<tr style=" height : 19px;">
		            						<td></td>
		            					</tr>
		            				</table>	
		            				
		            				<h:outputLabel value="" />
				            			<h:selectOneListbox id="freshmanListBox" style=" width : 174px; background-color:#CFECEC;" 
				            								value="#{deanCourseBean.selectedFreshmanCourse}"
				            								valueChangeListener="#{deanCourseBean.freshmanSplitChange}" onchange="submit()">
				            				<f:selectItems value="#{deanCourseBean.freshmanCourses}"/>	           					
				            			</h:selectOneListbox>
				            			
				            			
				            			<h:outputLabel value="" />
				            			<h:outputLabel value="" />
				            			<h:outputLabel value="" />
				            			<h:outputLabel value="" />
				            			
				            			<h:outputText value="Course : " />
				            			<h:inputText id="freshmanCourseTextField" disabled="true" style="background-color:#FFF8C6; width : 170px;" 
				            						 value="#{deanCourseBean.selectedFreshmanSplitCourse}"></h:inputText>
				            			
				            			<h:outputText value="Lecturer : " />
				            			<h:inputText id="freshmanLecturerTextField" disabled="true" style="background-color:#FFF8C6; width : 170px;" 
				            						 value="#{deanCourseBean.selectedFreshmanSplitLecturer}"></h:inputText>
				            			
				            			<h:outputText value="Theory : " />
				            			<h:inputText id="freshmanTeoCreditTextField" disabled="true" style="background-color:#FFF8C6;width : 50px;"
				            						 value="#{deanCourseBean.freshmanCreditValeuTeo}"></h:inputText>
				            			
				            			<h:outputText value="Practice : " />
				            			<h:inputText id= "freshmanPracCreditTextField" disabled="true" style="background-color:#FFF8C6; width:50px;"
				            						 value="#{deanCourseBean.freshmanCreditValuePrac}"/>
				            			
				            			<h:outputText value="Course Type : " />
				            			<h:selectOneMenu id="freshmanOperationTypeComboBox" style=" width : 151px;" 
				            							 value="#{deanCourseBean.selectedFreshmanOperation}"
								            			 valueChangeListener="#{deanCourseBean.freshmanOperationChange}" onchange="submit()">
										    <f:selectItems value="#{deanCourseBean.freshmanOperations}" />            	
										</h:selectOneMenu>
				            			
				            			
				            			<h:outputText value="Credit : " />
				            			<h:selectOneMenu id="freshmanCreditComboBox" 
				            							 value="#{deanCourseBean.selectedFreshmanCredit}"
				   										 valueChangeListener="#{deanCourseBean.freshmanCreditChange}" onchange="submit()">            				
				            				<f:selectItems value="#{deanCourseBean.freshmanCredits}"/>        			
				            			</h:selectOneMenu>
				            			
				            			<h:outputText value="Days : " />
				            			<h:selectOneMenu id="freshmanDaysComboBox" 
				            							 value="#{deanCourseBean.selectedFreshmanDay}"
				            							 valueChangeListener="#{deanCourseBean.freshmanDayChange}" onchange="submit()">
								      		<f:selectItems value="#{deanCourseBean.freshmanDays}" /> 
								        </h:selectOneMenu>
				            				            	
				            			<h:outputText value="StartHour : " />
				            			<h:selectOneMenu id="freshmanStartHourComboBox" 
				            							 value="#{deanCourseBean.selectedFreshmanStartHour}"
				            							 valueChangeListener="#{deanCourseBean.freshmanHourChange}" onchange="submit()">            				
				            				<f:selectItems  value="#{deanCourseBean.freshmanHours}"/>        			
				            			</h:selectOneMenu>
				            			
				            			
				            		</h:panelGrid>
				            					
				          			<h:panelGrid columns="3">
				            			<h:outputLabel value="asasasasasasasa" style="COLOR: #82CAFF;;"/>
				            			<h:commandButton id="freshmanSubmit" value="Submit" action="#{deanCourseBean.initFreshmanCourseTableEvent}" onclick="submit()"></h:commandButton>
									    <h:commandButton id="freshmanReset" value="Reset" action="#{deanCourseBean.clearFreshmanCourseTable}" onclick="submit()"></h:commandButton>
		            				</h:panelGrid>
		            				<h:panelGrid columns="3" style="background-color:#95B9C7; width : 330px;">
		            					<h:outputLabel value="Lock Course Hour" style="COLOR: #ffffff; FONT-SIZE: small; FONT-WEIGHT: bold; FONT-FAMILY: 'Verdana';"/>
		            					<h:outputLabel value="" />
		            					<h:outputLabel value="" />
		            					
		            					<h:outputLabel value="Hour Value :" />
		            					<h:selectOneMenu id="freshmanLockedHour" style="width : 139px;"
		            									 value="#{deanCourseBean.freshmanLockedHour}"
		            									 valueChangeListener="#{deanCourseBean.freshmanLockHourChange}" onchange="submit()">
		            						<f:selectItem itemValue="Choose Lock Hour"/>
		            						<f:selectItem itemValue="1"/>
		            						<f:selectItem itemValue="2"/>
		            						<f:selectItem itemValue="3"/>
		            						<f:selectItem itemValue="4"/>
		            						<f:selectItem itemValue="5"/>
		            						<f:selectItem itemValue="6"/>
		            						<f:selectItem itemValue="7"/>
		            						<f:selectItem itemValue="8"/>
		            					</h:selectOneMenu>
		            					<h:outputLabel value="" />
		            					
		            					<h:outputLabel value="Day Value :" />
		            					<h:selectOneMenu id="freshmanLockedDay" 
		            									 value="#{deanCourseBean.freshmanLockedDay}" style=" width : 139px;"
		            									 valueChangeListener="#{deanCourseBean.freshmanLockDayChange}" onchange="submit()">
		            						<f:selectItem itemValue="Choose Lock Day"/>
		            						<f:selectItem itemValue="Monday"/>
		            						<f:selectItem itemValue="Tuesday"/>
		            						<f:selectItem itemValue="Wednesday"/>
		            						<f:selectItem itemValue="Thursday"/>
		            						<f:selectItem itemValue="Friday"/>
		            					</h:selectOneMenu>
		            					<h:outputLabel value="" />
		            					
		            					<h:outputLabel value="" />
			            					<h:panelGrid columns="2">
			            					<h:commandButton value="Lock" action="#{deanCourseBean.freshmanLockOperation}" onclick="submit()"/>
			            					<h:commandButton value="Unlock All" action="#{deanCourseBean.freshmanUnlockOperation}" onclick="submit()"/>
			            					</h:panelGrid>
		            					<h:outputLabel value="" />
		            					<table>
		            						<tr style=" height : 12px;">
			            						<td style=" width : 2px;">
			            						</td>
		            						</tr>
		            					</table>
		            				</h:panelGrid>
		            				
            				</h:panelGrid>
            			</h:form>
            		</td>
            		<td style=" width : 100px;"></td>
            		<td>
            			<rich:dataTable id="freshmanDataTable" value="#{deanCourseBean.initFreshmanCourseTable}" var="item" style=" width : 700px;">	
	        				
	        				<f:facet name="header">
	                			<h:outputText value="Schedule Table" />
	        				</f:facet>
        				
	        				<rich:column style=" width : 180px; height: 19px; background-color:#FCDFFF;">
	        					<f:facet name="header">
	        						<h:outputText value="Hours/Days" />
	        					</f:facet>
	        					<h:outputText value="#{item[0]}" />
	        					
	        				</rich:column>
                			        				        				
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Monday"/>
                			</f:facet>
                			<h:outputText value="#{item[1]}" />
        				 </rich:column>
        				 <rich:column style=" width : 250px; background-color:#FFF8C6;">
                			<f:facet name="header">
                				<h:outputText value="Tuesday"/>
                			</f:facet>
                			<h:outputText value="#{item[2]}" />
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Wednesday"/>
                			</f:facet>
                			<h:outputText value="#{item[3]}" />
        				 </rich:column>
        				 <rich:column style=" width : 250px; background-color:#FFF8C6;">
                			<f:facet name="header">
                				<h:outputText value="Thursday"/>
                			</f:facet>
                			<h:outputText value="#{item[4]}" />
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Friday"/>
                			</f:facet>
                			<h:outputText value="#{item[5]}" />
        				 </rich:column>    				
            			</rich:dataTable>
            			
            		</td>
            	</tr>
            	
            	<!-- Freshman Girislerin yapilacagi yer!!! -->
            	<tr>
            		<td>           			
            	 
            		</td>
            	</tr>
            </table>
        	  
	      </rich:panel>
        </rich:tab>
        <rich:tab label="Sophomore" id="sp">
            <rich:panel style="height : 750px; background-color:#E0FFFF;">
            	   <table>
            	<tr>
            		<td>
            		<h:panelGrid columns="1" style="background-color:#82CAFF; width : 312px;">
            			<h:outputLabel value="Sophomore Schedule" style="FONT-FAMILY: 'Verdana'; FONT-SIZE: medium; FONT-WEIGHT: bold; color:#ffffff"/>
          			
            			<h:form prependId="false">
            				
	            				<h:panelGrid columns="2" style="background-color:#AFDCEC; width : 330px;">
	            					
	            					<h:outputLabel value="" />
		            				<table>
		            					<tr style=" height : 19px;">
		            						<td></td>
		            					</tr>
		            				</table>
	            					
	            					<h:outputLabel value="" />
			            			<h:selectOneListbox id="sophomoreListBox" style=" width : 173px; background-color:#CFECEC;"
			            							    value="#{deanCourseBean.selectedSophomoreCourse}"
			            								valueChangeListener="#{deanCourseBean.sophomoreSplitChange}" onchange="submit()">
			            				<f:selectItems value="#{deanCourseBean.sophomoreCourses}"/>	           					
			            			</h:selectOneListbox>
			            			
			            			<h:outputLabel value="" />
			            			<h:outputLabel value="" />
			            			<h:outputLabel value="" />
			            			<h:outputLabel value="" />
			            			
			            			<h:outputText value="Course : " />
			            			<h:inputText id="sophomoreCourseTextField" disabled="true" style="background-color:#FFF8C6; width : 170px;" 
			            					   	 value="#{deanCourseBean.selectedSophomoreSplitCourse}"></h:inputText>
			            			
			            			<h:outputText value="Lecturer : " />
			            			<h:inputText id="sophomoreLecturerTextField" disabled="true" style="background-color:#FFF8C6; width : 170px;" 
			            					 	 value="#{deanCourseBean.selectedSophomoreSplitLecturer}"></h:inputText>
			            			
			            			<h:outputText value="Theory : " />
			            			<h:inputText id="sophomoreTeoCreditTextField" disabled="true" style="background-color:#FFF8C6;width : 50px;"
			            						 value="#{deanCourseBean.sophomoreCreditValeuTeo}"></h:inputText>
			            			
			            			<h:outputText value="Practice : " />
			            			<h:inputText id= "sophomorePracCreditTextField" disabled="true" style="background-color:#FFF8C6; width:50px;"
			            						 value="#{deanCourseBean.sophomoreCreditValuePrac}"/>
			            			
			            			<h:outputText value="Course Type : " />
			            			<h:selectOneMenu id="sophomoreOperationTypeComboBox" style=" width : 151px;"
								            		 value="#{deanCourseBean.selectedSophomoreOperation}"
													 valueChangeListener="#{deanCourseBean.sophomoreOperationChange}" onchange="submit()">
						            	<f:selectItems value="#{deanCourseBean.sophomoreOperations}" />            	
									</h:selectOneMenu>
			            			
			            			<h:outputText value="Credit : " />
			            			<h:selectOneMenu id="sophomoreCreditComboBox" 
			            							 value="#{deanCourseBean.selectedSophomoreCredit}"
			            							 valueChangeListener="#{deanCourseBean.sophomoreCreditChange}" onchange="submit()">            				
			            				<f:selectItems value="#{deanCourseBean.sophomoreCredits}"/>        			
			            			</h:selectOneMenu>
			            			
			            			<h:outputText value="Days : " />
			            			<h:selectOneMenu id="sophomoreDaysComboBox" 
			            							 value="#{deanCourseBean.selectedSophomoreDay}"
			           								 valueChangeListener="#{deanCourseBean.sophomoreDayChange}" onchange="submit()">
							       		<f:selectItems value="#{deanCourseBean.sophomoreDays}" /> 
							        </h:selectOneMenu>
			            			
			            			<h:outputText value="StartHour : " />
			            			<h:selectOneMenu id="sophomoreStartHourComboBox" value="#{deanCourseBean.selectedSophomoreStartHour}">            				
			            				<f:selectItems value="#{deanCourseBean.sophomoreHours}"/>        			
			            			</h:selectOneMenu>
			            			
			            			
			            		</h:panelGrid>
		            	
		            	
		            
		            		<h:panelGrid columns="3">
		            		<h:outputLabel value="asdasdasdasdasd" style="color:#82CAFF;"/>
		            			<h:commandButton id="sophomoreSubmit" value="Submit" action="#{deanCourseBean.initSophomoreCourseTableEvent}" onclick="submit()"></h:commandButton>
							    <h:commandButton id="sophomoreReset" value="Reset" action="#{deanCourseBean.clearSophomoreCourseTable}" onclick="submit()"></h:commandButton>
            				</h:panelGrid>
            			
            			
	            		<h:panelGrid columns="3" style="background-color:#95B9C7; width : 330px;">
		            					<h:outputLabel value="Lock Course Hour" style="COLOR: #ffffff; FONT-SIZE: small; FONT-WEIGHT: bold; FONT-FAMILY: 'Verdana';"/>
		            					<h:outputLabel value="" />
		            					<h:outputLabel value="" />
		            					
		            					<h:outputLabel value="Hour Value :" />
		            					<h:selectOneMenu id="lockSophomoreHourCombo" 
		            									 value="#{deanCourseBean.sophomoreLockedHour}" style=" width : 139px;"
		            									 valueChangeListener="#{deanCourseBean.sophomoreLockHourChange}" onchange="submit()">
		            						<f:selectItem itemValue="Choose Lock Hour"/>
		            						<f:selectItem itemValue="1"/>
		            						<f:selectItem itemValue="2"/>
		            						<f:selectItem itemValue="3"/>
		            						<f:selectItem itemValue="4"/>
		            						<f:selectItem itemValue="5"/>
		            						<f:selectItem itemValue="6"/>
		            						<f:selectItem itemValue="7"/>
		            						<f:selectItem itemValue="8"/>
		            					</h:selectOneMenu>
		            					<h:outputLabel value="" />
		            					
		            					<h:outputLabel value="Day Value :" />
		            					<h:selectOneMenu id="lockSophomoreDayCombo" 
		            									 value="#{deanCourseBean.sophomoreLockedDay}" style=" width : 139px;"
		            									 valueChangeListener="#{deanCourseBean.sophomoreLockDayChange}" onchange="submit()">
		            						<f:selectItem itemValue="Choose Lock Day"/>
		            						<f:selectItem itemValue="Monday"/>
		            						<f:selectItem itemValue="Tuesday"/>
		            						<f:selectItem itemValue="Wednesday"/>
		            						<f:selectItem itemValue="Thursday"/>
		            						<f:selectItem itemValue="Friday"/>
		            					</h:selectOneMenu>
		            					<h:outputLabel value="" />
		            					
		            					<h:outputLabel value="" />
		            					<h:panelGrid columns="2">
		            						<h:commandButton value="Lock" action="#{deanCourseBean.sophomoreLockOperation}" onclick="submit()"/>		            					
			            					<h:commandButton value="Unlock All" action="#{deanCourseBean.sophomoreUnlockOperation}" onclick="submit()"/>
			            				</h:panelGrid>
		            					
		            					<h:outputLabel value="" />
		            					<table>
		            						<tr style=" height : 12px;">
			            						<td style=" width : 2px;">
			            						</td>
		            						</tr>
		            					</table>
		            				</h:panelGrid>
		            				</h:form>	
            			</h:panelGrid>
            		
            		</td>
            		<td style=" width : 100px;"></td>
            		<td>
		            			<rich:dataTable id="sophomoreDataTable" 
		            				value="#{deanCourseBean.initSophomoreCourseTable}" var="item" style=" width : 700px;">
		            				
			        				<f:facet name="header">
			                			<h:outputText value="Schedule Table" />
			        				</f:facet>
		        				
			        				<rich:column style=" width : 180px; height: 19px;background-color:#FCDFFF;">
			        					<f:facet name="header">
			        						<h:outputText value="Hours/Days " />
			        					</f:facet>
			        					<h:outputText style="font-align:center;" value="#{item[0]}" />	
			        					
			        				</rich:column>
		                			
		        				        				
		        				 <rich:column style=" width : 250px;">
		                			<f:facet name="header">
		                				<h:outputText value="Monday"/>
		                			</f:facet>
		                			<h:outputText style="font-align:center;" value="#{item[1]}" />
		        				 </rich:column>
		        				 <rich:column style=" width : 250px;background-color:#FFF8C6;">
		                			<f:facet name="header">
		                				<h:outputText value="Tuesday"/>
		                			</f:facet>
		                			<h:outputText style="font-align:center;" value="#{item[2]}" />
		        				 </rich:column>
		        				 <rich:column style=" width : 250px;">
		                			<f:facet name="header">
		                				<h:outputText value="Wednesday"/>
		                			</f:facet>
		                			<h:outputText style="font-align:center;" value="#{item[3]}" />
		        				 </rich:column>
		        				 <rich:column style=" width : 250px; background-color:#FFF8C6;">
		                			<f:facet name="header">
		                				<h:outputText value="Thursday"/>
		                			</f:facet>
		                			<h:outputText style="font-align:center;" value="#{item[4]}" />
		        				 </rich:column>
		        				 <rich:column style="width : 250px;">
		                			<f:facet name="header">
		                				<h:outputText value="Friday"/>
		                			</f:facet>
		                			<h:outputText style="font-align:center;" value="#{item[5]}" />
		        				 </rich:column>
      				
            			</rich:dataTable>
            			
            		</td>
            	</tr>
            	<tr>
            		<td>

            		</td>
            	</tr>
            </table>			    				   	
            </rich:panel>
        </rich:tab>
        <rich:tab label="Junior" id="jr">
            <rich:panel style="height : 750px; background-color:#E0FFFF;">
            	   <table>
            	<tr>
            		<td>
            			<h:panelGrid columns="1" style="background-color:#82CAFF;width : 312px;">
            			 <h:outputLabel value="Junior Schedule" style="FONT-FAMILY: 'Verdana'; FONT-SIZE: medium; FONT-WEIGHT: bold; color:#ffffff"/>
          			       			 
            			 <h:form prependId="false">
            			 
            			 	<h:panelGrid columns="2" style="background-color:#AFDCEC;width : 330px;">
            			 		
            			 		<h:outputLabel value="" />
		            				<table>
		            					<tr style=" height : 19px;">
		            						<td></td>
		            					</tr>
		            				</table>
            			 		
            			 		<h:outputLabel value="" />
		            			<h:selectOneListbox id="juniorListBox" style=" width : 174px; background-color:#CFECEC;" 
		            							value="#{deanCourseBean.selectedJuniorCourse}"
		            							valueChangeListener="#{deanCourseBean.juniorSplitChange}" onchange="submit()">
		            				<f:selectItems value="#{deanCourseBean.juniorCourses}"/>	           					
		            			</h:selectOneListbox>
		            			
		            			<h:outputText value="Course : " />
		            			<h:inputText id="juniorCourseTextField" disabled="true" style="background-color:#FFF8C6; width : 170px;" 
		            						 value="#{deanCourseBean.selectedJuniorSplitCourse}"></h:inputText>
		            			
		            			<h:outputText value="Lecturer : " />
		            			<h:inputText id="juniorLecturerTextField" disabled="true" style="background-color:#FFF8C6; width : 170px;" 
		            						 value="#{deanCourseBean.selectedJuniorSplitLecturer}"></h:inputText>
		            			
		            			<h:outputText value="Theory : " />
		            			<h:inputText id="juniorTeoCreditTextField" disabled="true" style="background-color:#FFF8C6;width : 50px;"
		            						 value="#{deanCourseBean.juniorCreditValueTeo}"></h:inputText>
		            			
		            			<h:outputText value="Practice : " />
		            			<h:inputText id= "juniorPracCreditTextField" disabled="true" style="background-color:#FFF8C6; width:50px;"
		            						 value="#{deanCourseBean.juniorCreditValuePrac}"/>
		            			
		            			<h:outputText value="Course Type : " />
		            			<h:selectOneMenu id="juniorOperationTypeComboBox" style=" width : 151px;"
							            		 value="#{deanCourseBean.selectedJuniorOperation}"
							            		 valueChangeListener="#{deanCourseBean.juniorOperationChange}" onchange="submit()">
									 <f:selectItems value="#{deanCourseBean.juniorOperations}" />            	
								</h:selectOneMenu>
		            			
		            			<h:outputText value="Credit : " />
		            			<h:selectOneMenu id="juniorCreditComboBox" 
		            							 value="#{deanCourseBean.selectedJuniorCredit}"
		            							 valueChangeListener="#{deanCourseBean.juniorCreditChange}" onchange="submit()">            				
		            				<f:selectItems value="#{deanCourseBean.juniorCredits}"/>        			
		            			</h:selectOneMenu>
		            			
		            			<h:outputText value="Days : " />
		            			<h:selectOneMenu id="juniorDaysComboBox" 
		            							 value="#{deanCourseBean.selectedJuniorDay}"
		           								 valueChangeListener="#{deanCourseBean.juniorDayChange}" onchange="submit()">
						            <f:selectItems value="#{deanCourseBean.juniorDays}" /> 
						       	</h:selectOneMenu>
		            			
		            			<h:outputText value="StartHour : " />
		            			<h:selectOneMenu id="juniorStartHourComboBox" 
		            							 value="#{deanCourseBean.selectedJuniorStartHour}">            				
		            				<f:selectItems  value="#{deanCourseBean.juniorHours}"/>        			
		            			</h:selectOneMenu>
		            			
		            		</h:panelGrid>
		            
		            	
		            		<h:panelGrid columns="3">
		            			<h:outputLabel value="asdasdasdasdasd" style="color:#82CAFF;"/>
		            			<h:commandButton id="juniorSubmit"  value="Submit" action="#{deanCourseBean.initJuniorCourseTableEvent}" onclick="submit()"></h:commandButton>
							    <h:commandButton id="juniorReset" value="Reset" action="#{deanCourseBean.clearJuniorCourseTable}" onclick="submit()"></h:commandButton>
            				</h:panelGrid>
            			
            			
            			<h:panelGrid columns="3" style="background-color:#95B9C7; width : 330px;">
		            					<h:outputLabel value="Lock Course Hour" style="COLOR: #ffffff; FONT-SIZE: small; FONT-WEIGHT: bold; FONT-FAMILY: 'Verdana';"/>
		            					<h:outputLabel value="" />
		            					<h:outputLabel value="" />
		            					
		            					<h:outputLabel value="Hour Value :" />
		            					<h:selectOneMenu id="lockJuniorHourCombo" 
		            									 value="#{deanCourseBean.juniorLockedHour}" style=" width : 139px;"
		            									 valueChangeListener="#{deanCourseBean.juniorLockHourChange}" onchange="submit()">
		            						<f:selectItem itemValue="Choose Lock Hour"/>
		            						<f:selectItem itemValue="1"/>
		            						<f:selectItem itemValue="2"/>
		            						<f:selectItem itemValue="3"/>
		            						<f:selectItem itemValue="4"/>
		            						<f:selectItem itemValue="5"/>
		            						<f:selectItem itemValue="6"/>
		            						<f:selectItem itemValue="7"/>
		            						<f:selectItem itemValue="8"/>
		            					</h:selectOneMenu>
		            					<h:outputLabel value="" />
		            					
		            					<h:outputLabel value="Day Value :" />
		            					<h:selectOneMenu id="lockJuniorDayCombo" 
		            									 value="#{deanCourseBean.juniorLockedDay}" style=" width : 139px;"
		            									 valueChangeListener="#{deanCourseBean.juniorLockDayChange}" onchange="submit()">
		            						<f:selectItem itemValue="Choose Lock Day"/>
		            						<f:selectItem itemValue="Monday"/>
		            						<f:selectItem itemValue="Tuesday"/>
		            						<f:selectItem itemValue="Wednesday"/>
		            						<f:selectItem itemValue="Thursday"/>
		            						<f:selectItem itemValue="Friday"/>
		            					</h:selectOneMenu>
		            					<h:outputLabel value="" />
		            					
		            					<h:outputLabel value="" />
		            						<h:panelGrid columns="2">
			            						<h:commandButton value="Lock" action="#{deanCourseBean.juniorLockOperation}" onclick="submit()"/>
			            						<h:commandButton value="Unlock All" action="#{deanCourseBean.juniorUnlockOperation}" onclick="submit()"/>
											</h:panelGrid>
		            					<h:outputLabel value="" />
		            					<table>
		            						<tr style=" height : 12px;">
			            						<td style=" width : 2px;">
			            						</td>
		            						</tr>
		            					</table>
		            				</h:panelGrid>
            			</h:form>
            		</h:panelGrid>
            		</td>
            		<td style=" width : 100px;"></td>
            		<td>
            			<rich:dataTable id="myDataTableJunior" value="#{deanCourseBean.initJuniorCourseTable}" var="item" style=" width : 700px;">
            				
	        				<f:facet name="header">
	                			<h:outputText value="Schedule Table" />
	        				</f:facet>
        				
	        				<rich:column style=" width : 180px; height: 19px;background-color:#FCDFFF;">
	        					<f:facet name="header">
	        						<h:outputText value="Hours/Days " />
	        					</f:facet>
	        					<h:outputText style="font-align:center;" value="#{item[0]}" />	
	        					
	        				</rich:column>
                			
        				        				
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Monday"/>
                			</f:facet>
                			<h:outputText style="font-align:center;" value="#{item[1]}" />
        				 </rich:column>
        				 <rich:column style=" width : 250px;background-color:#FFF8C6;">
                			<f:facet name="header">
                				<h:outputText value="Tuesday"/>
                			</f:facet>
                			<h:outputText style="font-align:center;" value="#{item[2]}" />
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Wednesday"/>
                			</f:facet>
                			<h:outputText style="font-align:center;" value="#{item[3]}" />
        				 </rich:column>
        				 <rich:column style=" width : 250px;background-color:#FFF8C6;">
                			<f:facet name="header">
                				<h:outputText value="Thursday"/>
                			</f:facet>
                			<h:outputText style="font-align:center;" value="#{item[4]}" />
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Friday"/>
                			</f:facet>
                			<h:outputText style="font-align:center;" value="#{item[5]}" />
        				 </rich:column>     				
            			</rich:dataTable>
            		</td>
            	</tr>
            	<tr>
            		<td>
            			            	  
            		</td>
            	</tr>
            </table>			    				   	
            </rich:panel>
        </rich:tab>
        <rich:tab label="Senior" id="sr">
            <rich:panel style="height : 750px; background-color:#E0FFFF;">
            	   <table>
            	<tr>
            		<td>
            			<h:panelGrid columns="1" style="background-color:#82CAFF;width : 312px;">
            			 <h:outputLabel value="Senior Schedule" style="FONT-FAMILY: 'Verdana'; FONT-SIZE: medium; FONT-WEIGHT: bold; color:#ffffff"/>
            		
            			 <h:form prependId="false">
            			 	
            			 	<h:panelGrid columns="2" style="background-color:#AFDCEC;width : 330px;">
            			 		
            			 		<h:outputLabel value="" />
		            				<table>
		            					<tr style=" height : 19px;">
		            						<td></td>
		            					</tr>
		            				</table>
            			 		
            			 		<h:outputLabel value="" />
		            			<h:selectOneListbox id="seniorListBox" style=" width : 174px; background-color:#CFECEC;" 
		            								value="#{deanCourseBean.selectedSeniorCourse}"
		            								valueChangeListener="#{deanCourseBean.seniorSplitChange}" onchange="submit()">
		            				<f:selectItems value="#{deanCourseBean.seniorCourses}"/>	           					
		            			</h:selectOneListbox>
		            			
		            			<h:outputText value="Course : " />
		            			<h:inputText id="seniorCourseTextField" disabled="true" style="background-color:#FFF8C6; width : 170px;" 
		            						 value="#{deanCourseBean.selectedSeniorSplitCourse}"></h:inputText>
		            			
		            			<h:outputText value="Lecturer : " />
		            			<h:inputText id="seniorLecturerTextField" disabled="true" style="background-color:#FFF8C6; width : 170px;" 
		            						 value="#{deanCourseBean.selectedSeniorSplitLecturer}"></h:inputText>
		            			
		            			<h:outputText value="Theory : " />
		            			<h:inputText id="seniorTeoCreditTextField" disabled="true" style="background-color:#FFF8C6;width : 50px;"
		            						 value="#{deanCourseBean.seniorCreditValueTeo}"></h:inputText>
		            			
		            			<h:outputText value="Practice : " />
		            			<h:inputText id= "seniorPracCreditTextField" disabled="true" style="background-color:#FFF8C6; width:50px;"
		            						 value="#{deanCourseBean.seniorCreditValuePrac}"/>
		            			
		            			<h:outputText value="Course Type : " />
		            			<h:selectOneMenu id="seniorOperationTypeComboBox" style=" width : 151px;"
							            		 value="#{deanCourseBean.selectedSeniorOperation}"
							            		 valueChangeListener="#{deanCourseBean.seniorOperationChange}" onchange="submit()">
						         	<f:selectItems value="#{deanCourseBean.seniorOperations}" />            	
								</h:selectOneMenu>
		            			
		            			<h:outputText value="Credit : " />
		            			<h:selectOneMenu id="seniorCreditComboBox" 
		            							 value="#{deanCourseBean.selectedSeniorCredit}"
		            							 valueChangeListener="#{deanCourseBean.seniorCreditChange}" onchange="submit()">            				
		            				<f:selectItems value="#{deanCourseBean.seniorCredits}"/>        			
		            			</h:selectOneMenu>
		            			
		            			<h:outputText value="Days : " />
		            			<h:selectOneMenu id="seniorDaysComboBox" 
		            							 value="#{deanCourseBean.selectedSeniorDay}"
		           								 valueChangeListener="#{deanCourseBean.seniorDayChange}" onchange="submit()">
						            <f:selectItems value="#{deanCourseBean.seniorDays}" /> 
						        </h:selectOneMenu>
		            			
		            			
		            			<h:outputText value="StartHour : " />
		            			<h:selectOneMenu id="seniorStartHourComboBox" value="#{deanCourseBean.selectedSeniorStartHour}">            				
		            				<f:selectItems  value="#{deanCourseBean.seniorHours}"/>        			
		            			</h:selectOneMenu>
		            			
		            		</h:panelGrid>
		            	
		            		<h:panelGrid columns="3">
		            			<h:outputLabel value="asdasdasdasdasd" style="color:#82CAFF;"/>
		            			<h:commandButton id="seniorSubmit"  value="Submit" action="#{deanCourseBean.initSeniorCourseTableEvent}" onclick="submit()"></h:commandButton>
							    <h:commandButton id="seniorReset" value="Reset" action="#{deanCourseBean.clearSeniorCourseTable}" onclick="submit()"></h:commandButton>
            				</h:panelGrid>	
            			
            			
            			<h:panelGrid columns="3" style="background-color:#95B9C7; width : 330px;">
		            					<h:outputLabel value="Lock Course Hour" style="COLOR: #ffffff; FONT-SIZE: small; FONT-WEIGHT: bold; FONT-FAMILY: 'Verdana';"/>
		            					<h:outputLabel value="" />
		            					<h:outputLabel value="" />
		            					
		            					<h:outputLabel value="Hour Value :" />
		            					<h:selectOneMenu id="lockSeniorHourCombo" 
		            									 value="#{deanCourseBean.seniorLockedHour}" style=" width : 139px;"
		            									 valueChangeListener="#{deanCourseBean.seniorLockHourChange}" onchange="submit()">
		            						<f:selectItem itemValue="Choose Lock Hour"/>
		            						<f:selectItem itemValue="1"/>
		            						<f:selectItem itemValue="2"/>
		            						<f:selectItem itemValue="3"/>
		            						<f:selectItem itemValue="4"/>
		            						<f:selectItem itemValue="5"/>
		            						<f:selectItem itemValue="6"/>
		            						<f:selectItem itemValue="7"/>
		            						<f:selectItem itemValue="8"/>
		            					</h:selectOneMenu>
		            					<h:outputLabel value="" />
		            					
		            					<h:outputLabel value="Day Value :" />
		            					<h:selectOneMenu id="lockSeniorDayCombo" 
		            									 value="#{deanCourseBean.seniorLockedDay}" style=" width : 139px;"
		            									 valueChangeListener="#{deanCourseBean.seniorLockDayChange}" onchange="submit()">
		            						<f:selectItem itemValue="Choose Lock Day"/>
		            						<f:selectItem itemValue="Monday"/>
		            						<f:selectItem itemValue="Tuesday"/>
		            						<f:selectItem itemValue="Wednesday"/>
		            						<f:selectItem itemValue="Thursday"/>
		            						<f:selectItem itemValue="Friday"/>
		            					</h:selectOneMenu>
		            					<h:outputLabel value="" />
		            					
		            					<h:outputLabel value="" />
		            						<h:panelGrid columns="2">
		            						<h:commandButton value="Lock" action="#{deanCourseBean.seniorLockOperation}" onclick="submit()"/>
		            						<h:commandButton value="Unlock All" action="#{deanCourseBean.seniorUnlockOperation}" onclick="submit()"/>
		            						</h:panelGrid>
		            					<h:outputLabel value="" />
		            					<table>
		            						<tr style=" height : 12px;">
			            						<td style=" width : 2px;">
			            						</td>
		            						</tr>
		            					</table>
		            				</h:panelGrid>
            			</h:form>
            		</h:panelGrid>
            		</td>
            		<td style=" width : 100px;"></td>
            		<td>
            			<rich:dataTable id="myDataTableSenior" value="#{deanCourseBean.initSeniorCourseTable}" var="item" style=" width : 700px;">
            				
	        				<f:facet name="header">
	                			<h:outputText value="Schedule Table" />
	        				</f:facet>
        				
	        				<rich:column style=" width : 180px; height: 19px;background-color:#FCDFFF;">
	        					<f:facet name="header">
	        						<h:outputText value="Hours/Days " />
	        					</f:facet>
	        					<h:outputText style="font-align:center;" value="#{item[0]}" />	
	        					
	        				</rich:column>
                			
        				        				
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Monday"/>
                			</f:facet>
                			<h:outputText style="font-align:center;" value="#{item[1]}" />
        				 </rich:column>
        				 <rich:column style=" width : 250px;background-color:#FFF8C6;">
                			<f:facet name="header">
                				<h:outputText value="Tuesday"/>
                			</f:facet>
                			<h:outputText style="font-align:center;" value="#{item[2]}" />
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Wednesday"/>
                			</f:facet>
                			<h:outputText style="font-align:center;" value="#{item[3]}" />
        				 </rich:column>
        				 <rich:column style=" width : 250px; background-color:#FFF8C6;">
                			<f:facet name="header">
                				<h:outputText value="Thursday"/>
                			</f:facet>
                			<h:outputText style="font-align:center;" value="#{item[4]}" />
        				 </rich:column>
        				 <rich:column style=" width : 250px;">
                			<f:facet name="header">
                				<h:outputText value="Friday"/>
                			</f:facet>
                			<h:outputText style="font-align:center;" value="#{item[5]}" />
        				 </rich:column>
      				
            			</rich:dataTable>
            		</td>
            	</tr>
            </table>			    				   	
            </rich:panel>
        </rich:tab>
			
		<rich:tab label="Dean Courses" id="dc">
			<rich:panel style=" height : 450px; background-color:#AFC7C7;">
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
					            
					            <h:selectOneMenu id="deanCourses" onchange="submit();"
								    value="#{deanCourseBean.selectedDeanCourse}"
								    valueChangeListener="#{deanCourseBean.deanValueChange}" style=" width : 194px;">
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
							            		style=" width : 151px;" value="#{deanCourseBean.selectedOperation}"
							            		>
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
						            		 	>
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
						            		>
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
					            			>
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
							            	<h:commandButton value="Reset" action="#{deanCourseBean.clearDeanCourseTable}" onclick="submit()"></h:commandButton>			            	    		
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
						<rich:panel header="" style=" width : 650px; height:350px">						
						
						
							<rich:dataTable id="deanWeekTable" value="#{deanCourseBean.initCourseTable}" 
							style="width:600px; height : 53px;" var="item">
								<f:facet name="header">
		                			<h:outputText value="Schedule Table" />
		        				</f:facet>
	        				
		        				<rich:column style=" width : 180px; height: 19px; background-color:#FCDFFF">
		        					<f:facet name="header">
		        						<h:outputText value="Hours / Days" />
		        					</f:facet>
		        					<h:outputLabel value="#{item[0]}" />	        					
		        				</rich:column>
	                				        				        				
	        				 <rich:column style=" width : 250px;">
	                			<f:facet name="header">
	                				<h:outputText value="Monday"/>
	                			</f:facet>
	                			<h:outputText value="#{item[1]}"/>
	                			<!--<h:selectBooleanCheckbox value=""></h:selectBooleanCheckbox>-->
	                			                			            			
	        				 </rich:column>
	        				 <rich:column style=" width : 250px; background-color:#FFF8C6;">
	                			<f:facet name="header">
	                				<h:outputText value="Tuesday"/>
	                			</f:facet>
	                			<h:outputText value="#{item[2]}"/>
	        				 </rich:column>
	        				 <rich:column style=" width : 250px;">
	                			<f:facet name="header">
	                				<h:outputText value="Wednesday"/>
	                			</f:facet>
	                			<h:outputText value="#{item[3]}"/>
	        				 </rich:column>
	        				 <rich:column style=" width : 250px; background-color:#FFF8C6">
	                			<f:facet name="header">
	                				<h:outputText value="Thursday"/>
	                			</f:facet>
	                			<h:outputText value="#{item[4]}"/>
	        				 </rich:column>
	        				 <rich:column style=" width : 400px;">
	                			<f:facet name="header">
	                				<h:outputText value="Friday"/>
	                			</f:facet>
	                			<h:outputText value="#{item[5]}"/>
	        				 </rich:column>
							</rich:dataTable>
							
							</rich:panel>
					</td>
																	
					</tr>
				</table>
			</rich:panel>
		</rich:tab>
		<rich:tab label="Run AutoScheduling & Save to Archive" id="at">
            <rich:panel style=" height : 450px;">
            
            	<rich:panel style="background-color:#CFECEC;" id="updateArchivePanelId">
            		
            			<f:facet name="label">
        					<h:outputLabel value="Select Year and Semester" style="FONT-WEIGHT: bold; FONT-SIZE: medium; FONT-FAMILY: 'Verdana';"/>
            			</f:facet>
            			  <h:panelGrid columns="1">
            			  		<h:outputLabel value="" />
									<h:outputLabel value="" />
			            			<h:commandButton id="AutoScheduling" value="RunAutoScheduling" action="#{deanCourseBean.runAutoScheduling}" onclick="submit()"></h:commandButton>	
		            			<h:outputLabel value="" />        			
	            			<h:panelGrid columns="3" style="background-color:#AFDCEC; width : 197px;">
	            				
	            				<h:outputLabel value="" />
	            				<table>
		            				<tr style=" height : 9px;">
		            					<td>		            						
		            					</td>
		            				</tr>
	            				</table>
	            				<h:outputLabel value="" />
	            				
	            				<table>
		            				<tr style="height : 2px; width : 30px;">
		            					<td>		            						
		            					</td>
		            				</tr>
	            				</table>
		            			<h:selectOneMenu id="autoArchiveYearComboBox" value="#{deanCourseBean.paramYearVal}" style=" width : 154px;">
		            				
		            				<f:selectItems value="#{deanCourseBean.yearList}"/>
		            			</h:selectOneMenu>
		            			<h:outputLabel value="" />
	            			
		            			<table>
		            				<tr style="height : 2px; width : 30px;">
		            					<td>		            						
		            					</td>
		            				</tr>
	            				</table>
		            			<h:selectOneMenu id="autoArchiveSemesterComboBox" value="#{deanCourseBean.paramSemesterVal}" style=" width : 154px;">
		            				
		            				<f:selectItems value="#{deanCourseBean.semesterList}"/>
		            			</h:selectOneMenu>
		            			<h:outputLabel value="" />
		            			
		            				
		            			<table>
		            				<tr style="height : 2px; width : 30px;">
		            					<td>		            						
		            					</td>
		            				</tr>
	            				</table>	
	            				<h:outputLabel value="" />
	            				
		            			<h:outputLabel value="" />
		            			<h:outputLabel value="Version Name : "/>
			            			<h:inputText value="" >
			            				<rich:ajaxValidator event="onblur"/>
			            			</h:inputText>
			            			<rich:message style="color:red;FONT-SIZE: small; FONT-FAMILY: 'Verdana';"></rich:message>
		            				<h:outputLabel value="" />
		            				<h:commandButton value="Save Archive" action="#{deanCourseBean.generateExcelReport}"/>
		            				
		            				<h:outputLabel value="" />
		            					            				            			            				
	            				<table>
		            				<tr style="height : 8px; width : 30px;">
		            					<td>		            						
		            					</td>
		            				</tr>
	            				</table>
								</h:panelGrid>								

            		</h:panelGrid>
            	</rich:panel>
            
            </rich:panel>
        </rich:tab>		
	</rich:tabPanel>
	</h:form>
</f:view>
</body>
</html>