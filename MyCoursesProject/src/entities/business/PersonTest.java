package entities.business;

public class PersonTest {
	
	

	public String getName(){
		return name;
	}
	
	public String getMiddleName(){
		return middleName;
	}
	
	
	public void setMiddleName(java.lang.String middleName) {
		this.middleName = middleName;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	private String name = "Erhun";
	private String middleName ="Baycelik";
}
