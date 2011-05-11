package entities.business;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.richfaces.model.SortField;
import org.richfaces.model.SortOrder;
import org.richfaces.model.selection.SimpleSelection;

import entities.dao.Course;
import entities.dao.Department;
import entities.dao.SysUser;
import entities.dao.TypeofCourse;

public class CourseBean {
	
	private Course course;
	private Integer courseId;
	private String courseCode;
	private String courseName;
	private int teoricLectureHours;
	private int practiceLectureHourse;
	private boolean attendance;
	private TypeofCourse typeofCourseId;
	private String courseDescription;
	private Department department;
	
	public CourseBean(){
		super();
		selectItemsForAttendance.add(new SelectItem("True"));
		selectItemsForAttendance.add(new SelectItem("False"));
		
		selectItemsGrades.add(new SelectItem("1"));
		selectItemsGrades.add(new SelectItem("2"));
		selectItemsGrades.add(new SelectItem("3"));
		selectItemsGrades.add(new SelectItem("4"));
	}
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getTeoricLectureHours() {
		return teoricLectureHours;
	}
	public void setTeoricLectureHours(int teoricLectureHours) {
		this.teoricLectureHours = teoricLectureHours;
	}
	public int getPracticeLectureHourse() {
		return practiceLectureHourse;
	}
	public void setPracticeLectureHourse(int practiceLectureHourse) {
		this.practiceLectureHourse = practiceLectureHourse;
	}
	public boolean isAttendance() {
		return attendance;
	}
	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}
	public TypeofCourse getTypeofCourseId() {
		return typeofCourseId;
	}
	public void setTypeofCourseId(TypeofCourse typeofCourseId) {
		this.typeofCourseId = typeofCourseId;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	
	

	///////////////////////////////////////////////////////////
	
	private List<SelectItem> selectItemsForDepartments;
	private List<SelectItem> selectItemsForTypeofCourses;
	private List<SelectItem> selectItemsForPreconditions;
	private List<SelectItem> selectItemsForAttendance = new ArrayList<SelectItem>();
	private List<SelectItem> selectItemsGrades = new ArrayList<SelectItem>();;
	private Course currentItem = new Course();
	private Set<Integer> keys = new HashSet<Integer>();
	private int currentRow;
	private List<Course> allCourses = null;
	private List<Course> allPreconditionCourses = null;
	private List<Department> allDepartments = null;
	private List<TypeofCourse> allTypeofCourses = null;

	
	
	
	public List<SelectItem> getSelectItemsForPreconditions() {
		selectItemsForPreconditions = new ArrayList<SelectItem>();
		FillPreconditionsCombo();
		return selectItemsForPreconditions;
	}

	public void setSelectItemsForPreconditions(
			List<SelectItem> selectItemsForPreconditions) {
		this.selectItemsForPreconditions = selectItemsForPreconditions;
	}

	public List<SelectItem> getSelectItemsGrades() {
		return selectItemsGrades;
	}

	public void setSelectItemsGrades(List<SelectItem> selectItemsGrades) {
		this.selectItemsGrades = selectItemsGrades;
	}

	public List<SelectItem> getSelectItemsForTypeofCourses() {
		FillTypeofCoursesCombo();
		return selectItemsForTypeofCourses;
	}
	public void setSelectItemsForTypeofCourses(
			List<SelectItem> selectItemsForTypeofCourses) {
		this.selectItemsForTypeofCourses = selectItemsForTypeofCourses;
	}
	public List<SelectItem> getSelectItemsForDepartments() {
		FillDepartmentsCombo();
		return selectItemsForDepartments;
	}

	public void setSelectItemsForDepartments(List<SelectItem> selectItems) {
		this.selectItemsForDepartments = selectItems;
	}
	
	public List<SelectItem> getSelectItemsForAttendance() {
		return selectItemsForAttendance;
	}
	public void setSelectItemsForAttendance(
			List<SelectItem> selectItemsForAttendance) {
		this.selectItemsForAttendance = selectItemsForAttendance;
	}
	public void selectionChangedDepartmentCombo(ValueChangeEvent  evt) {
		 String selectedValue = (String) evt.getNewValue();
		 Department dpt = new Department(selectedValue);
		 allDepartments = dpt.getDepartmentByCode();

		 if (!selectedValue.equals("")) {
			 currentItem.setDepartment(allDepartments.get(0));
		 }
	}
	
	public void selectionChangedAttendanceCombo(ValueChangeEvent  evt) {
		 String selectedValue = (String) evt.getNewValue();

		 if (!selectedValue.equals("")) {
			 currentItem.setAttendance(Boolean.parseBoolean(selectedValue));
		 }
	}
	
	public void selectionChangedGradeCombo(ValueChangeEvent  evt) {
		 String selectedValue = (String) evt.getNewValue();

		 if (!selectedValue.equals("")) {
			 currentItem.setGrade(Integer.parseInt(selectedValue));
		 }
	}
	
	public void selectionChangedTypeofCourseCombo(ValueChangeEvent  evt) {
		 String selectedValue = (String) evt.getNewValue();
		 TypeofCourse toc = new TypeofCourse(selectedValue);
		 allTypeofCourses = toc.getTypeofCourseByCode();

		 if (!selectedValue.equals("")) {
			 currentItem.setTypeofCourse(allTypeofCourses.get(0));
		 }
	}
	
	public void FillDepartmentsCombo(){
		int i;
		selectItemsForDepartments = new ArrayList<SelectItem>();
		department = allCourses.get(0).getDepartment();
		allDepartments = department.getAllDepartments();
		for(i=0;i<allDepartments.size();i++){
			String strDpt = allDepartments.get(i).getDeptCode();
			selectItemsForDepartments.add(new SelectItem(strDpt));
		}
	}
	
	public void FillPreconditionsCombo(){
		int i;
		selectItemsForPreconditions = new ArrayList<SelectItem>();
		course = allCourses.get(0);
		allPreconditionCourses = course.getAllCourses();
		for(i=0;i<allPreconditionCourses.size();i++){
			String strDpt = allPreconditionCourses.get(i).getCourseCode();
			selectItemsForPreconditions.add(new SelectItem(strDpt));
		}
	}
	
	public void FillTypeofCoursesCombo(){
		int i;
		selectItemsForTypeofCourses = new ArrayList<SelectItem>();
		typeofCourseId = allCourses.get(0).getTypeofCourse();
		allTypeofCourses = typeofCourseId.getAllTypeofCourses();
		for(i=0;i<allTypeofCourses.size();i++){
			String strTypeofCourse = allTypeofCourses.get(i).getTypeofCourse();
			selectItemsForTypeofCourses.add(new SelectItem(strTypeofCourse));
		}
	}
	
	public List<Course> getAllCourses() {
		synchronized (this) {
			if (allCourses == null) {
				allCourses = new ArrayList<Course>();
					try {
						allCourses = currentItem.getAllCourses();
						//listSeperator();
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllUsers Error: "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}
		return allCourses;
	}

	

	public String addCourse(){
		try{
			
			
			int size = allCourses.size();		
			
			Course course = new Course(currentItem);
			allCourses.add(size,course);
			course.AddCourse();
			keys.clear();
			keys.add(allCourses.size());
			allCourses = course.getAllCourses();
			
			
			
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
		return null;
	}
	
	public void store() {
		/*
		allCars.set(currentRow, currentItem);
		keys.clear();
		keys.add(currentRow);
		*/
		
		/*try-catch blogu eklenecek*/
		try{
			currentItem = allCourses.get(currentRow);
			currentItem.updateCourse();
			allCourses.set(currentRow, currentItem);
			keys.clear();
			keys.add(currentRow);
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}

	
	public void delete() {
		
		/* try-catch bloğu eklenecek
		 *Önce veritabanımızdan siliyoruz, ardından listeden siliyoruz.
		 *Olası bir veritabanı hatasında ve silmeme probleminde listeden
		 *de silinmeyecek ve kullanıcı veritabanı hatasından bilgilendirilecektir.
		 * 
		 */
		try {
			currentItem = allCourses.get(currentRow);
			currentItem.deleteCourse();
			allCourses.remove(currentItem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Course getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(Course currentItem) {
		this.currentItem = currentItem;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}


	public Set<Integer> getKeys() {
		return keys;
	}

	public void setKeys(Set<Integer> keys) {
		this.keys = keys;
	}
}
