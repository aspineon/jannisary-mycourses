package entities.utility;

import entities.dao.Syllabus;
import java.util.ArrayList;

public class ScheduleAtomic {
	private Syllabus syllabus;
	private String courseType;
	private String day;
	private int startHour;
	private int credit;
	private ArrayList<Integer> knowledge;
	
	public ScheduleAtomic() {
		
	}
	
	public ScheduleAtomic(Syllabus pSyllabus, String pCourseType, String pDay, int pStartHour, int pCredit) {
		this.syllabus = pSyllabus;
		this.courseType = pCourseType;
		this.day = pDay;
		this.startHour = pStartHour;
		this.credit = pCredit;
		this.knowledge = this.generateKnowledge(pCredit);
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
	}

	public ArrayList<Integer> getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(ArrayList<Integer> knowledge) {
		this.knowledge = knowledge;
	}
}
