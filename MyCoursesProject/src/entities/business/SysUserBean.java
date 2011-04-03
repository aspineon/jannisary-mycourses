package entities.business;

/**
 * 
 */


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import entities.dao.SysUser;

/**
 * @author Nick Belaevski - nbelaevski@exadel.com created 02.03.2007
 * 
 */
public class SysUserBean {

	private SysUser currentItem = new SysUser();


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

	
	private Set<Integer> keys = new HashSet<Integer>();
	private int currentRow;
	
	

	private List<SysUser> allUsers = null;
}


