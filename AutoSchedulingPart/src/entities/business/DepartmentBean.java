package entities.business;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import entities.dao.Department;

public class DepartmentBean 
{
	Department department = new Department();
	ArrayList<SelectItem> selectItemList = new ArrayList<SelectItem>();
	/*
	public String getDepartmentCode() throws SQLException
	{
							
		String deptCodeVal = department.getDeptCode();
		departmentList.add(deptCodeVal);
		
		return null;
	}
	*/
	/*
	public void selectionChange(ValueChangeEvent evt)
	{
		String selectedValue = (String)evt.getNewValue();
		if(!selectedValue.equals(""))
		{
			department.setDeptCode(selectedValue);
		}
	}
*/
	public ArrayList<SelectItem> getSelectItemList() {
		System.out.println("asd");
		synchronized (this) {
			if(departmentList == null)
			{
				departmentList = new ArrayList<Department>();
				try
				{
					departmentList = department.getDepartmentNameList();
					
					for(int i = 0; i < departmentList.size(); i++)
					{
						String departmentDesc = departmentList.get(i).getDeptDescription();
						selectItemList.add(new SelectItem(departmentDesc));
					}
				}
				catch(Exception ex)
				{
					System.err.println(ex.getMessage());
				}
			}
		}
		
		return selectItemList;
	}
	public void setSelectItemList(ArrayList<SelectItem> selectItemList) {
		this.selectItemList = selectItemList;
	}
	
	
/*
	public ArrayList<SelectItem> getCourseCodeList() {
		synchronized (this) {
			if (courseCodeList == null) {
				courseCodeList = new ArrayList<SelectItem>();
					try {
						int i;
						for(i=0; i<allSyllabusList.size(); i++){
							int courseId = allSyllabusList.get(i).getCourseId();
							course.setCourseId(courseId);
							String courseCode = course.getCourseCodeById().get(0).getCourseCode();
							courseCodeList.add(new SelectItem(courseCode));
						}
						
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllSyllabus Error: "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}
		
		return courseCodeList;
	}
	*/
	/*public void setDepartmentList(ArrayList<String> departmentList) {
		System.out.println("getDeprtmentList");
		this.departmentList = departmentList;
	}*/	
	
	ArrayList<Department> departmentList = null;
	
}
