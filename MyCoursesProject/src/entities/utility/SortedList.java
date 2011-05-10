package entities.utility;

import java.util.ArrayList;



public class SortedList {
	private ArrayList<ScheduleAtomic> listSorted = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> falseAttendanceList = new ArrayList<ScheduleAtomic>();
	
	private ArrayList<ScheduleAtomic> fourthCreditList = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> thirdCreditList = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> secondCreditList = new ArrayList<ScheduleAtomic>();
	private ArrayList<ScheduleAtomic> firstCreditList = new ArrayList<ScheduleAtomic>();
	
	public SortedList(){
	}
	
	public SortedList(ArrayList<ScheduleAtomic> ls){
		this.listSorted = ls;
		separateCredit();
	}//end of constructor
	
	private void separateCredit(){
		try {
			ScheduleAtomic scAtomic = null;
			for (int i = 0; i < listSorted.size(); i++) {
				scAtomic = new ScheduleAtomic(listSorted.get(i));

				if (scAtomic.getCredit() == 1) {firstCreditList.add(scAtomic);} 
				else if (scAtomic.getCredit() == 2) {secondCreditList.add(scAtomic);} 
				else if (scAtomic.getCredit() == 3) {thirdCreditList.add(scAtomic);} 
				else if (scAtomic.getCredit() == 4) {fourthCreditList.add(scAtomic);}
			}//end of for loop
			
			fourthCreditList = separateAttandence(fourthCreditList);
			thirdCreditList  = separateAttandence(thirdCreditList);
			secondCreditList = separateAttandence(secondCreditList);
			firstCreditList  = separateAttandence(firstCreditList);
			
			fourthCreditList = separatePrecondition(fourthCreditList);
			thirdCreditList = separatePrecondition(thirdCreditList);
			secondCreditList = separatePrecondition(secondCreditList);
			firstCreditList = separatePrecondition(firstCreditList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}//end of separateCredit method
	
	private ArrayList<ScheduleAtomic> separateAttandence(ArrayList<ScheduleAtomic> scAtomicList){
		try {
			for(int i = 0; i < scAtomicList.size(); i++){
				if(!scAtomicList.get(i).isAttendance()){
					falseAttendanceList.add(scAtomicList.get(i));
					scAtomicList.remove(i);
				}//end if
			}//end of for loop
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scAtomicList;
	}//end of method
	
	private ArrayList<ScheduleAtomic> separatePrecondition(ArrayList<ScheduleAtomic> lsScheduleAtomic){
		ScheduleAtomic sc = null;
		for(int i = 0; i < lsScheduleAtomic.size(); i++){
			if(lsScheduleAtomic.get(i).getPreCondition().equals("")){
				sc = new ScheduleAtomic(lsScheduleAtomic.get(i));
				lsScheduleAtomic.remove(i);
				lsScheduleAtomic.add(sc);
			}//end of if
		}//end of for-loop
		return lsScheduleAtomic;
	}//end of separatePrecondition method

	public ArrayList<ScheduleAtomic> getListSorted() {
		return listSorted;
	}

	public void setListSorted(ArrayList<ScheduleAtomic> listSorted) {
		this.listSorted = listSorted;
	}

	public ArrayList<ScheduleAtomic> getFalseAttendanceList() {
		return falseAttendanceList;
	}

	public void setFalseAttendanceList(ArrayList<ScheduleAtomic> falseAttendanceList) {
		this.falseAttendanceList = falseAttendanceList;
	}

	public ArrayList<ScheduleAtomic> getFourthCreditList() {
		return fourthCreditList;
	}

	public void setFourthCreditList(ArrayList<ScheduleAtomic> fourthCreditList) {
		this.fourthCreditList = fourthCreditList;
	}

	public ArrayList<ScheduleAtomic> getThirdCreditList() {
		return thirdCreditList;
	}

	public void setThirdCreditList(ArrayList<ScheduleAtomic> thirdCreditList) {
		this.thirdCreditList = thirdCreditList;
	}

	public ArrayList<ScheduleAtomic> getSecondCreditList() {
		return secondCreditList;
	}

	public void setSecondCreditList(ArrayList<ScheduleAtomic> secondCreditList) {
		this.secondCreditList = secondCreditList;
	}

	public ArrayList<ScheduleAtomic> getFirstCreditList() {
		return firstCreditList;
	}

	public void setFirstCreditList(ArrayList<ScheduleAtomic> firstCreditList) {
		this.firstCreditList = firstCreditList;
	}
}
