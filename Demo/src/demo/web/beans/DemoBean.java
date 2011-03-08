package demo.web.beans;

import entities.Business.TestUserBusiness;
import entities.DOMAIN.TestUser;

public class DemoBean {
	public String queryUserBean(TestUser tUser)
	{
		String strUserName= "";
		TestUserBusiness tUserBusiness = new TestUserBusiness();
		strUserName = tUserBusiness.queryUser(tUser);
		return strUserName;
	}
}
