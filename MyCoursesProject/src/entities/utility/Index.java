package entities.utility;

public class Index {
	
	private String day = "";
	private int hour = 0;
	
	public Index() {	
		super();
	}
	
	public Index(String pDay, int pHour) {
		super();
		this.day = pDay;
		this.hour = pHour;
	}
	
	public Index(Index item) {
		super();
		this.day = item.getDay();
		this.hour = item.getHour();
	}
	
	public Index(int row, int col) {
		super();
		this.day = "";
		if(row == 0) { this.day = "Monday"; }
		if(row == 1) { this.day = "Tuesday"; }
		if(row == 2) { this.day = "Wednesday"; }
		if(row == 3) { this.day = "Thursday"; }
		if(row == 4) { this.day = "Friday"; }
		this.hour = col + 1; 
	} 
	
	@Override
	public String toString() {
		String retStr = "\n\n *** Index ***\n";
		retStr = retStr + "Day: " + this.day + " | Hour: " + this.hour;
		return retStr;
	}
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

}
