package entities.business;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
		SysUser sysUserObj = new SysUser();
		List<SysUser> sysUserList;
		
		try{
			sysUserObj.setUserName(userName);
			sysUserObj.setUserPassword(password);
			
			sysUserList = sysUserObj.getSysUserByUsernameAndPassword();
			
			if(sysUserList.size() == 0){
				System.err.println("Login Fail");
				returnString = "failure";
				return returnString;
			}
			else{	
				FacesContext context = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
				session.setAttribute("username", userName);
				System.out.println("Success");
				returnString = "success";
				return returnString;				
			}
		
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}
}
