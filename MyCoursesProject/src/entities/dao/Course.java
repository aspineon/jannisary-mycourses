package entities.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Generated Mar 31, 2011 9:47:33 PM by Hibernate Tools 3.4.0.CR1

/**
 * Course generated by hbm2java
 */
public class Course implements java.io.Serializable {

	private Integer courseId;
	private String courseCode;
	private String courseName;
	private int teoricLectureHours;
	private int practiceLectureHourse;
	private boolean attendance;
	private int typeofCourseId;
	private String courseDescription;

	public Course() {
		
	}

	public Course(String courseCode, String courseName, int teoricLectureHours,
			int practiceLectureHourse, boolean attendance, int typeofCourseId) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.teoricLectureHours = teoricLectureHours;
		this.practiceLectureHourse = practiceLectureHourse;
		this.attendance = attendance;
		this.typeofCourseId = typeofCourseId;
	}

	public Course(String courseCode, String courseName, int teoricLectureHours,
			int practiceLectureHourse, boolean attendance, int typeofCourseId,
			String courseDescription) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.teoricLectureHours = teoricLectureHours;
		this.practiceLectureHourse = practiceLectureHourse;
		this.attendance = attendance;
		this.typeofCourseId = typeofCourseId;
		this.courseDescription = courseDescription;
	}

	@SuppressWarnings("unchecked")
	public List<String> getCourseCodeById(){
		List<String> courseCodeList = null;
		
        Session session = null;
        
        try {
                SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                session = sessionFactory.openSession();
                
                Query query = session.getNamedQuery("getCourseCodeById");
                query.setParameter("pCourse_Id", this.courseId);
                courseCodeList =(List<String>) query.list();
                
        } catch (Exception e) {
                // TODO: handle exception
                e.getMessage();
        } 
        return courseCodeList;
	}
	
	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseCode() {
		return this.courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getTeoricLectureHours() {
		return this.teoricLectureHours;
	}

	public void setTeoricLectureHours(int teoricLectureHours) {
		this.teoricLectureHours = teoricLectureHours;
	}

	public int getPracticeLectureHourse() {
		return this.practiceLectureHourse;
	}

	public void setPracticeLectureHourse(int practiceLectureHourse) {
		this.practiceLectureHourse = practiceLectureHourse;
	}

	public boolean isAttendance() {
		return this.attendance;
	}

	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}

	public int getTypeofCourseId() {
		return this.typeofCourseId;
	}

	public void setTypeofCourseId(int typeofCourseId) {
		this.typeofCourseId = typeofCourseId;
	}

	public String getCourseDescription() {
		return this.courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public void AddCourse(){
		Session session=null;
		try{
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			//System.out.println("Lecturer: AddLecturer1");
			Query query = session.getNamedQuery("AddCourse");
			//System.out.println("Lecturer: AddLecturer2");
			query.setParameter("p_Course_Code", courseCode);
			query.setParameter("p_Course_Name", "human");
			query.setParameter("p_Teoric_Lecture_Hours", 2);
			query.setParameter("p_Practice_Lecture_Hours", 2);
			query.setParameter("p_Attendance", 2);
			query.setParameter("p_Typeof_Course_Id", 2);
			query.setParameter("p_Course_Description", "bos bi ders");
			query.executeUpdate();
			
			//System.out.println("Lecturer: AddLecturer3");
			
		}catch(Exception e){
			System.err.print(e.getMessage());
		}
	}
}
