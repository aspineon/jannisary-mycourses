package entities.utility;

import java.util.ArrayList;
import entities.dao.Syllabus;

public class OrderedTable {
	
	private java.lang.String courseName;
	private java.lang.String lecturerName;

	public OrderedTable() {
	}
	
	public OrderedTable(java.lang.String cName, java.lang.String lName) {
		super();
		this.courseName = cName;
		this.lecturerName = lName;
	}
	
	public String toString() {
		return this.courseName + ":" + this.lecturerName;
	}
	
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.courseName == null) ? 0 : this.courseName.hashCode());
        result = prime * result + ((this.lecturerName == null) ? 0 : this.lecturerName.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final OrderedTable other = (OrderedTable) obj;
        if (courseName == null) {
            if (other.courseName != null)
                return false;
        } else if (!courseName.equals(other.courseName))
            return false;
        if (lecturerName == null) {
            if (other.lecturerName != null)
                return false;
        } else if (!lecturerName.equals(other.lecturerName))
            return false;
        return true;
    }
	
//***********************************************************
	
    public ArrayList<OrderedTable> getOrderedTableByGrade(int grade) {
		ArrayList<OrderedTable> orderedTableList = new ArrayList<OrderedTable>();
		
		Syllabus sItem = new Syllabus();
		ArrayList<Syllabus> sList = sItem.getSyllabusByGrade(grade);
		for(int i = 0; i < sList.size(); i++) {
			java.lang.String courseName = sList.get(i).getCourse().getCourseName();
			java.lang.String lecturerName = sList.get(i).getLecturer().getLecturerName();
			OrderedTable ort = new OrderedTable(courseName, lecturerName);
			orderedTableList.add(ort);
		}
		return orderedTableList;
	}
	
//***********************************************************
	
	public String getCourseName() {
		return this.courseName;
	}
	
	public void setCourseName(String cName) {
		this.courseName = cName;
	}
	
	public String getLecturerName() {
		return this.lecturerName;
	}
	
	public void setLecturerName(String lName) {
		this.lecturerName = lName;
	}

}
