package entities.utility;

import entities.dao.Syllabus;
import java.util.ArrayList;

public class ScheduleAtomic {
	private Syllabus syllabus;
	private String courseType;
	private String day;
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
		this.knowledge = this.generateKnowledge(credit);
	}

	public ArrayList<Integer> getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(ArrayList<Integer> knowledge) {
		this.knowledge = knowledge;
	}
}
