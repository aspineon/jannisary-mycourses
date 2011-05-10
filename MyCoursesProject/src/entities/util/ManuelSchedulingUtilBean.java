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

import entities.business.ScheduleBean;
import entities.dao.Course;
import entities.dao.Schedule;
import entities.dao.Syllabus;
import entities.utility.ExcelPOI;

public class ManuelSchedulingUtilBean {
	
	/*******************************ManualSchedulingUtilBean alt alanları********************/
	
	private int hours;//Schedule tablosunun Hours ile ilgili alanına veri atmayı sağlayan değişken.
	private int componentIdtoDay;// Sürükle-Bırak işlemi sırasında Schedule tablosunun Timeof_Course alanına atılacak verinin hesaplanması için gün bilgisini belirlendiği değişken
	private int componentIdtoHour;// Sürükle-Bırak işlemi sırasında Schedule tablosunun Timeof_Course alanına atılacak verinin hesaplanması için saat bilgisini belirlendiği değişken
	private Syllabus paramSyllabus = new Syllabus();//dao.Syllabus sınıfında sorgu yapabilmek için oluşturuldu.
	private Schedule paramSchedule = new Schedule();
	private BasicScheduleUtilBean tempBasicScheduleItem = new BasicScheduleUtilBean();
	/*
	 ************************* 1.,2.,3.,4. sınıflar için matris tanımları *****************
	 */
	private BasicScheduleUtilBean[][] firstGradeSchedule = new BasicScheduleUtilBean[5][8];
	private BasicScheduleUtilBean[][] secondGradeSchedule = new BasicScheduleUtilBean[5][8];
	private BasicScheduleUtilBean[][] thirdGradeSchedule = new BasicScheduleUtilBean[5][8];
	private BasicScheduleUtilBean[][] fourthGradeSchedule = new BasicScheduleUtilBean[5][8];
	
	/* 
	 * Ara yüzde dersler Theorik ve pratik olarak 2 ayrı listede gösteriliyor. 
	 * Bu dersleri arkaplan da tuttuğumuz listeler.
	 *  */
	private ArrayList<BasicScheduleUtilBean> courseList = new ArrayList<BasicScheduleUtilBean>();
	private ArrayList<BasicScheduleUtilBean> labList = new ArrayList<BasicScheduleUtilBean>();
	
	private List<Syllabus> allSyllabuses = null;
	private List<BasicScheduleUtilBean> allBasicScheduleItems = new ArrayList<BasicScheduleUtilBean>();
	private List<Schedule> allRealScheduleItems = null;
	
	/* Semester ve Grade combobox ları için tutulan listeler */
	private List<SelectItem> listSemester = new ArrayList<SelectItem>();
	private List<SelectItem> listGrade = new ArrayList<SelectItem>();
	private List<SelectItem> listYear = new ArrayList<SelectItem>();
	private List<SelectItem> listDay = new ArrayList<SelectItem>();
	private List<SelectItem> listHour = new ArrayList<SelectItem>();
	private List<SelectItem> listNextTwoYearsToSave = new ArrayList<SelectItem>();
	private List<SelectItem> listSemesterToSave = new ArrayList<SelectItem>();
	private List<SelectItem> listArchiveToSave = new ArrayList<SelectItem>();
	
	private int intGrade; //intGrade storeProcedure'a parametre olarak geçirilen sınıf değişkeni.
	private String semester; //semeter storeProcedure'e parametre olarak geçirilen sınıf değişkeni.
	private String strGrade; //strGrade comboboxdan String olarak gelen verilerin karşılaştırmasının yapılması ne intGrade'e seçimin yansıtılmasını sağlayan değişken.
	private Object dragValue; //Sürükle bırak işleminde sürüklenen nesnenin bilgilerini tutan nesne.
	private int selectedYearForEdit;// Edit yapmak istediğinde bu değişkeni kullanacağız
	public int currentYear; //İlgili yıl bilgilerinin edinildiği alt alan
	private String errorLabel; //Sınıf(Classroom) ve Hoca(Lecturer) bilgileri ile kontrol yapıldıktan sonra hatanın yansıtıldığı label.
	private String dayForReset;
	private int hourForReset;
	private int editOrAddFlag;
	private int savedYear;
	private String savedSemester;
	private String savedVersionName;
	private String savedArchiveComboSelect;
	
    

	/*
     * Arayüzde yer alan herbir saat dilimi hücresinde sürükle bırak işlemi ardından etkilenen isim alanlarını
     * gösteren değişkenler.
     *  */
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
	
	/******Sınıf yapıcı metodu*************/
	public ManuelSchedulingUtilBean(){
		super();
		currentYear = calculateYear();
		listGrade.add(new SelectItem("FirstYear"));
		listGrade.add(new SelectItem("SecondYear"));
		listGrade.add(new SelectItem("ThirdYear"));
		listGrade.add(new SelectItem("FourthYear"));
		
		listSemester.add(new SelectItem("Fall"));
		listSemester.add(new SelectItem("Spring"));
		
		listArchiveToSave.add(new SelectItem("Yes"));
		listArchiveToSave.add(new SelectItem("No"));
		
		listYear.add(new SelectItem(Integer.toString(currentYear)));
		listYear.add(new SelectItem(Integer.toString(currentYear-1)));
		listYear.add(new SelectItem(Integer.toString(currentYear-2)));
		listYear.add(new SelectItem(Integer.toString(currentYear-3)));
		listYear.add(new SelectItem(Integer.toString(currentYear-4)));
		listYear.add(new SelectItem(Integer.toString(currentYear-5)));
		listYear.add(new SelectItem(Integer.toString(currentYear-6)));
		listYear.add(new SelectItem(Integer.toString(currentYear-7)));
		listYear.add(new SelectItem(Integer.toString(currentYear-8)));
		listYear.add(new SelectItem(Integer.toString(currentYear-9)));
		listYear.add(new SelectItem(Integer.toString(currentYear-10)));
		
		listDay.add(new SelectItem("Monday"));
		listDay.add(new SelectItem("Tuesday"));
		listDay.add(new SelectItem("Wednesday"));
		listDay.add(new SelectItem("Thursday"));
		listDay.add(new SelectItem("Friday"));
		
		listNextTwoYearsToSave.add(new SelectItem(currentYear));
		listNextTwoYearsToSave.add(new SelectItem(currentYear+1));
		
		listSemesterToSave.add(new SelectItem("Fall"));
		listSemesterToSave.add(new SelectItem("Spring"));
		
		for(int k=1;k<9;k++){
			listHour.add(new SelectItem(k));
		}
		
		
		
		/*Başlangıçta Syllabus verilerini almak için (Course List tablosunu
		 * doldurmak için) grade ve semester değerleri ilkleniyor.
		 * */
		intGrade = 1;
		semester="Fall";
		
		
		fillMatrix();
	}
	
	/****************Arayüz etkileşiminde kullanılan sınıf Olayları(Actions)**********************/
	
	public void getIdAction(ActionEvent ev){
		//System.out.println("My event is:" + ev.getComponent().getId());
		String compId = ev.getComponent().getId().toString();
		
		int len = compId.length();
		componentIdtoDay = Integer.parseInt(compId.substring(len-2, len-1));
		componentIdtoHour = Integer.parseInt(compId.substring(len-1, len));
	
	}
	
	public String clickGetCoursesButton() throws Exception{
		System.out.println("Get Course Button");
		editOrAddFlag = 0;// Add
		allSyllabuses = null;
		allBasicScheduleItems = null;
		allRealScheduleItems = null;
		allSyllabuses = getSyllabusBySemesterAndGrade();
		return null;
	}
	
	public String clickGetCoursesButtonForEdit() throws Exception{
		System.out.println("Get Course Button For Edit");
		editOrAddFlag = 1;//Edit
		allSyllabuses = null;
		allBasicScheduleItems = null;
		allRealScheduleItems = null;
		allSyllabuses = getSyllabusBySemesterAndGradeAndYear();
		allRealScheduleItems = getScheduleBySemesterAndGradeAndYear();
		this.fillMatrixForEditOperation();
		return null;
	}

	public String clickResetCoordinateButton() throws Exception{
		System.out.println("Reset Coordinate Button");
		this.resetSelectedCoordinate();
		return null;
	}
	
	public String clickSave(){
		if(editOrAddFlag==0){
			/*if check box selected*/
			if(savedArchiveComboSelect.equals("Yes")){
				int intSyllabusArchiveId = -1;
				ExcelPOI excel = new ExcelPOI();
				excel.generateFreshmanSheet(convertExcelBasicScheduleMatrixToStringMatrix(firstGradeSchedule));
				excel.generateSophomoreSheet(convertExcelBasicScheduleMatrixToStringMatrix(secondGradeSchedule));
				excel.generateJuniorSheet(convertExcelBasicScheduleMatrixToStringMatrix(thirdGradeSchedule));
				excel.generateSeniorSheet(convertExcelBasicScheduleMatrixToStringMatrix(fourthGradeSchedule));
				
				String strYear = "" + savedYear;
				intSyllabusArchiveId = excel.writeToExcelPOI(strYear , savedSemester, savedVersionName);
				
				setSyllabusArchiveIdToScheduleMatrix(intSyllabusArchiveId);
				
			}//end of add syllabusArchive if
			if(savedArchiveComboSelect.equals("No")){
				setSyllabusArchiveIdToScheduleMatrix(1);
			}
			saveMatrix();
		}else if(editOrAddFlag==1){
			setMatrixSyllabusYearAndSemester();
			updateMatrix();
		}
		/*resetMatrix();
		fillMatrix();*/
		return null;
	} 	
	
	public void selectionChangedSemesterCombo(ValueChangeEvent evt){
		String selectedValue = (String) evt.getNewValue();
		
		if (!selectedValue.equals("")) {
			semester = selectedValue;
		}
		
	    System.out.println(semester);
	}
	
	public void selectionChangedSavedSemesterCombo(ValueChangeEvent evt){
		String selectedValue = (String) evt.getNewValue();
		
		if (!selectedValue.equals("")) {
			savedSemester = selectedValue;
		}
		
	    System.out.println(savedSemester);
	}
	
	public void selectionChangedSavedArchiveCombo(ValueChangeEvent evt){
		String selectedValue = (String) evt.getNewValue();
		
		if (!selectedValue.equals("")) {
			 savedArchiveComboSelect = selectedValue;
		}
		else{
			savedArchiveComboSelect="";
			}
	    System.out.println(savedArchiveComboSelect);
	}
	
	public void selectionChangedSavedYearCombo(ValueChangeEvent evt){
		String selectedValue = (String) evt.getNewValue();
		
		if (!selectedValue.equals("")) {
			savedYear = Integer.parseInt(selectedValue);
		}
		
	    System.out.println(savedYear);
	}
	
	public void selectionChangedDayComboForReset(ValueChangeEvent evt){
		String selectedValue = (String) evt.getNewValue();
		
		if (!selectedValue.equals("")) {
			dayForReset = selectedValue;
		}
		
	    System.out.println(dayForReset);
	}
	
	public void selectionChangedHourComboForReset(ValueChangeEvent evt){
		String selectedValue = (String) evt.getNewValue();
		
		if (!selectedValue.equals("")) {
			hourForReset = Integer.parseInt(selectedValue);
		}
		
	    System.out.println(hourForReset);
	}
	
	public void selectionChangedYearComboForEdit(ValueChangeEvent evt){
		String selectedValue = (String) evt.getNewValue();
		
		if (!selectedValue.equals("")) {
			selectedYearForEdit = Integer.parseInt(selectedValue);
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
	
	
	public void processDrag(DragEvent dragEvent) {
		System.out.println("Bean.processDrag()");
	}
	
	
	public void processDrop(DropEvent event) {
		try {
			System.out.println("Bean.processDrop()");
			tempBasicScheduleItem = (BasicScheduleUtilBean) event.getDragValue();
			System.out.println("lecturer name in processDrop: " + tempBasicScheduleItem.getCourseTheoricOrPraticName());
			//this.dragValue = (Object) event.getDragValue();
			//tempBasicScheduleItem = (BasicScheduleUtilBean) dragValue;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in processDrop!!!!!******");
		}
		
	}	
	
	public String dragAction() {
		System.out.println("Bean.dragAction()");
		return null;
	}
	
	
	/*Sürüklenen verinin ilgili saat hücresinde sınıf ve hoca kontrolleri yapıldıktan sonra matrislere atıldığı metod tanımı*/
	public String dropAction() {
		try {
			System.out.println("Bean.dropAction()");
			int timeofCourse;
			timeofCourse = componentIdtoDay*8 + componentIdtoHour + 1;
			
			
			System.out.println(tempBasicScheduleItem.getClassroomId());
			System.out.println(tempBasicScheduleItem.getLecturerName());
			System.out.println(tempBasicScheduleItem.getSyllabusId());
			
			
			if(intGrade == 1){
				System.out.println("in first grade if!!");
				if(tempBasicScheduleItem.getClassroomId() != secondGradeSchedule[componentIdtoDay][componentIdtoHour].getClassroomId() &&
					tempBasicScheduleItem.getClassroomId() != thirdGradeSchedule[componentIdtoDay][componentIdtoHour].getClassroomId() &&
					tempBasicScheduleItem.getClassroomId() != fourthGradeSchedule[componentIdtoDay][componentIdtoHour].getClassroomId() &&
				   !tempBasicScheduleItem.getLecturerName().equals(secondGradeSchedule[componentIdtoDay][componentIdtoHour].getLecturerName()) &&
				   !tempBasicScheduleItem.getLecturerName().equals(thirdGradeSchedule[componentIdtoDay][componentIdtoHour].getLecturerName()) &&
				   !tempBasicScheduleItem.getLecturerName().equals(fourthGradeSchedule[componentIdtoDay][componentIdtoHour].getLecturerName()))
					{
					System.out.println("success in first grade if");
						//tempBasicScheduleItem = firstGradeSchedule[componentIdtoDay][componentIdtoHour];
						tempBasicScheduleItem.setTimeofCourse(timeofCourse);
						tempBasicScheduleItem.setHours(1);
						firstGradeSchedule[componentIdtoDay][componentIdtoHour] = tempBasicScheduleItem; 
					}else{
						errorLabel = "There is a classroom conflict at " + componentIdtoHour + ", " + componentIdtoDay + " index with first class lesson";
					}
			}else if(intGrade == 2){
				if(tempBasicScheduleItem.getClassroomId() != firstGradeSchedule[componentIdtoDay][componentIdtoHour].getClassroomId() &&
						tempBasicScheduleItem.getClassroomId() != thirdGradeSchedule[componentIdtoDay][componentIdtoHour].getClassroomId() &&
						tempBasicScheduleItem.getClassroomId() != fourthGradeSchedule[componentIdtoDay][componentIdtoHour].getClassroomId()&&
					   !tempBasicScheduleItem.getLecturerName().equals(firstGradeSchedule[componentIdtoDay][componentIdtoHour].getLecturerName()) &&
					   !tempBasicScheduleItem.getLecturerName().equals(thirdGradeSchedule[componentIdtoDay][componentIdtoHour].getLecturerName()) &&
					   !tempBasicScheduleItem.getLecturerName().equals(fourthGradeSchedule[componentIdtoDay][componentIdtoHour].getLecturerName()))
						{
							//tempBasicScheduleItem = secondGradeSchedule[componentIdtoDay][componentIdtoHour];
							tempBasicScheduleItem.setTimeofCourse(timeofCourse);
							tempBasicScheduleItem.setHours(1);
							secondGradeSchedule[componentIdtoDay][componentIdtoHour] = tempBasicScheduleItem;
						}else{
							errorLabel = "There is a classroom conflict at " + componentIdtoHour + ", " + componentIdtoDay + " index with second class lesson";
						}
			}else if(intGrade == 3){
				if(tempBasicScheduleItem.getClassroomId() != firstGradeSchedule[componentIdtoDay][componentIdtoHour].getClassroomId() &&
						tempBasicScheduleItem.getClassroomId() != secondGradeSchedule[componentIdtoDay][componentIdtoHour].getClassroomId() &&
						tempBasicScheduleItem.getClassroomId() != fourthGradeSchedule[componentIdtoDay][componentIdtoHour].getClassroomId()&&
					   !tempBasicScheduleItem.getLecturerName().equals(secondGradeSchedule[componentIdtoDay][componentIdtoHour].getLecturerName()) &&
					   !tempBasicScheduleItem.getLecturerName().equals(firstGradeSchedule[componentIdtoDay][componentIdtoHour].getLecturerName()) &&
					   !tempBasicScheduleItem.getLecturerName().equals(fourthGradeSchedule[componentIdtoDay][componentIdtoHour].getLecturerName()))
						{
							//tempBasicScheduleItem = thirdGradeSchedule[componentIdtoDay][componentIdtoHour];
							tempBasicScheduleItem.setTimeofCourse(timeofCourse);
							tempBasicScheduleItem.setHours(1);
							thirdGradeSchedule[componentIdtoDay][componentIdtoHour] = tempBasicScheduleItem;
						}else{
							errorLabel = "There is a classroom conflict at " + componentIdtoHour + ", " + componentIdtoDay + " index with third class lesson";
						}
			}else if(intGrade == 4){
				if(tempBasicScheduleItem.getClassroomId() != firstGradeSchedule[componentIdtoDay][componentIdtoHour].getClassroomId() &&
						tempBasicScheduleItem.getClassroomId() != thirdGradeSchedule[componentIdtoDay][componentIdtoHour].getClassroomId() &&
						tempBasicScheduleItem.getClassroomId() != secondGradeSchedule[componentIdtoDay][componentIdtoHour].getClassroomId()&&
					   !tempBasicScheduleItem.getLecturerName().equals(secondGradeSchedule[componentIdtoDay][componentIdtoHour].getLecturerName()) &&
					   !tempBasicScheduleItem.getLecturerName().equals(thirdGradeSchedule[componentIdtoDay][componentIdtoHour].getLecturerName()) &&
					   !tempBasicScheduleItem.getLecturerName().equals(firstGradeSchedule[componentIdtoDay][componentIdtoHour].getLecturerName()))
						{
							//tempBasicScheduleItem = fourthGradeSchedule[componentIdtoDay][componentIdtoHour];
							tempBasicScheduleItem.setTimeofCourse(timeofCourse);
							tempBasicScheduleItem.setHours(1);
							fourthGradeSchedule[componentIdtoDay][componentIdtoHour] = tempBasicScheduleItem;
						}else{
							errorLabel = "There is a classroom conflict at " + componentIdtoHour + ", " + componentIdtoDay + " index with fourth class lesson";
						}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in dropAction!!!!*********");
		}
		return null;
	}
	
	
	/*****************************Sınıf içinde kullanılan metodlar*************************/
	private void fillMatrix(){
		BasicScheduleUtilBean bs = new BasicScheduleUtilBean();
		bs.setClassroomId(-1);
		bs.setLecturerName("Lecturer");
		
		try {
			for(int i=0;i<5;i++){
				for(int j=0;j<8;j++){
					firstGradeSchedule[i][j] = bs;
					secondGradeSchedule[i][j] = bs;
					thirdGradeSchedule[i][j] = bs;
					fourthGradeSchedule[i][j] = bs;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setMatrixSyllabusYearAndSemester(){
		if(intGrade==1){
			for(int i=0;i<5;i++){
				for(int j=0;j<8;j++){
					firstGradeSchedule[i][j].setYear(savedYear);
					firstGradeSchedule[i][j].setSemester(savedSemester);
				}
			}
		}else if(intGrade==2){
			for(int i=0;i<5;i++){
				for(int j=0;j<8;j++){
					secondGradeSchedule[i][j].setYear(savedYear);
					secondGradeSchedule[i][j].setSemester(savedSemester);
				}
			}
		}else if(intGrade==3){
			for(int i=0;i<5;i++){
				for(int j=0;j<8;j++){
					thirdGradeSchedule[i][j].setYear(savedYear);
					thirdGradeSchedule[i][j].setSemester(savedSemester);
				}
			}
		}else if(intGrade==4){
			for(int i=0;i<5;i++){
				for(int j=0;j<8;j++){
					fourthGradeSchedule[i][j].setYear(savedYear);
					fourthGradeSchedule[i][j].setSemester(savedSemester);
				}
			}
		}
	}
	
	public void resetSelectedCoordinate(){
		BasicScheduleUtilBean bs = new BasicScheduleUtilBean();
		bs.setClassroomId(-1);
		bs.setLecturerName("Lecturer");
		
		int day=0;
		if(dayForReset.equals("Monday")){
			day = 0;
		}else if(dayForReset.equals("Tuesday")){
			day = 1;
		}else if(dayForReset.equals("Wednesday")){
			day = 2;
		}else if(dayForReset.equals("Thursday")){
			day = 3;
		}else if(dayForReset.equals("Friday")){
			day = 4;
		}
		
		if(intGrade==1){
			firstGradeSchedule[day][hourForReset-1] = bs;
		}else if(intGrade == 2){
			secondGradeSchedule[day][hourForReset-1] = bs;
		}else if(intGrade == 3){
			thirdGradeSchedule[day][hourForReset-1] = bs;
		}else if(intGrade == 4){
			fourthGradeSchedule[day][hourForReset-1] = bs;
		}
	}
	
	private void fillMatrixForEditOperation(){
		if(allRealScheduleItems.size() != 0){
			int quotient;
			int remainder;
			BasicScheduleUtilBean bs = null;
			if(intGrade==1){
				for(int i=0;i<allRealScheduleItems.size();i++){
					bs = new BasicScheduleUtilBean();
					quotient = allRealScheduleItems.get(i).getTimeofCourse();
					remainder = quotient % 8;
					quotient = (int) Math.floor(quotient/8);
					bs.setClassroomId(allRealScheduleItems.get(i).getSyllabus().getClassroom().getClassroomId());
					bs.setCourseName(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseName());
					bs.setHours(1);
					bs.setLecturerName(allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerName());
					bs.setSyllabusId(allRealScheduleItems.get(i).getSyllabus().getSyllabusId());
					bs.setTimeofCourse(allRealScheduleItems.get(i).getTimeofCourse());
					bs.setCourseType(allRealScheduleItems.get(i).getCourseType());
					bs.setScheduleId(allRealScheduleItems.get(i).getScheduleId());
					bs.setCourseId(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseId());
					bs.setLecturerId(allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerId());
					bs.setSectionNo(allRealScheduleItems.get(i).getSyllabus().getSectionNo());
					
					if(allRealScheduleItems.get(i).getCourseType().equals("theoric")){
						bs.setCourseTheoricOrPraticName(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseName() + "(T), {" + allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerName() + "}");
					}else if(allRealScheduleItems.get(i).getCourseType().equals("practice")){
						bs.setCourseTheoricOrPraticName(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseName() + "(P), {" + allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerName() + "}");
					}
					if(remainder == 0){
						firstGradeSchedule[quotient-1][7]  = bs;
						
					}else{
						firstGradeSchedule[quotient][remainder-1] = bs;
					}
				}
			}else if(intGrade==2){
				for(int i=0;i<allRealScheduleItems.size();i++){
					bs = new BasicScheduleUtilBean();
					quotient = allRealScheduleItems.get(i).getTimeofCourse();
					remainder = quotient % 8;
					quotient = (int) Math.floor(quotient/8);
					bs.setClassroomId(allRealScheduleItems.get(i).getSyllabus().getClassroom().getClassroomId());
					bs.setCourseName(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseName());
					bs.setHours(1);
					bs.setLecturerName(allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerName());
					bs.setSyllabusId(allRealScheduleItems.get(i).getSyllabus().getSyllabusId());
					bs.setTimeofCourse(allRealScheduleItems.get(i).getTimeofCourse());
					bs.setCourseType(allRealScheduleItems.get(i).getCourseType());
					bs.setScheduleId(allRealScheduleItems.get(i).getScheduleId());
					bs.setCourseId(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseId());
					bs.setLecturerId(allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerId());
					bs.setSectionNo(allRealScheduleItems.get(i).getSyllabus().getSectionNo());
					
					if(allRealScheduleItems.get(i).getCourseType().equals("theoric")){
						bs.setCourseTheoricOrPraticName(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseName() + "(T), {" + allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerName() + "}");
					}else if(allRealScheduleItems.get(i).getCourseType().equals("practice")){
						bs.setCourseTheoricOrPraticName(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseName() + "(P), {" + allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerName() + "}");
					}
					if(remainder == 0){
						secondGradeSchedule[quotient-1][7]  = bs;
						
					}else{
						secondGradeSchedule[quotient][remainder-1] = bs;
					}
				}
			}if(intGrade==3){
				for(int i=0;i<allRealScheduleItems.size();i++){
					bs = new BasicScheduleUtilBean();
					quotient = allRealScheduleItems.get(i).getTimeofCourse();
					remainder = quotient % 8;
					quotient = (int) Math.floor(quotient/8);
					bs.setClassroomId(allRealScheduleItems.get(i).getSyllabus().getClassroom().getClassroomId());
					bs.setCourseName(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseName());
					bs.setHours(1);
					bs.setLecturerName(allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerName());
					bs.setSyllabusId(allRealScheduleItems.get(i).getSyllabus().getSyllabusId());
					bs.setTimeofCourse(allRealScheduleItems.get(i).getTimeofCourse());
					bs.setCourseType(allRealScheduleItems.get(i).getCourseType());
					bs.setScheduleId(allRealScheduleItems.get(i).getScheduleId());
					bs.setCourseId(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseId());
					bs.setLecturerId(allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerId());
					bs.setSectionNo(allRealScheduleItems.get(i).getSyllabus().getSectionNo());
					
					if(allRealScheduleItems.get(i).getCourseType().equals("theoric")){
						bs.setCourseTheoricOrPraticName(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseName() + "(T), {" + allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerName() + "}");
					}else if(allRealScheduleItems.get(i).getCourseType().equals("practice")){
						bs.setCourseTheoricOrPraticName(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseName() + "(P), {" + allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerName() + "}");
					}
					if(remainder == 0){
						thirdGradeSchedule[quotient-1][7]  = bs;
						
					}else{
						thirdGradeSchedule[quotient][remainder-1] = bs;
					}
				}
			}else if(intGrade==4){
				for(int i=0;i<allRealScheduleItems.size();i++){
					bs = new BasicScheduleUtilBean();
					quotient = allRealScheduleItems.get(i).getTimeofCourse();
					remainder = quotient % 8;
					quotient = (int) Math.floor(quotient/8);
					bs.setClassroomId(allRealScheduleItems.get(i).getSyllabus().getClassroom().getClassroomId());
					bs.setCourseName(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseName());
					bs.setHours(1);
					bs.setLecturerName(allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerName());
					bs.setSyllabusId(allRealScheduleItems.get(i).getSyllabus().getSyllabusId());
					bs.setTimeofCourse(allRealScheduleItems.get(i).getTimeofCourse());
					bs.setCourseType(allRealScheduleItems.get(i).getCourseType());
					bs.setScheduleId(allRealScheduleItems.get(i).getScheduleId());
					bs.setCourseId(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseId());
					bs.setLecturerId(allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerId());
					bs.setSectionNo(allRealScheduleItems.get(i).getSyllabus().getSectionNo());
					
					if(allRealScheduleItems.get(i).getCourseType().equals("theoric")){
						bs.setCourseTheoricOrPraticName(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseName() + "(T), {" + allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerName() + "}");
					}else if(allRealScheduleItems.get(i).getCourseType().equals("practice")){
						bs.setCourseTheoricOrPraticName(allRealScheduleItems.get(i).getSyllabus().getCourse().getCourseName() + "(P), {" + allRealScheduleItems.get(i).getSyllabus().getLecturer().getLecturerName() + "}");
					}
					if(remainder == 0){
						fourthGradeSchedule[quotient-1][7]  = bs;
						
					}else{
						fourthGradeSchedule[quotient][remainder-1] = bs;
					}
				}//end of for
			}//end of else if
		}//end of first if
	}
	
	private int calculateYear(){
		int year=-1;
		
		try {
			Date d = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			year = c.get(Calendar.YEAR);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return year;
	}
		
	public List<Syllabus> getSyllabusBySemesterAndGrade() {
		
		synchronized (this) {
			if (allSyllabuses == null) {
				allSyllabuses = new ArrayList<Syllabus>();
				allBasicScheduleItems = new ArrayList<BasicScheduleUtilBean>();
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
		transformSyllabusesToBasicScheduleItems();
		separateTheoricAndPraticCourse();
		System.out.println("getSyllabusBySemesterAndGrade");
		return allSyllabuses;
	}
	
	public List<Syllabus> getSyllabusBySemesterAndGradeAndYear() {
		
		synchronized (this) {
			if (allSyllabuses == null) {
				allSyllabuses = new ArrayList<Syllabus>();
				allBasicScheduleItems = new ArrayList<BasicScheduleUtilBean>();
					try {
						Course paramCourse= new Course();
						paramCourse.setGrade(intGrade);
						paramSyllabus.setCourse(paramCourse);
						paramSyllabus.setSemester(semester);
						paramSyllabus.setYear(selectedYearForEdit);
						allSyllabuses = paramSyllabus.getSyllabusBySemesterAndGrade();
						
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllSyllabus Error: "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}
		transformSyllabusesToBasicScheduleItems();
		separateTheoricAndPraticCourse();
		System.out.println("getSyllabusBySemesterAndGrade");
		return allSyllabuses;
	}
	
	public List<Schedule> getScheduleBySemesterAndGradeAndYear() {
		
		synchronized (this) {
			if (allRealScheduleItems == null) {
				allRealScheduleItems = new ArrayList<Schedule>();
					try {
						Course paramCourse= new Course();
						paramCourse.setGrade(intGrade);
						paramSyllabus.setCourse(paramCourse);
						paramSyllabus.setSemester(semester);
						paramSyllabus.setYear(selectedYearForEdit);
						paramSchedule.setSyllabus(paramSyllabus);
						allRealScheduleItems = paramSchedule.getScheduleBySemesterAndGradeAndYear();
						
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllSchedules Error: "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}
		return allRealScheduleItems;
	}
	
	private List<BasicScheduleUtilBean> transformSyllabusesToBasicScheduleItems(){
		try {
			int sizeofAllSyllabusesList = allSyllabuses.size();
			
			for(int i=0;i<sizeofAllSyllabusesList;i++){
				BasicScheduleUtilBean bsub = new BasicScheduleUtilBean();
				bsub.setCourseName(allSyllabuses.get(i).getCourse().getCourseName());
				bsub.setPracticeHours(allSyllabuses.get(i).getCourse().getPracticeLectureHourse());
				bsub.setTeoricHours(allSyllabuses.get(i).getCourse().getTeoricLectureHours());
				bsub.setLecturerName(allSyllabuses.get(i).getLecturer().getLecturerName());
				bsub.setClassroomId(allSyllabuses.get(i).getClassroom().getClassroomId());
				bsub.setSyllabusId(allSyllabuses.get(i).getSyllabusId());
				bsub.setCourseId(allSyllabuses.get(i).getCourse().getCourseId());
				bsub.setLecturerId(allSyllabuses.get(i).getLecturer().getLecturerId());
				bsub.setSectionNo(allSyllabuses.get(i).getSectionNo());
				
				allBasicScheduleItems.add(bsub);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allBasicScheduleItems;
	}
	
	private void separateTheoricAndPraticCourse(){
		//Listelere sürekli ekleme yapmamak adına listeleri yeniden oluşturuyoruz.
		courseList = new ArrayList<BasicScheduleUtilBean>();
		labList = new ArrayList<BasicScheduleUtilBean>();
		
		try {
			for(int i=0;i<allBasicScheduleItems.size();i++){
				if(allBasicScheduleItems.get(i).getTeoricHours() != 0){
					BasicScheduleUtilBean bs = new BasicScheduleUtilBean();
					bs.setCourseType("theoric");
					String newName = allBasicScheduleItems.get(i).getCourseName();
					newName = newName + " (T), {" + allBasicScheduleItems.get(i).getLecturerName() + "}";
					bs.setCourseTheoricOrPraticName(newName);
					bs.setClassroomId(allBasicScheduleItems.get(i).getClassroomId());
					bs.setCourseName(allBasicScheduleItems.get(i).getCourseName());
					bs.setLecturerName(allBasicScheduleItems.get(i).getLecturerName());
					bs.setSyllabusId(allBasicScheduleItems.get(i).getSyllabusId());
					bs.setTimeofCourse(allBasicScheduleItems.get(i).getTimeofCourse());
					bs.setCourseId(allBasicScheduleItems.get(i).getCourseId());
					bs.setLecturerId(allBasicScheduleItems.get(i).getLecturerId());
					bs.setSectionNo(allBasicScheduleItems.get(i).getSectionNo());
					
					courseList.add(bs);
					System.out.println("getTeoricLectureHours");
				}
				
				if(allBasicScheduleItems.get(i).getPracticeHours() != 0){
					BasicScheduleUtilBean bs = new BasicScheduleUtilBean();
					bs.setCourseType("practice");
					String newName = allBasicScheduleItems.get(i).getCourseName();
					newName = newName + "(P), {" + allBasicScheduleItems.get(i).getLecturerName() + "}";
					bs.setCourseTheoricOrPraticName(newName);
					bs.setClassroomId(allBasicScheduleItems.get(i).getClassroomId());
					bs.setCourseName(allBasicScheduleItems.get(i).getCourseName());
					bs.setLecturerName(allBasicScheduleItems.get(i).getLecturerName());
					bs.setSyllabusId(allBasicScheduleItems.get(i).getSyllabusId());
					bs.setTimeofCourse(allBasicScheduleItems.get(i).getTimeofCourse());
					bs.setCourseId(allBasicScheduleItems.get(i).getCourseId());
					bs.setLecturerId(allBasicScheduleItems.get(i).getLecturerId());
					bs.setSectionNo(allBasicScheduleItems.get(i).getSectionNo());
					
					labList.add(bs);
					System.out.println("getPracticeLectureHourse");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void resetMatrix(){
		
		firstGradeSchedule = null;
		secondGradeSchedule = null;
		thirdGradeSchedule = null;
		fourthGradeSchedule = null;
		
		firstGradeSchedule = new BasicScheduleUtilBean[5][8];
	    secondGradeSchedule = new BasicScheduleUtilBean[5][8];
		thirdGradeSchedule = new BasicScheduleUtilBean[5][8];
		fourthGradeSchedule = new BasicScheduleUtilBean[5][8];
	}
	
	private void updateMatrix(){
		ScheduleBean scheduleBean = new ScheduleBean();
		scheduleBean.updateSchedule(firstGradeSchedule, secondGradeSchedule, thirdGradeSchedule, fourthGradeSchedule);
	}
	
	private void saveMatrix(){
		ScheduleBean scheduleBean = new ScheduleBean();
		scheduleBean.addSchedule(firstGradeSchedule, secondGradeSchedule, thirdGradeSchedule, fourthGradeSchedule);
	}
	/*
	 * Schedule nesnelerini archive ile 
	 * */
	private void setSyllabusArchiveIdToScheduleMatrix(int intSyllabusArchiveId){
		for(int i=0;i<5;i++){
			for(int j = 0; j < 8; j++){
				firstGradeSchedule[i][j].setSyllabusArchiveId(intSyllabusArchiveId);
				secondGradeSchedule[i][j].setSyllabusArchiveId(intSyllabusArchiveId);
				thirdGradeSchedule[i][j].setSyllabusArchiveId(intSyllabusArchiveId);
				fourthGradeSchedule[i][j].setSyllabusArchiveId(intSyllabusArchiveId);
			}
		}
	}
	
	/*
	 * Excel raporlamasında kullanılmak üzere ExcelPOI içindeki metotodlara dönüştürme işlemleri
	 * */
	private String [][] convertExcelBasicScheduleMatrixToStringMatrix(BasicScheduleUtilBean [][] paramMatrix){
		String matrix[][] = new String [6][9];
		matrix[0][0] = "Hours / Days";
		matrix[0][1] = "Monday";
		matrix[0][2] = "Tuesday";
		matrix[0][3] = "Wednesday";
		matrix[0][4] = "Thursday";
		matrix[0][5] = "Friday";
		
		try {
			for(int i=1;i<5;i++){
				for(int j = 0; j < 8; j++){
					matrix[i][j] = paramMatrix[i-1][j].getCourseName();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matrix;
	}
	
	/* Sınıf alt lanlarının Getters and Setters*/
	public ArrayList<BasicScheduleUtilBean> getLabList() {
		return labList;
	}

	public ArrayList<BasicScheduleUtilBean> getCourseList() {
		return courseList;
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
	public BasicScheduleUtilBean[][] getFirstGradeSchedule() {
		return firstGradeSchedule;
	}
	public void setFirstGradeSchedule(BasicScheduleUtilBean[][] firstGradeSchedule) {
		this.firstGradeSchedule = firstGradeSchedule;
	}
	public BasicScheduleUtilBean[][] getSecondGradeSchedule() {
		return secondGradeSchedule;
	}
	public void setSecondGradeSchedule(BasicScheduleUtilBean[][] secondGradeSchedule) {
		this.secondGradeSchedule = secondGradeSchedule;
	}
	public BasicScheduleUtilBean[][] getThirdGradeSchedule() {
		return thirdGradeSchedule;
	}
	public void setThirdGradeSchedule(BasicScheduleUtilBean[][] thirdGradeSchedule) {
		this.thirdGradeSchedule = thirdGradeSchedule;
	}
	public BasicScheduleUtilBean[][] getFourthGradeSchedule() {
		return fourthGradeSchedule;
	}
	public void setFourthGradeSchedule(BasicScheduleUtilBean[][] fourthGradeSchedule) {
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
	
	public String getErrorLabel() {
		if(errorLabel==null){
			errorLabel = " ";
		}
		return errorLabel;
	}

	public void setErrorLabel(String errorLabel) {
		this.errorLabel = errorLabel;
	}
	
	public List<SelectItem> getListYear() {
		return listYear;
	}

	public void setListYear(List<SelectItem> listYear) {
		this.listYear = listYear;
	}
	
	public List<SelectItem> getListDay() {
		return listDay;
	}

	public void setListDay(List<SelectItem> listDay) {
		this.listDay = listDay;
	}

	public List<SelectItem> getListHour() {
		return listHour;
	}

	public void setListHour(List<SelectItem> listHour) {
		this.listHour = listHour;
	}
	
	public List<SelectItem> getListNextTwoYearsToSave() {
		return listNextTwoYearsToSave;
	}

	public void setListNextTwoYearsToSave(List<SelectItem> listNextTwoYearsToSave) {
		this.listNextTwoYearsToSave = listNextTwoYearsToSave;
	}

	public List<SelectItem> getListSemesterToSave() {
		return listSemesterToSave;
	}

	public void setListSemesterToSave(List<SelectItem> listSemesterToSave) {
		this.listSemesterToSave = listSemesterToSave;
	}

	public int getSavedYear() {
		return savedYear;
	}

	public void setSavedYear(int savedYear) {
		this.savedYear = savedYear;
	}

	public String getSavedSemester() {
		return savedSemester;
	}

	public void setSavedSemester(String savedSemester) {
		this.savedSemester = savedSemester;
	}
	
	// Getters and setters for the 40 datatable values

	

	public String getValueForDt11() {
			if(intGrade == 1){
				if(firstGradeSchedule[0][0].getClassroomId() == -1){
					valueForDt11 = "";
				}else{
					valueForDt11 = firstGradeSchedule[0][0].getCourseTheoricOrPraticName();
				}
			}else if(intGrade == 2){
				if(secondGradeSchedule[0][0].getClassroomId() == -1){
					valueForDt11 = "";
				}else{
					valueForDt11 = secondGradeSchedule[0][0].getCourseTheoricOrPraticName();
				}
				
			}else if(intGrade == 3){
				if(thirdGradeSchedule[0][0].getClassroomId() == -1){
					valueForDt11 = "";
				}else{
					valueForDt11 = thirdGradeSchedule[0][0].getCourseTheoricOrPraticName();
				}
			}else if(intGrade == 4){
				if(fourthGradeSchedule[0][0].getClassroomId() == -1){
					valueForDt11 = "";
				}else{
					valueForDt11 = fourthGradeSchedule[0][0].getCourseTheoricOrPraticName();
				}
			}
			return valueForDt11;
	}

	public void setValueForDt11(String valueForDt11) {
		this.valueForDt11 = valueForDt11;
	}

	public String getValueForDt12() {
			if(intGrade == 1){
				if(firstGradeSchedule[0][1].getClassroomId() == -1){
					valueForDt12 = "";
				}else{
					valueForDt12 = firstGradeSchedule[0][1].getCourseTheoricOrPraticName();
				}
			}else if(intGrade == 2){
				if(secondGradeSchedule[0][1].getClassroomId() == -1){
					valueForDt12 = "";
				}else{
					valueForDt12 = secondGradeSchedule[0][1].getCourseTheoricOrPraticName();
				}
				
			}else if(intGrade == 3){
				if(thirdGradeSchedule[0][1].getClassroomId() == -1){
					valueForDt12 = "";
				}else{
					valueForDt12 = thirdGradeSchedule[0][1].getCourseTheoricOrPraticName();
				}
			}else if(intGrade == 4){
				if(fourthGradeSchedule[0][1].getClassroomId() == -1){
					valueForDt12 = "";
				}else{
					valueForDt12 = fourthGradeSchedule[0][1].getCourseTheoricOrPraticName();
				}
			}
			return valueForDt12;
	}

	public void setValueForDt12(String valueForDt12) {
		this.valueForDt12 = valueForDt12;
	}

	public String getValueForDt13() {
		if(intGrade == 1){
			if(firstGradeSchedule[0][2].getClassroomId() == -1){
				valueForDt13 = "";
			}else{
				valueForDt13 = firstGradeSchedule[0][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[0][2].getClassroomId() == -1){
				valueForDt13 = "";
			}else{
				valueForDt13 = secondGradeSchedule[0][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[0][2].getClassroomId() == -1){
				valueForDt13 = "";
			}else{
				valueForDt13 = thirdGradeSchedule[0][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[0][2].getClassroomId() == -1){
				valueForDt13 = "";
			}else{
				valueForDt13 = fourthGradeSchedule[0][2].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt13;
	}

	public void setValueForDt13(String valueForDt13) {
		this.valueForDt13 = valueForDt13;
	}

	public String getValueForDt14() {
		if(intGrade == 1){
			if(firstGradeSchedule[0][3].getClassroomId() == -1){
				valueForDt14 = "";
			}else{
				valueForDt14 = firstGradeSchedule[0][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[0][3].getClassroomId() == -1){
				valueForDt14 = "";
			}else{
				valueForDt14 = secondGradeSchedule[0][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[0][3].getClassroomId() == -1){
				valueForDt14 = "";
			}else{
				valueForDt14 = thirdGradeSchedule[0][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[0][3].getClassroomId() == -1){
				valueForDt14 = "";
			}else{
				valueForDt14 = fourthGradeSchedule[0][3].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt14;
	}

	public void setValueForDt14(String valueForDt14) {
		this.valueForDt14 = valueForDt14;
	}

	public String getValueForDt15() {
		if(intGrade == 1){
			if(firstGradeSchedule[0][4].getClassroomId() == -1){
				valueForDt15 = "";
			}else{
				valueForDt15 = firstGradeSchedule[0][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[0][4].getClassroomId() == -1){
				valueForDt15 = "";
			}else{
				valueForDt15 = secondGradeSchedule[0][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[0][4].getClassroomId() == -1){
				valueForDt15 = "";
			}else{
				valueForDt15 = thirdGradeSchedule[0][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[0][4].getClassroomId() == -1){
				valueForDt15 = "";
			}else{
				valueForDt15 = fourthGradeSchedule[0][4].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt15;
	}

	public void setValueForDt15(String valueForDt15) {
		this.valueForDt15 = valueForDt15;
	}

	public String getValueForDt16() {
		if(intGrade == 1){
			if(firstGradeSchedule[0][5].getClassroomId() == -1){
				valueForDt16 = "";
			}else{
				valueForDt16 = firstGradeSchedule[0][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[0][5].getClassroomId() == -1){
				valueForDt16 = "";
			}else{
				valueForDt16 = secondGradeSchedule[0][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[0][5].getClassroomId() == -1){
				valueForDt16 = "";
			}else{
				valueForDt16 = thirdGradeSchedule[0][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[0][5].getClassroomId() == -1){
				valueForDt16 = "";
			}else{
				valueForDt16 = fourthGradeSchedule[0][5].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt16;
	}

	public void setValueForDt16(String valueForDt16) {
		this.valueForDt16 = valueForDt16;
	}

	public String getValueForDt17() {
		if(intGrade == 1){
			if(firstGradeSchedule[0][6].getClassroomId() == -1){
				valueForDt17 = "";
			}else{
				valueForDt17 = firstGradeSchedule[0][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[0][6].getClassroomId() == -1){
				valueForDt17 = "";
			}else{
				valueForDt17 = secondGradeSchedule[0][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[0][6].getClassroomId() == -1){
				valueForDt17 = "";
			}else{
				valueForDt17 = thirdGradeSchedule[0][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[0][6].getClassroomId() == -1){
				valueForDt17 = "";
			}else{
				valueForDt17 = fourthGradeSchedule[0][6].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt17;
	}

	public void setValueForDt17(String valueForDt17) {
		this.valueForDt17 = valueForDt17;
	}

	public String getValueForDt18() {
		if(intGrade == 1){
			if(firstGradeSchedule[0][7].getClassroomId() == -1){
				valueForDt18 = "";
			}else{
				valueForDt18 = firstGradeSchedule[0][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[0][7].getClassroomId() == -1){
				valueForDt18 = "";
			}else{
				valueForDt18 = secondGradeSchedule[0][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[0][7].getClassroomId() == -1){
				valueForDt18 = "";
			}else{
				valueForDt18 = thirdGradeSchedule[0][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[0][7].getClassroomId() == -1){
				valueForDt18 = "";
			}else{
				valueForDt18 = fourthGradeSchedule[0][7].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt18;
	}

	public void setValueForDt18(String valueForDt18) {
		this.valueForDt18 = valueForDt18;
	}

	public String getValueForDt21() {
		if(intGrade == 1){
			if(firstGradeSchedule[1][0].getClassroomId() == -1){
				valueForDt21 = "";
			}else{
				valueForDt21 = firstGradeSchedule[1][0].getCourseTheoricOrPraticName();
			}
			
		}else if(intGrade == 2){
			if(secondGradeSchedule[1][0].getClassroomId() == -1){
				valueForDt21 = "";
			}else{
				valueForDt21 = secondGradeSchedule[1][0].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[1][0].getClassroomId() == -1){
				valueForDt21 = "";
			}else{
				valueForDt21 = thirdGradeSchedule[1][0].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[1][0].getClassroomId() == -1){
				valueForDt21 = "";
			}else{
				valueForDt21 = fourthGradeSchedule[1][0].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt21;
	}

	public void setValueForDt21(String valueForDt21) {
		this.valueForDt21 = valueForDt21;
	}

	public String getValueForDt22() {
		if(intGrade == 1){
			if(firstGradeSchedule[1][1].getClassroomId() == -1){
				valueForDt22 = "";
			}else{
				valueForDt22 = firstGradeSchedule[1][1].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[1][1].getClassroomId() == -1){
				valueForDt22 = "";
			}else{
				valueForDt22 = secondGradeSchedule[1][1].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[1][1].getClassroomId() == -1){
				valueForDt22 = "";
			}else{
				valueForDt22 = thirdGradeSchedule[1][1].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[1][1].getClassroomId() == -1){
				valueForDt22 = "";
			}else{
				valueForDt22 = fourthGradeSchedule[1][1].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt22;
	}

	public void setValueForDt22(String valueForDt22) {
		this.valueForDt22 = valueForDt22;
	}

	public String getValueForDt23() {
		if(intGrade == 1){
			if(firstGradeSchedule[1][2].getClassroomId() == -1){
				valueForDt23 = "";
			}else{
				valueForDt23 = firstGradeSchedule[1][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[1][2].getClassroomId() == -1){
				valueForDt23 = "";
			}else{
				valueForDt23 = secondGradeSchedule[1][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[1][2].getClassroomId() == -1){
				valueForDt23 = "";
			}else{
				valueForDt23 = thirdGradeSchedule[1][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[1][2].getClassroomId() == -1){
				valueForDt23 = "";
			}else{
				valueForDt23 = fourthGradeSchedule[1][2].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt23;
	}

	public void setValueForDt23(String valueForDt23) {
		this.valueForDt23 = valueForDt23;
	}

	public String getValueForDt24() {
		if(intGrade == 1){
			if(firstGradeSchedule[1][3].getClassroomId() == -1){
				valueForDt24 = "";
			}else{
				valueForDt24 = firstGradeSchedule[1][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[1][3].getClassroomId() == -1){
				valueForDt24 = "";
			}else{
				valueForDt24 = secondGradeSchedule[1][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[1][3].getClassroomId() == -1){
				valueForDt24 = "";
			}else{
				valueForDt24 = thirdGradeSchedule[1][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[1][3].getClassroomId() == -1){
				valueForDt24 = "";
			}else{
				valueForDt24 = fourthGradeSchedule[1][3].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt24;
	}

	public void setValueForDt24(String valueForDt24) {
		this.valueForDt24 = valueForDt24;
	}

	public String getValueForDt25() {
		if(intGrade == 1){
			if(firstGradeSchedule[1][4].getClassroomId() == -1){
				valueForDt25 = "";
			}else{
				valueForDt25 = firstGradeSchedule[1][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[1][4].getClassroomId() == -1){
				valueForDt25 = "";
			}else{
				valueForDt25 = secondGradeSchedule[1][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[1][4].getClassroomId() == -1){
				valueForDt25 = "";
			}else{
				valueForDt25 = thirdGradeSchedule[1][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[1][4].getClassroomId() == -1){
				valueForDt25 = "";
			}else{
				valueForDt25 = fourthGradeSchedule[1][4].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt25;
	}

	public void setValueForDt25(String valueForDt25) {
		this.valueForDt25 = valueForDt25;
	}

	public String getValueForDt26() {
		if(intGrade == 1){
			if(firstGradeSchedule[1][5].getClassroomId() == -1){
				valueForDt26 = "";
			}else{
				valueForDt26 = firstGradeSchedule[1][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[1][5].getClassroomId() == -1){
				valueForDt26 = "";
			}else{
				valueForDt26 = secondGradeSchedule[1][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[1][5].getClassroomId() == -1){
				valueForDt26 = "";
			}else{
				valueForDt26 = thirdGradeSchedule[1][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[1][5].getClassroomId() == -1){
				valueForDt26 = "";
			}else{
				valueForDt26 = fourthGradeSchedule[1][5].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt26;
	}

	public void setValueForDt26(String valueForDt26) {
		this.valueForDt26 = valueForDt26;
	}

	public String getValueForDt27() {
		if(intGrade == 1){
			if(firstGradeSchedule[1][6].getClassroomId() == -1){
				valueForDt27 = "";
			}else{
				valueForDt27 = firstGradeSchedule[1][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[1][6].getClassroomId() == -1){
				valueForDt27 = "";
			}else{
				valueForDt27 = secondGradeSchedule[1][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[1][6].getClassroomId() == -1){
				valueForDt27 = "";
			}else{
				valueForDt27 = thirdGradeSchedule[1][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[1][6].getClassroomId() == -1){
				valueForDt27 = "";
			}else{
				valueForDt27 = fourthGradeSchedule[1][6].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt27;
	}

	public void setValueForDt27(String valueForDt27) {
		this.valueForDt27 = valueForDt27;
	}

	public String getValueForDt28() {
		if(intGrade == 1){
			if(firstGradeSchedule[1][7].getClassroomId() == -1){
				valueForDt28 = "";
			}else{
				valueForDt28 = firstGradeSchedule[1][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[1][7].getClassroomId() == -1){
				valueForDt28 = "";
			}else{
				valueForDt28 = secondGradeSchedule[1][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[1][7].getClassroomId() == -1){
				valueForDt28 = "";
			}else{
				valueForDt28 = thirdGradeSchedule[1][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[1][7].getClassroomId() == -1){
				valueForDt28 = "";
			}else{
				valueForDt28 = fourthGradeSchedule[1][7].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt28;
	}

	public void setValueForDt28(String valueForDt28) {
		this.valueForDt28 = valueForDt28;
	}

	public String getValueForDt31() {
		if(intGrade == 1){
			if(firstGradeSchedule[2][0].getClassroomId() == -1){
				valueForDt31 = "";
			}else{
				valueForDt31 = firstGradeSchedule[2][0].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[2][0].getClassroomId() == -1){
				valueForDt31 = "";
			}else{
				valueForDt31 = secondGradeSchedule[2][0].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[2][0].getClassroomId() == -1){
				valueForDt31 = "";
			}else{
				valueForDt31 = thirdGradeSchedule[2][0].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[2][0].getClassroomId() == -1){
				valueForDt31 = "";
			}else{
				valueForDt31 = fourthGradeSchedule[2][0].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt31;
	}

	public void setValueForDt31(String valueForDt31) {
		this.valueForDt31 = valueForDt31;
	}

	public String getValueForDt32() {
		if(intGrade == 1){
			if(firstGradeSchedule[2][1].getClassroomId() == -1){
				valueForDt32 = "";
			}else{
				valueForDt32 = firstGradeSchedule[2][1].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[2][1].getClassroomId() == -1){
				valueForDt32 = "";
			}else{
				valueForDt32 = secondGradeSchedule[2][1].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[2][1].getClassroomId() == -1){
				valueForDt32 = "";
			}else{
				valueForDt32 = thirdGradeSchedule[2][1].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[2][1].getClassroomId() == -1){
				valueForDt32 = "";
			}else{
				valueForDt32 = fourthGradeSchedule[2][1].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt32;
	}

	public void setValueForDt32(String valueForDt32) {
		this.valueForDt32 = valueForDt32;
	}

	public String getValueForDt33() {
		if(intGrade == 1){
			if(firstGradeSchedule[2][2].getClassroomId() == -1){
				valueForDt33 = "";
			}else{
				valueForDt33 = firstGradeSchedule[2][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[2][2].getClassroomId() == -1){
				valueForDt33 = "";
			}else{
				valueForDt33 = secondGradeSchedule[2][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[2][2].getClassroomId() == -1){
				valueForDt33 = "";
			}else{
				valueForDt33 = thirdGradeSchedule[2][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(firstGradeSchedule[2][2].getClassroomId() == -1){
				valueForDt33 = "";
			}else{
				valueForDt33 = fourthGradeSchedule[2][2].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt33;
	}

	public void setValueForDt33(String valueForDt33) {
		this.valueForDt33 = valueForDt33;
	}

	public String getValueForDt34() {
		if(intGrade == 1){
			if(firstGradeSchedule[2][3].getClassroomId() == -1){
				valueForDt34 = "";
			}else{
				valueForDt34 = firstGradeSchedule[2][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[2][3].getClassroomId() == -1){
				valueForDt34 = "";
			}else{
				valueForDt34 = secondGradeSchedule[2][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[2][3].getClassroomId() == -1){
				valueForDt34 = "";
			}else{
				valueForDt34 = thirdGradeSchedule[2][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[2][3].getClassroomId() == -1){
				valueForDt34 = "";
			}else{
				valueForDt34 = fourthGradeSchedule[2][3].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt34;
	}

	public void setValueForDt34(String valueForDt34) {
		this.valueForDt34 = valueForDt34;
	}

	public String getValueForDt35() {
		if(intGrade == 1){
			if(firstGradeSchedule[2][4].getClassroomId() == -1){
				valueForDt35 = "";
			}else{
				valueForDt35 = firstGradeSchedule[2][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[2][4].getClassroomId() == -1){
				valueForDt35 = "";
			}else{
				valueForDt35 = secondGradeSchedule[2][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[2][4].getClassroomId() == -1){
				valueForDt35 = "";
			}else{
				valueForDt35 = thirdGradeSchedule[2][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[2][4].getClassroomId() == -1){
				valueForDt35 = "";
			}else{
				valueForDt35 = fourthGradeSchedule[2][4].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt35;
	}

	public void setValueForDt35(String valueForDt35) {
		this.valueForDt35 = valueForDt35;
	}

	public String getValueForDt36() {
		if(intGrade == 1){
			if(firstGradeSchedule[2][5].getClassroomId() == -1){
				valueForDt36 = "";
			}else{
				valueForDt36 = firstGradeSchedule[2][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[2][5].getClassroomId() == -1){
				valueForDt36 = "";
			}else{
				valueForDt36 = secondGradeSchedule[2][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[2][5].getClassroomId() == -1){
				valueForDt36 = "";
			}else{
				valueForDt36 = thirdGradeSchedule[2][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[2][5].getClassroomId() == -1){
				valueForDt36 = "";
			}else{
				valueForDt36 = fourthGradeSchedule[2][5].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt36;
	}

	public void setValueForDt36(String valueForDt36) {
		this.valueForDt36 = valueForDt36;
	}

	public String getValueForDt37() {
		if(intGrade == 1){
			if(firstGradeSchedule[2][6].getClassroomId() == -1){
				valueForDt37 = "";
			}else{
				valueForDt37 = firstGradeSchedule[2][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[2][6].getClassroomId() == -1){
				valueForDt37 = "";
			}else{
				valueForDt37 = secondGradeSchedule[2][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[2][6].getClassroomId() == -1){
				valueForDt37 = "";
			}else{
				valueForDt37 = thirdGradeSchedule[2][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[2][6].getClassroomId() == -1){
				valueForDt37 = "";
			}else{
				valueForDt37 = fourthGradeSchedule[2][6].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt37;
	}

	public void setValueForDt37(String valueForDt37) {
		this.valueForDt37 = valueForDt37;
	}

	public String getValueForDt38() {
		if(intGrade == 1){
			if(firstGradeSchedule[2][7].getClassroomId() == -1){
				valueForDt38 = "";
			}else{
				valueForDt38 = firstGradeSchedule[2][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[2][7].getClassroomId() == -1){
				valueForDt38 = "";
			}else{
				valueForDt38 = secondGradeSchedule[2][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[2][7].getClassroomId() == -1){
				valueForDt38 = "";
			}else{
				valueForDt38 = thirdGradeSchedule[2][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[2][7].getClassroomId() == -1){
				valueForDt38 = "";
			}else{
				valueForDt38 = fourthGradeSchedule[2][7].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt38;
	}

	public void setValueForDt38(String valueForDt38) {
		this.valueForDt38 = valueForDt38;
	}

	public String getValueForDt41() {
		if(intGrade == 1){
			if(firstGradeSchedule[3][0].getClassroomId() == -1){
				valueForDt41 = "";
			}else{
				valueForDt41 = firstGradeSchedule[3][0].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[3][0].getClassroomId() == -1){
				valueForDt41 = "";
			}else{
				valueForDt41 = secondGradeSchedule[3][0].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[3][0].getClassroomId() == -1){
				valueForDt41 = "";
			}else{
				valueForDt41 = thirdGradeSchedule[3][0].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[3][0].getClassroomId() == -1){
				valueForDt41 = "";
			}else{
				valueForDt41 = fourthGradeSchedule[3][0].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt41;
	}

	public void setValueForDt41(String valueForDt41) {
		this.valueForDt41 = valueForDt41;
	}

	public String getValueForDt42() {
		if(intGrade == 1){
			if(firstGradeSchedule[3][1].getClassroomId() == -1){
				valueForDt42 = "";
			}else{
				valueForDt42 = firstGradeSchedule[3][1].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[3][1].getClassroomId() == -1){
				valueForDt42 = "";
			}else{
				valueForDt42 = secondGradeSchedule[3][1].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[3][1].getClassroomId() == -1){
				valueForDt42 = "";
			}else{
				valueForDt42 = thirdGradeSchedule[3][1].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[3][1].getClassroomId() == -1){
				valueForDt42 = "";
			}else{
				valueForDt42 = fourthGradeSchedule[3][1].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt42;
	}

	public void setValueForDt42(String valueForDt42) {
		this.valueForDt42 = valueForDt42;
	}

	public String getValueForDt43() {
		if(intGrade == 1){
			if(firstGradeSchedule[3][2].getClassroomId() == -1){
				valueForDt43 = "";
			}else{
				valueForDt43 = firstGradeSchedule[3][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[3][2].getClassroomId() == -1){
				valueForDt43 = "";
			}else{
				valueForDt43 = secondGradeSchedule[3][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[3][2].getClassroomId() == -1){
				valueForDt43 = "";
			}else{
				valueForDt43 = thirdGradeSchedule[3][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[3][2].getClassroomId() == -1){
				valueForDt43 = "";
			}else{
				valueForDt43 = fourthGradeSchedule[3][2].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt43;
	}

	public void setValueForDt43(String valueForDt43) {
		this.valueForDt43 = valueForDt43;
	}

	public String getValueForDt44() {
		if(intGrade == 1){
			if(firstGradeSchedule[3][3].getClassroomId() == -1){
				valueForDt44 = "";
			}else{
				valueForDt44 = firstGradeSchedule[3][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[3][3].getClassroomId() == -1){
				valueForDt44 = "";
			}else{
				valueForDt44 = secondGradeSchedule[3][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[3][3].getClassroomId() == -1){
				valueForDt44 = "";
			}else{
				valueForDt44 = thirdGradeSchedule[3][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[3][3].getClassroomId() == -1){
				valueForDt44 = "";
			}else{
				valueForDt44 = fourthGradeSchedule[3][3].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt44;
	}

	public void setValueForDt44(String valueForDt44) {
		this.valueForDt44 = valueForDt44;
	}

	public String getValueForDt45() {
		if(intGrade == 1){
			if(firstGradeSchedule[3][4].getClassroomId() == -1){
				valueForDt45 = "";
			}else{
				valueForDt45 = firstGradeSchedule[3][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[3][4].getClassroomId() == -1){
				valueForDt45 = "";
			}else{
				valueForDt45 = secondGradeSchedule[3][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[3][4].getClassroomId() == -1){
				valueForDt45 = "";
			}else{
				valueForDt45 = thirdGradeSchedule[3][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[3][4].getClassroomId() == -1){
				valueForDt45 = "";
			}else{
				valueForDt45 = fourthGradeSchedule[3][4].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt45;
	}

	public void setValueForDt45(String valueForDt45) {
		this.valueForDt45 = valueForDt45;
	}

	public String getValueForDt46() {
		if(intGrade == 1){
			if(firstGradeSchedule[3][5].getClassroomId() == -1){
				valueForDt46 = "";
			}else{
				valueForDt46 = firstGradeSchedule[3][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[3][5].getClassroomId() == -1){
				valueForDt46 = "";
			}else{
				valueForDt46 = secondGradeSchedule[3][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[3][5].getClassroomId() == -1){
				valueForDt46 = "";
			}else{
				valueForDt46 = thirdGradeSchedule[3][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[3][5].getClassroomId() == -1){
				valueForDt46 = "";
			}else{
				valueForDt46 = fourthGradeSchedule[3][5].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt46;
	}

	public void setValueForDt46(String valueForDt46) {
		this.valueForDt46 = valueForDt46;
	}

	public String getValueForDt47() {
		if(intGrade == 1){
			if(firstGradeSchedule[3][6].getClassroomId() == -1){
				valueForDt47 = "";
			}else{
				valueForDt47 = firstGradeSchedule[3][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[3][6].getClassroomId() == -1){
				valueForDt47 = "";
			}else{
				valueForDt47 = secondGradeSchedule[3][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[3][6].getClassroomId() == -1){
				valueForDt47 = "";
			}else{
				valueForDt47 = thirdGradeSchedule[3][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[3][6].getClassroomId() == -1){
				valueForDt47 = "";
			}else{
				valueForDt47 = fourthGradeSchedule[3][6].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt47;
	}

	public void setValueForDt47(String valueForDt47) {
		this.valueForDt47 = valueForDt47;
	}

	public String getValueForDt48() {
		if(intGrade == 1){
			if(firstGradeSchedule[3][7].getClassroomId() == -1){
				valueForDt48 = "";
			}else{
				valueForDt48 = firstGradeSchedule[3][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[3][7].getClassroomId() == -1){
				valueForDt48 = "";
			}else{
				valueForDt48 = secondGradeSchedule[3][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[3][7].getClassroomId() == -1){
				valueForDt48 = "";
			}else{
				valueForDt48 = thirdGradeSchedule[3][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[3][7].getClassroomId() == -1){
				valueForDt48 = "";
			}else{
				valueForDt48 = fourthGradeSchedule[3][7].getCourseTheoricOrPraticName();
			}			
		}
		return valueForDt48;
	}

	public void setValueForDt48(String valueForDt48) {
		this.valueForDt48 = valueForDt48;
	}

	public String getValueForDt51() {
		if(intGrade == 1){
			if(firstGradeSchedule[4][0].getClassroomId() == -1){
				valueForDt51 = "";
			}else{
				valueForDt51 = firstGradeSchedule[4][0].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[4][0].getClassroomId() == -1){
				valueForDt51 = "";
			}else{
				valueForDt51 = secondGradeSchedule[4][0].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[4][0].getClassroomId() == -1){
				valueForDt51 = "";
			}else{
				valueForDt51 = thirdGradeSchedule[4][0].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[4][0].getClassroomId() == -1){
				valueForDt51 = "";
			}else{
				valueForDt51 = fourthGradeSchedule[4][0].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt51;
	}

	public void setValueForDt51(String valueForDt51) {
		this.valueForDt51 = valueForDt51;
	}

	public String getValueForDt52() {
		if(intGrade == 1){
			if(firstGradeSchedule[4][1].getClassroomId() == -1){
				valueForDt52 = "";
			}else{
				valueForDt52 = firstGradeSchedule[4][1].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[4][1].getClassroomId() == -1){
				valueForDt52 = "";
			}else{
				valueForDt52 = secondGradeSchedule[4][1].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[4][1].getClassroomId() == -1){
				valueForDt52 = "";
			}else{
				valueForDt52 = thirdGradeSchedule[4][1].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[4][1].getClassroomId() == -1){
				valueForDt52 = "";
			}else{
				valueForDt52 = fourthGradeSchedule[4][1].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt52;
	}

	public void setValueForDt52(String valueForDt52) {
		this.valueForDt52 = valueForDt52;
	}

	public String getValueForDt53() {
		if(intGrade == 1){
			if(firstGradeSchedule[4][2].getClassroomId() == -1){
				valueForDt53 = "";
			}else{
				valueForDt53 = firstGradeSchedule[4][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[4][2].getClassroomId() == -1){
				valueForDt53 = "";
			}else{
				valueForDt53 = secondGradeSchedule[4][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[4][2].getClassroomId() == -1){
				valueForDt53 = "";
			}else{
				valueForDt53 = thirdGradeSchedule[4][2].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[4][2].getClassroomId() == -1){
				valueForDt53 = "";
			}else{
				valueForDt53 = fourthGradeSchedule[4][2].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt53;
	}

	public void setValueForDt53(String valueForDt53) {
		this.valueForDt53 = valueForDt53;
	}

	public String getValueForDt54() {
		if(intGrade == 1){
			if(firstGradeSchedule[4][3].getClassroomId() == -1){
				valueForDt54 = "";
			}else{
				valueForDt54 = firstGradeSchedule[4][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[4][3].getClassroomId() == -1){
				valueForDt54 = "";
			}else{
				valueForDt54 = secondGradeSchedule[4][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[4][3].getClassroomId() == -1){
				valueForDt54 = "";
			}else{
				valueForDt54 = thirdGradeSchedule[4][3].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[4][3].getClassroomId() == -1){
				valueForDt54 = "";
			}else{
				valueForDt54 = fourthGradeSchedule[4][3].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt54;
	}

	public void setValueForDt54(String valueForDt54) {
		this.valueForDt54 = valueForDt54;
	}

	public String getValueForDt55() {
		if(intGrade == 1){
			if(firstGradeSchedule[4][4].getClassroomId() == -1){
				valueForDt55 = "";
			}else{
				valueForDt55 = firstGradeSchedule[4][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[4][4].getClassroomId() == -1){
				valueForDt55 = "";
			}else{
				valueForDt55 = secondGradeSchedule[4][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[4][4].getClassroomId() == -1){
				valueForDt55 = "";
			}else{
				valueForDt55 = thirdGradeSchedule[4][4].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[4][4].getClassroomId() == -1){
				valueForDt55 = "";
			}else{
				valueForDt55 = fourthGradeSchedule[4][4].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt55;
	}

	public void setValueForDt55(String valueForDt55) {
		this.valueForDt55 = valueForDt55;
	}

	public String getValueForDt56() {
		if(intGrade == 1){
			if(firstGradeSchedule[4][5].getClassroomId() == -1){
				valueForDt56 = "";
			}else{
				valueForDt56 = firstGradeSchedule[4][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[4][5].getClassroomId() == -1){
				valueForDt56 = "";
			}else{
				valueForDt56 = secondGradeSchedule[4][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[4][5].getClassroomId() == -1){
				valueForDt56 = "";
			}else{
				valueForDt56 = thirdGradeSchedule[4][5].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[4][5].getClassroomId() == -1){
				valueForDt56 = "";
			}else{
				valueForDt56 = fourthGradeSchedule[4][5].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt56;
	}

	public void setValueForDt56(String valueForDt56) {
		this.valueForDt56 = valueForDt56;
	}

	public String getValueForDt57() {
		if(intGrade == 1){
			if(firstGradeSchedule[4][6].getClassroomId() == -1){
				valueForDt57 = "";
			}else{
				valueForDt57 = firstGradeSchedule[4][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[4][6].getClassroomId() == -1){
				valueForDt57 = "";
			}else{
				valueForDt57 = secondGradeSchedule[4][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[4][6].getClassroomId() == -1){
				valueForDt57 = "";
			}else{
				valueForDt57 = thirdGradeSchedule[4][6].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[4][6].getClassroomId() == -1){
				valueForDt57 = "";
			}else{
				valueForDt57 = fourthGradeSchedule[4][6].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt57;
	}

	public void setValueForDt57(String valueForDt57) {
		this.valueForDt57 = valueForDt57;
	}

	public String getValueForDt58() {
		if(intGrade == 1){
			if(firstGradeSchedule[4][7].getClassroomId() == -1){
				valueForDt58 = "";
			}else{
				valueForDt58 = firstGradeSchedule[4][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 2){
			if(secondGradeSchedule[4][7].getClassroomId() == -1){
				valueForDt58 = "";
			}else{
				valueForDt58 = secondGradeSchedule[4][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 3){
			if(thirdGradeSchedule[4][7].getClassroomId() == -1){
				valueForDt58 = "";
			}else{
				valueForDt58 = thirdGradeSchedule[4][7].getCourseTheoricOrPraticName();
			}
		}else if(intGrade == 4){
			if(fourthGradeSchedule[4][7].getClassroomId() == -1){
				valueForDt58 = "";
			}else{
				valueForDt58 = fourthGradeSchedule[4][7].getCourseTheoricOrPraticName();
			}
		}
		return valueForDt58;
	}

	public void setValueForDt58(String valueForDt58) {
		this.valueForDt58 = valueForDt58;
	}

	public void setSavedVersionName(String savedVersionName) {
		this.savedVersionName = savedVersionName;
	}

	public String getSavedVersionName() {
		return savedVersionName;
	}
	
	public List<SelectItem> getListArchiveToSave() {
		return listArchiveToSave;
	}

	public void setListArchiveToSave(List<SelectItem> listArchiveToSave) {
		this.listArchiveToSave = listArchiveToSave;
	}
}
