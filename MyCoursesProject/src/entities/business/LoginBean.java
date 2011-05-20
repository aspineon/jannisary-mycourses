package entities.business;


import java.io.IOException;
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
		//String returnString = "success";
		SysUser sysUserObj = new SysUser();
		List<SysUser> sysUserList;
		
		try{
			sysUserObj.setUserName(userName);
			sysUserObj.setUserPassword(password);
			
			sysUserList = sysUserObj.getSysUserByUsernameAndPassword();
			
			if(sysUserList.size() == 0){
				//System.err.println("Login Fail");
				getLoginUser().setUserName("");
				FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/MyCoursesProject/faces/PreProd/login.jsp");
				//returnString = "failure";
				return null;
			}
			else{	
				FacesContext context = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
				session.setAttribute("username", sysUserList.get(0).getUserName());
				getLoginUser().setUserName(sysUserList.get(0).getUserName());
				getLoginUser().setUserStatus(sysUserList.get(0).getUserStatus().toLowerCase());
				FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/MyCoursesProject/faces/PreProd/index.jsp");
				//System.out.println("Success");
				//returnString = "success";
				return null;				
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
	
	public String getIsUser() {
		if(!(LoginBean.getLoginUser().getUserStatus().equals("admin") || LoginBean.getLoginUser().getUserStatus().equals("user")) ){
	 		try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/MyCoursesProject/faces/PreProd/login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	}
		return isUser;
	}

	public String getIsAdmin() {
		if(LoginBean.getLoginUser().getUserStatus().equals("user")){
	 		try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/MyCoursesProject/faces/PreProd/index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	}
		else if(!LoginBean.getLoginUser().getUserStatus().equals("admin")){
	 		try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/MyCoursesProject/faces/PreProd/login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	}
		
		
		return isAdmin;
	}

	public void setIsUser(String isUser) {
		this.isUser = isUser;
	}



	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**********Sınıf Alt Alanları*******/
	private String isUser="";
	private String isAdmin="";
	private static LoginBean loginUser;
	private String userName="";
	private String password="";
	private String userStatus ="";
}
