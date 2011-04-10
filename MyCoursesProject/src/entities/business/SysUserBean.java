package entities.business;

/**
 * 
 */


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


import entities.dao.SysUser;

/**
 * @author Nick Belaevski - nbelaevski@exadel.com created 02.03.2007
 * 
 */
public class SysUserBean {

	public SysUserBean() {
		selectItems.add(new SelectItem("User"));
		selectItems.add(new SelectItem("Admin"));
	}
	
	private SysUser currentItem = new SysUser();
	List<SelectItem> selectItems = new ArrayList<SelectItem>();

	/* Bug selectionChanged method***********
	 * Su an bu olay en son değişikliği kaydediyor, o yüzden ekranda ekleme
	 * işelmi yaparken en son başka bir alan değiştirilirse o değeri alıyor
	 * */
	 public void selectionChanged(ValueChangeEvent  evt) {
		 String selectedValue = (String) evt.getNewValue();
		 

		 if (!selectedValue.equals("")) {
			 currentItem.setUserStatus(selectedValue);
		 }
	}
	
	public List<SysUser> getAllUsers() {
		synchronized (this) {
			if (allUsers == null) {
				allUsers = new ArrayList<SysUser>();
					try {
						allUsers = currentItem.getAllUser();
						//listSeperator();
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllUsers Error: "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}
		return allUsers;
	}

	

	public String addUser(){
		try{
			
			
			int size = allUsers.size();		
			
			SysUser sysUser = new SysUser(currentItem);
			allUsers.add(size,sysUser);
			sysUser.addUser();
			keys.clear();
			keys.add(allUsers.size());
			/*SysUser.jsp'de yer alan User Name ve User Password alanlarını temizle*/
			currentItem.setUserName("");
			currentItem.setUserPassword("");
			
			
			
		}catch(Exception ex){
			System.err.println(ex.getMessage());}
		return null;
	}
	
	public void store() {
		/*
		allCars.set(currentRow, currentItem);
		keys.clear();
		keys.add(currentRow);
		*/
		
		/*try-catch blogu eklenecek*/
		try{
			currentItem = allUsers.get(currentRow);
			currentItem.updateUser();
			allUsers.set(currentRow, currentItem);
			keys.clear();
			keys.add(currentRow);
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}

	
	public void delete() {
		
		/* try-catch bloğu eklenecek
		 *Önce veritabanımızdan siliyoruz, ardından listeden siliyoruz.
		 *Olası bir veritabanı hatasında ve silmeme probleminde listeden
		 *de silinmeyecek ve kullanıcı veritabanı hatasından bilgilendirilecektir.
		 * 
		 */
		currentItem = allUsers.get(currentRow);
		currentItem.deleteUser();
		allUsers.remove(currentItem);
	}
	
	public List<SelectItem> getSelectItems(){
		return selectItems;
	}
	
	public SysUser getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(SysUser currentItem) {
		this.currentItem = currentItem;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public Set<Integer> getKeys() {
		return keys;
	}

	public void setKeys(Set<Integer> keys) {
		this.keys = keys;
	}

	
	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getUserStatus() {
		return userStatus;
	}




	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}




	public String getUserPassword() {
		return userPassword;
	}




	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	private Set<Integer> keys = new HashSet<Integer>();
	private int currentRow;
	
	
	private String userName;
	private String userStatus;
	private String userPassword;
	
	
	private List<SysUser> allUsers = null;
}


