package entities.business;

import java.util.ArrayList;

import entities.dao.SyllabusArchive;

public class SyllabusArchiveBean {
	private String semester;
	private Integer year;
	private String filePath;
	private String versionName;
	
	private SyllabusArchive syllabusArchive = new SyllabusArchive();
	
	public SyllabusArchiveBean(){
		
	}
	
	public SyllabusArchiveBean(String semester, Integer year, String filePath, String versionName){
		this.semester =  semester;
		this.year = year;
		this.filePath = filePath;
		this.versionName = versionName;
	}
	
	public void addSyllabusArchive(){
		
		syllabusArchive.setYear(this.year);
		syllabusArchive.setSemester(this.semester);
		syllabusArchive.setFilePath(this.filePath);
		syllabusArchive.setVersionName(this.versionName);
		
		syllabusArchive.addSyllabusArchive();
	}
	
	public int getSyllabusArchiveIdByVersionName(){
		int intSyllabusArchiveId = -1;
		
		ArrayList<SyllabusArchive> syllabusArchiveList = new ArrayList<SyllabusArchive>(); 
		syllabusArchive.setVersionName(this.versionName);
		syllabusArchiveList = syllabusArchive.GetSyllabusArchiveIdByVersionName();
		intSyllabusArchiveId = syllabusArchiveList.get(0).getSyllabusArchiveId();
		
		return intSyllabusArchiveId;
	}//end of getSyllabusArchiveIdByVersionName method

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	
	
}
