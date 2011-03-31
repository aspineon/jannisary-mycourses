package entities.business;
import entities.dao.Course;;

public class CourseBean {
	
	public String AddCourse(){
		Course course = new Course();
		course.setCourseCode(courseCode);
		course.AddCourse();
		return null;
	}
	
	private Course course;
	private Integer courseId;
	private String courseCode;
	private String courseName;
	private int teoricLectureHours;
	private int practiceLectureHourse;
	private boolean attendance;
	private int typeofCourseId;
	private String courseDescription;
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getTeoricLectureHours() {
		return teoricLectureHours;
	}
	public void setTeoricLectureHours(int teoricLectureHours) {
		this.teoricLectureHours = teoricLectureHours;
	}
	public int getPracticeLectureHourse() {
		return practiceLectureHourse;
	}
	public void setPracticeLectureHourse(int practiceLectureHourse) {
		this.practiceLectureHourse = practiceLectureHourse;
	}
	public boolean isAttendance() {
		return attendance;
	}
	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}
	public int getTypeofCourseId() {
		return typeofCourseId;
	}
	public void setTypeofCourseId(int typeofCourseId) {
		this.typeofCourseId = typeofCourseId;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

}
