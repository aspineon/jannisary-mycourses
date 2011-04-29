package entities.business;
import java.awt.Color;
import java.util.*;
import java.lang.Integer;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.dao.Course;
import entities.dao.Lecturer;
import entities.dao.Syllabus;
import entities.utility.OrderedTable;

public class DeanCourseBean 
{
	private String selectedYear = "";
	private String selectedSemester = "";
	
	private boolean yearFlag = false;
	private boolean semesterFlag = false;
	
	private ArrayList<SelectItem> yearList = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> semesterList = new ArrayList<SelectItem>();
	
	private ArrayList<SelectItem> deanCourseList = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> deanLecturerList = new ArrayList<SelectItem>();
	//bu dört alt alan her bir tab için ilgili tablonun verilerini tutmakta
	private ArrayList<SelectItem> freshmanCourses = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> freshmanLecturerList = new ArrayList<SelectItem>();
	
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
	
	private String selectedDeanLecturer;
	String selectedDeanDay = "";
	String selectedOperation = "";
	String selectedStartHour = "";
	String selectedEndHour = "";
	String selectedRoom = "";
	boolean buttonStatus = false;
	
	Color testColor = Color.green;
	
	//******************** Freshman Bilgileri ******************************************
	
	private String selectedFreshmanCourse = "";
	private String selectedFreshmanLecturer;
	private String selectedFreshmanDay = "";
	private String selectedFreshmanOperation = "";
	private String selectedFreshmanStartHour = "";
	private String selectedFreshmanEndHour = "";
	private String selectedFreshmanRoom = "";
	private String freshmanCreditValeuTeo = "";
	private String freshmanCreditValuePrac = "";
	
	//**********************************************************************************
	
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
//This is the event which holds the operations when a year is selected
	public void yearValueChange(ValueChangeEvent event) {
		System.out.println("Year Code : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedYear = newValue;
		if((this.selectedYear != "Choose Year") && (this.selectedYear != null)) {
			this.semesterFlag = false;
			this.clearAllComponents();
			this.loadSemester();	
		}
	}
//This is the event which holds the operations when a semester is selected
	public void semesterValueChange(ValueChangeEvent event) {
		System.out.println("Course Code : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedSemester = newValue;
		if((this.selectedSemester != "Choose Semester") && (this.selectedSemester != null)) {
			this.loadAllLists(this.selectedYear, this.selectedSemester);
		}
	}
//This is the event which holds the operations when a course selected in dean tab	
	public void handleValueChange(ValueChangeEvent event)
	{
		System.out.println("Course Code : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedDeanCourse = newValue;
		this.clearSubFields();
		this.loadSubFields();
	}
// This is the event which holds the operation when a freshman course selected in freshman tab!!!!
	public void freshmanCourseChange(ValueChangeEvent event)
	{
		System.out.println("Freshman course has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedFreshmanCourse = newValue;
		this.clearFreshmanSubFields();
		this.loadFreshmanSubFields();
	}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private void clearAllComponents() {
		this.semesterList = new ArrayList<SelectItem>();
		this.freshmanCourses = new ArrayList<SelectItem>();
		this.sophomoreCourses = new ArrayList<SelectItem>();
		this.juniorCourses = new ArrayList<SelectItem>();
		this.seniorCourses = new ArrayList<SelectItem>();
	}
	
	private void loadAllLists(String year, String semester) {
		try
		{
			int yearOfSyllabus = Integer.parseInt(year);
			this.loadDeanCourses(yearOfSyllabus, semester);
			this.loadCoursesByGrade(yearOfSyllabus, semester);
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
	}
//***************************************************************************************
	private void loadDeanCourses(int year, String semester) {
		this.deanCourseList = null;
		this.courseList = courseObj.getDeanCourses(year, semester);
		ArrayList<SelectItem> itemList = new ArrayList<SelectItem>();
		for(int i = 0; i < this.courseList.size(); i++) {
			String name = this.courseList.get(i).getCourseName();
			itemList.add(new SelectItem(name));
		}
		this.deanCourseList = itemList;
	}
//***************************************************************************************
	private void loadCoursesByGrade(int year, String semester) {
		OrderedTable ort = new OrderedTable();
		//First Year(Freshman) Courses are loaded
		ArrayList<OrderedTable> itemList = ort.getOrderedTableByGrade(year, semester, 1);
		for(int i = 0; i < itemList.size(); i++) {
			String courseName = itemList.get(i).getCourseName();
			String lecturerName = itemList.get(i).getLecturerName();
			String item = courseName + " : " + lecturerName;
			SelectItem sItem = new SelectItem(item);
			this.freshmanCourses.add(sItem);
		}
		//Second Year(Sophomore) Courses are loaded
		itemList = ort.getOrderedTableByGrade(year, semester, 2);
		for(int i = 0; i < itemList.size(); i++) {
			SelectItem item = new SelectItem(itemList.get(i));
			this.sophomoreCourses.add(item);
		}
		//Third Year(Junior) Courses are loaded
		itemList = ort.getOrderedTableByGrade(year, semester, 3);
		for(int i = 0; i < itemList.size(); i++) {
			SelectItem item = new SelectItem(itemList.get(i));
			this.juniorCourses.add(item);
		}
		//Fourth Year(Senior) Courses are loaded
		itemList = ort.getOrderedTableByGrade(year, semester, 4);
		for(int i = 0; i < itemList.size(); i++) {
			SelectItem item = new SelectItem(itemList.get(i));
			this.seniorCourses.add(item);
		}
	}
//***************************************************************************************
	private void loadSemester() {
		if(this.semesterFlag == false) {	
			this.semesterList.add(new SelectItem("Choose Semester"));
			this.semesterList.add(new SelectItem("Fall"));
			this.semesterList.add(new SelectItem("Spring"));
			this.semesterList.add(new SelectItem("Summer"));
			this.semesterFlag = true;
		}
	}
//***************************************************************************************
//Alt alanlar önce temizlenmeli bu yüzden course seçimi ile dolacak olan componentleri
//temizliyoruz(clearFields). Ardýndan ders seçimiyle birlikte bu alanlarý gerekli veriler
//ile dolduruyoruz.
	private void clearSubFields() {
		this.deanLecturerList = new ArrayList<SelectItem>();		
		this.creditValueTheo = "";
		this.creditValuePrac = "";
	}
		
	private void loadSubFields()
	{
		ArrayList<Syllabus> itemList = syllabusObj.getSyllabusByCourseName(this.selectedDeanCourse);
		this.creditValueTheo = Integer.toString(itemList.get(0).getCourse().getTeoricLectureHours());
		this.creditValuePrac = Integer.toString(itemList.get(0).getCourse().getPracticeLectureHourse());
		for(int i = 0; i < itemList.size(); i++) {
			this.deanLecturerList.add(new SelectItem(itemList.get(i).getLecturer().getLecturerName()));
		}
	}
//***************************************************************************************	
	private void clearFreshmanSubFields()
	{
		this.freshmanCourses = new ArrayList<SelectItem>();
		this.freshmanCreditValeuTeo = "";
		this.freshmanCreditValuePrac = "";
	}
	
	private void loadFreshmanSubFields()
	{
		ArrayList<Syllabus> itemList = syllabusObj.getSyllabusByCourseName(this.selectedFreshmanCourse);
		this.freshmanCreditValeuTeo = Integer.toString(itemList.get(0).getCourse().getTeoricLectureHours());
		this.freshmanCreditValuePrac = Integer.toString(itemList.get(0).getCourse().getPracticeLectureHourse());
		for(int i = 0; i < itemList.size(); i++)
		{
			this.freshmanLecturerList.add(new SelectItem(itemList.get(i).getLecturer().getLecturerName()));
		}
	}
//***************************************************************************************
//************************* GETTER-SETTER METHODS ***************************************	
// Onur Özey (Finished 29.04)
// This getter method returns the list of years(YEARLÝST) which are contained in syllabus 
// table. Via of 'checkList' utility method, duplication of year data is averted.
	public ArrayList<SelectItem> getYearList() {
		if(this.yearFlag == false)	{
			SelectItem stm = new SelectItem("Choose Year");
			this.yearList.add(stm);
			ArrayList<Syllabus> itemList = syllabusObj.getSyllabusAll();
			ArrayList<String> tempList = new ArrayList<String>();
			for(int i = 0; i < itemList.size(); i++) {
				String item = Integer.toString(itemList.get(i).getYear());
				if(this.checkList(tempList, item) == false) {
					tempList.add(item);
				}
			}
			for(int j = 0; j < tempList.size(); j++) {
				this.yearList.add(new SelectItem(tempList.get(j).toString()));
			}
			this.yearFlag = true; 
		}
		return this.yearList;
	}
// Set method of 'YEARLÝST' subfield	
	public void setYearList(ArrayList<SelectItem> yearList) {
		this.yearList = yearList;
	}
//***************************************************************************************
// Onur Özey (Finished 29.04)
// Get-Set Methods of 'SEMESTERLÝST' subfield 
	public ArrayList<SelectItem> getSemesterList() {
		return semesterList;
	}

	public void setSemesterList(ArrayList<SelectItem> semesterList) {
		this.semesterList = semesterList;
	}
	
//***************************************************************************************
//Dean Courses bu noktada bean e yükleniyor. Burada iþlem yok. 
//Course.java da gerçekleþiyor
	public ArrayList<SelectItem> getDeanCourseList() {
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
//***************************************************************************************	
	public String getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(String selectedYear) {
		this.selectedYear = selectedYear;
	}
//***************************************************************************************
	public String getSelectedSemester() {
		return selectedSemester;
	}

	public void setSelectedSemester(String selectedSemester) {
		this.selectedSemester = selectedSemester;
	}
		
//======================================================================================	
//***************************************************************************************
//******************* UTILITY FUNCTIONS *************************************************	
// Onur Özey (Finished 29.04)
// This method checks whether the related data(String item) is contained by related
// ArrayList(ArrayList<String> itemList).
	private boolean checkList(ArrayList<String> itemList, String item) {
		boolean breaker = false;
		int i = 0;
		while((breaker != true) && (i < itemList.size())) {
			if(itemList.get(i).equals(item)) {
				breaker = true;
			}
			i++;
		}
		return breaker;
	}
//***************************************************************************************
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//**************************** FRESHMAN GET-SET ***************************************
	public String getSelectedFreshmanCourse() {
		return selectedFreshmanCourse;
	}

	public void setSelectedFreshmanCourse(String selectedFreshmanCourse) {
		this.selectedFreshmanCourse = selectedFreshmanCourse;
	}

	public String getSelectedFreshmanLecturer() {
		return selectedFreshmanLecturer;
	}

	public void setSelectedFreshmanLecturer(String selectedFreshmanLecturer) {
		this.selectedFreshmanLecturer = selectedFreshmanLecturer;
	}

	public String getSelectedFreshmanDay() {
		return selectedFreshmanDay;
	}

	public void setSelectedFreshmanDay(String selectedFreshmanDay) {
		this.selectedFreshmanDay = selectedFreshmanDay;
	}

	public String getSelectedFreshmanOperation() {
		return selectedFreshmanOperation;
	}

	public void setSelectedFreshmanOperation(String selectedFreshmanOperation) {
		this.selectedFreshmanOperation = selectedFreshmanOperation;
	}

	public String getSelectedFreshmanStartHour() {
		return selectedFreshmanStartHour;
	}

	public void setSelectedFreshmanStartHour(String selectedFreshmanStartHour) {
		this.selectedFreshmanStartHour = selectedFreshmanStartHour;
	}

	public String getSelectedFreshmanEndHour() {
		return selectedFreshmanEndHour;
	}

	public void setSelectedFreshmanEndHour(String selectedFreshmanEndHour) {
		this.selectedFreshmanEndHour = selectedFreshmanEndHour;
	}

	public String getSelectedFreshmanRoom() {
		return selectedFreshmanRoom;
	}

	public void setSelectedFreshmanRoom(String selectedFreshmanRoom) {
		this.selectedFreshmanRoom = selectedFreshmanRoom;
	}

	public String getFreshmanCreditValeuTeo() {
		return freshmanCreditValeuTeo;
	}

	public void setFreshmanCreditValeuTeo(String freshmanCreditValeuTeo) {
		this.freshmanCreditValeuTeo = freshmanCreditValeuTeo;
	}

	public String getFreshmanCreditValuePrac() {
		return freshmanCreditValuePrac;
	}

	public void setFreshmanCreditValuePrac(String freshmanCreditValuePrac) {
		this.freshmanCreditValuePrac = freshmanCreditValuePrac;
	}

	public ArrayList<SelectItem> getFreshmanLecturerList() {
		return freshmanLecturerList;
	}

	public void setFreshmanLecturerList(ArrayList<SelectItem> freshmanLecturerList) {
		this.freshmanLecturerList = freshmanLecturerList;
	}
	
	 
}
