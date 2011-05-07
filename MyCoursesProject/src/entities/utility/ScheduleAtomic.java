package entities.utility;

import entities.dao.Schedule;
import entities.dao.Syllabus;
import entities.dao.SyllabusArchive;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class ScheduleAtomic {
	private Syllabus syllabus;
	private String courseType;
	private String day = "";
	private int startHour = 0;
	private int credit = 0;
	private ArrayList<Integer> knowledge;
	
	public ScheduleAtomic() {
		super();
	}
	
	public ScheduleAtomic(Syllabus pSyllabus, String pCourseType, String pDay, int pStartHour, int pCredit) {
		super();
		this.syllabus = pSyllabus;
		this.courseType = pCourseType;
		this.day = pDay;
		this.startHour = pStartHour;
		this.credit = pCredit;
		this.knowledge = this.generateKnowledge(pCredit);
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
        if (this.credit == 0) {
        	if (other.credit != 0)
        		return false;
        } else if (this.credit != other.credit)
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
	private int dayToIntDay(String strDay){
		int intDay = 0;
		try{
			if(strDay.equals("Monday"))        { intDay = 1;}
			else if(strDay.equals("Tuesday"))  { intDay = 2;}
			else if(strDay.equals("Wednesday")){ intDay = 3;}
			else if(strDay.equals("Thursday")) { intDay = 4;}
			else if(strDay.equals("Friday"))   { intDay = 5;}
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
	
	
	// ************* Credit Split and Merge Functions *********************************************
	
	@SuppressWarnings("unused")
	public ScheduleAtomic splitCredit(int pCredit)
	{
		ScheduleAtomic splitObj = new ScheduleAtomic();
		
		if(pCredit > 0 && pCredit < this.credit)
		{
			splitObj.setSyllabus(this.syllabus);
			splitObj.setCourseType(this.courseType);
			splitObj.setCredit(this.credit - (this.credit - pCredit));
			splitObj.setDay(this.day);
			splitObj.setStartHour(this.startHour);
		}		
		return splitObj;
	}
	
	public void mergeCredit(ScheduleAtomic pScheduleAtomic)
	{
		if(((pScheduleAtomic.getStartHour() + pScheduleAtomic.getCredit()) == this.startHour)
				&& (pScheduleAtomic.getDay().equals(this.day)))
		{
			this.startHour = pScheduleAtomic.getStartHour();			
			this.credit = pScheduleAtomic.getCredit() + this.credit;
		}
		else
		{
			this.credit = pScheduleAtomic.getCredit() + this.credit;
		}
	}
	
//***************************************************************************************
	

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
}
