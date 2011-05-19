package entities.business;


import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import entities.dao.SysUser;

public class LoginBean 
{
	
	public String clickBtnLogOut(){
		getLoginUser().setUserName("");
		getLoginUser().setUserStatus("");
		return null;
	}
	
	/********************Sınıf Metodları****************************/
	
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
				getLoginUser().setUserName("");
				returnString = "failure";
				return returnString;
			}
			else{	
				FacesContext context = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
				session.setAttribute("username", sysUserList.get(0).getUserName());
				getLoginUser().setUserName(sysUserList.get(0).getUserName());
				getLoginUser().setUserStatus(sysUserList.get(0).getUserStatus().toLowerCase());
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
	
	/******************Sınıf Getter-Setter metodları*********************/
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
	
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public static LoginBean getLoginUser(){
		if(loginUser == null){
			loginUser = new LoginBean();
		}
		return loginUser;
	}
	
	
	/**********Sınıf Alt Alanları*******/
	private static LoginBean loginUser;
	private String userName="";
	private String password="";
	private String userStatus ="";
}
