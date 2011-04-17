package entities.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import entities.dao.Course;
import entities.dao.Lecturer;
import entities.dao.Syllabus;

public class SyllabusBean {
	
	
	public void selectionChangedLecturerEditCombo(ValueChangeEvent  evt) {
		 String selectedLecturerName = (String) evt.getNewValue();
		 
		 lecturer.setLecturerName(selectedLecturerName);
		 //Integer intLecturerId = lecturer.getIdByLecturerName().get(0).getLecturerId();
		 
		 lecturer = new Lecturer(lecturer.getIdByLecturerName().get(0));
		 
		 if (!selectedLecturerName.equals("")) {
			 currentItem.setLecturer(lecturer);
		 }
	}
	
	public void selectionChangedCourseEditCombo(ValueChangeEvent  evt) {
		 String selectedCourseCode = (String) evt.getNewValue();
		 
		 course.setCourseCode(selectedCourseCode);
//		 Integer intCourseId = course.getIdByCourseCode().get(0).getCourseId();

		 course = new Course(course.getIdByCourseCode().get(0));
		 
		 if (!selectedCourseCode.equals("")) {
			 currentItem.setCourse(course);
		 }
	}
	
	public void selectionChangedLectureAddCombo(ValueChangeEvent  evt) {
		 String selectedLectureName = (String) evt.getNewValue();

		 lecturer.setLecturerName(selectedLectureName);
		 Integer intLecturerId = lecturer.getIdByLecturerName().get(0).getLecturerId();
		 
		 if (!selectedLectureName.equals("")) {
			 lecturer.setLecturerId(intLecturerId);
			 currentItem.setLecturer(lecturer);
		 }
	}
	
	public void selectionChangedCourseAddCombo(ValueChangeEvent  evt) {
		 String selectedCourseCode = (String) evt.getNewValue();
		 
		 course.setCourseCode(selectedCourseCode);
		 Integer intCourseId = course.getIdByCourseCode().get(0).getCourseId();
		 
		 if (!selectedCourseCode.equals("")) {
			 course.setCourseId(intCourseId);
			 currentItem.setCourse(course);
		 }
	}
	
	
	public void store(){
		/*try-catch blogu eklenecek*/
		try{
			currentItem = allSyllabusList.get(currentRow);
			currentItem.setCourse(course);
			currentItem.setLecturer(lecturer);
			currentItem.updateSyllabus();
			//getAllSyllabusList Ã§aÄŸrÄ±larak gÃ¼ncellenen veriler alÄ±nÄ±yor.
			//getAllSyllabusList();
			allSyllabusList.set(currentRow, currentItem);
			keys.clear();
			keys.add(currentRow);
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}
	
	public void delete(){
		try{
		currentItem = allSyllabusList.get(currentRow);
		currentItem.deleteSyllabus();
		allSyllabusList.remove(currentItem);
	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public String addSyllabus(){
		try{
			
			
			int size = allSyllabusList.size();		
			
			
			Syllabus syllabus = new Syllabus(currentItem);
			allSyllabusList.add(size,syllabus);
			syllabus.addSyllabus();
			keys.clear();
			keys.add(allSyllabusList.size());
			/*SysUser.jsp'de yer alan User Name ve User Password alanlarÄ±nÄ± temizle*/
			//currentItem.
			//currentItem.setUserPassword("");
			
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
		return null;
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
                                            //Course nesnelerini al.
                                            List<Course> courseList = course.getAllCourses();
                                            int i;
                                            for(i=0; i<courseList.size(); i++){
                                                    String strCourseCode = courseList.get(i).getCourseCode();
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
		List<Lecturer> lecturerList = lecturer.getAllLecturer();
		synchronized (this) {
			if (lecturerNameList == null) {
				lecturerNameList = new ArrayList<SelectItem>();
					try {
						int i;
						for(i=0; i<lecturerList.size(); i++){
							String strLecturerName = lecturerList.get(i).getLecturerName();
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
