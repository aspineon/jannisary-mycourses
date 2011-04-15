package entities.business;
import java.util.*;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class DeanCourseBean 
{
	private List<SelectItem> deanCourseList;
	private List<SelectItem> deanLecturerList;
	String selectedDeanCourse = "";
	String selectedDeanLecturer;
	
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
	
	private void loadLecturer()
	{
		if(selectedDeanCourse == null)
			selectedDeanCourse = "";
		deanLecturerList = new ArrayList<SelectItem>();
		if(selectedDeanCourse.equals("PHY1002"))
		{
			deanLecturerList.add(new SelectItem("PHY1002-CSE", "Mehmet SAHIN"));
			
		}
		else if(selectedDeanCourse.equals("MAT1002"))
		{
			deanLecturerList.add(new SelectItem("MAT1002-CSE", "Mustafa GUZEL"));
			
		}
		else if(selectedDeanCourse.equals("TAR1002"))
		{
			deanLecturerList.add(new SelectItem("TAR1002-CSE", "Gurcan BOZKIR"));
		}
		else if(selectedDeanCourse.equals("TUR2002"))
		{
			deanLecturerList.add(new SelectItem("TUR2002-CSE", "Gulece BILEN"));
		}	
		else
		{
			deanLecturerList.add(new SelectItem("", "Dean Course did not select"));
		}
	}
	
}
