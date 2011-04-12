package entities.business;

import java.util.*;

import javax.annotation.PostConstruct;

public class DeanBean 
{
	private ArrayList<Integer> dayList;
	
	@PostConstruct
	public void init()
	{
		dayList = new ArrayList<Integer>();
		dayList.add(1);
		dayList.add(2);
		dayList.add(3);
		dayList.add(4);
		dayList.add(5);
		dayList.add(6);
		dayList.add(7);
		dayList.add(8);
	}
	
	public ArrayList<Integer> getList()
	{
		return dayList;
	}
}
