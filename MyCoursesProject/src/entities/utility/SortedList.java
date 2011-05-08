package entities.utility;

import java.util.ArrayList;
import entities.dao.Course;



public class SortedList {

	
	public SortedList(){
	}
	
	public SortedList(SortedList ls){
		
		this.listSorted = ls.listSorted;
		this.falseAttendanceList = ls.falseAttendanceList;
		
		this.firstYearList = ls.firstYearList;
		this.secondYearList = ls.secondYearList;
		this.thirdYearList = ls.thirdYearList;
		this.fourthYearList = ls.fourthYearList;
		
		separateCredit();
	}//end of constructor
	
	private void separateCredit(){
		try {
			ScheduleAtomic scAtomic = null;
			for (int i = 0; i < listSorted.size(); i++) {
				scAtomic = new ScheduleAtomic(listSorted.get(i));

				if (scAtomic.getCredit() == 1) {firstYearList.add(scAtomic);} 
				else if (scAtomic.getCredit() == 2) {secondYearList.add(scAtomic);} 
				else if (scAtomic.getCredit() == 3) {thirdYearList.add(scAtomic);} 
				else if (scAtomic.getCredit() == 4) {fourthYearList.add(scAtomic);}
			}//end of for loop
			
			fourthYearList = separateAttandence(fourthYearList);
			thirdYearList  = separateAttandence(thirdYearList);
			secondYearList = separateAttandence(secondYearList);
			firstYearList  = separateAttandence(firstYearList);
			
			fourthYearList = separatePrecondition(fourthYearList);
			thirdYearList = separatePrecondition(thirdYearList);
			secondYearList = separatePrecondition(secondYearList);
			firstYearList = separatePrecondition(firstYearList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}//end of separateCredit method
	
	private ArrayList<ScheduleAtomic> separateAttandence(ArrayList<ScheduleAtomic> scAtomicList){
		try {
			Course course = null;
			for(int i = 0; i < scAtomicList.size(); i++){
				course = new Course(scAtomicList.get(i).getSyllabus().getCourse());
				if(course.isAttendance()){
					falseAttendanceList.add(scAtomicList.get(i));
				}//end if
			}//end of for loop
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scAtomicList;
	}//end of method
	
	private ArrayList<ScheduleAtomic> separatePrecondition(ArrayList<ScheduleAtomic> lsScheduleAtomic){
		Course course = null;
		ScheduleAtomic sc = null;
		for(int i = 0; i < lsScheduleAtomic.size(); i++){
			course = new Course(lsScheduleAtomic.get(i).getSyllabus().getCourse());
			if(course.getPrecondition().equals("")){
				sc = new ScheduleAtomic(lsScheduleAtomic.get(i));
				lsScheduleAtomic.remove(i);
				lsScheduleAtomic.add(sc);
			}//end of if
		}//end of for-loop
		return lsScheduleAtomic;
	}//end of separatePrecondition method
	
	private ArrayList<ScheduleAtomic> listSorted = new ArrayList<ScheduleAtomic>();
	
	private ArrayList<ScheduleAtomic> falseAttendanceList = new ArrayList<ScheduleAtomic>();
	
	private ArrayList<ScheduleAtomic> fourthYearList = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> thirdYearList = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> secondYearList = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> firstYearList = new ArrayList<ScheduleAtomic>();
	
}
