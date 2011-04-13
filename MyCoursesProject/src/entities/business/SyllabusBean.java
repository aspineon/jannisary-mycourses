package entities.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;

import entities.dao.Course;
import entities.dao.Lecturer;
import entities.dao.Syllabus;

public class SyllabusBean {
	
	
	
	
	public void store(){
		
	}
	
	public void delete(){
		
	}
	
	public void addSyllabus(){
		
	}
	
	public Integer getSyllabusId() {
		return syllabusId;
	}
	public void setSyllabusId(Integer syllabusId) {
		this.syllabusId = syllabusId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public Integer getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(Integer sectionNo) {
		this.sectionNo = sectionNo;
	}
	public List<Syllabus> getAllSyllabusList() {
		/*
		 * ayni anda iki veya daha fazla thread tarafindan calistirilmamasi istenen metotlara verilen keyword.
		 *  synchonized ve statik olmayan bir metot calistirilirken nesneleri kilitlenir,
		 *  metodun sonunda tekrar unlock edilir
		 * */
		synchronized (this) {
			if (allSyllabusList == null) {
				allSyllabusList = new ArrayList<Syllabus>();
					try {
						allSyllabusList = currentItem.getAllSyllabus() ;
						
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllSyllabus Error: "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}
		return allSyllabusList;
	}
	
	public void setAllSyllabusList(List<Syllabus> allSyllabusList) {
		this.allSyllabusList = allSyllabusList;
	}
	/*Bug:
	 * Farklı 2 hoca aynı dersi vermesi durumunda aynı ders kodu comboBox içerisinde 2 kez gösteriliyor.
	 * Course Code 2 kez gösterilmemeli, bug çözülmeli.
	 * */
	public ArrayList<SelectItem> getCourseCodeList() {
		synchronized (this) {
			if (courseCodeList == null) {
				courseCodeList = new ArrayList<SelectItem>();
					try {
						int i;
						for(i=0; i<allSyllabusList.size(); i++){
							String strCourseCode = allSyllabusList.get(i).getCourse().getCourseCode();
							courseCodeList.add(new SelectItem(strCourseCode));
						}
						
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllSyllabus Error: "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}
		
		return courseCodeList;
	}
	public void setCourseCodeList(ArrayList<SelectItem> courseNameList) {
		this.courseCodeList = courseNameList;
	}
	public ArrayList<SelectItem> getLecturerNameList() {
		
		synchronized (this) {
			if (lecturerNameList == null) {
				lecturerNameList = new ArrayList<SelectItem>();
					try {
						int i;
						for(i=0; i<allSyllabusList.size(); i++){
							String strLecturerName = allSyllabusList.get(i).getLecturer().getLecturerName();
							lecturerNameList.add(new SelectItem(strLecturerName));
						}
						
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllSyllabusBean:getLecturerNameList Error: "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}
		
		return lecturerNameList;
	}
	public void setLecturerNameList(ArrayList<SelectItem> lecturerNameList) {
		this.lecturerNameList = lecturerNameList;
	}
	
	public Set<Integer> getKeys() {
		return keys;
	}

	public void setKeys(Set<Integer> keys) {
		this.keys = keys;
	}
	
	public Syllabus getCurrentItem(){
		return currentItem;
	}
	
	public void setCurrentItem(Syllabus currentItem){
		this.currentItem = currentItem;
	}
	
	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}
	
	
    private Integer syllabusId;
    private int year;
    private String semester;
    private Integer sectionNo;
    
    private Course course = new Course();
    private Lecturer lecturer = new Lecturer();
    private int currentRow;
    private Set<Integer> keys = new HashSet<Integer>();
    private Syllabus currentItem = new Syllabus();
    private List<Syllabus> allSyllabusList = null;
    private ArrayList<SelectItem> courseCodeList = null;
    private ArrayList<SelectItem> lecturerNameList = null;
}
