package entities.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ActionEvent;

public class TableLockBean 
{
	private List<WeekSchedule> daysList;
	private WeekSchedule selectedDay;
	private Set<Integer> rowsToUpdate;
	
	public TableLockBean()
	{
		daysList = new ArrayList<WeekSchedule>();
		daysList.add(new WeekSchedule("1", "Monday"));
		daysList.add(new WeekSchedule("2", "Tuesday"));
		daysList.add(new WeekSchedule("3", "Wednesday"));
		daysList.add(new WeekSchedule("4", "Thursday"));
		daysList.add(new WeekSchedule("5", "Friday"));
		
		rowsToUpdate = new HashSet<Integer>();
		
	}

	public List<WeekSchedule> getDaysList() {
		return daysList;
	}

	public void setDaysList(List<WeekSchedule> daysList) {
		this.daysList = daysList;
	}

	public WeekSchedule getSelectedDay() {
		return selectedDay;
	}

	public void setSelectedDay(WeekSchedule selectedDay) {
		this.selectedDay = selectedDay;
	}

	public Set<Integer> getRowsToUpdate() {
		return rowsToUpdate;
	}

	public void setRowsToUpdate(Set<Integer> rowsToUpdate) {
		this.rowsToUpdate = rowsToUpdate;
	}
	
	public String rowClickAction()
	{
		System.out.println("Row has been clicked!!");
		selectedDay.setSelected(!selectedDay.isSelected());
		rowsToUpdate.clear();
		rowsToUpdate.add(daysList.indexOf(selectedDay));
		
		return null;
	}
	
}
