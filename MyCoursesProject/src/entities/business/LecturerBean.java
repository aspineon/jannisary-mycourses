package entities.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import entities.dao.*;

public class LecturerBean {
	
	public void selectionChangedDepartmentEditCombo(ValueChangeEvent  evt) {
		 String selectedDepartmentCode = (String) evt.getNewValue();
		 department.setDeptCode(selectedDepartmentCode);
		 
		 
		 if (!selectedDepartmentCode.equals("")) {
			 department = new Department(department.getDepartmentByCode().get(0));
			 currentItem.setDepartment(department);
		 }
	}
	
	public void selectionChangedDeparmentAddCombo(ValueChangeEvent  evt) {
		 String selectedDeptCode = (String) evt.getNewValue();

		 department.setDeptCode(selectedDeptCode);
		 Integer intDepartmentId = department.getDepartmentByCode().get(0).getDepartmentId();
		 
		 if (!selectedDeptCode.equals("")) {
			 department.setDepartmentId(intDepartmentId);
			 currentItem.setDepartment(department);
		 }
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
	
	public void store(){
		/*try-catch blogu eklenecek*/
		try{
			currentItem = allLecturerList.get(currentRow);
			
			currentItem.setDepartment(department);
			currentItem. updateLecturer();
			allLecturerList.set(currentRow, currentItem);
			keys.clear();
			keys.add(currentRow);
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}
	
	public void delete(){
		try{
		currentItem = allLecturerList.get(currentRow);
		currentItem.deleteLecturer();
		allLecturerList.remove(currentItem);
	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public String addLecturer(){
		try{
			
			int size = allLecturerList.size();		
			Lecturer newlecturer = new Lecturer(currentItem);
			allLecturerList.add(size,newlecturer);
			newlecturer.addLecturer();
			keys.clear();
			keys.add(allLecturerList.size());
			allLecturerList = newlecturer.getAllLecturer();
			
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
		return null;
	}
	
	
	public Lecturer getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(Lecturer currentItem) {
		this.currentItem = currentItem;
	}
	
	
	
	public List<SelectItem> getSelectItemsForDepartments() {
		
		synchronized (this) {
            if (selectItemsForDepartments == null) {
                    selectItemsForDepartments = new ArrayList<SelectItem>();
                            try {
                                    //Department nesnelerini al.
                                    List<Department> departmentList = department.getAllDepartments();
                                    int i;
                                    for(i=0; i<departmentList.size(); i++){
                                            String strDeptCode = departmentList.get(i).getDeptCode();
                                            selectItemsForDepartments.add(new SelectItem(strDeptCode));
                                    }
                                    
                            } catch (Exception e) {
                                    System.out.println("!!!!!!loadAllSyllabus Error: "
                                                    + e.getMessage());
                                    e.printStackTrace();
                            }
            }
    }
		
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
