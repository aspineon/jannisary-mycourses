package entities.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.validator.Length;
import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;

// Generated Apr 11, 2011 11:14:09 PM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Syllabus generated by hbm2java
 */
public class Syllabus implements java.io.Serializable {

	private static final long serialVersionUID = 282648810302091391L;

	private Integer syllabusId;
	private Lecturer lecturer;
	private Course course;
	
	@NotEmpty
    @Pattern(regex=".*[^\\s].*", message="This string contain only spaces")
    @Length(min=3,max=12)
	private String semester;
	
	@NotEmpty
    @Pattern(regex="^[-+]?[0-9]*\\.?[0-9]+$", message="Input must be numeric!")
    @Length(min=4,max=4)
	private int year;
	
	
	@NotNull
    @Min(0)
    @Max(8)
    @Pattern(regex="^[-+]?[0-9]*\\.?[0-9]+$", message="Input must be numeric!")
	private Integer sectionNo;
	
	private Classroom classroom;
	
	
	private Set schedules = new HashSet(0);

	public Syllabus() {
	}

	public Syllabus(Lecturer lecturer, Course course, String semester, int year) {
		this.lecturer = lecturer;
		this.course = course;
		this.semester = semester;
		this.year = year;
	}

	public Syllabus(Lecturer lecturer, Course course, String semester, int year, Integer sectionNo, Classroom classroom, Set schedules) {
		this.lecturer = lecturer;
		this.course = course;
		this.semester = semester;
		this.year = year;
		this.sectionNo = sectionNo;
		this.classroom = classroom;
		this.schedules = schedules;
	}
	
	/*Copy constructor Added by Erhun 16.04.2011*/
	public Syllabus(Syllabus syllabus){
		this.syllabusId = syllabus.getSyllabusId();
		this.semester = syllabus.getSemester();
		this.year = syllabus.getYear();
		this.course = syllabus.getCourse();
		this.lecturer = syllabus.getLecturer();
		this.sectionNo = syllabus.getSectionNo();
		this.schedules = syllabus.getSchedules();
		this.classroom = syllabus.getClassroom();
	}
	
	public void deleteSyllabus(){
		
		Session session=null;
		try{
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.getNamedQuery("DeleteSyllabus");
			query.setParameter("pSyllabusId", syllabusId);
			query.executeUpdate();
			tx.commit();
		}catch(Exception e){
			System.err.print(e.getMessage());
		}
		finally{
			session.close();
		}
	}
	
public void updateSyllabus(){
		
		Session session=null;
		try{
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.getNamedQuery("UpdateSyllabus");
			query.setParameter("pSyllabusId", syllabusId);
			query.setParameter("pSemester", semester);
			query.setParameter("pYear", year);
			query.setParameter("pCourseId", this.getCourse().getCourseId());
			query.setParameter("pLecturerId", this.getLecturer().getLecturerId());
			query.setParameter("pSectionNo", sectionNo);
			query.setParameter("pClassroomId", this.getClassroom().getClassroomId());
			query.executeUpdate();
			tx.commit();
		}catch(Exception e){
			System.err.print(e.getMessage());
		}
		finally{
			session.close();
		}
	}

public void addSyllabus(){
	
	Session session=null;
	try{
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.getNamedQuery("AddSyllabus");
		query.setParameter("pSemester", semester);
		query.setParameter("pYear", year);
		query.setParameter("pCourseId", course.getCourseId());
		query.setParameter("pLecturerId", lecturer.getLecturerId());
		query.setParameter("pSectionNo", sectionNo);
		query.setParameter("pClassroomId", this.getClassroom().getClassroomId());
		query.executeUpdate();
		tx.commit();
	}catch(Exception e){
		System.err.print(e.getMessage());
	}
	finally{
		session.close();
	}
}
/***************Project Second Part SP***********/
//AutoScheduling Part
@SuppressWarnings("unchecked")
public ArrayList<Syllabus> getSyllabusByGrade(int year, String semester, int grade)
{
        Session session = null;
        ArrayList<Syllabus> syllabusList = new ArrayList<Syllabus>();
        try
        {
                 SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                 session = sessionFactory.openSession();
                 
                 Query query = session.getNamedQuery("getSyllabusByGrade");
                 query.setParameter("pYear", year);
                 query.setParameter("pSemester", semester);
                 query.setParameter("pGrade", grade); 
                 syllabusList = (ArrayList<Syllabus>)query.list();
        }
        catch(Exception ex)
        {
                ex.getMessage();
        }                
        return syllabusList;
}
//AutoScheduling Part
@SuppressWarnings("unchecked")
public ArrayList<Syllabus> getSyllabusByCourseName(String courseName, int year, String semester)
{
        Session session = null;
        ArrayList<Syllabus> syllabusList = new ArrayList<Syllabus>();
        try
        {
                 SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                 session = sessionFactory.openSession();
                 
                 Query query = session.getNamedQuery("getSyllabusByCourseName");
                 query.setParameter("pCourseName", courseName);
                 query.setParameter("pYear", year);
                 query.setParameter("pSemester", semester);
                 syllabusList = (ArrayList<Syllabus>)query.list();
        }
        catch(Exception ex)
        {
                ex.getMessage();
        }                
        return syllabusList;
}
//AutoScheduling Part
@SuppressWarnings("unchecked")
public ArrayList<Syllabus> getSyllabusByCourseCode(int year, String semester, String courseCode)
{
        Session session = null;
        ArrayList<Syllabus> syllabusList = new ArrayList<Syllabus>();
        try
        {
                 SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                 session = sessionFactory.openSession();
                 
                 Query query = session.getNamedQuery("getSyllabusByCourseCode");
                 query.setParameter("pYear", year);
                 query.setParameter("pSemester", semester);
                 query.setParameter("pCourseCode", courseCode);
              
                 syllabusList = (ArrayList<Syllabus>)query.list();
        }
        catch(Exception ex)
        {
                ex.getMessage();
        }                
        return syllabusList;
}
//AutoScheduling Part
@SuppressWarnings("unchecked")
public ArrayList<Syllabus> getSyllabusAll()
{
        Session session = null;
        ArrayList<Syllabus> syllabusList = new ArrayList<Syllabus>();
        try
        {
                 SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                 session = sessionFactory.openSession();
                 
                 Query query = session.getNamedQuery("getSyllabusAll");
                 syllabusList = (ArrayList<Syllabus>)query.list();
        }
        catch(Exception ex)
        {
                ex.getMessage();
        }                
        return syllabusList;
}
//AutoScheduling Part
@SuppressWarnings("unchecked")
public ArrayList<Syllabus> getSyllabusByYear(int year)
{
        Session session = null;
        ArrayList<Syllabus> syllabusList = new ArrayList<Syllabus>();
        try
        {
                 SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                 session = sessionFactory.openSession();
                 
                 Query query = session.getNamedQuery("getSyllabusByYear");
                 query.setParameter("pYear", year);
                 syllabusList = (ArrayList<Syllabus>)query.list();
        }
        catch(Exception ex)
        {
                ex.getMessage();
        }                
        return syllabusList;
}
//AutoScheduling Part
@SuppressWarnings("unchecked")
public ArrayList<Syllabus> getDeanCourses(int year, String semester)
{
             Session session = null;
     ArrayList<Syllabus> deanCourseNameList = new ArrayList<Syllabus>();
     try
     {
             SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
         session = sessionFactory.openSession();
         Query query = session.getNamedQuery("getDeanCourses");
         query.setParameter("pYear", year);
         query.setParameter("pSemester", semester);
         deanCourseNameList = (ArrayList<Syllabus>)query.list();
     }
     catch(Exception ex)
     {
         ex.getMessage();
     }
     return deanCourseNameList;
 }

    @SuppressWarnings("unchecked")
	public List<Syllabus> getSyllabusBySemesterAndGrade(){
		List<Syllabus> allSyllabus = null;
		
         Session session = null;
         
         try {
                 SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                 session = sessionFactory.openSession();
                 
                 Query query = session.getNamedQuery("GetSyllabusBySemesterAndGrade");
                 query.setParameter("pGrade", course.getGrade());
                 query.setParameter("pSemester", semester);
                 query.setParameter("pYear", year);
                 allSyllabus =(List<Syllabus>) query.list();
                 
         } catch (Exception e) {
                 // TODO: handle exception
                 e.getMessage();
         } 
         return allSyllabus;
	}
    /****************************Second Part SP end*********************/
	@SuppressWarnings("unchecked")
	public List<Syllabus> getAllSyllabus(){
		List<Syllabus> allSyllabus = null;
		
         Session session = null;
         
         try {
                 SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                 session = sessionFactory.openSession();
                 
                 Query query = session.getNamedQuery("GetAllSyllabus");
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

	public Lecturer getLecturer() {
		return this.lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
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

	public Integer getSectionNo() {
		return this.sectionNo;
	}

	public void setSectionNo(Integer sectionNo) {
		this.sectionNo = sectionNo;
	}

	public Set getSchedules() {
		return this.schedules;
	}

	public void setSchedules(Set schedules) {
		this.schedules = schedules;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	
}
