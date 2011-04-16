package entities.business;

public class WeekSchedule 
{
	private boolean selected;
	private String hours;
	private String days;
	
	public WeekSchedule()
	{
		
	}
	
	public WeekSchedule(String hours, String days)
	{
		this.hours = hours;
		this.days = days;
	}
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	
	
}
