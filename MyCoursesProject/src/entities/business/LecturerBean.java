package entities.business;

import entities.dao.Lecturer;

public class LecturerBean {
	private String lecturerName;
	private Lecturer lecturer = new Lecturer();
	
	public String AddLecturer(){
		lecturer.setLecturerName(lecturerName);
		lecturer.AddLecturer();
		return null;
	}

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}
	
	
}
