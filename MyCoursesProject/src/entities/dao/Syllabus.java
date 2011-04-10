package entities.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Generated Mar 31, 2011 9:47:33 PM by Hibernate Tools 3.4.0.CR1

/**
 * Syllabus generated by hbm2java
 */
@SuppressWarnings("serial")
public class Syllabus implements java.io.Serializable {

	private Integer syllabusId;
	private String semester;
	private int year;
	private int courseId;
	private int lecturerId;
	private Integer sectionNo;

	public Syllabus() {
	}

	public Syllabus(String semester, int year, int courseId, int lecturerId) {
		this.semester = semester;
		this.year = year;
		this.courseId = courseId;
		this.lecturerId = lecturerId;
	}

	public Syllabus(String semester, int year, int courseId, int lecturerId,
			Integer sectionNo) {
		this.semester = semester;
		this.year = year;
		this.courseId = courseId;
		this.lecturerId = lecturerId;
		this.sectionNo = sectionNo;
	}

	@SuppressWarnings("unchecked")
	public List<Syllabus> getAllSyllabus(){
		List<Syllabus> allSyllabus = null;
		
         Session session = null;
         
         try {
                 SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                 session = sessionFactory.openSession();
                 
                 Query query = session.getNamedQuery("getAllSyllabus");
                 allSyllabus =(List<Syllabus>) query.list();
                 
         } catch (Exception e) {
                 // TODO: handle exception
                 e.getMessage();
         } 
         return allSyllabus;
	}
	
	public Integer getSyllabusId() {
		return this.syllabusId;
	}

	public void setSyllabusId(Integer syllabusId) {
		this.syllabusId = syllabusId;
	}

	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getCourseId() {
		return this.courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getLecturerId() {
		return this.lecturerId;
	}

	public void setLecturerId(int lecturerId) {
		this.lecturerId = lecturerId;
	}

	public Integer getSectionNo() {
		return this.sectionNo;
	}

	public void setSectionNo(Integer sectionNo) {
		this.sectionNo = sectionNo;
	}

}
