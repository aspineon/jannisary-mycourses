package entities.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.richfaces.event.DragEvent;
import org.richfaces.event.DropEvent;

import entities.dao.Course;
import entities.dao.Department;
import entities.dao.Schedule;
import entities.dao.Syllabus;

public class ManuelSchedulingUtilBean {
	private int timeof_Course;
	private int hours;
	private List<Syllabus> allSyllabuses = null;
	private int componentIdtoDay;
	private int componentIdtoHour; 
	
	private Schedule[][] firstGradeSchedule = new Schedule[5][8];
	private Schedule[][] secondGradeSchedule = new Schedule[5][8];
	private Schedule[][] thirdGradeSchedule = new Schedule[5][8];
	private Schedule[][] fourthGradeSchedule = new Schedule[5][8];
	
	private List<SelectItem> listCourseGrade = new ArrayList<SelectItem>();
	private int grade;
	private String semester;
	
	private Object dragValue;
	
	public ManuelSchedulingUtilBean(){
		super();
		listCourseGrade.add(new SelectItem("FirstYear-Fall"));
		listCourseGrade.add(new SelectItem("FirstYear-Spring"));
		listCourseGrade.add(new SelectItem("SecondYear-Fall"));
		listCourseGrade.add(new SelectItem("SecondYear-Spring"));
		listCourseGrade.add(new SelectItem("ThirdYear-Fall"));
		listCourseGrade.add(new SelectItem("ThirdYear-Spring"));
		listCourseGrade.add(new SelectItem("FourthYear-Fall"));
		listCourseGrade.add(new SelectItem("FourthYear-Spring"));
	}
	
	public void getIdAction(ActionEvent ev){
		//System.out.println("My event is:" + ev.getComponent().getId());
		String compId = ev.getSource().toString();
		componentIdtoDay = Integer.parseInt(compId.substring(0, 1));
		componentIdtoHour = Integer.parseInt(compId.substring(1, 2));
	
	}
	
	public void selectionChangedGradeCombo(ValueChangeEvent evt){
		String selectedValue = (String) evt.getNewValue();
		
		
		 if (!selectedValue.equals("")) {
			 if(selectedValue.substring(0, 2).equals("Fi")){
				 grade = 1;
			 }else if(selectedValue.substring(0, 1).equals("S")){
				 grade = 2;
			 }else if(selectedValue.substring(0, 1).equals("T")){
				 grade = 3;
			 }else if(selectedValue.substring(0, 1).equals("Fo")){
				 grade = 4;
			 }
			 StringTokenizer st = new StringTokenizer(selectedValue, "-");
			 st.nextToken();
			 semester = st.nextToken();			 
		 }
	}
	
	/*public void enumForGrade(Grade grd){
		switch (grd) {
			case FirstYear: System.out.println("Mondays are bad.");
					     break;
					
			case SecondYear: System.out.println("Fridays are better.");
					     break;
					     
			case ThirdYear:
			case FourthYear: System.out.println("Weekends are best.");
					     break;
					     
			default:	 System.out.println("Midweek days are so-so.");
					     break;
		}
				
	}
	
	public enum Grade {
		FirstYear, SecondYear, ThirdYear, FourthYear
	}*/
	
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
	
	
}
