package entities.util;

import entities.dao.Syllabus;

public class BasicScheduleUtilBean {
	private String courseName;
	private int teoricHours;
	private int practiceHours;
	private String lecturerName;
	private int classroomId;
	private int syllabusId;
	private String courseType;
	private String courseTheoricOrPraticName;
	private int timeofCourse;
	private int hours;
	
	public BasicScheduleUtilBean(){
		
	}
	
	public BasicScheduleUtilBean(BasicScheduleUtilBean bs){
		this.courseName = bs.getCourseName();
		this.teoricHours = bs.getTeoricHours();
		this.practiceHours = bs.getPracticeHours();
		this.lecturerName = bs.getLecturerName();
		this.classroomId = bs.getClassroomId();
		this.syllabusId = bs.getSyllabusId();
		this.courseType = bs.getCourseType();
		this.courseTheoricOrPraticName = bs.getCourseTheoricOrPraticName();
		this.timeofCourse = bs.getTimeofCourse();
		this.hours = bs.getHours();
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getTeoricHours() {
		return teoricHours;
	}
	public void setTeoricHours(int teoricHours) {
		this.teoricHours = teoricHours;
	}
	public int getPracticeHours() {
		return practiceHours;
	}
	public void setPracticeHours(int practiceHours) {
		this.practiceHours = practiceHours;
	}
	public String getLecturerName() {
		return lecturerName;
	}
	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}
	
	public int getClassroomId() {
		return classroomId;
	}
	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}
	public int getSyllabusId() {
		return syllabusId;
	}
	public void setSyllabusId(int syllabusId) {
		this.syllabusId = syllabusId;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCourseTheoricOrPraticName() {
		return courseTheoricOrPraticName;
	}
	public void setCourseTheoricOrPraticName(String courseTheoricOrPraticName) {
		this.courseTheoricOrPraticName = courseTheoricOrPraticName;
	}
	public int getTimeofCourse() {
		return timeofCourse;
	}
	public void setTimeofCourse(int timeofCourse) {
		this.timeofCourse = timeofCourse;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
}
