package entities.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.richfaces.event.DragEvent;
import org.richfaces.event.DropEvent;


import entities.dao.Course;
import entities.dao.Schedule;
import entities.dao.Syllabus;



public class ManuelSchedulingUtilBean {
	
	
	
	private int timeof_Course;
	private int hours;
	private List<Syllabus> allSyllabuses = null;
	private int componentIdtoDay;
	private int componentIdtoHour; 
	private Syllabus paramSyllabus = new Syllabus();//dao.Syllabus sınıfında sorgu yapabilmek için oluşturuldu.
	
	private Schedule[][] firstGradeSchedule = new Schedule[5][8];
	private Schedule[][] secondGradeSchedule = new Schedule[5][8];
	private Schedule[][] thirdGradeSchedule = new Schedule[5][8];
	private Schedule[][] fourthGradeSchedule = new Schedule[5][8];
	
	
	private List<SelectItem> listSemester = new ArrayList<SelectItem>();
	private List<SelectItem> listGrade = new ArrayList<SelectItem>();
	
	private int intGrade;
	private String semester;
	private String strGrade;
	private Object dragValue;
	public int currentYear;
	
	public ManuelSchedulingUtilBean(){
		super();
		
		
		listGrade.add(new SelectItem("FirstYear"));
		listGrade.add(new SelectItem("SecondYear"));
		listGrade.add(new SelectItem("ThirdYear"));
		listGrade.add(new SelectItem("FourthYear"));
		
		listSemester.add(new SelectItem("Fall"));
		listSemester.add(new SelectItem("Spring"));
		
		/*Başlangıçta Syllabus verilerini almak için (Course List tablosunu
		 * doldurmak için) grade ve semester değerleri ilkleniyor.
		 * */
		intGrade = 1;
		semester="Fall";
		currentYear = calculateYear();
	}
	
	public static int calculateYear(){
		
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int year = c.get(Calendar.YEAR);
		
		return year;
	}
	
	public void getIdAction(ActionEvent ev){
		//System.out.println("My event is:" + ev.getComponent().getId());
		String compId = ev.getSource().toString();
		componentIdtoDay = Integer.parseInt(compId.substring(0, 1));
		componentIdtoHour = Integer.parseInt(compId.substring(1, 2));
	
	}
	
	public void selectionChangedSemesterCombo(ValueChangeEvent evt){
		String selectedValue = (String) evt.getNewValue();
		
		if (!selectedValue.equals("")) {
			semester = selectedValue;
			if(intGrade != 0){
				//call getSyllabusBySemesterAndGrade
			}
		}
		
	    System.out.println(semester);
	}
	
	public void selectionChangedGradeCombo(ValueChangeEvent evt){
		String selectedValue = (String) evt.getNewValue();
		
		
		 if (!selectedValue.equals("")) {
			 if(selectedValue.equals("FirstYear")){
				 intGrade = 1;
			 }else if(selectedValue.equals("SecondYear")){
				 intGrade = 2;
			 }else if(selectedValue.equals("ThirdYear")){
				 intGrade = 3;
			 }else if(selectedValue.equals("FourthYear")){
				 intGrade = 4;
			 }
			 
			 if(!semester.equals("")){
				 
				 //call getSyllabusBySemesterAndGrade
			 }
			 
			 System.out.println(intGrade);
		 }
	}
	
	public void processDrop(DropEvent event) {
		System.out.println("Bean.processDrop()");
		this.dragValue = (Syllabus) event.getDragValue();
		Syllabus syl = (Syllabus) dragValue;
		
		//combodan ve üzerine geldiğimiz datatable dan aldığımız saat, sınıf ve semester bilgileriyle matrise eleman eklenmesi yapılacak
		//o saatte dersin yapılacağı sınıfın doluluk kontrolü, belirtilen hocanın o anda başka bir ders vermekte olup olmadığı kontrolleri de yapılacak! 
		/*if(){
			
		}*/
	}
	
	public void processDrag(DragEvent dragEvent) {
		System.out.println("Bean.processDrag()");
	}
	
	public String dragAction() {
		System.out.println("Bean.dragAction()");
		return null;
	}

	public String dropAction() {
		System.out.println("Bean.dropAction()");
		/*Course crs = (Course) dragValue;
		if(crs.getPrecondition().equals("Test")){
			this.containerCME.add(crs);
		}else if(crs.getPrecondition().equals("asd")){
			this.containerMSI.add(crs);
		}*/
		return null;
	}
	
	
	public List<Syllabus> getSyllabusBySemesterAndGrade() {
		
		synchronized (this) {
			if (allSyllabuses == null) {
				allSyllabuses = new ArrayList<Syllabus>();
					try {
						Course paramCourse= new Course();
						paramCourse.setGrade(intGrade);
						paramSyllabus.setCourse(paramCourse);
						paramSyllabus.setSemester(semester);
						paramSyllabus.setYear(currentYear);
						allSyllabuses = paramSyllabus.getSyllabusBySemesterAndGrade();
						
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllSyllabus Error: "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}
		return allSyllabuses;
	}
	
	
	//// getters and setters
	
	
	
	public int getTimeof_Course() {
		return timeof_Course;
	}

	public void setTimeof_Course(int timeof_Course) {
		this.timeof_Course = timeof_Course;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public List<Syllabus> getAllSyllabuses() {
		allSyllabuses = getSyllabusBySemesterAndGrade();
		return allSyllabuses;
	}
	public void setAllSyllabuses(List<Syllabus> allSyllabuses) {
		this.allSyllabuses = allSyllabuses;
	}
	public Schedule[][] getFirstGradeSchedule() {
		return firstGradeSchedule;
	}
	public void setFirstGradeSchedule(Schedule[][] firstGradeSchedule) {
		this.firstGradeSchedule = firstGradeSchedule;
	}
	public Schedule[][] getSecondGradeSchedule() {
		return secondGradeSchedule;
	}
	public void setSecondGradeSchedule(Schedule[][] secondGradeSchedule) {
		this.secondGradeSchedule = secondGradeSchedule;
	}
	public Schedule[][] getThirdGradeSchedule() {
		return thirdGradeSchedule;
	}
	public void setThirdGradeSchedule(Schedule[][] thirdGradeSchedule) {
		this.thirdGradeSchedule = thirdGradeSchedule;
	}
	public Schedule[][] getFourthGradeSchedule() {
		return fourthGradeSchedule;
	}
	public void setFourthGradeSchedule(Schedule[][] fourthGradeSchedule) {
		this.fourthGradeSchedule = fourthGradeSchedule;
	}

	public Object getDragValue() {
		return dragValue;
	}

	public void setDragValue(Object dragValue) {
		this.dragValue = dragValue;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	
	public List<SelectItem> getListSemester() {
		return listSemester;
	}

	public void setListSemester(List<SelectItem> listSemester) {
		this.listSemester = listSemester;
	}

	public List<SelectItem> getListGrade() {
		return listGrade;
	}

	public void setListGrade(List<SelectItem> listGrade) {
		this.listGrade = listGrade;
	}

	public String getStrGrade() {
		return strGrade;
	}

	public void setStrGrade(String strGrade) {
		this.strGrade = strGrade;
	}
	
}
