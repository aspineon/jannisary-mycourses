package entities.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import entities.dao.Course;
import entities.dao.Department;

public class DepartmentBean {
	
	public List<Department> getAllDepartments() {
		synchronized (this) {
			if (allDepartments == null) {
				allDepartments = new ArrayList<Department>();
					try {
						allDepartments = currentItem.getAllDepartments();
						//listSeperator();
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllDepartments Error: "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}
		return allDepartments;
	}

	public String addDepartment(){
		try{
			
			
			int size = allDepartments.size();		
			
			
			Department department = new Department(currentItem);
			allDepartments.add(size,department);
			department.addDepartment();
			keys.clear();
			keys.add(allDepartments.size());
			/*Department.jsp'de yer alan Departmen DeptCode ve Dept Description alanlarını temizle*/
			currentItem.setDeptCode("");
			currentItem.setDeptDescription("");
			allDepartments = department.getAllDepartments();
		}catch(Exception ex){
			System.err.println(ex.getMessage());}
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
			currentItem = allDepartments.get(currentRow);
			currentItem.updateDepartment();
			allDepartments.set(currentRow, currentItem);
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
		try{
			currentItem = allDepartments.get(currentRow);
			currentItem.deleteDepartment();
			allDepartments.remove(currentItem);
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}
	
	
	
	public Department getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(Department currentItem) {
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
	
	

	public List<SelectItem> getSelectItemsDepartment() {
		synchronized (this) {
            if (selectItemsDepartment == null) {
                    selectItemsDepartment = new ArrayList<SelectItem>();
                            try {
                                    int i;
                                    for(i=0; i< allDepartments.size(); i++){
                                            String strDeptCode = allDepartments.get(i).getDeptCode();
                                            selectItemsDepartment.add(new SelectItem(strDeptCode));
                                    }
                                    
                            } catch (Exception e) {
                                    System.out.println("!!!!!!DepartmentBean getSelectItemsDepartment Error: "
                                                    + e.getMessage());
                                    e.printStackTrace();
                            }
            }
    }
		return selectItemsDepartment;
	}

	public void setSelectItemsDepartment(List<SelectItem> selectItemsDepartment) {
		this.selectItemsDepartment = selectItemsDepartment;
	}



	private Department currentItem = new Department();
	List<SelectItem> selectItemsDepartment = null;
	private Set<Integer> keys = new HashSet<Integer>();
	private int currentRow;
	
	
	private List<Department> allDepartments = null;
}