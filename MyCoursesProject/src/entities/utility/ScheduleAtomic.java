package entities.utility;

import entities.dao.Schedule;
import entities.dao.Syllabus;
import entities.dao.SyllabusArchive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.model.SelectItem;

public class ScheduleAtomic {
	private Syllabus syllabus;
	private String courseType;
	private String day = "";
	private int startHour = 0;
	private int credit = 0;
	private ArrayList<Integer> knowledge;
	private boolean attendance;
	private int courseId;
	private int lecturerId;
	private int classroomId;
	private String preCondition;
	private String courseName;
	private String lecturerName;
	
	public ScheduleAtomic() {
		super();
	}
	
	public ScheduleAtomic(Syllabus pSyllabus, String pCourseType, String pDay, int pStartHour, int pCredit, boolean pAttendance, int pCourseId, int pLecturerId, int pClassroomId, String pPreCondition, String pCourseName, String pLecturerName) {
		super();
		this.syllabus = pSyllabus;
		this.courseType = pCourseType;
		this.day = pDay;
		this.startHour = pStartHour;
		this.credit = pCredit;
		this.knowledge = this.generateKnowledge(pCredit);
		this.attendance = pAttendance;
		this.courseId = pCourseId;
		this.lecturerId = pLecturerId;
		this.classroomId = pClassroomId;
		this.preCondition = pPreCondition;
		this.courseName = pCourseName;
		this.lecturerName = pLecturerName;
	}
	//Copy Constructor
	public ScheduleAtomic(ScheduleAtomic sItem) {
		super();
		this.syllabus = sItem.syllabus;
		this.courseType = sItem.courseType;
		this.day = sItem.day;
		this.startHour = sItem.startHour;
		this.credit = sItem.credit;
		this.knowledge = sItem.knowledge;
		this.attendance = sItem.attendance;
		this.courseId = sItem.courseId;
		this.lecturerId = sItem.lecturerId;
		this.classroomId = sItem.classroomId;
		this.preCondition = sItem.preCondition;
		this.courseName = sItem.courseName;
		this.lecturerName = sItem.lecturerName;
	}
	
	@Override
	public String toString() {
		String retStr = "\n";
		retStr = retStr + "Syllabus: " + this.syllabus.getSyllabusId();
		retStr = retStr + "| Type: " + this.courseType;
		retStr = retStr + "| Credit: " + this.credit;
		retStr = retStr + "| Precondition: " + this.preCondition + "\n";
		retStr = retStr + "Course Id: " + this.courseId;
		retStr = retStr + "| Course Name: " + this.courseName + "\n";
		retStr = retStr + "Lecturer Id: " + this.lecturerId;
		retStr = retStr + "| Lecturer Name: " + this.lecturerName + "\n";
		retStr = retStr + "Classroom Id: " + this.classroomId + "\n";
		retStr = retStr + "Day: " + this.day;
		retStr = retStr + "| StartHour: " + this.startHour + "\n";
		boolean att = this.syllabus.getCourse().isAttendance();
		String ek = "Attandance required\n";
		if(att == false) {
			ek = "Attendance not required\n";
		}
		retStr = retStr + ek;
		String knowledge = "{";
		for(int i = 0; i < this.knowledge.size(); i++) {
			String knwStr = Integer.toString(this.knowledge.get(i));
			knowledge = knowledge + knwStr + ",";
		}
		knowledge = knowledge + "}\n\n";
		retStr = retStr + knowledge;
		return retStr;
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        final ScheduleAtomic other = (ScheduleAtomic) obj;
        if (this.syllabus.getSyllabusId() == null) {
            if (other.syllabus.getSyllabusId() != null)
                return false;
        } else if (this.syllabus.getSyllabusId() != other.syllabus.getSyllabusId())
            return false;
        if (this.courseType == null) {
            if (other.courseType != null)
                return false;
        } else if (!this.courseType.equals(other.courseType))
            return false;
        if (this.day == null) {
        	if (other.day != null)
        		return false;
        } else if (!this.day.equals(other.day))
        	return false;
        if (this.startHour == 0) {
        	if (other.startHour != 0)
        		return false;
        } else if (this.startHour != other.startHour)
        	return false;
        
        return true;
    }
	
	public ArrayList<SelectItem> getKnowledgeByDay(String day) {
		ArrayList<SelectItem> retList = new ArrayList<SelectItem>();
		int bottom = 0; 
		int top = 0;
		if(day.equals("Monday")) {
			bottom = 0; 
			top = 9;
		}
		if(day.equals("Tuesday")) {
			bottom = 8;
			top = 17;
		}
		if(day.equals("Wednesday")) {
			bottom = 16;
			top = 25;
		}
		if(day.equals("Thursday")) {
			bottom = 24;
			top = 33;
		}
		if(day.equals("Friday")) {
			bottom = 32;
			top = 41;
		}
		
		if(top == 0) { return retList; }
		retList.add(new SelectItem("Choose Start Hour"));
		for(int i = 0; i < this.knowledge.size(); i++) {
			if(this.knowledge.get(i) > bottom && this.knowledge.get(i) < top) {
				int retVal = this.knowledge.get(i) % 8;
				if(retVal == 0) { retVal = 8;}
				SelectItem sItem = new SelectItem(Integer.toString(retVal));
				retList.add(sItem);
			}
		}
		return retList;
	}
	
	private ArrayList<Integer> generateKnowledge(int credit) {
		ArrayList<Integer> retList = new ArrayList<Integer>();
		if(credit == 1) {
			for(int i = 1; i < 41; i++) {
				retList.add(i);
			}
			return retList;
		}
		if(credit == 2) {
			for(int i = 1; i < 41; i++) {
				if((i % 4) != 0) {
					retList.add(i);
				}
			}
			return retList;
		}
		if(credit == 3) {
			for(int i = 1; i < 41; i++) {
				if(((i % 4) != 3) && ((i % 4) != 0)) {
					retList.add(i);
				}
			}
		}
		if(credit == 4) {
			for(int i = 1; i < 41; i++) {
				if((i % 4) == 1) {
					retList.add(i);
				}
			}
		}
		return retList;
	}
	
	/*Added by Erhun(07.05.2011)*/
	public int dayToIntDay(String strDay){
		int intDay = 0;
		try{
			if(strDay.equals("Monday"))        { intDay = 0;}
			else if(strDay.equals("Tuesday"))  { intDay = 1;}
			else if(strDay.equals("Wednesday")){ intDay = 2;}
			else if(strDay.equals("Thursday")) { intDay = 3;}
			else if(strDay.equals("Friday"))   { intDay = 4;}
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
		return intDay;
	}
	
	/*Added by Erhun(07.05.2011)*/
	public void addScheduleAtomicListToScheduleTable(List<ScheduleAtomic> listScheduleAtomic){
		Schedule schedule = new Schedule();
		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
		ScheduleAtomic scheduleAtomic = new ScheduleAtomic();
		int intSyllabusArchiveId=0;//hardCode olarak eklenmiştir,
		try{
			for(int i = 0; i < listScheduleAtomic.size(); i++){
				schedule = new Schedule();
				
				scheduleAtomic = new ScheduleAtomic(listScheduleAtomic.get(i));
				SyllabusArchive sylArchive = new SyllabusArchive();
				sylArchive.setSyllabusArchiveId(intSyllabusArchiveId);
				
				schedule.setSyllabusArchive(sylArchive);
				schedule.setSyllabus(scheduleAtomic.getSyllabus());
				schedule.setCourseType(scheduleAtomic.getCourseType());
				schedule.setHours(scheduleAtomic.getCredit());
				int intDay = dayToIntDay(scheduleAtomic.getDay());
				intDay = intDay * 8;
				int timeOfCourse = (intDay * 8) + scheduleAtomic.getStartHour();
				schedule.setTimeofCourse(timeOfCourse);
				
				scheduleList.add(schedule);
			}
			
			schedule.setScheduleList(scheduleList);
			schedule.addScheduleList();
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	/*Added by Erhun(09.05.2011)*/
	public int getRandomValue(int range){
		int retIndex = 0;
		Random random = new Random();
		try{
			retIndex = random.nextInt(range);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return retIndex;
	}//end of getRandomKnowledge method

	public int getKnowledgeSize() {
		return this.knowledge.size();
	}
	
	public void removeKnowledgeByIndex(int index) {
		this.knowledge.remove(index);
	}
	
	public int getKnowledgeByIndex(int index) {
		return this.getKnownIndex(index);
	}
	
	private int getKnownIndex(int index) {
		return this.knowledge.get(index);	
	}
	
	public void refreshKnowledge() {
		this.setCredit(this.credit);
	} 
	
	/*Added by Erhun(09.05.2011)*/
	public Index convertIntToIndex(int intDayHour){
		Index index = new Index();
		String strDay = "";	
		int intDay = intDayHour / 8;
		int intHour = intDayHour % 8;
		if(intHour == 0) {
			intHour = 8;	
			if(intDay == 1) { strDay = "Monday"; }
			if(intDay == 2) { strDay = "Tuesday"; }
			if(intDay == 3) { strDay = "Wednesday"; }
			if(intDay == 4) { strDay = "Thursday"; }
			if(intDay == 5) { strDay = "Friday"; }
			index.setDay(strDay);
			index.setHour(intHour);
			return index;
		}
		if(intDay == 0) { strDay = "Monday"; }
		if(intDay == 1) { strDay = "Tuesday"; }
		if(intDay == 2) { strDay = "Wednesday"; }
		if(intDay == 3) { strDay = "Thursday"; }
		if(intDay == 4) { strDay = "Friday"; }
		index.setDay(strDay);
		index.setHour(intHour);
		return index;
		
	}//end of convertIntToIndex method
	
// ************* Credit Split and Merge Functions *********************************************
	
	public ScheduleAtomic splitCredit(int pCredit)
	{
		ScheduleAtomic splitObj = new ScheduleAtomic();
		
		if(pCredit > 0 && pCredit < this.credit)
		{
			splitObj.setSyllabus(this.syllabus);
			splitObj.setCourseType(this.courseType);
			splitObj.setCredit(pCredit);
			splitObj.setDay(this.day);
			splitObj.setStartHour(this.startHour);
			splitObj.setAttendance(this.attendance);
			splitObj.setCourseId(this.courseId);
			splitObj.setLecturerId(this.lecturerId);
			splitObj.setClassroomId(this.classroomId);
			splitObj.setPreCondition(this.preCondition);
			this.setCredit(this.credit - pCredit);
		}		
		return splitObj;
	}
	
	public void mergeCredit(ScheduleAtomic pScheduleAtomic, String listType)
	{
		if(listType.equals("Marked")) {
			if(((pScheduleAtomic.getStartHour() + pScheduleAtomic.getCredit()) == this.startHour) && (pScheduleAtomic.getDay().equals(this.day))) {
				this.startHour = pScheduleAtomic.getStartHour();			
				this.credit = pScheduleAtomic.getCredit() + this.credit;
				return;
			}
			if(((this.getStartHour() + this.credit) == pScheduleAtomic.getStartHour()) && (this.day.equals(pScheduleAtomic.getDay()))) {
				this.credit = pScheduleAtomic.getCredit() + this.credit;
				return;
			}
		}
		if(listType.equals("Unmarked")) {
			this.credit = pScheduleAtomic.getCredit() + this.credit;
			this.setCredit(this.credit);
		}
	}
	
//***************************************************************************************
	public ArrayList<ScheduleAtomic> rollback(ArrayList<ScheduleAtomic> sList, int val) {
		int factor = this.findCofactors(val);
		for(int i = 0; i < sList.size(); i++) {
			if(sList.get(i).getCredit() < factor) {
				sList.get(i).addKnowledge(val);
			}
		}
		return sList;
	}

	public void addKnowledge(int val) {
		this.knowledge.add(val);
	}
	
	private int findCofactors(int val) {
		int factor = val % 4;
		int retVal = 0;
		if( factor == 0) { retVal = 2; }
		if( factor == 1) { retVal = 5; }
		if( factor == 2) { retVal = 4; }
		if( factor == 3) { retVal = 3; }
		return retVal;
	}
//*************** GETTER-SETTER METHODS *************************************************
	public Syllabus getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(Syllabus syllabus) {
		this.syllabus = syllabus;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
		this.knowledge = this.generateKnowledge(credit);
	}

	public ArrayList<Integer> getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(ArrayList<Integer> knowledge) {
		this.knowledge = knowledge;
	}

	public boolean isAttendance() {
		return attendance;
	}

	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getLecturerId() {
		return lecturerId;
	}

	public void setLecturerId(int lecturerId) {
		this.lecturerId = lecturerId;
	}

	public int getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}

	public String getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}
	
	
}

