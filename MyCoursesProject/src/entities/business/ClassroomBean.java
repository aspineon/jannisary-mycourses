package entities.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import entities.dao.Classroom;
import entities.dao.Department;

public class ClassroomBean {
	
	/* Bug Fixed
	 * Bug selectionChanged method***********
	 * Su an bu olay en son değişikliği kaydediyor, o yüzden ekranda ekleme
	 * işlemi yaparken en son başka bir alan değiştirilirse o değeri alıyor
	 * */
	public void selectionChangedDepartmentEditCombo(ValueChangeEvent  evt) {
		 String selectedDeptCode = (String) evt.getNewValue();
		 department.setDeptCode(selectedDeptCode);
		 department = new Department(department.getDepartmentByCode().get(0)); 
		 if (!selectedDeptCode.equals("")) {
			 currentItem.setDepartment(department);
		 }
	}
	
	public void selectionChangedDepartmentAddCombo(ValueChangeEvent  evt) {
		String selectedDeptCode = (String) evt.getNewValue();
		department.setDeptCode(selectedDeptCode);
		department = new Department(department.getDepartmentByCode().get(0));
		if (!selectedDeptCode.equals("")) {
			 currentItem.setDepartment(department);
		}
	}
	
	public List<Classroom> getAllClassrooms() {
		if(!LoginBean.getLoginUser().getUserStatus().equals("admin")){
	 		try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/MyCoursesProject/faces/PreProd/login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	}
		
		synchronized (this) {
			allClassrooms = new ArrayList<Classroom>();
			try {
				allClassrooms = currentItem.getAllClassroom();
			} catch (Exception e) {
				System.out.println("!Load AllClassrooms Error: " + e.getMessage());
			}
		}
		return allClassrooms;
	}

	public String addClassroom(){
		try{	
			int size = allClassrooms.size();
			Classroom classroom = new Classroom(currentItem);
			allClassrooms.add(size,classroom);
			classroom.addClassroom();
			keys.clear();
			keys.add(allClassrooms.size());
			/*SysUser.jsp'de yer alan User Name ve User Password alanlarını temizle*/
			currentItem.setClassroomCode("");
			allClassrooms = classroom.getAllClassroom();
		}catch(Exception ex){
			System.err.println(ex.getMessage());}
		return null;
	}
	
	public void store() {
		
		/*try-catch blogu eklenecek*/
		try{
			currentItem.setDepartment(department);
			currentItem.updateClassroom();
			allClassrooms.set(currentRow, currentItem);
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
			currentItem = allClassrooms.get(currentRow);
			currentItem.deleteClassroom();
			allClassrooms.remove(currentItem);
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}
	
	public List<SelectItem> getDepartmentCodeList(){
		synchronized (this) {   
        	departmentCodeList = new ArrayList<SelectItem>();
            try {
            	//Department nesnelerini al.
                List<Department> departmentList = department.getAllDepartments();
                int i;
                for(i=0; i<departmentList.size(); i++){
                	String strDepartmentCode = departmentList.get(i).getDeptCode();
                	departmentCodeList.add(new SelectItem(strDepartmentCode));
                }
            }catch (Exception e) {
            	System.out.println("!!!!!!loadAllSyllabus Error: " + e.getMessage());
            	e.printStackTrace();
            }
        }
		return departmentCodeList;
	}
	
	public List<SelectItem> getClassroomCodeList() {
		
		synchronized (this) {
	        classroomCodeList = new ArrayList<SelectItem>();
	        try {    
	            for(int i=0; i<allClassrooms.size(); i++){
                    String strClassroomCode = allClassrooms.get(i).getClassroomCode();
                    classroomCodeList.add(new SelectItem(strClassroomCode));
	            }
	        }catch (Exception e) {
	        	System.out.println("!Load All Classrooms Error: " + e.getMessage());
	        }
        } 
		return classroomCodeList;
	}

	public void setClassroomCodeList(List<SelectItem> classroomCodeList) {
		this.classroomCodeList = classroomCodeList;
	}

	public Classroom getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(Classroom currentItem) {
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getClassroomCode() {
		return classroomCode;
	}

	public void setClassroomCode(String classroomCode) {
		this.classroomCode = classroomCode;
	}


	/********************Sınıf Alt Alanları*************************/
	private Classroom currentItem = new Classroom();
	private List<SelectItem> departmentCodeList = new ArrayList<SelectItem>() ;
	private List<SelectItem> classroomCodeList = new ArrayList<SelectItem>();
	private Set<Integer> keys = new HashSet<Integer>();
	private int currentRow;
	private Department department = new Department();
	private String classroomCode;
	private List<Classroom> allClassrooms = new ArrayList<Classroom>();
}
