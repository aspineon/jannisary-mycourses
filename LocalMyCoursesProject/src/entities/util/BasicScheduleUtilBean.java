package entities.util;


public class BasicScheduleUtilBean {
	private String courseName;
	private int teoricHours;
	private int practiceHours;
	private String lecturerName;
	private int classroomId;
	private int lecturerId;
	private int courseId;
	private int sectionNo;
	private int syllabusId;
	private String courseType;
	private String courseTheoricOrPraticName;
	private int timeofCourse;
	private int hours;
	private int year;
	private String semester;
	private int scheduleId;
	private int syllabusArchiveId;
	private String lectureTitle;
	

	public BasicScheduleUtilBean(){
		
	}
	
	
	
	public BasicScheduleUtilBean(BasicScheduleUtilBean bs){
		this.courseName = bs.getCourseName();
		this.teoricHours = bs.getTeoricHours();
		this.practiceHours = bs.getPracticeHours();
		this.lecturerName = bs.getLecturerName();
		this.classroomId = bs.getClassroomId();
		this.lecturerId = bs.getLecturerId();
		this.courseId = bs.getCourseId();
		this.sectionNo = bs.getSectionNo();
		this.syllabusId = bs.getSyllabusId();
		this.courseType = bs.getCourseType();
		this.courseTheoricOrPraticName = bs.getCourseTheoricOrPraticName();
		this.timeofCourse = bs.getTimeofCourse();
		this.hours = bs.getHours();
		this.year=bs.getYear();
		this.semester = bs.getSemester();
		this.scheduleId = bs.getScheduleId();
		this.syllabusArchiveId = bs.getSyllabusArchiveId();
		
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getTeoricHours() {
		return teoricHours;
	}
	public void setTeoricHours(int teoricHours) {
		this.teoricHours = teoricHours;
	}
	public int getPracticeHours() {
		return practiceHours;
	}
	public void setPracticeHours(int practiceHours) {
		this.practiceHours = practiceHours;
	}
	public String getLecturerName() {
		return lecturerName;
	}
	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}
	
	public int getClassroomId() {
		return classroomId;
	}
	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}
	public int getSyllabusId() {
		return syllabusId;
	}
	public void setSyllabusId(int syllabusId) {
		this.syllabusId = syllabusId;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCourseTheoricOrPraticName() {
		return courseTheoricOrPraticName;
	}
	public void setCourseTheoricOrPraticName(String courseTheoricOrPraticName) {
		this.courseTheoricOrPraticName = courseTheoricOrPraticName;
	}
	public int getTimeofCourse() {
		return timeofCourse;
	}
	public void setTimeofCourse(int timeofCourse) {
		this.timeofCourse = timeofCourse;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getLecturerId() {
		return lecturerId;
	}

	public void setLecturerId(int lecturerId) {
		this.lecturerId = lecturerId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(int sectionNo) {
		this.sectionNo = sectionNo;
	}
	
	public int getSyllabusArchiveId() {
		return syllabusArchiveId;
	}

	public void setSyllabusArchiveId(int syllabusArchiveId) {
		this.syllabusArchiveId = syllabusArchiveId;
	}



	public String getLectureTitle() {
		return lectureTitle;
	}



	public void setLectureTitle(String lectureTitle) {
		this.lectureTitle = lectureTitle;
	}
	
	
	
}
