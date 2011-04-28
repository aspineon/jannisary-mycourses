package entities.business;
import java.awt.Color;
import java.util.*;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;

import entities.dao.Course;
import entities.dao.Lecturer;
import entities.dao.Syllabus;
import entities.utility.OrderedTable;

public class DeanCourseBean 
{
	private ArrayList<SelectItem> deanCourseList;
	private ArrayList<SelectItem> deanLecturerList = new ArrayList<SelectItem>();
	//bu dört alt alan her bir tab için ilgili tablonun verilerini tutmakta
	private ArrayList<SelectItem> freshmanCourses = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> sophomoreCourses = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> juniorCourses = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> seniorCourses = new ArrayList<SelectItem>();
	
	private String selectedDeanCourse = "";
	private String creditValueTheo = "";
	private String creditValuePrac = "";
	
	private ArrayList<Course> courseList;
	private Hashtable<String, Integer> dayMapToIndexHash;
	public String[][] initCourseTable = new String[8][6];

	Course courseObj = new Course();
	Syllabus syllabusObj = new Syllabus();
	
	String selectedDeanLecturer;
	String selectedDeanDay = "";
	String selectedOperation = "";
	String selectedStartHour = "";
	String selectedEndHour = "";
	String selectedRoom = "";
	boolean buttonStatus = false;
	
	Color testColor = Color.green;
	
	public DeanCourseBean()
	{
		deanCourseList = new ArrayList<SelectItem>();
		deanCourseList.add(new SelectItem("PHY1002", "Physics II"));
		deanCourseList.add(new SelectItem("MAT1002", "Calculus II"));
		deanCourseList.add(new SelectItem("TUR2002", "Turk Dili"));
		deanCourseList.add(new SelectItem("TAR1002", "Tarih II"));
		
		dayMapToIndexHash = new Hashtable<String, Integer>();
		dayMapToIndexHash.put("Monday", 1);
		dayMapToIndexHash.put("Tuesday", 2);
		dayMapToIndexHash.put("Wednesday", 3);
		dayMapToIndexHash.put("Thursday", 4);
		dayMapToIndexHash.put("Friday", 5);
		
		initCourseTable[0][0] = "1";
		initCourseTable[1][0] = "2";
		initCourseTable[2][0] = "3";
		initCourseTable[3][0] = "4";
		initCourseTable[4][0] = "5";
		initCourseTable[5][0] = "6";
		initCourseTable[6][0] = "7";
		initCourseTable[7][0] = "8";
				
			
	}
	
	public String clearDeanCourseTable()
	{
		try
		{
			initCourseTable = new String[8][6];
			initCourseTable[0][0] = "1";
			initCourseTable[1][0] = "2";
			initCourseTable[2][0] = "3";
			initCourseTable[3][0] = "4";
			initCourseTable[4][0] = "5";
			initCourseTable[5][0] = "6";
			initCourseTable[6][0] = "7";
			initCourseTable[7][0] = "8";
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return null;
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
						
						//int totalCourseHour = endHourOfDeanCourse - startHourOfDeanCourse;
						
						int startHourT = startHourOfDeanCourse - 1;
						int endHourT = endHourOfDeanCourse - 1;
						
						//for(int acc = 0; acc < totalCourseHour + 1; acc++)
						//{	
							if(initCourseTable[startHourT][dayIndexOnCourseTable] == null)
							{
								if(initCourseTable[endHourT][dayIndexOnCourseTable] == null)
								{
									initCourseTable[startHourT][dayIndexOnCourseTable] = selectedDeanCourse + "(T)";
									initCourseTable[endHourT][dayIndexOnCourseTable] = selectedDeanCourse + "(T)";
									//System.out.println("Test Result ::: "+initCourseTable[startHourOfDeanCourse][dayIndexOnCourseTable]);							
								}
							}
							else
							{
								System.out.println("Indices are not available");
							}
						//}
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
						
						//int totalCourseHour = endHourOfDeanCourse - startHourOfDeanCourse;
						
						int startHourT = startHourOfDeanCourse - 1;
						int endHourT = endHourOfDeanCourse - 1;
						
						//for(int acc = 0; acc < totalCourseHour + 1; acc++)
						//{	
							if(initCourseTable[startHourT][dayIndexOnCourseTable] == null)
							{
								if(initCourseTable[endHourT][dayIndexOnCourseTable] == null)
								{
									initCourseTable[startHourT][dayIndexOnCourseTable] = selectedDeanCourse + "(P)";
									initCourseTable[endHourT][dayIndexOnCourseTable] = selectedDeanCourse + "(P)";
									//System.out.println("Test Result ::: "+initCourseTable[startHourOfDeanCourse][dayIndexOnCourseTable]);							
								}
							}
							else
							{
								System.out.println("Indices are not available");
							}
						//}
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
		this.selectedDeanCourse = newValue;
		loadFields();
	}
	
	private void loadFields()
	{
		ArrayList<Syllabus> itemList = syllabusObj.getSyllabusByCourseName(this.selectedDeanCourse);
		this.creditValueTheo = Integer.toString(itemList.get(0).getCourse().getTeoricLectureHours());
		this.creditValuePrac = Integer.toString(itemList.get(0).getCourse().getPracticeLectureHourse());
		this.deanLecturerList = new ArrayList<SelectItem>();
		for(int i = 0; i < itemList.size(); i++) {
			this.deanLecturerList.add(new SelectItem(itemList.get(i).getLecturer().getLecturerName()));
		}
	}
//****************************************************************************
//Dean Courses bu noktada bean e yükleniyor. Burada iþlem yok. 
//Course.java da gerçekleþiyor
	public ArrayList<SelectItem> getDeanCourseList() {
		this.courseList = courseObj.getDeanCourses();
		ArrayList<SelectItem> itemList = new ArrayList<SelectItem>();
		for(int i = 0; i < this.courseList.size(); i++) {
			String name = this.courseList.get(i).getCourseName();
			itemList.add(new SelectItem(name));
		}
		this.deanCourseList = itemList;
		return this.deanCourseList;
	}

	public void setDeanCourseList(ArrayList<SelectItem> deanCourseList) {
		this.deanCourseList = deanCourseList;
	}
//*****************************************************************************
// Seçilen dersin hocasýný gösteren listenin get set metodlarý
	public ArrayList<SelectItem> getDeanLecturerList() {
		return deanLecturerList;
	}

	public void setDeanLecturerList(ArrayList<SelectItem> deanLecturerList) {
		this.deanLecturerList = deanLecturerList;
	}
//******************************************************************************	
	public String getCreditValueTheo() {
		return creditValueTheo;
	}

	public void setCreditValueTheo(String creditValueTheo) {
		this.creditValueTheo = creditValueTheo;
	}
//******************************************************************************
	public String getCreditValuePrac() {
		return creditValuePrac;
	}

	public void setCreditValuePrac(String creditValuePrac) {
		this.creditValuePrac = creditValuePrac;
	}
//******************************************************************************
// Seçili olan dekanlýk dersinin get-set metodlarý
	public String getSelectedDeanCourse() {
		return selectedDeanCourse;
	}

	public void setSelectedDeanCourse(String selectedDeanCourse) {
		this.selectedDeanCourse = selectedDeanCourse;
	}
//*****************************************************************************
//Burdan sonra bulunan dört blokta her tabda bulunan ders listelerindeki verilerin
//tutulduðu alt alanlarýn get-set metodlarý bulunmaktadýr.
//*****************************************************************************
	public ArrayList<SelectItem> getFreshmanCourses() {
		OrderedTable ort = new OrderedTable();
		ArrayList<OrderedTable> itemList = ort.getOrderedTableByGrade(1);
		for(int i = 0; i < itemList.size(); i++) {
			SelectItem item = new SelectItem(itemList.get(i));
			this.freshmanCourses.add(item);
		}
		return freshmanCourses;
	}

	public void setFreshmanCourses(ArrayList<SelectItem> freshmanCourses) {
		this.freshmanCourses = freshmanCourses;
	}
//*****************************************************************************
	public ArrayList<SelectItem> getSophomoreCourses() {
		return sophomoreCourses;
	}

	public void setSophomoreCourses(ArrayList<SelectItem> sophomoreCourses) {
		this.sophomoreCourses = sophomoreCourses;
	}
//*******************************************************************************
	public ArrayList<SelectItem> getJuniorCourses() {
		return juniorCourses;
	}

	public void setJuniorCourses(ArrayList<SelectItem> juniorCourses) {
		this.juniorCourses = juniorCourses;
	}
//*******************************************************************************
	public ArrayList<SelectItem> getSeniorCourses() {
		return seniorCourses;
	}

	public void setSeniorCourses(ArrayList<SelectItem> seniorCourses) {
		this.seniorCourses = seniorCourses;
	}
//*********************************************************************************
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
	//*********************************************

	//***********************************************
}
