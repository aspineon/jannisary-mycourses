package entities.utility;

import java.util.ArrayList;
import entities.utility.ScheduleAtomic;


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
	
	public ArrayList<ScheduleAtomic> convertToOneList() {
		ArrayList<ScheduleAtomic> retList = new ArrayList<ScheduleAtomic>();
		for(int i = 0; i < this.fourthCreditList.size(); i++) {
			retList.add(this.fourthCreditList.get(i));
		}
		for(int i = 0; i < this.thirdCreditList.size(); i++) {
			retList.add(this.thirdCreditList.get(i));
		}
		for(int i = 0; i < this.secondCreditList.size(); i++) {
			retList.add(this.secondCreditList.get(i));
		}
		for(int i = 0; i < this.firstCreditList.size(); i++) {
			retList.add(this.firstCreditList.get(i));
		}
		return retList;
	}
	
	public boolean isEmpty() {
		int total = 0;
		boolean result = true;
		total = total + this.firstCreditList.size(); 
		total = total + this.secondCreditList.size();
		total = total + this.thirdCreditList.size();
		total = total + this.fourthCreditList.size();
		total = total + this.falseAttendanceList.size();
		if(total != 0) { result = false; }
		return result;
	}
	
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

	/*Added by Erhun(11.05.2011)*/
	public SortedList forwardAllList(int intVal){
		this.firstCreditList = this.forward(firstCreditList, intVal);
		this.secondCreditList = this.forward(secondCreditList, intVal);
		this.thirdCreditList = this.forward(thirdCreditList, intVal);
		this.fourthCreditList = this.forward(fourthCreditList, intVal);
		return this;
	}
	/*Added by Erhun(11.05.2011)*/
	public ArrayList<ScheduleAtomic> forward(ArrayList<ScheduleAtomic> listUnmarkedList, int intVal){
		ArrayList<Integer> listKnowledge = null;
		try {
			for(int i=0; i < listUnmarkedList.size();i++){
				listKnowledge = listUnmarkedList.get(i).getKnowledge();
				if(listKnowledge.contains(intVal)){
					listKnowledge.remove(intVal);
				}//end of if block
			}//end of for loop
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return listUnmarkedList;
	}//end of forward method
	
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
