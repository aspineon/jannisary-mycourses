package entities.business;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import entities.dao.Classroom;
import entities.dao.Course;
import entities.dao.Syllabus;

public class DeanBean 
{
	private ArrayList<Integer> list;
	private Integer testArray[];
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
		
		/*
		testArray = new Integer[8];
		testArray[0] = 1;
		testArray[1] = 2;
		testArray[2] = 3;
		testArray[3] = 4;
		testArray[4] = 5;
		testArray[5] = 6;
		testArray[6] = 7;
		testArray[7] = 8;*/
		
	}
	/*
	public Integer[] getTestArray() {
		return testArray;
	}

	public void setTestArray(Integer[] testArray) {
		this.testArray = testArray;
	}
*/
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
	
	private int getCourseIdFromCourseList(String paramCourseName)
	{
		int returnValue = -1;
		try
		{
			for(int acc = 0; acc < deanCourseList.size(); acc++)
			{
				String str_CourseName = deanCourseList.get(acc).getCourseName();
				if(str_CourseName.equals(paramCourseName))
				{
					returnValue = deanCourseList.get(acc).getCourseId();
				}
			}
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
		
		return returnValue;
	}
	
	
	public void selectionChanged(ValueChangeEvent evt)
	{
		String selectedCourseName = (String)evt.getNewValue();
		if(!selectedCourseName.equals(""))
		{
			int intCourseId = getCourseIdFromCourseList(selectedCourseName);
			deanCourse.setCourseId(intCourseId);
			deanCourse.getLecturerNameByCourseId();
		}
	}
	
		
	public ArrayList<SelectItem> getDeanLecturerList() {
		
		System.out.println("asd");
		synchronized (this) {
			if(deanSyllabusList == null)
			{
				deanSyllabusList = new ArrayList<Syllabus>();
				try
				{
					deanSyllabusList = deanCourse.getLecturerNameByCourseId();
					
					for(int i = 0; i < deanSyllabusList.size(); i++)
					{
						String lecturerName = deanSyllabusList.get(i).getLecturer().getLecturerName();
						deanLecturerList.add(new SelectItem(lecturerName));//(new SelectItem(departmentDesc));
					}
				}
				catch(Exception ex)
				{
					System.err.println(ex.getMessage());
				}
			}
		}
		
		return deanLecturerList;
		
		/*
		deanLecturerList = new ArrayList<SelectItem>();
		if(deanSyllabusList != null)
		{
			for(int i = 0; i < deanSyllabusList.size(); i++)
			{
				String str_LecturerName = deanSyllabusList.get(i).getLecturer().getLecturerName();
				deanLecturerList.add(new SelectItem(str_LecturerName));
			}
		}
		else
		{
			deanLecturerList.add(new SelectItem(""));
		}
		return deanLecturerList;*/
	}

	public void setDeanLecturerList(ArrayList<SelectItem> deanLecturerList) {
		this.deanLecturerList = deanLecturerList;
	}

	ArrayList<Syllabus> deanSyllabusList = null;
	ArrayList<SelectItem> deanLecturerList = null;
	ArrayList<Course> deanCourseList = null;
	
//**********************************************************************************	

}
