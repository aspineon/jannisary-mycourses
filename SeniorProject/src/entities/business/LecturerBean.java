package entities.business;

import entities.dao.Lecturer;

public class LecturerBean {
	
	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}
	
	public String AddLecturer(){
		System.out.println("LecturerBean: AddLecturer " + lecturerName);
		lecturer = new Lecturer();
		lecturer.setLecturerName(lecturerName);
		System.out.println("LecturerBean: AddLecturer " + lecturerName);
		lecturer.AddLecturer();
		return null;
	}
	
	private Lecturer lecturer;
	private String lecturerName;
	
}
