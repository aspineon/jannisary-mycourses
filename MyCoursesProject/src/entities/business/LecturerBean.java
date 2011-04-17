package entities.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import entities.dao.Course;
import entities.dao.Department;
import entities.dao.Lecturer;
import entities.dao.Syllabus;
import entities.dao.SysUser;
import entities.dao.TypeofCourse;

public class LecturerBean {
	
	public void selectionChangedDepartmentEditCombo(ValueChangeEvent  evt) {
		 String selectedDepartmentCode = (String) evt.getNewValue();
		 
		 
//		 lecturer.setLecturerName(selectedLecturerName);
		 //Integer intLecturerId = lecturer.getIdByLecturerName().get(0).getLecturerId();
		 department.setDeptCode(selectedDepartmentCode);
//		 lecturer = new Lecturer(lecturer.getIdByLecturerName().get(0));
		 
		 /*if (!selectedLecturerName.equals("")) {
			 currentItem.setLecturer(lecturer);
		 }*/
	}
	
	
	
	public List<Lecturer> getAllLecturerList() {
		/*
		 * ayni anda iki veya daha fazla thread tarafindan calistirilmamasi istenen metotlara verilen keyword.
		 *  synchonized ve statik olmayan bir metot calistirilirken nesneleri kilitlenir,
		 *  metodun sonunda tekrar unlock edilir
		 * */
		synchronized (this) {
			if (allLecturerList == null) {
				allLecturerList = new ArrayList<Lecturer>();
					try {
						allLecturerList = currentItem.getAllLecturer();
						
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllSyllabus Error: "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}
		return allLecturerList;
	}
	
	public String AddLecturer(){
//		currentItem.setLecturerName(lecturerName);
//		currentItem.AddLecturer();
		return null;
	}
	
	public Lecturer getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(Lecturer currentItem) {
		this.currentItem = currentItem;
	}
	
	
	
	public List<SelectItem> getSelectItemsForDepartments() {
		return selectItemsForDepartments;
	}

	public void setSelectItemsForDepartments(
			List<SelectItem> selectItemsForDepartments) {
		this.selectItemsForDepartments = selectItemsForDepartments;
	}

	public Set<Integer> getKeys() {
		return keys;
	}

	public void setKeys(Set<Integer> keys) {
		this.keys = keys;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public void setAllLecturerList(List<Lecturer> allLecturerList) {
		this.allLecturerList = allLecturerList;
	}


	private Department department = new Department();
	private List<SelectItem> selectItemsForDepartments;
	private Lecturer currentItem = new Lecturer();
	private Set<Integer> keys = new HashSet<Integer>();
	private int currentRow;
	private List<Lecturer> allLecturerList = null;
	private List<Department> allDepartments = null;
	
}
