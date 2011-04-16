package entities.business;
import java.awt.Color;
import java.util.*;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class DeanCourseBean 
{
	private List<SelectItem> deanCourseList;
	private List<SelectItem> deanLecturerList;
	
	String selectedDeanCourse = "";
	String selectedDeanLecturer;
	String selectedDeanDay = "";
	String selectedOperation = "";
	String selectedStartHour = "";
	String selectedEndHour = "";
	
	Color testColor = Color.green;
	
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
		selectedDeanCourse = newValue;		
	}
	
	public void handleValueOperationChange(ValueChangeEvent event)
	{
		System.out.println("Course Code : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		selectedOperation = newValue;		
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
