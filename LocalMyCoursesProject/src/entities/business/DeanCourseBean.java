package entities.business;
import java.awt.Color;
import java.util.*;
import java.io.IOException;
import java.lang.Integer;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.hibernate.validator.NotEmpty;

import entities.dao.Course;
import entities.dao.Syllabus;

import entities.utility.*;

public class DeanCourseBean 
{
	
//************************** SUBFIELDS *************************************************	
// For general usage, these object are created. By passing them methods, related data
// is taken from these classes.
	Course courseObj = new Course();
	Syllabus syllabusObj = new Syllabus();
	ScheduleAtomic scheduleAtomicObj = new ScheduleAtomic();
// When year and semester values are selected, they will be attached to these subfields. 	
	private String selectedYear = ""; 												//1
	private String selectedSemester = "";											//2
// These subfields holds the actions state when selecting year and semester.
	private boolean yearFlag = false;
	private boolean semesterFlag = false;
	private boolean autoScheduleFlag = false;
	private boolean autoScheduleResultFlag = false;
// Year and semester data are hold in these subfields.
	private ArrayList<SelectItem> yearList = new ArrayList<SelectItem>(); 			//3
	private ArrayList<SelectItem> semesterList = new ArrayList<SelectItem>();		//4
// 'Dean Course' type courses in selected year and semester are stored in this subfield.	
	private ArrayList<SelectItem> deanCourseList = new ArrayList<SelectItem>(); 	//5
// After selection of dean course, lecturers of this course are stored in this subfield.
	private ArrayList<SelectItem> deanLecturerList = new ArrayList<SelectItem>();  	//6
//***************************************************************************************
// This eight subfields holds the data about courses in four grade.
	private ArrayList<SelectItem> freshmanCourses = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> sophomoreCourses = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> juniorCourses = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> seniorCourses = new ArrayList<SelectItem>();
//**************************************************************************************	
// These subfields holds data about the scheduling operations
	private ArrayList<ScheduleAtomic> freshmanSchedules = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> sophomoreSchedules = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> juniorSchedules = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> seniorSchedules = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> deanSchedules = new ArrayList<ScheduleAtomic>();
//*******************************************************************************************	
	private ArrayList<ScheduleAtomic> freshmanBackup = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> sophomoreBackup = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> juniorBackup = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> seniorBackup = new ArrayList<ScheduleAtomic>();
	
	private ArrayList<ScheduleAtomic> freshmanBackupMarked = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> sophomoreBackupMarked = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> juniorBackupMarked = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> seniorBackupMarked = new ArrayList<ScheduleAtomic>();
//*********************** DEAN ***************************************************************	
	private String selectedDeanCourse = "";
	private String creditValueTheo = "";
	private String creditValuePrac = "";
	
	//******************************************************************************
	private String paramYearVal = "";
	private String paramSemesterVal = "";
	
	private String paramVersion = "";
	//******************************************************************************
//******************************************************************************
	private ArrayList<SelectItem> freshmanOperations = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> sophomoreOperations = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> juniorOperations = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> seniorOperations = new ArrayList<SelectItem>();
	
	private ArrayList<SelectItem> freshmanDays = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> sophomoreDays = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> juniorDays = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> seniorDays = new ArrayList<SelectItem>();
	
	private ArrayList<SelectItem> freshmanHours = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> sophomoreHours = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> juniorHours = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> seniorHours = new ArrayList<SelectItem>();
	
	private ArrayList<SelectItem> freshmanCredits = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> sophomoreCredits = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> juniorCredits = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> seniorCredits = new ArrayList<SelectItem>();
//******************************************************************************
	private ArrayList<ScheduleAtomic> freshmanUnmarkedList = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> freshmanMarkedList = new ArrayList<ScheduleAtomic>();
	
	private ArrayList<ScheduleAtomic> sophomoreUnmarkedList = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> sophomoreMarkedList = new ArrayList<ScheduleAtomic>();
	
	private ArrayList<ScheduleAtomic> juniorUnmarkedList = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> juniorMarkedList = new ArrayList<ScheduleAtomic>();
	
	private ArrayList<ScheduleAtomic> seniorUnmarkedList = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> seniorMarkedList = new ArrayList<ScheduleAtomic>();
	
	private Hashtable<String, Integer> dayMapToIndexHash;
	private Hashtable<String, Integer> dayMapToIntegerHash;
//******************* MATRICES *************************************************
	public String[][] initCourseTable = new String[9][6];
	public String[][] initFreshmanCourseTable = new String[9][6];
	public String[][] initSophomoreCourseTable = new String[9][6];
	public String[][] initJuniorCourseTable = new String[9][6];
	public String[][] initSeniorCourseTable = new String[9][6];
	
	private Integer[][] controlFreshmanCourse = {{0, 0, 0, 0, 0},
			  									{0, 0, 0, 0, 0},
			  									{0, 0, 0, 0, 0},
			  									{0, 0, 0, 0, 0},
			  									{0, 0, 0, 0, 0},
			  									{0, 0, 0, 0, 0},
			  									{0, 0, 0, 0, 0},
			  									{0, 0, 0, 0, 0}};

	private Integer[][] controlSophomoreCourse = {{0, 0, 0, 0, 0},
											 	 {0, 0, 0, 0, 0},
											 	 {0, 0, 0, 0, 0},
											 	 {0, 0, 0, 0, 0},
											 	 {0, 0, 0, 0, 0},
											 	 {0, 0, 0, 0, 0},
											 	 {0, 0, 0, 0, 0},
											 	 {0, 0, 0, 0, 0}};

	private Integer[][] controlJuniorCourse = {{0, 0, 0, 0, 0},
											  {0, 0, 0, 0, 0},
											  {0, 0, 0, 0, 0},
											  {0, 0, 0, 0, 0},
											  {0, 0, 0, 0, 0},
											  {0, 0, 0, 0, 0},
											  {0, 0, 0, 0, 0},
											  {0, 0, 0, 0, 0}};

	private Integer[][] controlSeniorCourse = {{0, 0, 0, 0, 0},
										  	  {0, 0, 0, 0, 0},
										  	  {0, 0, 0, 0, 0},
										  	  {0, 0, 0, 0, 0},
										  	  {0, 0, 0, 0, 0},
										  	  {0, 0, 0, 0, 0},
										  	  {0, 0, 0, 0, 0},
										  	  {0, 0, 0, 0, 0}};
	
	
	private Integer[][] controlFreshmanLecturer = {{0, 0, 0, 0, 0},
												  {0, 0, 0, 0, 0},
												  {0, 0, 0, 0, 0},
												  {0, 0, 0, 0, 0},
												  {0, 0, 0, 0, 0},
												  {0, 0, 0, 0, 0},
												  {0, 0, 0, 0, 0},
												  {0, 0, 0, 0, 0}};
	
	private Integer[][] controlSophomoreLecturer = {{0, 0, 0, 0, 0},
			  									   {0, 0, 0, 0, 0},
			  									   {0, 0, 0, 0, 0},
			  									   {0, 0, 0, 0, 0},
			  									   {0, 0, 0, 0, 0},
			  									   {0, 0, 0, 0, 0},
			  									   {0, 0, 0, 0, 0},
			  									   {0, 0, 0, 0, 0}};
	
	private Integer[][] controlJuniorLecturer = {{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0}};
	
	private Integer[][] controlSeniorLecturer = {{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0},
			   									{0, 0, 0, 0, 0}};
	
	
	private Integer[][] controlFreshmanClassroom = {{0, 0, 0, 0, 0},
			   									   {0, 0, 0, 0, 0},
			   									   {0, 0, 0, 0, 0},
			   									   {0, 0, 0, 0, 0},
			   									   {0, 0, 0, 0, 0},
			   									   {0, 0, 0, 0, 0},
			   									   {0, 0, 0, 0, 0},
			   									   {0, 0, 0, 0, 0}};
	
	private Integer[][] controlSophomoreClassroom = {{0, 0, 0, 0, 0},
			   										{0, 0, 0, 0, 0},
			   										{0, 0, 0, 0, 0},
			   										{0, 0, 0, 0, 0},
			   										{0, 0, 0, 0, 0},
			   										{0, 0, 0, 0, 0},
			   										{0, 0, 0, 0, 0},
			   										{0, 0, 0, 0, 0}};
	
	private Integer[][] controlJuniorClassroom = {{0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0}};
	
	private Integer[][] controlSeniorClassroom = {{0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0},
			   									 {0, 0, 0, 0, 0}};
//******************************************************************************
	private String selectedDeanLecturer;
	private String selectedDeanDay = "";
	private String selectedDeanOperation = "";
	private String selectedDeanStartHour = "";
	private boolean deanStartFlag = false;
	private boolean deanOperationFlag = false;
	private boolean deanDayFlag = false;
	private boolean deanHourFlag = false;
	
	private Syllabus selectedDeanSyllabus;
	
	Color testColor = Color.green;
//**************** Freshman Subfields ***********************************************
	private String selectedFreshmanCourse = "";
	private String selectedFreshmanSplitCourse = "";
	private String selectedFreshmanSplitLecturer = "";
	private String selectedFreshmanLecturer;
	private String selectedFreshmanDay = "";
	private String selectedFreshmanOperation = "";
	private String selectedFreshmanStartHour = "";
	private String selectedFreshmanCredit = "";
	private String freshmanCreditValeuTeo = "";
	private String freshmanCreditValuePrac = "";
	
	private Syllabus selectedFreshmanSyllabus = null;
	private ScheduleAtomic selectedScheduleAtomicFreshman = null;
	private int atomicIndexFreshman = -1;
	private int topCreditFreshman = -1;
	private String optFlagFreshman = "";
	
	private String freshmanLockedDay = "";
	private String freshmanLockedHour = "";
	private boolean freshmanReadyToLock = false;
	private boolean freshmanLockDayFlag = false;
	private boolean freshmanLockHourFlag = false;
	private ArrayList<Index> freshmanLockedIndexes = new ArrayList<Index>();

	private boolean splitFlagFreshman = false;
	private boolean freshmanCreditFlag = false;
	private boolean freshmanDayFlag = false;
	private boolean freshmanHourFlag = false;
	
//**************** Sophomore Subfields **********************************************
	private String selectedSophomoreCourse = "";
	private String selectedSophomoreSplitCourse = "";
	private String selectedSophomoreSplitLecturer = "";
	private String selectedSophomoreLecturer;
	private String selectedSophomoreDay = "";
	private String selectedSophomoreOperation = "";
	private String selectedSophomoreStartHour = "";
	private String selectedSophomoreCredit = "";
	private String sophomoreCreditValeuTeo = "";
	private String sophomoreCreditValuePrac = "";
	
	private Syllabus selectedSophomoreSyllabus = null;
	private ScheduleAtomic selectedScheduleAtomicSophomore = null;
	private int atomicIndexSophomore = -1;
	private int topCreditSophomore = -1;
	private String optFlagSophomore = "";
	
	private String sophomoreLockedDay = "";
	private String sophomoreLockedHour = "";
	private boolean sophomoreReadyToLock = false;
	private boolean sophomoreLockDayFlag = false;
	private boolean sophomoreLockHourFlag = false;
	private ArrayList<Index> sophomoreLockedIndexes = new ArrayList<Index>();
	
	private boolean splitFlagSophomore = false;
	private boolean sophomoreCreditFlag = false;
	private boolean sophomoreDayFlag = false;
	private boolean sophomoreHourFlag = false;
	
//**************** Junior Subfields *************************************************
	private String selectedJuniorCourse = "";
	private String selectedJuniorSplitCourse = "";
	private String selectedJuniorSplitLecturer = "";
	private String selectedJuniorLecturer;
	private String selectedJuniorDay = "";
	private String selectedJuniorOperation = "";
	private String selectedJuniorStartHour = "";
	private String selectedJuniorCredit = "";
	private String juniorCreditValueTeo = "";
	private String juniorCreditValuePrac = "";
	
	private Syllabus selectedJuniorSyllabus = null;
	private ScheduleAtomic selectedScheduleAtomicJunior = null;
	private int atomicIndexJunior = -1;
	private int topCreditJunior = -1;
	private String optFlagJunior = "";
	
	private String juniorLockedDay = "";
	private String juniorLockedHour = "";
	private boolean juniorReadyToLock = false;
	private boolean juniorLockDayFlag = false;
	private boolean juniorLockHourFlag = false;
	private ArrayList<Index> juniorLockedIndexes = new ArrayList<Index>();
	
	private boolean splitFlagJunior = false;
	private boolean juniorCreditFlag = false;
	private boolean juniorDayFlag = false;
	private boolean juniorHourFlag = false;
	
//**************** Senior Subfields **************************************************
	private String selectedSeniorCourse = "";
	private String selectedSeniorSplitCourse = "";
	private String selectedSeniorSplitLecturer = "";
	private String selectedSeniorLecturer;
	private String selectedSeniorDay = "";
	private String selectedSeniorOperation = "";
	private String selectedSeniorStartHour = "";
	private String selectedSeniorCredit = "";
	private String seniorCreditValueTeo = "";
	private String seniorCreditValuePrac = "";
	
	private Syllabus selectedSeniorSyllabus = null;
	private ScheduleAtomic selectedScheduleAtomicSenior = null;
	private int atomicIndexSenior = -1;
	private int topCreditSenior = -1;
	private String optFlagSenior = "";
	
	private String seniorLockedDay = "";
	private String seniorLockedHour = "";
	private boolean seniorReadyToLock = false;
	private boolean seniorLockDayFlag = false;
	private boolean seniorLockHourFlag = false;
	private ArrayList<Index> seniorLockedIndexes = new ArrayList<Index>();
	
	private boolean splitFlagSenior = false;
	private boolean seniorCreditFlag = false;
	private boolean seniorDayFlag = false;
	private boolean seniorHourFlag = false;
	
//*************************************************************************************************
//******************** CONSTRUCTOR ***************************************************	
	public DeanCourseBean()
	{	
		this.dayMapToIndexHash = new Hashtable<String, Integer>();
		this.dayMapToIndexHash.put("Monday", 1);
		this.dayMapToIndexHash.put("Tuesday", 2);
		this.dayMapToIndexHash.put("Wednesday", 3);
		this.dayMapToIndexHash.put("Thursday", 4);
		this.dayMapToIndexHash.put("Friday", 5);
		
		this.dayMapToIntegerHash = new Hashtable<String, Integer>();
		this.dayMapToIntegerHash.put("Monday", 0);
		this.dayMapToIntegerHash.put("Tuesday", 1);
		this.dayMapToIntegerHash.put("Wednesday", 2);
		this.dayMapToIntegerHash.put("Thursday", 3);
		this.dayMapToIntegerHash.put("Friday", 4);
		
		initCourseTable[0][0] = "1";
		initCourseTable[1][0] = "2";
		initCourseTable[2][0] = "3";
		initCourseTable[3][0] = "4";
		initCourseTable[4][0] = "5";
		initCourseTable[5][0] = "6";
		initCourseTable[6][0] = "7";
		initCourseTable[7][0] = "8";	
		
		for(int i = 0; i < 8; i++) {
			for(int j = 1; j < 6; j++) {
				initCourseTable[i][j] = "";
			}
		}
		
		initFreshmanCourseTable[0][0] = "1";
		initFreshmanCourseTable[1][0] = "2";
		initFreshmanCourseTable[2][0] = "3";
		initFreshmanCourseTable[3][0] = "4";
		initFreshmanCourseTable[4][0] = "5";
		initFreshmanCourseTable[5][0] = "6";
		initFreshmanCourseTable[6][0] = "7";
		initFreshmanCourseTable[7][0] = "8";
		
		for(int i = 0; i < 8; i++) {
			for(int j = 1; j < 6; j++) {
				initFreshmanCourseTable[i][j] = "";
			}
		}
		
		initSophomoreCourseTable[0][0] = "1";
		initSophomoreCourseTable[1][0] = "2";
		initSophomoreCourseTable[2][0] = "3";
		initSophomoreCourseTable[3][0] = "4";
		initSophomoreCourseTable[4][0] = "5";
		initSophomoreCourseTable[5][0] = "6";
		initSophomoreCourseTable[6][0] = "7";
		initSophomoreCourseTable[7][0] = "8";
		
		for(int i = 0; i < 8; i++) {
			for(int j = 1; j < 6; j++) {
				initSophomoreCourseTable[i][j] = "";
			}
		}
		
		initJuniorCourseTable[0][0] = "1";
		initJuniorCourseTable[1][0] = "2";
		initJuniorCourseTable[2][0] = "3";
		initJuniorCourseTable[3][0] = "4";
		initJuniorCourseTable[4][0] = "5";
		initJuniorCourseTable[5][0] = "6";
		initJuniorCourseTable[6][0] = "7";
		initJuniorCourseTable[7][0] = "8";
		
		for(int i = 0; i < 8; i++) {
			for(int j = 1; j < 6; j++) {
				initJuniorCourseTable[i][j] = "";
			}
		}
		
		initSeniorCourseTable[0][0] = "1";
		initSeniorCourseTable[1][0] = "2";
		initSeniorCourseTable[2][0] = "3";
		initSeniorCourseTable[3][0] = "4";
		initSeniorCourseTable[4][0] = "5";
		initSeniorCourseTable[5][0] = "6";
		initSeniorCourseTable[6][0] = "7";
		initSeniorCourseTable[7][0] = "8";
		
		for(int i = 0; i < 8; i++) {
			for(int j = 1; j < 6; j++) {
				initSeniorCourseTable[i][j] = "";
			}
		}
	}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//******************** TABLE CLEANING ************************************************
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
			
			for(int i = 0; i < 8; i++) {
				for(int j = 1; j < 6; j++) {
					initCourseTable[i][j] = "";
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return null;
	}	
	
	public String clearFreshmanCourseTable()
	{
		try
		{
			initFreshmanCourseTable = new String[8][6];
			initFreshmanCourseTable[0][0] = "1";
			initFreshmanCourseTable[1][0] = "2";
			initFreshmanCourseTable[2][0] = "3";
			initFreshmanCourseTable[3][0] = "4";
			initFreshmanCourseTable[4][0] = "5";
			initFreshmanCourseTable[5][0] = "6";
			initFreshmanCourseTable[6][0] = "7";
			initFreshmanCourseTable[7][0] = "8";
			
			for(int i = 0; i < 8; i++) {
				for(int j = 1; j < 6; j++) {
					initFreshmanCourseTable[i][j] = "";
				}
			}
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
		return null;
	}
	
	public String clearSophomoreCourseTable()
	{
		try
		{
			initSophomoreCourseTable = new String[8][6];
			initSophomoreCourseTable[0][0] = "1";
			initSophomoreCourseTable[1][0] = "2";
			initSophomoreCourseTable[2][0] = "3";
			initSophomoreCourseTable[3][0] = "4";
			initSophomoreCourseTable[4][0] = "5";
			initSophomoreCourseTable[5][0] = "6";
			initSophomoreCourseTable[6][0] = "7";
			initSophomoreCourseTable[7][0] = "8";
			
			for(int i = 0; i < 8; i++) {
				for(int j = 1; j < 6; j++) {
					initSophomoreCourseTable[i][j] = "";
				}
			}
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
		return null;
	}
	
	public String clearJuniorCourseTable()
	{
		try
		{
			initJuniorCourseTable = new String[8][6];
			initJuniorCourseTable[0][0] = "1";
			initJuniorCourseTable[1][0] = "2";
			initJuniorCourseTable[2][0] = "3";
			initJuniorCourseTable[3][0] = "4";
			initJuniorCourseTable[4][0] = "5";
			initJuniorCourseTable[5][0] = "6";
			initJuniorCourseTable[6][0] = "7";
			initJuniorCourseTable[7][0] = "8";
			
			for(int i = 0; i < 8; i++) {
				for(int j = 1; j < 6; j++) {
					initJuniorCourseTable[i][j] = "";
				}
			}
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
		return null;
	}
	
	public String clearSeniorCourseTable()
	{
		try
		{
			initSeniorCourseTable = new String[8][6];
			initSeniorCourseTable[0][0] = "1";
			initSeniorCourseTable[1][0] = "2";
			initSeniorCourseTable[2][0] = "3";
			initSeniorCourseTable[3][0] = "4";
			initSeniorCourseTable[4][0] = "5";
			initSeniorCourseTable[5][0] = "6";
			initSeniorCourseTable[6][0] = "7";
			initSeniorCourseTable[7][0] = "8";
			
			for(int i = 0; i < 8; i++) {
				for(int j = 1; j < 6; j++) {
					initSeniorCourseTable[i][j] = "";
				}
			}
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
		return null;
	}
// ******************** INITIALIZING DEAN COURSES TAB ***********************************
	public String initDeanCourseTable()
	{
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.clearDeanCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		if((this.deanStartFlag == true) && (this.deanOperationFlag == true) && (this.deanDayFlag == true) && (this.deanHourFlag == true)) {
			String type = "";
			if(this.selectedDeanOperation.equals("Theory")) { type = "Theo"; }
			if(this.selectedDeanOperation.equals("Practice")) { type = "Prac"; }
			int index = this.findRelatedAtomic("Dean", "", "SEEK", this.selectedDeanSyllabus, type, 0);
			ScheduleAtomic item = new ScheduleAtomic(this.deanSchedules.get(index));
			item.setStartHour(Integer.parseInt(this.selectedDeanStartHour));
			item.setDay(this.selectedDeanDay);
			
			int hour = item.getStartHour() - 1;
			int day = this.dayMapToIntegerHash.get(item.getDay());
			int limit = hour + item.getCredit();
			
			String grade = "";
			if(item.getGrade() == 1) { grade = "Freshman"; }
			if(item.getGrade() == 2) { grade = "Sophomore"; }
			if(item.getGrade() == 3) { grade = "Junior"; }
			if(item.getGrade() == 4) { grade = "Senior"; }
			
			boolean controlCheck = true;
			for(int h = hour; ((controlCheck != false) && (h < limit)); h++) {
				controlCheck = this.controlCourse(grade, day, h);
			}
					
			for(int j = hour; ((controlCheck != false) && (j < limit)); j++) {
				controlCheck = this.controlLecturer(grade, day, j, item.getLecturerId());
			} 
			for(int k = hour; ((controlCheck != false) && (k < limit)); k++) {
				controlCheck = this.controlClassroom(grade, day, k, item.getClassroomId());
			}
			
			if(controlCheck == true) {
				int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
				int hourForward = item.getStartHour() - 1;
				if(item.isAttendance() == true) {
					for(int p = 0; p < item.getCredit(); p++) { 
						if(item.getGrade() == 1) {
							this.controlFreshmanCourse[hourForward][dayMatrix] = item.getCourseId();
							if(initFreshmanCourseTable[hourForward][dayMatrix].equals("")) {
								this.initFreshmanCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
							}
							else {
								this.initFreshmanCourseTable[hourForward][dayMatrix + 1] = this.initFreshmanCourseTable[hourForward][dayMatrix] + "   " + item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = this.initCourseTable[hourForward][dayMatrix + 1] + "  " + item.writeInfo();
							}
						}
						if(item.getGrade() == 2) {
							this.controlSophomoreCourse[hourForward][dayMatrix] = item.getCourseId();
							if(initSophomoreCourseTable[hourForward][dayMatrix].equals("")) {
								this.initSophomoreCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
							}
							else {
								this.initSophomoreCourseTable[hourForward][dayMatrix + 1] = this.initSophomoreCourseTable[hourForward][dayMatrix] + "   " + item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = this.initCourseTable[hourForward][dayMatrix + 1] + "  " + item.writeInfo();
							}
						}
						if(item.getGrade() == 3) {
							this.controlJuniorCourse[hourForward][dayMatrix] = item.getCourseId();
							if(initJuniorCourseTable[hourForward][dayMatrix].equals("")) {
								this.initJuniorCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
							}
							else {
								this.initJuniorCourseTable[hourForward][dayMatrix + 1] = this.initJuniorCourseTable[hourForward][dayMatrix] + "   " + item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = this.initCourseTable[hourForward][dayMatrix + 1] + "  " + item.writeInfo();
							}
						}
						if(item.getGrade() == 4) {
							this.controlSeniorCourse[hourForward][dayMatrix] = item.getCourseId();
							if(initSeniorCourseTable[hourForward][dayMatrix].equals("")) {
								this.initSeniorCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
							}
							else {
								this.initSeniorCourseTable[hourForward][dayMatrix + 1] = this.initSeniorCourseTable[hourForward][dayMatrix] + "   " + item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = this.initCourseTable[hourForward][dayMatrix + 1] + "  " + item.writeInfo();
							}
						}
						hourForward++;
					}
				}
				else {
					for(int p = 0; p < item.getCredit(); p++) { 
						if(item.getGrade() == 1) {
							
							if(initFreshmanCourseTable[hourForward][dayMatrix].equals("")) {
								this.initFreshmanCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
							}
							else {
								this.initFreshmanCourseTable[hourForward][dayMatrix + 1] = this.initFreshmanCourseTable[hourForward][dayMatrix] + "   " + item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = this.initCourseTable[hourForward][dayMatrix + 1] + "  " + item.writeInfo();
							}
						}
						if(item.getGrade() == 2) {
							
							if(initSophomoreCourseTable[hourForward][dayMatrix].equals("")) {
								this.initSophomoreCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
							}
							else {
								this.initSophomoreCourseTable[hourForward][dayMatrix + 1] = this.initSophomoreCourseTable[hourForward][dayMatrix] + "   " + item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = this.initCourseTable[hourForward][dayMatrix + 1] + "  " + item.writeInfo();
							}
						}
						if(item.getGrade() == 3) {
							
							if(initJuniorCourseTable[hourForward][dayMatrix].equals("")) {
								this.initJuniorCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
							}
							else {
								this.initJuniorCourseTable[hourForward][dayMatrix + 1] = this.initJuniorCourseTable[hourForward][dayMatrix] + "   " + item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = this.initCourseTable[hourForward][dayMatrix + 1] + "  " + item.writeInfo();
							}
						}
						if(item.getGrade() == 4) {
							
							if(initSeniorCourseTable[hourForward][dayMatrix].equals("")) {
								this.initSeniorCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = item.writeInfo();
							}
							else {
								this.initSeniorCourseTable[hourForward][dayMatrix + 1] = this.initSeniorCourseTable[hourForward][dayMatrix] + "   " + item.writeInfo();
								this.initCourseTable[hourForward][dayMatrix + 1] = this.initCourseTable[hourForward][dayMatrix + 1] + "  " + item.writeInfo();
							}
						}
						hourForward++;
					}
				}
				if(item.getGrade() == 1) { 
					this.freshmanMarkedList.add(item); 
					this.freshmanBackupMarked.add(item);
				}
				if(item.getGrade() == 2) { 
					this.sophomoreMarkedList.add(item); 
					this.sophomoreBackupMarked.add(item);
				}
				if(item.getGrade() == 3) { 
					this.juniorMarkedList.add(item); 
					this.juniorBackupMarked.add(item);
				}
				if(item.getGrade() == 4) { 
					this.seniorMarkedList.add(item); 
					this.seniorBackupMarked.add(item);
				}
				return retStr;
			}//control check
		}
		return retStr;
	}
//********************* INITIALIZING FOUR GRADEs TABS ***********************************	
	public String initFreshmanCourseTableEvent()
	{	
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.clearDeanCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		if(this.freshmanHourFlag == true) {
			if(this.splitFlagFreshman == false) {
				this.selectedScheduleAtomicFreshman = this.freshmanUnmarkedList.get(this.atomicIndexFreshman);
			}
			else {
				this.selectedScheduleAtomicFreshman = this.freshmanUnmarkedList.get(this.atomicIndexFreshman).splitCredit(Integer.parseInt(this.selectedFreshmanCredit));
			}
			
			ScheduleAtomic sItem = new ScheduleAtomic(this.selectedScheduleAtomicFreshman);
			sItem.setDay(this.selectedFreshmanDay);
			sItem.setStartHour(Integer.parseInt(this.selectedFreshmanStartHour));
				
			int day = this.dayMapToIntegerHash.get(sItem.getDay());
			int hour = sItem.getStartHour();
			hour--;
			int limit = hour + sItem.getCredit();
				
			boolean controlCheck = true;
			for(int h = hour; ((controlCheck != false) && (h < limit)); h++) {
				controlCheck = this.controlCourse("Freshman", day, h);
			}
					
			for(int j = hour; ((controlCheck != false) && (j < limit)); j++) {
				controlCheck = this.controlLecturer("Freshman", day, j, sItem.getLecturerId());
			}
			for(int k = hour; ((controlCheck != false) && (k < limit)); k++) {
				controlCheck = this.controlClassroom("Freshman", day, k, sItem.getClassroomId());
			}
					
			if(controlCheck == true) {
				int dayMatrix = this.dayMapToIntegerHash.get(sItem.getDay());
				int hourForward = sItem.getStartHour() - 1;
				if(sItem.isAttendance() == true) {
					for(int p = 0; p < sItem.getCredit(); p++) { 
						this.controlFreshmanLecturer[hourForward][dayMatrix] = sItem.getLecturerId();
						this.controlFreshmanClassroom[hourForward][dayMatrix] = sItem.getClassroomId();
						this.controlFreshmanCourse[hourForward][dayMatrix] = sItem.getCourseId();
						if(initFreshmanCourseTable[hourForward][dayMatrix].equals("")) {
							this.initFreshmanCourseTable[hourForward][dayMatrix + 1] = sItem.writeInfo();
						}
						else {
							this.initFreshmanCourseTable[hourForward][dayMatrix + 1] = this.initFreshmanCourseTable[hourForward][dayMatrix] + "   " + sItem.writeInfo();
						}
						hourForward++;
					}
				}
				else {
					for(int p = 0; p < sItem.getCredit(); p++) {
						if(initFreshmanCourseTable[hourForward][dayMatrix + 1].equals("")) {
							this.initFreshmanCourseTable[hourForward][dayMatrix + 1] = sItem.writeInfo();
						}
						else {
							this.initFreshmanCourseTable[hourForward][dayMatrix + 1] = this.initFreshmanCourseTable[hourForward][dayMatrix] + "\n" + sItem.writeInfo();
						}
						hourForward++;
					}		
				}
				this.freshmanMarkedList.add(sItem);	
				if(this.splitFlagFreshman == false) {
					this.freshmanUnmarkedList.remove(this.atomicIndexFreshman);
				}
				else {
					this.selectedScheduleAtomicFreshman = new ScheduleAtomic();
				}
				
				this.clearTimeValues("Freshman");
				return retStr;
			}//controlCheck if				
			if(this.splitFlagFreshman == true) {
				ScheduleAtomic boundedAtomic = this.freshmanUnmarkedList.get(this.atomicIndexFreshman);
				boundedAtomic.mergeCredit(sItem, "Marked");
			}
		}
		return retStr;
	}
	
	public String initSophomoreCourseTableEvent()
	{	
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.clearDeanCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		if(this.sophomoreHourFlag == true) {
			if(this.splitFlagSophomore == false) {
				this.selectedScheduleAtomicSophomore = this.sophomoreUnmarkedList.get(this.atomicIndexSophomore);
			}
			else {
				this.selectedScheduleAtomicSophomore = this.sophomoreUnmarkedList.get(this.atomicIndexSophomore).splitCredit(Integer.parseInt(this.selectedSophomoreCredit));
			}
			
			ScheduleAtomic sItem = new ScheduleAtomic(this.selectedScheduleAtomicSophomore);
			sItem.setDay(this.selectedSophomoreDay);
			sItem.setStartHour(Integer.parseInt(this.selectedSophomoreStartHour));
				
			int day = this.dayMapToIntegerHash.get(sItem.getDay());
			int hour = sItem.getStartHour();
			hour--;
			int limit = hour + sItem.getCredit();
				
			boolean controlCheck = true;
			for(int h = hour; ((controlCheck != false) && (h < limit)); h++) {
				controlCheck = this.controlCourse("Sophomore", day, h);
			}
					
			for(int j = hour; ((controlCheck != false) && (j < limit)); j++) {
				controlCheck = this.controlLecturer("Sophomore", day, j, sItem.getLecturerId());
			}
			for(int k = hour; ((controlCheck != false) && (k < limit)); k++) {
				controlCheck = this.controlClassroom("Sophomore", day, k, sItem.getClassroomId());
			}
					
			if(controlCheck == true) {
				int dayMatrix = this.dayMapToIntegerHash.get(sItem.getDay());
				int hourForward = sItem.getStartHour() - 1;
				if(sItem.isAttendance() == true) {
					for(int p = 0; p < sItem.getCredit(); p++) { 
						this.controlSophomoreLecturer[hourForward][dayMatrix] = sItem.getLecturerId();
						this.controlSophomoreClassroom[hourForward][dayMatrix] = sItem.getClassroomId();
						this.controlSophomoreCourse[hourForward][dayMatrix] = sItem.getCourseId();
						if(initSophomoreCourseTable[hourForward][dayMatrix].equals("")) {
							this.initSophomoreCourseTable[hourForward][dayMatrix + 1] = sItem.writeInfo();
						}
						else {
							this.initSophomoreCourseTable[hourForward][dayMatrix + 1] = this.initSophomoreCourseTable[hourForward][dayMatrix] + "   " + sItem.writeInfo();
						}
						hourForward++;
					}
				}
				else {
					for(int p = 0; p < sItem.getCredit(); p++) {
						if(initSophomoreCourseTable[hourForward][dayMatrix + 1].equals("")) {
							this.initSophomoreCourseTable[hourForward][dayMatrix + 1] = sItem.writeInfo();
						}
						else {
							this.initSophomoreCourseTable[hourForward][dayMatrix + 1] = this.initSophomoreCourseTable[hourForward][dayMatrix] + "\n" + sItem.writeInfo();
						}
						hourForward++;
					}		
				}
				this.sophomoreMarkedList.add(sItem);	
				if(this.splitFlagSophomore == false) {
					this.sophomoreUnmarkedList.remove(this.atomicIndexSophomore);
				}
				else {
					this.selectedScheduleAtomicSophomore = new ScheduleAtomic();
				}
				
				this.clearTimeValues("Sophomore");
				return retStr;
			}//controlCheck if				
			if(this.splitFlagSophomore == true) {
				ScheduleAtomic boundedAtomic = this.sophomoreUnmarkedList.get(this.atomicIndexSophomore);
				boundedAtomic.mergeCredit(sItem, "Marked");
			}
		}
		return retStr;
	}
	
	public String initJuniorCourseTableEvent()
	{
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.clearDeanCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		if(this.juniorHourFlag == true) {
			if(this.splitFlagJunior == false) {
				this.selectedScheduleAtomicJunior = this.juniorUnmarkedList.get(this.atomicIndexJunior);
			}
			else {
				this.selectedScheduleAtomicJunior = this.juniorUnmarkedList.get(this.atomicIndexJunior).splitCredit(Integer.parseInt(this.selectedJuniorCredit));
			}
			
			ScheduleAtomic sItem = new ScheduleAtomic(this.selectedScheduleAtomicJunior);
			sItem.setDay(this.selectedJuniorDay);
			sItem.setStartHour(Integer.parseInt(this.selectedJuniorStartHour));
				
			int day = this.dayMapToIntegerHash.get(sItem.getDay());
			int hour = sItem.getStartHour();
			hour--;
			int limit = hour + sItem.getCredit();
				
			boolean controlCheck = true;
			for(int h = hour; ((controlCheck != false) && (h < limit)); h++) {
				controlCheck = this.controlCourse("Junior", day, h);
			}
					
			for(int j = hour; ((controlCheck != false) && (j < limit)); j++) {
				controlCheck = this.controlLecturer("Junior", day, j, sItem.getLecturerId());
			}
			for(int k = hour; ((controlCheck != false) && (k < limit)); k++) {
				controlCheck = this.controlClassroom("Junior", day, k, sItem.getClassroomId());
			}
					
			if(controlCheck == true) {
				int dayMatrix = this.dayMapToIntegerHash.get(sItem.getDay());
				int hourForward = sItem.getStartHour() - 1;
				if(sItem.isAttendance() == true) {
					for(int p = 0; p < sItem.getCredit(); p++) { 
						this.controlJuniorLecturer[hourForward][dayMatrix] = sItem.getLecturerId();
						this.controlJuniorClassroom[hourForward][dayMatrix] = sItem.getClassroomId();
						this.controlJuniorCourse[hourForward][dayMatrix] = sItem.getCourseId();
						if(initJuniorCourseTable[hourForward][dayMatrix].equals("")) {
							this.initJuniorCourseTable[hourForward][dayMatrix + 1] = sItem.writeInfo();
						}
						else {
							this.initJuniorCourseTable[hourForward][dayMatrix + 1] = this.initJuniorCourseTable[hourForward][dayMatrix] + "   " + sItem.writeInfo();
						}
						hourForward++;
					}
				}
				else {
					for(int p = 0; p < sItem.getCredit(); p++) {
						if(initJuniorCourseTable[hourForward][dayMatrix + 1].equals("")) {
							this.initJuniorCourseTable[hourForward][dayMatrix + 1] = sItem.writeInfo();
						}
						else {
							this.initJuniorCourseTable[hourForward][dayMatrix + 1] = this.initJuniorCourseTable[hourForward][dayMatrix] + "\n" + sItem.writeInfo();
						}
						hourForward++;
					}		
				}
				this.juniorMarkedList.add(sItem);	
				if(this.splitFlagJunior == false) {
					this.juniorUnmarkedList.remove(this.atomicIndexJunior);
				}
				else {
					this.selectedScheduleAtomicJunior = new ScheduleAtomic();
				}
				
				this.clearTimeValues("Junior");
				return retStr;
			}//controlCheck if				
			if(this.splitFlagJunior == true) {
				ScheduleAtomic boundedAtomic = this.juniorUnmarkedList.get(this.atomicIndexJunior);
				boundedAtomic.mergeCredit(sItem, "Marked");
			}
		}
		return retStr;
	}
	
	public String initSeniorCourseTableEvent()
	{
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.clearDeanCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		if(this.seniorHourFlag == true) {
			if(this.splitFlagSenior == false) {
				this.selectedScheduleAtomicSenior = this.seniorUnmarkedList.get(this.atomicIndexSenior);
			}
			else {
				this.selectedScheduleAtomicSenior = this.seniorUnmarkedList.get(this.atomicIndexSenior).splitCredit(Integer.parseInt(this.selectedSeniorCredit));
			}
			
			ScheduleAtomic sItem = new ScheduleAtomic(this.selectedScheduleAtomicSenior);
			sItem.setDay(this.selectedSeniorDay);
			sItem.setStartHour(Integer.parseInt(this.selectedSeniorStartHour));
				
			int day = this.dayMapToIntegerHash.get(sItem.getDay());
			int hour = sItem.getStartHour();
			hour--;
			int limit = hour + sItem.getCredit();
				
			boolean controlCheck = true;
			for(int h = hour; ((controlCheck != false) && (h < limit)); h++) {
				controlCheck = this.controlCourse("Senior", day, h);
			}
					
			for(int j = hour; ((controlCheck != false) && (j < limit)); j++) {
				controlCheck = this.controlLecturer("Senior", day, j, sItem.getLecturerId());
			}
			for(int k = hour; ((controlCheck != false) && (k < limit)); k++) {
				controlCheck = this.controlClassroom("Senior", day, k, sItem.getClassroomId());
			}
					
			if(controlCheck == true) {
				int dayMatrix = this.dayMapToIntegerHash.get(sItem.getDay());
				int hourForward = sItem.getStartHour() - 1;
				if(sItem.isAttendance() == true) {
					for(int p = 0; p < sItem.getCredit(); p++) { 
						this.controlSeniorLecturer[hourForward][dayMatrix] = sItem.getLecturerId();
						this.controlSeniorClassroom[hourForward][dayMatrix] = sItem.getClassroomId();
						this.controlSeniorCourse[hourForward][dayMatrix] = sItem.getCourseId();
						if(initSeniorCourseTable[hourForward][dayMatrix].equals("")) {
							this.initSeniorCourseTable[hourForward][dayMatrix + 1] = sItem.writeInfo();
						}
						else {
							this.initSeniorCourseTable[hourForward][dayMatrix + 1] = this.initSeniorCourseTable[hourForward][dayMatrix] + "   " + sItem.writeInfo();
						}
						hourForward++;
					}
				}
				else {
					for(int p = 0; p < sItem.getCredit(); p++) {
						if(initSeniorCourseTable[hourForward][dayMatrix + 1].equals("")) {
							this.initSeniorCourseTable[hourForward][dayMatrix + 1] = sItem.writeInfo();
						}
						else {
							this.initSeniorCourseTable[hourForward][dayMatrix + 1] = this.initSeniorCourseTable[hourForward][dayMatrix] + "\n" + sItem.writeInfo();
						}
						hourForward++;
					}		
				}
				this.seniorMarkedList.add(sItem);	
				if(this.splitFlagSenior == false) {
					this.seniorUnmarkedList.remove(this.atomicIndexSenior);
				}
				else {
					this.selectedScheduleAtomicSenior = new ScheduleAtomic();
				}
				
				this.clearTimeValues("Senior");
				return retStr;
			}//controlCheck if				
			if(this.splitFlagSenior == true) {
				ScheduleAtomic boundedAtomic = this.seniorUnmarkedList.get(this.atomicIndexSenior);
				boundedAtomic.mergeCredit(sItem, "Marked");
			}
		}
		return retStr;
	}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
//********************** EVENTS **********************************************************
// Onur (Finished 03.05)
// This is the event which holds the operations when a year is selected. According to year
// selected, semester data will be loaded to the related subfield.
	public void yearValueChange(ValueChangeEvent event) {
		System.out.println("Year Code : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedYear = newValue;
		this.autoScheduleFlag = false;
		this.clearAllComponents();
		if((!this.selectedYear.equals("Choose Year")) && (this.selectedYear != null)) {
			if(this.semesterFlag == true) {
				this.loadAllLists(selectedYear, selectedSemester);
				this.autoScheduleFlag = true;
			}
			else {
				this.semesterList = new ArrayList<SelectItem>();
				this.loadSemester();
			}		
		}
		else {
			this.clearAllComponents();
		}
	}
// Onur (Finished 03.05)
// This is the event which holds the operations when a semester is selected. Via of 
// selected semester value, all the subfield which must be initialized after this event
	public void semesterValueChange(ValueChangeEvent event) {
		System.out.println("Course Code : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedSemester = newValue;
		this.autoScheduleFlag = false;
		if((!this.selectedSemester.equals("Choose Semester")) && (this.selectedSemester != null)) {
			this.clearAllComponents();
			this.loadAllLists(this.selectedYear, this.selectedSemester);
			this.autoScheduleFlag = true;
			this.semesterFlag = true;
		}
		else { 
			this.semesterFlag = false;
			this.clearAllComponents();
		}
	}
//***********************************************************************************************	
	public String runAutoScheduling() {
		String retVal = "";
		if(this.autoScheduleFlag == true) {
			this.refreshKnowledges("Freshman");
			this.refreshKnowledges("Sophomore");
			this.refreshKnowledges("Junior");
			this.refreshKnowledges("Senior");
			
			this.freshmanUnmarkedList = this.scheduleAtomicObj.clearSpots(this.freshmanUnmarkedList);
			this.sophomoreUnmarkedList = this.scheduleAtomicObj.clearSpots(this.sophomoreUnmarkedList);
			this.juniorUnmarkedList = this.scheduleAtomicObj.clearSpots(this.juniorUnmarkedList);
			this.seniorUnmarkedList = this.scheduleAtomicObj.clearSpots(this.seniorUnmarkedList);
			
			this.freshmanAutoScheduling();
			this.sophomoreAutoScheduling();
			this.juniorAutoScheduling();
			this.seniorAutoScheduling();
			
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			
			this.clearControlTables("Freshman");
			this.clearControlTables("Sophomore");
			this.clearControlTables("Junior");
			this.clearControlTables("Senior");
			
			this.writeFreshmanSchedule();
			this.writeSophomoreSchedule();
			this.writeJuniorSchedule();
			this.writeSeniorSchedule();
			
			this.retrieveFromBackup("Freshman");
			this.retrieveFromBackup("Sophomore");
			this.retrieveFromBackup("Junior");
			this.retrieveFromBackup("Senior");
			
			this.loadToBackup("Freshman");
			this.loadToBackup("Sophomore");
			this.loadToBackup("Junior");
			this.loadToBackup("Senior");
			
			this.freshmanMarkedList.clear();
			this.sophomoreMarkedList.clear();
			this.juniorMarkedList.clear();
			this.seniorMarkedList.clear();
			
			this.autoScheduleResultFlag = true;
		}	
		return retVal;
	}
//*********************************************************************************************
	public void freshmanLockDayChange(ValueChangeEvent event) {
		System.out.println("Freshman Lock Day : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.freshmanLockedDay = newValue;
		if((!this.freshmanLockedDay.equals("Choose Lock Day")) && (this.freshmanLockedDay != null)) {
			this.freshmanLockDayFlag = true;
			if(this.freshmanLockHourFlag == true) { this.freshmanReadyToLock = true; }
			else { this.freshmanReadyToLock = false; }
		}
		else {
			this.freshmanLockDayFlag = false;
			this.freshmanReadyToLock = false;
		}
	}
	
	public void freshmanLockHourChange(ValueChangeEvent event) {
		System.out.println("Freshman Lock Hour : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.freshmanLockedHour = newValue;
		if((!this.freshmanLockedHour.equals("Choose Lock Hour")) && (this.freshmanLockedHour != null)) {
			this.freshmanLockHourFlag = true;
			if(this.freshmanLockDayFlag == true) { this.freshmanReadyToLock = true; }
			else { this.freshmanReadyToLock = false; }
		}
		else {
			this.freshmanLockHourFlag = false;
			this.freshmanReadyToLock = false;
		}
	} 
	
	public String freshmanLockOperation() {
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		if(this.freshmanReadyToLock == true) {
			Index item = new Index(this.freshmanLockedDay, Integer.parseInt(this.freshmanLockedHour));
			int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
			int day = this.dayMapToIndexHash.get(item.getDay());
			int hour = (item.getHour());
			hour--;
			if(this.controlFreshmanCourse[hour][dayMatrix] == 0) {
				Integer iVal = new Integer(-1);
				this.controlFreshmanCourse[hour][dayMatrix] = iVal;
				this.initFreshmanCourseTable[hour][day] = "LOCKED";
				this.freshmanLockedIndexes.add(item);
			}
		}
		return retStr;
	}
	
	public String freshmanUnlockOperation() {
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		for(int i = 0; i < this.freshmanLockedIndexes.size(); i++) {
			Index item = this.freshmanLockedIndexes.get(i);
			int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
			int day = this.dayMapToIndexHash.get(item.getDay());
			int hour = item.getHour();
			hour--;
			if(this.controlFreshmanCourse[hour][dayMatrix] == -1) {
				this.controlFreshmanCourse[hour][dayMatrix] = 0;
			}
			if(this.initFreshmanCourseTable[hour][day].equals("LOCKED")) {
				this.initFreshmanCourseTable[hour][day] = "";
			}
		}
		this.freshmanLockedIndexes.clear();
		return retStr;
	}
//********************************************************************************************
	public void sophomoreLockDayChange(ValueChangeEvent event) {
		System.out.println("Sophomore Lock Day : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.sophomoreLockedDay = newValue;
		if((!this.sophomoreLockedDay.equals("Choose Lock Day")) && (this.sophomoreLockedDay != null)) {
			this.sophomoreLockDayFlag = true;
			if(this.sophomoreLockHourFlag == true) { this.sophomoreReadyToLock = true; }
			else { this.sophomoreReadyToLock = false; }
		}
		else {
			this.sophomoreLockDayFlag = false;
			this.sophomoreReadyToLock = false;
		}
	}
	
	public void sophomoreLockHourChange(ValueChangeEvent event) {
		System.out.println("Sophomore Lock Hour : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.sophomoreLockedHour = newValue;
		if((!this.sophomoreLockedHour.equals("Choose Lock Hour")) && (this.sophomoreLockedHour != null)) {
			this.sophomoreLockHourFlag = true;
			if(this.sophomoreLockDayFlag == true) { this.sophomoreReadyToLock = true; }
			else { this.sophomoreReadyToLock = false; }
		}
		else {
			this.sophomoreLockHourFlag = false;
			this.sophomoreReadyToLock = false;
		}
	} 
	
	public String sophomoreLockOperation() {
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		if(this.sophomoreReadyToLock == true) {
			Index item = new Index(this.sophomoreLockedDay, Integer.parseInt(this.sophomoreLockedHour));
			int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
			int day = this.dayMapToIndexHash.get(item.getDay());
			int hour = (item.getHour());
			hour--;
			if(this.controlSophomoreCourse[hour][dayMatrix] == 0) {
				Integer iVal = new Integer(-1);
				this.controlSophomoreCourse[hour][dayMatrix] = iVal;
				this.initSophomoreCourseTable[hour][day] = "LOCKED";
				this.sophomoreLockedIndexes.add(item);
				return retStr;
			}
			else { return retStr; }
		}
		return retStr;
	}
	
	public String sophomoreUnlockOperation() {
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		for(int i = 0; i < this.sophomoreLockedIndexes.size(); i++) {
			Index item = this.sophomoreLockedIndexes.get(i);
			int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
			int day = this.dayMapToIndexHash.get(item.getDay());
			int hour = item.getHour();
			hour--;
			if(this.controlSophomoreCourse[hour][dayMatrix] == -1) {
				this.controlSophomoreCourse[hour][dayMatrix] = 0;
			}
			if(this.initSophomoreCourseTable[hour][day].equals("LOCKED")) {
				this.initSophomoreCourseTable[hour][day] = "";
			}
		}
		this.sophomoreLockedIndexes.clear();
		return retStr;
	}
//************************************************************************************************
//***************************************************************************************
	public void juniorLockDayChange(ValueChangeEvent event) {
		System.out.println("Junior Lock Day : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.juniorLockedDay = newValue;
		if((!this.juniorLockedDay.equals("Choose Lock Day")) && (this.juniorLockedDay != null)) {
			this.juniorLockDayFlag = true;
			if(this.juniorLockHourFlag == true) { this.juniorReadyToLock = true; }
			else { this.juniorReadyToLock = false; }
		}
		else {
			this.juniorLockDayFlag = false;
			this.juniorReadyToLock = false;
		}
	}
	
	public void juniorLockHourChange(ValueChangeEvent event) {
		System.out.println("Junior Lock Hour : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.juniorLockedHour = newValue;
		if((!this.juniorLockedHour.equals("Choose Lock Hour")) && (this.juniorLockedHour != null)) {
			this.juniorLockHourFlag = true;
			if(this.juniorLockDayFlag == true) { this.juniorReadyToLock = true; }
			else { this.juniorReadyToLock = false; }
		}
		else {
			this.juniorLockHourFlag = false;
			this.juniorReadyToLock = false;
		}
	} 
	
	public String juniorLockOperation() {
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		if(this.juniorReadyToLock == true) {
			Index item = new Index(this.juniorLockedDay, Integer.parseInt(this.juniorLockedHour));
			int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
			int day = this.dayMapToIndexHash.get(item.getDay());
			int hour = (item.getHour());
			hour--;
			if(this.controlJuniorCourse[hour][dayMatrix] == 0) {
				Integer iVal = new Integer(-1);
				this.controlJuniorCourse[hour][dayMatrix] = iVal;
				this.initJuniorCourseTable[hour][day] = "LOCKED";
				this.juniorLockedIndexes.add(item);
			}
		}
		return retStr;
	}
	
	public String juniorUnlockOperation() {
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		for(int i = 0; i < this.juniorLockedIndexes.size(); i++) {
			Index item = this.juniorLockedIndexes.get(i);
			int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
			int day = this.dayMapToIndexHash.get(item.getDay());
			int hour = item.getHour();
			hour--;
			if(this.controlJuniorCourse[hour][dayMatrix] == -1) {
				this.controlJuniorCourse[hour][dayMatrix] = 0;
			}
			if(this.initJuniorCourseTable[hour][day].equals("LOCKED")) {
				this.initJuniorCourseTable[hour][day] = "";
			}
		}
		this.juniorLockedIndexes.clear();
		return retStr;
	}
//***************************************************************************************
	public void seniorLockDayChange(ValueChangeEvent event) {
		System.out.println("Senior Lock Day : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.seniorLockedDay = newValue;
		if((!this.seniorLockedDay.equals("Choose Lock Day")) && (this.seniorLockedDay != null)) {
			this.seniorLockDayFlag = true;
			if(this.seniorLockHourFlag == true) { this.seniorReadyToLock = true; }
			else { this.seniorReadyToLock = false; }
		}
		else {
			this.seniorLockDayFlag = false;
			this.seniorReadyToLock = false;
		}
	}
	
	public void seniorLockHourChange(ValueChangeEvent event) {
		System.out.println("Senior Lock Hour : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.seniorLockedHour = newValue;
		if((!this.seniorLockedHour.equals("Choose Lock Hour")) && (this.seniorLockedHour != null)) {
			this.seniorLockHourFlag = true;
			if(this.seniorLockDayFlag == true) { this.seniorReadyToLock = true; }
			else { this.seniorReadyToLock = false; }
		}
		else {
			this.seniorLockHourFlag = false;
			this.seniorReadyToLock = false;
		}
	} 
	
	public String seniorLockOperation() {
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		if(this.seniorReadyToLock == true) {
			Index item = new Index(this.seniorLockedDay, Integer.parseInt(this.seniorLockedHour));
			int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
			int day = this.dayMapToIndexHash.get(item.getDay());
			int hour = (item.getHour());
			hour--;
			if(this.controlSeniorCourse[hour][dayMatrix] == 0) {
				Integer iVal = new Integer(-1);
				this.controlSeniorCourse[hour][dayMatrix] = iVal;
				this.initSeniorCourseTable[hour][day] = "LOCKED";
				this.seniorLockedIndexes.add(item);
			}
		}
		return retStr;
	}
	
	public String seniorUnlockOperation() {
		String retStr = "";
		if(this.autoScheduleResultFlag == true) {
			this.clearFreshmanCourseTable();
			this.clearSophomoreCourseTable();
			this.clearJuniorCourseTable();
			this.clearSeniorCourseTable();
			this.autoScheduleResultFlag = false;
		}
		
		for(int i = 0; i < this.seniorLockedIndexes.size(); i++) {
			Index item = this.seniorLockedIndexes.get(i);
			int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
			int day = this.dayMapToIndexHash.get(item.getDay());
			int hour = item.getHour();
			hour--;
			if(this.controlSeniorCourse[hour][dayMatrix] == -1) {
				this.controlSeniorCourse[hour][dayMatrix] = 0;
			}
			if(this.initSeniorCourseTable[hour][day].equals("LOCKED")) {
				this.initSeniorCourseTable[hour][day] = "";
			}
		}
		this.seniorLockedIndexes.clear();
		return retStr;
	}
//*********************************************************************************************
//This is the event which holds the operations when a course selected in dean tab	
	public void deanValueChange(ValueChangeEvent event) {
		System.out.println("Dean Course Name : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedDeanCourse = newValue;
		this.clearDeanSubFields();
		if((!this.selectedDeanCourse.equals("Choose Course")) && (this.selectedDeanCourse != null))
		{
			this.loadDeanSubFields();
		}
	}
	
	public void deanLecturerChange(ValueChangeEvent event) {
		System.out.println("Dean Lecturer Name : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedDeanLecturer = newValue;
		if((!this.selectedDeanLecturer.equals("Choose Lecturer")) && (this.selectedDeanCourse != null)) {
			this.deanStartFlag = true;
		}
		else {
			this.deanStartFlag = false;
		}
	}
	
	public void deanOperationChange(ValueChangeEvent event) {
		System.out.println("Dean Operation : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedDeanOperation = newValue;
		if((!this.selectedDeanOperation.equals("Choose Operation")) && (this.selectedDeanOperation != null)) {
			this.deanOperationFlag = true;
		}
		else {
			this.deanOperationFlag = false;
			
		}
	}
	
	public void deanDayChange(ValueChangeEvent event) {
		System.out.println("Dean Day : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedDeanDay = newValue;
		if((!this.selectedDeanDay.equals("Choose Day")) && (this.selectedDeanDay != null)) {
			this.deanDayFlag = true;
		}
		else {
			this.deanDayFlag = false;
		}
	}
	
// ****************** 4(Freshman, Sophomore, Junior, Senior) GRADE EVENTS ***************
//******************* FRESHMAN EVENTS ***************************************************
	public void freshmanSplitChange(ValueChangeEvent event) {
		System.out.println("Freshman Course has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedFreshmanCourse = newValue;
		this.clearSubFields("Freshman");
		this.clearTimeValues("Freshman");
		if(!this.selectedFreshmanCourse.equals("Course Selection") && this.selectedFreshmanCourse != null) {
			this.loadSubFields("Freshman");
		}
	}
	
	public void freshmanOperationChange(ValueChangeEvent event) {
		System.out.println("Freshman Operation has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedFreshmanOperation = newValue;
		this.topCreditFreshman = -1;
		this.atomicIndexFreshman = -1;
		this.clearTimeValues("Freshman");
		if((!this.selectedFreshmanOperation.equals("Choose Course Type")) && (this.selectedFreshmanOperation != null)) {
			boolean permission = true;
			if(this.selectedFreshmanOperation.equals("Theoretical")) { 
				this.optFlagFreshman = "Theo";
				if(Integer.parseInt(this.freshmanCreditValeuTeo) == 0) { permission = false; }
			}
			if(this.selectedFreshmanOperation.equals("Practice")) { 
				this.optFlagFreshman = "Prac";
				if(Integer.parseInt(this.freshmanCreditValuePrac) == 0) { permission = false; }
			}	
			if(permission == true) {
				this.atomicIndexFreshman = this.findRelatedAtomic("Freshman", "Unmarked", "SEEK", this.selectedFreshmanSyllabus, this.optFlagFreshman, 0);
				if(this.atomicIndexFreshman != -1) {
					this.topCreditFreshman = this.freshmanUnmarkedList.get(this.atomicIndexFreshman).getCredit();
					this.freshmanCredits.clear();
					this.loadCredits("Freshman", this.topCreditFreshman);
					if((this.freshmanCreditFlag == true) && (Integer.parseInt(this.selectedFreshmanCredit) <= this.topCreditFreshman)) {
						if(Integer.parseInt(this.selectedFreshmanCredit) == this.topCreditFreshman) {
							this.splitFlagFreshman = false;	
						}
						else { 
							this.splitFlagFreshman = true; 
						}
						this.loadDays("Freshman");
						if(this.freshmanDayFlag == true) {
							this.scheduleAtomicObj.setCredit(Integer.parseInt(this.selectedFreshmanCredit));
							this.freshmanHours = this.scheduleAtomicObj.getKnowledgeByDay(this.selectedFreshmanDay);
							this.scheduleAtomicObj.getKnowledge().clear();
							if(this.freshmanHours.contains(new SelectItem(this.selectedFreshmanStartHour))) {
								this.freshmanHourFlag = true;
							}
							else {
								this.freshmanHourFlag = false;
							}
						}//day flag
					}//credit flag
				}//atomicIndexFreshman
			}//permission
		}
		else {  
			this.optFlagFreshman = "";
			this.clearTimeValues("Freshman");
		}
	}
	
	public void freshmanCreditChange(ValueChangeEvent event) {
		System.out.println("Freshman Credit has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedFreshmanCredit = newValue;
		this.freshmanDays.clear();
		this.freshmanHours.clear();
		if(!this.selectedFreshmanCredit.equals("Choose Credit") && this.selectedFreshmanCredit != null) {
			if(this.topCreditFreshman != -1) {
				this.freshmanCreditFlag = true;
				if(this.topCreditFreshman != Integer.parseInt(this.selectedFreshmanCredit)) { 
					this.splitFlagFreshman = true; 
				}
				else { 
					this.splitFlagFreshman = false; 
				}
				this.loadDays("Freshman");
				if(this.freshmanDayFlag == true) {
					this.scheduleAtomicObj.setCredit(Integer.parseInt(this.selectedFreshmanCredit));
					this.freshmanHours = this.scheduleAtomicObj.getKnowledgeByDay(this.selectedFreshmanDay);
					this.scheduleAtomicObj.getKnowledge().clear();
					if(this.freshmanHours.contains(new SelectItem(this.selectedFreshmanStartHour))) {
						this.freshmanHourFlag = true;
					}
					else {
						this.freshmanHourFlag = false;
					}
				}
			}
		}
		else {
			this.freshmanCreditFlag = false;
			this.freshmanDays.clear();
			this.freshmanHours.clear();
		}
	}
	
	public void freshmanDayChange(ValueChangeEvent event) {
		System.out.println("Freshman Day has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedFreshmanDay = newValue;
		this.freshmanHours.clear();
		if((!this.selectedFreshmanDay.equals("Choose Day")) && (this.selectedFreshmanDay != null)) {
			this.scheduleAtomicObj.setCredit(Integer.parseInt(this.selectedFreshmanCredit));
			this.freshmanHours = this.scheduleAtomicObj.getKnowledgeByDay(this.selectedFreshmanDay);
			this.scheduleAtomicObj.getKnowledge().clear();
			this.freshmanDayFlag = true;
			if(this.freshmanHours.contains(new SelectItem(this.selectedFreshmanStartHour))) {
				this.freshmanHourFlag = true;
			}
			else {
				this.freshmanHourFlag = false;
			}
		}
		else {
			this.freshmanDayFlag = false;
			this.freshmanHours.clear();
		}
	}
	
	public void freshmanHourChange(ValueChangeEvent event) {
		System.out.println("Freshman Hour has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedFreshmanStartHour = newValue;
		if((!this.selectedFreshmanStartHour.equals("Choose Start Hour")) && (this.selectedFreshmanStartHour != null)) {
			this.freshmanHourFlag = true;
		}
		else { 
			this.freshmanHourFlag = false;
		}
	}
//******************* SOPHOMORE EVENTS **************************************************
	public void sophomoreSplitChange(ValueChangeEvent event) {
		System.out.println("Sophomore course has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedSophomoreCourse = newValue;
		this.clearSubFields("Sophomore");
		this.clearTimeValues("Sophomore");
		if(!this.selectedSophomoreCourse.equals("Course Selection") && this.selectedSophomoreCourse != null) {
			this.loadSubFields("Sophomore");
		}
	}
	
	public void sophomoreOperationChange(ValueChangeEvent event) {
		System.out.println("Sophomore Operation has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedSophomoreOperation = newValue;
		this.topCreditSophomore = -1;
		this.atomicIndexSophomore = -1;
		this.clearTimeValues("Sophomore");
		if((!this.selectedSophomoreOperation.equals("Choose Course Type")) && (this.selectedSophomoreOperation != null)) {
			boolean permission = true;
			if(this.selectedSophomoreOperation.equals("Theoretical")) { 
				this.optFlagSophomore = "Theo";
				if(Integer.parseInt(this.sophomoreCreditValeuTeo) == 0) { permission = false; }
			}
			if(this.selectedSophomoreOperation.equals("Practice")) { 
				this.optFlagSophomore = "Prac";
				if(Integer.parseInt(this.sophomoreCreditValuePrac) == 0) { permission = false; }
			}	
			if(permission == true) {
				this.atomicIndexSophomore = this.findRelatedAtomic("Sophomore", "Unmarked", "SEEK", this.selectedSophomoreSyllabus, this.optFlagSophomore, 0);
				if(this.atomicIndexSophomore != -1) {
					this.topCreditSophomore = this.sophomoreUnmarkedList.get(this.atomicIndexSophomore).getCredit();
					this.sophomoreCredits.clear();
					this.loadCredits("Sophomore", this.topCreditSophomore);
					if((this.sophomoreCreditFlag == true) && (Integer.parseInt(this.selectedSophomoreCredit) <= this.topCreditSophomore)) {
						if(Integer.parseInt(this.selectedSophomoreCredit) == this.topCreditSophomore) {
							this.splitFlagSophomore = false;	
						}
						else { 
							this.splitFlagSophomore = true; 
						}
						this.loadDays("Sophomore");
						if(this.sophomoreDayFlag == true) {
							this.scheduleAtomicObj.setCredit(Integer.parseInt(this.selectedSophomoreCredit));
							this.sophomoreHours = this.scheduleAtomicObj.getKnowledgeByDay(this.selectedSophomoreDay);
							this.scheduleAtomicObj.getKnowledge().clear();
							if(this.sophomoreHours.contains(new SelectItem(this.selectedSophomoreStartHour))) {
								this.sophomoreHourFlag = true;
							}
							else {
								this.sophomoreHourFlag = false;
							}
						}//day flag
					}//credit flag
				}//atomicIndexsophomore
			}//permission
		}
		else {  
			this.optFlagSophomore = "";
			this.clearTimeValues("Sophomore");
		}
	}
	
	public void sophomoreCreditChange(ValueChangeEvent event) {
		System.out.println("Sophomore Credit has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedSophomoreCredit = newValue;
		this.sophomoreDays.clear();
		this.sophomoreHours.clear();
		if(!this.selectedSophomoreCredit.equals("Choose Credit") && this.selectedSophomoreCredit != null) {
			if(this.topCreditSophomore != -1) {
				this.sophomoreCreditFlag = true;
				if(this.topCreditSophomore != Integer.parseInt(this.selectedSophomoreCredit)) { 
					this.splitFlagSophomore = true; 
				}
				else { 
					this.splitFlagSophomore = false; 
				}
				this.loadDays("Sophomore");
				if(this.sophomoreDayFlag == true) {
					this.scheduleAtomicObj.setCredit(Integer.parseInt(this.selectedSophomoreCredit));
					this.sophomoreHours = this.scheduleAtomicObj.getKnowledgeByDay(this.selectedSophomoreDay);
					this.scheduleAtomicObj.getKnowledge().clear();
					if(this.sophomoreHours.contains(new SelectItem(this.selectedSophomoreStartHour))) {
						this.sophomoreHourFlag = true;
					}
					else {
						this.sophomoreHourFlag = false;
					}
				}
			}
		}
		else {
			this.sophomoreCreditFlag = false;
			this.sophomoreDays.clear();
			this.sophomoreHours.clear();
		}
	}
	
	public void sophomoreDayChange(ValueChangeEvent event) {
		System.out.println("Sophomore Day has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedSophomoreDay = newValue;
		this.sophomoreHours.clear();
		if((!this.selectedSophomoreDay.equals("Choose Day")) && (this.selectedSophomoreDay != null)) {
			this.scheduleAtomicObj.setCredit(Integer.parseInt(this.selectedSophomoreCredit));
			this.sophomoreHours = this.scheduleAtomicObj.getKnowledgeByDay(this.selectedSophomoreDay);
			this.scheduleAtomicObj.getKnowledge().clear();
			this.sophomoreDayFlag = true;
			if(this.sophomoreHours.contains(new SelectItem(this.selectedSophomoreStartHour))) {
				this.sophomoreHourFlag = true;
			}
			else {
				this.sophomoreHourFlag = false;
			}
		}
		else {
			this.sophomoreDayFlag = false;
			this.sophomoreHours.clear();
		}
	}
	
	public void sophomoreHourChange(ValueChangeEvent event) {
		System.out.println("Sophomore Hour has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedSophomoreStartHour = newValue;
		if((!this.selectedSophomoreStartHour.equals("Choose Start Hour")) && (this.selectedSophomoreStartHour != null)) {
			this.sophomoreHourFlag = true;
		}
		else { 
			this.sophomoreHourFlag = false;
		}
	}
	
//******************* JUNIOR EVENTS *****************************************************
	public void juniorSplitChange(ValueChangeEvent event) {
		System.out.println("Junior course has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedJuniorCourse = newValue;
		this.clearSubFields("Junior");
		this.clearTimeValues("Junior");
		if(!this.selectedJuniorCourse.equals("Course Selection") && this.selectedJuniorCourse != null) {
			this.loadSubFields("Junior");
		}	
	}
	
	public void juniorOperationChange(ValueChangeEvent event) {
		System.out.println("Junior Operation has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedJuniorOperation = newValue;
		this.topCreditJunior = -1;
		this.atomicIndexJunior = -1;
		this.clearTimeValues("Junior");
		if((!this.selectedJuniorOperation.equals("Choose Course Type")) && (this.selectedJuniorOperation != null)) {
			boolean permission = true;
			if(this.selectedJuniorOperation.equals("Theoretical")) { 
				this.optFlagJunior = "Theo";
				if(Integer.parseInt(this.juniorCreditValueTeo) == 0) { permission = false; }
			}
			if(this.selectedJuniorOperation.equals("Practice")) { 
				this.optFlagJunior = "Prac";
				if(Integer.parseInt(this.juniorCreditValuePrac) == 0) { permission = false; }
			}	
			if(permission == true) {
				this.atomicIndexJunior = this.findRelatedAtomic("Junior", "Unmarked", "SEEK", this.selectedJuniorSyllabus, this.optFlagJunior, 0);
				if(this.atomicIndexJunior != -1) {
					this.topCreditJunior = this.juniorUnmarkedList.get(this.atomicIndexJunior).getCredit();
					this.juniorCredits.clear();
					this.loadCredits("Junior", this.topCreditJunior);
					if((this.juniorCreditFlag == true) && (Integer.parseInt(this.selectedJuniorCredit) <= this.topCreditJunior)) {
						if(Integer.parseInt(this.selectedJuniorCredit) == this.topCreditJunior) {
							this.splitFlagJunior = false;	
						}
						else { 
							this.splitFlagJunior = true; 
						}
						this.loadDays("Junior");
						if(this.juniorDayFlag == true) {
							this.scheduleAtomicObj.setCredit(Integer.parseInt(this.selectedJuniorCredit));
							this.juniorHours = this.scheduleAtomicObj.getKnowledgeByDay(this.selectedJuniorDay);
							this.scheduleAtomicObj.getKnowledge().clear();
							if(this.juniorHours.contains(new SelectItem(this.selectedJuniorStartHour))) {
								this.juniorHourFlag = true;
							}
							else {
								this.juniorHourFlag = false;
							}
						}//day flag
					}//credit flag
				}//atomicIndexJunior
			}//permission
		}
		else {  
			this.optFlagJunior = "";
			this.clearTimeValues("Junior");
		}
	}
	
	public void juniorCreditChange(ValueChangeEvent event) {
		System.out.println("Junior Credit has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedJuniorCredit = newValue;
		this.juniorDays.clear();
		this.juniorHours.clear();
		if(!this.selectedJuniorCredit.equals("Choose Credit") && this.selectedJuniorCredit != null) {
			if(this.topCreditJunior != -1) {
				this.juniorCreditFlag = true;
				if(this.topCreditJunior != Integer.parseInt(this.selectedJuniorCredit)) { 
					this.splitFlagJunior = true; 
				}
				else { 
					this.splitFlagJunior = false; 
				}
				this.loadDays("Junior");
				if(this.juniorDayFlag == true) {
					this.scheduleAtomicObj.setCredit(Integer.parseInt(this.selectedJuniorCredit));
					this.juniorHours = this.scheduleAtomicObj.getKnowledgeByDay(this.selectedJuniorDay);
					this.scheduleAtomicObj.getKnowledge().clear();
					if(this.juniorHours.contains(new SelectItem(this.selectedJuniorStartHour))) {
						this.juniorHourFlag = true;
					}
					else {
						this.juniorHourFlag = false;
					}
				}
			}
		}
		else {
			this.juniorCreditFlag = false;
			this.juniorDays.clear();
			this.juniorHours.clear();
		}
	}
	
	public void juniorDayChange(ValueChangeEvent event) {
		System.out.println("Junior Day has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedJuniorDay = newValue;
		this.juniorHours.clear();
		if((!this.selectedJuniorDay.equals("Choose Day")) && (this.selectedJuniorDay != null)) {
			this.scheduleAtomicObj.setCredit(Integer.parseInt(this.selectedJuniorCredit));
			this.juniorHours = this.scheduleAtomicObj.getKnowledgeByDay(this.selectedJuniorDay);
			this.scheduleAtomicObj.getKnowledge().clear();
			this.juniorDayFlag = true;
			if(this.juniorHours.contains(new SelectItem(this.selectedJuniorStartHour))) {
				this.juniorHourFlag = true;
			}
			else {
				this.juniorHourFlag = false;
			}
		}
		else {
			this.juniorDayFlag = false;
			this.juniorHours.clear();
		}
	}
	
	public void juniorHourChange(ValueChangeEvent event) {
		System.out.println("Junior Hour has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedJuniorStartHour = newValue;
		if((!this.selectedJuniorStartHour.equals("Choose Start Hour")) && (this.selectedJuniorStartHour != null)) {
			this.juniorHourFlag = true;
		}
		else { 
			this.juniorHourFlag = false;
		}
	}
	
//******************* SENIOR EVENTS *****************************************************

	public void seniorSplitChange(ValueChangeEvent event) {
		System.out.println("Senior course has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedSeniorCourse = newValue;
		this.clearSubFields("Senior");
		this.clearTimeValues("Senior");
		if(!this.selectedSeniorCourse.equals("Course Selection") && this.selectedSeniorCourse != null) {
			this.loadSubFields("Senior");
		}	
	}
	
	public void seniorOperationChange(ValueChangeEvent event) {
		System.out.println("Senior Operation has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedSeniorOperation = newValue;
		this.topCreditSenior = -1;
		this.atomicIndexSenior = -1;
		this.clearTimeValues("Senior");
		if((!this.selectedSeniorOperation.equals("Choose Course Type")) && (this.selectedSeniorOperation != null)) {
			boolean permission = true;
			if(this.selectedSeniorOperation.equals("Theoretical")) { 
				this.optFlagSenior = "Theo";
				if(Integer.parseInt(this.seniorCreditValueTeo) == 0) { permission = false; }
			}
			if(this.selectedSeniorOperation.equals("Practice")) { 
				this.optFlagSenior = "Prac";
				if(Integer.parseInt(this.seniorCreditValuePrac) == 0) { permission = false; }
			}	
			if(permission == true) {
				this.atomicIndexSenior = this.findRelatedAtomic("Senior", "Unmarked", "SEEK", this.selectedSeniorSyllabus, this.optFlagSenior, 0);
				if(this.atomicIndexSenior != -1) {
					this.topCreditSenior = this.seniorUnmarkedList.get(this.atomicIndexSenior).getCredit();
					this.seniorCredits.clear();
					this.loadCredits("Senior", this.topCreditSenior);
					if((this.seniorCreditFlag == true) && (Integer.parseInt(this.selectedSeniorCredit) <= this.topCreditSenior)) {
						if(Integer.parseInt(this.selectedSeniorCredit) == this.topCreditSenior) {
							this.splitFlagSenior = false;	
						}
						else { 
							this.splitFlagSenior = true; 
						}
						this.loadDays("Senior");
						if(this.seniorDayFlag == true) {
							this.scheduleAtomicObj.setCredit(Integer.parseInt(this.selectedSeniorCredit));
							this.seniorHours = this.scheduleAtomicObj.getKnowledgeByDay(this.selectedSeniorDay);
							this.scheduleAtomicObj.getKnowledge().clear();
							if(this.seniorHours.contains(new SelectItem(this.selectedSeniorStartHour))) {
								this.seniorHourFlag = true;
							}
							else {
								this.seniorHourFlag = false;
							}
						}//day flag
					}//credit flag
				}//atomicIndexSenior
			}//permission
		}
		else {  
			this.optFlagSenior = "";
			this.clearTimeValues("Senior");
		}
	}
	
	public void seniorCreditChange(ValueChangeEvent event) {
		System.out.println("senior Credit has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedSeniorCredit = newValue;
		this.seniorDays.clear();
		this.seniorHours.clear();
		if(!this.selectedSeniorCredit.equals("Choose Credit") && this.selectedSeniorCredit != null) {
			if(this.topCreditSenior != -1) {
				this.seniorCreditFlag = true;
				if(this.topCreditSenior != Integer.parseInt(this.selectedSeniorCredit)) { 
					this.splitFlagSenior = true; 
				}
				else { 
					this.splitFlagSenior = false; 
				}
				this.loadDays("Senior");
				if(this.seniorDayFlag == true) {
					this.scheduleAtomicObj.setCredit(Integer.parseInt(this.selectedSeniorCredit));
					this.seniorHours = this.scheduleAtomicObj.getKnowledgeByDay(this.selectedSeniorDay);
					this.scheduleAtomicObj.getKnowledge().clear();
					if(this.seniorHours.contains(new SelectItem(this.selectedSeniorStartHour))) {
						this.seniorHourFlag = true;
					}
					else {
						this.seniorHourFlag = false;
					}
				}
			}
		}
		else {
			this.seniorCreditFlag = false;
			this.seniorDays.clear();
			this.seniorHours.clear();
		}
	}
	
	public void seniorDayChange(ValueChangeEvent event) {
		System.out.println("senior Day has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedSeniorDay = newValue;
		this.seniorHours.clear();
		if((!this.selectedSeniorDay.equals("Choose Day")) && (this.selectedSeniorDay != null)) {
			this.scheduleAtomicObj.setCredit(Integer.parseInt(this.selectedSeniorCredit));
			this.seniorHours = this.scheduleAtomicObj.getKnowledgeByDay(this.selectedSeniorDay);
			this.scheduleAtomicObj.getKnowledge().clear();
			this.seniorDayFlag = true;
			if(this.seniorHours.contains(new SelectItem(this.selectedSeniorStartHour))) {
				this.seniorHourFlag = true;
			}
			else {
				this.seniorHourFlag = false;
			}
		}
		else {
			this.seniorDayFlag = false;
			this.seniorHours.clear();
		}
	}
	
	public void seniorHourChange(ValueChangeEvent event) {
		System.out.println("Junior Hour has been changed!!!");
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedSeniorStartHour = newValue;
		if((!this.selectedSeniorStartHour.equals("Choose Start Hour")) && (this.selectedSeniorStartHour != null)) {
			this.seniorHourFlag = true;
		}
		else { 
			this.seniorHourFlag = false;
		}
	}
	
	public void deanStartHourChange(ValueChangeEvent event) {
		System.out.println("Dean Start Hour : " + event.getComponent().getId());
		String oldValue = (String)event.getOldValue();
		String newValue = (String)event.getNewValue();
		System.out.println("Old Value : "+oldValue);
		System.out.println("New Value : "+newValue);
		this.selectedDeanStartHour = newValue;
		if((!this.selectedDeanStartHour.equals("Choose Start Hour")) && (this.selectedDeanStartHour != null)) {
			this.deanHourFlag = true;
		}
		else {
			this.deanHourFlag = false;
		}
	}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//******************* LOAD & CLEAR METHODS **********************************************	
// Onur (Finished 01.05)
// This method clears the all components when year value is changed. It's a kind of
// refresh operation for system components
	private void clearAllComponents() {
		this.freshmanCourses.clear();
		this.sophomoreCourses.clear();
		this.juniorCourses.clear();
		this.seniorCourses.clear();
		this.deanCourseList.clear();
		
		this.deanSchedules.clear();
		this.freshmanUnmarkedList.clear();
		this.sophomoreUnmarkedList.clear();
		this.juniorUnmarkedList.clear();
		this.seniorUnmarkedList.clear();
		
		this.freshmanMarkedList.clear();
		this.sophomoreMarkedList.clear();
		this.juniorMarkedList.clear();
		this.seniorMarkedList.clear();
		
		this.freshmanBackup.clear();
		this.sophomoreBackup.clear();
		this.juniorBackup.clear();
		this.seniorBackup.clear();
		
		this.freshmanBackupMarked.clear();
		this.sophomoreBackupMarked.clear();
		this.juniorBackupMarked.clear();
		this.seniorBackupMarked.clear();
		
		this.freshmanLockedIndexes.clear();
		this.sophomoreLockedIndexes.clear();
		this.juniorLockedIndexes.clear();
		this.seniorLockedIndexes.clear();
		
		this.clearControlTables("Freshman");
		this.clearControlTables("Sophomore");
		this.clearControlTables("Junior");
		this.clearControlTables("Senor");
		
		this.clearDeanCourseTable();
		this.clearFreshmanCourseTable();
		this.clearSophomoreCourseTable();
		this.clearJuniorCourseTable();
		this.clearSeniorCourseTable();
		
		this.clearSubFields("Freshman");
		this.clearSubFields("Sophomore");
		this.clearSubFields("Junior");
		this.clearSubFields("Senior");
		
		this.clearTimeValues("Freshman");
		this.clearTimeValues("Sophomore");
		this.clearTimeValues("Junior");
		this.clearTimeValues("Senior");
	}
//***************************************************************************************
// Onur (Finished 01.05)
// This method provides the all data needed for all components which are filled after 
// selecting the year and semester values. Try catch block, catches error which can be
// thrown during the convert of string to an integer.
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
// Onur (Finished 03.05)
// This method returns the all dean courses in related year and semester. The subfield of
// bean class which holds the data about dean courses filled with the related data here.
	private void loadDeanCourses(int year, String semester) {
		ArrayList<Syllabus> sList = syllabusObj.getDeanCourses(year, semester);
		if((sList != null) && (sList.size() != 0)) {
			this.deanSchedules = this.generateAtomic(sList);
			this.deanCourseList.add(new SelectItem("Choose Course"));
			for(int i = 0; i < sList.size(); i++) {
				String name = sList.get(i).getCourse().getCourseName();
				this.deanCourseList.add(new SelectItem(name));
			}
			return;
		}
		this.deanCourseList = new ArrayList<SelectItem>();
	}
//***************************************************************************************
// Onur (Finished 04.05)
// This method filled the related subfields of class with the expected data which are
// determined by the year and semester values. Each subfield of four grade filled in here.
	private void loadCoursesByGrade(int year, String semester) {
		//First Year(Freshman) Courses are loaded
		ArrayList<Syllabus> itemList = syllabusObj.getSyllabusByGrade(year, semester, 1);
		if(itemList.size() != 0) {
			this.freshmanUnmarkedList = this.generateAtomic(itemList);
			this.freshmanBackup = this.generateAtomic(itemList);
			this.freshmanCourses.add(new SelectItem("Course Selection"));
			for(int i = 0; i < itemList.size(); i++) {
				this.freshmanCourses.add(new SelectItem(itemList.get(i).getCourse().getCourseCode()));
			}
		}
		//Second Year(Sophomore) Courses are loaded
		itemList = syllabusObj.getSyllabusByGrade(year, semester, 2);
		if(itemList.size() != 0) {
			this.sophomoreUnmarkedList = this.generateAtomic(itemList);
			this.sophomoreBackup = this.generateAtomic(itemList);
			this.sophomoreCourses.add(new SelectItem("Course Selection"));
			for(int i = 0; i < itemList.size(); i++) {
				this.sophomoreCourses.add(new SelectItem(itemList.get(i).getCourse().getCourseCode()));
			}
		}
		//Third Year(Junior) Courses are loaded
		itemList = syllabusObj.getSyllabusByGrade(year, semester, 3);
		if(itemList.size() != 0) {
			this.juniorUnmarkedList = this.generateAtomic(itemList);
			this.juniorBackup = this.generateAtomic(itemList);
			this.juniorCourses.add(new SelectItem("Course Selection"));
			for(int i = 0; i < itemList.size(); i++) {
				this.juniorCourses.add(new SelectItem(itemList.get(i).getCourse().getCourseCode()));
			}
		}
		//Fourth Year(Senior) Courses are loaded
		itemList = syllabusObj.getSyllabusByGrade(year, semester, 4);
		if(itemList.size() != 0) {
			this.seniorUnmarkedList = this.generateAtomic(itemList);
			this.seniorBackup = this.generateAtomic(itemList);
			this.seniorCourses.add(new SelectItem("Course Selection"));
			for(int i = 0; i < itemList.size(); i++) {
				this.seniorCourses.add(new SelectItem(itemList.get(i).getCourse().getCourseCode()));
			}
		}
	}
//***************************************************************************************
// Onur (Finished 03.05)
// This method loads the 'semesterList' subfield which holds the data of semester combobox
// with the related data below.
	private void loadSemester() {	
		this.semesterList.add(new SelectItem("Choose Semester"));
		this.semesterList.add(new SelectItem("Fall"));
		this.semesterList.add(new SelectItem("Spring"));
		this.semesterList.add(new SelectItem("Summer"));
	}
//***************************************************************************************
	private void loadDays(String grade) {
		if(grade.equals("Freshman")) {
			freshmanDays.add(new SelectItem("Choose Day"));
			freshmanDays.add(new SelectItem("Monday"));
			freshmanDays.add(new SelectItem("Tuesday"));
			freshmanDays.add(new SelectItem("Wednesday"));
			freshmanDays.add(new SelectItem("Thursday"));
			freshmanDays.add(new SelectItem("Friday"));
			return;
		}
		if(grade.equals("Sophomore")) {
			sophomoreDays.add(new SelectItem("Choose Day"));
			sophomoreDays.add(new SelectItem("Monday"));
			sophomoreDays.add(new SelectItem("Tuesday"));
			sophomoreDays.add(new SelectItem("Wednesday"));
			sophomoreDays.add(new SelectItem("Thursday"));
			sophomoreDays.add(new SelectItem("Friday"));
			return;
		}
		if(grade.equals("Junior")) {
			juniorDays.add(new SelectItem("Choose Day"));
			juniorDays.add(new SelectItem("Monday"));
			juniorDays.add(new SelectItem("Tuesday"));
			juniorDays.add(new SelectItem("Wednesday"));
			juniorDays.add(new SelectItem("Thursday"));
			juniorDays.add(new SelectItem("Friday"));
			return;
		}
		if(grade.equals("Senior")) {
			seniorDays.add(new SelectItem("Choose Day"));
			seniorDays.add(new SelectItem("Monday"));
			seniorDays.add(new SelectItem("Tuesday"));
			seniorDays.add(new SelectItem("Wednesday"));
			seniorDays.add(new SelectItem("Thursday"));
			seniorDays.add(new SelectItem("Friday"));
			return;
		}
	}
//***************************************************************************************
	private void loadCredits(String grade, int pCredit) {
		if(grade.equals("Freshman")) {
			int credit = pCredit;
			if(credit > 4) { credit = 4; }
			this.freshmanCredits = new ArrayList<SelectItem>();
			this.freshmanCredits.add(new SelectItem("Choose Credit"));
			for(int i = 0; i < credit; i++) {
				this.freshmanCredits.add(new SelectItem(Integer.toString(i + 1)));
			}
			return;
		}
		if(grade.equals("Sophomore")) {
			int credit = pCredit;
			if(credit > 4) { credit = 4; }
			this.sophomoreCredits = new ArrayList<SelectItem>();
			this.sophomoreCredits.add(new SelectItem("Choose Credit"));
			for(int i = 0; i < credit; i++) {
				this.sophomoreCredits.add(new SelectItem(Integer.toString(i + 1)));
			}
			return;
		}
		if(grade.equals("Junior")) {
			int credit = pCredit;
			if(credit > 4) { credit = 4; }
			this.juniorCredits = new ArrayList<SelectItem>();
			this.juniorCredits.add(new SelectItem("Choose Credit"));
			for(int i = 0; i < credit; i++) {
				this.juniorCredits.add(new SelectItem(Integer.toString(i + 1)));
			}
			return;
		}
		if(grade.equals("Senior")) {
			int credit = pCredit;
			if(credit > 4) { credit = 4; }
			this.seniorCredits = new ArrayList<SelectItem>();
			this.seniorCredits.add(new SelectItem("Choose Credit"));
			for(int i = 0; i < credit; i++) {
				this.seniorCredits.add(new SelectItem(Integer.toString(i + 1)));
			}
			return;
		}
	}
//***************************************************************************************
// Onur (Finished 31.04)
// When selection of dean course changed, then all information contained in sub-components
// must be cleared and updated again. This method clears the all fields related with.
	private void clearDeanSubFields() {
		this.deanLecturerList.clear();		
		this.creditValueTheo = "";
		this.creditValuePrac = "";
	}
// Onur (Finished 03.05)
// According to 'selected dean course' this method loads the related components with 
// related data 
	
	private void loadDeanSubFields()
	{
		int year = Integer.parseInt(this.selectedYear);
		ArrayList<Syllabus> itemList = syllabusObj.getSyllabusByCourseName(this.selectedDeanCourse, year, this.selectedSemester);
		this.creditValueTheo = Integer.toString(itemList.get(0).getCourse().getTeoricLectureHours());
		this.creditValuePrac = Integer.toString(itemList.get(0).getCourse().getPracticeLectureHourse());
		this.selectedDeanSyllabus = itemList.get(0);
		this.deanLecturerList.add(new SelectItem("Choose Lecturer"));
		this.deanLecturerList.add(new SelectItem(itemList.get(0).getLecturer().getLecturerName()));
	}
	
	private void retrieveFromBackup(String grade) {
		if(grade.equals("Freshman")) {
			this.freshmanUnmarkedList.clear();
			for(int i = 0; i < this.freshmanBackup.size(); i++) {
				 this.freshmanUnmarkedList.add(new ScheduleAtomic(this.freshmanBackup.get(i)));
			}
			return;
		}
		if(grade.equals("Sophomore")) {
			this.sophomoreUnmarkedList.clear();
			for(int i = 0; i < this.sophomoreBackup.size(); i++) {
				 this.sophomoreUnmarkedList.add(new ScheduleAtomic(this.sophomoreBackup.get(i)));
			}
			return;
		}
		if(grade.equals("Junior")) {
			this.juniorUnmarkedList.clear();
			for(int i = 0; i < this.juniorBackup.size(); i++) {
				 this.juniorUnmarkedList.add(new ScheduleAtomic(this.juniorBackup.get(i)));
			}
			return;
		}
		if(grade.equals("Senior")) {
			this.seniorUnmarkedList.clear();
			for(int i = 0; i < this.seniorBackup.size(); i++) {
				 this.seniorUnmarkedList.add(new ScheduleAtomic(this.seniorBackup.get(i)));
			}
			return;
		}
	}
	
	private void loadToBackup(String grade) {
		if(grade.equals("Freshman")) {
			this.freshmanBackupMarked.clear();
			for(int i = 0; i < this.freshmanMarkedList.size(); i++) {
				this.freshmanBackupMarked.add(new ScheduleAtomic(this.freshmanMarkedList.get(i)));
			}
			return;
		}
		if(grade.equals("Sophomore")) {
			this.sophomoreBackupMarked.clear();
			for(int i = 0; i < this.sophomoreMarkedList.size(); i++) {
				this.sophomoreBackupMarked.add(new ScheduleAtomic(this.sophomoreMarkedList.get(i)));
			}
			return;
		}
		if(grade.equals("Junior")) {
			this.juniorBackupMarked.clear();
			for(int i = 0; i < this.juniorMarkedList.size(); i++) {
				this.juniorBackupMarked.add(new ScheduleAtomic(this.juniorMarkedList.get(i)));
			}
			return;
		}
		if(grade.equals("Senior")) {
			this.seniorBackupMarked.clear();
			for(int i = 0; i < this.seniorMarkedList.size(); i++) {
				this.seniorBackupMarked.add(new ScheduleAtomic(this.seniorMarkedList.get(i)));
			}
			return;
		}
	}
	
//****************** FIELD CLEAR & LOAD METHODS OF GRADES ******************************
	private void loadSubFields(String grade) {
		int year = Integer.parseInt(this.selectedYear);
		if(grade.equals("Freshman")) {
			ArrayList<Syllabus> itemList = syllabusObj.getSyllabusByCourseCode(year, this.selectedSemester, this.selectedFreshmanCourse);
			this.selectedFreshmanSyllabus = itemList.get(0);
			
			this.selectedFreshmanSplitCourse = this.selectedFreshmanSyllabus.getCourse().getCourseName();
			this.selectedFreshmanSplitLecturer = this.selectedFreshmanSyllabus.getLecturer().getLecturerName();
			this.freshmanCreditValeuTeo = Integer.toString(this.selectedFreshmanSyllabus.getCourse().getTeoricLectureHours());
			this.freshmanCreditValuePrac = Integer.toString(this.selectedFreshmanSyllabus.getCourse().getPracticeLectureHourse());
			
			this.freshmanOperations.add(new SelectItem("Choose Course Type"));
			this.freshmanOperations.add(new SelectItem("Theoretical"));
			this.freshmanOperations.add(new SelectItem("Practice"));
			return;
		}
		if(grade.equals("Sophomore")) {
			ArrayList<Syllabus> itemList = syllabusObj.getSyllabusByCourseCode(year, this.selectedSemester, this.selectedSophomoreCourse);
			this.selectedSophomoreSyllabus = itemList.get(0);
			
			this.selectedSophomoreSplitCourse = this.selectedSophomoreSyllabus.getCourse().getCourseName();
			this.selectedSophomoreSplitLecturer = this.selectedSophomoreSyllabus.getLecturer().getLecturerName();
			this.sophomoreCreditValeuTeo = Integer.toString(this.selectedSophomoreSyllabus.getCourse().getTeoricLectureHours());
			this.sophomoreCreditValuePrac = Integer.toString(this.selectedSophomoreSyllabus.getCourse().getPracticeLectureHourse());
			
			this.sophomoreOperations.add(new SelectItem("Choose Course Type"));
			this.sophomoreOperations.add(new SelectItem("Theoretical"));
			this.sophomoreOperations.add(new SelectItem("Practice"));
			return;
		}
		if(grade.equals("Junior")) { 
			ArrayList<Syllabus> itemList = syllabusObj.getSyllabusByCourseCode(year, this.selectedSemester, this.selectedJuniorCourse);
			this.selectedJuniorSyllabus = itemList.get(0);
		
			this.selectedJuniorSplitCourse = this.selectedJuniorSyllabus.getCourse().getCourseName();
			this.selectedJuniorSplitLecturer = this.selectedJuniorSyllabus.getLecturer().getLecturerName();
			this.juniorCreditValueTeo = Integer.toString(this.selectedJuniorSyllabus.getCourse().getTeoricLectureHours());
			this.juniorCreditValuePrac = Integer.toString(this.selectedJuniorSyllabus.getCourse().getPracticeLectureHourse());
		
			this.juniorOperations.add(new SelectItem("Choose Course Type"));
			this.juniorOperations.add(new SelectItem("Theoretical"));
			this.juniorOperations.add(new SelectItem("Practice"));
			return;
		}
		if(grade.equals("Senior")) { 
			ArrayList<Syllabus> itemList = syllabusObj.getSyllabusByCourseCode(year, this.selectedSemester, this.selectedSeniorCourse);
			this.selectedSeniorSyllabus = itemList.get(0);
		
			this.selectedSeniorSplitCourse = this.selectedSeniorSyllabus.getCourse().getCourseName();
			this.selectedSeniorSplitLecturer = this.selectedSeniorSyllabus.getLecturer().getLecturerName();
			this.seniorCreditValueTeo = Integer.toString(this.selectedSeniorSyllabus.getCourse().getTeoricLectureHours());
			this.seniorCreditValuePrac = Integer.toString(this.selectedSeniorSyllabus.getCourse().getPracticeLectureHourse());
		
			this.seniorOperations.add(new SelectItem("Choose Course Type"));
			this.seniorOperations.add(new SelectItem("Theoretical"));
			this.seniorOperations.add(new SelectItem("Practice"));
			return;
		}
	}
	
	private void clearSubFields(String grade) {
		if(grade.equals("Freshman")) {
			this.selectedFreshmanSplitCourse = "";
			this.selectedFreshmanSplitLecturer = "";
			this.freshmanCreditValeuTeo = "";
			this.freshmanCreditValuePrac = "";
			this.freshmanOperations.clear();
			return;
		}
		if(grade.equals("Sophomore")) { 
			this.selectedSophomoreSplitCourse = "";
			this.selectedSophomoreSplitLecturer = "";
			this.sophomoreCreditValeuTeo = "";
			this.sophomoreCreditValuePrac = "";
			this.sophomoreOperations.clear();
			return;
		}
		if(grade.equals("Junior")) { 
			this.selectedJuniorSplitCourse = "";
			this.selectedJuniorSplitLecturer = "";
			this.juniorCreditValueTeo = "";
			this.juniorCreditValuePrac = "";
			this.juniorOperations.clear();
			return;
		}
		if(grade.equals("Senior")) { 
			this.selectedSeniorSplitCourse = "";
			this.selectedSeniorSplitLecturer = "";
			this.seniorCreditValueTeo = "";
			this.seniorCreditValuePrac = "";
			this.seniorOperations.clear();
			return;
		}
	}
	
	private void clearTimeValues(String grade) {
		if(grade.equals("Freshman")) {
			this.freshmanCredits.clear();
			this.freshmanDays.clear();
			this.freshmanHours.clear(); 
			
			this.selectedFreshmanCredit = "";
			this.selectedFreshmanDay = "";
			this.selectedFreshmanStartHour = "";
			return;
		}
		if(grade.equals("Sophomore")) {
			this.sophomoreCredits.clear();
			this.sophomoreDays.clear();
			this.sophomoreHours.clear();
			
			this.selectedSophomoreCredit = "";
			this.selectedSophomoreDay = "";
			this.selectedSophomoreStartHour = "";
			return;
		}
		if(grade.equals("Junior")) { 
			this.juniorCredits.clear();
			this.juniorDays.clear();
			this.juniorHours.clear();
			
			this.selectedJuniorCredit = "";
			this.selectedJuniorDay = "";
			this.selectedJuniorStartHour = "";
			return;
		}
		if(grade.equals("Senior")) { 
			this.seniorCredits.clear();
			this.seniorDays.clear();
			this.seniorHours.clear();
			
			this.selectedSeniorCredit = "";
			this.selectedSeniorDay = "";
			this.selectedSeniorStartHour = "";
			return;
		}
	}
		
//***************************************************************************************	
//*********************** UTILITY METHODS ***********************************************
// Onur (Finished 29.04)
// This function has ability to detect duplication of an item in a list. We can not use
// DISTINCT operation in our stored procedures. That's why we have to eliminate duplicated
// data. This method called in 'getYearList()'.
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
// Onur (Finished 03.05)
// This function generates Schedule Automatic objects from Syllabus objects. In another
// way we can say a syllabus list is converted to a scheduleAtomic list. 
	private ArrayList<ScheduleAtomic> generateAtomic(ArrayList<Syllabus> sList) {
		ArrayList<ScheduleAtomic> retList = new ArrayList<ScheduleAtomic>();
		for(int i = 0; i < sList.size(); i++) {
			Syllabus sItem = new Syllabus(sList.get(i));
			
			boolean att = sItem.getCourse().isAttendance();
			int courseId = sItem.getCourse().getCourseId();
			String courseName = sItem.getCourse().getCourseName();
			String preCondition = sItem.getCourse().getPrecondition();
			
			int lecturerId = sItem.getLecturer().getLecturerId();
			String lecturerName = sItem.getLecturer().getLecturerName();
			String lecturerTitle = sItem.getLecturer().getTitle();
			
			int classroomId = sItem.getClassroom().getClassroomId();
			String classroomCode = sItem.getClassroom().getClassroomCode();
			int grade = sItem.getCourse().getGrade();
			
			int tHour = sItem.getCourse().getTeoricLectureHours();
			if(tHour != 0) {
				retList.add(new ScheduleAtomic(sItem, "Theo", "", 0, tHour, att, courseId, lecturerId, classroomId, preCondition, courseName, lecturerName, lecturerTitle, classroomCode, grade));
			}
			int pHour = sItem.getCourse().getPracticeLectureHourse();
			if(pHour != 0) {
				retList.add(new ScheduleAtomic(sItem, "Prac", "", 0, pHour, att, courseId, lecturerId, classroomId, preCondition, courseName, lecturerName, lecturerTitle, classroomCode, grade));
			}
		}
		return retList;
	}
	
	private int findRelatedAtomic(String grade, String sign, String opType, Syllabus syllabus, String courseType, int credit) {
		ScheduleAtomic sAtom = new ScheduleAtomic(syllabus, courseType, "", 0, credit, false, 0, 0, 0, "", "", "", "", "", 0);
		if(grade.equals("Freshman")) {
			if(sign.equals("Unmarked")) {
				for(int i = 0; i < this.freshmanUnmarkedList.size(); i++) {
					if(sAtom.equals(this.freshmanUnmarkedList.get(i))) { return i; }
				}
				return -1;
			}
			if(sign.equals("Marked")) {
				for(int i = 0; i < this.freshmanMarkedList.size(); i++) {
					if(sAtom.equals(this.freshmanMarkedList.get(i))) { return i; }
				}
				return -1;
			}
		}
		if(grade.equals("Sophomore")) {
			if(sign.equals("Unmarked")) {
				for(int i = 0; i < this.sophomoreUnmarkedList.size(); i++) {
					if(sAtom.equals(this.sophomoreUnmarkedList.get(i))) { return i; }
				}
				return -1;
			}
			if(sign.equals("Marked")) {
				for(int i = 0; i < this.sophomoreMarkedList.size(); i++) {
					if(sAtom.equals(this.sophomoreMarkedList.get(i))) { return i; }
				}
				return -1;
			}
		}
		if(grade.equals("Junior")) {
			if(sign.equals("Unmarked")) {
				for(int i = 0; i < this.juniorUnmarkedList.size(); i++) {
					if(sAtom.equals(this.juniorUnmarkedList.get(i))) { return i; }
				}
				return -1;
			}
			if(sign.equals("Marked")) {
				for(int i = 0; i < this.juniorMarkedList.size(); i++) {
					if(sAtom.equals(this.juniorMarkedList.get(i))) { return i; }
				}
				return -1;
			}
		}
		if(grade.equals("Senior")) {
			if(sign.equals("Unmarked")) {
				for(int i = 0; i < this.seniorUnmarkedList.size(); i++) {
					if(sAtom.equals(this.seniorUnmarkedList.get(i))) { return i; }
				}
				return -1;
			}
			if(sign.equals("Marked")) {
				for(int i = 0; i < this.seniorMarkedList.size(); i++) {
					if(sAtom.equals(this.seniorMarkedList.get(i))) { return i; }
				}
				return -1;
			}
		}
		if(grade.equals("Dean")) {
			for(int i = 0; i < this.deanSchedules.size(); i++) {
				if(sAtom.equals(this.deanSchedules.get(i))) { return i; }
			}
			return -1;
		}
		return -1;
	}
	
	private void loadLockedIndexes() {
		Integer iVal = new Integer(-1);
		for(int i = 0; i < this.freshmanLockedIndexes.size(); i++) {
			Index item = this.freshmanLockedIndexes.get(i);
			int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
			int day = this.dayMapToIndexHash.get(item.getDay());
			int hour = item.getHour();
			hour--;
			this.controlFreshmanCourse[hour][dayMatrix] = iVal;
			this.initFreshmanCourseTable[hour][day] = "LOCKED";
		}
		for(int i = 0; i < this.sophomoreLockedIndexes.size(); i++) {
			Index item = this.sophomoreLockedIndexes.get(i);
			int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
			int day = this.dayMapToIndexHash.get(item.getDay());
			int hour = item.getHour();
			hour--;
			this.controlSophomoreCourse[hour][dayMatrix] = iVal;
			this.initSophomoreCourseTable[hour][day] = "LOCKED";
		}
		for(int i = 0; i < this.juniorLockedIndexes.size(); i++) {
			Index item = this.juniorLockedIndexes.get(i);
			int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
			int day = this.dayMapToIndexHash.get(item.getDay());
			int hour = item.getHour();
			hour--;
			this.controlJuniorCourse[hour][dayMatrix] = iVal;
			this.initJuniorCourseTable[hour][day] = "LOCKED";
		}
		for(int i = 0; i < this.seniorLockedIndexes.size(); i++) {
			Index item = this.seniorLockedIndexes.get(i);
			int dayMatrix = this.dayMapToIntegerHash.get(item.getDay());
			int day = this.dayMapToIndexHash.get(item.getDay());
			int hour = item.getHour();
			hour--;
			this.controlSeniorCourse[hour][dayMatrix] = iVal;
			this.initSeniorCourseTable[hour][day] = "LOCKED";
		}
	}
	
	private void writeFreshmanSchedule() {
		for(int i = 0; i < this.freshmanMarkedList.size(); i++) {
			ScheduleAtomic item = this.freshmanMarkedList.get(i);
			String day = item.getDay();
			int hour = item.getStartHour() - 1;
			int credit = item.getCredit();
			int dayTable = this.dayMapToIndexHash.get(day);
			for(int j = 0; j < credit; j++) {
				if(!this.initFreshmanCourseTable[hour][dayTable].equals("")) {
					this.initFreshmanCourseTable[hour][dayTable] = this.initFreshmanCourseTable[hour][dayTable] + "\n" + item.writeInfo();
				}
				else {
					this.initFreshmanCourseTable[hour][dayTable] = item.writeInfo();
				}
				hour++;
			}
		}
	}

	private void writeSophomoreSchedule() {
		for(int i = 0; i < this.sophomoreMarkedList.size(); i++) {
			ScheduleAtomic item = this.sophomoreMarkedList.get(i);
			String day = item.getDay();
			int hour = item.getStartHour() - 1;
			int credit = item.getCredit();
			int dayTable = this.dayMapToIndexHash.get(day);
			for(int j = 0; j < credit; j++) {
				if(!this.initSophomoreCourseTable[hour][dayTable].equals("")) {
					this.initSophomoreCourseTable[hour][dayTable] = this.initSophomoreCourseTable[hour][dayTable] + "\n" + item.writeInfo();
				}
				else {
					this.initSophomoreCourseTable[hour][dayTable] = item.writeInfo();
				}
				hour++;
			}
		}
	}
	
	private void writeJuniorSchedule() {
		for(int i = 0; i < this.juniorMarkedList.size(); i++) {
			ScheduleAtomic item = this.juniorMarkedList.get(i);
			String day = item.getDay();
			int hour = item.getStartHour() - 1;
			int credit = item.getCredit();
			int dayTable = this.dayMapToIndexHash.get(day);
			for(int j = 0; j < credit; j++) {
				if(!this.initJuniorCourseTable[hour][dayTable].equals("")) {
					this.initJuniorCourseTable[hour][dayTable] = this.initJuniorCourseTable[hour][dayTable] + "\n" + item.writeInfo();
				}
				else {
					this.initJuniorCourseTable[hour][dayTable] = item.writeInfo();
				}
				hour++;
			}
		}
	}
	
	private void writeSeniorSchedule() {
		for(int i = 0; i < this.seniorMarkedList.size(); i++) {
			ScheduleAtomic item = this.seniorMarkedList.get(i);
			String day = item.getDay();
			int hour = item.getStartHour() - 1;
			int credit = item.getCredit();
			int dayTable = this.dayMapToIndexHash.get(day);
			for(int j = 0; j < credit; j++) {
				if(!this.initSeniorCourseTable[hour][dayTable].equals("")) {
					this.initSeniorCourseTable[hour][dayTable] = this.initSeniorCourseTable[hour][dayTable] + "\n" + item.writeInfo();
				}
				else {
					this.initSeniorCourseTable[hour][dayTable] = item.writeInfo();
				}
				hour++;
			}
		}
	}
	
	private void clearControlTables(String grade) {
		if(grade.equals("Freshman")) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 5; j++) {
					this.controlFreshmanCourse[i][j] = 0;
					this.controlFreshmanLecturer[i][j] = 0;
					this.controlFreshmanClassroom[i][j] = 0;
				}
			}
			return;
		}
		if(grade.equals("Sophomore")) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 5; j++) {
					this.controlSophomoreCourse[i][j] = 0;
					this.controlSophomoreLecturer[i][j] = 0;
					this.controlSophomoreClassroom[i][j] = 0;
				}
			}
			return;
		}
		if(grade.equals("Junior")) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 5; j++) {
					this.controlJuniorCourse[i][j] = 0;
					this.controlJuniorLecturer[i][j] = 0;
					this.controlJuniorClassroom[i][j] = 0;
				}
			}
			return;
		}
		if(grade.equals("Senior")) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 5; j++) {
					this.controlSeniorCourse[i][j] = 0;
					this.controlSeniorLecturer[i][j] = 0;
					this.controlSeniorClassroom[i][j] = 0;
				}
			}
			return;
		}
	}
	
	private void refreshKnowledges(String grade) {
		if(grade.equals("Freshman")) {
			for(int i = 0; i < this.freshmanUnmarkedList.size(); i++) {
				this.freshmanUnmarkedList.get(i).refreshKnowledge();
			}
		}
		if(grade.equals("Sophomore")) {
			for(int i = 0; i < this.sophomoreUnmarkedList.size(); i++) {
				this.sophomoreUnmarkedList.get(i).refreshKnowledge();
			}
		}
		if(grade.equals("Senior")) {
			for(int i = 0; i < this.seniorUnmarkedList.size(); i++) {
				this.seniorUnmarkedList.get(i).refreshKnowledge();
			}
		}
		if(grade.equals("Junior")) {
			for(int i = 0; i < this.juniorUnmarkedList.size(); i++) {
				this.juniorUnmarkedList.get(i).refreshKnowledge();
			}
		}
	}
	
	private boolean controlCourse(String grade, int day, int hour) {
		boolean retVal = true;
		if(grade.equals("Freshman")) {
			if(this.controlFreshmanCourse[hour][day] != 0) {
				retVal = false;
			}
			return retVal;			
		}
		if(grade.equals("Sophomore")) {
			if(this.controlSophomoreCourse[hour][day] != 0) {
				retVal = false;
			}
			return retVal;			
		}
		if(grade.equals("Senior")) {
			if(this.controlSeniorCourse[hour][day] != 0) {
				retVal = false;
			}
			return retVal;			
		}
		if(grade.equals("Junior")) {
			if(this.controlJuniorCourse[hour][day] != 0) {
				retVal = false;
			}
			return retVal;			
		}
		return retVal;
	}
	
	private boolean controlLecturerInOthers(String grade, int day, int hour, int lecturerId) {
		boolean retVal = true;
		if(grade.equals("Sophomore")) {
			if(this.controlFreshmanLecturer[hour][day] == lecturerId) {
				retVal = false;
			}
			return retVal;
		}
		if(grade.equals("Junior")) {
			if((this.controlFreshmanLecturer[hour][day] == lecturerId) ||
			   (this.controlSophomoreLecturer[hour][day] == lecturerId)) {
				retVal = false;
			}
			return retVal;
		}
		if(grade.equals("Senior")) {
			if((this.controlFreshmanLecturer[hour][day] == lecturerId) ||
			   (this.controlSophomoreLecturer[hour][day] == lecturerId) ||
			   (this.controlJuniorLecturer[hour][day] == lecturerId)) {
				retVal = false;
			}
			return retVal;
		}
		return retVal;
	}
	
	private boolean controlClassroomInOthers(String grade, int day, int hour, int classroomId) {
		boolean retVal = true;
		if(grade.equals("Sophomore")) {
			if(this.controlFreshmanClassroom[hour][day] == classroomId) {
				retVal = false;
			}
			return retVal;
		}
		if(grade.equals("Junior")) {
			if((this.controlFreshmanClassroom[hour][day] == classroomId) ||
			   (this.controlSophomoreClassroom[hour][day] == classroomId)) {
				retVal = false;
			}
			return retVal;
		}
		if(grade.equals("Senior")) {
			if((this.controlFreshmanClassroom[hour][day] == classroomId) ||
			   (this.controlSophomoreClassroom[hour][day] == classroomId) ||
			   (this.controlJuniorClassroom[hour][day] == classroomId)) {
				retVal = false;
			}
			return retVal;
		}
		return retVal;
	}
	
	private boolean controlLecturer(String grade, int day, int hour, int lecturerId) {
		boolean retVal = true;
		if(grade.equals("Freshman")) {
			if(this.controlFreshmanLecturer[hour][day] == lecturerId) {
				retVal = false;
			}
			return retVal;			
		}
		if(grade.equals("Sophomore")) {
			if(this.controlSophomoreLecturer[hour][day] == lecturerId) {
				retVal = false;
			}
			return retVal;			
		}
		if(grade.equals("Senior")) {
			if(this.controlSeniorLecturer[hour][day] == lecturerId) {
				retVal = false;
			}
			return retVal;			
		}
		if(grade.equals("Junior")) {
			if(this.controlJuniorLecturer[hour][day] == lecturerId) {
				retVal = false;
			}
			return retVal;			
		}
		return retVal;
	}
	
	private boolean controlClassroom(String grade, int day, int hour, int classroomId) {
		boolean retVal = true;
		if(grade.equals("Freshman")) {
			if(this.controlFreshmanClassroom[hour][day] == classroomId) {
				retVal = false;
			}
			return retVal;			
		}
		if(grade.equals("Sophomore")) {
			if(this.controlSophomoreClassroom[hour][day] == classroomId) {
				retVal = false;
			}
			return retVal;			
		}
		if(grade.equals("Senior")) {
			if(this.controlSeniorClassroom[hour][day] == classroomId) {
				retVal = false;
			}
			return retVal;			
		}
		if(grade.equals("Junior")) {
			if(this.controlJuniorClassroom[hour][day] == classroomId) {
				retVal = false;
			}
			return retVal;			
		}
		return retVal;
	}
//************************************************************************************************
//******************** SCHEDULING ALGORITHMS *****************************************************	
	private void freshmanAutoScheduling() {
		try {
			SortedList sList = new SortedList(this.freshmanUnmarkedList);
			ArrayList<ScheduleAtomic> atomicList = sList.convertToOneList();
			ArrayList<ScheduleAtomic> attList = sList.getFalseAttendanceList();
			ArrayList<ScheduleAtomic> stack = new ArrayList<ScheduleAtomic>();

			int unmarkedIndex = this.scheduleAtomicObj.getRandomValue(atomicList.size());
			ScheduleAtomic workingAtomic = atomicList.get(unmarkedIndex);
			int range = workingAtomic.getKnowledgeSize();
			int randomPosition = this.scheduleAtomicObj.getRandomValue(range);
			int randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
			Index iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
			
			boolean optDone = false;
			while(atomicList.size() != 0) {	
				if(optDone == true) {
					unmarkedIndex = this.scheduleAtomicObj.getRandomValue(atomicList.size());
					workingAtomic = atomicList.get(unmarkedIndex);
					range = workingAtomic.getKnowledgeSize();
					randomPosition = this.scheduleAtomicObj.getRandomValue(range);
					randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
					iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
				}
				
				boolean options = true;
				optDone = false;
				while((options != false) && (optDone != true)) {
					int hour = iVal.getHour();
					hour = hour - 1;
					int day = this.dayMapToIntegerHash.get(iVal.getDay());
					int limit = workingAtomic.getCredit() + hour;
					
					boolean controlCheck = true;
					for(int h = hour; ((controlCheck != false) && (h < limit)); h++) {
						controlCheck = this.controlCourse("Freshman", day, h);
					}
					
					for(int j = hour; ((controlCheck != false) && (j < limit)); j++) {
						controlCheck = this.controlLecturer("Freshman", day, j, workingAtomic.getLecturerId());
					}
					for(int k = hour; ((controlCheck != false) && (k < limit)); k++) {
						controlCheck = this.controlClassroom("Freshman", day, k, workingAtomic.getClassroomId());
					}
					
					if(controlCheck == true) {
						int val = randomVal;
						workingAtomic.setDay(iVal.getDay());
						workingAtomic.setStartHour(iVal.getHour());
						int hourRollBack = workingAtomic.getStartHour();
						hourRollBack = hourRollBack - 1;
						for(int p = 0; p < workingAtomic.getCredit(); p++) {
							Integer newInteger = new Integer(val); 
							atomicList = sList.forward(atomicList, newInteger);
							stack = sList.forward(stack, newInteger);
							this.controlFreshmanLecturer[hourRollBack][day] = workingAtomic.getLecturerId();
							this.controlFreshmanClassroom[hourRollBack][day] = workingAtomic.getClassroomId();
							this.controlFreshmanCourse[hourRollBack][day] = workingAtomic.getCourseId();
							val++;
							hourRollBack++;
						}
						ScheduleAtomic sItem = new ScheduleAtomic(workingAtomic);
						stack.add(sItem);
						atomicList.remove(unmarkedIndex);
						optDone = true;
					} 
					else {
						workingAtomic.removeKnowledgeByIndex(randomPosition);
						workingAtomic.addBlockedSpot(randomVal, workingAtomic.getCredit());
						if(workingAtomic.getKnowledge().size() == 0) {
							int index = stack.size() - 1;
							workingAtomic = new ScheduleAtomic(stack.get(index));
							
							int tempHour = workingAtomic.getStartHour();
							int tempDay = this.dayMapToIntegerHash.get(workingAtomic.getDay());
							Integer known = new Integer((tempDay * 8) + tempHour);
							atomicList = scheduleAtomicObj.rollback(atomicList, known);
							
							int hourRollBack = workingAtomic.getStartHour() - 1;
							int dayRollBack = this.dayMapToIntegerHash.get(workingAtomic.getDay()); 
							for(int r = 0; r < workingAtomic.getCredit(); r++) {
								this.controlFreshmanLecturer[hourRollBack][dayRollBack] = 0;
								this.controlFreshmanClassroom[hourRollBack][dayRollBack] = 0;
								this.controlFreshmanCourse[hourRollBack][day] = 0;
								hourRollBack++;
							}
							stack.remove(index);
							stack = scheduleAtomicObj.refreshKnowledgeSpots(stack, known);
							
							workingAtomic.setStartHour(0);
							workingAtomic.setDay("");
							atomicList.add(workingAtomic);
							atomicList = scheduleAtomicObj.refreshKnowledgeSpots(atomicList, known);
							unmarkedIndex = atomicList.size() - 1;
							
							range = workingAtomic.getKnowledgeSize();
							randomPosition = this.scheduleAtomicObj.getRandomValue(range);
							randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
							iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
							
							options = false;
						}
						else {
							range = workingAtomic.getKnowledgeSize();
							randomPosition = this.scheduleAtomicObj.getRandomValue(range);
							randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
							iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
						}
					}
				}//end of while inner
			}//end of while outer
			this.addToMarkedList(stack, "Freshman");
			ArrayList<ScheduleAtomic> attFalseList = this.autoScheduleForAttendanceFalse(attList);
			this.addToMarkedList(attFalseList, "Freshman");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sophomoreAutoScheduling() {
		try {
			SortedList sList = new SortedList(this.sophomoreUnmarkedList);
			ArrayList<ScheduleAtomic> atomicList = sList.convertToOneList();
			ArrayList<ScheduleAtomic> attList = sList.getFalseAttendanceList();
			ArrayList<ScheduleAtomic> stack = new ArrayList<ScheduleAtomic>();

			int unmarkedIndex = this.scheduleAtomicObj.getRandomValue(atomicList.size());
			ScheduleAtomic workingAtomic = atomicList.get(unmarkedIndex);
			int range = workingAtomic.getKnowledgeSize();
			int randomPosition = this.scheduleAtomicObj.getRandomValue(range);
			int randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
			Index iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
			
			boolean optDone = false;
			while(atomicList.size() != 0) {	
				if(optDone == true) {
					unmarkedIndex = this.scheduleAtomicObj.getRandomValue(atomicList.size());
					workingAtomic = atomicList.get(unmarkedIndex);
					range = workingAtomic.getKnowledgeSize();
					randomPosition = this.scheduleAtomicObj.getRandomValue(range);
					randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
					iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
				}
				
				boolean options = true;
				optDone = false;
				while((options != false) && (optDone != true)) {
					int hour = iVal.getHour();
					hour = hour - 1;
					int day = this.dayMapToIntegerHash.get(iVal.getDay());
					int limit = workingAtomic.getCredit() + hour;
					boolean blocked = false;
					boolean controlCheck = true;
					for(int h = hour; ((controlCheck != false) && (h < limit)); h++) {
						controlCheck = this.controlCourse("Sophomore", day, h);
					}
					
					for(int j = hour; ((controlCheck != false) && (j < limit)); j++) {
						controlCheck = this.controlLecturer("Sophomore", day, j, workingAtomic.getLecturerId());
					}
					for(int k = hour; ((controlCheck != false) && (k < limit)); k++) {
						controlCheck = this.controlClassroom("Sophomore", day, k, workingAtomic.getClassroomId());
					}
					
					if(controlCheck == false) { blocked = true; }
					for(int l = hour; ((controlCheck != false) && (l < limit)); l++) {
						controlCheck = this.controlClassroomInOthers("Sophomore", day, l, workingAtomic.getClassroomId());
					}
					for(int p = hour; ((controlCheck != false) && (p < limit)); p++) {
						controlCheck = this.controlLecturerInOthers("Sophomore", day, p, workingAtomic.getLecturerId());
					}
					
					if(controlCheck == true) {
						int val = randomVal;
						workingAtomic.setDay(iVal.getDay());
						workingAtomic.setStartHour(iVal.getHour());
						int hourRollBack = workingAtomic.getStartHour();
						hourRollBack = hourRollBack - 1;
						for(int p = 0; p < workingAtomic.getCredit(); p++) {
							Integer newInteger = new Integer(val); 
							atomicList = sList.forward(atomicList, newInteger);
							stack = sList.forward(stack, newInteger);
							this.controlSophomoreLecturer[hourRollBack][day] = workingAtomic.getLecturerId();
							this.controlSophomoreClassroom[hourRollBack][day] = workingAtomic.getClassroomId();
							this.controlSophomoreCourse[hourRollBack][day] = workingAtomic.getCourseId();
							val++;
							hourRollBack++;
						}
						ScheduleAtomic sItem = new ScheduleAtomic(workingAtomic);
						stack.add(sItem);
						atomicList.remove(unmarkedIndex);
						optDone = true;
					} 
					else {
						workingAtomic.removeKnowledgeByIndex(randomPosition);
						if(blocked == true) {
							workingAtomic.addBlockedSpot(randomVal, workingAtomic.getCredit());
						}
						if(workingAtomic.getKnowledge().size() == 0) {
							int index = stack.size() - 1;
							workingAtomic = new ScheduleAtomic(stack.get(index));
							
							int tempHour = workingAtomic.getStartHour();
							int tempDay = this.dayMapToIntegerHash.get(workingAtomic.getDay());
							Integer known = new Integer((tempDay * 8) + tempHour);
							atomicList = scheduleAtomicObj.rollback(atomicList, known);
							
							int hourRollBack = workingAtomic.getStartHour() - 1;
							int dayRollBack = this.dayMapToIntegerHash.get(workingAtomic.getDay()); 
							for(int r = 0; r < workingAtomic.getCredit(); r++) {
								this.controlSophomoreLecturer[hourRollBack][dayRollBack] = 0;
								this.controlSophomoreClassroom[hourRollBack][dayRollBack] = 0;
								this.controlSophomoreCourse[hourRollBack][day] = 0;
								hourRollBack++;
							}
							stack.remove(index);
							stack = scheduleAtomicObj.refreshKnowledgeSpots(stack, known);
							
							workingAtomic.setStartHour(0);
							workingAtomic.setDay("");
							atomicList.add(workingAtomic);
							atomicList = scheduleAtomicObj.refreshKnowledgeSpots(atomicList, known);
							unmarkedIndex = atomicList.size() - 1;
							
							range = workingAtomic.getKnowledgeSize();
							randomPosition = this.scheduleAtomicObj.getRandomValue(range);
							randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
							iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
							
							options = false;
						}
						else {
							range = workingAtomic.getKnowledgeSize();
							randomPosition = this.scheduleAtomicObj.getRandomValue(range);
							randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
							iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
						}
					}
				}//end of while inner
			}//end of while outer
			this.addToMarkedList(stack, "Sophomore");
			ArrayList<ScheduleAtomic> attFalseList = this.autoScheduleForAttendanceFalse(attList);
			this.addToMarkedList(attFalseList, "Sophomore");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private void juniorAutoScheduling() {
		try {
			SortedList sList = new SortedList(this.juniorUnmarkedList);
			ArrayList<ScheduleAtomic> atomicList = sList.convertToOneList();
			ArrayList<ScheduleAtomic> attList = sList.getFalseAttendanceList();
			ArrayList<ScheduleAtomic> stack = new ArrayList<ScheduleAtomic>();

			int unmarkedIndex = this.scheduleAtomicObj.getRandomValue(atomicList.size());
			ScheduleAtomic workingAtomic = atomicList.get(unmarkedIndex);
			int range = workingAtomic.getKnowledgeSize();
			int randomPosition = this.scheduleAtomicObj.getRandomValue(range);
			int randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
			Index iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
			
			boolean optDone = false;
			while(atomicList.size() != 0) {	
				if(optDone == true) {
					unmarkedIndex = this.scheduleAtomicObj.getRandomValue(atomicList.size());
					workingAtomic = atomicList.get(unmarkedIndex);
					range = workingAtomic.getKnowledgeSize();
					randomPosition = this.scheduleAtomicObj.getRandomValue(range);
					randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
					iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
				}
				
				boolean options = true;
				optDone = false;
				while((options != false) && (optDone != true)) {
					int hour = iVal.getHour();
					hour = hour - 1;
					int day = this.dayMapToIntegerHash.get(iVal.getDay());
					int limit = workingAtomic.getCredit() + hour;
					boolean blocked = false;
					boolean controlCheck = true;
					for(int h = hour; ((controlCheck != false) && (h < limit)); h++) {
						controlCheck = this.controlCourse("Junior", day, h);
					}
					
					for(int j = hour; ((controlCheck != false) && (j < limit)); j++) {
						controlCheck = this.controlLecturer("Junior", day, j, workingAtomic.getLecturerId());
					}
					for(int k = hour; ((controlCheck != false) && (k < limit)); k++) {
						controlCheck = this.controlClassroom("Junior", day, k, workingAtomic.getClassroomId());
					}
					
					if(controlCheck == false) { blocked = true; }
					for(int l = hour; ((controlCheck != false) && (l < limit)); l++) {
						controlCheck = this.controlClassroomInOthers("Junior", day, l, workingAtomic.getClassroomId());
					}
					for(int p = hour; ((controlCheck != false) && (p < limit)); p++) {
						controlCheck = this.controlLecturerInOthers("Junior", day, p, workingAtomic.getLecturerId());
					}
					
					if(controlCheck == true) {
						int val = randomVal;
						workingAtomic.setDay(iVal.getDay());
						workingAtomic.setStartHour(iVal.getHour());
						int hourRollBack = workingAtomic.getStartHour();
						hourRollBack = hourRollBack - 1;
						for(int p = 0; p < workingAtomic.getCredit(); p++) {
							Integer newInteger = new Integer(val); 
							atomicList = sList.forward(atomicList, newInteger);
							stack = sList.forward(stack, newInteger);
							this.controlJuniorLecturer[hourRollBack][day] = workingAtomic.getLecturerId();
							this.controlJuniorClassroom[hourRollBack][day] = workingAtomic.getClassroomId();
							this.controlJuniorCourse[hourRollBack][day] = workingAtomic.getCourseId();
							val++;
							hourRollBack++;
						}
						ScheduleAtomic sItem = new ScheduleAtomic(workingAtomic);
						stack.add(sItem);
						atomicList.remove(unmarkedIndex);
						optDone = true;
					} 
					else {
						workingAtomic.removeKnowledgeByIndex(randomPosition);
						if(blocked == true) {
							workingAtomic.addBlockedSpot(randomVal, workingAtomic.getCredit());
						}
						if(workingAtomic.getKnowledge().size() == 0) {
							int index = stack.size() - 1;
							workingAtomic = new ScheduleAtomic(stack.get(index));
							
							int tempHour = workingAtomic.getStartHour();
							int tempDay = this.dayMapToIntegerHash.get(workingAtomic.getDay());
							Integer known = new Integer((tempDay * 8) + tempHour);
							atomicList = scheduleAtomicObj.rollback(atomicList, known);
							
							int hourRollBack = workingAtomic.getStartHour() - 1;
							int dayRollBack = this.dayMapToIntegerHash.get(workingAtomic.getDay()); 
							for(int r = 0; r < workingAtomic.getCredit(); r++) {
								this.controlJuniorLecturer[hourRollBack][dayRollBack] = 0;
								this.controlJuniorClassroom[hourRollBack][dayRollBack] = 0;
								this.controlJuniorCourse[hourRollBack][day] = 0;
								hourRollBack++;
							}
							stack.remove(index);
							stack = scheduleAtomicObj.refreshKnowledgeSpots(stack, known);
							
							workingAtomic.setStartHour(0);
							workingAtomic.setDay("");
							atomicList.add(workingAtomic);
							atomicList = scheduleAtomicObj.refreshKnowledgeSpots(atomicList, known);
							unmarkedIndex = atomicList.size() - 1;
							
							range = workingAtomic.getKnowledgeSize();
							randomPosition = this.scheduleAtomicObj.getRandomValue(range);
							randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
							iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
							
							options = false;
						}
						else {
							range = workingAtomic.getKnowledgeSize();
							randomPosition = this.scheduleAtomicObj.getRandomValue(range);
							randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
							iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
						}
					}
				}//end of while inner
			}//end of while outer
			this.addToMarkedList(stack, "Junior");
			ArrayList<ScheduleAtomic> attFalseList = this.autoScheduleForAttendanceFalse(attList);
			this.addToMarkedList(attFalseList, "Junior");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void seniorAutoScheduling() {
		try {
			SortedList sList = new SortedList(this.seniorUnmarkedList);
			ArrayList<ScheduleAtomic> atomicList = sList.convertToOneList();
			ArrayList<ScheduleAtomic> attList = sList.getFalseAttendanceList();
			ArrayList<ScheduleAtomic> stack = new ArrayList<ScheduleAtomic>();

			int unmarkedIndex = this.scheduleAtomicObj.getRandomValue(atomicList.size());
			ScheduleAtomic workingAtomic = atomicList.get(unmarkedIndex);
			int range = workingAtomic.getKnowledgeSize();
			int randomPosition = this.scheduleAtomicObj.getRandomValue(range);
			int randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
			Index iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
			
			boolean optDone = false;
			while(atomicList.size() != 0) {	
				if(optDone == true) {
					unmarkedIndex = this.scheduleAtomicObj.getRandomValue(atomicList.size());
					workingAtomic = atomicList.get(unmarkedIndex);
					range = workingAtomic.getKnowledgeSize();
					randomPosition = this.scheduleAtomicObj.getRandomValue(range);
					randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
					iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
				}
				
				boolean options = true;
				optDone = false;
				while((options != false) && (optDone != true)) {
					int hour = iVal.getHour();
					hour = hour - 1;
					int day = this.dayMapToIntegerHash.get(iVal.getDay());
					int limit = workingAtomic.getCredit() + hour;
					boolean blocked = false;
					boolean controlCheck = true;
					for(int h = hour; ((controlCheck != false) && (h < limit)); h++) {
						controlCheck = this.controlCourse("Senior", day, h);
					}
					
					for(int j = hour; ((controlCheck != false) && (j < limit)); j++) {
						controlCheck = this.controlLecturer("Senior", day, j, workingAtomic.getLecturerId());
					}
					for(int k = hour; ((controlCheck != false) && (k < limit)); k++) {
						controlCheck = this.controlClassroom("Senior", day, k, workingAtomic.getClassroomId());
					}
					
					if(controlCheck == false) { blocked = true; }
					for(int l = hour; ((controlCheck != false) && (l < limit)); l++) {
						controlCheck = this.controlClassroomInOthers("Senior", day, l, workingAtomic.getClassroomId());
					}
					for(int p = hour; ((controlCheck != false) && (p < limit)); p++) {
						controlCheck = this.controlLecturerInOthers("Senior", day, p, workingAtomic.getLecturerId());
					}
					
					if(controlCheck == true) {
						int val = randomVal;
						workingAtomic.setDay(iVal.getDay());
						workingAtomic.setStartHour(iVal.getHour());
						int hourRollBack = workingAtomic.getStartHour();
						hourRollBack = hourRollBack - 1;
						for(int p = 0; p < workingAtomic.getCredit(); p++) {
							Integer newInteger = new Integer(val); 
							atomicList = sList.forward(atomicList, newInteger);
							stack = sList.forward(stack, newInteger);
							this.controlSeniorLecturer[hourRollBack][day] = workingAtomic.getLecturerId();
							this.controlSeniorClassroom[hourRollBack][day] = workingAtomic.getClassroomId();
							this.controlSeniorCourse[hourRollBack][day] = workingAtomic.getCourseId();
							val++;
							hourRollBack++;
						}
						ScheduleAtomic sItem = new ScheduleAtomic(workingAtomic);
						stack.add(sItem);
						atomicList.remove(unmarkedIndex);
						optDone = true;
					} 
					else {
						workingAtomic.removeKnowledgeByIndex(randomPosition);
						if(blocked == true) {
							workingAtomic.addBlockedSpot(randomVal, workingAtomic.getCredit());
						}
						if(workingAtomic.getKnowledge().size() == 0) {
							int index = stack.size() - 1;
							workingAtomic = new ScheduleAtomic(stack.get(index));
							
							int tempHour = workingAtomic.getStartHour();
							int tempDay = this.dayMapToIntegerHash.get(workingAtomic.getDay());
							Integer known = new Integer((tempDay * 8) + tempHour);
							atomicList = scheduleAtomicObj.rollback(atomicList, known);
							
							int hourRollBack = workingAtomic.getStartHour() - 1;
							int dayRollBack = this.dayMapToIntegerHash.get(workingAtomic.getDay()); 
							for(int r = 0; r < workingAtomic.getCredit(); r++) {
								this.controlSeniorLecturer[hourRollBack][dayRollBack] = 0;
								this.controlSeniorClassroom[hourRollBack][dayRollBack] = 0;
								this.controlSeniorCourse[hourRollBack][day] = 0;
								hourRollBack++;
							}
							stack.remove(index);
							stack = scheduleAtomicObj.refreshKnowledgeSpots(stack, known);
							
							workingAtomic.setStartHour(0);
							workingAtomic.setDay("");
							atomicList.add(workingAtomic);
							atomicList = scheduleAtomicObj.refreshKnowledgeSpots(atomicList, known);
							unmarkedIndex = atomicList.size() - 1;
							
							range = workingAtomic.getKnowledgeSize();
							randomPosition = this.scheduleAtomicObj.getRandomValue(range);
							randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
							iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
							
							options = false;
						}
						else {
							range = workingAtomic.getKnowledgeSize();
							randomPosition = this.scheduleAtomicObj.getRandomValue(range);
							randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
							iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
						}
					}
				}//end of while inner
			}//end of while outer
			this.addToMarkedList(stack, "Senior");
			ArrayList<ScheduleAtomic> attFalseList = this.autoScheduleForAttendanceFalse(attList);
			this.addToMarkedList(attFalseList, "Senior");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private ArrayList<ScheduleAtomic> autoScheduleForAttendanceFalse(ArrayList<ScheduleAtomic> attList) {
		int unmarkedIndex, range, randomPosition, randomVal;
		ScheduleAtomic workingAtomic = new ScheduleAtomic();
		Index iVal = new Index();
		ArrayList<ScheduleAtomic> retList = new ArrayList<ScheduleAtomic>();
		ArrayList<ScheduleAtomic> iniList = attList;		
		while(iniList.size() > 0) {
			unmarkedIndex = this.scheduleAtomicObj.getRandomValue(iniList.size());
			workingAtomic = iniList.get(unmarkedIndex);
			range = workingAtomic.getKnowledgeSize();
			randomPosition = this.scheduleAtomicObj.getRandomValue(range);
			randomVal = workingAtomic.getKnowledgeByIndex(randomPosition);
			iVal = this.scheduleAtomicObj.convertIntToIndex(randomVal);
			
			ScheduleAtomic tempAtomic = new ScheduleAtomic(workingAtomic);
			iniList.remove(unmarkedIndex);
			tempAtomic.setDay(iVal.getDay());
			tempAtomic.setStartHour(iVal.getHour());
			retList.add(tempAtomic);
		}
		return retList;
	}
	
	public String generateExcelReport()
	{
		String returnVal = "";		
		try
		{
			if((!paramYearVal.equals("Choose Year")) && (paramYearVal != null) 
					&& (!paramSemesterVal.equals("Choose Semester")) && (paramSemesterVal != null))
			{
				ExcelPOI excelObj = new ExcelPOI();
				excelObj.generateFreshmanSheet(this.convertMatrix("Freshman"));
				excelObj.generateSophomoreSheet(this.convertMatrix("Sophomore"));
				excelObj.generateJuniorSheet(this.convertMatrix("Junior"));
				excelObj.generateSeniorSheet(this.convertMatrix("Senior"));
				excelObj.writeAutoSchedulingToExcelPOI(paramYearVal, paramSemesterVal, paramVersion);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return returnVal;
	}

	private String[][] convertMatrix(String grade) {
		String[][] retMatrix = new String[9][6];
		
		retMatrix[0][0] = "Hours/Days";
		retMatrix[0][1] = "Monday";
		retMatrix[0][2] = "Tuesday";
		retMatrix[0][3] = "Wednesday";
		retMatrix[0][4] = "Thursday";
		retMatrix[0][5] = "Friday";
		
		retMatrix[1][0] = "1";
		retMatrix[2][0] = "2";
		retMatrix[3][0] = "3";
		retMatrix[4][0] = "4";
		retMatrix[5][0] = "5";
		retMatrix[6][0] = "6";
		retMatrix[7][0] = "7";
		retMatrix[8][0] = "8";
		
		for(int i = 1; i < 9; i++) {
			for(int j = 1; j < 6; j++) {
				retMatrix[i][j] = "";
			}
		}
		
		if(grade.equals("Freshman")) {
			for(int i = 0; i < this.freshmanBackupMarked.size(); i++) {
				ScheduleAtomic item = this.freshmanBackupMarked.get(i);
				int hour = item.getStartHour();
				int day = this.dayMapToIndexHash.get(item.getDay());
				for(int j = 0; j < item.getCredit(); j++) {
					if(!retMatrix[hour][day].equals("")) {
						retMatrix[hour][day] = retMatrix[hour][day] + "\n" + item.writeInfo();
					}
					else {
						retMatrix[hour][day] = item.writeInfo();
					}
					hour++;
				}
			}
			this.freshmanBackupMarked.clear();
			return retMatrix;
		}
		if(grade.equals("Sophomore")) {			
			for(int i = 0; i < this.sophomoreBackupMarked.size(); i++) {
				ScheduleAtomic item = this.sophomoreBackupMarked.get(i);
				int hour = item.getStartHour();
				int day = this.dayMapToIndexHash.get(item.getDay());
				for(int j = 0; j < item.getCredit(); j++) {
					if(!retMatrix[hour][day].equals("")) {
						retMatrix[hour][day] = retMatrix[hour][day] + "\n" + item.writeInfo();
					}
					else {
						retMatrix[hour][day] = item.writeInfo();
					}
					hour++;
				}
			}
			this.sophomoreBackupMarked.clear();
			return retMatrix;			
		}
		if(grade.equals("Junior")) {
			for(int i = 0; i < this.juniorBackupMarked.size(); i++) {
				ScheduleAtomic item = this.juniorBackupMarked.get(i);
				int hour = item.getStartHour();
				int day = this.dayMapToIndexHash.get(item.getDay());
				for(int j = 0; j < item.getCredit(); j++) {
					if(!retMatrix[hour][day].equals("")) {
						retMatrix[hour][day] = retMatrix[hour][day] + "\n" + item.writeInfo();
					}
					else {
						retMatrix[hour][day] = item.writeInfo();
					}
					hour++;
				}
			}
			this.juniorBackupMarked.clear();
			return retMatrix;
		}
		if(grade.equals("Senior")) {
			for(int i = 0; i < this.seniorBackupMarked.size(); i++) {
				ScheduleAtomic item = this.seniorBackupMarked.get(i);
				int hour = item.getStartHour();
				int day = this.dayMapToIndexHash.get(item.getDay());
				for(int j = 0; j < item.getCredit(); j++) {
					if(!retMatrix[hour][day].equals("")) {
						retMatrix[hour][day] = retMatrix[hour][day] + "\n" + item.writeInfo();
					}
					else {
						retMatrix[hour][day] = item.writeInfo();
					}
					hour++;
				}
			}
			this.seniorBackupMarked.clear();
			return retMatrix;
		}
		return retMatrix;
	}
	
	private void addToMarkedList(ArrayList<ScheduleAtomic> sList, String grade) {
		if(grade.equals("Freshman")) {
			for(int i = 0; i < sList.size(); i++) {
				ScheduleAtomic item = new ScheduleAtomic(sList.get(i));
				this.freshmanMarkedList.add(item);
			}
			return;
		}
		if(grade.equals("Sophomore")) {
			for(int i = 0; i < sList.size(); i++) {
				ScheduleAtomic item = new ScheduleAtomic(sList.get(i));
				this.sophomoreMarkedList.add(item);
			}
			return;
		}
		if(grade.equals("Junior")) {
			for(int i = 0; i < sList.size(); i++) {
				ScheduleAtomic item = new ScheduleAtomic(sList.get(i));
				this.juniorMarkedList.add(item);
			}
			return;
		}
		if(grade.equals("Senior")) {
			for(int i = 0; i < sList.size(); i++) {
				ScheduleAtomic item = new ScheduleAtomic(sList.get(i));
				this.seniorMarkedList.add(item);
			}
			return;
		}
	}
	
//************************* GETTER-SETTER METHODS ***************************************	
//***1***********************************************************************************
// Onur (Finished 29.04)
// This getter method returns the list of years(YEARLIST) which are contained in syllabus 
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
// Set method of 'YEARLIST' subfield	
	public void setYearList(ArrayList<SelectItem> yearList) {
		this.yearList = yearList;
	}
//***2************************************************************************************
	public ArrayList<SelectItem> getSemesterList() {
		return semesterList;
	}
	
	public String getParamVersion() {
		return paramVersion;
	}
	public void setParamVersion(String paramVersion) {
		this.paramVersion = paramVersion;
	}
	public String getParamYearVal() {
		return paramYearVal;
	}
	public void setParamYearVal(String paramYearVal) {
		this.paramYearVal = paramYearVal;
	}
	public String getParamSemesterVal() {
		return paramSemesterVal;
	}
	public void setParamSemesterVal(String paramSemesterVal) {
		this.paramSemesterVal = paramSemesterVal;
	}
	public void setSemesterList(ArrayList<SelectItem> semesterList) {
		this.semesterList = semesterList;
	}
//***3************************************************************************************	
	public String getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(String selectedYear) {
		this.selectedYear = selectedYear;
	}
//***4************************************************************************************
	public String getSelectedSemester() {
		return selectedSemester;
	}

	public void setSelectedSemester(String selectedSemester) {
		this.selectedSemester = selectedSemester;
	}
//***************************************************************************************
//***5********************* DEAN GET SET *************************************************
	public ArrayList<SelectItem> getDeanCourseList() {
		return this.deanCourseList;
	}

	public void setDeanCourseList(ArrayList<SelectItem> deanCourseList) {
		this.deanCourseList = deanCourseList;
	}
//***6******************************************************************************
	public ArrayList<SelectItem> getDeanLecturerList() {
		return deanLecturerList;
	}

	public void setDeanLecturerList(ArrayList<SelectItem> deanLecturerList) {
		this.deanLecturerList = deanLecturerList;
	}
//************************************************************************************	
	public String getCreditValueTheo() {
		return creditValueTheo;
	}

	public void setCreditValueTheo(String creditValueTheo) {
		this.creditValueTheo = creditValueTheo;
	}
//************************************************************************************
	public String getCreditValuePrac() {
		return creditValuePrac;
	}

	public void setCreditValuePrac(String creditValuePrac) {
		this.creditValuePrac = creditValuePrac;
	}
//************************************************************************************
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

	public String getSelectedDeanDay() {
		return selectedDeanDay;
	}

	public void setSelectedDeanDay(String selectedDeanDay) {
		this.selectedDeanDay = selectedDeanDay;
	}

	public String getSelectedDeanOperation() {
		return selectedDeanOperation;
	}
	public void setSelectedDeanOperation(String selectedDeanOperation) {
		this.selectedDeanOperation = selectedDeanOperation;
	}
	public String getSelectedDeanStartHour() {
		return selectedDeanStartHour;
	}
	public void setSelectedDeanStartHour(String selectedDeanStartHour) {
		this.selectedDeanStartHour = selectedDeanStartHour;
	}
	public Color getTestColor() {
		return testColor;
	}

	public void setTestColor(Color testColor) {
		this.testColor = testColor;
	}
//**************************** FRESHMAN GET-SET *************************************
	public ArrayList<SelectItem> getFreshmanCourses() {
		return freshmanCourses;
	}

	public void setFreshmanCourses(ArrayList<SelectItem> freshmanCourses) {
		this.freshmanCourses = freshmanCourses;
	}
	
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

	public String getSelectedFreshmanCredit() {
		return selectedFreshmanCredit;
	}

	public void setSelectedFreshmanCredit(String selectedFreshmanCredit) {
		this.selectedFreshmanCredit = selectedFreshmanCredit;
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

	public String getSelectedFreshmanSplitCourse() {
		return selectedFreshmanSplitCourse;
	}

	public void setSelectedFreshmanSplitCourse(String selectedFreshmanSplitCourse) {
		this.selectedFreshmanSplitCourse = selectedFreshmanSplitCourse;
	}

	public String getSelectedFreshmanSplitLecturer() 
	{
		return selectedFreshmanSplitLecturer;
	}

	public void setSelectedFreshmanSplitLecturer(
			String selectedFreshmanSplitLecturer) {
		this.selectedFreshmanSplitLecturer = selectedFreshmanSplitLecturer;
	}
	
	public String[][] getInitFreshmanCourseTable() {
		return initFreshmanCourseTable;
	}
	
	public void setInitFreshmanCourseTable(String[][] initFreshmanCourseTable) {
		this.initFreshmanCourseTable = initFreshmanCourseTable;
	}	
//********************** SOPHOMORE GET_SET METHODSSS ***********************************	
	public ArrayList<SelectItem> getSophomoreCourses() {
		return sophomoreCourses;
	}

	public void setSophomoreCourses(ArrayList<SelectItem> sophomoreCourses) {
		this.sophomoreCourses = sophomoreCourses;
	}
	
	public String[][] getInitSophomoreCourseTable() {
		return initSophomoreCourseTable;
	}
	
	public void setInitSophomoreCourseTable(String[][] initSophomoreCourseTable) {
		this.initSophomoreCourseTable = initSophomoreCourseTable;
	}
	
	public String getSelectedSophomoreCourse() {
		return selectedSophomoreCourse;
	}
	
	public void setSelectedSophomoreCourse(String selectedSophomoreCourse) {
		this.selectedSophomoreCourse = selectedSophomoreCourse;
	}
	
	public String getSelectedSophomoreSplitCourse() {
		return selectedSophomoreSplitCourse;
	}
	
	public void setSelectedSophomoreSplitCourse(String selectedSophomoreSplitCourse) {
		this.selectedSophomoreSplitCourse = selectedSophomoreSplitCourse;
	}
	
	public String getSelectedSophomoreSplitLecturer() {
		return selectedSophomoreSplitLecturer;
	}
	
	public void setSelectedSophomoreSplitLecturer(
			String selectedSophomoreSplitLecturer) {
		this.selectedSophomoreSplitLecturer = selectedSophomoreSplitLecturer;
	}
	
	public String getSelectedSophomoreLecturer() {
		return selectedSophomoreLecturer;
	}
	
	public void setSelectedSophomoreLecturer(String selectedSophomoreLecturer) {
		this.selectedSophomoreLecturer = selectedSophomoreLecturer;
	}
	
	public String getSelectedSophomoreDay() {
		return selectedSophomoreDay;
	}
	
	public void setSelectedSophomoreDay(String selectedSophomoreDay) {
		this.selectedSophomoreDay = selectedSophomoreDay;
	}
	
	public String getSelectedSophomoreOperation() {
		return selectedSophomoreOperation;
	}
	
	public void setSelectedSophomoreOperation(String selectedSophomoreOperation) {
		this.selectedSophomoreOperation = selectedSophomoreOperation;
	}
	
	public String getSelectedSophomoreStartHour() {
		return selectedSophomoreStartHour;
	}
	
	public void setSelectedSophomoreStartHour(String selectedSophomoreStartHour) {
		this.selectedSophomoreStartHour = selectedSophomoreStartHour;
	}
	
	public String getSelectedSophomoreCredit() {
		return selectedSophomoreCredit;
	}
	
	public void setSelectedSophomoreCredit(String selectedSophomoreCredit) {
		this.selectedSophomoreCredit = selectedSophomoreCredit;
	}
	
	public String getSophomoreCreditValeuTeo() {
		return sophomoreCreditValeuTeo;
	}
	
	public void setSophomoreCreditValeuTeo(String sophomoreCreditValeuTeo) {
		this.sophomoreCreditValeuTeo = sophomoreCreditValeuTeo;
	}
	
	public String getSophomoreCreditValuePrac() {
		return sophomoreCreditValuePrac;
	}
	
	public void setSophomoreCreditValuePrac(String sophomoreCreditValuePrac) {
		this.sophomoreCreditValuePrac = sophomoreCreditValuePrac;
	}
//******************** JUNIOR GET SET METHODS ******************************************
	public ArrayList<SelectItem> getJuniorCourses() {
		return juniorCourses;
	}

	public void setJuniorCourses(ArrayList<SelectItem> juniorCourses) {
		this.juniorCourses = juniorCourses;
	}
	
	public String[][] getInitJuniorCourseTable() {
		return initJuniorCourseTable;
	}
	
	public void setInitJuniorCourseTable(String[][] initJuniorCourseTable) {
		this.initJuniorCourseTable = initJuniorCourseTable;
	}
	
	public String getSelectedJuniorCourse() {
		return selectedJuniorCourse;
	}
	
	public void setSelectedJuniorCourse(String selectedJuniorCourse) {
		this.selectedJuniorCourse = selectedJuniorCourse;
	}
	
	public String getSelectedJuniorSplitCourse() {
		return selectedJuniorSplitCourse;
	}
	
	public void setSelectedJuniorSplitCourse(String selectedJuniorSplitCourse) {
		this.selectedJuniorSplitCourse = selectedJuniorSplitCourse;
	}
	
	public String getSelectedJuniorSplitLecturer() {
		return selectedJuniorSplitLecturer;
	}
	
	public void setSelectedJuniorSplitLecturer(String selectedJuniorSplitLecturer) {
		this.selectedJuniorSplitLecturer = selectedJuniorSplitLecturer;
	}
	
	public String getSelectedJuniorLecturer() {
		return selectedJuniorLecturer;
	}
	
	public void setSelectedJuniorLecturer(String selectedJuniorLecturer) {
		this.selectedJuniorLecturer = selectedJuniorLecturer;
	}
	
	public String getSelectedJuniorDay() {
		return selectedJuniorDay;
	}
	
	public void setSelectedJuniorDay(String selectedJuniorDay) {
		this.selectedJuniorDay = selectedJuniorDay;
	}
	
	public String getSelectedJuniorOperation() {
		return selectedJuniorOperation;
	}
	
	public void setSelectedJuniorOperation(String selectedJuniorOperation) {
		this.selectedJuniorOperation = selectedJuniorOperation;
	}
	
	public String getSelectedJuniorStartHour() {
		return selectedJuniorStartHour;
	}
	
	public void setSelectedJuniorStartHour(String selectedJuniorStartHour) {
		this.selectedJuniorStartHour = selectedJuniorStartHour;
	}
	
	public String getSelectedJuniorCredit() {
		return selectedJuniorCredit;
	}
	
	public void setSelectedJuniorCredit(String selectedJuniorCredit) {
		this.selectedJuniorCredit = selectedJuniorCredit;
	}
	
	public String getJuniorCreditValueTeo() {
		return juniorCreditValueTeo;
	}
	
	public void setJuniorCreditValueTeo(String juniorCreditValueTeo) {
		this.juniorCreditValueTeo = juniorCreditValueTeo;
	}
	
	public String getJuniorCreditValuePrac() {
		return juniorCreditValuePrac;
	}
	
	public void setJuniorCreditValuePrac(String juniorCreditValuePrac) {
		this.juniorCreditValuePrac = juniorCreditValuePrac;
	}
	
//******************* SENIOR GET-SET METHODS *****************************************
	public ArrayList<SelectItem> getSeniorCourses() {
		return seniorCourses;
	}

	public void setSeniorCourses(ArrayList<SelectItem> seniorCourses) {
		this.seniorCourses = seniorCourses;
	}
	
	public String[][] getInitSeniorCourseTable() {
		return initSeniorCourseTable;
	}
	
	public void setInitSeniorCourseTable(String[][] initSeniorCourseTable) {
		this.initSeniorCourseTable = initSeniorCourseTable;
	}
	
	public String getSelectedSeniorCourse() {
		return selectedSeniorCourse;
	}
	
	public void setSelectedSeniorCourse(String selectedSeniorCourse) {
		this.selectedSeniorCourse = selectedSeniorCourse;
	}
	
	public String getSelectedSeniorSplitCourse() {
		return selectedSeniorSplitCourse;
	}
	
	public void setSelectedSeniorSplitCourse(String selectedSeniorSplitCourse) {
		this.selectedSeniorSplitCourse = selectedSeniorSplitCourse;
	}
	
	public String getSelectedSeniorSplitLecturer() {
		return selectedSeniorSplitLecturer;
	}
	
	public void setSelectedSeniorSplitLecturer(String selectedSeniorSplitLecturer) {
		this.selectedSeniorSplitLecturer = selectedSeniorSplitLecturer;
	}
	
	public String getSelectedSeniorLecturer() {
		return selectedSeniorLecturer;
	}
	
	public void setSelectedSeniorLecturer(String selectedSeniorLecturer) {
		this.selectedSeniorLecturer = selectedSeniorLecturer;
	}
	
	public String getSelectedSeniorDay() {
		return selectedSeniorDay;
	}
	
	public void setSelectedSeniorDay(String selectedSeniorDay) {
		this.selectedSeniorDay = selectedSeniorDay;
	}
	
	public String getSelectedSeniorOperation() {
		return selectedSeniorOperation;
	}
	
	public void setSelectedSeniorOperation(String selectedSeniorOperation) {
		this.selectedSeniorOperation = selectedSeniorOperation;
	}
	
	public String getSelectedSeniorStartHour() {
		return selectedSeniorStartHour;
	}
	
	public void setSelectedSeniorStartHour(String selectedSeniorStartHour) {
		this.selectedSeniorStartHour = selectedSeniorStartHour;
	}
	
	public String getSelectedSeniorCredit() {
		return selectedSeniorCredit;
	}
	
	public void setSelectedSeniorCredit(String selectedSeniorCredit) {
		this.selectedSeniorCredit = selectedSeniorCredit;
	}
	
	public String getSeniorCreditValueTeo() {
		return seniorCreditValueTeo;
	}
	
	public void setSeniorCreditValueTeo(String seniorCreditValueTeo) {
		this.seniorCreditValueTeo = seniorCreditValueTeo;
	}
	
	public String getSeniorCreditValuePrac() {
		return seniorCreditValuePrac;
	}
	
	public void setSeniorCreditValuePrac(String seniorCreditValuePrac) {
		this.seniorCreditValuePrac = seniorCreditValuePrac;
	}
//**************************************************************************************	 
//*********** GETTER-SETTER METHODS OF SUBFIELDS RELATED WITH SCHEDULE *****************	
	public ArrayList<ScheduleAtomic> getFreshmanSchedules() {
		return freshmanSchedules;
	}
	
	public void setFreshmanSchedules(ArrayList<ScheduleAtomic> freshmanSchedules) {
		this.freshmanSchedules = freshmanSchedules;
	}
	
	public ArrayList<ScheduleAtomic> getSophomoreSchedules() {
		return sophomoreSchedules;
	}
	
	public void setSophomoreSchedules(ArrayList<ScheduleAtomic> sophomoreSchedules) {
		this.sophomoreSchedules = sophomoreSchedules;
	}
	
	public ArrayList<ScheduleAtomic> getJuniorSchedules() {
		return juniorSchedules;
	}
	
	public void setJuniorSchedules(ArrayList<ScheduleAtomic> juniorSchedules) {
		this.juniorSchedules = juniorSchedules;
	}
	
	public ArrayList<ScheduleAtomic> getSeniorSchedules() {
		return seniorSchedules;
	}
	
	public void setSeniorSchedules(ArrayList<ScheduleAtomic> seniorSchedules) {
		this.seniorSchedules = seniorSchedules;
	}
	
	public ArrayList<ScheduleAtomic> getDeanSchedules() {
		return deanSchedules;
	}
	
	public void setDeanSchedules(ArrayList<ScheduleAtomic> deanSchedules) {
		this.deanSchedules = deanSchedules;
	}
//************************************************************************************	
	public ArrayList<SelectItem> getFreshmanOperations() {
		return freshmanOperations;
	}
	public void setFreshmanOperations(ArrayList<SelectItem> freshmanOperations) {
		this.freshmanOperations = freshmanOperations;
	}
	public ArrayList<SelectItem> getSophomoreOperations() {
		return sophomoreOperations;
	}
	public void setSophomoreOperations(ArrayList<SelectItem> sophomoreOperations) {
		this.sophomoreOperations = sophomoreOperations;
	}
	public ArrayList<SelectItem> getJuniorOperations() {
		return juniorOperations;
	}
	public void setJuniorOperations(ArrayList<SelectItem> juniorOperations) {
		this.juniorOperations = juniorOperations;
	}
	public ArrayList<SelectItem> getSeniorOperations() {
		return seniorOperations;
	}
	public void setSeniorOperations(ArrayList<SelectItem> seniorOperations) {
		this.seniorOperations = seniorOperations;
	}
//************************************************************************************	
	public ArrayList<SelectItem> getFreshmanDays() {
		return freshmanDays;
	}
	public void setFreshmanDays(ArrayList<SelectItem> freshmanDays) {
		this.freshmanDays = freshmanDays;
	}
	public ArrayList<SelectItem> getSophomoreDays() {
		return sophomoreDays;
	}
	public void setSophomoreDays(ArrayList<SelectItem> sophomoreDays) {
		this.sophomoreDays = sophomoreDays;
	}
	public ArrayList<SelectItem> getJuniorDays() {
		return juniorDays;
	}
	public void setJuniorDays(ArrayList<SelectItem> juniorDays) {
		this.juniorDays = juniorDays;
	}
	public ArrayList<SelectItem> getSeniorDays() {
		return seniorDays;
	}
	public void setSeniorDays(ArrayList<SelectItem> seniorDays) {
		this.seniorDays = seniorDays;
	}
//***************************************************************************************
	public ArrayList<SelectItem> getFreshmanHours() {
		return freshmanHours;
	}
	public void setFreshmanHours(ArrayList<SelectItem> freshmanHours) {
		this.freshmanHours = freshmanHours;
	}
	public ArrayList<SelectItem> getSophomoreHours() {
		return sophomoreHours;
	}
	public void setSophomoreHours(ArrayList<SelectItem> sophomoreHours) {
		this.sophomoreHours = sophomoreHours;
	}
	public ArrayList<SelectItem> getJuniorHours() {
		return juniorHours;
	}
	public void setJuniorHours(ArrayList<SelectItem> juniorHours) {
		this.juniorHours = juniorHours;
	}
	public ArrayList<SelectItem> getSeniorHours() {
		return seniorHours;
	}
	public void setSeniorHours(ArrayList<SelectItem> seniorHours) {
		this.seniorHours = seniorHours;
	}
//*************************************************************************************
	public ArrayList<SelectItem> getFreshmanCredits() {
		return freshmanCredits;
	}
	public void setFreshmanCredits(ArrayList<SelectItem> freshmanCredits) {
		this.freshmanCredits = freshmanCredits;
	}
	public ArrayList<SelectItem> getSophomoreCredits() {
		return sophomoreCredits;
	}
	public void setSophomoreCredits(ArrayList<SelectItem> sophomoreCredits) {
		this.sophomoreCredits = sophomoreCredits;
	}
	public ArrayList<SelectItem> getJuniorCredits() {
		return juniorCredits;
	}
	public void setJuniorCredits(ArrayList<SelectItem> juniorCredits) {
		this.juniorCredits = juniorCredits;
	}
	public ArrayList<SelectItem> getSeniorCredits() {
		return seniorCredits;
	}
	public void setSeniorCredits(ArrayList<SelectItem> seniorCredits) {
		this.seniorCredits = seniorCredits;
	}
//**********************************************************************************************	
	public String getFreshmanLockedDay() {
		return freshmanLockedDay;
	}
	public void setFreshmanLockedDay(String freshmanLockedDay) {
		this.freshmanLockedDay = freshmanLockedDay;
	}
	public String getFreshmanLockedHour() {
		return freshmanLockedHour;
	}
	public void setFreshmanLockedHour(String freshmanLockedHour) {
		this.freshmanLockedHour = freshmanLockedHour;
	}
//**********************************************************************************************
	public String getSophomoreLockedDay() {
		return sophomoreLockedDay;
	}
	public void setSophomoreLockedDay(String sophomoreLockedDay) {
		this.sophomoreLockedDay = sophomoreLockedDay;
	}
	public String getSophomoreLockedHour() {
		return sophomoreLockedHour;
	}
	public void setSophomoreLockedHour(String sophomoreLockedHour) {
		this.sophomoreLockedHour = sophomoreLockedHour;
	}
//**********************************************************************************************
	public String getJuniorLockedDay() {
		return juniorLockedDay;
	}
	public void setJuniorLockedDay(String juniorLockedDay) {
		this.juniorLockedDay = juniorLockedDay;
	}
	public String getJuniorLockedHour() {
		return juniorLockedHour;
	}
	public void setJuniorLockedHour(String juniorLockedHour) {
		this.juniorLockedHour = juniorLockedHour;
	}
//**********************************************************************************************
	public String getSeniorLockedDay() {
		return seniorLockedDay;
	}
	public void setSeniorLockedDay(String seniorLockedDay) {
		this.seniorLockedDay = seniorLockedDay;
	}
	public String getSeniorLockedHour() {
		return seniorLockedHour;
	}
	public void setSeniorLockedHour(String seniorLockedHour) {
		this.seniorLockedHour = seniorLockedHour;
	}
//**********************************************************************************************
	private void testSortedList() {
		SortedList item = new SortedList(this.freshmanUnmarkedList);
		System.out.println("\n\n*** 4 Credit Atomics ***");
		ArrayList<ScheduleAtomic> lst = item.getFourthCreditList();
		for(int i = 0; i < lst.size(); i++) {
			System.out.print(lst.get(i).toString());
		}
		System.out.println("*** 3 Credit Atomics ***");
		lst = item.getThirdCreditList();
		for(int i = 0; i < lst.size(); i++) {
			System.out.print(lst.get(i).toString());
		}
		System.out.println("*** 2 Credit Atomics ***");
		lst = item.getSecondCreditList();
		for(int i = 0; i < lst.size(); i++) {
			System.out.print(lst.get(i).toString());
		}
		System.out.println("*** 1 Credit Atomics ***");
		lst = item.getFirstCreditList();
		for(int i = 0; i < lst.size(); i++) {
			System.out.print(lst.get(i).toString());
		}
		System.out.println("*** Not Attendence Atomics ***");
		lst = item.getFalseAttendanceList();
		for(int i = 0; i < lst.size(); i++) {
			System.out.print(lst.get(i).toString());
		}
	}

	private void testRandomMethod() {
		ScheduleAtomic sItem = new ScheduleAtomic();
		sItem.setCredit(2);
		
		int num = sItem.getRandomValue(sItem.getKnowledge().size());
		System.out.println("\n Random generates: " + num + "\n");
		Index item = sItem.convertIntToIndex(num);
		System.out.println(item.toString());
	}
	
	private void testMergeMethod() {
		System.out.println(this.selectedScheduleAtomicFreshman.toString());
		System.out.println(this.freshmanUnmarkedList.get(this.atomicIndexFreshman).toString());
	}
}
