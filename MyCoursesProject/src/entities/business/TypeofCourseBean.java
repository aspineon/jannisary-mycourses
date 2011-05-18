package entities.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import entities.dao.TypeofCourse;

public class TypeofCourseBean {
	
	/********************Sınıf Metodları*********************************/
	
	public String addTypeofCourse(){
		try{
			int size = allTypeofCourses.size();		
			TypeofCourse typeofCourse = new TypeofCourse(currentItem);
			allTypeofCourses.add(size,typeofCourse);
			typeofCourse.addTypeofCourse();
			keys.clear();
			keys.add(allTypeofCourses.size());
			allTypeofCourses = typeofCourse.getAllTypeofCourses();
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
		return null;
	}
	
	public void store() {
		/*try-catch blogu eklenecek*/
		try{
			currentItem = allTypeofCourses.get(currentRow);
			currentItem.updateTypeofCourse();
			allTypeofCourses.set(currentRow, currentItem);
			keys.clear();
			keys.add(currentRow);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
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
			System.out.println(e.getMessage());
		}
	}

	/*****************Sınıf Getter-Setter Metodları****************************/
	public List<TypeofCourse> getAllTypeofCourses() {
		synchronized (this) {
			allTypeofCourses = new ArrayList<TypeofCourse>();
			try {
				allTypeofCourses = currentItem.getAllTypeofCourses();
			} catch (Exception e) {
				System.out.println("!Load AllUsers Error: " + e.getMessage());
			}
		}
		return allTypeofCourses;
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
	
	/*******************Sınıf Alt Alanları***********************/
	private Integer typeofCourseId;
	private String typeofCourse;
	private TypeofCourse currentItem = new TypeofCourse();
	private Set<Integer> keys = new HashSet<Integer>();
	private int currentRow;
	private List<TypeofCourse> allTypeofCourses = new ArrayList<TypeofCourse>();
}
