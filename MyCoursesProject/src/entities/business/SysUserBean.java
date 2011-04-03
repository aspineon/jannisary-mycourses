package entities.business;

/**
 * 
 */


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import entities.dao.SysUser;

/**
 * @author Nick Belaevski - nbelaevski@exadel.com created 02.03.2007
 * 
 */
public class SysUserBean {

	private SysUser currentItem = new SysUser();

	public void fetchCurrentRow(ActionEvent event) {
		String strUserId = (FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("UserId"));
		currentRow = Integer.parseInt(FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("row"));
		for (SysUser item : allUsers) {
			if (item.getUserId().toString().equals(strUserId)){
				currentItem=item;
				break;
			}
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

	

	
	public void store() {
		/*
		allCars.set(currentRow, currentItem);
		keys.clear();
		keys.add(currentRow);
		*/
		
	}

	public void delete() {
//		allCars.remove(currentRow);
		currentItem.deleteUser();
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


