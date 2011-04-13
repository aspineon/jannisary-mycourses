package entities.business;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import entities.dao.Classroom;
import entities.dao.Course;

public class DeanBean 
{
	private ArrayList<Integer> list;
	@PostConstruct
	public void init()
	{
		list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
	}
	
	public ArrayList<Integer> getList()
	{		
		return list;
	}
	
	// Dekan derslerini filtreleme **************************************************
	Course deanCourse = new Course();
	ArrayList<SelectItem> selectItemListDeanCourse = new ArrayList<SelectItem>();
	
	public ArrayList<SelectItem> getSelectItemListDeanCourse() {
		System.out.println("asd");
		synchronized (this) {
			if(deanCourseList == null)
			{
				deanCourseList = new ArrayList<Course>();
				try
				{
					deanCourseList = deanCourse.getDeanCourseNameList();
					
					for(int i = 0; i < deanCourseList.size(); i++)
					{
						String departmentDesc = deanCourseList.get(i).getCourseName();
						selectItemListDeanCourse.add(new SelectItem(departmentDesc));//(new SelectItem(departmentDesc));
					}
				}
				catch(Exception ex)
				{
					System.err.println(ex.getMessage());
				}
			}
		}
		
		return selectItemListDeanCourse;
	}
	
	ArrayList<Course> deanCourseList = null;
	
//**********************************************************************************	

}
