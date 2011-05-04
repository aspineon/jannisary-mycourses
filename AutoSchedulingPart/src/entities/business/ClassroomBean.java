package entities.business;

import java.util.ArrayList;
import javax.faces.model.SelectItem;
import entities.dao.Classroom;
import entities.dao.Department;

public class ClassroomBean 
{
	Classroom classroom = new Classroom();
	ArrayList<SelectItem> selectItemListClassroom = new ArrayList<SelectItem>();
	
	public ArrayList<SelectItem> getSelectItemListClassroom() {
		System.out.println("asd");
		synchronized (this) {
			if(classroomList == null)
			{
				classroomList = new ArrayList<Classroom>();
				try
				{
					classroomList = classroom.getClassroomNameList();
					
					for(int i = 0; i < classroomList.size(); i++)
					{
						String departmentDesc = classroomList.get(i).getClassroomCode();
						selectItemListClassroom.add(new SelectItem(departmentDesc));
					}
				}
				catch(Exception ex)
				{
					System.err.println(ex.getMessage());
				}
			}
		}
		
		return selectItemListClassroom;
	}
	
	ArrayList<Classroom> classroomList = null;
}
