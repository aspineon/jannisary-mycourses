package entities.dao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
// Generated Apr 11, 2011 11:14:09 PM by Hibernate Tools 3.4.0.CR1

/**
 * SysUser generated by hbm2java
 */
public class SysUser implements java.io.Serializable {

	private Integer userId;
	private String userName;
	private String userStatus;
	private String userPassword;

	public SysUser() {
	}

	public SysUser(String userName, String userStatus, String userPassword) {
		this.userName = userName;
		this.userStatus = userStatus;
		this.userPassword = userPassword;
	}

/*Copy Constructor added 8.04.2011 by Erhun*/
	
	public SysUser(SysUser sysUser){
		this.userName = sysUser.userName;
		this.userPassword = sysUser.userPassword;
		this.userStatus = sysUser.userStatus;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysUser> getAllUser(){
		List<SysUser> allUser = null;
		
		 //List<TestUser> result = null;
         Session session = null;
         
         try {
                 SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                 session = sessionFactory.openSession();
                 
                 Query query = session.getNamedQuery("getAllUser");
                 allUser =(List<SysUser>) query.list();
                 
         } catch (Exception e) {
                 // TODO: handle exception
                 e.getMessage();
         } 
         return allUser;
	}
	
	public void addUser(){
		Session session=null;
		try{
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("AddUser");
			query.setParameter("pUserName", userName);
			query.setParameter("pUserPass", userPassword);
			query.setParameter("pUserStatus", userStatus);
			query.executeUpdate();
		}catch(Exception e){
			System.err.print(e.getMessage());
		}
	}
	
	public void deleteUser(){
		Session session=null;
		try{
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("DeleteUser");
			query.setParameter("pUserId", userId);
			query.executeUpdate();
		}catch(Exception e){
			System.err.print(e.getMessage());
		}
	}

	public void updateUser() throws Exception{
		Session session=null;
		try{
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("UpdateUser");
			query.setParameter("pUserId", userId);
			query.setParameter("pUserName", userName);
			query.setParameter("pUserStatus", userStatus);
			query.setParameter("pUserPass", userPassword);
			query.executeUpdate();
		}catch(Exception e){
			System.err.print(e.getMessage());
			throw new Exception(e);
		}
	}
	
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
