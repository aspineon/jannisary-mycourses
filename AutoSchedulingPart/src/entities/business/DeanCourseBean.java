package entities.business;
import java.awt.Color;
import java.util.*;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;

public class DeanCourseBean 
{
	private List<SelectItem> deanCourseList;
	private List<SelectItem> deanLecturerList;
	
	private Hashtable<String, Integer> dayMapToIndexHash;
	private Hashtable<String, String> courseInRoomsHash;
	
	public String[][] initCourseTable = new String[5][8];
	
	String selectedDeanCourse = "";
	String selectedDeanLecturer;
	String selectedDeanDay = "";
	String selectedOperation = "";
	String selectedStartHour = "";
	String selectedEndHour = "";
	String selectedRoom = "";
	boolean buttonStatus = false;
	
	Color testColor = Color.green;
	
	public String getSelectedRoom() {
		return selectedRoom;
	}

	public void setSelectedRoom(String selectedRoom) {
		this.selectedRoom = selectedRoom;
	}

	public String getSelectedOperation() {
		return selectedOperation;
	}

	public void setSelectedOperation(String selectedOperation) {
		this.selectedOperation = selectedOperation;
	}

	public String getSelectedStartHour() {
		return selectedStartHour;
	}

	public void setSelectedStartHour(String selectedStartHour) {
		this.selectedStartHour = selectedStartHour;
	}

	public String getSelectedEndHour() {
		return selectedEndHour;
	}

	public void setSelectedEndHour(String selectedEndHour) {
		this.selectedEndHour = selectedEndHour;
	}

	public String getSelectedDeanDay() {
		return selectedDeanDay;
	}

	public void setSelectedDeanDay(String selectedDeanDay) {
		this.selectedDeanDay = selectedDeanDay;
	}

	public Color getTestColor() {
		return testColor;
	}

	public void setTestColor(Color testColor) {
		this.testColor = testColor;
	}

	String creditValueTheo;
	String creditValuePrac;
	
	public DeanCourseBean()
	{
		deanCourseList = new ArrayList<SelectItem>();
		deanCourseList.add(new SelectItem("PHY1002", "Physics II"));
		deanCourseList.add(new SelectItem("MAT1002", "Calculus II"));
		deanCourseList.add(new SelectItem("TUR2002", "Turk Dili"));
		deanCourseList.add(new SelectItem("TAR1002", "Tarih II"));
		
		dayMapToIndexHash = new Hashtable<String, Integer>();
		dayMapToIndexHash.put("Monday", 0);
		dayMapToIndexHash.put("Tuesday", 1);
		dayMapToIndexHash.put("Wednesday", 2);
		dayMapToIndexHash.put("Thursday", 3);
		dayMapToIndexHash.put("Friday", 4);
		
	}

	public List<SelectItem> getDeanCourseList() {
		return deanCourseList;
	}

	public void setDeanCourseList(List<SelectItem> deanCourseList) {
		this.deanCourseList = deanCourseList;
	}

	public List<SelectItem> getDeanLecturerList() {
		if(deanLecturerList == null)
		{
			loadLecturer();
		}
		return deanLecturerList;
	}

	public String getCreditValueTheo() {
		return creditValueTheo;
	}

	public void setCreditValueTheo(String creditValueTheo) {
		this.creditValueTheo = creditValueTheo;
	}

	public String getCreditValuePrac() {
		return creditValuePrac;
	}

	public void setCreditValuePrac(String creditValuePrac) {
		this.creditValuePrac = creditValuePrac;
	}

	public void setDeanLecturerList(List<SelectItem> deanLecturerList) {
		this.deanLecturerList = deanLecturerList;
	}

	public String getSelectedDeanCourse() {
		return selectedDeanCourse;
	}

	public void setSelectedDeanCourse(String selectedDeanCourse) {
		this.selectedDeanCourse = selectedDeanCourse;
	}

	public String getSelectedDeanLecturer() {
		return selectedDeanLecturer;
	}

	public void setSelectedDeanLecturer(String selectedDeanLecturer) {
		this.selectedDeanLecturer = selectedDeanLecturer;
	}
	
	public String[][] getInitCourseTable() {
		return initCourseTable;
	}

	public void setInitCourseTable(String[][] initCourseTable) {
		this.initCourseTable = initCourseTable;
	}

	public boolean isButtonStatus() {
		return buttonStatus;
	}

	public void setButtonStatus(boolean buttonStatus) {
		this.buttonStatus = buttonStatus;
	}
	
	public String initDeanCourseTable()
	{
		try
		{			
			
			if(selectedOperation.equals("Theory Operation"))
			{
				System.out.println("Function 2*****");
				if(!selectedDeanDay.equals("Choose Days"))
				{
					
					if(!selectedStartHour.equals("Choose Start Hour") && !selectedEndHour.equals("Choose End Hour"))
					{
						
						int dayIndexOnCourseTable = dayMapToIndexHash.get(selectedDeanDay);
						int startHourOfDeanCourse = Integer.parseInt(selectedStartHour);
						int endHourOfDeanCourse = Integer.parseInt(selectedEndHour);
						
						int totalCourseHour = endHourOfDeanCourse - startHourOfDeanCourse;
						
						int startHourTableStatus = startHourOfDeanCourse - 1;
						
						for(int acc = 0; acc < totalCourseHour + 1; acc++)
						{	
							if(initCourseTable[dayIndexOnCourseTable][startHourTableStatus] == null)
							{
								initCourseTable[dayIndexOnCourseTable][startHourTableStatus++] = selectedDeanCourse + "(T)";
								System.out.println("Test Result ::: "+initCourseTable[startHourOfDeanCourse][dayIndexOnCourseTable]);							
							}
							else
							{
								System.out.println("Indices are not available");
							}
						}
						System.out.println(initCourseTable);
					}
				}
			}		
			else if(selectedOperation.equals("Practice Operation"))
			{
				if(!selectedDeanDay.equals("Choose Days"))
				{
					if(!selectedStartHour.equals("Choose Start Hour") && !selectedEndHour.equals("Choose End Hour"))
					{
						int dayIndexOnCourseTable = dayMapToIndexHash.get(selectedDeanDay);
						int startHourOfDeanCourse = Integer.parseInt(selectedStartHour);
						int endHourOfDeanCourse = Integer.parseInt(selectedEndHour);
						
						int totalCourseHour = endHourOfDeanCourse - startHourOfDeanCourse;
						int startHourTableStatus = startHourOfDeanCourse - 1;
						
						for(int acc = 0; acc < totalCourseHour + 1; acc++)
						{
							if(initCourseTable[dayIndexOnCourseTable][startHourTableStatus] == null)
							{
								initCourseTable[dayIndexOnCourseTable][startHourTableStatus++] = selectedDeanCourse + "(P)";
								System.out.println("Test Result ::: "+initCourseTable[startHourOfDeanCourse][dayIndexOnCourseTable]);							
							}
							else 
							{
								System.out.println("Secilen indexler dolu!!! *****");
							}
						}
					}
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	
	
	public void handleValueChange(ValueChangeEvent event)
	{
		System.out.println("Course Code : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		selectedDeanCourse = newValue;
		loadLecturer();
	}
	
	public void handleValueDayChange(ValueChangeEvent event)
	{
		System.out.println("Course Code : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		selectedDeanDay = newValue;		
	}
	
	public void handleValueOperationChange(ValueChangeEvent event)
	{
		System.out.println("Course Code : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		selectedOperation = newValue;	
		buttonStatus = false;
	}
	public void handleValueStartHourChange(ValueChangeEvent event)
	{
		System.out.println("Course Code : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		selectedStartHour = newValue;		
	}
	
	public void handleValueEndHourChange(ValueChangeEvent event)
	{
		System.out.println("Course Code : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		selectedEndHour = newValue;		
	}
	
	public void handleValueRoomChange(ValueChangeEvent event)
	{
		System.out.println("Course Code : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		selectedRoom = newValue;		
	}
	
	private void loadLecturer()
	{
		if(selectedDeanCourse == null)
			selectedDeanCourse = "";
		deanLecturerList = new ArrayList<SelectItem>();
		if(selectedDeanCourse.equals("PHY1002"))
		{
			deanLecturerList.add(new SelectItem("PHY1002-CSE", "Mehmet SAHIN"));
			deanLecturerList.add(new SelectItem("PHY1002-CSE", "Aylin YILDIZ"));
			creditValueTheo = "4";
			creditValuePrac = "0";
		}
		else if(selectedDeanCourse.equals("MAT1002"))
		{
			deanLecturerList.add(new SelectItem("MAT1002-CSE", "Mustafa GUZEL"));
			creditValueTheo = "4";
			creditValuePrac = "0";			
		}
		else if(selectedDeanCourse.equals("TAR1002"))
		{
			deanLecturerList.add(new SelectItem("TAR1002-CSE", "Gurcan BOZKIR"));
			creditValueTheo = "2";
			creditValuePrac = "0";
		}
		else if(selectedDeanCourse.equals("TUR2002"))
		{
			deanLecturerList.add(new SelectItem("TUR2002-CSE", "Gulece BILEN"));
			creditValueTheo = "2";
			creditValuePrac = "0";
		}	
		else
		{
			deanLecturerList.add(new SelectItem("", "No Course Selected"));
			creditValueTheo = "No Selection";
			creditValuePrac = "No Selection";
		}
	}
	
}
