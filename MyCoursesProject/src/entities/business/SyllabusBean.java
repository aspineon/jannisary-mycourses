package entities.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.dao.Course;
import entities.dao.Syllabus;

public class SyllabusBean {
	
	
	
	
	public void store(){
		
	}
	
	public void delete(){
		
	}
	
	public void addSyllabus(){
		
	}
	
    public int getLecturerId() {
		return lecturerId;
	}
	public void setLecturerId(int lecturerId) {
		this.lecturerId = lecturerId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
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
	public ArrayList<String> getCourseCodeList() {
		synchronized (this) {
			if (courseCodeList == null) {
				courseCodeList = new ArrayList<String>();
					try {
						int i;
						for(i=0; i<allSyllabusList.size(); i++){
							int courseId = allSyllabusList.get(i).getCourseId();
							course.setCourseId(courseId);
							String courseCode = course.getCourseCodeById().get(0);
							courseCodeList.add(courseCode);
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
	public void setCourseCodeList(ArrayList<String> courseNameList) {
		this.courseCodeList = courseNameList;
	}
	public ArrayList<String> getLecturerNameList() {
		return lecturerNameList;
	}
	public void setLecturerNameList(ArrayList<String> lecturerNameList) {
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
	
	private int lecturerId;
    private int courseId;
    private Integer syllabusId;
    private int year;
    private String semester;
    private Integer sectionNo;
    
    private Course course = new Course();
    private int currentRow;
    private Set<Integer> keys = new HashSet<Integer>();
    private Syllabus currentItem = new Syllabus();
    private List<Syllabus> allSyllabusList = null;
    private ArrayList<String> courseCodeList = null;
    private ArrayList<String> lecturerNameList = null;
}
