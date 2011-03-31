package entities.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SyllabusBean {
	
	ArrayList<String> lectureList;
	
	
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "janissary";
	String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "sapass";
    
    String lecturerName, courseName;
    String syllabusCancel = "";
    
    public String getLecturerName()
    {
    	return lecturerName;
    }
    
    public String getCourseName()
    {
    	return courseName;
    }
    public void setLecturerName(String name)
    {
    	this.lecturerName = name;
    }
    
    public void setCourseName(String name)
    {
    	this.courseName = name;
    }
    
    public ArrayList<String> fillToLectureComboBox()
    {
    	lectureList = new ArrayList<String>();
    	
    	try
    	{
    		Class.forName(driver).newInstance();
    		conn = DriverManager.getConnection(url+dbName,userName,password);
    		Statement s = conn.createStatement ();
    		String selectLectures = "SELECT * FROM course";    		
    		s.executeQuery(selectLectures);
    		
    		ResultSet rs = s.getResultSet();
    		
    		while(rs.next())
    		{
    			lectureList.add(rs.getString("Course_Name"));
    		}
    		
    		conn.close();   		
    	}
    	catch(Exception ex)
    	{
    		
    	}
    	return lectureList;
    }
    public String cancelSyllabus()
    {
    	try
    	{
    		Class.forName(driver).newInstance();
    		conn = DriverManager.getConnection(url+dbName,userName,password);
    		Statement s = conn.createStatement ();
    		String selectLectures = "DELETE FROM syllabus WHERE Course_Id = (SELECT Course_Id FROM course where Course_Name = '"+courseName+"')" +
    				" AND Lecturer_Id = (SELECT Lecturer_Id FROM lecturer where Lecturer_Name = '"+lecturerName+"') ";    		
    		s.executeQuery(selectLectures);
    		
    		conn.close();
    		syllabusCancel = "Succesfully completed !!";
    	}
    	catch(Exception ex)
    	{
    		syllabusCancel = ex.getMessage();
    	}
    	return syllabusCancel;
    }
    
}
