package entities.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.Email;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.Pattern;

import entities.dao.Course;
import entities.dao.TypeofCourse;

public class TypeofCourseBean {
	private Integer typeofCourseId;
	
	
	private String typeofCourse;
	
	public Integer getTypeofCourseId() {
		return typeofCourseId;
	}
	public void setTypeofCourseId(Integer typeofCourseId) {
		this.typeofCourseId = typeofCourseId;
	}
	public String getTypeofCourse() {
		return typeofCourse;
	}
	public void setTypeofCourse(String typeofCourse) {
		this.typeofCourse = typeofCourse;
	}
	
	//////////////////////////////////////////////////////
	
	private TypeofCourse currentItem = new TypeofCourse();
	private Set<Integer> keys = new HashSet<Integer>();
	private int currentRow;
	private List<TypeofCourse> allTypeofCourses = null;
	
	public List<TypeofCourse> getAllTypeofCourses() {
		synchronized (this) {
			if (allTypeofCourses == null) {
				allTypeofCourses = new ArrayList<TypeofCourse>();
					try {
						allTypeofCourses = currentItem.getAllTypeofCourses();
						//listSeperator();
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllUsers Error: "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}
		return allTypeofCourses;
	}

	

	public String addTypeofCourse(){
		try{
			
			
			int size = allTypeofCourses.size();		
			
			TypeofCourse typeofCourse = new TypeofCourse(currentItem);
			allTypeofCourses.add(size,typeofCourse);
			typeofCourse.addTypeofCourse();
			keys.clear();
			keys.add(allTypeofCourses.size());
			/*SysUser.jsp'de yer alan User Name ve User Password alanlarını temizle*/
			
			/*currentItem.setUserName("");
			currentItem.setUserPassword("");*/
			
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
			currentItem = allTypeofCourses.get(currentRow);
			currentItem.updateTypeofCourse();
			allTypeofCourses.set(currentRow, currentItem);
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
			currentItem = allTypeofCourses.get(currentRow);
			currentItem.deleteTypeofCourse();
			allTypeofCourses.remove(currentItem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TypeofCourse getCurrentItem() {
		return currentItem;
	}
	public void setCurrentItem(TypeofCourse currentItem) {
		this.currentItem = currentItem;
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

	public void setAllTypeofCourses(List<TypeofCourse> allTypeofCourses) {
		this.allTypeofCourses = allTypeofCourses;
	}
		
	
}
