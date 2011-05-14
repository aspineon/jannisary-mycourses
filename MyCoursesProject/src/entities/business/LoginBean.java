package entities.business;

import java.util.List;
import javax.servlet.http.*;
import entities.dao.SysUser;

public class LoginBean 
{
	String userName;
	String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String checkLogin()
	{
		String returnString = "success";
		HttpServletResponse response = null;
		SysUser sysUserObj = new SysUser();
		List<SysUser> sysUserList;
		
		try
		{
			sysUserObj.setUserName(userName);
			sysUserObj.setUserPassword(password);
			
			sysUserList = sysUserObj.getSysUserByUsernameAndPassword();
			
			if(sysUserList.size() == 0)
			{
				System.err.println("Login Fail");
				returnString = "failure";
				return returnString;
				
			}
			else
			{
				System.out.println("Success");
				returnString = "success";
				return returnString;
				//response.sendRedirect(response.encodeRedirectUrl("http://localhost:8080/MyCoursesProject/faces/PreProd/index.jsp"));
			}
		
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	
}
