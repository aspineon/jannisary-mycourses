package entities.dao;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

// Generated Apr 11, 2011 11:14:09 PM by Hibernate Tools 3.4.0.CR1

/**
 * SyllabusArchive generated by hbm2java
 */
public class SyllabusArchive implements java.io.Serializable {

	private static final long serialVersionUID = 7984451947877817956L;
	
	private Integer syllabusArchiveId;
	private String semester;
	private int year;
	private String filePath;
	private String versionName;
	private Set schedules = new HashSet(0);
	
	public SyllabusArchive() {
	}

	public SyllabusArchive(String semester, int year, String filePath, String versionName) {
		this.semester = semester;
		this.year = year;
		this.filePath = filePath;
		this.versionName = versionName;
	}

	public void addSyllabusArchive(){
		Session session=null;
		try{
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.getNamedQuery("AddSyllabusArchive");
			query.setParameter("pSemester", this.semester);
			query.setParameter("pYear", this.year);
			query.setParameter("pFilePath", this.filePath);
			query.setParameter("pVersionName", this.versionName);
			query.executeUpdate();
			tx.commit();
		}catch(Exception e){
			System.err.print(e.getMessage());
		}
		finally{
			session.close();
		}
	}
	
	public ArrayList<SyllabusArchive> GetSyllabusArchiveIdByVersionName()
	{
	        Session session = null;
	        ArrayList<SyllabusArchive> syllabusArchiveList = new ArrayList<SyllabusArchive>();
	        try{
                 SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                 session = sessionFactory.openSession();
                 
                 Query query = session.getNamedQuery("GetSyllabusArchiveIdByVersionName");
                 query.setParameter("pVersionName", this.versionName);
                 syllabusArchiveList = (ArrayList<SyllabusArchive>)query.list();
	        }
	        catch(Exception ex){
	                System.err.print(ex.getMessage());
	        }                
	        return syllabusArchiveList;
	}
	
	public Integer getSyllabusArchiveId() {
		return this.syllabusArchiveId;
	}

	public void setSyllabusArchiveId(Integer syllabusArchiveId) {
		this.syllabusArchiveId = syllabusArchiveId;
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

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setSchedules(Set schedules) {
		this.schedules = schedules;
	}

	public Set getSchedules() {
		return schedules;
	}
}
