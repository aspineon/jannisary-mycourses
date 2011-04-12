package entities.business;

import java.util.*;

import javax.annotation.PostConstruct;

public class DeanBean 
{
	private ArrayList<Integer> list;
	@PostConstruct
	public void init()
	{
		list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
	}
	
	public ArrayList<Integer> getList()
	{
		
		return list;
	}
}
