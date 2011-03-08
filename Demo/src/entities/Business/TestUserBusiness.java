package entities.Business;


import entities.DOMAIN.TestUser;
import java.util.List;

public class TestUserBusiness {
	
	public TestUserBusiness(){
		
	}
	
	public TestUserBusiness(String userName, String userPass){
		
		_userName = userName;
		_userPass = userPass;
	}
	
	
	
	public String get_userName() {
		return _userName;
	}



	public void set_userName(String _userName) {
		this._userName = _userName;
	}



	public String get_userPass() {
		return _userPass;
	}



	public void set_userPass(String _userPass) {
		this._userPass = _userPass;
	}



	public String queryUser(TestUser tUser){
		
		TestUserBusiness tstUserBusiness = new TestUserBusiness(tUser.getUserName(),tUser.getUserPass());
		List<TestUser> ls = (List<TestUser>) tUser.queryUser(tstUserBusiness);
		String strName = ls.get(0).getUserName();
		return strName;
	}
	
	
	
	private String _userName;
	private String _userPass;
}
