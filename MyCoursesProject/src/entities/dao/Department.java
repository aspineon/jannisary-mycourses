package entities.dao;

// Generated Apr 11, 2011 11:14:09 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.Pattern;

/**
 * Department generated by hbm2java
 */
public class Department implements java.io.Serializable {

	private Integer departmentId;
	
	@NotEmpty
    @Pattern(regex=".*[^\\s].*", message="This string contain only spaces")
    @Length(min=2,max=12)
	private String deptCode;
	
	@NotEmpty
    @Length(min=3,max=200)
	private String deptDescription;
	private Set courses = new HashSet(0);
	private Set lecturers = new HashSet(0);
	private Set classrooms = new HashSet(0);

	public Department() {
	}

	public Department(String deptCode) {
		this.deptCode = deptCode;
	}

	public Department(String deptCode, String deptDescription, Set courses,
			Set lecturers, Set classrooms) {
		this.deptCode = deptCode;
		this.deptDescription = deptDescription;
		this.courses = courses;
		this.lecturers = lecturers;
		this.classrooms = classrooms;
	}
	
	/*Copy constructor added by Erhun 17.04.2011*/
	public Department(Department department){
		this.departmentId = department.departmentId;
		this.deptCode = department.deptCode;
		this.deptDescription = department.deptDescription;
		this.lecturers = department.lecturers;
		this.courses = department.courses;
		this.classrooms = department.classrooms;
	}
	
	public List<Department> getAllDepartments(){
		List<Department> departmentList = null;
		Session session = null;
		
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			
			Query query = session.getNamedQuery("getAllDepartments");
			departmentList = (List<Department>) query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} 
		return departmentList;
	}
	
	public List<Department> getDepartmentByCode(){
		List<Department> departmentList = null;
		
        Session session = null;
        
        try {
                SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                session = sessionFactory.openSession();
                
                Query query = session.getNamedQuery("getDepartmentByCode");
                
                query.setParameter("pDepartmentCode", deptCode);
                departmentList = (List<Department>) query.list();
                
        } catch (Exception e) {
                // TODO: handle exception
                e.getMessage();
        } 
        return departmentList;
		
	}

	public void addDepartment(){
		Session session=null;
		try{
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.getNamedQuery("AddDepartment");
			query.setParameter("pDeptCode", deptCode);
			query.setParameter("pDeptDescription", deptDescription);
			query.executeUpdate();
			tx.commit();
			
		}catch(Exception e){
			System.err.print(e.getMessage());
		}
		finally{
			session.close();
			
		}
	}
	
	public void deleteDepartment(){
		Session session=null;
		try{
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.getNamedQuery("DeleteDepartment");
			query.setParameter("pDepartmentId", departmentId);
			query.executeUpdate();
			tx.commit();
		}catch(Exception e){
			System.err.print(e.getMessage());
		}
		finally{
			session.close();
		}
	}

	public void updateDepartment() throws Exception{
		Session session=null;
		try{
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.getNamedQuery("UpdateDepartment");
			query.setParameter("pDepartmentId", departmentId);
			query.setParameter("pDeptCode", deptCode);
			query.setParameter("pDeptDescription", deptDescription);
			query.executeUpdate();
			tx.commit();
		}catch(Exception e){
			System.err.print(e.getMessage());
			throw new Exception(e);
		}
		finally{
			session.close();
		}
	}
	
	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptDescription() {
		return this.deptDescription;
	}

	public void setDeptDescription(String deptDescription) {
		this.deptDescription = deptDescription;
	}

	public Set getCourses() {
		return this.courses;
	}

	public void setCourses(Set courses) {
		this.courses = courses;
	}

	public Set getLecturers() {
		return this.lecturers;
	}

	public void setLecturers(Set lecturers) {
		this.lecturers = lecturers;
	}

	public Set getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(Set classrooms) {
		this.classrooms = classrooms;
	}
}
