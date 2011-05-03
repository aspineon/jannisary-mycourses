package entities.utility;

import entities.dao.Syllabus;

public class ScheduleAtomic {
	private Syllabus syllabus;
	private String courseType;
	private String day;
	private int startHour;
	private int credit;
	
	public ScheduleAtomic() {
		
	}
	
	public ScheduleAtomic(Syllabus pSyllabus, String pCourseType, String pDay, int pStartHour, int pCredit) {
		this.syllabus = pSyllabus;
		this.courseType = pCourseType;
		this.day = pDay;
		this.startHour = pStartHour;
		this.credit = pCredit;
		
	}
//*************** GETTER-SETTER METHODS *************************************************
	public Syllabus getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(Syllabus syllabus) {
		this.syllabus = syllabus;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
}
