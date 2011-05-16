package entities.business;

import entities.dao.Schedule;
import entities.dao.Syllabus;
import entities.util.BasicScheduleUtilBean;

/**
 * @author arshavin
 *
 */
public class ScheduleBean {
	
	private Schedule schedule = new Schedule();
	private String pCourseType;
	private int pTimeOfCourse;
	private int pHours;
	private Syllabus pSyllabus = new Syllabus();
	private int pSyllabusId;
	
	public void addSchedule(BasicScheduleUtilBean[][] firstGradeSchedule, BasicScheduleUtilBean[][] secondGradeSchedule,
			BasicScheduleUtilBean[][] thirdGradeSchedule,BasicScheduleUtilBean[][] fourthGradeSchedule){
		
		schedule.setFirstGradeSchedule(firstGradeSchedule);
		schedule.setSecondGradeSchedule(secondGradeSchedule);
		schedule.setThirdGradeSchedule(thirdGradeSchedule);
		schedule.setFourthGradeSchedule(fourthGradeSchedule);
		
		schedule.addScheduleMatrix();
	}
	
	public void updateSchedule(BasicScheduleUtilBean[][] firstGradeSchedule, BasicScheduleUtilBean[][] secondGradeSchedule,
			BasicScheduleUtilBean[][] thirdGradeSchedule,BasicScheduleUtilBean[][] fourthGradeSchedule,int selectedYearForEdit,String semester,String savedSemester,int savedYear){
		
		schedule.setFirstGradeSchedule(firstGradeSchedule);
		schedule.setSecondGradeSchedule(secondGradeSchedule);
		schedule.setThirdGradeSchedule(thirdGradeSchedule);
		schedule.setFourthGradeSchedule(fourthGradeSchedule);
		schedule.setSemester(semester);
		schedule.setSelectedYearForEdit(selectedYearForEdit);
		schedule.setSavedSemester(savedSemester);
		schedule.setSavedYear(savedYear);
		
		schedule.updateScheduleMatrix();
	}

	public String getpCourseType() {
		return pCourseType;
	}

	public void setpCourseType(String pCourseType) {
		this.pCourseType = pCourseType;
	}

	public int getpTimeOfCourse() {
		return pTimeOfCourse;
	}

	public void setpTimeOfCourse(int pTimeOfCourse) {
		this.pTimeOfCourse = pTimeOfCourse;
	}

	public int getpHours() {
		return pHours;
	}

	public void setpHours(int pHours) {
		this.pHours = pHours;
	}

	public int getpSyllabusId() {
		return pSyllabusId;
	}

	public void setpSyllabusId(int pSyllabusId) {
		this.pSyllabusId = pSyllabusId;
	}
	
}
