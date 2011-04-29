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

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;


import entities.dao.Classroom;
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
	 
	
	private ArrayList<Schedule> courseList = new ArrayList<Schedule>();
	private ArrayList<Schedule> labList = new ArrayList<Schedule>();
	
	
	private List<SelectItem> listSemester = new ArrayList<SelectItem>();
	private List<SelectItem> listGrade = new ArrayList<SelectItem>();
	
	private int intGrade;
	private String semester;
	private String strGrade;
	private Object dragValue;
	public int currentYear;
	
	/////// Datatable values
	private String valueForDt11;
	private String valueForDt12;
	private String valueForDt13;
	private String valueForDt14;
	private String valueForDt15;
	private String valueForDt16;
	private String valueForDt17;
	private String valueForDt18;
	private String valueForDt21;
	private String valueForDt22;
	private String valueForDt23;
	private String valueForDt24;
	private String valueForDt25;
	private String valueForDt26;
	private String valueForDt27;
	private String valueForDt28;
	private String valueForDt31;
	private String valueForDt32;
	private String valueForDt33;
	private String valueForDt34;
	private String valueForDt35;
	private String valueForDt36;
	private String valueForDt37;
	private String valueForDt38;
	private String valueForDt41;
	private String valueForDt42;
	private String valueForDt43;
	private String valueForDt44;
	private String valueForDt45;
	private String valueForDt46;
	private String valueForDt47;
	private String valueForDt48;
	private String valueForDt51;
	private String valueForDt52;
	private String valueForDt53;
	private String valueForDt54;
	private String valueForDt55;
	private String valueForDt56;
	private String valueForDt57;
	private String valueForDt58;
	///////
	
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
	
	public String clickGetCoursesButton(){
		System.out.println("Get Course Button");
		allSyllabuses = null;
		allSyllabuses = getSyllabusBySemesterAndGrade();
		return null;
	}
	
	public void getIdAction(ActionEvent ev){
		//System.out.println("My event is:" + ev.getComponent().getId());
		String compId = ev.getComponent().getId().toString();
		
		int len = compId.length();
		componentIdtoDay = Integer.parseInt(compId.substring(len-2, len-1));
		componentIdtoHour = Integer.parseInt(compId.substring(len-1, len));
	
	}
	
	public void selectionChangedSemesterCombo(ValueChangeEvent evt){
		String selectedValue = (String) evt.getNewValue();
		
		if (!selectedValue.equals("")) {
			semester = selectedValue;
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
			 
			 System.out.println(intGrade);
		 }
	}
	
	private Schedule tempSchedule = new Schedule();
	public void processDrop(DropEvent event) {
		System.out.println("Bean.processDrop()");
		this.dragValue = (Schedule) event.getDragValue();
		tempSchedule = (Schedule) dragValue;
		
		//combodan ve üzerine geldiğimiz datatable dan aldığımız saat, sınıf ve semester bilgileriyle matrise eleman eklenmesi yapılacak
		//o saatte dersin yapılacağı sınıfın doluluk kontrolü, belirtilen hocanın o anda başka bir ders vermekte olup olmadığı kontrolleri de yapılacak! 
		
	}
	
	public void processDrag(DragEvent dragEvent) {
		System.out.println("Bean.processDrag()");
	}
	
	public String dragAction() {
		System.out.println("Bean.dragAction()");
		return null;
	}

	public String dropAction() {
		try {
			System.out.println("Bean.dropAction()");
			/*Course crs = (Course) dragValue;
			if(crs.getPrecondition().equals("Test")){
				this.containerCME.add(crs);
			}else if(crs.getPrecondition().equals("asd")){
				this.containerMSI.add(crs);
			}*/
			int timeofCourse = componentIdtoDay*8 + componentIdtoHour;
			tempSchedule.setTimeofCourse(timeofCourse);
			tempSchedule.setHours(1);
			
			if(intGrade == 1){
				firstGradeSchedule[componentIdtoHour][componentIdtoDay] = tempSchedule; 
			}else if(intGrade == 2){
				secondGradeSchedule[componentIdtoHour][componentIdtoDay] = tempSchedule;
			}else if(intGrade == 3){
				thirdGradeSchedule[componentIdtoHour][componentIdtoDay] = tempSchedule;
			}else if(intGrade == 4){
				fourthGradeSchedule[componentIdtoHour][componentIdtoDay] = tempSchedule;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		SeparateTheoricAndPraticCourse();
		System.out.println("getSyllabusBySemesterAndGrade");
		return allSyllabuses;
	}
	
	private void SeparateTheoricAndPraticCourse(){
		//Listelere sürekli ekleme yapmamak adıan listeleri yeniden oluşturuyoruz.
		courseList = new ArrayList<Schedule>();
		labList = new ArrayList<Schedule>();
		
		for(int i=0;i<allSyllabuses.size();i++){
			if(allSyllabuses.get(i).getCourse().getTeoricLectureHours() != 0){
				Schedule schedule = new Schedule();
				schedule.setSyllabus(allSyllabuses.get(i));
				schedule.setCourseType("theoric");
				String newName = allSyllabuses.get(i).getCourse().getCourseName();
				newName = newName + " (T)";
				schedule.setCourseTheoricOrPraticName(newName);
				courseList.add(schedule);
				System.out.println("getTeoricLectureHours");
			}
			
			if(allSyllabuses.get(i).getCourse().getPracticeLectureHourse() != 0){
				
				Schedule schedule = new Schedule();
				schedule.setSyllabus(allSyllabuses.get(i));
				schedule.setCourseType("practice");
				String newName = allSyllabuses.get(i).getCourse().getCourseName();
				newName = newName + " (P)";
				schedule.setCourseTheoricOrPraticName(newName);
				labList.add(schedule);
				System.out.println("getPracticeLectureHourse");
			}
		}
	}
	
	//// getters and setters
	

	public ArrayList<Schedule> getLabList() {
		return labList;
	}

	public ArrayList<Schedule> getCourseList() {
		return courseList;
	}

	
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

	public int getComponentIdtoDay() {
		return componentIdtoDay;
	}

	public void setComponentIdtoDay(int componentIdtoDay) {
		this.componentIdtoDay = componentIdtoDay;
	}

	public int getComponentIdtoHour() {
		return componentIdtoHour;
	}

	public void setComponentIdtoHour(int componentIdtoHour) {
		this.componentIdtoHour = componentIdtoHour;
	}

	public Syllabus getParamSyllabus() {
		return paramSyllabus;
	}

	public void setParamSyllabus(Syllabus paramSyllabus) {
		this.paramSyllabus = paramSyllabus;
	}

	public int getIntGrade() {
		return intGrade;
	}

	public void setIntGrade(int intGrade) {
		this.intGrade = intGrade;
	}
	
	// Getters and setters for the 40 datatable values
	
	public String getValueForDt11() {
			if(intGrade == 1){
				if(firstGradeSchedule[0][0] == null){
					valueForDt11 = "";
				}else{
					valueForDt11 = firstGradeSchedule[0][0].getSyllabus().getCourse().getCourseName();
				}
			}else if(intGrade == 2){
				valueForDt11 = secondGradeSchedule[0][0].getSyllabus().getCourse().getCourseName();
			}else if(intGrade == 3){
				valueForDt11 = thirdGradeSchedule[0][0].getSyllabus().getCourse().getCourseName();
			}else if(intGrade == 4){
				valueForDt11 = fourthGradeSchedule[0][0].getSyllabus().getCourse().getCourseName();
			}
			return valueForDt11;
	}

	public void setValueForDt11(String valueForDt11) {
		this.valueForDt11 = valueForDt11;
	}

	public String getValueForDt12() {
			if(intGrade == 1){
				if(firstGradeSchedule[0][1] == null){
					valueForDt12 = "";
				}else{
					valueForDt12 = firstGradeSchedule[0][1].getSyllabus().getCourse().getCourseName();
				}
			}else if(intGrade == 2){
				valueForDt12 = secondGradeSchedule[0][1].getSyllabus().getCourse().getCourseName();
			}else if(intGrade == 3){
				valueForDt12 = thirdGradeSchedule[0][1].getSyllabus().getCourse().getCourseName();
			}else if(intGrade == 4){
				valueForDt12 = fourthGradeSchedule[0][1].getSyllabus().getCourse().getCourseName();
			}
			return valueForDt12;
	}

	public void setValueForDt12(String valueForDt12) {
		this.valueForDt12 = valueForDt12;
	}

	public String getValueForDt13() {
		if(intGrade == 1){
			if(firstGradeSchedule[0][2] == null){
				valueForDt13 = "";
			}else{
				valueForDt13 = firstGradeSchedule[0][2].getSyllabus().getCourse().getCourseName();
			}
		}else if(intGrade == 2){
			valueForDt13 = secondGradeSchedule[0][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt13 = thirdGradeSchedule[0][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt13 = fourthGradeSchedule[0][2].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt13;
	}

	public void setValueForDt13(String valueForDt13) {
		this.valueForDt13 = valueForDt13;
	}

	public String getValueForDt14() {
		if(intGrade == 1){
			valueForDt14 = firstGradeSchedule[0][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt14 = secondGradeSchedule[0][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt14 = thirdGradeSchedule[0][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt14 = fourthGradeSchedule[0][3].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt14;
	}

	public void setValueForDt14(String valueForDt14) {
		this.valueForDt14 = valueForDt14;
	}

	public String getValueForDt15() {
		if(intGrade == 1){
			valueForDt15 = firstGradeSchedule[0][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt15 = secondGradeSchedule[0][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt15 = thirdGradeSchedule[0][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt15 = fourthGradeSchedule[0][4].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt15;
	}

	public void setValueForDt15(String valueForDt15) {
		this.valueForDt15 = valueForDt15;
	}

	public String getValueForDt16() {
		if(intGrade == 1){
			valueForDt16 = firstGradeSchedule[0][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt16 = secondGradeSchedule[0][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt16 = thirdGradeSchedule[0][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt16 = fourthGradeSchedule[0][5].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt16;
	}

	public void setValueForDt16(String valueForDt16) {
		this.valueForDt16 = valueForDt16;
	}

	public String getValueForDt17() {
		if(intGrade == 1){
			valueForDt17 = firstGradeSchedule[0][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt17 = secondGradeSchedule[0][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt17 = thirdGradeSchedule[0][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt17 = fourthGradeSchedule[0][6].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt17;
	}

	public void setValueForDt17(String valueForDt17) {
		this.valueForDt17 = valueForDt17;
	}

	public String getValueForDt18() {
		if(intGrade == 1){
			valueForDt18 = firstGradeSchedule[0][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt18 = secondGradeSchedule[0][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt18 = thirdGradeSchedule[0][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt18 = fourthGradeSchedule[0][7].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt18;
	}

	public void setValueForDt18(String valueForDt18) {
		this.valueForDt18 = valueForDt18;
	}

	public String getValueForDt21() {
		if(intGrade == 1){
			valueForDt21 = firstGradeSchedule[1][0].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt21 = secondGradeSchedule[1][0].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt21 = thirdGradeSchedule[1][0].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt21 = fourthGradeSchedule[1][0].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt21;
	}

	public void setValueForDt21(String valueForDt21) {
		this.valueForDt21 = valueForDt21;
	}

	public String getValueForDt22() {
		if(intGrade == 1){
			valueForDt22 = firstGradeSchedule[1][1].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt22 = secondGradeSchedule[1][1].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt22 = thirdGradeSchedule[1][1].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt22 = fourthGradeSchedule[1][1].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt22;
	}

	public void setValueForDt22(String valueForDt22) {
		this.valueForDt22 = valueForDt22;
	}

	public String getValueForDt23() {
		if(intGrade == 1){
			valueForDt23 = firstGradeSchedule[1][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt23 = secondGradeSchedule[1][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt23 = thirdGradeSchedule[1][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt23 = fourthGradeSchedule[1][2].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt23;
	}

	public void setValueForDt23(String valueForDt23) {
		this.valueForDt23 = valueForDt23;
	}

	public String getValueForDt24() {
		if(intGrade == 1){
			valueForDt24 = firstGradeSchedule[1][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt24 = secondGradeSchedule[1][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt24 = thirdGradeSchedule[1][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt24 = fourthGradeSchedule[1][3].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt24;
	}

	public void setValueForDt24(String valueForDt24) {
		this.valueForDt24 = valueForDt24;
	}

	public String getValueForDt25() {
		if(intGrade == 1){
			valueForDt25 = firstGradeSchedule[1][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt25 = secondGradeSchedule[1][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt25 = thirdGradeSchedule[1][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt25 = fourthGradeSchedule[1][4].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt25;
	}

	public void setValueForDt25(String valueForDt25) {
		this.valueForDt25 = valueForDt25;
	}

	public String getValueForDt26() {
		if(intGrade == 1){
			valueForDt26 = firstGradeSchedule[1][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt26 = secondGradeSchedule[1][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt26 = thirdGradeSchedule[1][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt26 = fourthGradeSchedule[1][5].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt26;
	}

	public void setValueForDt26(String valueForDt26) {
		this.valueForDt26 = valueForDt26;
	}

	public String getValueForDt27() {
		if(intGrade == 1){
			valueForDt27 = firstGradeSchedule[1][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt27 = secondGradeSchedule[1][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt27 = thirdGradeSchedule[1][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt27 = fourthGradeSchedule[1][6].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt27;
	}

	public void setValueForDt27(String valueForDt27) {
		this.valueForDt27 = valueForDt27;
	}

	public String getValueForDt28() {
		if(intGrade == 1){
			valueForDt28 = firstGradeSchedule[1][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt28 = secondGradeSchedule[1][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt28 = thirdGradeSchedule[1][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt28 = fourthGradeSchedule[1][7].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt28;
	}

	public void setValueForDt28(String valueForDt28) {
		this.valueForDt28 = valueForDt28;
	}

	public String getValueForDt31() {
		if(intGrade == 1){
			valueForDt31 = firstGradeSchedule[2][0].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt31 = secondGradeSchedule[2][0].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt31 = thirdGradeSchedule[2][0].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt31 = fourthGradeSchedule[2][0].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt31;
	}

	public void setValueForDt31(String valueForDt31) {
		this.valueForDt31 = valueForDt31;
	}

	public String getValueForDt32() {
		if(intGrade == 1){
			valueForDt32 = firstGradeSchedule[2][1].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt32 = secondGradeSchedule[2][1].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt32 = thirdGradeSchedule[2][1].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt32 = fourthGradeSchedule[2][1].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt32;
	}

	public void setValueForDt32(String valueForDt32) {
		this.valueForDt32 = valueForDt32;
	}

	public String getValueForDt33() {
		if(intGrade == 1){
			valueForDt33 = firstGradeSchedule[2][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt33 = secondGradeSchedule[2][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt33 = thirdGradeSchedule[2][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt33 = fourthGradeSchedule[2][2].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt33;
	}

	public void setValueForDt33(String valueForDt33) {
		this.valueForDt33 = valueForDt33;
	}

	public String getValueForDt34() {
		if(intGrade == 1){
			valueForDt34 = firstGradeSchedule[2][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt34 = secondGradeSchedule[2][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt34 = thirdGradeSchedule[2][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt34 = fourthGradeSchedule[2][3].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt34;
	}

	public void setValueForDt34(String valueForDt34) {
		this.valueForDt34 = valueForDt34;
	}

	public String getValueForDt35() {
		if(intGrade == 1){
			valueForDt35 = firstGradeSchedule[2][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt35 = secondGradeSchedule[2][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt35 = thirdGradeSchedule[2][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt35 = fourthGradeSchedule[2][4].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt35;
	}

	public void setValueForDt35(String valueForDt35) {
		this.valueForDt35 = valueForDt35;
	}

	public String getValueForDt36() {
		if(intGrade == 1){
			valueForDt36 = firstGradeSchedule[2][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt36 = secondGradeSchedule[2][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt36 = thirdGradeSchedule[2][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt36 = fourthGradeSchedule[2][5].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt36;
	}

	public void setValueForDt36(String valueForDt36) {
		this.valueForDt36 = valueForDt36;
	}

	public String getValueForDt37() {
		if(intGrade == 1){
			valueForDt37 = firstGradeSchedule[2][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt37 = secondGradeSchedule[2][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt37 = thirdGradeSchedule[2][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt37 = fourthGradeSchedule[2][6].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt37;
	}

	public void setValueForDt37(String valueForDt37) {
		this.valueForDt37 = valueForDt37;
	}

	public String getValueForDt38() {
		if(intGrade == 1){
			valueForDt38 = firstGradeSchedule[2][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt38 = secondGradeSchedule[2][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt38 = thirdGradeSchedule[2][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt38 = fourthGradeSchedule[2][7].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt38;
	}

	public void setValueForDt38(String valueForDt38) {
		this.valueForDt38 = valueForDt38;
	}

	public String getValueForDt41() {
		if(intGrade == 1){
			valueForDt41 = firstGradeSchedule[3][0].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt41 = secondGradeSchedule[3][0].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt41 = thirdGradeSchedule[3][0].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt41 = fourthGradeSchedule[3][0].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt41;
	}

	public void setValueForDt41(String valueForDt41) {
		this.valueForDt41 = valueForDt41;
	}

	public String getValueForDt42() {
		if(intGrade == 1){
			valueForDt42 = firstGradeSchedule[3][1].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt42 = secondGradeSchedule[3][1].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt42 = thirdGradeSchedule[3][1].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt42 = fourthGradeSchedule[3][1].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt42;
	}

	public void setValueForDt42(String valueForDt42) {
		this.valueForDt42 = valueForDt42;
	}

	public String getValueForDt43() {
		if(intGrade == 1){
			valueForDt43 = firstGradeSchedule[3][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt43 = secondGradeSchedule[3][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt43 = thirdGradeSchedule[3][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt43 = fourthGradeSchedule[3][2].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt43;
	}

	public void setValueForDt43(String valueForDt43) {
		this.valueForDt43 = valueForDt43;
	}

	public String getValueForDt44() {
		if(intGrade == 1){
			valueForDt44 = firstGradeSchedule[3][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt44 = secondGradeSchedule[3][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt44 = thirdGradeSchedule[3][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt44 = fourthGradeSchedule[3][3].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt44;
	}

	public void setValueForDt44(String valueForDt44) {
		this.valueForDt44 = valueForDt44;
	}

	public String getValueForDt45() {
		if(intGrade == 1){
			valueForDt45 = firstGradeSchedule[3][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt45 = secondGradeSchedule[3][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt45 = thirdGradeSchedule[3][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt45 = fourthGradeSchedule[3][4].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt45;
	}

	public void setValueForDt45(String valueForDt45) {
		this.valueForDt45 = valueForDt45;
	}

	public String getValueForDt46() {
		if(intGrade == 1){
			valueForDt46 = firstGradeSchedule[3][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt46 = secondGradeSchedule[3][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt46 = thirdGradeSchedule[3][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt46 = fourthGradeSchedule[3][5].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt46;
	}

	public void setValueForDt46(String valueForDt46) {
		this.valueForDt46 = valueForDt46;
	}

	public String getValueForDt47() {
		if(intGrade == 1){
			valueForDt47 = firstGradeSchedule[3][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt47 = secondGradeSchedule[3][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt47 = thirdGradeSchedule[3][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt47 = fourthGradeSchedule[3][6].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt47;
	}

	public void setValueForDt47(String valueForDt47) {
		this.valueForDt47 = valueForDt47;
	}

	public String getValueForDt48() {
		if(intGrade == 1){
			valueForDt48 = firstGradeSchedule[3][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt48 = secondGradeSchedule[3][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt48 = thirdGradeSchedule[3][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt48 = fourthGradeSchedule[3][7].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt48;
	}

	public void setValueForDt48(String valueForDt48) {
		this.valueForDt48 = valueForDt48;
	}

	public String getValueForDt51() {
		if(intGrade == 1){
			valueForDt51 = firstGradeSchedule[4][0].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt51 = secondGradeSchedule[4][0].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt51 = thirdGradeSchedule[4][0].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt51 = fourthGradeSchedule[4][0].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt51;
	}

	public void setValueForDt51(String valueForDt51) {
		this.valueForDt51 = valueForDt51;
	}

	public String getValueForDt52() {
		if(intGrade == 1){
			valueForDt52 = firstGradeSchedule[4][1].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt52 = secondGradeSchedule[4][1].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt52 = thirdGradeSchedule[4][1].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt52 = fourthGradeSchedule[4][1].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt52;
	}

	public void setValueForDt52(String valueForDt52) {
		this.valueForDt52 = valueForDt52;
	}

	public String getValueForDt53() {
		if(intGrade == 1){
			valueForDt53 = firstGradeSchedule[4][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt53 = secondGradeSchedule[4][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt53 = thirdGradeSchedule[4][2].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt53 = fourthGradeSchedule[4][2].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt53;
	}

	public void setValueForDt53(String valueForDt53) {
		this.valueForDt53 = valueForDt53;
	}

	public String getValueForDt54() {
		if(intGrade == 1){
			valueForDt54 = firstGradeSchedule[4][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt54 = secondGradeSchedule[4][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt54 = thirdGradeSchedule[4][3].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt54 = fourthGradeSchedule[4][3].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt54;
	}

	public void setValueForDt54(String valueForDt54) {
		this.valueForDt54 = valueForDt54;
	}

	public String getValueForDt55() {
		if(intGrade == 1){
			valueForDt55 = firstGradeSchedule[4][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt55 = secondGradeSchedule[4][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt55 = thirdGradeSchedule[4][4].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt55 = fourthGradeSchedule[4][4].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt55;
	}

	public void setValueForDt55(String valueForDt55) {
		this.valueForDt55 = valueForDt55;
	}

	public String getValueForDt56() {
		if(intGrade == 1){
			valueForDt56 = firstGradeSchedule[4][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt56 = secondGradeSchedule[4][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt56 = thirdGradeSchedule[4][5].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt56 = fourthGradeSchedule[4][5].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt56;
	}

	public void setValueForDt56(String valueForDt56) {
		this.valueForDt56 = valueForDt56;
	}

	public String getValueForDt57() {
		if(intGrade == 1){
			valueForDt57 = firstGradeSchedule[4][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt57 = secondGradeSchedule[4][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt57 = thirdGradeSchedule[4][6].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt57 = fourthGradeSchedule[4][6].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt57;
	}

	public void setValueForDt57(String valueForDt57) {
		this.valueForDt57 = valueForDt57;
	}

	public String getValueForDt58() {
		if(intGrade == 1){
			valueForDt58 = firstGradeSchedule[4][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 2){
			valueForDt58 = secondGradeSchedule[4][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 3){
			valueForDt58 = thirdGradeSchedule[4][7].getSyllabus().getCourse().getCourseName();
		}else if(intGrade == 4){
			valueForDt58 = fourthGradeSchedule[4][7].getSyllabus().getCourse().getCourseName();
		}
		return valueForDt58;
	}

	public void setValueForDt58(String valueForDt58) {
		this.valueForDt58 = valueForDt58;
	}
	
}
