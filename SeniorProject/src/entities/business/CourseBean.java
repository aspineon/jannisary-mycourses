package entities.business;
import entities.dao.Course;

public class CourseBean {
	
	public void testFunc(){
		this._courseName = "human";
	}
	
	private String _courseName;
	private int _teoricLectureHours;
	private int _practiceLectureHours;
	private String _courseDescription;
	private String _typeOfCourse;
	private int _attendance;
	private Course course = new Course();
	
	public String AddCourse(){
		course.setCourseName(_courseName);
		course.setAttendance(0);
		course.setPracticeLectureHourse(0);
		course.setTeoricLectureHours(0);
		course.setTypeofCourse("test");
		course.setCourseDescription("test");
		course.AddCourse();
		return null;
	}
	
	public String get_courseName() {
		return _courseName;
	}
	public void set_courseName(String _courseName) {
		this._courseName = _courseName;
	}
	public int get_teoricLectureHours() {
		return _teoricLectureHours;
	}
	public void set_teoricLectureHours(int _teoricLectureHours) {
		this._teoricLectureHours = _teoricLectureHours;
	}
	public int get_practiceLectureHours() {
		return _practiceLectureHours;
	}
	public void set_practiceLectureHours(int _practiceLectureHours) {
		this._practiceLectureHours = _practiceLectureHours;
	}
	public String get_courseDescription() {
		return _courseDescription;
	}
	public void set_courseDescription(String _courseDescription) {
		this._courseDescription = _courseDescription;
	}
	public String get_typeOfCourse() {
		return _typeOfCourse;
	}
	public void set_typeOfCourse(String _typeOfCourse) {
		this._typeOfCourse = _typeOfCourse;
	}
	public int get_attendance() {
		return _attendance;
	}
	public void set_attendance(int _attendance) {
		this._attendance = _attendance;
	}
	
	
}
